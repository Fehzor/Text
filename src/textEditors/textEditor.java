package textEditors;

import models.textModelBasic;
import display.textPanel;
import display.displayFrame;
import utility.letterTuple;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;

public class textEditor extends textPanel{
	public static final textEditor text = new textEditor(displayFrame.DEFAULT_MAIN_HEIGHT,displayFrame.DEFAULT_MAIN_WIDTH);

	public static int x = 0;
	public static int y = 0;
	public static String message = "";

	public static ArrayList<ArrayList<letterTuple>> image;
	
	public static final int MOVEMENT_MODE = 0;
	public static final int COMMAND_MODE = 1;
	public static final int DRAW_MODE = 2;
	public static final int REPLACE_MODE = 3;
	public static final int DETAIL_MODE = 4;
        public static final int ERASE_MODE = 5;
	public static int mode = MOVEMENT_MODE;
	
	public static letterTuple marker = new letterTuple('#',Color.WHITE,Color.WHITE);
        public static boolean flashing = false;
        
	public static boolean shift = false;	

	private textEditor(int height, int width){
		super(height,width);
                
                //This thread calls the flash method over and over again, so that you can see the cursor.
                Thread markerflash = new Thread(){
                    
                    
                    public void run(){
                        textEditor.text.flash();
                        try{
                            Thread.sleep(1100);
                        } catch (Exception e){}
                        run();
                    }
                }; markerflash.start();
                
		textEditor.image = new ArrayList<ArrayList<letterTuple>>();

		for(int row=0;row<height;++row){
			textEditor.image.add(new ArrayList<letterTuple>());
			for(int col=0;col<width;++col){
				textEditor.image.get(row).add(new letterTuple(' ',Color.BLACK,Color.BLACK));
				
				updateAt(image.get(row).get(col),col,row);
			}
		}
		
		makeKeyListener(this);
	}
        
        //This method inverts the cursor temporarily.
        public static void flash(){
            if(textEditor.flashing){
                    textEditor.flashing = false;
                    textEditor.text.updateAt(textEditor.marker,textEditor.x,textEditor.y);

            } else {
                textEditor.flashing = true;
                letterTuple markerFlash = new letterTuple(
                        textEditor.marker.letter(),
                        new Color(16777215 - textEditor.marker.background().getRGB()),
                        new Color(16777215 - textEditor.marker.foreground().getRGB()));
                textEditor.text.updateAt(markerFlash,textEditor.x,textEditor.y);
            }
            
            
            textEditor.text.repaint();
        }
	
	//Sets the screen to the current image + the cursor
	public static void refreshAll(){
		for(int row=0;row<textEditor.image.size();++row){
			for(int col=0;col<textEditor.image.get(0).size();++col){			
				textEditor.text.updateAt(image.get(row).get(col),col,row);
			}
		}
		
		textEditor.text.updateAt(textEditor.marker,textEditor.x,textEditor.y);
		textEditor.text.repaint();
	}

	public static void main(String [] args){
		displayFrame DF = new displayFrame(text, "Text Editor");
	}

	public static void makeKeyListener(textEditor text){
		KeyListener key = new KeyListener(){
			public void keyPressed(KeyEvent e){
				refreshAll();
				if(e.getKeyCode() == KeyEvent.VK_SEMICOLON){
					textEditor.mode = COMMAND_MODE;
                                        textEditor.image.get(y).get(x).set(new letterTuple(' ',Color.BLACK,Color.BLACK));
				
				} else if(e.getKeyCode() == KeyEvent.VK_A && textEditor.mode!=COMMAND_MODE){
					textEditor.setLetterTuple();
	
				} else if(e.getKeyCode() == KeyEvent.VK_O && textEditor.mode!=COMMAND_MODE){
					textEditor.mode = ERASE_MODE;
                                
                                } else if(e.getKeyCode() == KeyEvent.VK_J && textEditor.mode!=COMMAND_MODE){
					textEditor.mode = DRAW_MODE;
					image.get(y).get(x).setBackground(textEditor.marker.background());
				
				} else if(e.getKeyCode() == KeyEvent.VK_I && textEditor.mode!=COMMAND_MODE){
					textEditor.mode = REPLACE_MODE;
					image.get(y).get(x).setBackground(textEditor.marker.background());
					image.get(y).get(x).setForeground(textEditor.marker.foreground());
					image.get(y).get(x).setLetter(textEditor.marker.letter());
				
				} else if(e.getKeyCode() == KeyEvent.VK_L && textEditor.mode!=COMMAND_MODE){
					textEditor.mode = DETAIL_MODE;
					image.get(y).get(x).setForeground(textEditor.marker.foreground());
					image.get(y).get(x).setLetter(textEditor.marker.letter());	

				}  else if(e.getKeyCode() == KeyEvent.VK_K && textEditor.mode != COMMAND_MODE){
					textEditor.mode = MOVEMENT_MODE;
				
				}  else if(textEditor.mode == MOVEMENT_MODE){
					textEditor.moveCursor(e);
				
				} else if(textEditor.mode == COMMAND_MODE){
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						//textEditor.mode = MOVEMENT_MODE;
						textEditor.executeCommand();
						//textEditor.refreshAll();
						return;
					} else if(e.getKeyCode() == KeyEvent.VK_SPACE){
						textEditor.message+=" ";
					} else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
						textEditor.message = textEditor.message.substring(0,textEditor.message.length()-1);
					} else {
						textEditor.message+=KeyEvent.getKeyText(e.getKeyCode());
					}
				
					textEditor.text.placeString(message,0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
					textEditor.text.repaint();

				} else if(textEditor.mode == DRAW_MODE){
					image.get(y).get(x).setBackground(textEditor.marker.background());
					textEditor.moveCursor(e);
					image.get(y).get(x).setBackground(textEditor.marker.background());

				} else if(textEditor.mode == REPLACE_MODE){
					image.get(y).get(x).setBackground(textEditor.marker.background());
					image.get(y).get(x).setForeground(textEditor.marker.foreground());
					image.get(y).get(x).setLetter(textEditor.marker.letter());
					textEditor.moveCursor(e);	
					image.get(y).get(x).setBackground(textEditor.marker.background());
					image.get(y).get(x).setForeground(textEditor.marker.foreground());
					image.get(y).get(x).setLetter(textEditor.marker.letter());
	
				} else if(textEditor.mode == DETAIL_MODE){
					image.get(y).get(x).setForeground(textEditor.marker.foreground());
					image.get(y).get(x).setLetter(textEditor.marker.letter());
					textEditor.moveCursor(e);	
					image.get(y).get(x).setForeground(textEditor.marker.foreground());
					image.get(y).get(x).setLetter(textEditor.marker.letter());

				} else if(textEditor.mode == ERASE_MODE){
					textEditor.moveCursor(e);	
                                        image.get(y).get(x).set (letterTuple.TRANSPARENT);

				}
			}
			
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_SHIFT){
					textEditor.shift=false;
				}
			}
			public void keyTyped(KeyEvent e){}
		};
		
		text.setFocusable(true);
		text.addKeyListener(key);
	}

	public static void executeCommand(){
		Scanner s = new Scanner(textEditor.message);

		String type = s.next();
		
		switch (type){
			case "SAVE":
			case "S":
			try{	
				String name = s.next();
				ArrayList<ArrayList<letterTuple>> model = new ArrayList<ArrayList<letterTuple>>();
				
				for(int row=0;row<textEditor.y;++row){
					model.add(new ArrayList<letterTuple>());
					for(int col=0;col<textEditor.x;++col){
						model.get(row).add(textEditor.image.get(row).get(col));
					}
				}
				
				textModelBasic toSave = new textModelBasic(model);
				
				PrintWriter out = new PrintWriter(new File(name));
				
				toSave.saveFile(out);
			} catch (Exception e){}
			break;

			case "LOAD":
			try{
				String name = s.next();
				Scanner scan = new Scanner(new File(name));
				textModelBasic toLoad = new textModelBasic(scan);
				ArrayList<ArrayList<letterTuple>> model = toLoad.display();

				for(int row=0;row<toLoad.height();++row){
					for(int col=0;col<toLoad.width();++col){
						letterTuple set = model.get(row).get(col);
						textEditor.image.get(row).get(col).set(set);
					}
				}
				
			} catch (Exception e){}
			break;
			
			case "BACK":
			case "B":
			textEditor.marker.setBackground(JColorChooser.showDialog(null,"Choose Background Color",Color.BLACK));
			break;
			
			case "LETTERCOLOR":
			case "LC":
			textEditor.marker.setForeground(JColorChooser.showDialog(null,"Choose Letter Color",Color.BLACK));
			break;
	
			case "LETTER":
			case "L":
			textEditor.setText();			
			break;

			case "TRANSPARENT":
			case "T":
			textEditor.marker.set(letterTuple.TRANSPARENT);
			break;	

			case "SETUP":
			try{
				int h = s.nextInt();
				int w = s.nextInt();
				
				for(int row=0;row<textEditor.image.size();++row){
					for(int col=0;col<textEditor.image.get(0).size();++col){
						if(row<h && col<w){
							textEditor.image.get(row).get(col).set(letterTuple.TRANSPARENT);
						} else {
							textEditor.image.get(row).get(col).set(marker);
						}
					}
				}
			} catch (Exception e){}
			break;

			case "HELP":
			case "H":
			if (s.hasNext()){
				type = s.next();
				switch(type){
					case "COMMAND":
					textEditor.text.placeString("COMMANDS ARE:",0,displayFrame.DEFAULT_MAIN_HEIGHT-2);
					textEditor.text.placeString("SAVE,LOAD,BACK,LETTERCOLOR,LETTER,TRANSPARENT",0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
					break;
					
					case "MODE":
					textEditor.text.placeString("MODES ARE:",0,displayFrame.DEFAULT_MAIN_HEIGHT-2);
					textEditor.text.placeString("I=REPLACEALL, J=BACKGROUND, L=DETAIL, K=MOVEMENT",0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
					break;
					
					case "HOTKEYS":
					textEditor.text.placeString("HOTKEYS ARE:",0,displayFrame.DEFAULT_MAIN_HEIGHT-2);
					textEditor.text.placeString("HOLD SHIFT=MOVE 5 TILES, A=SET MARKER (ALL)",0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
					break;

					case "BACK":
					textEditor.text.placeString("BACK; B",0,displayFrame.DEFAULT_MAIN_HEIGHT-2);
					textEditor.text.placeString("SETS THE BACKGROUND OF THE MARKER.",0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
					break;

					case "LETTERCOLOR":
					textEditor.text.placeString("LETTERCOLOR; LC",0,displayFrame.DEFAULT_MAIN_HEIGHT-2);
					textEditor.text.placeString("SETS THE LETTER COLOR OF THE MARKER.",0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
					break;

					case "LETTER":
					textEditor.text.placeString("LETTER, L",0,displayFrame.DEFAULT_MAIN_HEIGHT-2);
					textEditor.text.placeString("SETS THE CHARACTER USED; COPIES FROM CLIP BOARD",0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
					break;

					case "SETUP":
					textEditor.text.placeString("SETUP <HEIGHT> <WIDTH>, L",0,displayFrame.DEFAULT_MAIN_HEIGHT-2);
					textEditor.text.placeString("PREPARES AN AREA IN THE TOP LEFT WITH ALL TRANSPARENT SPACES.",0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
					break;

				}
			} else {
				textEditor.text.placeString("HELP <COMMAND/MODE/HOTKEYS>",0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
			}
			textEditor.text.repaint();
			break;

			default:
			textEditor.text.placeString("Type HELP for help!",0,displayFrame.DEFAULT_MAIN_HEIGHT-1);
			textEditor.text.repaint();
			break;
		}

		textEditor.message = "";
		textEditor.mode = MOVEMENT_MODE;
	}
	
	public static void moveCursor(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(key){
			case KeyEvent.VK_SHIFT:
			textEditor.shift=true;
			break;			

			case KeyEvent.VK_F:
                        if(textEditor.x == displayFrame.DEFAULT_MAIN_WIDTH-1){
                            return;
                        }
			textEditor.writeOverCursor();
			textEditor.x+=1;
			if(textEditor.shift){
                            if(textEditor.x > displayFrame.DEFAULT_MAIN_WIDTH-6){
                                return;
                            }
                            textEditor.x+=4;
                        }
			break;

			case KeyEvent.VK_S:
                        if(textEditor.x == 0){
                            return;
                        }
                        textEditor.writeOverCursor();
			textEditor.x-=1;
			if(textEditor.shift){
                            if(textEditor.x < 5){
                                return;
                            }
                            textEditor.x-=4;
                        }
			break;

			case KeyEvent.VK_E:
                        if(textEditor.y == 0){
                            return;
                        }
			textEditor.writeOverCursor();
			textEditor.y-=1;
			if(textEditor.shift){
                            if(textEditor.y < 5){
                                return;
                            }
                            textEditor.y-=4;
                        }
			break;

			case KeyEvent.VK_D:
                        if(textEditor.y == displayFrame.DEFAULT_MAIN_HEIGHT-1){
                            return;
                        }
			textEditor.writeOverCursor();
			textEditor.y+=1;
			if(textEditor.shift){
                            if(textEditor.y > displayFrame.DEFAULT_MAIN_HEIGHT-6){
                                return;
                            }
                            textEditor.y+=4;
                        }
			break;

			default:
			break;
		}
		
		//letterTuple marker = new letterTuple('X',Color.WHITE,Color.BLACK);
		
		textEditor.text.updateAt(textEditor.marker,textEditor.x,textEditor.y);
		textEditor.text.repaint();
	}
	
	public static void setLetterTuple(){
		textEditor.marker.setBackground(JColorChooser.showDialog(null,"Choose Background Color",marker.background()));
		textEditor.marker.setForeground(JColorChooser.showDialog(null,"Choose Letter Color",marker.foreground()));
		textEditor.setText();
		//USE THE COLOR THING TO GET COLORS HERE!
	}
	
	//Must be called every time the cursor moves.
	public static void writeOverCursor(){
		textEditor.text.updateAt(textEditor.image.get(textEditor.y).get(textEditor.x),textEditor.x,textEditor.y);
	}

	public static void setText(){
		try{
			String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); 
			textEditor.marker.setLetter(data.charAt(0));
		} catch (Exception e) {};
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Images;
import java.util.*;
import text.Utility.*;
import java.awt.*;
import java.io.Serializable;
import text.Frame.TextDisplay;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4z
 */
public class BackGround implements Serializable{
    private ColorTuple main; //The color most of the xbackground is
    private ArrayList<Point> details; //A list of letters to add
    private HashMap<Point,Character> detailChar; //A list of where those letters are.
    private HashMap<Point,Color> detailColor; //A list of where those letters are.
    private HashMap<Point,Color> detailBack; //If not found, default.
    
    //F = Front
    private ArrayList<Point> detailsF; //A list of letters to add
    private HashMap<Point,Character> detailCharF; //A list of where those letters are.
    private HashMap<Point,Color> detailColorF; //A list of where those letters are.
    private HashMap<Point,Color> detailBackF; //If not found, default.
    
    private ColorTuple overlay; //If there is a color overlaying a portion of the screen
    
    private boolean sparkle = false;
    private ColorTuple sparkleLetter = new ColorTuple(Color.WHITE,Color.WHITE,'*');
    
    private boolean waves = false;
    private static ArrayList<ColorTuple> waveColors = new ArrayList<>();
    private static ArrayList<Point> wavePoints = new ArrayList<>();
    private static final int WAVE_CHANCE =13;
    private static final int MAX_WAVES = 75;
    
    private static ColorTuple water;// = new ColorTuple(Color.BLUE,Color.WHITE,' ');
    
    private static BackGround bg = new BackGround();
    private BackGround(){

        Color c = ImageBuilder.mergeColors(Color.BLUE, Color.CYAN);
        water = new ColorTuple(c,Color.WHITE,' ');
            
        int a = 0;
        while(a < 255){
            ColorTuple col = water.clone();
            ImageBuilder.makeTransparent(col, a);
            waveColors.add(col);
            a+=10;
        }
    }
    
    public BackGround(ColorTuple main){
        this.main = main;
        details = new ArrayList<>();
        detailChar = new HashMap<>();
        detailColor = new HashMap<>();
        detailBack = new HashMap<>();
        
        detailsF = new ArrayList<>();
        detailCharF = new HashMap<>();
        detailColorF = new HashMap<>();
        detailBackF = new HashMap<>();
    }
    
    public BackGround(ColorTuple main, String bits, int numBits){
        this.main = main;
        details = new ArrayList<>();
        detailChar = new HashMap<>();
        detailColor = new HashMap<>();
        detailBack = new HashMap<>();
        
        detailsF = new ArrayList<>();
        detailCharF = new HashMap<>();
        detailColorF = new HashMap<>();
        detailBackF = new HashMap<>();
        
        Random oRan = new Random();
        for(int i = 0; i<numBits; ++i){
            char bit = bits.charAt(oRan.nextInt(bits.length()));
            int x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
            int y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
            Point P = new Point(x,y);
            
            details.add(P);
            detailChar.put(P,bit);
            detailColor.put(P,main.secondary);
            
            detailBack.put(P,main.primary);
        }
    }
    
    public void newBits(ColorTuple main, String bits, int numBits){
        this.main = main;
        details = new ArrayList<>();
        detailChar = new HashMap<>();
        detailColor = new HashMap<>();
        detailBack = new HashMap<>();
        
        detailsF = new ArrayList<>();
        detailCharF = new HashMap<>();
        detailColorF = new HashMap<>();
        detailBackF = new HashMap<>();
        
        Random oRan = new Random();
        for(int i = 0; i<numBits; ++i){
            char bit = bits.charAt(oRan.nextInt(bits.length()));
            int x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
            int y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
            Point P = new Point(x,y);
            
            details.add(P);
            detailChar.put(P,bit);
            detailColor.put(P,main.secondary);
            
            detailBack.put(P,main.primary);
        }
    }
    
    public void addTextImageBasic(Point topLeft,TextImageBasic TIB){
        int x = topLeft.x;
        int y = topLeft.y;
        for(int i = 0; i<TIB.image.size();++i){
            for(int j = 0; j<TIB.image.get(0).size(); ++j){
                Point add = new Point(x,y);
                detailsF.add(add);
                detailCharF.put(add,TIB.image.get(i).get(j).icon);
                detailColorF.put(add,TIB.image.get(i).get(j).secondary);
                detailBackF.put(add,TIB.image.get(i).get(j).primary);
                ++x;
            }
            x = topLeft.x;
            y+= 1;
        }
    }
    
    //This is displayed under the water
    public void addTextImageBasicBack(Point topLeft,TextImageBasic TIB){
        int x = topLeft.x;
        int y = topLeft.y;
        for(int i = 0; i<TIB.image.size();++i){
            for(int j = 0; j<TIB.image.get(0).size(); ++j){
                Point add = new Point(x,y);
                detailsF.add(add);
                detailCharF.put(add,TIB.image.get(i).get(j).icon);
                detailColorF.put(add,TIB.image.get(i).get(j).secondary);
                detailBackF.put(add,TIB.image.get(i).get(j).primary);
                ++x;
            }
            x = topLeft.x;
            y+= 1;
        }
    }
    
    //
    public void addWater(int i){
      if(i == 0){
          return;
      } else {
        overlay = water;
        ImageBuilder.makeTransparent(overlay,150);
        this.waves = true;
      }
      
      
    }
    
    public void draw(Graphics g, double xSize, double ySize){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(main.primary);
        
        main.drawBack(g, 0, 0, TextDisplay.JP.getWidth(), TextDisplay.JP.getHeight());
        //main.drawFront(g, 0, 0, TextDisplay.JP.getWidth(), TextDisplay.JP.getHeight());
        
        //Draws all background details on
        for(Point P : details){
            ColorTuple bigPixel = main.clone();
            bigPixel.icon = detailChar.get(P);
            bigPixel.secondary = detailColor.get(P);
            
            try{
                bigPixel.primary =  detailBack.get(P);
            } catch(Exception E){
                //If there's no primary... well, that's fine.
                //It'll juse use the background's.
                //Most details have no backing.
                System.out.println(bigPixel.icon);
                bigPixel.primary = main.primary;
            }

            bigPixel.drawBack(g, P.x, P.y, xSize, ySize);
            bigPixel.drawFront(g, P.x, P.y, xSize, ySize);
        }
        
        if(overlay != null){


            overlay.drawBack(g, 0, 0, TextDisplay.JP.getWidth(), TextDisplay.JP.getHeight());

            if(sparkle){
                int x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X+3);
                int y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y+3);
                sparkleLetter.drawFront(g,x,y,xSize,ySize);
            }

            if(waves){
                if(oRan.nextInt(100) > WAVE_CHANCE && wavePoints.size() < MAX_WAVES){
                    //int x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X+3);
                    int y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y+3);
                    wavePoints.add(new Point(-50,y));
                }

                if(wavePoints.size() > 0){
                    for(Point p : wavePoints){
                        int pos = 0;
                        for(ColorTuple CT : waveColors){
                            CT.drawBack(g,p.x+pos,p.y,xSize*2,ySize*2);
                            pos++;
                        }
                        p.x+=1;


                    }

                    for(int i = wavePoints.size()-1; i > 0; --i){
                        Point p = wavePoints.get(i);
                        if(p.x > 100){
                            wavePoints.remove(p);
                            //System.out.println("REMOVED");
                        }
                    }
                }
            }
        }
        
        //overlay.drawFront(g, 0, 0, TextDisplay.JP.getWidth(), TextDisplay.JP.getHeight());
        //Front details
        for(Point P : detailsF){
            ColorTuple bigPixel = main.clone();
            bigPixel.icon = detailCharF.get(P);
            bigPixel.secondary = detailColorF.get(P);
            
            try{
                bigPixel.primary =  detailBackF.get(P);
            } catch(Exception E){
                //If there's no primary... well, that's fine.
                //It'll juse use the background's.
                //Most details have no backing.
                System.out.println(bigPixel.icon);
                bigPixel.primary = main.primary;
            }

            bigPixel.drawBack(g, P.x, P.y, xSize, ySize);
            bigPixel.drawFront(g, P.x, P.y, xSize, ySize);
        }
    }
}

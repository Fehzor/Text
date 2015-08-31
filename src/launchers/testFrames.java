package launchers;

import mechanics.item;
import mechanics.input;
import mechanics.actor;
import models.textModelBasic;
import display.worldPanel;
import display.displayFrame;
import display.textPanel;
import utility.letterTuple;
import utility.letterTupleComplex;
import utility.properties.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import mechanics.*;


public class testFrames{
	public static displayFrame DF;
	public static worldPanel WP;

	public static void main( String [] args ){	
		input in = new input();
		WP = new worldPanel( new world() , in );
		DF = new displayFrame(WP, "testFrames");
		
		//testMessages();
	
                //testLetterTuples();
                testLetterTupleComplex();
		//testActor();
		//testActors();		
		//testItems();
		//testFrames.floodMainPanel();
	}
	
        public static void testLetterTuples(){
		Random r = new Random();
		ArrayList<ArrayList<letterTuple>> basicModel = new ArrayList<ArrayList<letterTuple>>();
                
                //An annonomous letterTuple that draws WONDERFUL things.
                letterTuple LT_Crazy = new letterTuple('F',Color.white,Color.black){
                    
                public void drawAt(int col, int row, textPanel tp, Graphics g){
		
                Random R = new Random();
                Graphics2D g2d = (Graphics2D) g;	
	
		float startX = ((float)col * (float)tp.xInterval);		
		float startY = ((float)row * (float)tp.yInterval);	

		g2d.setColor(new Color(R.nextInt(255),R.nextInt(255),R.nextInt(255)));	
		
		//System.out.println(startX+" "+startY+" "+(int)xInterval+" "+(int)yInterval);
		g2d.fillRect((int)startX,(int)startY, (int)Math.ceil(tp.xInterval/2), (int)Math.ceil(tp.yInterval/2));
                
                g2d.setColor(new Color(R.nextInt(255),R.nextInt(255),R.nextInt(255)));	
		
		//System.out.println(startX+" "+startY+" "+(int)xInterval+" "+(int)yInterval);
		g2d.fillRect((int)startX+(int)Math.ceil(tp.xInterval/2),(int)startY, (int)Math.ceil(tp.xInterval/2), (int)Math.ceil(tp.yInterval/2));
                
                g2d.setColor(new Color(R.nextInt(255),R.nextInt(255),R.nextInt(255)));	
		
		//System.out.println(startX+" "+startY+" "+(int)xInterval+" "+(int)yInterval);
		g2d.fillRect((int)startX,(int)startY+(int)Math.ceil(tp.yInterval/2), (int)Math.ceil(tp.xInterval/2), (int)Math.ceil(tp.yInterval/2));
		
		g2d.setColor(new Color(R.nextInt(255),R.nextInt(255),R.nextInt(255)));	
		
		//System.out.println(startX+" "+startY+" "+(int)xInterval+" "+(int)yInterval);
		g2d.fillRect((int)startX+(int)Math.ceil(tp.xInterval/2),(int)startY+(int)Math.ceil(tp.yInterval/2), (int)Math.ceil(tp.xInterval/2), (int)Math.ceil(tp.yInterval/2));
                
		//g2d.setColor(Color.black);
		//g2d.drawString(letter()+"",startX+tp.xInterval*1/10,startY+tp.yInterval*4/5);
                } 
                    
                } ;
                
		for(int i=0;i<5;++i){
			basicModel.add(new ArrayList<letterTuple>());
			for(int j=0;j<5;++j){//Makes a region of the image, a 50 by 50 square of letters, noteworthy.
					basicModel.get(i).add(LT_Crazy);
			}
		}
		
		textModelBasic TMB = new textModelBasic(basicModel);
		
		actor A = new actor(25, 25, TMB);
		while(true){
			A.act();
			WP.update(A);
			try{Thread.sleep(1500);}catch(Exception e){}
			WP.repaint();
		}
	}
        
        
        
	public static void testActor(){
		Random r = new Random();
		ArrayList<ArrayList<letterTuple>> basicModel = new ArrayList<ArrayList<letterTuple>>();
		for(int i=0;i<5;++i){
			basicModel.add(new ArrayList<letterTuple>());
			for(int j=0;j<5;++j){//Makes a region of the image, a 50 by 50 square of letters, noteworthy.
				if(i==2||j==2){
					basicModel.get(i).add(letterTuple.TRANSPARENT);
				} else {
					basicModel.get(i).add(new letterTuple((char)r.nextInt(125),Color.white,new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255))));
				}
			}
		}
		
		textModelBasic TMB = new textModelBasic(basicModel);
		
		actor A = new actor(25, 25, TMB);
		while(true){
			A.act();
			WP.update(A);
			try{Thread.sleep(150);}catch(Exception e){}
			WP.repaint();
		}
	}
	
        public static void testLetterTupleComplex(){
		Random r = new Random();
		ArrayList<ArrayList<letterTuple>> basicModel = new ArrayList<ArrayList<letterTuple>>();
		for(int i=0;i<15;++i){
			basicModel.add(new ArrayList<letterTuple>());
			for(int j=0;j<15;++j){//Makes a region of the image, a 50 by 50 square of letters, noteworthy.
                            letterTuple base = new letterTuple((char)r.nextInt(125),new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),Color.black);
                            letterTupleComplex LTC = new letterTupleComplex(base);
			    //LTC.setProperty(new property_AddBlueRandom(75,200));
                            LTC.setProperty(new property_Sparkle(5));
                            basicModel.get(i).add(LTC);
                        }
		}
		
		textModelBasic TMB = new textModelBasic(basicModel);
		
		actor A = new actor(25, 25, TMB){
                    public void act(){return;}
                };
		while(true){
			A.act();
			WP.update(A);
			try{Thread.sleep(150);}catch(Exception e){}
			WP.repaint();
		}
	}
        
	public static void testActors(){
		Random r = new Random();
		ArrayList<ArrayList<letterTuple>> basicModel = new ArrayList<ArrayList<letterTuple>>();
		for(int i=0;i<2;++i){
			basicModel.add(new ArrayList<letterTuple>());
			for(int j=0;j<2;++j){//Makes a region of the image, a 50 by 50 square of letters, noteworthy.
				
				basicModel.get(i).add(new letterTuple((char)r.nextInt(125),Color.white,new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255))));
				
			}
		}
		
		textModelBasic TMB = new textModelBasic(basicModel);
		
		ArrayList<actor> AList = new ArrayList<actor>();		
		for(int i=0;i<20;++i){
			AList.add(new actor(25+r.nextInt(25), 25+r.nextInt(25), TMB));
		}

		while(true){for( actor A : AList ){
			A.act();	
			WP.update(A);
		} try{Thread.sleep(100);}catch(Exception e){}
		WP.repaint();
		}
	}	

	public static void testItems(){
		Random r = new Random();
		ArrayList<ArrayList<letterTuple>> basicModel = new ArrayList<ArrayList<letterTuple>>();
		for(int i=0;i<50;++i){
			basicModel.add(new ArrayList<letterTuple>());
			for(int j=0;j<50;++j){//Makes a region of the image, a 50 by 50 square of letters, noteworthy.
				if(i>20&&j>20){
					basicModel.get(i).add(new letterTuple('?',Color.pink,Color.black));
				} else {
					basicModel.get(i).add(new letterTuple((char)r.nextInt(125),Color.black,new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255))));
				}
			}
		}
		
		textModelBasic TMB = new textModelBasic(basicModel);
		
		item theItem = new item("Something Incredible","An item typically used to test the inventory frame. WOW! IT WORKED!",TMB);
		}

	//Flood the main panel with random letterTuples!!! :D <3 WOOOOOOO!
	public static void floodMainPanel(){
		Random r = new Random();
		while(true){
			try{Thread.sleep(15);}catch(Exception e){}
			
			letterTuple set = new letterTuple();
			set.setLetter((char)(((int)'a')+r.nextInt(26)));
			
			Color foreground = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
			set.setForeground(foreground);
			
			Color background = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
			set.setBackground(background);			

			DF.mainPanel.updateAt(set,r.nextInt(displayFrame.DEFAULT_MAIN_WIDTH),r.nextInt(displayFrame.DEFAULT_MAIN_HEIGHT));
			
			//System.out.println("ADDED :D :D :D");
			DF.repaint();
		}
	}

	public static void testMessages(){
		//WP.message[0]="Hello world!";	
		//WP.message[1]="Milk is my fav colour!";
		//WP.displayMessage();
	}
}

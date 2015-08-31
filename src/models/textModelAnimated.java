package models;


import utility.letterTuple;
import models.textModel;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Awesomesauce
 */
public class textModelAnimated extends textModel{
    //Loads from a file
        
        private ArrayList<textModel> frames = new ArrayList<textModel>();
        private int currentFrame = 0;
        
        public textModelAnimated(ArrayList<textModel> frames){
            this.frames = frames;
        }
        
	public void loadFile(Scanner source){
            //It needs to be able to load a list of of them into the frames.
        }
	
	//Save the model as a file
	public void saveFile(PrintWriter out){
            //It should save the list of textModels using their save methods etc.
        }

	//Returns a set of letter tuples, ready for display
	public ArrayList<ArrayList<letterTuple>> display(){
            ArrayList<ArrayList<letterTuple>> toReturn = frames.get(currentFrame).display();
            return toReturn;
        }
	
	//Returns a model with this of display.
	public textModelBasic make2D(){
            textModelBasic toReturn = frames.get(currentFrame).make2D();
            return toReturn;
        }

	//Returns the letter tuple at that moment at that point.
	public letterTuple displayAt(int col, int row){
            return frames.get(currentFrame).displayAt(col,row);
        }
	
	//Returns the height (maximum height?)
	public int height(){
            return frames.get(currentFrame).height();
        }
	
	//Returns the width (maximum width?)
	public int width(){
            return frames.get(currentFrame).width();
        }
        
        //Get the number of frames
        public int getFrames(){
            return frames.size();
        }
        
        //get the currentFrame
        public int current(){
            return currentFrame;
        }
        
        //Set this to a particular frame
        public void setFrame(int set){
            currentFrame = set;
            if (currentFrame > getFrames()){
                currentFrame = currentFrame%getFrames();
            }
        }
        
        //Set the frame up by one.
        public void nextFrame(){
            currentFrame++;
            if(currentFrame == getFrames()){
                currentFrame = 0;
            }
        }
}

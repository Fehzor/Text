package models;

import java.util.*;
import java.io.*;
import java.awt.*;

public class drawable{
	private boolean update = true;
	
	private textModel model;
	private int x,y,lastX,lastY;
	
	public drawable(int x, int y){
		this.x=x;
		this.y=x;
		lastX=x;
		lastY=y;
	}

	//Will need to be able to load from file.
	public drawable(int x, int y, textModel set){
		this.lastX = x;
		this.lastY = y;
		this.x = x;
		this.y = y;
		this.model = set;
	}
	
	//GETTERS AND SETTERS BELOW
	public int getX(){ return this.x; }
	public int getY(){ return this.y; }
	public int getLastX(){ return this.lastX; }
	public int getLastY(){ return this.lastY; }
	public boolean check(){ return this.update; }
	public textModel getModel(){ return this.model; }	
	
	public void setModel( textModel tm ){
		model = tm;
	}

	//Called whenever something has updated this class.
	public void update(){ this.update = false; }
	
	public void setX( int x ){ 
		if(!this.update){
			this.lastX = this.x;
			this.lastY = this.y;
		}
		this.x = x;
		if(!this.update) update = true;
	}

	public void setY( int y ){
		if(!this.update){
			this.lastY = this.y;
			this.lastX = this.x;
		}
		this.y = y;
		if(!this.update) update = true;
	}
}

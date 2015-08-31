package mechanics;

import models.drawable;
import models.textModel;
import java.awt.*;
import java.io.*;
import java.util.*;

public class actor extends drawable{
	
        public static final actor NULL_ACTOR = new actor(-1,-1);
    
        public static final int PLAYER_ID = 0;
        public static final int MOB_ID = 1;
        public static final int DOOR_ID = 2;
    
        public world currentWorld;
        
        public int ID;
        
	public actor(int x, int y){
		super(x,y);
	}
	
	public actor(int x, int y, textModel tm, world current){
		super(x,y,tm);
		this.currentWorld = current;
	}

	public actor(int x, int y, textModel tm){
		super(x,y,tm);
	}
	
	//This method will be called constantly to make the actor move about and act.
	public void act(){
		//ACTORS GONNA ACT- here is an example
	
		Random r = new Random();
		
		this.setX(r.nextInt(5)-2+this.getX());
		this.setY(r.nextInt(5)-2+this.getY());
		
		//Eventually, I should set this method to-
		//System.out.println("Replace public void act() to create behaviour!");
	}
}

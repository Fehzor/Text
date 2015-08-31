package mechanics;

import mechanics.actors.player;
import display.displayFrame;
import models.drawable;
import display.worldPanel;
import utility.*;
import java.util.*;
import java.awt.*;

public class world implements Runnable{
	private ArrayList<actor> actors = new ArrayList<actor>();
	private ArrayList<drawable> drawables = new ArrayList<drawable>();
        
        private ArrayList<collider> colliders = new ArrayList<collider>();
        
        public ArrayList<ArrayList<letterTuple>> background = new ArrayList<ArrayList<letterTuple>>();

	public boolean go = true;
	
	worldPanel display;
        public worldNode owner;
	
	public world(){
		addActor(player.thePlayer);
                this.generateBackground();
	}
        
        //Make the generic background.
        private void generateBackground(){
            Random r = new Random();
		
                //Generate the background.
		for(int col=0; col<displayFrame.DEFAULT_MAIN_WIDTH; ++col){
			background.add(new ArrayList<letterTuple>());
			for(int row=0; row<displayFrame.DEFAULT_MAIN_HEIGHT; ++row){
				if(r.nextInt(50)!=7)background.get(col).add(new letterTuple(' ',Color.black,Color.black));
				else{
					background.get(col).add(new letterTuple((char)('#'),Color.black,new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255))));
				}
			}
		}
        }
        
        public void removeBackground(){
                //Generate the background.
		for(int col=0; col<displayFrame.DEFAULT_MAIN_WIDTH; ++col){
			for(int row=0; row<displayFrame.DEFAULT_MAIN_HEIGHT; ++row){
				background.get(col).get(row).set(letterTuple.TRANSPARENT);
			}
		}
        }
	
	//GIVE IT A SPIN.
	public void move(){
		//Make all actors act once.
		for(int i = 0; i<actors.size();++i){
			actors.get(i).act();
			display.update(actors.get(i));
		}
		
		display.repaint();
	}
        
        public actor checkColliders(int x, int y){
            if(colliders.size() == 0){
                return actor.NULL_ACTOR;
            }
            
            for(int i = 0; i< colliders.size(); ++i){
                if(colliders.get(i).checkCollision(x, y)){
                    return colliders.get(i).owner;
                }
            }
            return actor.NULL_ACTOR;
        }
	
	public void run(){
		while(go==true){
			move();
			try{Thread.sleep(100);}catch(Exception e){}
		}
	}
	
	public void addActor(actor add){
		actors.add(add);
	}
        
        public void removeActor(actor remove){
		actors.remove(remove);
	}
        
        public void addCollider(collider add){
		colliders.add(add);
	}
        
        public void removeCollider(collider remove){
            colliders.remove(remove);
        }

	public void addDrawable(drawable add){
		drawables.add(add);
		display.update(add);
	}
        
        
        
        //Stops the world loop
        public void stop(){
            this.go = false;
        }
        
        //Begins the world loop
        public void start(worldPanel WP){
            this.go = true;
            display = WP;
            Thread t = new Thread(this);
            t.start();
        }
}

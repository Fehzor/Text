package mechanics.actors;

import mechanics.actor;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import mechanics.actor;

public class player extends actor{
	public int directionX = NONE;
	public int directionY = NONE;
	public static final int NONE = 0;
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;

	public static player thePlayer = new player();	

	public player(){ super(20,20); this.ID = PLAYER_ID;}
	
	public void act(){
		switch(directionY){
			case UP:
			setY(getY()-2);
			break;
			
			case DOWN:
			setY(getY()+2);
			break;
			
			default:
			break;	
		}
		
		switch(directionX){
			case LEFT:
			setX(getX()-2);
			break;

			case RIGHT:
			setX(getX()+2);
			break;
			
			default:
			break;
		}
                
                checkMainCollisions();
	}
        
        private void checkMainCollisions(){
            //Check collisions;
                //Can't check collisions if not in a world.
                if(currentWorld == null)return;
                
                actor collide = currentWorld.checkColliders(this.getX(), this.getY());
                
                //Didn't hit anything.
                if(collide == actor.NULL_ACTOR) return;
                
                //If the player is on top of a door actor, get the ID from it and go.
                if (collide.ID == actor.DOOR_ID){
                    
                    int where = ((actor_doorway)collide).getLocation();
                    
                    currentWorld.owner.change(where);
                    
                    //Temporary
                    this.setX(20);
                    this.setY(20);
                }
        }
	
	public static void keyPress(KeyEvent e){
		player.thePlayer.keyPress(e.getKeyCode());
	}	

	private void keyPress(int key){
		if(key == KeyEvent.VK_S){
			this.directionX = LEFT;
		}
		
		if(key == KeyEvent.VK_F){
			this.directionX = RIGHT;
		}
		
		if(key == KeyEvent.VK_E){
			this.directionY = UP;
		}

		if(key == KeyEvent.VK_D){
			this.directionY = DOWN;
		}
	}
	
	public static void keyRelease(KeyEvent e){
		player.thePlayer.keyRelease(e.getKeyCode());
	}
	
	private void keyRelease(int key){
		if(key == KeyEvent.VK_S && this.directionX == LEFT){
			this.directionX = NONE;
		}
		
		if(key == KeyEvent.VK_F && this.directionX == RIGHT){
			this.directionX = NONE;
		}
		
		if(key == KeyEvent.VK_E && this.directionY == UP){
			this.directionY = NONE;
		}

		if(key == KeyEvent.VK_D && this.directionY == DOWN){
			this.directionY = NONE;
		}

	}
}

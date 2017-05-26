/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors;
import text.Utility.ImageBuilder;
import text.Actors.Messages.Menu;
import text.Actors.Messages.Prompt;
import text.Actors.Messages.InspectMenu;
import text.WorldFrame.Room;
import text.Images.*;
import java.awt.Color;
import java.util.*;
import text.Frame.TextListener;
import text.Utility.ColorTuple;
import java.awt.event.*;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import text.Combat.Cards.CardRunner;
import text.Combat.Cards.Deck;
import text.Combat.Hero.HeroController;
import text.Combat.Hero.HeroLetter;
import text.Combat.Hero.WordController;
import text.Combat.CombatSystem;
import text.Combat.Dice.DiceSet;
import text.Combat.EightBall.EightBall;
import text.Combat.Hero.Instrument;
import text.Combat.RPS.RPSObject;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.ImageLoader;
import text.Utility.*;
import text.Frame.*;
import text.Inventory.*;

/**
 *
 * @author FF6EB4
 */
public class Player extends Actor implements Serializable{
    public static Room current;
    public static final int WEST = 3;
    public static final int NORTH = 0;
    public static final int SOUTH = 2;
    public static final int EAST = 1;
    //This field will be set whenever the player creates a menu.
    public Menu myMenu;
    private static boolean moving = false;
    public static boolean paused = false;
    
    public static Inventory inv = new Inventory(10);
    public static Inventory tools = new Inventory(5);
    
    public static ArrayList<Equipable> equipment = new ArrayList<>();
    public static Player The = new Player();
    
    public static CombatSystem combat = new CombatSystem();
    
    private final Lock switchLock = new ReentrantLock();
    
    /**
     * This is the amount of time left in an animation for the player.
     */
    public int animationTime = 0;
    private static TextImage standardImage = generateSprite();
   
    private Player(){
        super(40,40,generateSprite());
    }
    
    public boolean act(){
        if(animationTime == 0){
            animationTime = -1;
            paused = false;
            this.image = standardImage;
        } else if (animationTime > 0){
            animationTime = animationTime - 1;
        }
        
        
        
        if(!paused){
            this.depth = this.y;
            
            for(Equipable EQ : equipment){
                EQ.act();
            }
        if(TextListener.isHeld(KeyEvent.VK_X)){
            //HeroController HC = new HeroController("asdfasdf",(float).3);;
            WordController HC = new WordController("PieAndMilk",(float)1.0);
            this.current.addActor(HC);
            this.paused = true;
        }
            //Movement
            moving = false;
            if(TextListener.isHeld(KeyEvent.VK_F)){
                this.x+=2;
                image.flip(false);
                moving = true;
            }
            if(TextListener.isHeld(KeyEvent.VK_S)){
                this.x-=2;
                image.flip(true);
                moving = true;
            }
            if(TextListener.isHeld(KeyEvent.VK_D)){
                this.y+=1;
                moving = true;
            }
            if(TextListener.isHeld(KeyEvent.VK_E)){
                this.y-=1;
                moving = true;
            }

            //Deal with walking animations...
            if(animationTime == -1){
                if(moving){
                    ((TextImageAnimated)image).go();
                } else {
                    ((TextImageAnimated)image).stop();
                    ((TextImageAnimated)image).resetFrame();
                }
            }

            //COMMAND PROMPT
            if(TextListener.firstPress(KeyEvent.VK_ENTER)){
                Prompt P = new Prompt(current);
            }

            //HOW THE PLAYER PICKS STUFF UP
            if(TextListener.firstPress(KeyEvent.VK_A)){
                try{
                    ArrayList<Actor> hits = current.HitScan.checkHit(x,y,0);
                    hits.remove(Player.The);
                    if( hits.size() > 0 ){
                        for(Actor A : hits){
                            A.paused = true;
                        }
                        myMenu = new InspectMenu(hits,current);

                        //hits.get(1).y = 12;
                        //inv.put(new ColorTuple(Color.WHITE,Color.PINK,'X'));
                    }
                } catch (Exception E){System.err.println("Unexpected Error!-- Player's VK_A event");}
            }

            
        } 
        //
        //THINGS THAT THE PLAYER CAN DO WHEN PAUSED OR NOT.
        //
        
        if(paused && animationTime == -1){
            try{
            ((TextImageAnimated)image).stop();
            ((TextImageAnimated)image).resetFrame();
            } catch (Exception E){
                //This probably means the player is doing an animation!
            }
        }
        
        //Is there a menu whose results have just finished?
        if(myMenu!=null && myMenu.done){
            int selection = myMenu.selection;
            myMenu = null;
            this.paused = false;
        }
        
        
        
        return true;
    }
    
    //LOAD THE PLAYER SPRITE :D
    public static TextImage generateSprite(){
        ImageLoader.switchMap("HUMAN");
        
        TextImageBasic still = ImageLoader.loadImage("player_stand.txt");
        TextImageBasic move = ImageLoader.loadImage("player_walk.txt");
        
        //ImageBuilder.makeTransparent(still);
        //ImageBuilder.makeTransparent(move);
        
        TextImageComplex stillC = new TextImageComplex(still);
        TextImageComplex walkC = new TextImageComplex(move);
        
        TextImageAnimated ret = new TextImageAnimated(stillC,walkC);
        ret.go();
        
        return ret;
    }
    
    public void outSideRoom(){ 
        if(switchLock.tryLock()){
            int dir = 0;
            if(x <= 0){
                dir = Player.WEST;
            }
            
            if(x >= TextDisplay.SCREEN_SIZE_X){
                dir = Player.EAST;
            }
            
            if(y <= 0){
                dir = Player.NORTH;
            }

            if(y >= TextDisplay.SCREEN_SIZE_Y){
                dir = Player.SOUTH;
            }
            
            
            current.owner.playerOutOfBounds(dir);
            current.owner.positionPlayer(dir);
            
            switchLock.unlock();
        }
        
        //System.out.println(x+" "+y);
    }
    
    public void equip(Equipable EQ){
        if(Player.The.inv.inspectStuff().contains(EQ)!=true){
            boolean full = !Player.inv.addStuff(new Physical(EQ));
            if(full){
                return;
            }
        }
        
        //If it's in the same slot as something else...
        for(int i = equipment.size()-1; i > 0; --i){
            Equipable mine = equipment.get(i);
            if(mine.slot == EQ.slot){
                unequip(mine);
                return;
            }
        }
        
        EQ.equipped = true;
        equipment.add(EQ);
        
        this.inv.removeStuff(EQ);
        this.tools.addStuff(new Physical(EQ));
        
        if(EQ instanceof Instrument){
            this.combat = ((Instrument) EQ).myCombatSystem;
        }
        
        if(EQ instanceof DiceSet){
            this.combat = ((DiceSet) EQ).myRunner;
        }
        
        if(EQ instanceof Deck){
            this.combat = new CardRunner(((Deck) EQ));
        }
        
        if(EQ instanceof EightBall){
            this.combat = ((EightBall) EQ).EBS;
        }
        
        if(EQ instanceof RPSObject){
            this.combat = ((RPSObject) EQ).RPS;
        }
        
        
        return;
    }
    
    public void unequip(Equipable EQ){
        EQ.equipped = false;
        if(EQ instanceof Instrument || EQ instanceof DiceSet || 
                EQ instanceof Deck || EQ instanceof EightBall ||
                EQ instanceof RPSObject){
            this.combat = new CombatSystem();
        }
        equipment.remove(EQ);
        this.tools.removeStuff(EQ);
        this.inv.addStuff(new Physical(EQ));
        
        return;
    }
    
    public String toString(){
        return "Cancel";
    }
}

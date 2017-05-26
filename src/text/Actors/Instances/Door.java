/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances;

import java.awt.Point;
import text.Utility.ImageLoader;
import text.Utility.ImageBuilder;
import text.WorldFrame.World;
import text.Actors.Interactable;
import java.io.Serializable;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Frame.TextDisplay;
import text.WorldFrame.*;
import text.Images.*;
import text.Utility.*;
import text.Utility.Tiles.TileBuilder;
import text.WorldFrame.Templates.WorldBuilder;
import text.WorldFrame.Templates.WorldTemplate;
import text.WorldFrame.Worlds.RoomWorld;

/**
 *
 * @author FF6EB4
 */
public class Door extends Interactable implements Serializable{
    public boolean open = true; //Is it open or closed? True = open.
    public boolean stripped = false; //Is it a trap door or just a world container now?
    World linked;
    WorldTemplate contained;
    Door exit;
    
    private static RoomWorld invRoom = new RoomWorld();
    private static Door D;
    
    Option enter = new Option(this){
                    public boolean act(){
                        if(linked == null && exit == null){
                            linked = WorldBuilder.generate(contained);
                        }
                        
                        if(exit != null){
                            if(exit.held){
                                ///GO TO THE PLAYERS INVENTORY HERE
                                exit.held = false;
                                Player.The.inv.removeNotHeld();
                                
                                if(D == null){
                                    Room theRoom = new Room(0,invRoom);
                                    
                                    //TODO Make this look inventory like lol
                                    TextImageBasic wall = TileBuilder.buildBricked();
                                    
                                    theRoom.BG.addTextImageBasic(new Point(0,0),wall);
                                    theRoom.block(Room.NORTH,23);
                                    
                                    invRoom.addRoom(theRoom);
                                    
                                    D = new Door(((Door)owner),"Door");
                                    D.x = TextDisplay.SCREEN_SIZE_X / 2;
                                    invRoom.R.addActor(D);
                                    
                                    D.current = theRoom;
                                }
                                
                                D.exit = (Door) owner;
                                ((Door)owner).exit = D;
                                
                                
                                Player.The.current.owner.switchWorld(D.current);
                                Player.The.x = exit.x;
                                Player.The.y = exit.y;
                                
                                return true;
                            } else {
                                Player.The.current.owner.switchWorld(exit.current);
                                Player.The.x = exit.x;
                                Player.The.y = exit.y;
                            }
                        } else {
                            //System.out.println("ENTERING");
                            Player.The.current.owner.switchWorld(linked,0);
                        }
                        return true;
                    }
                    public String toString(){return "Enter";}
            };
    
    public Door(String name){
        super(0,0,Door.buildImage(name));
        this.name = name;
    }
       
    public Door(Door D, String name){
        super(0,0,Door.buildImage(name));
        this.name = name;
        exit = D;
        D.exit = this;
    }
    
    public Door(int x, int y, Door D, String name){
        super(x,y,Door.buildImage(name));
        this.name = name;
        exit = D;
        D.exit = this;
    }
    
    public Door(WorldTemplate WT, String name){
        super(0,0,Door.buildImage(name));
        this.name = name;
        contained = WT;
    }
    
    public Door(int x, int y, WorldTemplate WT, String name){
        super(x,y,Door.buildImage(name));
        this.name = name;
        contained = WT;
    }
    
    public Door(World W, String name){
        super(0,0,colorImage(Door.buildImage(name),W));
        this.name = name;
        linked = W;
    }
    
    public Door(int x, int y, World W, String name){
        super(x,y,colorImage(Door.buildImage(name),W));
        this.name = name;
        linked = W;
    }
    
    private static TextImage buildImage(String name){
        ImageLoader.switchMap("CITYSCALE");
        
        return ImageLoader.loadAnimated(name.toLowerCase()+"_open.txt",name.toLowerCase()+"_closed.txt");
        //return ImageLoader.loadAnimated("teleporter_frameA.txt","teleporter_frameB.txt");
    }
    
    private static TextImage colorImage(TextImage img,World W){
        ColorTuple merge = W.E.soil.averageColor();
        ImageBuilder.colorMergeImage((TextImageAnimated)img, merge);
        return img;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor>ret = new ArrayList<>();
        if(stripped){
            ret.add(Option.cancel(this));
            
            ret.add(enter);
            
            ret.add(new Option(this){
                    public boolean act(){
                        owner.dead = true;
                        return true;
                    }
                    public String toString(){return "Remove+Destroy";}
            });
            
            return ret;
        }
        
        if(!open){
            ret = super.pollOptions();
                ret.add(new Option(this){
                public boolean act(){
                    ((Door)owner).open = true;
                    ((TextImageAnimated)owner.image).resetFrame();
                    return true;
                }
                public String toString(){return "Open";}
            });
            
            return ret;
        } else {
            
            ret.add(new Option(this){
                public boolean act(){
                    ((Door)owner).open = false;
                    ((TextImageAnimated)owner.image).setSecond();
                    return true;
                }
                public String toString(){return "Close";}
            });
            
            if(this.held==false){
                ret.add(enter);
            }
            
            return ret;
        }
    }
    
    public String toString(){
        
        return name;
    }
}

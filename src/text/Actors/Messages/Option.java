/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Messages;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import text.Inventory.Resource;
import text.Utility.ColorTuple;
import java.util.*;
import text.Actors.Actor;
import text.Actors.*;
import text.Actors.Player;
import text.Frame.TextDisplay;
import text.Inventory.Physical;
//import text.Tools.*;
import text.Actors.Instances.*;
import text.Actors.Instances.Chests.LockedChest;
import text.Actors.Instances.Chests.Password;
import text.Game.GameBoard;
import text.Game.GamePiece;
import text.Images.TextImageBasic;
import text.Inventory.Core;
import text.Inventory.Inventory;
import text.Tools.Tool;
import text.Tools.ToolPart;
import text.Utility.ImageBuilder;
import text.Utility.ReadAndWriter;
import text.WorldFrame.MainWorld;

/**
 *
 *  THIS IS A SPECIAL CLASS FOR USE WITH MENUS
 *  IT IS NOT ADDED TO THE WORLD LOOP LIKE OTHER ACTORS.
 * 
 * @author FF6EB4
 */
public class Option extends Actor{

    
    
    public Actor owner;
    public boolean chosen = false;
    public Actor choice;
    
    public Option(Actor owner){
        super(0,0,null);
        blockable = false;
        this.owner = owner;
        this.dead = true;
    }
    
    public boolean act(){
        //OVERRIDE THIS BY MAKING AN ANONYMOUS SUBCLASS
        return true;
    }
    
    public String toString(){
        //OVERRIDE THIS BY MAKING AN ANONYMOUS SUBCLASS
        return "OPTION.TOSTRING()";
    }
    
    //OPTIONS CANNOT BE DRAWN. USE WITH MENUS.
    public void draw(Graphics g, int xSize, int ySize){ 
        return;
    }
    
    public static Option cancel(Actor you){
        return new Option(you){
          public boolean act(){
              owner.paused = false;
              return true;}
          public String toString(){return "Cancel";}
        };
    }
    
    //
    //  THINGS THAT MANY INTERACTABLE OBJECTS CAN DO.
    //
    
    //Change to resources.
    public static Option convert(Actor you, ArrayList<Resource> RList){
        return new Option(you){
            Random oRan = new Random();
            public boolean act(){
                System.out.println(RList);
                for(int i = 0; i<RList.size(); ++i){
                
              
                Player.The.inv.put(RList.get(i));
              
                
            }
                
            if(owner.held){
                Player.The.inv.removeStuff(owner);
            }
              
            owner.held = false;
            owner.dead = true;
            
            return true;
          }
          public String toString(){return "Convert";}
        };
    }
    
    //Change to resources.
    public static Option convert(Actor you, ColorTuple give, int amount){
        return new Option(you){
            Random oRan = new Random();
            public boolean act(){
            
            Resource R = new Resource(give,amount);
            
            Player.The.inv.put(R);
              
            if(owner.held){
                Player.The.inv.removeStuff(owner);
            }
              
            owner.held = false;
            owner.dead = true;
            
            return true;
            }
          public String toString(){return "Convert";}
        };
    }
    
    //Place in inventory as an object
    public static Option pickUp(Actor you,ColorTuple icon){
        return new Option(you){
          Random oRan = new Random();
          public boolean act(){
              
              Physical<Actor> YOU = new Physical<Actor>(icon,you);
              
              if(Player.The.inv.full()){
                  return true;
              }
              
              Player.The.inv.addStuff(YOU);
              
              owner.current = null;
              
              owner.dead = true;
              owner.held = true;
              owner.paused = true;
              
              return true;
          }
          public String toString(){return "Pick Up";}
        };
    }
    
    //Take out of inventory + set down.
    public static Option setDown(Actor you){
        return new Option(you){
          public boolean act(){
              //System.out.println("Collected!");
              try{
                  Player.The.unequip((Equipable)you);
              } catch (Exception E){}

              
              Player.The.current.addActor(owner);
              
              owner.x = Player.The.x;
              owner.y = Player.The.y;
              owner.depth = owner.y;
              
              owner.dead = false;
              owner.held = false;
              owner.paused = false;
              
              owner.current = Player.current;
              
              Player.The.inv.removeStuff(owner);
              
              return true;
          }
          public String toString(){return "Set Down";}
        };
    }
    
    //A few tool/equip things
    
    public static Option equip(Actor you){
        return new Option(you){
          public boolean act(){
              //System.out.println("Collected!");
              Player.The.equip((Equipable)you);
              
              return true;
          }
          public String toString(){return "Equip: "+(Equipable.slotName(((Equipable)you).slot));}
        };
    }
    
    public static Option unequip(Actor you){
        return new Option(you){
          public boolean act(){
              //System.out.println("Collected!");
              Player.The.unequip((Equipable)you);
              
              return true;
          }
          public String toString(){return "Unequip";}
        };
    }
    
    
    public static Option attachToolPart(Actor you){
        return new Option(you){
            
        public boolean act(){
            ToolPart base = ((ToolPart)owner);
            
            ArrayList<Physical> pList = Player.The.inv.queryStuff("toolpart");
            
            //Remove duplicate tool parts
            for(int i = pList.size()-1; i >= 0; --i){
                if(pList.get(i).getData().equals(you)){
                    pList.remove(i);
                }
            }
            
            String[] parts = new String[pList.size()];
            
            int i = 0;
            for(Physical P : pList){
                parts[i++] = ((ToolPart)P.getData()).toString();
            }
              
            new Menu(parts,Player.The.current){
                public void menuEnd(){
                    try{
                        ToolPart toAdd = (ToolPart)(pList.get(selection).getData());
                        if(toAdd.equals(you)){
                            return;
                        }
                        boolean worked = base.connect(toAdd);//((Teleporter)you).addDoor(toAdd);
                        //System.out.println(worked);
                        if(worked){
                            Player.The.inv.removeStuff((Actor)toAdd);
                            //System.out.println("IT WORKED");
                        }
                    } catch (Exception E){
                        System.out.println("Oh no!"+E);
                        //Nothing was there to select.
                    }
                }
            };
            
            return true;
        }
        public String toString(){return "Attach...";}
        };
    }
    
    public static Option attachTeleport(Actor you){
        return new Option(you){
            
        public boolean act(){
            ArrayList<Physical> pList = Player.The.inv.queryStuff("trapdoor");
            
            String[] parts = new String[pList.size()];
              
            int i = 0;
            for(Physical P : pList){
                parts[i++] = ((Door)P.getData()).toString();
            }
              
            new Menu(parts,Player.The.current){
                public void menuEnd(){
                    try{
                        Door toAdd = (Door)(pList.get(selection).getData());
                        boolean worked =((Teleporter)you).addDoor(toAdd);
                        
                        if(worked){
                          //System.out.println("Tele attachment complete!");
                          Player.The.inv.removeStuff((Actor)toAdd);
                        }
                    } catch (Exception E){
                        System.out.println("Oh no!"+E);
                        //Nothing was there to select.
                    }
                }
            };
            
            return true;
        }
        public String toString(){return "Attach";}
        };
    }
    
    public static Option write(Actor you, Serializable toSave, String file){
        return new Option(you){
          public boolean act(){
              
              ReadAndWriter.save((Physical) toSave);
              
              return true;}
          public String toString(){return "Cancel";}
        };
    }
    
    public static Option saveAnItem(Actor you){
        return new Option(you){
            
        public boolean act(){
            ArrayList<Physical> pList = Player.The.inv.queryStuff("all");
            
            String[] items = new String[pList.size()];
            
            int i = 0;
            for(Physical P : pList){
                items[i++] = (P.getData()).toString();
            }
              
            new Menu(items,Player.The.current){
                public void menuEnd(){
                    try{
                        Physical save = pList.get(selection);
                        
                        ReadAndWriter.save((Physical) save);
                    } catch (Exception E){
                        System.out.println("Oh no!"+E);
                        //Nothing was there to select.
                    }
                }
            };
            
            return true;
        }
        public String toString(){return "Save An Item...";}
        };
    }
    
    public static Option read(Actor you){
        return new Option(you){
          public boolean act(){
              
              ReadAndWriter.read();
              
              return true;}
          public String toString(){return "Load An Item...";}
        };
    }
    
    /**
     * Displays a messsage
     * @param you = 'this'
     * @param optionName = what the message name displays as
     * @param message = the message to be displayed
     * @return 
     */
    public static Option display(Actor you, String optionName, String message){
        return new Option(you){
          public boolean act(){
              String[] disp = new String[1];
              disp[0] = message;
              
              Player.The.current.owner.display(disp);
              
              return true;}
          public String toString(){return optionName;}
        };
    }
    
        /**
     * Displays a messsage
     * @param you = 'this'
     * @param optionName = what the message name displays as
     * @param message = the message to be displayed
     * @return 
     */
    public static Option display(Actor you, String optionName, String[] message){
        return new Option(you){
          public boolean act(){
              String[] disp =  message;
              
              Player.The.current.owner.display(disp);
              
              return true;}
          public String toString(){return optionName;}
        };
    }
    
    public static Option vend(Actor you, Actor vend, String q, int amt){
        return new Option(you){
            public boolean act(){
            
            if(Player.The.inv.checkResources(q, amt) == false){
                String[] disp = new String[1];
                disp[0] = "You can't afford that!";
                Player.The.current.owner.display(disp);
                return true;
            }
            
            Core C = Player.The.inv.drainResources(q,amt);
            
            Actor give = vend.clone();
            give.held = true;
            
            ImageBuilder.colorMergeImage((TextImageBasic)give.image, C.getIcon());
            Physical phys = new Physical(give);
            
            Player.The.inv.addStuff(phys);
            
            //((VendingMachine)owner).bought = true;
            
            owner.paused = false;
            
            return true;}
          public String toString(){return vend.name+": "+amt+" "+q;}
        };
    }
    
    //
    //
    //  USED FOR INVENTORIES OUTSIDE OF THE PLAYER'S
    //
    //
    
    //Display resources from inv
    public static Option checkInventory(Actor you, Inventory inv){
                return new Option(you){
            public boolean act(){
                inv.displayResource("all");
                owner.paused = false;
            return true;}
          public String toString(){return "Check Letters";}
        };
    }
    
    public static Option takeInventory(Actor you,Inventory inv){
                return new Option(you){
            public boolean act(){
                inv.transferResourcesTo(Player.The.inv);
                owner.paused = false;
            return true;}
          public String toString(){return "Take Letters";}
        };
    }
    
    
    //Pop up the options menu from inv.
    public static Option checkInventoryObj(Actor you, Inventory inv){
                return new Option(you){
            public boolean act(){
                ArrayList<Actor> get = inv.inspectStuff();
                ArrayList<Actor> ret = new ArrayList<>();
                
                for(Actor A : get){
                    take(A,inv);
                }
                
                ret.add(0,new Option(this){
                    public String toString(){
                        return "Cancel";
                    }
                });
                
            Player.The.myMenu = new InspectMenu(get,Player.The.current);
            owner.paused = false;
            return true;}
          public String toString(){return "Check Objects";}
        };
    }
    
    public static Option take(Actor stuff, Inventory inv){
                return new Option(stuff){
            public boolean act(){
              owner.paused = false;

              if(Player.The.inv.full()){
                  return true;
              }
              
              Physical<Actor> YOU;
              YOU = new Physical<>(new ColorTuple(Color.BLACK,Color.BLACK,' '),stuff);
              
              Player.The.inv.addStuff(YOU);
              inv.removeStuff(stuff);
              
              return true;
          }
          public String toString(){return "Take "+stuff;}
        };
    }
    
    public static Option search(Searchable owning){
        return new Option(owning){
            public boolean act(){
              if(!owning.hasActor()){
                  String[] disp = new String[1];
                  disp[0] = "Found nothing.";
                  Player.The.current.owner.display(disp);
                  return true;
              }
              
              if(Player.The.inv.full()){
                  return true;
              }
              
              Physical<Actor> YOU;
              YOU = new Physical<>(new ColorTuple(Color.BLACK,Color.BLACK,' '),owning.take());
              Player.The.inv.addStuff(YOU);
              
              return true;
          }
          public String toString(){return "Search...";}
        };
    }
    
    public static Option attachGamePiece(Actor you, int where){
        return new Option(you){
            
        public boolean act(){
            ArrayList<Physical> pList = Player.The.inv.queryStuff("gamepiece");
            
            String[] parts = new String[pList.size()];
              
            int i = 0;
            for(Physical P : pList){
                parts[i++] = ((GamePiece)P.getData()).toString();
            }
              
            new Menu(parts,Player.The.current){
                public void menuEnd(){
                    try{
                        GamePiece toAdd = (GamePiece)(pList.get(selection).getData());
                        boolean worked =((GameBoard)you).addGamePiece(toAdd, where, 0);
                        
                        if(worked){
                          Player.The.inv.removeStuff((Actor)toAdd);
                        }
                    } catch (Exception E){
                        System.out.println("Oh no!"+E);
                        //Nothing was there to select.
                    }
                }
            };
            
            return true;
        }
        public String toString(){return "Add piece to "+where;}
        };
    }
    
    public static Option unlock(Actor you){
        return new Option(you){
            
        public boolean act(){
            ArrayList<Physical> pList = Player.The.inv.queryStuff("password");
            
            String[] parts = new String[pList.size()];
              
            int i = 0;
            for(Physical P : pList){
                parts[i++] = ((Password)P.getData()).toString();
            }
              
            new Menu(parts,Player.The.current){
                public void menuEnd(){
                    try{
                        Password P = (Password)(pList.get(selection).getData());
                        Player.The.inv.removeStuff(P);
                        
                        ((LockedChest)you).lock = false;
                        
                    } catch (Exception E){
                        System.out.println("Oh no!"+E);
                        //Nothing was there to select.
                    }
                }
            };
            
            return true;
        }
        public String toString(){return "Select password";}
        };
    }
}

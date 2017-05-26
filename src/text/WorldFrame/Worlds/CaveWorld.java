/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Worlds;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Instances.CityPart;
import text.Actors.PuzzleObjects.Bone;
import text.Actors.Instances.Door;
import text.Actors.NPC.NPCs.Caves.MolePeople.MoleManA;
import text.Actors.NPC.NPCs.Caves.Moles.MoleA;
import text.Actors.NPC.NPCs.Caves.Moles.MoleSkip;
import text.Actors.NPC.NPCs.Caves.Scientist;
import text.Actors.NPC.NPCs.Caves.Spiders.CaveSpiderA;
import text.Actors.NPC.NPCs.Caves.Spiders.CaveSpiderB;
import text.Actors.NPC.NPCs.Caves.Spiders.CaveSpiderSkip;
import text.Actors.NPC.NPCs.Caves.Worms.WormA;
import text.Actors.NPC.NPCs.Caves.Worms.WormB;
import text.Actors.Pickup;
import text.Actors.Player;
import text.Actors.PuzzleObjects.ScienceObject;
import text.Combat.Enemy.Enemies.Caves.ScienceFight;
import text.Combat.Enemy.Enemies.Caves.Spider_BaitNSwitch;
import text.Combat.Enemy.Enemies.Caves.Spider_Boss;
import text.Combat.Enemy.Enemies.Caves.Spider_Paper;
import text.Combat.Enemy.Enemies.Caves.Spider_PaperScissors;
import text.Combat.Enemy.Enemies.Caves.Spider_PlayerCounter;
import text.Combat.Enemy.Enemies.Caves.Spider_PlayerReverseCounter;
import text.Combat.Enemy.Enemies.Caves.Spider_Random;
import text.Combat.Enemy.Enemies.Caves.Spider_Rock;
import text.Combat.Enemy.Enemies.Caves.Spider_RockPaper;
import text.Combat.Enemy.Enemies.Caves.Spider_RockScissors;
import text.Combat.Enemy.Enemies.Caves.Spider_Scissors;
import text.Combat.Enemy.Enemies.Enemy;
import text.Environment.Environment;
import text.Environment.Inanimate.Rock;
import text.Frame.TextDisplay;
import text.Images.BackGround;
import text.Utility.ColorTuple;
import text.Utility.SuperRandom;
import text.WorldFrame.Progress;
import text.WorldFrame.Room;
import static text.WorldFrame.Templates.CityBuilder.oRan;
import text.WorldFrame.Templates.CityTemplate;
import text.WorldFrame.Templates.IndoorTemplate;
import text.WorldFrame.Templates.WorldTemplate;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class CaveWorld extends World{
    public static String bits = ".";
    public static ColorTuple currentColor = new ColorTuple(new Color(112, 86, 74),Color.ORANGE,' ');
    
    public static ColorTuple marker = new ColorTuple(Color.GRAY,Color.BLACK,' ');
    public int lastDepth = 0;
    public int nextDepth = 40;
    
    public Room R;
    public Room next;
    public World outside;
    
    public int depth = 1;
    //public int depth = 600;
    
    public ArrayList<Enemy> setBaseA = loadSetBaseA();
    public ArrayList<Enemy> setBaseB = loadSetBaseB();
    public ArrayList<Enemy> setBaseC = loadSetBaseC();
    public ArrayList<Enemy> setAltA = loadSetAltA();
    public ArrayList<Enemy> setAltB = loadSetAltB();
    public ArrayList<Enemy> setAltC = loadSetAltC();
    public ArrayList<Enemy> setCounterA = loadSetCounterA();
    public ArrayList<Enemy> setCounterB = loadSetCounterB();
    public ArrayList<Enemy> devious = loadSetDevious();
    public ArrayList<Enemy> boss = loadBoss();
    
    public ArrayList<WorldTemplate> subworlds = loadSubs();
    
    public CaveWorld(){
        super();
        
        this.name = "Cavern- Depth "+depth;
        
        this.E = new Environment(Environment.TYPE_NONE);
        
        this.R = new Room(0,this);
        this.next = new Room(1,this);
        
        addRoom(R);
        addRoom(next);
        
        redecorate(R);
    }
    
    public int addRoom(Room Rom){
        int ret = super.addRoom(Rom);
        R.block(Room.NORTH,4);
        R.block(Room.SOUTH,4);
        R.block(Room.EAST,4);
        R.block(Room.WEST,4);
        return ret;
    }
    
    public boolean needsRooms(){
        return roomCount() < 2;
    }
    
    public boolean adjacent(int a, int b){
        if(a == 1 && b == 1) return true;
        return false;
    }
    
    public ArrayList<Integer> adjacentList(int a){
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(0);
        return ret;
    }
    
    public void playerOutOfBounds(int dir){
        switchRoom(0);
    }
    
    public int getLinkRoom(){
        return -1;
    }
    
    public void displayMap(){
        if(depth == 666){
            
            String[] ret = new String[]{"There is no hell below, nor above. Only Science."};
            this.display(ret);
        } else if(depth > 666){
            String[] ret = new String[]
            {
                "Congrats, you did it. You're past floor 666.",
                "Whenever you're done doing... whatever it is you wanted to do?",
                "Type return into the command line to go back.",
                "Take care and have fun down here!!",
                "(and no, this isn't purgatory, so the text was right)"
            };
            this.display(ret);
        } else {
            String[]ret=new String[4];
            ret[0] = "" + name;
            ret[1] = "[Cavern]--> Shovel --> [Deeper Cavern];";
            ret[2] = "-OR-";
            ret[3] = "Enter--> Return --> Return to Main";
            this.display(ret);
        }
        
    }
    
    ///
    /// SPAWNING ROOM STUFF
    ///
    
    public void swapRooms(){
        ArrayList<Actor> check = R.getActors();
        for(Actor A : check){
            try{
                if(!((Enemy)A).defeated){
                    this.display(new String[]{"You're not done here yet."});
                    return;
                }
            } catch (Exception E){}
        }
        
        Room temp = R;
        R = next;
        next = temp;
        
        this.depth += 1;
        this.name= "Cavern- Depth "+depth;
        
        redecorate(R);
        
        this.switchRoom(R.id);
    }
    
    private void redecorate(Room rom){
        //First we kill all of them.
        ArrayList<Actor> clear = rom.getActors();
        for(Actor A : clear){
            A.dead = true;
        }
        Player.The.dead = false;
        
        //Special case: Last floor.
        if(this.depth == 666){
            
            currentColor = new ColorTuple(Color.GRAY,Color.BLACK,' ');
            if(Progress.The.SCIENCE == 0){
                rom.addActor(new Scientist());
                rom.BG.newBits(currentColor, "|___", 20000);
            } else if (Progress.The.SCIENCE == 1){
                rom.addActor(new ScienceFight());
                rom.BG.newBits(currentColor, "|||_", 20000);
            } else {
                rom.BG.newBits(currentColor, "\\/", 20000);
            }
            
            rom.addActor(new ScienceObject());
            rom.addActor(new ScienceObject());
            rom.addActor(new ScienceObject());
            rom.addActor(new ScienceObject());
            
            
        } else if(this.depth > 666){
            currentColor = new ColorTuple(Color.BLACK,Color.WHITE,' ');
            rom.BG.newBits(currentColor, bits, depth);
        }else {
            //Then we add new ones.
            newBack(R);
            NPC(R);
            addBits(R);
            addRocks(R);
        }
        
        R.block(Room.NORTH,4);
        R.block(Room.SOUTH,4);
        R.block(Room.EAST,4);
        R.block(Room.WEST,4);
    }
    
    private void NPC(Room add){
        
        
        if(this.depth == 1){
            add.addActor(new CaveSpiderA());
        }
        
        if(this.depth == 2){
            add.addActor(new CaveSpiderSkip());
            add.addActor(new CaveSpiderSkip());
            add.addActor(new CaveSpiderSkip());
        }
        
        if(this.depth == 3){
            add.addActor(new CaveSpiderB());
        }
        
        if(depth < 360){
            int get = 1+oRan.nextInt(depth);
            System.out.println(get);
            try{
                Enemy toAdd = null;
                switch(get){
                    case 3: case 9: case 14:
                        toAdd = selectNPCFromList(setBaseA);
                        break;
                    case 15: case 21: case 25:
                        toAdd = selectNPCFromList(setBaseB);
                        break;
                    case 27: case 30: case 33:
                        toAdd = selectNPCFromList(setBaseC);
                        break;
                    case 35: case 40: case 45: case 50:
                        toAdd = selectNPCFromList(setAltA);
                        break;
                    case 55: case 60: case 65: case 70:
                        toAdd = selectNPCFromList(setAltB);
                        break;
                    case 75: case 80: case 95: case 100:
                        toAdd = selectNPCFromList(setAltC);
                        break;    
                    case 105: case 110: case 120: case 140: case 150:
                        toAdd = selectNPCFromList(setCounterA);
                        break;
                    case 160: case 170: case 180: case 190: case 200:
                        toAdd = selectNPCFromList(setCounterB);
                        break;
                    case 215: case 230: case 245: case 260: case 275: case 290: case 300:
                        toAdd = selectNPCFromList(devious);
                        break;
                    case 323:
                        toAdd = selectNPCFromList(boss);
                }
                if(toAdd != null){
                    add.addActor(toAdd);
                }
                
                if(oRan.nextInt(33) <3 ){
                    add.addActor(new CaveSpiderSkip());
                }
                
                if(oRan.nextInt(100) < 30){
                    add.addActor(new Bone());
                    add.addActor(new Bone());
                    add.addActor(new Bone());
                    if(oRan.nextInt(100) < 40){
                        add.addActor(new Bone());
                        add.addActor(new Bone());
                    }
                }
                
            } catch (NullPointerException NPE){System.out.println("NONE ADDED");} //List was empty.

        }
        
        if(depth > 340 && depth < 460){
            int wormChance = oRan.nextInt(100);
            
            if(wormChance % 7 == 3 || wormChance % 5 == 2){
                for(int i = 0; i < wormChance; ++i){
                    if( i%5 == 2 || i%7 == 3 ){
                        add.addActor(new WormA());
                    }
                }
                if(depth > 450){
                    if(wormChance % 13 == 5 || wormChance % 3 == 1){
                        add.addActor(new WormB());
                    }
                }
            }
            
            
        }
        
        
        if(depth > 440){
            int molePeopleChance = oRan.nextInt(depth);
            if(molePeopleChance > 450){
                add.addActor(new MoleManA());
                
                if(molePeopleChance > 600){
                    addDoor(add);
                    add.addActor(new MoleA());
                    add.addActor(new MoleManA());
                }
                
            } else if (molePeopleChance > 200){
                add.addActor(new MoleA());
            }
            
            if(molePeopleChance%7 == 0 && depth < 630){
                add.addActor(new MoleSkip());
            }
        }
    }
    
    private void addDoor(Room R){
        WorldTemplate choose = this.subworlds.get(oRan.nextInt(this.subworlds.size()));
        
        /*
        World temp;
        temp = WorldBuilder.generate(choose);
        
        Door D = new Door(temp,"Door");
        */
        
        Door D = new Door(choose,"Door");
        choose.toSet = D;
        D.current = R;
        
        D.y = 20;
        D.x = TextDisplay.SCREEN_SIZE_X / 2;
        D.depth = -1;
        
        R.addActor(D);
        
    }
    
    private Enemy selectNPCFromList(ArrayList<Enemy> eList){
        if(eList.size() > 0){
                    int get = oRan.nextInt(eList.size());
                    Enemy e = eList.get(get);
                    eList.remove(e);
                    return e;
        }
        return null;
    }
    
    private void newBack(Room add){
        if(depth == nextDepth){
            currentColor = marker;
            marker = ColorTuple.getRandom();
            nextDepth += 13;
            lastDepth = depth;
        } else {
            if(depth - lastDepth > (nextDepth - depth)*1/3){
                ColorTuple CT = ColorTuple.mergeColors(currentColor,marker);
                CT = ColorTuple.mergeColors(currentColor,CT);
                currentColor = ColorTuple.mergeColors(currentColor,CT);
            }
        }
        
        add.BG.newBits(currentColor, bits, depth);
    }
    
    private void addBits(Room add){
        
        int num = SuperRandom.nextIntJump(3, 90);
        
        for(int i = 0; i<num; ++i){
            ColorTuple C = ColorTuple.getRandom();
            C.primary = currentColor.primary;
            C.secondary = currentColor.secondary;
            
            Pickup P2 = new Pickup(C);
            
            P2.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
            P2.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
            
            add.addActor(P2);
        }
        
    }
    
    private void addRocks(Room add){
        int num = SuperRandom.nextIntJump(7, 10);
        
        for(int i = 0; i<num; ++i){
            Rock R = new Rock(currentColor);
            
            R.x = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_X);
            R.y = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_Y);
            
            add.addActor(R);
        }
        
    }
    
    /////
    ///// enemy loading
    /////
    
    public static ArrayList<Enemy> loadSetBaseA(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_Rock());
        ret.add(new Spider_Rock());
        ret.add(new Spider_Rock());
        
        return ret;
    }
    
    public static ArrayList<Enemy> loadSetBaseB(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_Rock());
        ret.add(new Spider_Paper());
        
        ret.add(new Spider_Rock());
        ret.add(new Spider_Paper());
        
        ret.add(new Spider_Rock());
        ret.add(new Spider_Paper());
        
        return ret;
    }
    
    public static ArrayList<Enemy> loadSetBaseC(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_Paper());
        ret.add(new Spider_Scissors());
        ret.add(new Spider_Scissors());
        
        ret.add(new Spider_Paper());
        ret.add(new Spider_Scissors());
        ret.add(new Spider_Scissors());
        
        ret.add(new Spider_Paper());
        ret.add(new Spider_Scissors());
        ret.add(new Spider_Scissors());
        
        return ret;
    }
    
    public static ArrayList<Enemy> loadSetAltA(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_RockPaper());
        ret.add(new Spider_RockPaper());
        ret.add(new Spider_RockPaper());
        
        
        return ret;
    }
    
    public static ArrayList<Enemy> loadSetAltB(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_RockPaper());
        ret.add(new Spider_RockScissors());
        
        ret.add(new Spider_RockPaper());
        ret.add(new Spider_RockScissors());
        
        ret.add(new Spider_RockPaper());
        ret.add(new Spider_RockScissors());
        
        
        return ret;
    }
    
    public static ArrayList<Enemy> loadSetAltC(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_RockScissors());
        ret.add(new Spider_PaperScissors());
        ret.add(new Spider_PaperScissors());
        
        ret.add(new Spider_RockScissors());
        ret.add(new Spider_PaperScissors());
        ret.add(new Spider_PaperScissors());
        
        ret.add(new Spider_RockScissors());
        ret.add(new Spider_PaperScissors());
        ret.add(new Spider_PaperScissors());
        
        return ret;
    }
    public static ArrayList<Enemy> loadSetCounterA(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_PlayerCounter());
        ret.add(new Spider_PlayerCounter());
        ret.add(new Spider_PlayerCounter());
        
        return ret;
    }
    public static ArrayList<Enemy> loadSetCounterB(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_PlayerReverseCounter());
        ret.add(new Spider_PlayerReverseCounter());
        ret.add(new Spider_PlayerReverseCounter());
        
        return ret;
    }
    
    public static ArrayList<Enemy> loadSetDevious(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_BaitNSwitch());
        ret.add(new Spider_Random());
        
        ret.add(new Spider_BaitNSwitch());
        ret.add(new Spider_Random());
        
        ret.add(new Spider_BaitNSwitch());
        ret.add(new Spider_Random());
        
        return ret;
    }
    
    public static ArrayList<Enemy> loadBoss(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Spider_BaitNSwitch());
        ret.add(new Spider_Random());
        
        ret.add(new Spider_Boss());
        
        return ret;
    }
    
    //GENERATE INDOOR TEMPLATES ETC.
    
    public static ArrayList<WorldTemplate> loadSubs(){
        ArrayList<WorldTemplate> ret = new ArrayList<>();
        
        ret.add(new IndoorTemplate());
        ret.add(new IndoorTemplate());
        ret.add(new IndoorTemplate());
        
        return ret;
    }
}

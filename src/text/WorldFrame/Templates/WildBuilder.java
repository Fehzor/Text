/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Instances.HighLighter;
import text.Actors.Instances.VendingMachine;
import text.Actors.Interactable;
import text.Environment.Animals.Animal;
import text.Environment.Animals.AnimalTemplate;
import text.Environment.Animals.Nest;
import text.Environment.Environment;
import text.Environment.Plants.SmallPlant;
import text.Environment.Inanimate.Rock;
import text.Environment.Plants.Tree;
import text.Frame.TextDisplay;
import text.Utility.SuperRandom;
import text.WorldFrame.Room;
import static text.WorldFrame.Templates.WorldBuilder.oRan;
import static text.WorldFrame.Templates.WorldTemplate.GRAVEYARD;
import text.WorldFrame.World;
import text.WorldFrame.Worlds.CircleWorld;
import text.WorldFrame.Worlds.Cities.CityGridCenter;
import text.WorldFrame.Worlds.Cities.CityHallway;
import text.WorldFrame.Worlds.GridWorld;
import text.WorldFrame.Worlds.LostWorld;
import text.WorldFrame.Worlds.WordSearchWorld;

/**
 * @author FF6EB4
 */
public class WildBuilder extends WorldBuilder{
    
    public static World generate(WorldTemplate WT){
        Environment E = WT.E;
        World ret = new GridWorld((int) Math.sqrt((double)WT.rooms),E);
        
        if(WT.worldType == WildTemplate.LOST_WORLD){
            ret = new LostWorld(WT.rooms,E);
        }
        
        if(WT.worldType == WildTemplate.GRID_CITY){
           ret = new CityGridCenter(WT.size,(int)Math.ceil(((double)WT.size)/3.0));
        }
        
        if(WT.worldType == WildTemplate.ROUND_WORLD){
            ret = new CircleWorld((int) Math.sqrt((double)WT.rooms),(int) Math.sqrt((double)WT.rooms),E);
        }
        
        if(WT.worldType == WorldTemplate.CITY_HALLWAY){
            ret = new CityHallway((int) Math.sqrt((double)WT.rooms) + 3);
        }
        
        
        
        //
        // THERE IS A RANDOM CHANCE (1/100) OF A WORDSEARCH WORLD APPEARING INSTEAD.
        //
        boolean wordsearch = false;
        VendingMachine VM = null;
        if(oRan.nextInt(100) == 23 || WT.worldType == WorldTemplate.WORDSEARCH){
            wordsearch = true;
            ret = new WordSearchWorld((int) Math.sqrt((double)WT.rooms),E);
            VM = new VendingMachine();
            VM.stock(new HighLighter((WordSearchWorld)ret), "bright", 25);
            VM.x = SuperRandom.nextIntSkewed(TextDisplay.SCREEN_SIZE_X);
            VM.y = SuperRandom.nextIntSkewed(TextDisplay.SCREEN_SIZE_Y);
        }
        //
        // THERE IS A RANDOM CHANCE (1/100) OF A WORDSEARCH WORLD APPEARING INSTEAD.
        //
        
        ret.E = WT.E;
        ret.source = WT;
        
        ArrayList<SmallPlant> spList = WT.smallPlantList;
        ArrayList<Tree> tList = WT.treeList;
        ArrayList<Animal> aList = WT.animalList;
        ArrayList<Rock> rList = WT.rockList;
        
        ArrayList<Interactable> lList = new ArrayList<>();
        for(Interactable eta : WT.lootA){
            lList.add(eta);
        }
        
        while(ret.needsRooms()){
            Room R = new Room(ret.roomCount(),ret);
            
            if(WT.worldKind == GRAVEYARD){
                if(aList != null && aList.size() > 0){
                    addAnimals(R,aList,SuperRandom.nextIntJump(2,35),WT.E);
                }

                if(spList != null && spList.size() > 0){
                    placeOthers(R,spList);
                }

                if(tList != null && tList.size() > 0){
                    placeOthers(R,tList);
                }
                
                if(rList != null && rList.size() > 0){
                    addRocks(R,rList,SuperRandom.nextIntJump(15,10));
                }
                
                ret.name = WT.adjective + " Graveyard";
            } else {
            //addPlants(R,pList,oRan.nextInt(3));
                if(aList != null && aList.size() > 0){
                    addAnimals(R,aList,SuperRandom.nextIntJump(3,45),WT.E);
                }

                if(spList != null && spList.size() > 0){
                    addSmallPlants(R,spList);
                }

                if(tList != null && tList.size() > 0){
                    addTrees(R,tList,lList);
                }
                
                if(rList != null && rList.size() > 0){
                    addRocks(R,rList,SuperRandom.nextIntJump(E.soil.rocks,50));
                }
                
                ret.name = WT.adjective + " Wilds";
            }
            
            
            
            if(WT.cityPartsA != null && WT.cityPartsA.size() > 0){
                placeOthers(R, WT.cityPartsA);
            }
            
            if(WT.cityPartsB != null && WT.cityPartsB.size() > 0){
                placeOthers(R, WT.cityPartsB);
            }
            
            if(WT.cityPartsC != null && WT.cityPartsC.size() > 0){
                placeOthers(R, WT.cityPartsC);
            }
            
            ret.addRoom(R);
        }
        
        if(wordsearch && VM != null){
            ret.getRoom(0).addActor(VM);
        }
        
        
        
        return ret;
    }
    
    public static void placeOthers(Room R, ArrayList aList){
        for(Object o : aList){
            if(o instanceof Actor && oRan.nextInt(100) < 20){
                Actor get = (Actor) o;
                Actor add = get.clone();
                
                add.x = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_X);
                add.y = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_Y);
                add.depth = add.y;
                
                R.addActor(add);
            }
        }
    }
    
    public static void addAnimals(Room R, ArrayList<Animal> aList, int num, Environment E){
        if(aList == null || aList.size() == 0){
            return;
        }
        for(int i = 0; i<num; ++i){
            
            Animal a = aList.get(SuperRandom.nextIntSkewed(aList.size())).clone(E);
            
            int x = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_X); 
            int y = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_Y);
            a.x = x;
            a.y = y;
            a.current = R;

            String name = a.name;
            if(oRan.nextInt(100) < 35 && AnimalTemplate.animalNests.get(name) != null){
                addNest(R,a);
            }
            
            R.addActor(a);
        }
        
        
    }
    
    public static void addNest(Room R, Animal A){
        String name = A.name;
        Nest N = new Nest(AnimalTemplate.animalNests.get(name));
        
        int x = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_X); 
        int y = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_Y);
        
        N.x = x;
        N.y = y;
        
        N.name = name+" nest";
        
        A.home = N;
        
        for(int i = 0; i<oRan.nextInt(5); ++i){
            Animal B = (Animal)(A.clone());
            N.add(B);
        }
        R.addActor(N);
    }
    
    public static void addRocks(Room R, ArrayList<Rock> rList, int num){
        if(rList == null || rList.size() == 0){
            return;
        }
        for(int i = 0; i<num; ++i){
            Rock r = rList.get(SuperRandom.nextIntSkewed(rList.size())).clone();
            
            if(r.name.equals("Rock")){
                int x = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_X-20)+10; 
                int y = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_Y-20)+10;
                r.x = x;
                r.y = y;
                r.current = R;
                r.depth = y;
            }
            
            if(r.name.equals("Tombstone")){
                int x = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_X-20)/15;
                int y = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_Y-20)/30;
                x = x * 15;
                y = y * 30;
                x = x + 10;
                y = y + 10;
                r.x = x;
                r.y = y;
                r.current = R;
                r.depth = y;
            }
            
            R.addActor(r);
        }
    }

    
    /**
     * Add grass to a specified room.
     */
    public static void addSmallPlants(Room R, ArrayList<SmallPlant> spList){
        if(spList == null || spList.size() == 0){
            return;
        }
        int a = TextDisplay.SCREEN_SIZE_Y / 8;
        int b = TextDisplay.SCREEN_SIZE_X / 12;
        
        a+=1;
        b+=1;
        
        SmallPlant add = spList.get(SuperRandom.nextIntSkewed(spList.size())).clone();
        
        for(int i = 0; i<a; ++i){
            for(int j = 0; j<b; ++j){
                
                if(oRan.nextInt(100) < add.ratio){
                    //Grass G = new Grass(j * 12,i * 8);
                    SmallPlant G = add.clone();
                    G.x = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_X -20)+10;
                    G.y = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_Y -20)+10;
                    G.depth = G.y;
                    //ImageBuilder.addNoise(((TextImageBasic)G.image),35);
                    R.addActor(G);
                }
            }
        }
    }
    
    /**
     * Add big grass.
     */
    public static void addTrees(Room R, ArrayList<Tree> tList, ArrayList<Interactable> lList){
        if(tList == null || tList.size() == 0){
            return;
        }
        
        int a = TextDisplay.SCREEN_SIZE_Y / 8;
        int b = TextDisplay.SCREEN_SIZE_X / 12;
        
        a+=1;
        b+=1;
        
        Tree add = tList.get(SuperRandom.nextIntSkewed(tList.size()));
        
        
        
        
        for(int i = 0; i<a; ++i){
            for(int j = 0; j<b; ++j){
                
                if(oRan.nextInt(100) < add.ratio){
                    //Grass G = new Grass(j * 12,i * 8);
                    Tree G = add.clone();
                    G.x = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_X -20)+10;
                    G.y = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_Y -20)+10;
                    G.depth = G.y;
                    //ImageBuilder.addNoise(((TextImageBasic)G.image),35);
                    
                    //Hide things from loot into the trees.
                    
                    if(lList.size() > 0 && oRan.nextInt(100) < 77){ // 
                        
                        Interactable stash = (Interactable)lList.get(SuperRandom.nextIntSkewed(lList.size()));
                        G.give(stash);
                    }
                    R.addActor(G);
                }
            }
        }
    }
}

/**

 */

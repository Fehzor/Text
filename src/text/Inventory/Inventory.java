/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Inventory;
import text.Actors.Messages.QuickDisplay;
import text.Utility.MenuBuilder;
import java.util.*;
import text.Actors.*;
import text.Frame.*;
import text.Utility.*;
//import text.Tools.*;
import java.io.Serializable;
import text.Actors.Instances.*;
import text.Actors.Instances.Chests.Password;
import text.Environment.Inanimate.Rock;
import text.Game.GamePiece;
import text.Tools.ToolPart;

/**
 *
 * @author FF6EB4
 */
public class Inventory implements Serializable{
    public static final int DISPLAY_WIDTH = TextDisplay.SCREEN_SIZE_X;
    ArrayList<Resource> resources;
    public static Random oRan = new Random();
    
    int maxStuff;
    ArrayList<Physical<Actor>> stuff;
    
    Drawable window;
    
    public Inventory(int maxStuff){
        this.maxStuff = maxStuff;
        resources = new ArrayList<>();
        stuff = new ArrayList<>();
    }
    
    //Sets the max stuff holdable.
    public void setMax(int limit){
        this.maxStuff = limit;
    }
    
    public void displayResource(String query){
        ArrayList<Resource> disp = queryResources(query);
        Drawable d = MenuBuilder.buildResourceDisplay(disp);
        new QuickDisplay(Player.The.current,d);
    }
    
    //Returns a list of actors!
    public ArrayList<Actor> inspectStuff(){
        ArrayList<Actor> aList = new ArrayList<>();
        for(Physical P : stuff){
            aList.add((Actor)P.getData());
        }
        
        return aList;
    }
    
    public ArrayList<Physical> queryStuff(String q){
        ArrayList<Physical> ret = new ArrayList<>();
        //TODO- Add more special queries; i.e. '-red' or 'a-d' etc.
        
        Scanner oScan = new Scanner(q);
        
        String first = oScan.next().toLowerCase();
        if(first.equals("gamepiece")){
            for(int i = 0; i<stuff.size(); ++i){
                Physical p = stuff.get(i);
                if(p.getData() instanceof GamePiece){
                    ret.add(p);
                    //System.out.println("GOT IT!");
                }
            }
            return ret;
        }
        
        if(first.equals("password")){
            for(int i = 0; i<stuff.size(); ++i){
                Physical p = stuff.get(i);
                if(p.getData() instanceof Password){
                    ret.add(p);
                    //System.out.println("GOT IT!");
                }
            }
            return ret;
        }
        
        
        if(first.equals("toolpart")){
            for(int i = 0; i<stuff.size(); ++i){
                Physical p = stuff.get(i);
                if(p.getData() instanceof ToolPart && ((ToolPart)p.getData()).type != ToolPart.TYPE_HANDLE){
                    ret.add(p);
                    //System.out.println("GOT IT!");
                }
            }
            return ret;
        }
        
        if(first.equals("trapdoor")){
            for(int i = 0; i<stuff.size(); ++i){
                Physical p = stuff.get(i);
                if(p.getData() instanceof Door){
                    ret.add(p);
                    //System.out.println("GOT IT!");
                }
            }
            return ret;
        }
        
        if(first.equals("rock")){
            for(int i = 0; i<stuff.size(); ++i){
                Physical p = stuff.get(i);
                if(p.getData() instanceof Rock){
                    ret.add(p);
                    //System.out.println("GOT IT!");
                }
            }
            return ret;
        }
        
        
        if(first.equals("all")){
            for(int i = 0; i<stuff.size(); ++i){
                Physical p = stuff.get(i);
                if(true){
                    ret.add(p);
                    //System.out.println("GOT IT!");
                }
            }
            return ret;
        }
        
        return ret;
    }
    
    public ArrayList<Resource> queryResources(String q){
        ArrayList<Resource> ret = new ArrayList<>();
        //TODO- Add special queries; i.e. '-red' or 'a-d' etc.
        
        for(int i = 0; i<resources.size(); ++i){
                Resource r = resources.get(i);

                if(r.isType(q)){
                    ret.add(r);
                    //System.out.println("GOT IT!");
                }
        }
        
        if(ret.size() > 0){
            return ret;
        }
        
        return ret;
        /*
        String checkfor = "";
        checkfor += q;
        checkfor = checkfor.toLowerCase();
        checkfor += checkfor.toUpperCase();
        
        for(int i = 0; i<resources.size(); ++i){
            Resource r = resources.get(i);
            
            if(checkfor.contains(r.icon.icon+"")){
                ret.add(r);
                //System.out.println("GOT IT!");
            }
        }
        return ret;
        */
    }
    
    /**
     * Are there amount of q resources in this inventory?
    */
    public boolean checkResources(String q, int amount){
        ArrayList<Resource> removeFrom = queryResources(q);
        
        int total = 0;
        for(Resource R : removeFrom){
            total += R.amount;
        }
        
        boolean ret = total >= amount;
        return ret;
    }
    
    /**
     * Removes random resources of type q in amount amount.
     * 
     * @param q
     * @param amount 
     * 
     * Returns a core, filled with the removed colorTuples
     */
    public Core drainResources(String q, int amount){
        ArrayList<Resource> removeFrom = queryResources(q);
        ArrayList<Resource> removeTo = new ArrayList<>();
        
        HashMap<Integer, Integer> lotto = new HashMap<>();
        
        int total = 0;
        int num = 0;
        for(Resource R : removeFrom){
            total += R.amount;

            lotto.put(num,total);
            
            num += 1;
        }
        
        while(amount > 0){
            int ticket = oRan.nextInt(total);
            
            num = 0;
            while(lotto.get(num) < ticket){
                num++;
            }
            
            removeFrom.get(num).amount -= 1;
            Resource R = removeFrom.get(num).clone();
            removeTo.add(R);
            
            if(removeFrom.get(num).amount < 0){
                amount += 1;
            }
            
            amount -= 1;
        }
        
        checkResources();
        
        return new Core(removeTo);
    }
    
    public void transferResourcesTo(Inventory inv){
        for(Resource r : resources){
            inv.put(r);
        }
        this.resources = new ArrayList<>();
    }
    
    
    
    //Adds a physical object to the list
    public boolean addStuff(Physical p){
        ((Actor)p.getData()).held = true;
        if(!full()){
            stuff.add(p);
            return true;
        }
        return false;
    }
    
    public boolean full(){
        return !(maxStuff > stuff.size());
    }
    
    public void removeStuff(Actor A){
        A.held = false;
        removeNotHeld();
    }
    
    public void removeNotHeld(){
        for(int i = stuff.size()-1; i>=0; --i){
            if(stuff.get(i).getData().held == false){
                stuff.remove(i);
            }
        }
    }
    
    //Puts a ColorTuple in.
    public void put(ColorTuple CT){
        //TODO Add colors as a resource!
        //System.out.println("PUT");
        put(new Resource(CT));
    }
    
    //Puts a Resource, R, in
    public void put(Resource R){
        int addTo = resources.indexOf(R);
        if(addTo != -1){
            resources.get(addTo).amount++;
        } else {
            resources.add(R);
        }
    }
    
    //Check for and remove zero resources
    private void checkResources(){
        for(int i = resources.size()-1; i > 0; --i){
            if(resources.get(i).amount <= 0){
                resources.remove(i);
            }
        }
    }
    
    //Is there a window to close?
    public boolean checkWindow(){
        if(window!=null){
            return true;
        } else return false;
    }
    
    public void clearWindow(){
        TextDisplay.drawables.remove(window);
        this.window = null;
    }
}

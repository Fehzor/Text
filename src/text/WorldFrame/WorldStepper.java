/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Player;

/**
 * A threaded class that moves things around in the world
 * 
 * Threaded to avoid lagging the main world loop
 * 
 * @author FF6EB4
 */
public class WorldStepper implements Runnable{
    public static final int WAIT_TIME = 1000;
    boolean end = false;
    World W;
    public WorldStepper(World W){
        this.W = W;
        Thread T = new Thread(this);
        T.start();
    }
    
    public void run(){
        if(end)return;
        
        //System.out.println("WORLD STEPPING");;
        
        if(W.worldActors.size()>0){
            //System.out.println("yes...");
            for(int i = W.worldActors.size()-1; i>=0; --i){
                Actor A = W.worldActors.get(i);
                try{
                    if(A.world ==true){
                        //System.out.println(A+"START");
                        A.worldStep(W);
                        //System.out.println(A+"END");
                    }
                } catch (Exception E){
                    try{
                        A.world = false;
                    }catch(NullPointerException NPE){
                    System.out.println("IDK");}
                }//Sometimes this is called too early.
            }
        }
        
        W.updatePuzzles();
        
        try{
            Thread.sleep(WAIT_TIME);
        } catch (Exception E){}
        this.run();
    }
    
    /**
     * Tells this thread to end- used mostly while switching worlds.
     */
    public void kill(){
        this.end = true;
    }
}

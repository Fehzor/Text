/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame;

import text.WorldFrame.Progress;
import java.io.*;
import text.WorldFrame.Templates.WorldBuilder;
import text.WorldFrame.Templates.WildTemplate;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import text.Actors.Player;
import text.WorldFrame.MainWorld;
import text.WorldFrame.Regions.Region;
import text.WorldFrame.Regions.RegionFactory;
import static text.WorldFrame.Regions.RegionFactory.KIND_CIRCLE;
import static text.WorldFrame.Regions.RegionFactory.KIND_CITY;
import static text.WorldFrame.Regions.RegionFactory.KIND_CLUMP;
import text.WorldFrame.Templates.CityTemplate;
import text.WorldFrame.Templates.GraveyardTemplate;
import text.WorldFrame.Templates.WildBuilder;
import text.WorldFrame.Templates.WorldTemplate;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class GameCoordinator implements Observer{
    public static GameCoordinator The = new GameCoordinator();
    private GameCoordinator(){}
    
    private final Lock lock = new ReentrantLock();
    
    public World current;
    
    public Progress prog = loadProgress();
    
    public Region wildRegion = null;
    public Region cityRegion = null;
    //Eventually add more to this?
    
   

    public void update(Observable o, Object obj){
        if(lock.tryLock() == false){
            return;
        }
        //*/
        if(wildRegion == null){
                wildRegion = RegionFactory.spawn(new WildTemplate(),KIND_CLUMP,prog.wildSize-2);
                MainWorld.main = wildRegion.head;
        }
        //*/
        if(prog.citySize!=0 && cityRegion == null){
                cityRegion = RegionFactory.spawn(new CityTemplate(),KIND_CITY,prog.citySize);
                //MainWorld.main = cityRegion.head;
                
                if(prog.citySize>0){
                    cityRegion.connect(wildRegion);
                }
        }
        
        //*/
        try{
            current = Player.The.current.owner;
            if(current.currentRoom.id == -1){
                lock.unlock();
                return;
            } else {
                //Link here if Infinite?
            }
        } catch (Exception E){}
        saveProgress();

        lock.unlock();
        //} catch (NullPointerException NPE){}
    }
    
    public Progress loadProgress(){
        try{
            File F = new File("progress");
            FileInputStream FIS = new FileInputStream(F);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            
            Object o = (OIS.readObject());
            Progress P = (Progress) o;
            Progress.The = P;
            
            Progress.loadAllStories();
            
            return P;
            
        } catch (Exception E){
            System.out.println("progress not found... starting anew");
            Progress P = new Progress();
            Progress.loadAllStories();
            return P;
        }
    }
    
    public void saveProgress(){
        try{
            File F = new File("progress");
            FileOutputStream FOS = new FileOutputStream(F);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            
            OOS.writeObject(this.prog);
            
            
        } catch (Exception E){
            System.out.println("Err saving progress");
        }    
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances.Chests;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Convertable;
import text.Actors.Interactable;
import text.Actors.Messages.Option;
import text.Frame.TextDisplay;
import text.Images.TextImage;
import text.Inventory.Inventory;
import text.Inventory.Resource;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;
import text.Utility.LootGenerator;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class Chest extends Interactable implements Convertable{
    public Inventory inv = new Inventory(10);
    
    public Chest(){
        super();
        this.image = getImage();
        this.name = "Safe";
        this.dead = false;
        this.x = TextDisplay.SCREEN_SIZE_X / 2;
        this.y = TextDisplay.SCREEN_SIZE_Y / 2 + 10;
        
        this.generateRewardsBasic();
    }
    
    public TextImage getImage(){
        TextImage ret;
        
        ret = ImageLoader.loadAnimated("safe.txt","safe.txt");
        
        return ret;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> aList = super.pollOptions();
        
        Option E = Option.checkInventory(this, inv);
        aList.add(E);
        
        Option F = Option.takeInventory(this, inv);
        aList.add(F);
        
        for(Actor A : inv.inspectStuff()){
            Option H = Option.take(A, inv);
            aList.add(H);
        }

        return aList;
    }
    
    public ArrayList<Resource> convert(){
        return LootGenerator.getLootClassic(this.toString(),image);
    }
    
    public void generateRewardsBasic(){
        int r = oRan.nextInt(100);
        
        if ( r == 23){
            ColorTuple A = ColorTuple.getRandom();
            Resource AR = new Resource(A,1000);
            this.inv.put(AR);
            
            ColorTuple B = ColorTuple.getRandom();
            Resource BR = new Resource(B,1000);
            this.inv.put(BR);
            
            ColorTuple C = ColorTuple.getRandom();
            Resource CR = new Resource(C,1000);
            this.inv.put(CR);
            
        } else if(r < 40){
            ColorTuple me = ColorTuple.getRandom();
            Resource R = new Resource(me,100);
            this.inv.put(R);
        } else if (r < 70){
            ColorTuple A = ColorTuple.getRandom();
            Resource AR = new Resource(A,80);
            this.inv.put(AR);
            
            ColorTuple B = ColorTuple.getRandom();
            Resource BR = new Resource(B,50);
            this.inv.put(BR);
            
            ColorTuple C = ColorTuple.getRandom();
            Resource CR = new Resource(C,20);
            this.inv.put(CR);
        } else if (r < 90){
            for(int i = 0; i < 10; ++i){
                ColorTuple A = ColorTuple.getRandom();
                Resource AR = new Resource(A,25);
                this.inv.put(AR);
            }
        } else {
            
        }
    }
}

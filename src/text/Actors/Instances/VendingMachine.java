/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Utility.ImageLoader;
import text.Images.TextImageBasic;
import text.Inventory.Resource;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class VendingMachine extends CityPart{
    
    /*
    Will be used to determine based on what it sells, what it looks like.
    */
    public int type = -1;
    
    public static final int GENERIC = 0;
    public static final int GACHA = 1;
    public static final int FOOD = 2;
    public static final int UNDEROO = 3;
    public static final int NEWS = 4;
    public static final int WILD = 5;
    public static final int PILLS = 6;
    public static final int DRINKS = 7;
    public static final int TOOLS = 8;
    
    ArrayList<Actor> vendingStuffs;
    ArrayList<String> priceTypes;
    ArrayList<Integer> prices;
    
    public VendingMachine(int type){
        super();
        
        vendingStuffs = new ArrayList<>();
        priceTypes = new ArrayList<>();
        prices = new ArrayList<>();
        
        this.dead = false;
        this.name = "Vending Machine";
        loadImage(type);
        //ImageBuilder.colorMergeImage((TextImageBasic)this.image, new ColorTuple(Color.BLUE,Color.ORANGE,' '));
    }
    
    public VendingMachine clone(){
        VendingMachine VM = new VendingMachine(this.type);
        VM.image = this.image.clone();
        
        for(int i = 0; i < vendingStuffs.size(); ++i){
            VM.stock(vendingStuffs.get(i),priceTypes.get(i),prices.get(i));
        }
        
        return VM;
    }
    
    public VendingMachine(){
        this(GENERIC);
    }
    
    private void loadImage(int type){
        ImageLoader.switchMap("CITYSCALE");
        switch(type){
            case GENERIC:
                if(oRan.nextInt(100) < 50){
                    this.image = ImageLoader.loadImage("vending_snacks.txt");
                } else {
                    this.image = ImageLoader.loadImage("vending_drinks.txt");
                }
                return;
            case GACHA:
                if(oRan.nextInt(100) < 50){
                    this.image = ImageLoader.loadImage("vending_gachapon.txt");
                } else {
                    this.image = ImageLoader.loadImage("vending_gumball.txt");
                }
                return;
            case FOOD:
                this.image = ImageLoader.loadImage("vending_snacks.txt");
                return;
            case UNDEROO:
                ImageLoader.switchMap("HUMAN");//Special to these two.
                if(oRan.nextInt(100) < 50){
                    System.out.println("A");
                    this.image = ImageLoader.loadImage("vending_panties.txt");
                } else {
                    System.out.println("B");
                    this.image = ImageLoader.loadImage("vending_boxers.txt");
                }
                return;
            case NEWS:
                this.image = ImageLoader.loadImage("vending_newsstand.txt");
                return;
            case WILD:
                this.image = ImageLoader.loadImage("vending_broken.txt");
                return;
            case PILLS:
                this.image = ImageLoader.loadImage("vending_pills.txt");
                return;
            case DRINKS:
                if(oRan.nextInt(100) < 33){
                    this.image = ImageLoader.loadImage("vending_cola.txt");
                } else if(oRan.nextInt(100) < 50){
                    this.image = ImageLoader.loadImage("vending_drinks.txt");
                } else {
                    this.image = ImageLoader.loadImage("vending_milk.txt");
                }
                return;
            case TOOLS:
                this.image = ImageLoader.loadImage("vending_tools.txt");
        }
        
    }
    
    public boolean act(){ 
        this.depth = this.y;
        return true;
    }
    
    public void stock(Actor stuff, String type, int price){
        vendingStuffs.add(stuff);
        priceTypes.add(type);
        prices.add(price);
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        for(int i = 0; i< vendingStuffs.size(); ++i){
            ret.add(Option.vend(this,
                    vendingStuffs.get(i),
                    priceTypes.get(i),
                    prices.get(i)));
        }
        
        ret.add(Option.cancel(this));
        return ret;
    }
    
    public String toString(){
        return this.name;
    }
    
    public boolean purchased(){
        return true;
    }
}

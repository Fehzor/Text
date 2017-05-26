/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Dice;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Interactable;
import text.Actors.Player;
import text.Combat.Dice.DiceSet;
import text.Combat.Dice.DiceSet;
import text.Combat.Dice.DiceSet;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Tools.Tool;
import text.Tools.ToolEffect;
import text.Tools.ToolPart;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class Die extends ToolPart{
    public static ColorTuple bw1 = new ColorTuple(Color.BLACK,Color.WHITE,(char)('⚀'+0)); 
    
    
    public static ArrayList<Die> presets = loadPresets();
    
    public char sym;
    
    ArrayList<Integer> intList = new ArrayList<>();
    
    public Die(int x, int y){
        super(new TextImageBasic(bw1,false));
        this.type = TYPE_ACCESSORY;
        this.info = "Combine to form a dice set! Sides= "+intList;
        this.x = x;
        this.y = y;
    }
    
    public Die(int x, int y, Color back, Color front, int face, char symbol){
        this(x,y);
        
        ColorTuple temp = new ColorTuple(back,front,(char)('⚀'+face-1));
        this.image = new TextImageBasic(temp,false);
        
        this.sym = symbol;
        this.name = "Die ("+sym+")";
    }
    
    public String toString(){
        return this.name;
    }
    
    public Tool compile(Tool T){
        boolean compAll = false;
        if(T == null){
            compAll = true;
            T = new DiceSet();
            
            ToolEffect myArea = new ToolEffect(){
            public ArrayList<Actor> getArea(){
                ArrayList<Actor> hits = Player.The.current.getActors();
                ArrayList<Actor> ret = new ArrayList<>();
                for(Actor A : hits){
                    if(oRan.nextInt(100) < 23){
                        ret.add(A);
                    }
                }

                    return ret;
                }

            };
            
            T.effects.add(myArea);
            
            //CREATE AN ANIMATION
            ImageLoader.switchMap("HUMAN");
            TextImageComplex animate = new TextImageComplex(ImageLoader.loadImage("player_gunmode.txt"));
            ImageLoader.switchMap("CITYSCALE");
            TextImageComplex gunmodel = new TextImageComplex(ImageLoader.loadImage("dice.txt"));
            animate.addKnob(8, -16, gunmodel);
            
            T.animate = animate;
            T.animationTime = 40;
        }
        if(this.sym == '*'){
            int r = roll();
            T.animationTime = T.animationTime * r;
            T.power = T.power * r;
        }
        if(this.sym == '/'){
            int r = roll();
            T.animationTime = T.animationTime / r;
            T.power = T.power / r;
            if(T.power <=0)T.power = .01;
            if(T.animationTime <= 0) T.animationTime = 1;
        }
        if(this.sym == '+'){
            int r = roll();
            T.animationTime = T.animationTime + (r / 10);
            T.power = T.power + (r / 10);
        }
        if(this.sym == '+'){
            int r = roll();
            T.animationTime = T.animationTime - (r / 10);
            T.power = T.power - (r / 10);
            if(T.power <=0)T.power = .01;
            if(T.animationTime <= 0) T.animationTime = 1;
        }


        try{
            DiceSet D = (DiceSet)T;
            
            D.dice.add(this);
            
            if(this.sym == '-'){
                D.startHits += 7;
                D.missDivisor += 150;
            }
            
            if(this.sym == '+'){
                D.startHits += 5;
                D.missDivisor += 150;
            }
            
            if(this.sym == '*'){
                D.startHits += 7;
                D.missDivisor += 250;
            }
            
            if(this.sym == '/'){
                D.startHits += 1;
                D.missDivisor = (D.missDivisor * 17) / 20;
            }
            
        } catch (Exception E){System.out.println("NOT A DICE SET");}
        
        if(compAll){
            super.compile(T);
        }
        
        return T;
    }
    
    public int roll(){
        return intList.get(oRan.nextInt(intList.size()));
    }
    
    public String get(){
        return "" + sym + roll();      
        //If it rolls say, 5 and is a + dice- +5
    }
    
    public void addInt(int i){
        this.intList.add(i);
        this.info = "Sides="+intList;
    }
    
    public static ArrayList<Die> loadPresets(){
        ArrayList<Die> ret = new ArrayList<>();
        
        //+D6s- Black and white :D
        Die D6 = new Die(0,0,Color.BLACK,Color.WHITE,6,'+');
        for(int i = 0; i < 6; ++i){
            D6.addInt(i+1);
        }
        ret.add(D6);
        
        D6 = new Die(0,0,Color.BLACK,Color.WHITE,6,'+');
        D6.addInt(1);
        D6.addInt(1);
        D6.addInt(2);
        D6.addInt(3);
        D6.addInt(5);
        D6.addInt(8);
        ret.add(D6);
        
        //-D6s- White and green :D
        D6 = new Die(0,0,Color.WHITE,Color.GREEN,6,'-');
        for(int i = 0; i < 6; ++i){
            D6.addInt(i+1);
        }
        ret.add(D6);
        
        D6 = new Die(0,0,Color.WHITE,Color.GREEN,6,'-');
        D6.addInt(1);
        D6.addInt(1);
        D6.addInt(2);
        D6.addInt(3);
        D6.addInt(5);
        D6.addInt(8);
        ret.add(D6);
        
        //*D6s- Black and red :D
        D6 = new Die(0,0,Color.BLACK,Color.RED,6,'*');
        for(int i = 0; i < 6; ++i){
            D6.addInt(i+1);
        }
        ret.add(D6);
        
        D6 = new Die(0,0,Color.BLACK,Color.RED,6,'*');
        D6.addInt(0);
        D6.addInt(0);
        D6.addInt(5);
        D6.addInt(5);
        D6.addInt(10);
        D6.addInt(10);
        ret.add(D6);

        D6 = new Die(0,0,Color.BLACK,Color.RED,6,'*');
        D6.addInt(1);
        D6.addInt(2);
        D6.addInt(2);
        D6.addInt(4);
        D6.addInt(4);
        D6.addInt(8);
        ret.add(D6);     
        
        D6 = new Die(0,0,Color.BLACK,Color.RED,6,'*');
        D6.addInt(0);
        D6.addInt(0);
        D6.addInt(2);
        D6.addInt(2);
        D6.addInt(2);
        D6.addInt(11);
        ret.add(D6);   
        
        // /D6s- Pink and white :D
        D6 = new Die(0,0,Color.PINK,Color.BLACK,6,'/');
        for(int i = 0; i < 6; ++i){
            D6.addInt(i+1);
        }
        ret.add(D6);
        
        D6 = new Die(0,0,Color.PINK,Color.BLACK,5,'/');
        D6.addInt(0);
        D6.addInt(0);
        D6.addInt(5);
        D6.addInt(5);
        D6.addInt(10);
        D6.addInt(10);
        ret.add(D6);
        
        //10 sided
        Die D10 = new Die(0,0,Color.BLACK,Color.WHITE,3,'+');
        D10.addInt(0);
        D10.addInt(0);
        D10.addInt(0);
        D10.addInt(2);
        D10.addInt(3);
        D10.addInt(7);
        D10.addInt(8);
        D10.addInt(10);
        D10.addInt(10);
        D10.addInt(10);
        ret.add(D10);
        
        D10 = new Die(0,0,Color.BLACK,Color.WHITE,1,'+');
        D10.addInt(1);
        D10.addInt(1);
        D10.addInt(1);
        D10.addInt(1);
        D10.addInt(1);
        D10.addInt(6);
        D10.addInt(6);
        D10.addInt(6);
        D10.addInt(6);
        D10.addInt(6);
        ret.add(D10);
        
        //Subtraction
        D10 = new Die(0,0,Color.WHITE,Color.GREEN,3,'-');
        D10.addInt(0);
        D10.addInt(0);
        D10.addInt(0);
        D10.addInt(2);
        D10.addInt(3);
        D10.addInt(7);
        D10.addInt(8);
        D10.addInt(10);
        D10.addInt(10);
        D10.addInt(10);
        ret.add(D10);
        
        D10 = new Die(0,0,Color.WHITE,Color.GREEN,4,'-');
        D10.addInt(2);
        D10.addInt(2);
        D10.addInt(2);
        D10.addInt(2);
        D10.addInt(2);
        D10.addInt(2);
        D10.addInt(2);
        D10.addInt(4);
        D10.addInt(4);
        D10.addInt(4);
        ret.add(D10);
        
        //
        
        return ret;
    }
}

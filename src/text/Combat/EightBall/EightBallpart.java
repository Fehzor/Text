/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.EightBall;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Player;
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
public class EightBallpart extends ToolPart{
    
    public static ColorTuple b1 = new ColorTuple(Color.YELLOW,Color.BLACK,(char)('①'));
    public static ColorTuple b2 = new ColorTuple(Color.BLUE,Color.WHITE,(char)('①'+1));
    public static ColorTuple b3 = new ColorTuple(Color.ORANGE,Color.BLACK,(char)('①'+2));
    public static ColorTuple b4 = new ColorTuple(Color.MAGENTA,Color.BLACK,(char)('①'+3));
    public static ColorTuple b5 = new ColorTuple(Color.ORANGE,Color.WHITE,(char)('①'+4));
    public static ColorTuple b6 = new ColorTuple(Color.GREEN,Color.WHITE,(char)('①'+5));
    public static ColorTuple b7 = new ColorTuple(Color.RED,Color.WHITE,(char)('①'+6));
    public static ColorTuple b8 = new ColorTuple(Color.BLACK,Color.WHITE,(char)('①'+7));
    public static ColorTuple b9 = new ColorTuple(Color.YELLOW,Color.WHITE,(char)('①'+8));
    public static ColorTuple b10 = new ColorTuple(Color.BLUE,Color.WHITE,(char)('①'+9));
    public static ColorTuple b11 = new ColorTuple(Color.ORANGE,Color.WHITE,(char)('①'+10));
    public static ColorTuple b12 = new ColorTuple(Color.BLUE,Color.WHITE,(char)('①'+11));
    public static ColorTuple b13 = new ColorTuple(Color.ORANGE,Color.WHITE,(char)('①'+12));
    public static ColorTuple b14 = new ColorTuple(Color.GREEN,Color.WHITE,(char)('①'+13));
    public static ColorTuple b15 = new ColorTuple(Color.RED,Color.WHITE,(char)('①'+14));
    public static ColorTuple cue = new ColorTuple(Color.WHITE,Color.WHITE,(char)(' '));
    
    public int number = 8;
    
    public static ColorTuple getBall(int num){
        switch(num){
            case 1: return b1;
            case 2: return b2;
            case 3: return b3;
            case 4: return b4;
            case 5: return b5;
            case 6: return b6;
            case 7: return b7;
            case 8: return b8;
            case 9: return b9;
            case 10: return b10;
            case 11: return b11;
            case 12: return b12;
            case 13: return b13;
            case 14: return b14;
            case 15: return b15;
            default: return cue;
        }
    }
    
    public EightBallpart(int x, int y, int num){
        super(new TextImageBasic(getBall(num),true));
        
        this.type = TYPE_HANDLE;
        this.info = "Answer is hazy ask again later.";
        this.x = x;
        this.y = y;
        
        this.number = num;
        
        this.name = "Unfinished Pool Ball ("+num+")";
    }
    
    public EightBallpart(int x, int y){
        this(x,y,oRan.nextInt(17)+1);
    }
    
    public String toString(){
        return this.name;
    }
    
    public Tool compile(Tool T){
        EightBall EB = new EightBall(this.image,"Pool Ball("+number+")");
        T = EB;
        
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
        
        //CREATE AN ANIMATION
        ImageLoader.switchMap("HUMAN");
        TextImageComplex animate = new TextImageComplex(ImageLoader.loadImage("player_gunmode.txt"));
        ImageLoader.switchMap("CITYSCALE");
        TextImageComplex gunmodel = new TextImageComplex((TextImageBasic)this.image);
        animate.addKnob(5, -14, gunmodel);

        T.animate = animate;
        T.animationTime = 40;
        
        T.effects.add(myArea);
        
        
        super.compile(EB);
        
        return T;
    }
}

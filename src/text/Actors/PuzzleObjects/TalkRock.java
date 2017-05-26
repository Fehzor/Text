/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.PuzzleObjects;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Environment.Environment;
import text.Environment.Inanimate.Rock;
import static text.Environment.Inanimate.Rock.imageList;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.ImageBuilder;
import text.Utility.LootGenerator;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class TalkRock extends Rock{
    public String message = "Throw me away when I am empty.";
    
    
    public TalkRock(ColorTuple tint, Environment E, String s){
        super(tint,E);
        message = s;
    }
    
    public TalkRock(String name, int type, Environment E){
        super(name,type,E);
    }
    
    public TalkRock(ColorTuple tint, String name, int type, Environment E){
        super(tint,name,type,E);
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> aList = super.pollOptions();

        Option E = Option.display(this,"Talk to",message);
        aList.add(E);

        return aList;
    }
}

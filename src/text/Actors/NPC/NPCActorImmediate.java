/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Behaviour.Behaviour;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class NPCActorImmediate extends NPCActor{

    public String[] immediate = {"This displays asap!"};
    private boolean first = true;
    
    public NPCActorImmediate(String s, String s2){
        super(s,s2);
    }
    
    public boolean act(){
        super.act();
        if(this.first == true){
            try{
            Player.The.current.owner.display(immediate);
            first = false;
            } catch (Exception E){}
            
        }
        return true;
    }
}

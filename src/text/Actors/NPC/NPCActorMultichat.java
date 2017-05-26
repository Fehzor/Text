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
public class NPCActorMultichat extends NPCActor{

    int chatNum = 0;
    
    boolean flag = false;
    
    public NPCActorMultichat(String s, String s2){
        super(s,s2);
    }
    
    public boolean act(){
        super.act();
        if( flag){
            flag = false;
            chatNum++;

            if(chatNum >= text.length){
                chatNum = (text.length - 1);
            }
        }

        return true;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        ret.add(Option.display(this, "Talk", text[chatNum]));
        
        flag = true;
        
        return ret;
    }
}

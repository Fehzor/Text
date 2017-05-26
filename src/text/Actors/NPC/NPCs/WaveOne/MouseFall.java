/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC.NPCs.WaveOne;

import text.Actors.NPC.NPCActorPickupable;

/**
 *
 * @author FF6EB4
 */
public class MouseFall extends NPCActorPickupable{
    public MouseFall(){
        super("mouse","TREESCALE");
        this.name = "Babby Mouse";
        
        this.text = new String[]{"SQWREEEEEEEEEEEEEEEEEEEEEEEE!!"};
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Behaviour;

import text.Actors.Actor;
import text.Actors.Player;
import static text.Behaviour.Behaviour.oRan;
import text.Frame.TextDisplay;

/**
 *
 * @author FF6EB4
 */
public class BehaviourBuzz extends Behaviour{
    public void act(Actor actUpon){
        actUpon.x +=oRan.nextInt(2)-oRan.nextInt(2);
        actUpon.y +=oRan.nextInt(2)-oRan.nextInt(2);
    }
    
    public void outSideRoom(Actor actUpon){
        moveTowards(TextDisplay.SCREEN_SIZE_X/2,TextDisplay.SCREEN_SIZE_Y/2,actUpon);
    }
}

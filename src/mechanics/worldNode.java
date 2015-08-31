/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics;

import java.util.ArrayList;
import display.*;

/**
 *
 * @author Awesomesauce
 */
public class worldNode {
    public world heldWorld;
    public ArrayList<worldNode> connections = new ArrayList<worldNode>();
    
    public worldPanel mainPanel;
    
    /*
    The int, i represents the world to go to in the arrayList.
    The world in question knows this, and ports in those worlds are numbered, 
    such that the correspond  to plausible changes here.
    */
    public void change(int i){
        System.out.println(connections.get(i).heldWorld);
        mainPanel.loadWorld( connections.get(i).heldWorld );
    }
}

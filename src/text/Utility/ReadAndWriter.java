/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import text.Actors.Player;
import text.Inventory.Inventory;
import text.Inventory.Physical;
import text.WorldFrame.MainWorld;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class ReadAndWriter {
    
    public static FileOutputStream fos;
    public static ObjectOutputStream oos;
    
    public static void save(Physical toSave){
        try{
                File F = filechooser();
                if(F == null) return;
                String name = F.getName();
                
                fos = new FileOutputStream(F);
                oos = new ObjectOutputStream(fos);
                
                oos.writeObject(toSave);
                
            } catch (Exception E){
                System.err.println("Failed to save the thing.\n\n\n"+E);
            }
            System.out.println("SAVED "+toSave.getData());
    }
    
    //Gives the player whatever is loaded.
    public static void read(){
        if(Player.inv.full()){
            return;
        }
        
        File F = filechooser();
        if(F == null) return;
        String name = F.getName();
        
        try{
            FileInputStream fis = new FileInputStream(F);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Physical P = (Physical)(ois.readObject());
            
            Player.inv.addStuff(P);
                    

        } catch (Exception E){
            System.err.println("Failed to load the thing.\n\n\n"+E);
        }
        System.out.println("LOAD");
    }
    
    public static File filechooser(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Text", "txt");
        chooser.setFileFilter(filter);
        
        int returnVal = chooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
 
            return chooser.getSelectedFile();
        }
        return null;
    }
    
}

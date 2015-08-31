/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textEditors;
import models.textModelBasic;
import mechanics.*;
import utility.*;
import display.*;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import models.*;
import static textEditors.textEditor.marker;
/**
 *
 * @author Awesomesauce
 */
public class textEditorFriendly {
    public static letterTuple marker = new letterTuple();
    
    public static letterTuple marker1 = new letterTuple();
    public static letterTuple marker2 = new letterTuple();
    public static letterTuple marker3 = new letterTuple();
    
    public static textModel model;
    
    public static String name = "out.txt";
    public static int xSave, ySave;
    
    public static void main(String [] args){
        //Create the editor
        editorWorld EW = new editorWorld();
        textEditorFriendly.xSave = 15;
        textEditorFriendly.ySave = 15;
    }
    
    public static void setTransparent(){
		marker.set(letterTuple.TRANSPARENT);
		//USE THE COLOR THING TO GET COLORS HERE
    }
    
    public static void setLetterTuple(){
		textEditorFriendly.marker.setBackground(JColorChooser.showDialog(null,"Choose Background Color",marker.background()));
		textEditorFriendly.marker.setForeground(JColorChooser.showDialog(null,"Choose Letter Color",marker.foreground()));
		textEditorFriendly.setText();
		//USE THE COLOR THING TO GET COLORS HERE
    }
    
    public static void setBackground(){
        textEditorFriendly.marker.setBackground(JColorChooser.showDialog(null,"Choose Background Color",marker.background()));
    }
    
    public static void setLetterColor(){
        textEditorFriendly.marker.setBackground(JColorChooser.showDialog(null,"Choose Letter Color",marker.foreground()));
    }
    
    public static void setText(){
		try{
			String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); 
			textEditorFriendly.marker.setLetter(data.charAt(0));
		} catch (Exception e) {};
    }
    
    public static void setName(){
		try{
			String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); 
			textEditorFriendly.name = data;
		} catch (Exception e) {};
    }
    
    public static void save(){
                    
        try{
		ArrayList<ArrayList<letterTuple>> model = new ArrayList<ArrayList<letterTuple>>();
				
		for(int row=0;row<textEditorFriendly.ySave;++row){
			model.add(new ArrayList<letterTuple>());
			for(int col=0;col<textEditorFriendly.xSave;++col){
				model.get(row).add(textEditor.image.get(row).get(col));
			}
		}
			
		textModelBasic toSave = new textModelBasic(model);
				
		PrintWriter out = new PrintWriter(new File(textEditorFriendly.name));
				
		toSave.saveFile(out);
		} catch (Exception e){System.out.println("Error saving.");}
    }
        
        
}

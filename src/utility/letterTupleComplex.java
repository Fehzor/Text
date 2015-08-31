/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import utility.letterTuple;
import display.textPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

/**
 *
 * @author Awesomesauce
 */
public class letterTupleComplex extends letterTuple {
    private ArrayList<property> beforeList = new ArrayList<property>();
    private ArrayList<property> afterList = new ArrayList<property>();
    
    public letterTupleComplex(letterTuple base){
        super(base);
    }
    
    public void setProperty(property P){
        if(P.before())
            beforeList.add(P);
        else 
            afterList.add(P);
    }
    
    public void clear(){
        beforeList = new ArrayList<property>();
        afterList = new ArrayList<property>();
    }
    
    public void drawAt(int col, int row, textPanel tp, Graphics g){
                
                letterTuple me = this.simplify();
                
                //Apply all effects that occur before drawing.
                for(int i = 0; i < beforeList.size(); ++i)beforeList.get(i).apply(me, col, row, tp, g);
                
		Graphics2D g2d = (Graphics2D) g;	
	
		float startX = ((float)col * (float)tp.xInterval);		
		float startY = ((float)row * (float)tp.yInterval);	

		g2d.setColor(me.background());	
		
		//System.out.println(startX+" "+startY+" "+(int)xInterval+" "+(int)yInterval);
		g2d.fillRect((int)startX,(int)startY, (int)Math.ceil(tp.xInterval), (int)Math.ceil(tp.yInterval));
		
		g2d.setColor(me.foreground());
		g2d.drawString(me.letter()+"",startX+tp.xInterval*1/10,startY+tp.yInterval*4/5);
                
                //Apply all effects that occur after the letter is drawn.
                for(int i = 0; i < afterList.size(); ++i)afterList.get(i).apply(me, col, row, tp, g);
	}
    
    /**
     * return itself as a letterTuple only.
     */
    public letterTuple simplify(){
        return new letterTuple(this);
    }
}

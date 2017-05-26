/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Images;

import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author FF6EB4
 */
public class TextImageAnimated extends TextImage implements Serializable{

        private static final int DEFAULT_FRAME_SKIP = 2;
        private int FRAME_SKIP = DEFAULT_FRAME_SKIP;
        private int frame;
        
        public TextImage one;
        public TextImage two;
        
        private boolean go; //True = anmiate every time drawn, false = don't.
        private boolean which; //True = one; False = two;
        
        public TextImageAnimated(){
            super.type = 3;
            which = false;
            go = false;
            one = null;
            two = null;
            frame = -1;
        }
        
        public TextImageAnimated(TextImageComplex a, TextImageComplex b){
            super.type = 3;
            which = true;
            go = false;
            this.one = a;
            this.two = b;
            frame = 0;
        }
        
        public TextImageAnimated clone(){
            super.type = 3;
            TextImageComplex a = (TextImageComplex)one.clone();
            TextImageComplex b = (TextImageComplex)two.clone();
            TextImageAnimated ret = new TextImageAnimated(a,b);
            ret.flip(this.flipped());
            return ret;
        }
    
        public void drawBack(Graphics g, int x, int y, double xSize, double ySize){
            if(which){
                if(one!=null)
                    one.drawBack(g,x,y,xSize,ySize);
            } else {
                if(two!=null)
                    two.drawBack(g,x,y,xSize,ySize);
            }
            

        }
        
        public void drawFront(Graphics g, int x, int y, double xSize, double ySize){
            if(which){
                if(one!=null)
                    one.drawFront(g,x,y,xSize,ySize);
            } else {
                if(two!=null)
                    two.drawFront(g,x,y,xSize,ySize);
            }
            
            if(this.go){
                animate();
            }
        }
        
        public void flip(){
            super.flip();
            one.flip();
            two.flip();
        }
    
        public void flip(boolean flip){
            super.flip(flip);
            one.flip(flip);
            two.flip(flip);
        }
        
        public void go(){
            this.go = true;
        }
        
        public void stop(){
            this.go = false;
        }
        
        /**
         * Resets the frame to the first.
         */
        public void resetFrame(){
            which = true;
        }
        
        /**
         * Sets the frame to the second image
         */
        public void setSecond(){
            which = false;
        }
        
        private void animate(){
            
            if(frame == FRAME_SKIP){
                frame = 0;
                which = !which;
            } else {
                frame++;
            }
        }
        
        /**
         * Set the number of frames to skip in between animation.
        */
        public void setFrameSkip(int frames){
            this.FRAME_SKIP = frames;
        }
        
        public TextImageBasic getBasic(){
            if(which) return one.getBasic();
            return two.getBasic();
        }
}

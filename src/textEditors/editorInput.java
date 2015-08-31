/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textEditors;

import java.awt.event.KeyEvent;
import mechanics.actors.player;
import mechanics.input;
import mechanics.world;
import java.awt.event.MouseListener;
import java.awt.event.*;
import java.awt.Point;
import display.displayFrame;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
/**
 *
 * @author Awesomesauce
 */
public class editorInput implements MouseListener {
        
    JPopupMenu menu = new JPopupMenu("Popup");
    
        public editorInput(){
            buildMenu();
        }
        

        
        public void mousePressed(MouseEvent e){
            
            
            int button = e.getButton();
            
            if(button == MouseEvent.BUTTON1){
                this.mark(e);
            }
            
            if(button == MouseEvent.BUTTON3){
                menu.show(e.getComponent(),e.getX(),e.getY());
            }
        }
        
        /**
         * Marks the position clicked.
         * @param e 
         */
        public void mark(MouseEvent e){
              double rowPressed = (double)e.getY() / editorWorld.WP.yInterval;
              double colPressed = (double)e.getX() / editorWorld.WP.xInterval;
              editorWorld.W.background.get((int)colPressed).get((int)rowPressed).set(textEditorFriendly.marker);
        }
        
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
        public void mouseClicked(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        
        private void buildMenu(){
            //Define menu items here.
            
                JMenuItem testItem = new JMenuItem("Test Option");
                    testItem.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("Test action chosen!");
                        }
                    });
                menu.add(testItem);
                
                JMenuItem setMarker = new JMenuItem("Set Marker");
                    setMarker.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.setLetterTuple();
                        }
                    });
                menu.add(setMarker);
                
                JMenuItem setBack = new JMenuItem("Set BackGround");
                    setBack.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.setBackground();
                        }
                    });
                menu.add(setBack);
                
                JMenuItem setLetter = new JMenuItem("Set Letter Color");
                    setLetter.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.setLetterColor();
                        }
                    });
                menu.add(setLetter);
                
                JMenuItem setLetterType = new JMenuItem("Set Letter");
                    setLetterType.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.setText();
                        }
                    });
                menu.add(setLetterType);
                
                JMenuItem transparent = new JMenuItem("Set TRANSPARENT");
                    transparent.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.setTransparent();
                        }
                    });
                menu.add(transparent);
                
                JMenuItem preset1 = new JMenuItem("Set preset one");
                    preset1.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.marker.set(textEditorFriendly.marker1);
                        }
                    });
                menu.add(preset1);
                
                JMenuItem preset2 = new JMenuItem("Set preset two");
                    preset2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.marker.set(textEditorFriendly.marker2);
                        }
                    });
                menu.add(preset2);
                
                JMenuItem preset3 = new JMenuItem("Set preset three");
                    preset3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.marker.set(textEditorFriendly.marker3);
                        }
                    });
                menu.add(preset3);
                
                
                
                JMenuItem spreset1 = new JMenuItem("Save to preset one");
                    spreset1.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.marker1.set(textEditorFriendly.marker);
                        }
                    });
                menu.add(spreset1);
                
                JMenuItem spreset2 = new JMenuItem("Save to preset two");
                    spreset2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.marker2.set(textEditorFriendly.marker);
                        }
                    });
                menu.add(spreset2);
                
                JMenuItem spreset3 = new JMenuItem("Save to preset three");
                    spreset3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.marker3.set(textEditorFriendly.marker);
                        }
                    });
                menu.add(spreset3);
                
                JMenuItem save = new JMenuItem("Save Image");
                    save.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            textEditorFriendly.marker3.set(textEditorFriendly.marker);
                        }
                    });
                menu.add(save);
                
                
                
        }
}

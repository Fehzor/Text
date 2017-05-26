/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.EightBall;

import static java.awt.event.KeyEvent.VK_0;
import static java.awt.event.KeyEvent.VK_1;
import static java.awt.event.KeyEvent.VK_2;
import static java.awt.event.KeyEvent.VK_3;
import static java.awt.event.KeyEvent.VK_4;
import static java.awt.event.KeyEvent.VK_5;
import static java.awt.event.KeyEvent.VK_6;
import static java.awt.event.KeyEvent.VK_7;
import static java.awt.event.KeyEvent.VK_8;
import static java.awt.event.KeyEvent.VK_9;
import static java.awt.event.KeyEvent.VK_MINUS;
import java.util.ArrayList;
import java.util.Scanner;
import text.Combat.CombatSystem;
import text.Combat.CombatText;
import text.Frame.TextListener;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class EightBallSystem extends CombatSystem{
    public ArrayList<Integer> guesses;
    String typed = "";
    boolean start = true;
    int answer;
    int waitSteps = 0;
    
    public int round = 0;
    
    public EightBall EB;
    
    
    public EightBallSystem(EightBall EB){
        this.EB = EB;
    }
    
    public boolean go(){
        if(start){
            guess(EB.players);
            start = false;
            answer = oRan.nextInt(100);
        }
        
        checkInput();
        
        if(typed.length() > 1){
            parseInput();
            done = true;
        }
        
        return true;
    }
    
    public void guess(int i){
        guesses = new ArrayList<>();
        for(int j = 0; j < i; ++j){
            guesses.add(oRan.nextInt(100));
        }
    }
    
    public String[] display(){
        String[] ret = new String[7];
        if(!done){
            ret[0] = "Pick a number 0-"+100+"! Whoever is closest to the random selection wins!";
            ret[1] = "";
            ret[2] = "Others have chose: "+guesses;
            ret[3] = "";
            ret[4] = "";
            ret[5] = "Type the answer to continue- 01,02,03,etc. for numbers 1-9.";
            ret[6] = "";
        } else {
            ret[0] = "";
            ret[1] = "";
            ret[2] = "The number was " + answer +".";
            ret[3] = "You "+ (hits>0 ? "won!" : "lost..");
            ret[4] = "";
            ret[5] = "";
            ret[6] = "";
        }
        return ret;
    }
    
    private void checkInput(){
        checkKey(VK_0,'0');
        checkKey(VK_1,'1');
        checkKey(VK_2,'2');
        checkKey(VK_3,'3');
        checkKey(VK_4,'4');
        checkKey(VK_5,'5');
        checkKey(VK_6,'6');
        checkKey(VK_7,'7');
        checkKey(VK_8,'8');
        checkKey(VK_9,'9');
    }
    
    private void parseInput(){
        
        Scanner oScan = new Scanner(typed);
        int i = oScan.nextInt();
        typed = "";
        
        if(won(i)){
            this.hits = (guesses.size() + 5) * (guesses.size()+3);
            this.misses = 0;
        } else {
            this.misses = guesses.size();
            this.hits = 0;
        }
    }
    
    private boolean won(int i){
        
        int minDist = 100;
        for(int g : guesses){
            int distance = (Math.abs(g - answer));
            if(minDist > distance){
                minDist = distance;
            }
        }
        
        if(Math.abs(i - answer) < minDist) return true;
        return false;
    }
        
    private void checkKey(int key, char c){
        if(TextListener.firstPress(key)){
            this.typed += c;
            CombatText.addPhrase(4,c+"");
        }
    }
    
    public boolean done(){
        if(done){
            waitSteps++;
        }
        if(done && waitSteps > 60){
            waitSteps = 0;
            done = false;
            this.start = true;
            this.typed = "";
            //CombatText.addPhrase(4,hits+"DMG");
            
            
            return true;
        } else {
            return false;
        }
    }
}

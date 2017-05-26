/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.RPS;

import text.Combat.EightBall.*;
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
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_S;
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
public class RPSSystem extends CombatSystem{
    String typed = "";
    boolean start = true;
    public int answer;
    int waitSteps = 0;
    public int lastAnswer = 1;
    
    public RPSObject RPS;
    
    
    public RPSSystem(RPSObject RPS){
        this.RPS = RPS;
    }
    
    public boolean go(){
        if(start){
            start = false;
            answer = RPS.getRPS();
        }
        
        checkInput();
        
        if(typed.length() > 0){
            parseInput();
            done = true;
        }
        
        return true;
    }
    

    
    public String[] display(){
        String[] ret = new String[7];
        if(!done){
            ret[0] = "";
            ret[1] = "Choose- Rock (R) / Paper (P) / Scissors (S)";
            ret[2] = "";
            ret[3] = "";
            ret[4] = "";
            ret[5] = "Type your choice to continue- R, P or S";
            ret[6] = "";
        } else {
            ret[0] = "";
            ret[1] = "";
            
            
            String ans = "scissors";
            if(answer == 0) ans = "rock";
            if(answer == 1) ans = "paper";
            
            ret[2] = "The opposing result was " + ans +".";
            ret[3] = "";
            ret[4] = "You "+ (hits>0 ? "won!" : "lost..");
            ret[5] = "";
            ret[6] = "";
        }
        return ret;
    }
    
    private void checkInput(){
        checkKey(VK_R,'0');
        checkKey(VK_P,'1');
        checkKey(VK_S,'2');
    }
    
    private void parseInput(){
        
        Scanner oScan = new Scanner(typed);
        lastAnswer = oScan.nextInt();
        typed = "";
        
        if(won(lastAnswer)> 0){
            this.hits = RPS.value;
            this.misses = -1;
        } else if(won(lastAnswer)<0){
            this.misses = 3;
            this.hits = 0;
        } else {
            this.misses = 1;
            this.hits = 0;
        }
    }
    
    private int won(int i){
        if(i == answer) return 0;
        if(i == 0 && answer==1) return -1;
        if(i == 1 && answer==2) return -1;
        if(i == 2 && answer==0) return -1;
        return 1;
    }
        
    private void checkKey(int key, char c){
        if(TextListener.firstPress(key)){
            this.typed += c;
            if(c == '0'){
                CombatText.addPhrase(1,"rock");
            }
            if(c == '1'){
                CombatText.addPhrase(1,"paper");
            }
            if(c == '2'){
                CombatText.addPhrase(1,"scissors");
            }
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

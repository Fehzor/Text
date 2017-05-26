/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Dice;

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
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import text.Combat.CombatSystem;
import text.Combat.CombatText;
import text.Frame.TextListener;

/**
 *
 * @author FF6EB4
 */
public class DiceRunner extends CombatSystem{
    
    int answer = 42;
    String question = "11+31";
    String typed = "";

    DiceSet DS;
    
    long startTime;

    
    private boolean start = true;

    public DiceRunner(DiceSet DS){
        this.DS = DS;
    }

    public boolean go(){
        if(start){
            startTime = System.currentTimeMillis();
            start = false;
            roll();
        }
        long time = System.currentTimeMillis() - startTime;
        misses = (int)(time / DS.missDivisor);
        
        checkInput();
        this.done = checkAnswer();
        
        return true;
    }
    
    public boolean checkAnswer(){
        String compare = "" + answer;
        int j = 0; //Index in compare
        
        for(int i = 0; i < typed.length(); ++i){
            if(j == compare.length()){
                return true;
            }
            if(typed.charAt(i) == compare.charAt(j)){
                j++;
            } else {
                j = 0;
                if(typed.charAt(i) == compare.charAt(j)){
                    j++;
                }
            }
        }
        
        if(j == compare.length()){
            return true;
        }
        return false;
    }
    

    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "You've rolled the following equation...";
        ret[1] = "";
        ret[2] = "" + question;
        ret[3] = "";
        ret[4] = "Hits/Dodges: " + (DS.startHits - misses);
        ret[5] = "Type the answer to attack or defend!";
        ret[6] = "(Don't worry about messing up! For division, no decimals! Input 23 for infinity..or for 23)";
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
        checkKey(VK_MINUS,'-');
    }
    
    private void checkKey(int key, char c){
        if(TextListener.firstPress(key)){
            this.typed += c;
            CombatText.addPhrase(3,c+"");
        }
    }
        
    private void roll(){
        this.answer = DS.dice.get(0).roll();
        String s = ""+answer;
        boolean end = false;
        for(Die D : DS.dice){
            if(end){
                s = s + D.get();
            } else {
                end = true;
            }
        }
        this.question = s;
        this.answer = parse(question);
        
    }
    
    //String of algebra in, int out.
    public int parse(String question){
        //Seperate the string into lists.
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Character> symbols = new ArrayList<>();
        
        int index = 0;
        int nextNum = 0;
        while(index != question.length()){
            if(question.charAt(index) >= '0' && question.charAt(index) <= '9'){
                nextNum = nextNum*10;
                nextNum += question.charAt(index) - '0';
            } else {
                numbers.add(nextNum);
                nextNum = 0;
                
                char symbol = question.charAt(index);
                symbols.add(symbol);
            }
            index++;
        }
        numbers.add(nextNum);
        
        //Deal with there only being the one number.
        if(numbers.size() == 1){
            return numbers.get(0);
        }
        
        //Multiplication/Division
        for(index = 0; index < symbols.size(); ++index){
            if(symbols.get(index) == '*'){
                numbers.set(index,numbers.get(index) * numbers.get(index+1));
                numbers.remove(index+1);
                symbols.remove(index);
                index = index - 1;
            } else if(symbols.get(index) == '/'){
                try{
                    numbers.set(index,numbers.get(index) / numbers.get(index+1));
                    numbers.remove(index+1);
                    symbols.remove(index);
                    index = index - 1;
                } catch (Exception E){
                    return 23;
                }
            }
        }
        
        //Deal with there only being the one number.
        if(numbers.size() == 1){
            return numbers.get(0);
        }
        
        //Addition/Subtraction
        for(index = 0; index < symbols.size(); ++index){
            if(symbols.get(index) == '+'){
                numbers.set(index,numbers.get(index) + numbers.get(index+1));
                numbers.remove(index+1);
                symbols.remove(index);
                index = index - 1;
            } else if(symbols.get(index) == '-'){
                numbers.set(index,numbers.get(index) - numbers.get(index+1));
                numbers.remove(index+1);
                symbols.remove(index);
                index = index - 1;
            }
        }
        
        //Deal with there only being the one number.
        if(numbers.size() == 1){
            return numbers.get(0);
        }
        
        System.err.println("SOMETHING WENT WRONG- answer set to 0r.");
        return 0;
    }
    
    
        
    public boolean done(){
        if(done){
            done = false;
            hits = (DS.startHits - misses);
            if(hits < 0){
                misses = hits * -1;
                hits = 0;
            } else if (hits > 0){
                misses = 0;
            }
            //CombatText.addPhrase(4,hits+"DMG");
            this.typed = "";
            this.answer = 0;
            this.start = true;
            return true;
        } else {
            return false;
        }
    }
}

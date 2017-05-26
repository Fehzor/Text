
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Worlds;

import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.Instances.HighLighter;
import text.Actors.Instances.LetterScreen;
import text.Actors.Player;
import text.Utility.Diction.LiteralDictionary;
import text.Environment.Environment;
import text.WorldFrame.Room;

/**
 *
 * @author FF6EB4
 */
public class WordSearchWorld extends GridWorld{
    
    public ArrayList<String> taken = new ArrayList<>();
    
    ArrayList<LetterScreen> letters;
    HashMap<Room,LetterScreen> roomLetters;
    
    public String word = "";
    private boolean highlight;
    
    public WordSearchWorld(int size, Environment E) {
        super(size, E);
        highlight = false;
        letters = new ArrayList<>();
        roomLetters = new HashMap<>();
    }
    
    public int addRoom(Room R){
        int ret = super.addRoom(R);
        
        LetterScreen add = new LetterScreen(LiteralDictionary.getRandomLetter());
        letters.add(add);
        roomLetters.put(R,add);
        R.addActor(add);
        
        if(ret == 0){
            HighLighter HL = new HighLighter(this);
            R.addActor(HL);
        }
        
        return ret;
    }
    
    public void switchRoom(int i){
        super.switchRoom(i);
        
        if(highlight){
            this.word+= ""+roomLetters.get(currentRoom).c;
        } else {
            //System.out.println("FALSE");
            this.word = "";
        }
    }
    
    public void displayMap(){
        String[] ret = new String[this.size+3];
        
        int pRoomNum= Player.The.current.id;
        
        ret[0] = ""+name;
        
        for(int i = 0; i < this.size; ++i){
            for(int j = 0; j < this.size; ++j){
                if(j == 0){
                    ret[i+1] = "";
                }
                
                int nextRoom = i*size + j;
                
                if(nextRoom == pRoomNum){
                  ret[i+1]+= "-P-";
                } else if(checkDoor(nextRoom)){
                    ret[i+1] += "-W-";
                } else {
                    ret[i+1] += " "+roomLetters.get(getRoom(nextRoom)).c+" ";
                }
            }
        }
        ret[this.size+1] = "";
        ret[this.size+2] = word;
        this.display(ret);
    }
    
    
    public void toggleHighlight(boolean on){
        if(on){
            this.highlight = on;
            this.word+= ""+roomLetters.get(currentRoom).c;
        } else {
            this.highlight = on;
        }
    }
}

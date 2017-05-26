/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Cards;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_F;
import static java.awt.event.KeyEvent.VK_S;
import java.util.ArrayList;
import text.Actors.Messages.ControlledQuickDisplay;
import text.Actors.Player;
import text.Combat.CombatSystem;
import text.Combat.CombatText;
import text.Frame.TextListener;
import text.Images.TextImageBasic;
import text.Utility.ImageLoader;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class CardRunner extends CombatSystem{
    
    Deck D;
    public boolean first = true;
    public boolean selected = false;
    
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> discard = new ArrayList<>();
    private int handSize = 5;
    
    int pos = 0;
    
    public ArrayList<ControlledQuickDisplay> PDQList = new ArrayList<>();
    public ControlledQuickDisplay PDQArrow;
    TextImageBasic TIBArrow = loadArrow();
    
    String placeName = "None";
    int population = 0;
    int stuff = 0;
    int extraHits = 0;
    
    public Card lastCard = null;
    
    String displayMessage = "Choose any card!";
    
    public CardRunner(Deck D){
        this.D = D;
    }
    
    public boolean go(){
        if(first){
            first = false;
            draw();
            quickDisplay();
            displayArrow();
        }
        
        select();
        
        
        if(selected){
            //System.out.println(hand.get(pos));
            
            apply(hand.get(pos));
            
            discard.add(hand.remove(pos));
            done = true;
            removeDisplay();
            removeArrow();
        }
        
        return true;
    }
    
    public void apply(Card C){
        //FIRST EFFECT
        if(C.cardType == C.PLACE_CARD){
            this.placeName = C.cardName;
        }else if(C.cardType == C.PERSON_CARD){
            if(this.placeName.equals("None")){
                this.hits = 7;
                return;
            } else {
                this.stuff+= C.subValue;
                this.population+= C.value;
            }
        } else if(C.cardType == C.THING_CARD){
            if(this.placeName.equals("None")){
                this.hits = 11;
                return;
            } else {
                this.stuff+= C.value;
                this.population+= C.subValue;
            }
        } else if(C.cardType == C.ACTION_CARD){
            C.action(this);
        }
        
        
        //CHAIN EFFECTS
        if(lastCard == null){
        } else if (
                lastCard.cardName.equals("Mouse") && C.cardName.equals("Cat") || 
                lastCard.equals("Cat") && C.cardName.equals("Mouse")){
            this.displayMessage = "Cat + mouse = Less mice, more damage!";
            extraHits += 19;
            population -= 1;
        } else if (
                lastCard.cardName.equals("Gader") && C.cardName.equals("Cat") || 
                lastCard.equals("Cat") && C.cardName.equals("Gader")){
            this.displayMessage = "Gader+Cat!?!?!?? NOOOOOOO";
            extraHits += 43;
            population -= 2;
        } else if (
                lastCard.cardName.equals("Smallmonkey") && C.cardName.equals("Monkey") || 
                lastCard.equals("Monkey") && C.cardName.equals("Smallmonkey")){
            this.displayMessage = "They're going bananas!";
            extraHits += -5;
            population += 3;
        }
        lastCard = C;
        
        //DAMAGE CALCULATION
        int preHits = population + stuff + (population * stuff);
        
        if(preHits < 0) {
            misses = 2;
            hits = 0;
        } else {
            this.hits = 4 + (int) Math.sqrt(preHits);;
        }
        hits+= extraHits;
        extraHits = 0;
        
    }
    
    public void displayArrow(){
        if(PDQArrow != null){
            //PDQArrow.dead = true;
            PDQArrow.x = pos*19+4;
            PDQArrow.y = 15+25;
        } else {   
            PDQArrow = new ControlledQuickDisplay(
                    pos*19 + 4,
                    15+25,
                    Player.The.current,
                    TIBArrow);
        }
            
    }
    
    public void removeArrow(){
        if(PDQArrow != null){
            PDQArrow.dead = true;
        }
        PDQArrow = null;
    }
    
    public void quickDisplay(){
        int index = 0;
        for(Card C : hand){
            
            ControlledQuickDisplay PDQ;
            
            PDQ = new ControlledQuickDisplay(
                    index*19,
                    15,
                    Player.The.current,
                    C.cardImage);
            
            PDQList.add(PDQ);
            index++;
        }
    }
    
    public void removeDisplay(){
        for(ControlledQuickDisplay PDQ : PDQList){
            PDQ.dead = true;
        }
        PDQList = new ArrayList<>();
    }
    
    public void select(){
        if(TextListener.firstPress(VK_S)){
            pos -= 1;
            if(pos < 0) pos = 0;
            displayArrow();
        }
        
        if(TextListener.firstPress(VK_F)){
            pos += 1;
            if(pos >= hand.size()) pos = hand.size()-1;
            displayArrow();
        }
        
        if(TextListener.firstPress(VK_A)){
            selected = true;
        }
    }
    
        public boolean done(){
        if(done){
            done = false;
            first = true;
            selected = false;
            //CombatText.addPhrase(4,hits+"DMG");
            return true;
        } else {
            return false;
        }
    }
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = ""+displayMessage;
        ret[1] = "";
        ret[2] = "Hand: " + hand;;
        ret[3] = "";
        ret[4] = "Place:" + placeName;
        ret[5] = "Population: " + population;
        ret[6] = "Stuff: " + stuff;
        return ret;
    }
    
    //Draws cards until handsize is reached. If you run out of cards it's smaller.
    public void draw(){
        if(deck.size()>0){
            while(hand.size() < handSize){
                hand.add(deck.remove(0));
            }
        } else {
            for(int i = 0; i<oRan.nextInt(3) + 2; ++i){
                D.shuffle();
            }
            this.shuffle();
            if(deck.size() > handSize){
                while(hand.size() < handSize){
                    hand.add(deck.remove(0));
                }
            } else {
                while(deck.size() > 0){
                    hand.add(deck.remove(0));
                }
            }
        }
        
    }
    
    public void shuffle(){
        hand = new ArrayList<>();
        discard = new ArrayList<>();
        deck = new ArrayList<>();
        //D.shuffle(); //This way when listed it's different every time :D
        deck.addAll(D.cardList);
        
    }
    
   public TextImageBasic loadArrow(){
       ImageLoader.switchMap("CITYSCALE");
       return ImageLoader.loadImage("arrow_up.txt");
   }
   
   public void reset(){
       this.population = 0;
       this.placeName = "None";
       this.stuff = 0;
       this.lastCard = null;
       this.displayMessage = "Choose any card!";
   }
}

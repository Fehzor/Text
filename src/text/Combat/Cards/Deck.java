/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Cards;

import java.util.ArrayList;
import text.Tools.Tool;
import text.Utility.ImageLoader;
import static text.Utility.LootGenerator.oRan;

/**
 *
 * @author FF6EB4
 */
public class Deck extends Tool{
    
    public ArrayList<Card> cardList = new ArrayList<>();

    public Deck() {
        super(ImageLoader.loadImage("card_back.txt"),"Deck");
        this.slot = INSTRUMENT;
    }
    
    public void shuffle(){
        ArrayList<Card> newList = new ArrayList<>();
        
        int index = 0;
        for(Card C : cardList){
            newList.add(index,C);
            if(oRan.nextInt(100) > 23){
                index++;
            }
        }
        
        cardList = newList;
    }
}

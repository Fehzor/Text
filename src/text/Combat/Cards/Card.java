/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Cards;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Drawable;
import text.Actors.Messages.Option;
import text.Actors.Messages.QuickDisplay;
import text.Actors.Player;
import text.Images.TextImage;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Tools.Tool;
import text.Tools.ToolPart;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class Card extends ToolPart{
    public static TextImageBasic back = loadBack();
    
    public int cardType;
    public TextImageComplex cardImage;
    public String cardName;
    
    //Card Types
    public static final int PLACE_CARD = 1;
    public static final int PERSON_CARD = 2;
    public static final int THING_CARD = 3;
    public static final int ACTION_CARD = 4;
    
    public int value = 2;
    public int subValue = 0;
    
    public Card(int cardType, String cardName){
        super(back);
        this.cardType = cardType;
        this.cardName = cardName;
        
        this.type = TYPE_ACCESSORY;
        this.name = "Card";
        this.cardName = cardName;
    }
    
    public Card(int cardType, TextImageBasic image, String cardName){
        this(cardType, cardName);
        
        ImageLoader.switchMap("CITYSCALE");
        this.cardImage = new TextImageComplex(ImageLoader.loadImage("card_blank.txt"));
        this.cardImage.addKnob(1, 1, ImageBuilder.resizeImage(image,15,21));
        
        setInfo();
        //this.image = cardImage;
    }
    
    public Tool compile(Tool T){
        if(T == null){
            T = new Deck();
            super.compile(T);
        }
        
        try{
            Deck D = (Deck)T;
            
            D.cardList.add(this);
        } catch (Exception E){}
        
        
        
        return T;
    }
    
    public String toString(){
        return cardName + " " + this.name;
    }
    
    public static TextImageBasic loadBack(){
        TextImageBasic ret;
        ImageLoader.switchMap("CITYSCALE");
        ret = ImageLoader.loadImage("card_back.txt");
        return ret;
    }
    
    //Over ride this for action cards
    public void action(CardRunner CR){
        
    }
    
    public void setInfo(){
        this.info = "Combine to form a deck!";
    }
    
    public String getCardEffect(){
        if(this.cardType == PLACE_CARD){
            return "Place card: set the place";
        }
        if(this.cardType == PERSON_CARD){
            return "Entity card: +"+value+" population / +"+subValue+" things";
        }
        if(this.cardType == THING_CARD){
            return "Thing card: +"+value+" things / +"+subValue+" population";
        }
        
        return "It's a mystery!";
    }
    
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = super.pollOptions();
        
        ret.add(new Option(this){
            public boolean act(){
                Drawable D = new Drawable(((Card)owner).cardImage);
                QuickDisplay QD = new QuickDisplay(Player.The.current,D);
                return false;
            }
            public String toString(){
                return "View";
            }
        });
        
        ret.add(Option.display(this, "Card Effect",getCardEffect()));
        
        return ret;
    }
    
    //
    // PRESET CARDS BELOW HERE
    //
    public static Card getCardWildFire(){
        Card WF = new Card(ACTION_CARD, ImageLoader.loadImage("flame.txt"), "WildFire"){
            
            
            public void action(CardRunner CR){
                CR.extraHits += CR.population * 10;
                CR.population = 0;
                CR.stuff = CR.stuff / 2 + 10;
                if(CR.placeName.equals("Wilds")){
                    CR.extraHits += 50;
                }
                CR.placeName = "None";
                CR.displayMessage = "All gone.";
            }
            
            public String getCardEffect(){
                return "Decimate wildlife!";
            }
            
        };
        
        return WF;
    }

        public static Card getCardCycle(){
        Card WF = new Card(ACTION_CARD, ImageLoader.loadImage("cycle.txt"), "Cycle"){
            
            
            public void action(CardRunner CR){
                CR.shuffle();
                CR.draw();
                
            }
            
            public String getCardEffect(){
                return "Cycle your hand!";
            }
            
        };
        
        return WF;
    }
        
        
  
}

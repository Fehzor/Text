/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Cards;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Instances.CityPart;
import text.Actors.Player;
import text.Actors.PuzzleObjects.TalkRock;
import static text.Combat.Cards.Card.THING_CARD;
import static text.Combat.Cards.Card.PERSON_CARD;
import static text.Combat.Cards.Card.PLACE_CARD;
import static text.Combat.Cards.Card.ACTION_CARD;
import text.Combat.Enemy.Enemies.Enemy;
import text.Environment.Animals.Animal;
import text.Environment.Inanimate.Rock;
import text.Environment.Plants.SmallPlant;
import text.Environment.Plants.Tree;
import text.Images.TextImageBasic;
import text.Inventory.Physical;
import text.Tools.Tool;
import text.Tools.ToolEffect;
import text.Tools.ToolPart;
import text.Utility.ImageLoader;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class CardPrinter extends ToolPart{
    public CardPrinter(){
        super();
        this.image = ImageLoader.loadImage("card_reader.txt");
        
        this.type = TYPE_END;
        this.dead = false;
    }
    
    public Tool compile(Tool T){
        
        ToolEffect createCard = new ToolEffect(){
            public void applyEffect(ArrayList<Actor> list, double power){
                //System.out.println("HIT: "+list);
                for(Actor A : list){
                    
                    if(A instanceof Tree){
                        createCard(A,THING_CARD,1,1);
                    }
                    
                    if(A instanceof Animal){
                        createCard(A,PERSON_CARD);
                    }
                    
                    if(A instanceof TalkRock){
                        createCard(A,PERSON_CARD,1,1);
                    }
                    
                    if(A instanceof Enemy){
                        createCard(A,PERSON_CARD,4,-1);
                    }
                    
                    if(A instanceof SmallPlant){
                        createCard(A,THING_CARD);
                    }
                    
                    if(A instanceof CityPart){
                        CityPart CP = (CityPart)A;
                        if(CP.listNumber == 1){
                            createCard(A,THING_CARD,4,-1);
                        }
                        if(CP.listNumber == 2){
                            createCard(A,PERSON_CARD,1,1);
                        }
                        if(CP.listNumber == 3){
                            createCard(A,PLACE_CARD);
                        }
                    }
                    
                    if(A instanceof Rock){
                        createCard(A,THING_CARD);
                    }
                }
                
                if(list.size() == 0){
                    createCard(PLACE_CARD,Player.The.current.owner.name);
                }
            }
        };
        
        T.effects.add(createCard);
        T.name += " Printer";
        
        return T;
    }
    
    public static void createCard(Actor A, int type, int mainStat, int subStat){
        Card C = new Card(type,A.image.getBasic(),A.name);
        C.value = mainStat;
        C.subValue = subStat;
        
        if(Player.inv.full()){
            return;
        }
        Player.inv.addStuff(new Physical(C));
        
        A.dead = true;
    }
    
    public static void createCard(Actor A, int type){
        Card C = new Card(type,A.image.getBasic(),A.name);
        if(Player.inv.full()){
            return;
        }
        Player.inv.addStuff(new Physical(C));
        
        A.dead = true;
    }
    
    public static void createCard(int type, String name){
        Card C = new Card(type,null,name);
        if(Player.inv.full()){
            return;
        }
        Player.inv.addStuff(new Physical(C));
    }
    
    public String toString(){
        return "Card Printer";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Frame;
import text.WorldFrame.GameCoordinator;
import text.WorldFrame.Templates.WorldBuilder;
import text.WorldFrame.Templates.WildTemplate;
import text.WorldFrame.World;
import text.WorldFrame.Worlds.LostWorld;
import text.WorldFrame.Worlds.GridWorld;
import text.WorldFrame.MainWorld;
import text.Actors.Interactable;
import text.Actors.Instances.Teleporter;
import text.Actors.Instances.Door;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import text.WorldFrame.*;
import text.Actors.*;
import text.Images.*;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import text.Actors.Instances.Chests.Chest;
import text.Actors.Instances.Chests.LockedChest;
import text.Actors.Instances.Chests.Password;
import text.Actors.Instances.Floppy;
import text.Actors.Instances.Food;
import text.Actors.Instances.VendingMachine;
import text.Actors.NPC.NPCs.Caves.MolePeople.MoleManA;
import text.Actors.NPC.NPCs.Caves.Moles.MoleA;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalA;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalC;
import text.Actors.PuzzleObjects.Bone;
import text.Actors.PuzzleObjects.SciencePowder;
import text.Combat.Cards.Card;
import static text.Combat.Cards.Card.PERSON_CARD;
import static text.Combat.Cards.Card.PLACE_CARD;
import text.Combat.Cards.CardPrinter;
import text.Combat.Dice.Die;
import text.Combat.EightBall.EightBallpart;
import text.Combat.Enemy.Enemies.Caves.Spider_Boss;
import text.Combat.Enemy.Enemies.Caves.Spider_Random;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.GentRock;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Punkin_a;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Punkin_b;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.RockerRock;
import text.Combat.Enemy.Enemies.Wilds.Tiger_a;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_a;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_b;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_c;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_d;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_dice;

import text.Inventory.Inventory;
import text.Robits.Robit;
import text.Tools.Preset.ConverterTool;
import text.Tools.Preset.DirectionalTool;
import text.Combat.Hero.Instrument;
import text.Combat.Hero.Violin;
import text.Inventory.Physical;
import text.Combat.Hero.WhistleHead;
import text.Combat.RPS.RPSPart;
import text.Environment.Animals.BeeHive;
import text.Tools.Preset.MapTool;
import text.Tools.Preset.Shovel;
import text.Tools.ToolParts.Bumper;
import text.Tools.ToolParts.Converter;
import text.Tools.ToolParts.Handle;
import text.Tools.ToolParts.Revolver;
import text.Tools.ToolParts.SleepyPills;
import text.Tools.ToolParts.SmokePuff;
import text.Utility.*;
import text.WorldFrame.Templates.CityBuilder;
import text.WorldFrame.Templates.CityTemplate;
import text.WorldFrame.Templates.GameTemplate;
import text.WorldFrame.Templates.IndoorTemplate;
import text.WorldFrame.Templates.WildBuilder;
import text.WorldFrame.Templates.WorldTemplate;

/**
 *
 * @author FF6EB4
 */
public class Text {
    Random oRan = new Random();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Progress.loadAllStories();
        runMainTest();
    }
    
    public static void runMainTest(){
        World W = WorldBuilder.generateMain();
        MainWorld MW = (MainWorld)W;
        
        GameCoordinator.The.update(null,null);
        
        //Robit R = new Robit();
        //MW.getRoom(0).addActor(R);
        
        /*//
        Food f = new Food();
        f.x = 40;
        f.y = 40;
        Food f2 = new Food();
        f2.x = 60;
        f2.y = 40;
        Food f3 = new Food();
        f3.x = 80;
        f3.y = 40;
        
        MW.getRoom(0).addActor(f);
        MW.getRoom(0).addActor(f2);
        MW.getRoom(0).addActor(f3);
        //*/
        
        /*
        Door A = new Door("Door");
        Door B = new Door(A,"Door");
        MW.getRoom(0).addActor(A);
        MW.getRoom(0).addActor(B);
        */
        
        //LockedChest CH = new LockedChest();
        
        //CH.generateRewardsBasic();
        //CH.x = 95;
        //CH.y = 50;
        //MW.getRoom(0).addActor(CH);
        /*
        Password P = new Password();
        P.x = 95;
        P.y = 50;
        MW.getRoom(0).addActor(P);
        */
        
        /*
        for(Die D : Die.presets){
            MW.getRoom(0).addActor(D);
        }
        */
        
        /*/
        Card C = new Card(PLACE_CARD,Player.The.image.getBasic(),"Player");
        MW.getRoom(0).addActor(C);

        Card C2 = Card.getCardWildFire();
        MW.getRoom(0).addActor(C2);
        //*/
        
        //TutorialAnimalC ATUT = new TutorialAnimalC();
        //MW.getRoom(0).addActor(ATUT);
        
        MW.getRoom(0).addActor(new Bone());
        
        Shovel S = new Shovel();
        MW.getRoom(0).addActor(S);
        
        EightBallpart EBP = new EightBallpart(10,10);
        MW.getRoom(0).addActor(EBP);
        
        //MW.getRoom(0).addActor(new Spider_Boss());
        
        MW.getRoom(0).addActor(new RPSPart());
        
        //MW.getRoom(0).addActor(new Spider_Random());
        
        
        //*/
        Revolver toolPartA = new Revolver();
        toolPartA.x = 95;
        toolPartA.y = 40;
        MW.getRoom(0).addActor(toolPartA);
        
        
        Converter toolPartB = new Converter();
        toolPartB.x = 105;
        toolPartB.y = 40;
        MW.getRoom(0).addActor(toolPartB);
        
        Bumper toolPartC = new Bumper();
        toolPartC.x = 115;
        toolPartC.y = 40;
        MW.getRoom(0).addActor(toolPartC);
        
        Handle toolPartD = new Handle();
        toolPartD.x = 95;
        toolPartD.y = 50;
        MW.getRoom(0).addActor(toolPartD);
        
        SmokePuff toolPartE = new SmokePuff();
        toolPartE.x = 105;
        toolPartE.y = 50;
        MW.getRoom(0).addActor(toolPartE);
        
        Violin toolPartF = new Violin();
        toolPartF.x = 115;
        toolPartF.y = 50;
        MW.getRoom(0).addActor(toolPartF);
        
        CardPrinter CP = new CardPrinter();
        MW.getRoom(0).addActor(CP);
        
        MW.getRoom(0).addActor(new SciencePowder());
        
        //*/

        //MW.getRoom(0).addActor(test);
        //MW.getRoom(0).addActor(testaporter);
        
        Tiger_a RF = new Tiger_a();
        RF.x = 80;
        RF.y = 40;
        MW.getRoom(0).addActor(RF);
        
        /*
        ImageLoader.switchMap("CITYSCALE");
        BeeHive BH = new BeeHive(ImageLoader.loadImage("city_bike_yellow.txt"));
        BH.x = 100;
        BH.y = 50;
        MW.getRoom(0).addActor(BH);
        */
        
        
        
        Floppy F = new Floppy(50,50);
        //MW.getRoom(0).addActor(F);
        
        VendingMachine VM = new VendingMachine(VendingMachine.DRINKS);
        VM.stock(F, "free", 0);
        
        DirectionalTool DT = new DirectionalTool();
        ConverterTool CT = new ConverterTool();
        MapTool MT = new MapTool();
        Instrument IT = new Instrument();
        MW.getRoom(0).addActor(DT);
        DT.x = 50;
        DT.y = 50;
        
        VM.stock(CT, "free", 0);
        VM.stock(MT, "free", 0);
        VM.stock(IT, "free", 0);
        VM.stock(new NewsPaper(), "free", 0);
        
        VM.x = 50;
        VM.y = 50;
        MW.getRoom(0).addActor(VM);
        
        MW.MW = MW;
        
        ((MainWorld)W).playFirst();
    }

    public static ArrayList<ArrayList<ColorTuple>> generateSprite(){
        ArrayList<ArrayList<ColorTuple>> ret = new ArrayList<ArrayList<ColorTuple>>();
        
        for(int i = 0; i<10; ++i){
            ret.add(new ArrayList<ColorTuple>());
            for(int j = 0; j<10; ++j){
                ret.get(i).add( new ColorTuple(Color.BLACK,Color.GREEN,'F')); 
            }
        }
        
        return ret;
    }
}

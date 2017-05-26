/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors;

import java.io.File;
import java.io.FileNotFoundException;
import text.Actors.PuzzleObjects.*;
import java.util.ArrayList;
import java.util.Scanner;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Environment.Environment;
import text.Environment.Inanimate.Rock;
import static text.Environment.Inanimate.Rock.imageList;
import text.Utility.ImageLoader;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.ImageBuilder;
import text.Utility.LootGenerator;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class NewsPaper extends Interactable{
    public String[] message;
    
    public static String[] news;
    public final int STRING_LENGTH = 148;
    
    public NewsPaper(String[] s){
        super("News Print");
        this.name = "News Print";
        
        message = s;
        
        if(news == null){
            loadNews();
        }
        
        ImageLoader.switchMap("CITYSCALE");
        this.image = ImageLoader.loadImage("whistle.txt");
        
    }
    
    public NewsPaper(){
        super("News Print");
        this.name = "News Print";
        
        if(news == null){
            loadNews();
        }
        
        int story = oRan.nextInt(news.length);
        this.message = convertString(news[story]);
        
        
        ImageLoader.switchMap("CITYSCALE");
        this.image = ImageLoader.loadImage("whistle.txt");
    }
    
    public NewsPaper clone(){
        return new NewsPaper(this.message);
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> aList = super.pollOptions();

        Option E = Option.display(this,"Read",message);
        aList.add(E);

        return aList;
    }
    
    private void loadNews(){
        try{
            Scanner oScan = new Scanner(new File("news.txt"));
            int stories = oScan.nextInt();
            news = new String[stories];
            int i = 0;
            
            oScan.nextLine();
            
            while(oScan.hasNextLine()){
                news[i] = oScan.nextLine();
                i++;
            }
            
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading news!");
        }
    }
    
    public String toString(){
        return this.name;
    }
    
    private String[] convertString(String s){
        try{
            Scanner oScan = new Scanner(s);

            double lines = s.length() / STRING_LENGTH;
            String[] ret = new String[(int)(lines+1)];

            int i = 0;
            while(oScan.hasNext()){
                ret[i] = "";
                while(ret[i].length() < STRING_LENGTH && oScan.hasNext()){
                    ret[i] += oScan.next()+" ";
                }
                ++i;
            }

            while(i < lines+1){
                ret[i] = " ";
                ++i;
            }

            return ret;
        } catch(Exception e){}
        
        return new String[]{"You can't make out the text on the newsprint..."};
    }
}

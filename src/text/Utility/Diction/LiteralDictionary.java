/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility.Diction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static text.WorldFrame.World.oRan;

/**
 * http://www.wordfrequency.info
 * @author FF6EB4
 */
public class LiteralDictionary {
    
    private static ArrayList<String> adjectives;
    
    //ALL the words
    private static ArrayList<String> fullStringList;
    private static HashMap<String, Word> fullWordList;
    
    private static HashMap<Character, Integer> letterValues;
    private static HashMap<Character, Integer> letterFreq;
    
    //Init
    private static LiteralDictionary theDictionary = new LiteralDictionary();

    
    private LiteralDictionary(){
        adjectives = new ArrayList<>();
        
        fullStringList = new ArrayList<>();
        fullWordList = new HashMap<>();
        
        letterValues = new HashMap<>();
        letterFreq = new HashMap<>();
        try{
            Scanner oScan = new Scanner(new File("letter_values.txt"));
            
            while(oScan.hasNextLine()){
                char c = oScan.next().charAt(0);
                int val = oScan.nextInt();
                int freq = oScan.nextInt();
                
                letterValues.put(c, val);
                letterFreq.put(c,freq);
            }
            
            oScan = new Scanner(new File("AdjectiveList.txt"));
            
            while(oScan.hasNextLine()){
                String s = oScan.next().toUpperCase();
                
                adjectives.add(s);
            }
            
            oScan = new Scanner(new File("dictionary_full.txt"));
            
            while(oScan.hasNextLine()){
                String s = oScan.next().toUpperCase();
                Word w = new Word(s);
                
                fullStringList.add(s);
                fullWordList.put(s,w);
            }
        } catch (FileNotFoundException E){
            System.out.println("Something went wrong loading letters!");;
        }
    }
    

    
    public static int getValue(String s){
        int total = 0;
        
        s = s.toUpperCase();
        for(int i = 0; i<s.length(); ++i){
            char check = s.charAt(i);
            
            try{
                total += letterValues.get(check);
            } catch (NullPointerException NPE){
                total += 3; //Null Pointers assume bizzare characters. Worth 3.
            }
        }
        return total;
    }
    /**
     * Returns a random letter based on frequency
     * @return 
     * The letter
     */
    public static char getRandomLetter(){
        char letter = (char)('A'+oRan.nextInt(26));
        int i = oRan.nextInt(10);
        
        while(i < letterValues.get(letter)){
            letter = (char)('A'+oRan.nextInt(26));
            i = oRan.nextInt(10);
        }
        return letter;
    }
    
    public static boolean isWord(String s){
        return fullStringList.contains(s);
    }
    
    public static Word getWord(String s){
        if(isWord(s)){
            return fullWordList.get(s);
        } else {
            return null;
        }
    }
    
    public static String getAdjective(){
        int index = oRan.nextInt(adjectives.size());
        //System.out.println(adjectives.get(index));
        return adjectives.get(index);
    }
    
    public static String getRandomWord() {
        int index = oRan.nextInt(fullStringList.size());
        return fullStringList.get(index);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Hero;

import java.util.ArrayList;

/**
 *
 * @author FF6EB4
 */
public class WordController extends HeroController{
    public WordController(String s, float f){
        this.word = s;
        for(int i = 0; i<word.length(); ++i){
            posList.add(i*13);
            speedList.add((((double)f)+0.1));
            colorList.add(WORD);
            this.delay = 1;
            this.height = 0;
        }
        
        this.letterList = new ArrayList<>();
    }
}

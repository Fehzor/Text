/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.Actor;
import text.Actors.Interactable;
import text.Actors.Messages.Option;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class GamePiece extends Interactable{
    
    public static ArrayList<ColorTuple> colors = new ArrayList<ColorTuple>();
    
    public static ColorTuple blue = new ColorTuple(Color.BLUE,Color.WHITE,' ');
    public static ColorTuple red = new ColorTuple(Color.RED,Color.BLACK,' ');
    public static ColorTuple yellow = new ColorTuple(Color.YELLOW,Color.BLACK,' ');
    public static ColorTuple green = new ColorTuple(Color.GREEN,Color.BLACK,' ');
    public static ColorTuple pink = new ColorTuple(Color.PINK,Color.BLACK,' ');
    public static ColorTuple pink2 = new ColorTuple(new Color(255,110,180),Color.BLACK,' ');
    public static ColorTuple orange = new ColorTuple(Color.ORANGE,Color.WHITE,' ');
    public static ColorTuple magenta = new ColorTuple(Color.MAGENTA,Color.WHITE,' ');
    public static ColorTuple cyan = new ColorTuple(Color.CYAN,Color.BLACK,' ');
    public static ColorTuple dkgrey = new ColorTuple(Color.DARK_GRAY,Color.LIGHT_GRAY,' ');
    public static ColorTuple ltgrey = new ColorTuple(Color.LIGHT_GRAY,Color.DARK_GRAY,' ');
    public static ColorTuple black = new ColorTuple(Color.BLACK,Color.WHITE,' ');
    public static ColorTuple white = new ColorTuple(Color.WHITE,Color.BLACK,' ');
    
    public char letter = ' ';
    
    public GamePiece(char C){
        super();
        this.dead = false;
        this.image = ImageLoader.loadImage("game_piece.txt").clone();
        this.letter = C;
        
        //this.GI = GameIntellect.getRandomGI();
        
        this.colorImageRandom();
        
        this.moves = new ArrayList<>();
        //this.moves.add(GameMove.getRandomMove());
        //this.moves.add(GameMove.getRandomMove());
    }
    
    public GamePiece clone(){
        GamePiece ret = new GamePiece(this.letter);
        
        ret.image = this.image.clone();
        ret.moves = new ArrayList<>();
        ret.moves.addAll(this.moves);
        ret.GI = this.GI;
        
        return ret;
    }
    
    public int ID = 0;
    public int posX = 0;
    public int posY = 0;
    
    public GameIntellect GI;
    public ArrayList<GameMove> moves;
    
    public boolean act(){
        this.depth = y;
        return true;
    }
    
    public int[][] makeMove(GameBoard board){
        GameMove GM = GI.chooseMove(this, board);
        if(GM == null){
            return board.board;
        }
        return GM.makeMove(this, board);
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = super.pollOptions();
        
        ret.add(Option.display(this, "Info: AI", GI.toString()));
        ret.add(Option.display(this, "Info: Moves", moves.toString()));
        
        return ret;
    }
    
    public String toString(){
        return "Game Piece "+this.letter;
    }
    
    public void colorImage(ColorTuple edge, ColorTuple main){
        HashMap<Character,ColorTuple> colorset = 
                ImageBuilder.buildColorMap(edge, edge, main, main);
        
        ImageBuilder.swapColorScheme((TextImageBasic)this.image, colorset);
    }
    
    
    
    public void colorImageRandom(){
        if(colors.size() == 0){
            colors.add(red);
            colors.add(green);
            colors.add(blue);
            colors.add(yellow);
            colors.add(pink);
            colors.add(pink2);
            colors.add(orange);
            colors.add(magenta);
            colors.add(cyan);
            colors.add(ltgrey);
            colors.add(dkgrey);
            colors.add(black);
            colors.add(white);
        }
        int a = oRan.nextInt(colors.size());
        int b = oRan.nextInt(colors.size());
        while(b == a){
            b = oRan.nextInt(colors.size());
        }
        
        this.colorImage(colors.get(a),colors.get(b));
    }
}

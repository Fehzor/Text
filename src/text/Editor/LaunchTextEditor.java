/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Editor;
import text.WorldFrame.Templates.WorldBuilder;
import text.WorldFrame.World;
import text.WorldFrame.MainWorld;
import text.Editor.TextEditor;
import text.WorldFrame.Room;
import text.Actors.*;
import text.Images.*;
import java.awt.Color;
import java.util.*;
import text.Frame.TextDisplay;
import text.Utility.*;
import text.WorldFrame.*;

/**
 * #
 * @author FF6EB4
 */
public class LaunchTextEditor {
    
    public static void main(String[] args) {
        textEdit();
    }
    
    public static void textEdit(){
        World W = WorldBuilder.generateEditingWorld();
        MainWorld MW = (MainWorld)W;
        MainWorld.main = W;
        ((MainWorld)W).playFirst();
    }
    
    // =
}

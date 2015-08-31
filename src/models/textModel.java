package models;

import utility.letterTuple;
import java.util.*;
import java.awt.*;
import java.io.*;

public abstract class textModel{
	//Loads from a file
	public abstract void loadFile(Scanner source);
	
	//Save the model as a file
	public abstract void saveFile(PrintWriter out);

	//Returns a set of letter tuples, ready for display
	public abstract ArrayList<ArrayList<letterTuple>> display();
	
	//Returns a model with this of display.
	public abstract textModelBasic make2D();

	//Returns the letter tuple at that moment at that point.
	public abstract letterTuple displayAt(int col, int row);
	
	//Returns the height (maximum height?)
	public abstract int height();
	
	//Returns the width (maximum width?)
	public abstract int width();
}

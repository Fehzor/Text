package models;

/**
*The most basic model possible.
*
*/

import models.textModel;
import utility.letterTuple;
import java.util.*;
import java.awt.*;
import java.io.*;

public class textModelBasic extends textModel{
	ArrayList<ArrayList<letterTuple>> model;
        
        public textModelBasic(){}
        
	public textModelBasic(Scanner scan){
		loadFile(scan);
	}

	public textModelBasic(ArrayList<ArrayList<letterTuple>> model){
		this.model = model;
	}

	public void loadFile(Scanner scan){
		HashMap<Character, letterTuple> colorKey = new HashMap<Character, letterTuple>();
		ArrayList<ArrayList<letterTuple>> build = new ArrayList<ArrayList<letterTuple>>();

		try{
			int lines = scan.nextInt();
			
			for(int i=0;i<lines;++i){
				String s = scan.next();
				char rep = s.charAt(0);
				char act = s.charAt(1);
				int firstInt = scan.nextInt();
				if(firstInt != 256){
					Color back = new Color(firstInt, scan.nextInt(), scan.nextInt());
					Color front = new Color(scan.nextInt(), scan.nextInt(), scan.nextInt());
					colorKey.put(rep, new letterTuple(act, back, front));
				} else {
					colorKey.put(rep, letterTuple.TRANSPARENT);
				}
		}
			
			int rows = scan.nextInt();
			int cols = scan.nextInt();

			scan.nextLine();
			
			for(int row = 0; row < rows; ++row){
				build.add(new ArrayList<letterTuple>());	
				String line = scan.nextLine();
				for(int col = 0; col < cols; ++col){
					char toAdd = line.charAt(col);
					build.get(row).add(colorKey.get(toAdd));
				}
			}
		} catch (Exception e){System.err.println("Error reading file");}
		
		this.model = build;
	}
	
	public void saveFile(PrintWriter out){
		ArrayList<letterTuple> archived = new ArrayList<letterTuple>();
		for(int col=0; col<model.size(); ++col){
			for(int row=0;row<model.get(0).size();++row){
				if(!archived.contains(model.get(col).get(row))){
					archived.add(model.get(col).get(row));
				}
			}
		}
		out.print(archived.size());
		
		for(int i=0;i<archived.size();++i){
			String toWrite = ((char)(i+'!'))+"";
			
			letterTuple LT = archived.get(i);
			if(LT.equals(letterTuple.TRANSPARENT)){
				toWrite += "~ 256";
			} else {
				toWrite += LT.letter() + " ";
				toWrite += LT.background().getRed()+" "+LT.background().getGreen()+" "+LT.background().getBlue()+" ";
				toWrite += LT.foreground().getRed()+" "+LT.foreground().getGreen()+" "+LT.foreground().getBlue()+" ";
			}
			out.println();
			out.print(toWrite);
		}
		out.println();
		out.print(model.size() + " " + model.get(0).size());
		
		for(int col=0; col<model.size(); ++col){
			String toWrite = "";
			for(int row=0;row<model.get(0).size();++row){
				letterTuple at = model.get(col).get(row);
				char letter = ((char)('!'+archived.indexOf(at)));
				toWrite+=letter;
			}
			out.println();
			out.print(toWrite);
		}
		
		out.flush();
	}
	
	public letterTuple displayAt(int col, int row){
		return model.get(row).get(col);
	}

	public ArrayList<ArrayList<letterTuple>> display(){
		return model;
	}

	public textModelBasic make2D(){
		return this;
	}

	public int height(){
		if(model == null){
			return 0;
		}
		return model.size();
	}

	public int width(){
		if(model == null){
			return 0;
		}
		return model.get(0).size();
	}
}

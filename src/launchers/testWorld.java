package launchers;

import mechanics.actors.player;
import mechanics.input;
import mechanics.actor;
import models.textModelBasic;
import mechanics.world;
import models.textModelAnimated;
import models.textModel;
import display.worldPanel;
import display.displayFrame;
import utility.letterTuple;
import java.util.*;
import java.awt.*;
import java.io.*;
import mechanics.*;
import mechanics.actors.*;
	
public class testWorld{
	
	public static input in;
	public static worldPanel WP;
	public static displayFrame DF;
	public static world W;

	public static void main(String [] args){
		generate();
		
		//testActor();
		//testModelLoad();
		//testActorBehaviour();
		//testPlayer();
                //testAnimation();
                //testChange();
                testDoors();
	}

	public static void generate(){
		ArrayList<ArrayList<letterTuple>> background = new ArrayList<ArrayList<letterTuple>>();
		Random r = new Random();
		
		in = new input();
                testWorld.W = new world();
		testWorld.WP = new worldPanel( W, in );
		testWorld.DF = new displayFrame(WP, "testWorld");
		
	}

	public static textModelBasic generateLoadedModel(String name){
		try{
			textModelBasic TBM = new textModelBasic(new Scanner(new File(name)));
			return TBM;
		} catch (Exception e) { System.err.println("testTextModelBasicFile not found!"); }
		return null;
	}

	public static void saveModelBasic(String name){
		textModelBasic toSave = generateModel();
		File theFile = new File(name);	
		PrintWriter PW;
		try{
			PW = new PrintWriter(theFile);
			toSave.saveFile(PW);
		} catch (Exception E){System.err.println("Trouble making the file and such");}
	
	}
	
	//Generate a textModel, for testing purposesesesseses
	public static textModelBasic generateModel(){		
		Random r = new Random();
		ArrayList<ArrayList<letterTuple>> basicModel = new ArrayList<ArrayList<letterTuple>>();
		for(int i=0;i<5;++i){
			basicModel.add(new ArrayList<letterTuple>());
			for(int j=0;j<7;++j){
				if(i==2||j==2){
					basicModel.get(i).add(letterTuple.TRANSPARENT);
				} else {
					basicModel.get(i).add(new letterTuple((char)('!'+r.nextInt(70)),Color.white,new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255))));
				}
			}
		}
		
		textModelBasic TMB = new textModelBasic(basicModel);
		return TMB;
	}
        
        public static textModelAnimated generateAnimatedModel(){		
		ArrayList<textModel> models = new ArrayList<>();
                
                for(int i = 0; i < 10; ++i){
                    models.add(generateModel());
                }
		
		textModelAnimated TMA = new textModelAnimated(models);
		return TMA;
	}


	public static void testActor(){
		textModelBasic TMB = generateModel();
		player.thePlayer.setModel(generateModel());
		actor A = new actor(25,25,TMB,W);

		testWorld.W.addActor(A);

		testWorld.W.run();
	}

	public static void testModelLoad(){
		saveModelBasic("test");
		textModelBasic TMB = generateLoadedModel("test");
		player.thePlayer.setModel(generateModel());
		actor A = new actor(25,25,TMB,W);

		testWorld.W.addActor(A);

		testWorld.W.run();
	}

	public static void testActorBehaviour(){
		
		player.thePlayer.setModel(generateModel());		

		actor A = new actor(25,25,generateModel(),W){ public void act(){
			Random r = new Random();
			if(r.nextInt(1000)<950)return;
			
			actor B = new actor(this.getX(),this.getY(),this.getModel()){public void act(){
				Random r = new Random();
				this.setX(this.getX()+r.nextInt(2));
				this.setY(this.getY()+r.nextInt(2));
			}};
			
			currentWorld.addActor(B);
		}};
	
		testWorld.W.addActor(A);

		testWorld.W.run();
	}

	public static void testPlayer(){
		player P = player.thePlayer;
		P.setModel(generateModel());
		testWorld.W.run();
	}
        
        public static void testChange(){
		player P = player.thePlayer;
		P.setModel(generateModel());
		
                //Attempt to change worlds every 20000
                while(true){
                try{Thread.sleep(20000);}catch(Exception e){}
                
                testWorld.WP.loadWorld(new world());
                }
                
	}
        
        public static void testDoors(){
		

                world one = new world();
                world two = new world();
                
                worldNode WNOne = new worldNode();
                WNOne.heldWorld = one;
                one.owner = WNOne;
                WNOne.mainPanel = testWorld.WP;
                
                worldNode WNTwo = new worldNode();
                WNTwo.heldWorld = two;
                two.owner = WNTwo;
                WNTwo.mainPanel = testWorld.WP;
                
                WNOne.connections.add(WNTwo);
                WNOne.connections.add(WNTwo);
                
                WNTwo.connections.add(WNOne);
                WNTwo.connections.add(WNOne);
                
                worldGenerator.addEdgeDoor(actor_doorway.NORTH, one);
                worldGenerator.addEdgeDoor(actor_doorway.NORTH, two);

                player P = player.thePlayer;
		P.setModel(generateModel());

                testWorld.WP.loadWorld(one);
	}
        
        public static void testAnimation(){
		
		player.thePlayer.setModel(generateModel());		

		actor A = new actor(25,25,generateAnimatedModel(),W){ 
                    public void act(){
			Random r = new Random();
			if(r.nextInt(1000)<950)return;
                        
                        this.setX(this.getX()+r.nextInt(4)-2);
			this.setY(this.getY()+r.nextInt(4)-2);
                        
                        textModelAnimated TMA = (textModelAnimated)getModel();
                        TMA.nextFrame();
		}};
	
		testWorld.W.addActor(A);

		testWorld.W.run();
	}
}

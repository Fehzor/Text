package display;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class displayFrame extends JFrame{
	public static final int DEFAULT_MAIN_HEIGHT = 50;
	public static final int DEFAULT_MAIN_WIDTH = 150;	

	public displayFrame frame;
	public textPanel mainPanel;

	private JFrame mainFrame;

	public displayFrame(textPanel mainPanel, String name){
				super(name);
				this.mainPanel = mainPanel;
				
				//Set up the closing button to work
				addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
		
		SwingUtilities.invokeLater( new Runnable() {
			public void run(){					
				add(mainPanel);
							
				//mainFrame.pack();
				setSize(500,500);
				setLocationRelativeTo(null);
				setVisible(true);
		}});
	}

}

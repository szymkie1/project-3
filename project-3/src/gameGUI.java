
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@SuppressWarnings("serial")

public class gameGUI extends JFrame implements ActionListener{
	
	private JMenuBar topMenu;
	private JMenu gameMenu, helpMenu;
	private JMenuItem gReset, gExit, hHelp, hAbout;
	private JPanel gameBoard;
	private JPanel infoPanel;
	private JPanel uiPanel;
	private JLabel bScoreLabel, bestScore, moveLabel, moves;
	private JButton hint, reset;
	private level[] gameLevels = new level[12];
	
	public gameGUI(){
		
		
		
		super("Sliding Block Puzzles");
		
		File[] fileArray = fileSetup.setFiles();
		
		
		for(int numFiles = 0; numFiles < 12; numFiles++){
			gameLevels[numFiles] = new level();
			gameLevels[numFiles].initLevel(fileArray[numFiles]);
		}
		
		
		
		setSize(500,350);
		
		topMenu = new JMenuBar();
		setJMenuBar(topMenu);
		
		gameMenu = new JMenu("Game");
		topMenu.add(gameMenu);
		
		helpMenu = new JMenu("Help");
		topMenu.add(helpMenu);
		
		gReset = new JMenuItem("Reset");
		gameMenu.add(gReset);
		gReset.setMnemonic(KeyEvent.VK_R);
		gReset.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.ALT_MASK));
		gReset.addActionListener(this);
		gameMenu.addSeparator();
		
		gExit = new JMenuItem("Exit");
		gameMenu.add(gExit);
		gExit.setMnemonic(KeyEvent.VK_E);
		gExit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.ALT_MASK));
		gExit.addActionListener(this);
		
		
		hHelp = new JMenuItem("Help");
		helpMenu.add(hHelp);
		hHelp.setMnemonic(KeyEvent.VK_H);
		hHelp.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_H, ActionEvent.ALT_MASK));
		hHelp.addActionListener(this);
		helpMenu.addSeparator();
		
		hAbout = new JMenuItem("About");
		helpMenu.add(hAbout);
		hAbout.setMnemonic(KeyEvent.VK_A);
		hAbout.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.ALT_MASK));
		hAbout.addActionListener(this);
		
		gameBoard = new JPanel();
		infoPanel = new JPanel();
		
		uiPanel = new JPanel();
		uiPanel.setLayout(new BoxLayout(uiPanel, BoxLayout.Y_AXIS));
		
		moveLabel = new JLabel("Move: ");
		infoPanel.add(moveLabel);
		moves = new JLabel("0");
		infoPanel.add(moves);
		
		hint = new JButton("Hint");
		infoPanel.add(hint);
		
		reset = new JButton("Reset");
		infoPanel.add(reset);
		
		bScoreLabel = new JLabel("Best Score: ");
		infoPanel.add(bScoreLabel);
		bestScore = new JLabel("0");
		infoPanel.add(bestScore);
		
		uiPanel.add(infoPanel);
		uiPanel.add(gameBoard);
		
		add(uiPanel);
		
		
		
		
		
		
		
		
		
		
		setVisible(true);
		
	}

	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == gReset){
			System.out.println("Reset Pressed");
		}
		else if(e.getSource() == gExit){
			System.out.println("Exiting");
			System.exit(0);
		}
		else if(e.getSource() == hHelp){
			JOptionPane.showMessageDialog(gameGUI.this,
					"Coming Soon","Help", JOptionPane.PLAIN_MESSAGE );
		}
		else if(e.getSource() == hAbout){
			JOptionPane.showMessageDialog(gameGUI.this,
					"Sliding Block Puzzles \n"+
				    "CS 342-Project 3 \n"+
					"Authors \n"+
				    "Joshua Rodriguez \n"+
					"Ryan Szymkiewicz \n"
						,"About", JOptionPane.PLAIN_MESSAGE );
		}
		else{
			System.out.println("Problem with menu button actions");
		}
		
		
		
	}
	
}

class fileSetup{
	
	public static File[] setFiles(){
		
		File[] fileArray;
		
		fileArray = new File[12];
		
		fileArray[0] = new File("src/level1.data");
		fileArray[1] = new File("src/level2.data");
		fileArray[2] = new File("src/level3.data");
		fileArray[3] = new File("src/level4.data");
		fileArray[4] = new File("src/level5.data");
		fileArray[5] = new File("src/level6.data");
		fileArray[6] = new File("src/level7.data");
		fileArray[7] = new File("src/level8.data");
		fileArray[8] = new File("src/level9.data");
		fileArray[9] = new File("src/level10.data");
		fileArray[10] = new File("src/level11.data");
		fileArray[11] = new File("src/level12.data");
		
		for(int fileCount=0; fileCount < 12; fileCount++){
		try(FileInputStream fis = new FileInputStream(fileArray[fileCount])){
			System.out.println("Successful read");
			}
		catch(IOException e){
			System.out.println(e);
			System.exit(0);
		}
		}
		
		return fileArray;
	}
	
}
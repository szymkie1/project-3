/**************************
 * gameGUI.java
 * Written by Ryan Szymkiewicz for CS 342 Project 3
 * These classes run the game.  The GUI is set up here and listeners are
 * set up to check for user input.  The level files are also read in.
 ***************************/





import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.*;
import java.io.PrintStream;


@SuppressWarnings("serial")

//The gameGUI class extends JFrame and implements ActionListener
//this class sets up the GUI

public class gameGUI extends JFrame implements ActionListener{

	private JMenuBar topMenu;
	private JMenu gameMenu, helpMenu;
	private JMenuItem gReset, gExit, hHelp, hAbout;


	private JLabel bScoreLabel, bestScore, moveLabel, moves;
	private JButton hint, reset, solve;
	private level[] gameLevels = new level[12];
	
	private arrayContainer[] dispArrays = new arrayContainer[12];
	public board[] panels = new board[12];
	public JPanel uiPanel = new JPanel();
	private moveKeeper numMoves = new moveKeeper();
	private File scoreFile;
	private char[][] gameScores = new char[12][6];
	//set up variables that will be used
	
	//use the gameLevels array to initialize the levels
	//take each file and initalize each level from that file
	//get the blocks,  and then create a character array that
	//displays what each button should be displayed as
	//set this array as well.
	private void getDispArrays(File[] fileArray){
		
		for(int i=0; i < 12; i++){
		gameLevels[i] = new level();
		gameLevels[i].initLevel(fileArray[i]);
		gameLevels[i].getBlocks();
		char[][] arrayTemp=gameLevels[i].getArrayWithBlocks();
		dispArrays[i] = new arrayContainer();
		dispArrays[i].setArray(arrayTemp);
		}
		
	}
	
	//this will initialize 12 JPanels to switch between levels
	//set the level number and set it to use a grid layout
	private void initPanels(int start){
		
	   for(int i=start; i<start+1; i++){	
		panels[i] = new board();
		panels[i].setLevel(i);
		panels[i].setLayout(new GridLayout(6,6));
	   }
		
	}
	
	//the setupPanels method takes each panel and puts the game buttons on it
	private void setupPanels(int start){
		
		for(int p=start; p<start+1; p++){
			
        button gameButtons[][] = new button[6][6];//create array of buttons
		char[][] convArray=dispArrays[p].getArray();
		for (int i = 0; i < 6; i++) {//each level is set to be 6x6
			for (int j = 0; j < 6; j++) {
				gameButtons[i][j] = new button();//initialize each button
				
				panels[p].add(gameButtons[i][j]);//add button to panel
				gameButtons[i][j].setRow(i);//set row and column
				gameButtons[i][j].setColumn(j);
				String buttonString = Character.toString(convArray[i][j]);//get button text
				gameButtons[i][j].setButtonString(buttonString);//set button text
				char buttonChar =convArray[i][j];
				gameButtons[i][j].setText(buttonString);
				int whichBlock=letterToBlock(buttonChar);//find out which block is used here
				gameButtons[i][j].setBlock(whichBlock);//set the block
				gameButtons[i][j].addMouseListener(new buttonListener(gameButtons,
						gameLevels[p],panels[p],panels,uiPanel,moves,numMoves,
						bestScore,gameScores));
				//add a mouse listener to check for input
			}
			
		}
		
		
		}
	}


	public gameGUI(){


		//set up JFrame for the game
		super("Sliding Block Puzzles");

		//set size
		setSize(500,350);

		//get an array of level files from the directory
		File[] fileArray = fileSetup.setFiles();
		
		//get the scores file
		scoreFile = new File("scores.data");
		
		//set up an array that contains the current lowest scores
		//to complete a level
		try(FileInputStream fis = new FileInputStream(scoreFile)){
			System.out.println("Scoreboard file read.");
			
					int currentChar;
					int rowIter=0;
					int colIter=0;
					while((currentChar = fis.read()) != -1){ 
					
					gameScores[rowIter][colIter]=(char)currentChar;
					
					colIter++;
					if(colIter==6){
						rowIter++;
						colIter=0;
					}
						
					}
			
			}
		catch(IOException e){
			System.out.println(e);
			System.out.println("Scoreboard file read failed.");
			}
		
		  
		


		

		//set up the two menu bars-game and help
		//game menu will contain reset and exit options
		//help will contain help and about options
		//each menu option sets a mnemonic to allow for keystrokes and adds an action
		//listener

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

		
        
		//set up a new JPanel that will display level information
		JPanel infoPanel = new JPanel();

		//set up a box layout
		uiPanel.setLayout(new BoxLayout(uiPanel, BoxLayout.Y_AXIS));


		//set up information for levels 
		//contains move counter, reset, hint and solve buttons and the best score for level
		moveLabel = new JLabel("Move: ");
		infoPanel.add(moveLabel);
		moves = new JLabel("0");
		infoPanel.add(moves);

		hint = new JButton("Hint");
		infoPanel.add(hint);

		reset = new JButton("Reset");
		infoPanel.add(reset);

		solve = new JButton("Solve");
		infoPanel.add(solve);

		bScoreLabel = new JLabel("Best Score: ");
		infoPanel.add(bScoreLabel);
		bestScore = new JLabel(Character.toString(gameScores[0][3])+
				Character.toString(gameScores[0][4]));
		infoPanel.add(bestScore);

        getDispArrays(fileArray);
		
		
		//call the methods to set up the levels
		for(int iSetup=0; iSetup < 12; iSetup++){
		initPanels(iSetup);
		setupPanels(iSetup);
		}


		//add information panel to JPanel and also the first level
		uiPanel.add(infoPanel);
		uiPanel.add(panels[0]);
		
		
		

	
		
		
		
		add(uiPanel);
		
		
		
		
		








		setVisible(true);

	}

	//this method takes the block character and converts it to a number for use
	//with the buttons
	public int letterToBlock(char a){
		int num=-1;
		if(a=='Z'){
			num=0;
		}
		else if(a=='A'){
			num=1;
		}
		else if(a=='B'){
			num=2;
		}
		else if(a=='C'){
			num=3;
		}
		else if(a=='D'){
			num=4;
		}
		else if(a=='E'){
			num=5;
		}
		else if(a=='F'){
			num=6;
		}
		else if(a=='G'){
			num=7;
		}
		else if(a=='.'){
			num=-1;
		}
		else{
			System.out.println("Problem Converting");
		}

		return num;
	}





	//menu option actions are handled here
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == gReset){//reset
			System.out.println("Reset Pressed");
		}
		else if(e.getSource() == gExit){//exit
			System.out.println("Exiting");
			System.exit(0);
		}
		else if(e.getSource() == hHelp){//help
			JOptionPane.showMessageDialog(gameGUI.this,
					"Help\n"+
					"Buttons that span multiple rows\n"+
					"move vertically.  Buttons that span\n"+
					"multiple columns move vertically\n"+
					"The goal of the game is to move the Z\n"+
					"block to the right of the board.\n"+
					"To move a piece click on the button \n"+
					"that is closest to the direction you wish \n"+
					"to move.  For example,  if you wish to move \n"+
					"a block right,  first click on the rightmost button \n"+
					"contained in that block, and then click on the button that\n"+
					"you wish to move that block to \n"+
					"Yes,  level 12 is unsolvable. \n"+
					"That is the last level of the game as well \n"+
					"Once level 12 has been reached,  the game can \n"+
					"be exited.",
					"Help", JOptionPane.PLAIN_MESSAGE );
		}
		else if(e.getSource() == hAbout){//about
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




//the buttonListener class handles input by clicking on the buttons and
//moves the buttons
class buttonListener extends JFrame implements MouseListener{
	
	
	button[][] buttonArray;
	level currLevel;
	board currBoard;
	board[] boards;
	JPanel gamePanel;
	JLabel currMoves;
	moveKeeper moves;
	JLabel bestScore;
	char[][] scoreArr;
	
	
	
	
	//use the constructor to initialize variables that will be used
	//in the class
	buttonListener(button[][] tempArray, level tempLevel, board tempBoard, board[] tBoard,
			JPanel uiPanel, JLabel tMoves, moveKeeper tNumMoves, JLabel tbScores, 
			char[][] tScores){
		buttonArray=tempArray;
		currLevel=tempLevel;
		currBoard=tempBoard;
		boards=tBoard;
		gamePanel=uiPanel;
		currMoves=tMoves;
		moves=tNumMoves;
		bestScore=tbScores;
		scoreArr=tScores;
		

	}
	
	//move down
	private void moveDown(button b1, button b2){
		System.out.println("Move Down");
		char direction;
		int block;
		block=b1.getBlock();
		direction=currLevel.getBlocks().get(block).getDirection();
		
		if(direction=='v'){//check for proper direction
			int column=b1.getColumn();
			int startRow=b1.getRow();
			int endRow=b2.getRow();
			int changeRow=endRow-startRow;
			
			int checkEmptyIter=0;
			for(int checkEmpty=startRow+1; checkEmpty <= endRow; checkEmpty++){
				int currButton=buttonArray[checkEmpty][column].getBlock();
				if(currButton == -1){
					checkEmptyIter++;
				}
			}//check to make sure that collisions won't occur
			
			
			
			if(checkEmptyIter == changeRow){
				int size=currLevel.getBlocks().get(block).getSize();
				int startDel=currLevel.getBlocks().get(block).getLocations().get(0).getRow();
				
				for(int delInc=startDel; delInc < (size+startDel); delInc++ ){
					buttonArray[delInc][column].setBlock(-1);
					buttonArray[delInc][column].setText(".");
					System.out.println("Set position: "+delInc+" to -1");
				}//delete the block from the old squares
				
				char currBlock=currLevel.getBlocks().get(block).getLetter();
				
				//set the block onto the new squares
				if(size == 1){
					buttonArray[endRow][column].setBlock(block);
					buttonArray[endRow][column].setText(Character.toString(currBlock));
					currLevel.getBlocks().get(block).getLocations().get(0).setRow(endRow+1);
				}
				
				else{
					int start=endRow-size+1;
					int end=endRow;
					int currSize=0;
					for(int changeInc=start; changeInc <= end; changeInc++){
						buttonArray[changeInc][column].setBlock(block);
						buttonArray[changeInc][column].setText(Character.toString(currBlock));
				currLevel.getBlocks().get(block).getLocations().get(currSize).setRow(changeInc+1);
						currSize++;
					}
				}
				
				
				moves.addMove();//increment move class
				
				
			}
			else{
				System.out.println("Blocks are in the way of this move");
			}
		}
		else{
			System.out.println("Block doesn't move in this direction");
		}
	}
	
	//move up-same overall procedure as above
	private void moveUp(button b1, button b2){
		System.out.println("Move Up");
		char direction;
		int block;
		block=b1.getBlock();
		direction=currLevel.getBlocks().get(block).getDirection();
		
		if(direction=='v'){
			int column=b1.getColumn();
			int startRow=b1.getRow();
			int endRow=b2.getRow();
			int changeRow=endRow-startRow;
			
			int checkEmptyIter=0;
			for(int checkEmpty=startRow-1; checkEmpty >= endRow; checkEmpty--){
				int currButton=buttonArray[checkEmpty][column].getBlock();
				if(currButton == -1){
					checkEmptyIter++;
				}
		    }
			if(-(checkEmptyIter) == changeRow){
				int size=currLevel.getBlocks().get(block).getSize();
				int startDel=currLevel.getBlocks().get(block).getLocations().get(0).getRow();
				for(int delInc=startDel; delInc < (size+startDel); delInc++ ){
					buttonArray[delInc][column].setBlock(-1);
					buttonArray[delInc][column].setText(".");
					System.out.println("Set position: "+delInc+" to -1");
				}
				
				char currBlock=currLevel.getBlocks().get(block).getLetter();
				
				if(size == 1){
					buttonArray[endRow][column].setBlock(block);
					buttonArray[endRow][column].setText(Character.toString(currBlock));
					currLevel.getBlocks().get(block).getLocations().get(0).setRow(endRow+1);
				}
				
				else{
					int start=endRow;
					int end=endRow+size-1;
					int currSize=0;
					for(int changeInc=start; changeInc <= end; changeInc++){
						buttonArray[changeInc][column].setBlock(block);
						buttonArray[changeInc][column].setText(Character.toString(currBlock));
				currLevel.getBlocks().get(block).getLocations().get(currSize).setRow(changeInc+1);
						currSize++;
					}
			}
				moves.addMove();
			}
			else{
				System.out.println("Blocks are in the way of this move");
			}
			
		}
		else{
			System.out.println("Block doesn't move in this direction");
		}
		
	}
	
	//move right-same overall procedure as above
	private void moveRight(button b1, button b2){
		System.out.println("Move Right");
		char direction;
		int block;
		block=b1.getBlock();
		direction=currLevel.getBlocks().get(block).getDirection();
		
		if(direction=='h'){
			int row=b1.getRow();
			int startColumn=b1.getColumn();
			int endColumn=b2.getColumn();
			int changeColumn=endColumn-startColumn;
			
			int checkEmptyIter=0;
			for(int checkEmpty=startColumn; checkEmpty <= endColumn; checkEmpty++){
				System.out.println("Check Empty: "+checkEmpty);
				int currButton=buttonArray[row][checkEmpty].getBlock();
				if(currButton == -1){
					checkEmptyIter++;
				}
		    }
			if(checkEmptyIter == changeColumn){
				int size=currLevel.getBlocks().get(block).getSize();
				int startDel=currLevel.getBlocks().get(block).getLocations().get(0).getColumn();
				for(int delInc=startDel; delInc < (size+startDel); delInc++ ){
					buttonArray[row][delInc].setBlock(-1);
					buttonArray[row][delInc].setText(".");
					System.out.println("Set position: "+delInc+" to -1");
				}
				
				char currBlock=currLevel.getBlocks().get(block).getLetter();
				
				if(size == 1){
					buttonArray[row][endColumn].setBlock(block);
					buttonArray[row][endColumn].setText(Character.toString(currBlock));
					currLevel.getBlocks().get(block).getLocations().get(0).setColumn(endColumn+1);
				}
				else{
					int start=endColumn-size+1;
					int end=endColumn;
					int currSize=0;
					for(int changeInc=start; changeInc <= end; changeInc++){
						buttonArray[row][changeInc].setBlock(block);
						buttonArray[row][changeInc].setText(Character.toString(currBlock));
				currLevel.getBlocks().get(block).getLocations().get(currSize).setColumn(changeInc+1);
						currSize++;
					}
				}
				moves.addMove();
			}
			else{
				System.out.println("Blocks are in the way of this move");
			}
		}
		else{
			System.out.println("Block doesn't move in this direction");
		}
		
	}
	
	//move left-same overall procedure as above
	private void moveLeft(button b1, button b2){
		System.out.println("Move Left");
		char direction;
		int block;
		block=b1.getBlock();
		direction=currLevel.getBlocks().get(block).getDirection();
		
		if(direction=='h'){
			int row=b1.getRow();
			int startColumn=b1.getColumn();
			int endColumn=b2.getColumn();
			int changeColumn=endColumn-startColumn;
			
			int checkEmptyIter=0;
			for(int checkEmpty=startColumn-1; checkEmpty >= endColumn; checkEmpty--){
				int currButton=buttonArray[row][checkEmpty].getBlock();
				if(currButton == -1){
					checkEmptyIter++;
				}
		    }
			
			
			
			
			if(-(checkEmptyIter) == changeColumn){
				
				
				
				int size=currLevel.getBlocks().get(block).getSize();
				int startDel=currLevel.getBlocks().get(block).getLocations().get(0).getColumn();
				for(int delInc=startDel; delInc < (size+startDel); delInc++ ){
					buttonArray[row][delInc].setBlock(-1);
					buttonArray[row][delInc].setText(".");
					System.out.println("Set position: "+delInc+" to -1");
				}
				
				char currBlock=currLevel.getBlocks().get(block).getLetter();
				
				if(size == 1){
					buttonArray[row][endColumn].setBlock(block);
					buttonArray[row][endColumn].setText(Character.toString(currBlock));
					currLevel.getBlocks().get(block).getLocations().get(0).setColumn(endColumn+1);
				}
				else{
					int start=endColumn;
					int end=endColumn+size-1;
					int currSize=0;
					for(int changeInc=start; changeInc <= end; changeInc++){
						buttonArray[row][changeInc].setBlock(block);
						buttonArray[row][changeInc].setText(Character.toString(currBlock));
				currLevel.getBlocks().get(block).getLocations().get(currSize).setColumn(changeInc+1);
						currSize++;
					}
			}
				moves.addMove();
			}
			else{
				System.out.println("Blocks are in the way of this move");
			}
		}
		else{
			System.out.println("Block doesn't move in this direction");
		}
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub


		//set up MouseEvent to keep track of the last two clicks
		MouseEvent[] currEvents;

		mouseEvents.setTwoEvents(e);

		if(mouseEvents.getCurrentClick() == 1){
			currEvents=mouseEvents.getTwoEvents();
			button currButton = (button)currEvents[0].getSource();
			
		}
		else if(mouseEvents.getCurrentClick() == 0){//on second click
			currEvents=mouseEvents.getTwoEvents();
			button currButton1 = (button)currEvents[0].getSource();//get clicked buttons
			button currButton2 = (button)currEvents[1].getSource();

			if(currButton2.getBlock() != -1){
			System.out.println("You can't move a block if another block is in that location");	
			}//can't move to an occupied square
			else{
				
				int changeRow=currButton2.getRow()-currButton1.getRow();
				int changeColumn=currButton2.getColumn()-currButton1.getColumn();
				
				//determine the direction the block should move
				if(changeRow > 0){
					moveDown(currButton1, currButton2);
				}
				else if(changeRow < 0){
					moveUp(currButton1, currButton2);
				}
				else if(changeColumn > 0){
					moveRight(currButton1, currButton2);
				}
				else if(changeColumn < 0){
					moveLeft(currButton1, currButton2);
				}
				else{
					System.out.println("Not moving");
				}
				
				currMoves.setText(Integer.toString(moves.getMoves()));
				//check if level is complete, if so:
				if(buttonArray[0][5].getBlock() == 0 ||
					buttonArray[1][5].getBlock() == 0 ||
					buttonArray[2][5].getBlock() == 0 ||
					buttonArray[3][5].getBlock() == 0 ||
					buttonArray[4][5].getBlock() == 0 ||
					buttonArray[5][5].getBlock() == 0){
						System.out.println("Move to the next level");
						int currentLevel=currBoard.getLevel();
						System.out.println("Level:"+currBoard.getLevel());
						currBoard.setFinished();
						
						//check high scores to see if a new score has been set
						//if so,  re-write that file to indicate the new score
						int currentHighScore;
				currentHighScore=(Character.getNumericValue(scoreArr[currentLevel][3])*10)+
								(Character.getNumericValue(scoreArr[currentLevel][4]));
						if(moves.getMoves() < currentHighScore){
							System.out.println("Top score was: "+currentHighScore);
							System.out.println("User score is lower");
							int userScore=moves.getMoves();
							int firstNum=userScore/10;
							int secondNum=userScore%10;
							String tNum1=Integer.toString(firstNum);
							String tNum2=Integer.toString(secondNum);
							System.out.println("Moves "+tNum1+","+tNum2);
							char fNum=tNum1.charAt(0);
							char sNum=tNum2.charAt(0);
							System.out.println("Moves "+fNum+","+sNum);
							scoreArr[currentLevel][3]=fNum;
							scoreArr[currentLevel][4]=sNum;
							
							File outFile = new File("scores.data");
							try(PrintStream out = new PrintStream(outFile)){
								for(int a=0; a<12; a++){
									for(int b=0; b<6; b++){
										out.print(scoreArr[a][b]);
										
									}
								}
								
								out.close();
							}
							catch(IOException r) {
								System.out.println(r);
					          }

						}
						
						moves.setMovesZero();
						currMoves.setText("0");
						
						//the scores file must be re-read to check  for any 
						//score differences
						File scoreFile = new File("scores.data");
						try(FileInputStream fis = new FileInputStream(scoreFile)){
							System.out.println("Scoreboard file read.");
							
									int currentChar;
									int rowIter=0;
									int colIter=0;
									while((currentChar = fis.read()) != -1){ 
									
									scoreArr[rowIter][colIter]=(char)currentChar;
									
									colIter++;
									if(colIter==6){
										rowIter++;
										colIter=0;
									}
										
									}
							
							}
						catch(IOException q){
							System.out.println(q);
							System.out.println("Scoreboard file read failed.");
							}
						
						if(currentLevel < 12){
						bestScore.setText(Character.toString(scoreArr[currentLevel+1][3])+
								Character.toString(scoreArr[currentLevel+1][4]));
						
						//remove the last level
						gamePanel.remove(boards[currentLevel]);
					
						//set the next level
						gamePanel.add(boards[currentLevel+1]);
						}
						
						
						
						
					}
				
				
				}
			
				
			}

		}






	

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



}

//this class keeps track of the number of moves in a particular level
class moveKeeper{
	
	private int moves=0; 
	
	public void addMove(){
		moves++;
	}
	
	public int getMoves(){
		return moves;
	}
	
	public void setMovesZero(){
		moves=0;
	}
	
}

//this class checks to make sure that the level files are valid
class fileSetup{

	public static File[] setFiles(){

		File[] fileArray;

		fileArray = new File[12];

		fileArray[0] = new File("level1.data");
		fileArray[1] = new File("level2.data");
		fileArray[2] = new File("level3.data");
		fileArray[3] = new File("level4.data");
		fileArray[4] = new File("level5.data");
		fileArray[5] = new File("level6.data");
		fileArray[6] = new File("level7.data");
		fileArray[7] = new File("level8.data");
		fileArray[8] = new File("level9.data");
		fileArray[9] = new File("level10.data");
		fileArray[10] = new File("level11.data");
		fileArray[11] = new File("level12.data");

		for(int fileCount=0; fileCount < 12; fileCount++){
		try(FileInputStream fis = new FileInputStream(fileArray[fileCount])){
			System.out.println("Successful read");
			}
		catch(IOException e){
			System.out.println(e);
			System.out.println("Exiting game.");
			System.exit(0);
		}
		}

		return fileArray;
	}

}
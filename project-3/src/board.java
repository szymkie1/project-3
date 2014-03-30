/**************************
 * board.java
 * Written by Ryan Szymkiewicz for CS 342 Project 3
 * board.java extends JPanel and is used to contain the 12 game boards so that they can
 * easily be accessed in the gameGUI.java class
 ***************************/

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.*;

@SuppressWarnings("serial")
public class board extends JPanel{
	
	private int level=0;
	private int finished=0;
	
	//each level has a board.  set and get the level that this board is used with
	public void setLevel(int l){
		level=l;
	}
	
	public int getLevel(){
		return level;
	}

	//indicate if the board has been completed
	public void setFinished(){
		finished=1;
	}
	
	public int getFinished(){
		return finished;
	}


}
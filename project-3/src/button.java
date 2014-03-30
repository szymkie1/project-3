/**************************
 * button.java
 * Written by Ryan Szymkiewicz for CS 342 Project 3
 * button.java extends JButton to be used as the game buttons
 * this class will contain information about each game button that will 
 * be displayed on the game board
 ***************************/

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

import javax.swing.Timer;

@SuppressWarnings("serial")
// some swing elements are apparently deprecated. This
// suppresses warnings related to that-does not affect functionality
public class button extends JButton {

	
	int xCoord = -1;
     //set and get the row and column that the button will be used with
	public void setRow(int x) {
		xCoord = x;
	}

	int yCoord = -1;

	public void setColumn(int y) {
		yCoord = y;
	}

	public int getRow() {
		return xCoord;
	}

	public int getColumn() {
		return yCoord;
	}
	
	//set and get the letter that will be on the button
	 char letter;
	public void setLetter(char le){
		letter=le;
	}
	
	public char getLetter(){
		return letter;
	}
	
	//set and get if the button is part of a block.  -1 indicates that it is not
	int blockNum;
	public void setBlock(int b){
		blockNum=b;
	}
	
	public int getBlock(){
		return blockNum;
	}
	
	//helper getter and setters to use with displaying text on the buttons
	String buttonString;
	public void setButtonString(String s){
		buttonString=s;
	}
	
	public String getButtonString(){
		return buttonString;
	
	}
	}


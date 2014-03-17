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
	
	 char letter;
	public void setLetter(char le){
		letter=le;
	}
	
	public char getLetter(){
		return letter;
	}
	
	int blockNum;
	public void setBlock(int b){
		blockNum=b;
	}
	
	public int getBlock(){
		return blockNum;
	}
	
	String buttonString;
	public void setButtonString(String s){
		buttonString=s;
	}
	
	public String getButtonString(){
		return buttonString;
	
	}
	}


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class button extends JButton{
	
	int xLoc;
	int yLoc;
	char direction;
	char letter;
	
	public button(){
		
	}
	
	public void setX(int x){
		xLoc=x;
	}
	
	public int getX(){
		return xLoc;
	}
	
	public void setY(int y){
		yLoc=y;
	}
	
	public int getY(){
		return yLoc;
	}
	
	public void setDirection(char d){
		direction=d;
	}
	
	public char getDirection(){
		return direction;
	}
	
	public void setLetter(char l){
		letter=l;
	}
	
	public char getLetter(){
		return letter;
	}
	
	
	
	
	
	
	
}
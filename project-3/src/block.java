/**************************
 * block.java
 * Written by Ryan Szymkiewicz for CS 342 Project 3
 * This class contains information about the blocks for each level.
 * Inline comments will provide further details
 ***************************/


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class block{
	
	int row=0;
	int column=0;
	int width;
	int height;
	char direction;
	int size;
	int[] arrayRowPositions;
	int[] arrayColumnPositions;
	int rowSize;
	int columnSize;
	char letter;
	ArrayList<locations> locs;
	int rowIter=0;
	int columnIter=0;
	//declare variables
	
	//setter for row and column sizes(set to 6x6 by default...)
	public void setRowSize(int y){
		rowSize=y;
	}
	
	public void setColumnSize(int z){
		columnSize=z;
	}
	
	
	//set and get the row and column locations for each block
	public void setRow(int r){
		row=r;
	}

	public int getRow(){
		return row;
	}
	
	public void setColumn(int c){
		column=c;
	}
	
	public int getColumn(){
		return column;
	}
	
	//set and get the width and height of each block
	public void setWidth(int w){
		width=w;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setHeight(int h){
		height=h;
	}
	
	public int getHeight(){
		return height;
	}
	
	//set and get the direction that the block can move
	public void setDirection(char d){
		direction=d;
	}
	
	public char getDirection(){
		return direction;
	}
	
	//set and get size of block
	public void setSize(){
		size=width*height;
	}
	
	public int getSize(){
		return size;
	}
	
	//set and get the letter identifier that will be displayed on the block
	public void setLetter(char le){
		letter=le;
	}
	
	public char getLetter(){
		return letter;
	}
	
	
	
	//set array list of locations that each block will exist in
	public void setLocations(){
		locs = new ArrayList<locations>();//declare array list
		
		
		
          //one location for each increment of size		
		  for(int s=0; s<size; s++){
			  
		 
		
		 
			//call actual location xlass
			locations temp = new locations();
			
			 
			
			if(s==0){
				//set first location
				temp.setRow(row);
				temp.setColumn(column);
				rowIter++;
				columnIter++;
				
			}
			else{
				if(rowIter < height){
					temp.setRow(row + rowIter);
					rowIter++;
						}
				else{
					temp.setRow(row);
				}
				if(columnIter < width){
					temp.setColumn(column + columnIter);
					columnIter++;
				}
				else{
					temp.setColumn(column);
				}
				
				
			}
		 //set the other locations depending on the size
			locs.add(temp);
			//add the locations to the array list
		 
		  }
		
	}
	
	//return the array list
	public ArrayList<locations> getLocations(){
		return locs;
	}
	
}
	
	
	



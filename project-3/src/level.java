/**************************
 * level.java
 * Written by Ryan Szymkiewicz for CS 342 Project 3
 * This class contains information about each level.  It 
 * sets up a character array from the input file and also 
 * calls the functions to get blocks
 ***************************/

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class level{
	
	private int numLines=1;
	private int rows;
	private int columns;
	private char[][] inputArray;
	public ArrayList<block> blocks = new ArrayList<block>();
	private char[][] blockArray;
	
	
	//this method will get level information from the file
	public void initLevel(File currFile){
	
		//first read will check for the number of blocks
		try (FileInputStream fis = new FileInputStream(currFile)) {
			 
			int currChar;
			while ((currChar = fis.read()) != -1) {
				
				if((char)currChar == '\n'){
					numLines++;
				}
			}
			
			
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		inputArray = new char[numLines][20];
		
		
		//second read will take the input file and convert it
		//to a character array of input
		try (FileInputStream fis2 = new FileInputStream(currFile)) {
			 
			int currChar2;
			int currLine=0;
			int currSpace=0;
			while ((currChar2 = fis2.read()) != -1) {
				inputArray[currLine][currSpace]=(char)currChar2;
				if((char)currChar2 != '\n'){
					
					currSpace++;
				}
				else if((char)currChar2 == '\n'){
					
					currLine++;
					currSpace=0;
				}
				else{
					System.out.println("Problem translating file to array");
				}
				
			}
			
			
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		
		rows = Character.getNumericValue(inputArray[0][0]);
		columns = Character.getNumericValue(inputArray[0][3]);
		
	
	}
	
	//getters for the rows and columns
	public int getNumRows(){
		return rows;
	}
	
	public int getNumColumns(){
		return columns;
	}
	
	//get created input array
	public char[][] getInputArray(){
		return inputArray;
	}
	
	//get the blocks
	public ArrayList<block> getBlocks(){
		
		
		for(int i=1; i < numLines; i++){
			
			block temp = new block();
			temp.setRowSize(rows);//6
			temp.setColumnSize(columns);//6
			temp.setRow(Character.getNumericValue(inputArray[i][0]));//get row start
			temp.setColumn(Character.getNumericValue(inputArray[i][3]));//get column start
			temp.setWidth(Character.getNumericValue(inputArray[i][6]));//get width
			temp.setHeight(Character.getNumericValue(inputArray[i][9]));//get height
			temp.setDirection(inputArray[i][12]);//set direction
			temp.setSize();//set size
			if(i==1){
				temp.setLetter('Z');//set letter
			}
			else{
				temp.setLetter((char)(63 + i));
			}
			
			temp.setLocations();//set locations
			blocks.add(temp);//add block to list
			
		  }
		
		
		
		return blocks;
	}
	
	
	//this method will create a character array that will display the whole game
	//level with the blocks
	public char[][] getArrayWithBlocks(){
		blockArray = new char[6][6];
		
		for(int r=0; r < 6; r++){
			for(int c=0; c < 6; c++){
				blockArray[r][c]='.';//start by settig each position to empty
			}
		}
		
		int xLoc;
		int yLoc;
		char charSet;
		for(int a=0; a < blocks.size(); a++){
			for(int b = 0; b < blocks.get(a).getLocations().size() ; b++){
	
				xLoc=blocks.get(a).getLocations().get(b).getRow();
				yLoc=blocks.get(a).getLocations().get(b).getColumn();
				charSet=blocks.get(a).getLetter();//get blocks and their positions
				
				
				blockArray[xLoc][yLoc]=charSet;
	
			}
			
			
		}
		
		
		
		return blockArray;
	}
	
	
}
	
	
	
	
	
	

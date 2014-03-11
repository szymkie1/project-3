import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class level{
	
	private int numLines=1;
	private int rows=0;
	private int columns=0;
	private char[][] inputArray;
	
	
	
	public void initLevel(File currFile){
	
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
	
	
	public int getNumRows(){
		return rows;
	}
	
	public int getNumColumns(){
		return columns;
	}
	
	public char[][] getInputArray(){
		return inputArray;
	}
	
	
	
}
	
	
	
	
	
	

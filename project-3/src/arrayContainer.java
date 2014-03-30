/**************************
 * arrayContainer.java
 * Written by Ryan Szymkiewicz for CS 342 Project 3
 * This class will get and store the level character arrays
 * containing empty spaces and block locations before being
 * translated into the actual board.  Class contains getter
 * and setter for this purpose
 ***************************/

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.*;


public class arrayContainer{
	
	char[][] storeArray;
	
	public void setArray(char[][] temp){
		storeArray=temp;
	}
	
	public char[][] getArray(){
		return storeArray;
	}
	
	
}
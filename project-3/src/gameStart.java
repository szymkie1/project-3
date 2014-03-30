/**************************
 * gameStart.java
 * Written by Ryan Szymkiewicz for CS 342 Project 3
 * This class contains the main method that will start the program
 * also closes the game window on exit
 ***************************/
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.Timer;

public class gameStart{
	
	public static void main(String[] args){
		
		gameGUI gui = new gameGUI();
		gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
	
}
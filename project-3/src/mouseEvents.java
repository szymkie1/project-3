/**************************
 * mouseEvents.java
 * Written by Ryan Szymkiewicz for CS 342 Project 3
 * This class contains information for storing two mouse clicks and retrieving them
 ***************************/

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.*;

public class mouseEvents{
	
	static MouseEvent[] getEvents = new MouseEvent[2];//array of 2 clicks
	static int count=0;
	
	public static void setTwoEvents(MouseEvent e){
		
		//1st click,  set 1st click,  2nd to null
		if(count==0){
			getEvents[0]=e;
			getEvents[1]=null;
			count++;
		}
		else if(count==1){//set 2nd click
			getEvents[1]=e;
			count=0;
		}
		
		}
	//get those two events
	public static MouseEvent[] getTwoEvents(){
		return getEvents;
	}
	//get the click number
	public static int getCurrentClick(){
		return count;
	}
	


}
	
	
	
	

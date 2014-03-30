/**************************
 * locations.java
 * Written by Ryan Szymkiewicz for CS 342 Project 3
 * This class contains the locations occupied by each block
 * It contains getters and setters for the row and column positions
 ***************************/


class locations{
		
		int xLoc=0;
		int yLoc=0;
		public void setRow(int x){
			xLoc=(x-1);
		}
		public int getRow(){
			return xLoc;
		}
		public void setColumn(int y){
			yLoc=(y-1);
		}
		public int getColumn(){
			return yLoc;
		}
		}
		
		
	
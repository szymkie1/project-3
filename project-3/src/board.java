
public class board{
	
	public button[][] buttons = new button[6][6];
	
	public void setBoard(char[][] arrayBoard){
		
		for(int a=0; a < 6; a++){
			for(int b=0; b < 6; b++){
				
				buttons[a][b] = new button();
				buttons[a][b].setX(a);
				buttons[a][b].setY(b);
				buttons[a][b].setLetter(arrayBoard[a][b]);
				
			}
		}
		
		
		
	}
	
	public button[][] getBoard(){
		
		return buttons;
	}
	
	
}
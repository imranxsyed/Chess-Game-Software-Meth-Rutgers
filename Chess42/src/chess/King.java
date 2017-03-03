package chess;

public class King extends ChessPiece {
	
	public King(String color){
		if(color =="white"){
			this.color = color;
			x = 5;
			y = 8;
			location = "e8";
			name = "wK";
		}else{
			this.color = color;
			x = 5;
			y = 1;
			location = "e1";
			name = "bK";
		}
	}
	public boolean move(int toX, int toY){
	
		int newX = x - toX;
		int newY = y - toY;
		
		if(newX!=1 || newY!=1){
			return false;
		}
		x = toX;
		y = toY;
		return true;
	}
	
}

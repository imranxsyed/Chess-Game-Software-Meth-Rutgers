package chess;

public class Rook extends ChessPiece{

	public Rook(String color){
		if(color =="white"){
			this.color = color;
			x = 7;
			y = 0;
			location = "a1";
			name = "wR";
		}else{
			this.color = color;
			x = 0;
			y = 0;
			location = "a8";
			name = "bR";
		}
	}
	
	public boolean move(int toX, int toY){
		return false;
	}
}

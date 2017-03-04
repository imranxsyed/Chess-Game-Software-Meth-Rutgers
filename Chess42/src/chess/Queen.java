package chess;

public class Queen extends ChessPiece {

	
	public Queen(String color){
		if(color =="white"){
			this.color = color;
			x = 7;
			y = 3;
			location = "d1";
			name = " wQ";
		}else{
			this.color = color;
			x = 0;
			y = 3;
			location = "d8";
			name = "bQ";
		}
	}
	public boolean move(int from, int to) {
		return false;
	}

	
}

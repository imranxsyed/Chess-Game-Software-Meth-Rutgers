package chess;

public class Queen extends ChessPiece {

	
	public Queen(String color, int x, int y){
		type = "Queen";
		if(color =="white"){
			this.color = color;
			this.x = x;
			this.y = y;
			location = "d1";
			name = "wQ";
		}else{
			this.color = color;
			this.x = x;
			this.y = y;
			location = "d8";
			name = "bQ";
		}
	}
	public boolean move(ChessPiece[][] board, int from, int to) {
		return false;
	}

	public void check(ChessPiece[][] board){
		return;
	}
	
}

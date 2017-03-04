package chess;

public class King extends ChessPiece {
	
	public King(String color, int x, int y){
		type = "King";
		if(color =="white"){
			this.color = color;
			this.x = x;
			this.y = y;
			location = "e1";
			name = "wK";
		}else{
			this.color = color;
			this.x = x;
			this.y = y;
			location = "e8";
			name = "bK";
		}
	}
	public  boolean move(ChessPiece[][] board, int toX, int toY){
	
		int newX = Math.abs(this.x - toX);
		int newY = Math.abs(this.y - toY);
		System.out.println(newY);
		if(newX>1 || newY>1){
			return false;
		}else if(this.y ==toY && this.x == toX){
			System.out.println("Error");
			return false;
		}else{
			//checks if the piece there it its own piece
			if(board[toX][toY]!=null &&board[toX][toY].getColor()==this.getColor()){
				System.out.println("your piece is there");
				return false;
			}
			return true;
		}
	}
	
	public void check(ChessPiece[][] board){
		return;
	}
	
}

package chess;

public abstract class ChessPiece {
	
	String color;
	String location;
	String name;
	int x,y;
	public boolean move(int from, int to){
		return false;
	}
	
	public void printLocation(){
		System.out.print(location);
	}
	public void getName(){
		System.out.print(" "+name);
	}
	public void movePiece(ChessPiece[][] board, String to){
		
		int tx = to.charAt(0)-96;
		int ty = to.charAt(1)-48;
		
		//checks for out of boundary entry
		if(tx>8 ||tx< 1 || ty>8 || ty<0){
			System.out.println("Error");
			return;
		}
		
		if(move( tx, ty)){
			board[tx][ty] = this;
		}
		return;
		
	}
	
}

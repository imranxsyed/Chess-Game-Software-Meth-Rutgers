package chess;

public abstract class ChessPiece {
	
	public String color;
	
	//set by the x and y axis i.e. e5
	public String location;
	
	//name is set by the lower case letter of colored followed by type of piece i.e. "Black King" = bK
	public String name;
	
	public int x,y;
	
	/**
	 * 
	 * @param x is the rows of the board 1 - 8
	 * @param y is the columns of the board a - h
	 */
	
	public String getColor(){
		return color;
	}
	public abstract boolean move(ChessPiece[][]board, int from, int to);
	
	public void getLocation(){
		System.out.print(location);
	}
	public void getName(){
		System.out.print(name+ " ");
	}
	/*
	 * to comes in the form of (xy) i.e. e5
	 */
	public boolean movePiece(ChessPiece[][] board, String to){
		//column
		int ty = to.charAt(0)-97;
		//row
		int tx = to.charAt(1)-49;
		
		//board is upside down to how out board is made so it converts the value given by user
		//to a usable value for our array
		tx =  (tx-7)*(-1);
		
		//checks for out of boundary entry
		if(tx>7 ||tx< 0 || ty>7 || ty<0){
			System.out.println("Error: Out of bound");
			return false;
		}
	
		
		ChessPiece[][] tempBoard = board;
		//if move is legal this will changes the current location of the piece
		if(this.move(tempBoard, tx,ty)){
			this.y = ty;
			this.x = tx;
			this.location = to;
			return true;
		}
		
		return false;
		
	}
	
}

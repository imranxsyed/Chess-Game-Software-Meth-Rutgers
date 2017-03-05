package chess;

public abstract class ChessPiece {
	
	public String color;
	public static int checkCount = 0;
	//set by the x and y axis i.e. e5
	public String location;
	
	//name is set by the lower case letter of colored followed by type of piece i.e. "Black King" = bK
	public String name;
	public String type;
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
	public abstract boolean check(ChessPiece[][]board);
	public void getLocation(){
		System.out.print(location);
	}
	public void getName(){
		System.out.print(name+ " ");
	}
	public String getType(){
		return type;
	}
	/*
	 * to comes in the form of (xy) i.e. e5
	 */
	public void movePiece(ChessPiece[][] board, String to){
		//column
		if(to.length()>2){
			System.out.println("Error: invalid input, syntatic error");
			return;
		}
		int ty = to.charAt(0)-97;
		//row
		int tx = to.charAt(1)-49;
		
		//board is upside down to how out board is made so it converts the value given by user
		//to a usable value for our array
		tx =  (tx-7)*(-1);
		
		//checks for out of boundary entry
		if(tx>7 ||tx< 0 || ty>7 || ty<0){
			System.out.println("Error: Out of bound");
			return;
		}
	
		
		ChessPiece[][] tempBoard = board;
		//if move is legal this will changes the current location of the piece
		if(this.move(tempBoard, tx,ty)){
			int oldX = this.x;
			int oldY = this.y;
			/*ChessPiece isKing = board[tx][ty];
			if(isKing!=null && isKing.getType()=="King" &&isKing.getColor()!=this.getColor()){
				System.out.println(this.getColor() + " is the Winner");
			}*/
			this.y = ty;
			this.x = tx;
			this.location = to;
			board[oldX][oldY]=null;
			board[tx][ty]= this;
			
			if(this.check(board)){
				System.out.println("Check");
			}
			
			return;
		}
		//checks if King is in check
		System.out.println("Your move is not valid");
		return;
		
	}
	
}

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
	
	public boolean check(ChessPiece[][] board){
		
		int tempX = this.x;
		int tempY = this.y;
		
		
		//checks left up 
		tempX++;
		tempY--;
		if(tempY>-1 && tempX<8){
			ChessPiece tempPiece = board[tempX][tempY];
			if(tempPiece!=null && tempPiece.getType()=="King" && this.getColor()!=tempPiece.getColor()){ return true;}
		}
		
		
		//checks left
		tempY = this.y -1;
		tempX = this.x;
		if(tempY>-1){
			ChessPiece tempPiece = board[tempX][tempY];
			if(tempPiece!=null && tempPiece.getType()=="King" && this.getColor()!=tempPiece.getColor()){ return true;}
		}
		
		//checks left down
		tempY = this.y -1;
		tempX = this.x -1;
		if(tempY>-1 && tempX>-1){
			ChessPiece tempPiece = board[tempX][tempY];
			if(tempPiece!=null && tempPiece.getType()=="King" && this.getColor()!=tempPiece.getColor()){ return true;}
		}
		
		
		
		//checks down
		tempY = this.y;
		tempX = this.x -1;
		if(tempX>-1){
			ChessPiece tempPiece = board[tempX][tempY];
			if(tempPiece!=null && tempPiece.getType()=="King" && this.getColor()!=tempPiece.getColor()){ return true;}
		}
		
		
		//checks down right
		tempX = this.x -1;
		tempY = this.y +1;
		if(tempY<8 && tempX>-1){
			ChessPiece tempPiece = board[tempX][tempY];
			if(tempPiece!=null && tempPiece.getType()=="King" && this.getColor()!=tempPiece.getColor()){ return true;}
		}
		
		//checks right
		tempX = this.x;
		tempY = this.y+1;
		if(tempY<8){
			ChessPiece tempPiece = board[tempX][tempY];
			if(tempPiece!=null && tempPiece.getType()=="King" && this.getColor()!=tempPiece.getColor()){ return true;}
		}
		
		
		//checks up right up
		tempX = this.x+1;
		tempY = this.y +1;
		if(tempX<8 && tempY<8){
			ChessPiece tempPiece = board[tempX][tempY];
			if(tempPiece!=null && tempPiece.getType()=="King" && this.getColor()!=tempPiece.getColor()){ return true;}
		}
		
		//checks up
		tempY = this.y;
		tempX = this.x+1;
		if(tempX<8){
			ChessPiece tempPiece = board[tempX][tempY];
			if(tempPiece!=null && tempPiece.getType()=="King" && this.getColor()!=tempPiece.getColor()){ return true;}
		}
		return false;
	}
	
}

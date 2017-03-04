package chess;

public class Rook extends ChessPiece{
	
	public Rook(String color, int x, int y){
		if(color =="white"){
			this.color = color;
			this.x = x;
			this.y = y;
			if(y == 0){
				location = "a1";
			}else{
				location = "h1";
			}
			name = "wR";
		}else{
			this.color = color;
			this.x = x;
			this.y = y;
			if(y ==0){
				location = "a8";
			}else{
				location = "h8";
			}
			name = "bR";
		}
	}
	
	public boolean move(ChessPiece[][] board, int toX, int toY){
		
		int newX = Math.abs(this.x - toX);
		int newY = Math.abs(this.y - toY);
		
		if((newX == 0 || newY ==0) &&newX!=newY){
			if(newX==0){
				//movement across columns
				int temp = this.y;
				if(temp>toY){
					while(board[toX][temp]==null && temp!=toY){
						temp--;
					}
				}else{
					while(board[toX][temp]==null && temp!=toY){
						temp++;
					}
				}
				
				if(temp!=0){
					System.out.println("these is a abustructive stucture in path, invalid move");
					return false;
				}
				if(board[toX][toY]!=null && board[toX][toY].getColor()==this.getColor()){
					System.out.println("your piece is there");
					return false;
				}
				return true;
				
			}else{
				//movement across rows
				int temp = this.x;
				if(temp>toX){
					while(board[temp][toY]==null && temp!=toX){
						temp--;
					}
				}else{
					while(board[temp][toY]==null && temp!=toX){
						temp++;
					}
				}
				
				
				if(temp!=0){
					System.out.println("these is a abustructive stucture in path, invalid move");
					return false;
				}
				if(board[toX][toY]!=null && board[toX][toY].getColor()==this.getColor()){
					System.out.println("your piece is there");
					return false;
				}
				return true;
				
			}
		}else{
			System.out.println("invalid move");
			return false;
		}
	}
}

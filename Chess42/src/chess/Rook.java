package chess;

public class Rook extends ChessPiece{
	
	public Rook(String color, int x, int y){
		type = "Rook";
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
				//move left
				if(temp>toY){
					temp--;
					if(temp>-1){
						while(board[this.x][temp]==null && temp!=toY){
							temp--;
						}
					}
				}else{
					temp++;
					if(temp<8){
						while(board[this.x][temp]==null && temp!=toY){
							temp++;
						}
					}
				}
				if(temp!=toY){
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
					temp--;
					if(temp>-1){
						while(board[temp][this.y]==null && temp!=toX){
							temp--;
						}
					}
				}else{
					temp++;
					if(temp<8){
						while(board[temp][this.y]==null && temp!=toX){
							temp++;
						}
					}
				}
				
				
				if(temp!=toX){
					System.out.println("these is a abustructive stucture in path, invalid move");
					return false;
				}
				if(board[toX][toY]!=null && board[toX][toY].getColor()==this.getColor()){
					System.out.println("your piece is there");
					return false;
				}
				this.hasMoved = true;
				return true;
				
			}
		}else{
			
			return false;
		}
	}
	
	public boolean check(ChessPiece[][] board){
		int tempX = this.x-1;
		int tempY = this.y-1;
		
		//looks to the up for king 0-->8
		while(tempX>-1 && board[tempX][this.y]==null){
			tempX--;
		}
		if(tempX!=-1 && board[tempX][this.y].getType()=="King"){
			if(board[tempX][this.y]!=null && this.getColor() != board[tempX][this.y].getColor()){ return true;}
		}
		
		tempX = this.x+1;
		//checks to the down 8-->1
		while(tempX< 8 && board[tempX][this.y]==null){
			tempX++;
		}
		if(tempX!=8 && board[tempX][this.y].getType()=="King"){
			if(board[tempX][this.y]!=null && this.getColor() != board[tempX][this.y].getColor()){ return true;}
		}
		//checks left 
		while(tempY > -1 && board[this.x][tempY]==null){
			tempY--;
		}
		
		if(tempY!=-1 && board[this.x][tempY].getType()=="King"){
			if(this.getColor() != board[this.x][tempY].getColor()){return true;}
		}
		
		tempY = this.y+1;
		//checks right
		while(tempY< 8 && board[this.x][tempY]==null){
			tempY++;
		}
		
		if(tempY!=8 && board[this.x][tempY].getType() == "King"){
			if(this.getColor() != board[this.x][tempY].getColor()){ return true;}
		}
		return false;
	}
}

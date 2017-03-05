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
	public boolean move(ChessPiece[][] board, int toX, int toY) {
		
		int newX = Math.abs(this.x - toX);
		int newY = Math.abs(this.y - toY);
		
		if(newX==0 && newY==0){
			return false;
		}
		if(newX ==0 || newY==0){
			if(newX==0){
				//movement across columns
				int temp = this.y;
				//move left
				if(temp>toY){
					
					temp--;
					if(temp>-1){
						while(board[this.x][temp]==null && temp!=toY){temp--;}
					}
					
				}else{
					
					temp++;
					if(temp<8){
						while(board[this.x][temp]==null && temp!=toY){temp++;}
					}
					
				}
				
				if(temp!=toY){
					System.out.println("there is a abustructive stucture in path, invalid move");
					return false;
				}
				
				if(board[toX][toY]!=null && board[toX][toY].getColor()==this.getColor()){
					System.out.println("your piece is there");
					return false;
				}
				this.hasMoved = true;
				return true;
				
			}else{
				//movement across rows
				int temp = this.x;
				//move up
				if(temp>toX){
					temp--;
					if(temp>-1){
						while(board[temp][this.y]==null && temp!=toX){temp--;}
					}
				}else{
					
					temp++;
					if(temp<8){
						while(board[temp][this.y]==null && temp!=toX){temp++;}
					}
				
				}
				
				if(temp!=toX){
					System.out.println("these is a abustructive stucture in path");
					return false;
				}
				
				if(board[toX][toY]!=null && board[toX][toY].getColor()==this.getColor()){
					System.out.println("your piece is there");
					return false;
				}
				
				return true;
				
			}
		}else if(newY==newX){
			int tempX = this.x;
			int tempY = this.y;
			if(tempX>toX){
				if(tempY>toY){
					//moving up and left
					tempY--;
					tempX--;
					while(board[tempX][tempY]==null && tempY!=toY){
						tempX--;
						tempY--;
					}
				}else{
					tempY++;
					tempX--;
					while(board[tempX][tempY]==null && tempY!=toY){
						tempX--;
						tempY++;
					}
					//moving up and right
				}
			}else{
				if(tempY>toY){
					//moving down and left
					tempY--;
					tempX++;
					while(board[tempX][tempY]==null && tempY!=toY){
						tempX++;
						tempY--;
					}
				}else{
					//moving down and right 
					tempY++;
					tempX++;
					while(board[tempX][tempY]==null && tempY!=toY){
						tempX++;
						tempY++;
					}
				}	
			}
			if(tempY!=toY){
				System.out.println("these is a abustructive stucture in path");
				return false;
			}
			if(board[toX][toY]!=null && board[toX][toY].getColor()==this.getColor()){
				System.out.println("your piece is there");
				return false;
			}
			return true;
		}else{
			return false;
		}
	}

	public boolean check(ChessPiece[][] board){
		int tempY = this.y;
		int tempX = this.x;
		ChessPiece tempPiece;
		//check (down, left)
		tempY--;
		tempX++;
		while(tempY>-1 && tempX<8 &&board[tempX][tempY]==null){
			tempY--;
			tempX++;
		}
		if(tempY>=0 && tempX<=7){
			tempPiece= board[tempX][tempY];
			if(tempPiece.getType()=="King"&&tempPiece.getColor()!=this.getColor()){
				return true;
			}
		}
		
		//check down
		tempY = this.y;
		tempX = this.x+1;
		while(tempX<8 && board[tempX][this.y]==null){
			tempX++;
		}
		if(tempX<=7){
			tempPiece= board[tempX][this.y];
			if(tempPiece.getType()=="King"&&tempPiece.getColor()!=this.getColor()){
				return true;
			}
		}
		
		
		//check (down, right)
		tempX = this.x +1;
		tempY = this.y +1;
		while(tempY<8 && tempX<8 &&board[tempX][tempY]==null){
			tempY++;
			tempX++;
		}
		if(tempY<8 && tempX<8){
			tempPiece= board[tempX][tempY];
			if(tempPiece.getType()=="King"&&tempPiece.getColor()!=this.getColor()){
				return true;
			}
		}
		
		
		//check (right)
		tempX = this.x;
		tempY = this.y+1;
		while(tempY<8 &&board[this.x][tempY]==null){tempY++;}
		if(tempY<8){
			tempPiece= board[this.x][tempY];
			if(tempPiece.getType()=="King"&&tempPiece.getColor()!=this.getColor()){
				return true;
			}
		}
		
		//check (up, right)
		tempX = this.x -1;
		tempY = this.y +1;
		while(tempY<8 && tempX>-1 &&board[tempX][tempY]==null){
			tempY++;
			tempX--;
		}
		if(tempY<8 && tempX>-1){
			tempPiece= board[tempX][tempY];
			if(tempPiece.getType()=="King"&&tempPiece.getColor()!=this.getColor()){
				return true;
			}
		}
		
		
		//check up
		tempY = this.y;
		tempX = this.x-1;
		while(tempX>-1 && board[tempX][this.y]==null){
			tempX--;
		}
		if(tempX>-1){
			tempPiece= board[tempX][this.y];
			if(tempPiece.getType()=="King"&&tempPiece.getColor()!=this.getColor()){
				return true;
			}
		}
		
		
		//check (up, left)
		tempX = this.x -1;
		tempY = this.y -1;
		while(tempY>-1 && tempX>-1 &&board[tempX][tempY]==null){
			tempY--;
			tempX--;
		}
		if(tempY>-1 && tempX>-1){
		tempPiece= board[tempX][tempY];
			if(tempPiece.getType()=="King"&&tempPiece.getColor()!=this.getColor()){
				return true;
			}
		}
		
		
		
		//check left
		tempX = this.x;
		tempY = this.y-1;
		while(tempY>-1 &&board[this.x][tempY]==null){tempY--;}
		if(tempY>-1){
			tempPiece= board[this.x][tempY];
			if(tempPiece.getType()=="King"&&tempPiece.getColor()!=this.getColor()){
				return true;
			}
		}
		
		return false;
	}
	
}

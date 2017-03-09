package chess;

public class Bishop extends ChessPiece {
	
	
	public Bishop(String color, int x , int y){
		
		
		this.color = color;
		this.x = x;
		this.y = y;
		this.type = "Bishop";
		
		this.name = color.equalsIgnoreCase("White")? "wB" : "bB"; // setting the name
		this.location = column[y]+rows[Math.abs((rows.length-1)-x)]; // setting the location
	}
	
	
	public boolean move(ChessPiece[][] board, int x, int y) {
		
		
		int differenceX = Math.abs(this.x - x); //difference in cordinate x
		int differenceY = Math.abs(this.y- y); // difference in cordinate y
		
		if (differenceX != differenceY){
			
			System.out.println("Illegal Move, try again..");
			return false;
		}
		
		boolean upRight =false, upLeft = false, downRight = false, downLeft = false;
		
		upRight = x < this.x && y >this.y ? true : false; // going right up
		upLeft = x < this.x && y < this.y? true : false; // goint left up
		
		downRight =  x> this.x && y >this.y ? true : false; // going downleft
		downLeft =  x > this.x && y <this.y; // goint downright
		
		
		if (upRight){
			
			directionsSort condition = (cordinatex, cordinatey, finalx, finaly) -> cordinatex >= finalx+1 && cordinatey <=finaly-1 ;
			
			return filter(board, this.x-1, this.y+1, "-+", condition, x, y);
				
		}
		else if (upLeft){
			
			
			
			
			directionsSort condition = (cordinatex, cordinatey, finalx, finaly) -> cordinatex >= finalx+1 && cordinatey >=finaly+1 ;
			
			return filter(board, this.x-1, this.y-1, "--", condition, x, y);
		
		}
		
		else if (downRight){
			
			directionsSort condition = (cordinatex, cordinatey, finalx, finaly) -> cordinatex <= finalx-1 && cordinatey <=finaly-1 ;
			
			return filter(board, this.x+1, this.y+1, "++", condition, x, y);
			
		}
		
		else if(downLeft){
			
			
			directionsSort condition = (cordinatex, cordinatey, finalx, finaly) -> cordinatex <= finalx-1 && cordinatey >=finaly+1 ;
			
			return filter(board, this.x+1, this.y-1, "+-", condition, x, y);
			
		
		}
			
			System.out.println("Illegal move, try again.");
			return false;
			
		
		
		
		
		
		
	}
	
	public boolean check(ChessPiece[][] board) {
		
		boolean upRight = false, upLeft = false , downRight = false, downLeft = false;
		int xpath,ypath;
		
		
		
		
		upLeft = this.x >0 && this.y>0 ? true: false;
		downRight = this.x< 7 && this.y <7? true : false;
		
		upRight = this.x >0 && this.y<7 ? true :false;
		downLeft = this.x <7 && this.y >0 ;
		
		if (upLeft){
			
			xpath = this.x-1;
			ypath = this.y-1;
			
			while (xpath >= 0 && ypath >= 0){
				
				if (board[xpath][ypath]== null){}
				
				else if (!board[xpath][ypath].type.equalsIgnoreCase("king")){  break;}
				
				else if (!board[xpath][ypath].color.equalsIgnoreCase(this.color)){ return true;}
					
				xpath --;
				ypath--;
			}
				
		}
		
		if (downRight){
			
			xpath = this.x+1;
			ypath = this.y+1;
			
			
			while (xpath <= 7 && ypath <= 7){
				
				if (board[xpath][ypath]== null){}
				
				else if (!board[xpath][ypath].type.equalsIgnoreCase("king")){ break;}
				
				else if (!board[xpath][ypath].color.equalsIgnoreCase(this.color)){ return  true; }
					
				xpath ++;
				ypath++;
			}
			
		}
		
		if (upRight){
			
			xpath = this.x-1;
			ypath = this.y+1;
			
			while(xpath >=0 && ypath <=7){
				
				if (board[xpath][ypath]== null){}
				
				else if (!board[xpath][ypath].type.equalsIgnoreCase("king")){  break;}
				
				else if (!board[xpath][ypath].color.equalsIgnoreCase(this.color)){ return true;}
				
				xpath --;
				ypath++;
			}
			
		}
		
		 if (downLeft){
			
			
			xpath = this.x+1;
			ypath = this.y-1;
			
			while(xpath <=7 && ypath >=0){
				
				if (board[xpath][ypath]== null){}
				
				else if (!board[xpath][ypath].type.equalsIgnoreCase("king")){  break;}
				
				else if (!board[xpath][ypath].color.equalsIgnoreCase(this.color)){ return true;}
				
				xpath ++;
				ypath--;
				
			}
		}
		
		return false;
		
	}
	
	
	public boolean filter(ChessPiece[][] board, int pathX , int pathY , String sings, directionsSort function, int x, int y){
		
		
		
		
		
		while (function.apply(pathX, pathY, x, y)){
			
			
				if (board[pathX][pathY] !=null){
				
				System.out.println("Illegal move, try again.");
				return false;
			}
				pathX = sings.charAt(0) == '+'? pathX +1 : pathX-1;
				pathY = sings.charAt(1) == '+'? pathY+1 : pathY-1;
		}
		
		 return  (board[x][y] == null) 
				 || (board[x][y]!= null && (!board[x][y].color.equalsIgnoreCase(this.color)));
	}
}

interface directionsSort{
	
	
	boolean apply(int x , int y, int fx , int fy);
}

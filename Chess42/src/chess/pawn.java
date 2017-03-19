package chess;

public class pawn extends ChessPiece {
	
	String[] column = {"a","b","c","d","e","f","g","h"};
	String[] rows = {"1","2","3","4","5","6","7","8"};
	 
	
	public pawn(String color, int x, int y){
		this.type = "Pawn";
		this.color = color;
		this.x = x;
		this.y = y;
		
			name = color.equals("white")? "wP": "bP";
			location = color.equals("white")? column[y]+2 : column[y]+ 7;
	}
	
	
	
	
	public boolean move(ChessPiece[][] board, int x, int y) {
		
		boolean oneStepAhead,twoStepAhead, diagonalStep;
		oneStepAhead = false; twoStepAhead = false; diagonalStep = false;
		int cordinateX = Math.abs(this.x - x); // new cooridates for x 
		int codinateY = Math.abs(this.y- y);	//new coorditaes for y
		
		if ( codinateY == 0){
			
			switch(cordinateX){
			
			case 1: oneStepAhead = true; break;
			case 2: twoStepAhead = true; break;
			
			}
			
		}
		else if (codinateY == 1 && cordinateX == 1){
			
			diagonalStep = true;
			
		}else{ // meaning no legal cordintes are found
			
			
			
			return false;
		}
		
		//checked so far which of three kinds will be using in this step
		
		boolean output = false;
		
		if (oneStepAhead){ // if the coordiates matches with one step ahead
			
			output = board[x][y] == null ; //checking if the square ahead of it is empty
				
			this.promotion =  (this.color.equals("black") && y == 7);  // checking if its black and reaches all the to the white territoyr
			
			this.promotion =  (this.color.equals("white") && x == 0); // checking if its white and reaches all the to the black territory		
						
		}
		else if (twoStepAhead){ // if the coordiates matches with two step ahead
			
			output = (this.hasMoved == false) &&	 (board[x][y]== null); //all condition matches
			el_pessant = true;
				
		}
		else if (diagonalStep){
			
			
			
				if ((this.color.equalsIgnoreCase("black")&& this.x == 4)|| (this.color.equalsIgnoreCase("white")&& this.x == 3)){//check for el_pessant
					
						output = ( board[x][y] ==null ) && ((board[this.x][y] != null)&& (board[this.x][y].type.equalsIgnoreCase("Pawn")
						&& (!board[this.x][y].color.equalsIgnoreCase(this.color))&& (board[this.x][y].el_pessant== true)) );
			
								if (output){
									
									board[this.x][y] = null; // el_passant removing that manually since the movePiece method will not do it for you
								}
			
				} if(!output){ //if el_pessant is not true
					
					output = (board[x][y] !=null ) && (!(board[x][y].color.equalsIgnoreCase(this.color)));
				}
			this.promotion =  (this.color.equals("black") && x == 7);  // checking if its black and reaches all the to the white territoyr
			
			if (!this.promotion){
			
				this.promotion =  (this.color.equals("white") && x == 0); // checking if its white and reaches all the to the black territory	
			
			}
			/**
			 * 
			 */
			//might need to do the swap here on the spot
		}
		
		
		
		if (output){ // if it will move to the new location, we need to do the follwing
			
			this.hasMoved = true; // make sure we make its first moved to true
			
		}else{
			
		}
		
		
		return output;
	}
	
	
	public boolean check(ChessPiece[][] board) {
		
		
		int xCordinate = this.x;
		int yCordinate = this.y;
		
		if (yCordinate <7 ) {
			
			 /**
			  * checking if the posiible target for king is not null
			  * it is king
			  * it is the opposite color
			  */
			
			if (this.color.equalsIgnoreCase("White") && xCordinate >0){ // checking for white side
			
				if (board[xCordinate -1][yCordinate +1] != null && board[xCordinate -1][yCordinate +1].type.equalsIgnoreCase("King") &&
					(!board[xCordinate -1][yCordinate +1].color.equalsIgnoreCase(this.color))){
				
					return true;
			}
				
			}else if (this.color.equalsIgnoreCase("black")&& xCordinate <7){ // checking for black side
				
				if (board[xCordinate +1][yCordinate +1] != null && board[xCordinate +1][yCordinate +1].type.equalsIgnoreCase("King") &&
						(!board[xCordinate +1][yCordinate +1].color.equalsIgnoreCase(this.color))){
					
						return true;
				}
				
			}
		}
		
		
		if (yCordinate > 0) {
			
			 /**
			  * checking if the posiible target for king is not null
			  * it is king
			  * it is the opposite color
			  */

			
			if (this.color.equalsIgnoreCase("White") && xCordinate <0){
							
				
				if (board[xCordinate -1][yCordinate -1] != null && board[xCordinate -1][yCordinate -1].type.equalsIgnoreCase("King") &&
					(!board[xCordinate -1][yCordinate -1].color.equalsIgnoreCase(this.color))){
				
					return true;
				
				}
			}
			else if (this.color.equalsIgnoreCase("black")&& yCordinate <7){
				
				if (board[xCordinate +1][yCordinate -1] != null && board[xCordinate +1][yCordinate -1].type.equalsIgnoreCase("King") &&
						(!board[xCordinate +1][yCordinate -1].color.equalsIgnoreCase(this.color))){
					
						return true;
			}
			
		}
		
	}
		return false;

	}
}
	


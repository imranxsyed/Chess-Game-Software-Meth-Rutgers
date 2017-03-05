package chess;

/**
 * (y, x)
 * 
 *  (0,0)bR (0,1)bN (0,2)bB (0,3)bQ (0,4)bK (0,5)bN (0,6)bN (0,7)bR 8
 *  (1,0)bp (1,1)bp (1,2)bp (1,3)bp (1,4)bp (1,5)bp (1,6)bp (1,7)bp 7
 *  (2,0)   (2,1)   (2,2)   (2,3)   (2,4)   (2,5)   (2,6)   (2,7)   6
 *  (3,0)   (3,1)   (3,2)   (3,3)   (3,4)   (3,5)   (3,6)   (3,7)   5
 *  (4,0)   (4,1)   (4,2)   (4,3)   (4,4)   (4,5)   (4,6)   (4,7)   4
 *  (5,0)   (5,1)   (5,2)   (5,3)   (5,4)   (5,5)   (5,6)   (5,7)   3
 *  (6,0)wp (6,1)wp (6,2)wp (6,3)wp (6,4)wp (6,5)wp (6,6)wp (6,7)wp 2
 *  (7,0)wR (7,1)wN (7,2)wB (7,3)wQ (7,4)wK (7,5)wB (7,6)wN (7,7)wR 1
 *      a      b       c       d       e       f       g       h
 * 
 *
 */


public class Chess {
		
		static ChessPiece whiteKing;
		static ChessPiece blackKing;
	
		public static void main(String[]args){
			
			
			ChessPiece[][] board = createTable(); 
			printTable(board);
			
			//example of projessor using the 
			ChessPiece temp = board[0][3];
			
			
			temp = board[7][4];
			temp.movePiece(board, "f1");
			printTable(board);
			CheckMate(whiteKing, board);
			
			//updates the board
			
			
		}
		public static void CheckMate(ChessPiece king, ChessPiece[][] board){
			
			int tempX = king.x;
			int tempY = king.y;
			int checkCount = 0;
			ChessPiece tempPiece;
			ChessPiece[][] tempBoard = board;
			tempX++;
			tempY--;
			if(tempY>-1 && tempX<8){
				ChessPiece tempKing = board[tempX][tempY];
				tempBoard[tempX][tempY] = tempKing;
				tempBoard[king.x][king.y] = null;
				for(int i=0; i<board.length; i++){
					for(int j=0; j<board.length; j++){
						tempPiece = board[i][j];
						if(tempPiece!=null){
							if(tempPiece.check(tempBoard)){
								checkCount++;
								break;
							}
						}
					}
				}
				
			}else{
				checkCount++;
			}
			
			
			//checks left
			tempX = king.x;
			tempY = king.y -1;
			if(tempY>-1){
				ChessPiece tempKing = board[tempX][tempY];
				tempBoard[tempX][tempY] = tempKing;
				tempBoard[king.x][king.y] = null;
				for(int i=0; i<board.length; i++){
					for(int j=0; j<board.length; j++){
						tempPiece = board[i][j];
						if(tempPiece!=null){
							if(tempPiece.check(tempBoard)){
								checkCount++;
								break;
							}
						}
					}
				}
			}else{
				checkCount++;
			}
			
			//checks left down
			tempY = king.y -1;
			tempX = king.x -1;
			if(tempY>-1 && tempX>-1){
				ChessPiece tempKing = board[tempX][tempY];
				tempBoard[tempX][tempY] = tempKing;
				tempBoard[king.x][king.y] = null;
				for(int i=0; i<board.length; i++){
					for(int j=0; j<board.length; j++){
						tempPiece = board[i][j];
						if(tempPiece!=null){
							if(tempPiece.check(tempBoard)){
								checkCount++;
								break;
							}
						}
					}
				}
			}else{
				checkCount++;
			}
			
			
			
			//checks down
			tempX = king.x -1;
			if(tempX>-1){
				ChessPiece tempKing = board[tempX][tempY];
				tempBoard[tempX][tempY] = tempKing;
				tempBoard[king.x][king.y] = null;
				for(int i=0; i<board.length; i++){
					for(int j=0; j<board.length; j++){
						tempPiece = board[i][j];
						if(tempPiece!=null){
							if(tempPiece.check(tempBoard)){
								checkCount++;
								break;
							}
						}
					}
				}
			}else{
				checkCount++;
			}
			
			
			//checks down right
			tempX = king.x -1;
			tempY = king.y +1;
			if(tempY<8 && tempX>-1){
				ChessPiece tempKing = board[tempX][tempY];
				tempBoard[tempX][tempY] = tempKing;
				tempBoard[king.x][king.y] = null;
				for(int i=0; i<board.length; i++){
					for(int j=0; j<board.length; j++){
						tempPiece = board[i][j];
						if(tempPiece!=null){
							if(tempPiece.check(tempBoard)){
								checkCount++;
								break;
							}
						}
					}
				}
			}else{
				checkCount++;
			}
			
			//checks right
			tempY = king.y+1;
			if(tempY<8){
				ChessPiece tempKing = board[tempX][tempY];
				tempBoard[tempX][tempY] = tempKing;
				tempBoard[king.x][king.y] = null;
				for(int i=0; i<board.length; i++){
					for(int j=0; j<board.length; j++){
						tempPiece = board[i][j];
						if(tempPiece!=null){
							if(tempPiece.check(tempBoard)){
								checkCount++;
								break;
							}
						}
					}
				}
			}else{
				checkCount++;
			}
			
			
			//checks up right up
			tempX = king.x+1;
			tempY = king.y +1;
			if(tempX<8 && tempY<8){
				ChessPiece tempKing = board[tempX][tempY];
				tempBoard[tempX][tempY] = tempKing;
				tempBoard[king.x][king.y] = null;
				for(int i=0; i<board.length; i++){
					for(int j=0; j<board.length; j++){
						tempPiece = board[i][j];
						if(tempPiece!=null){
							if(tempPiece.check(tempBoard)){
								checkCount++;
								break;
							}
						}
					}
				}
			}else{
				checkCount++;
			}
			
			//checks up
			tempX = king.x+1;
			if(tempX<8){
				ChessPiece tempKing = board[tempX][tempY];
				tempBoard[tempX][tempY] = tempKing;
				tempBoard[king.x][king.y] = null;
				for(int i=0; i<board.length; i++){
					for(int j=0; j<board.length; j++){
						tempPiece = board[i][j];
						if(tempPiece!=null){
							if(tempPiece.check(tempBoard)){
								checkCount++;
								break;
							}
						}
					}
				}
			}else{
				checkCount++;
			}
			
			if(checkCount==8){
				if(king.getColor()=="white"){
					System.out.println("Black wins");
				}else{
					System.out.println("White wins");
				}
			}
			return;
		}
		public static void printTable(ChessPiece[][] board){
			
			boolean emp = true;
			int count = 8;
			for(int i= 0; i< board.length ;i++ ){
				for(int j= 0; j< board[i].length; j++){
					if(board[i][j]==null){
						if(emp){
							System.out.print("   ");
							emp = false;
						}else{
							System.out.print("## ");
							emp = true;
						}
					}else{
					board[i][j].getName();
						if(emp){
							emp = false;
						}else{
							emp = true;
						}
					}
				}
				if(emp){
					emp = false;
				}else{
					emp = true;
				}
				System.out.print(" "+count--);
				System.out.println();
			}
			String column = "abcdefgh";
			
			for(int i  = 0; i<column.length(); i++){
				System.out.print(" "+column.charAt(i)+" ");
			}
		System.out.println();
		System.out.println();
		return;
		
		}
		
		private static ChessPiece[][] createTable(){
			ChessPiece[][] board= new ChessPiece[8][8];
			for(int i= 0; i< board.length ;i++ ){
				
				for(int j= 0; j< board[i].length; j++){
					/*if(i==1){
						//fill black pawns 
						board[i][j]= new Pawn("black",1,j);
					}else if(i==6){
						//fills white pawns
						board[i][j] = new Pawn("white",6,j);
					}else{
					*/
					
					board[i][j]= null;
				}
			}
			
			board[0][0] = new Rook("black", 0,0);
			//board[0][1] = new Knight("black",0,1);
			//board[0][2] = new Bishop("black",0,2);
			board[0][3] = new Queen("black", 0,3);
			board[0][4] = new King("black",0,4);
			blackKing = board[0][4];
			//board[0][5] = new Bishop("black",0,5);
			//board[0][6] = new Knight("black",0,6);
			board[0][7] = new Rook("black",0,7);
			
			board[7][0] = new Rook("white",7,0);
			//board[7][1] = new Knight("white",7,1);
			//board[7][2] = new Bishop("white",7,2);
			board[7][3] = new Queen("white",7,3);
			board[7][4] = new King("white",7,4);
			whiteKing = board[7][4];
			//board[7][5] = new Bishop("white",7,5);
			//board[7][6] = new Knight("white",7,6);
			board[7][7] = new Rook("white",7,7);
			
			
			
			
			return board;
		}
		
}

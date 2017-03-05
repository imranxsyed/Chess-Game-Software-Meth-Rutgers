package chess;
import java.util.Scanner;

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
		static ChessPiece pieceToMove;
		static int whitePiecesCount;
		static int blackPiecesCount;
		public static void main(String[]args){
			
			
			ChessPiece[][] board = createTable(); 
			
			
			//example of projessor using the 
			boolean end = false;
			boolean whitesTurn = true;
			Scanner scan;
			String moveFrom;
			String moveTo;
			
			while(end!=true){
				printTable(board);
				System.out.println();
				if(whitesTurn){
					System.out.print("Whites move: ");
				}else{
					System.out.print("Blacks move: ");
				}
				
				scan = new Scanner(System.in);
				moveFrom = scan.next();
				moveTo = scan.next();
				if(whitesTurn){
					if(validPiece(board, moveFrom, whiteKing)){
						if(pieceToMove.movePiece(board, moveTo, whitePiecesCount)){
							CheckMate(blackKing, board);
							whitesTurn = false;
						}
					}
				}else{
					if(validPiece(board, moveFrom, blackKing)){
						if(pieceToMove.movePiece(board, moveTo, blackPiecesCount)){
							CheckMate(whiteKing, board);
							whitesTurn = true;
						}
					}
				}
				if(blackKing ==null){
					System.out.println("White Wins");
					end = true;
				}
				if(whiteKing == null){
					System.out.println("Black player Wins");
					end = true;
				}
			}
			
			//updates the board
			
			
		}
		
		/**
		 * This method exhausts all possible options that next player can do to their king 
		 * in order to avoid checkmate if the count ==8 this means that no matter where the king goes 
		 * they will lose
		 * 
		 * king has at most 8 possible options
		 * @param king opposite king 
		 * @param board
		 */
		public static void CheckMate(ChessPiece king, ChessPiece[][] board){
			
			
			int tempX = king.x;
			int tempY = king.y;
			int checkCount = 0;
			
			ChessPiece tempPiece = null;
			ChessPiece[][] tempBoard = new ChessPiece[8][8];
			
			for(int i = 0; i<board.length; i++){
				for(int j =0; j<board.length; j++){
					tempBoard[i][j]= board[i][j];
				}
			}
		
			//down and to the left
			tempX++;
			tempY--;
			if(tempY>-1 && tempX<8){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor()!=king.getColor()){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
				
					for(int i=0; i<board.length; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									break;
								}
							}
						}
					}
					tempBoard[tempX][tempY]=null;
					tempBoard[king.x][king.y]= tempKing;
				}
			}else{
				checkCount++;
			}
			
			//checks left
			tempX = king.x;
			tempY = king.y -1;
			
			if(tempY>-1){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor()!=king.getColor()){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
			
					for(int i=0; i<board.length; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									break;
								}
							}
						}
					}
					tempBoard[tempX][tempY]=null;
					tempBoard[king.x][king.y]= tempKing;
				}
			}else{
				checkCount++;
			}
			
			
			//checks left up
			tempY = king.y -1;
			tempX = king.x -1;
			if(tempY>-1 && tempX>-1){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor()!=king.getColor()){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
				
					for(int i=0; i<board.length; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									break;
								}
							}
						}
					}
					tempBoard[tempX][tempY]=null;
					tempBoard[king.x][king.y]= tempKing;
				}
			}else{
				checkCount++;
			}
			
			
			
			//checks up
			tempY = king.y;
			tempX = king.x -1;
			if(tempX>-1){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor()!=king.getColor()){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
				
					for(int i=0; i<board.length; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									break;
								}
							}
						}
					}
					tempBoard[tempX][tempY]=null;
					tempBoard[king.x][king.y]= tempKing;
				}
			}else{
				checkCount++;
			}
			
			
			//checks up right
			tempX = king.x -1;
			tempY = king.y +1;
			if(tempY<8 && tempX>-1){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor()!=king.getColor()){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
				
					for(int i=0; i<board.length; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									break;
								}
							}
						}
					}
					tempBoard[tempX][tempY]=null;
					tempBoard[king.x][king.y]= tempKing;
				}
			}else{
				checkCount++;
			}
			
			
			
			//checks right
			tempX = king.x;
			tempY = king.y+1;
			if(tempY<8){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor()!=king.getColor()){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
				
					for(int i=0; i<board.length; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									break;
								}
							}
						}
					}
					tempBoard[tempX][tempY]=null;
					tempBoard[king.x][king.y]= tempKing;
				}
			}else{
				checkCount++;
			}
			
		
			//checks down and to the right
			tempX = king.x+1;
			tempY = king.y +1;
			if(tempX<8 && tempY<8){
				ChessPiece tempKing = board[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor()!=king.getColor()){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
			
					for(int i=0; i<board.length; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									break;
								}
							}
						}
					}
					tempBoard[tempX][tempY]=null;
					tempBoard[king.x][king.y]= tempKing;
				}
			}else{
				checkCount++;
			}
			
			//checks down
			tempX = king.x+1;
			tempY = king.y;
			if(tempX<8){
				ChessPiece tempKing = board[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor()!=king.getColor()){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
				
					for(int i=0; i<board.length; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									break;
								}
							}
						}
					}
					tempBoard[tempX][tempY]=null;
					tempBoard[king.x][king.y]= tempKing;
				}
			}else{
				checkCount++;
			}
			
			if(checkCount==8){
				if(king.getColor()=="white"){
					System.out.println("Black wins");
					whiteKing = null;
				}else{
					System.out.println("White wins");
					blackKing = null;
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
		private static boolean validPiece(ChessPiece[][] board, String moveFrom, ChessPiece king){
			//column
			if(moveFrom.length()>2){
				System.out.println("Error: invalid input");
				return false;
			}
			int ty = moveFrom.charAt(0)-97;
			//row
			int tx = moveFrom.charAt(1)-49;
			
			//board is upside down to how out board is made so it converts the value given by user
			//to a usable value for our array
			tx =  (tx-7)*(-1);
			
			//checks for out of boundary entry
			if(tx>7 ||tx< 0 || ty>7 || ty<0){
				System.out.println("Error: Invalid input");
				return false;
			}
			
			if(board[tx][ty]==null){
				System.out.println("Error: there is no piece there");
				return false;
			}
			
			if(board[tx][ty].getColor()!= king.getColor()){
				System.out.println("Error: this is not your piece");
				return false;
			}
			pieceToMove = board[tx][ty];
			return true;
		}
}

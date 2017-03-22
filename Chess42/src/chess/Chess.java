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
//hello
/**
 * This is the Chess class in which the board will be generated from the createTable method. 
 * Board is generated from a double array of the abstract class ChessPiece. 
 * This is a two player game in which one player is white and the second player is black.
 * As the rules dictate white player will go first.
 * Movements are indicated in the form column, row in which the column ranges from a-h and row 8-1.
 * Each player has has 16 pieces and the objective of this game is to take the King of their opponent. 
 * @author Pedro Cruz
 *
 */
public class Chess {
		
	
	
		static ChessPiece whiteKing;
		static ChessPiece blackKing;
		static ChessPiece pieceToMove;
		static ChessPiece[][] board;
		static boolean end;
		//static ChessPiece[] whitePieces = new ChessPiece[16];
		//static ChessPiece[] blackPiece = new ChessPiece[16];
		static int whiteInCheck  =0;
		static int blackInCheck  =0;
		public static Scanner scan = new Scanner(System.in);
		
		public static void main(String[]args){
			
			
			board = createTable(); 
			end = false;
			boolean whitesTurn = true;
			String userInput = "";
			String moveFrom;
			String moveTo;
			String answ= "";
			String[] tok;
			while(end!=true){
				printTable(board);
				System.out.println();
				if(whitesTurn){
					checkElPessant("white");
					System.out.print("Whites move: ");
				}else{
					checkElPessant("black");
					System.out.print("Blacks move: ");
				}
				
				userInput = scan.nextLine();
				tok = userInput.split("\\s+");
				moveFrom = tok[0];
				if(tok.length<2){
					if(moveFrom.compareToIgnoreCase("resign")==0){
						end = true;
						continue;
					}else{
						System.out.println("illegal move");
						continue;
					}
				}
				moveTo = tok[1];
				if(tok.length>2){
					answ = tok[2];
					if(answ.compareToIgnoreCase("draw?")==0){
						if(isDraw()){
							end = true;
						}
					}
				}
				if(whitesTurn){
					if(validPiece(board, moveFrom, whiteKing)){
						if(pieceToMove.movePiece(board, moveTo, tok.length>2? tok[2]:null )){
							if(pieceToMove.check(board)){
								blackInCheck = 1;
								System.out.println("check");
							}else{
								blackInCheck = 0;
							}
							CheckMate(blackKing, board, blackInCheck);
							whitesTurn = false;
						}
					}
				}else{
					if(validPiece(board, moveFrom, blackKing)){
						if(pieceToMove.movePiece(board, moveTo, null)){
							if(pieceToMove.check(board)){
								whiteInCheck = 1;
								System.out.println("check");
							}else{
								whiteInCheck = 0;
							}
							CheckMate(whiteKing, board, whiteInCheck);
							whitesTurn = true;
						}
					}
				}
				if(end != true){
					if(blackKing.location =="null"){
						System.out.println("White Wins");
						end = true;
					}
					if(whiteKing.location == "null"){
						System.out.println("Black player Wins");
						end = true;
					}
					
				}
				
				System.out.println();
			}
			
			//updates the board
			
			
		}
		
		/**
		 * This method exhausts all possible options that next player can do to their king 
		 * in order to avoid checkmate if the count ==8 this means that no matter where the king goes 
		 * they will lose
		 * 
		 * king has at most 8 possible options
		 * @author Pedro Cruz
		 * @param king opposite king 
		 * @param board
		 */
		public static void CheckMate(ChessPiece king, ChessPiece[][] board, int inCheck){
			
			
			int tempX = king.x;
			int tempY = king.y;
			int checkCount = 0;
			int availableSpace = 8;
			
			ChessPiece oppKing;
			if(king.getColor().compareTo("white")==0){
				oppKing = blackKing;
			}else{
				oppKing = whiteKing;
			}
			
			
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
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor().compareTo(king.getColor())!=0){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
					tempBoard[oppKing.x][oppKing.y] = null;
					boolean stop = false;
					
					for(int i=0; i<board.length &&stop!=true; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									stop = true;
									break;
								}
							}
						}
					}
					tempBoard[oppKing.x][oppKing.y] = oppKing;
					tempBoard[tempX][tempY]=board[tempX][tempY];
					tempBoard[king.x][king.y]= tempKing;
				}else{
					availableSpace--;
				}
			}else{
				availableSpace--;
			}

			//checks left
			tempX = king.x;
			tempY = king.y -1;
			
			if(tempY>-1){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor().compareTo(king.getColor())!=0){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
					tempBoard[oppKing.x][oppKing.y] = null;
					boolean stop = false;
					
					for(int i=0; i<board.length && stop!=true; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									stop = true;
									break;
								}
							}
						}
					}
					tempBoard[oppKing.x][oppKing.y] = oppKing;
					tempBoard[tempX][tempY]=board[tempX][tempY];
					tempBoard[king.x][king.y]= tempKing;
				}else{
					availableSpace--;
				}
			}else{
				availableSpace--;
			}
			


			//checks left up
			tempY = king.y -1;
			tempX = king.x -1;
			if(tempY>-1 && tempX>-1){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor().compareTo(king.getColor())!=0){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
					tempBoard[oppKing.x][oppKing.y] = null;
					boolean stop = false;
					
					for(int i=0; i<board.length && stop!=true; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									stop = true;
									break;
								}
							}
						}
					}
					tempBoard[oppKing.x][oppKing.y] = oppKing;
					tempBoard[tempX][tempY]=board[tempX][tempY];
					tempBoard[king.x][king.y]= tempKing;
				}else{
					availableSpace--;
				}
			}else{
				availableSpace--;
			}
			
			
			
			
			//checks up
			tempY = king.y;
			tempX = king.x -1;
			if(tempX>-1){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor().compareTo(king.getColor())!=0){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
					tempBoard[oppKing.x][oppKing.y] = null;
					boolean stop=  false;
					
					for(int i=0; i<board.length && stop!=true; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									stop = true;
									break;
								}
							}
						}
					}
					tempBoard[oppKing.x][oppKing.y] = oppKing;
					tempBoard[tempX][tempY]=board[tempX][tempY];
					tempBoard[king.x][king.y]= tempKing;
				}else{
					availableSpace--;
				}
			}else{
				availableSpace--;
			}
			
			
			//checks up right
			tempX = king.x -1;
			tempY = king.y +1;
			if(tempY<8 && tempX>-1){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor().compareTo(king.getColor())!=0){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
					tempBoard[oppKing.x][oppKing.y] = null;
					boolean stop = false;
					
					for(int i=0; i<board.length && stop!=false; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									stop = true;
									break;
								}
							}
						}
					}
					tempBoard[oppKing.x][oppKing.y] = oppKing;
					tempBoard[tempX][tempY]=board[tempX][tempY];
					tempBoard[king.x][king.y]= tempKing;
				}else{
					availableSpace--;
				}
			}else{
				availableSpace--;
			}
			

			
			//checks right
			tempX = king.x;
			tempY = king.y+1;
			if(tempY<8){
				ChessPiece tempKing = tempBoard[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor().compareTo(king.getColor())!=0){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
					tempBoard[oppKing.x][oppKing.y] = null;
					boolean stop = false;
					
					
					for(int i=0; i<board.length && stop!= true; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									stop = true;
									break;
								}
							}
						}
					}
					tempBoard[oppKing.x][oppKing.y] = oppKing;
					tempBoard[tempX][tempY]=board[tempX][tempY];
					tempBoard[king.x][king.y]= tempKing;
				}else{
					availableSpace--;
				}
			}else{
				availableSpace--;
			}
			
			
			
			
			//checks down and to the right
			tempX = king.x+1;
			tempY = king.y +1;
			if(tempX<8 && tempY<8){
				ChessPiece tempKing = board[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor().compareTo(king.getColor())!=0){
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
					tempBoard[oppKing.x][oppKing.y] = null;
					boolean stop = false;
					
					for(int i=0; i<board.length && stop!=true; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									stop = true;
									checkCount++;
									break;
								}
							}
						}
					}
					tempBoard[oppKing.x][oppKing.y] = oppKing;
					tempBoard[tempX][tempY]=board[tempX][tempY];
					tempBoard[king.x][king.y]= tempKing;
				}else{
					availableSpace--;
				}
			}else{
				availableSpace--;
			}

			
			
			
			//checks down
			tempX = king.x+1;
			tempY = king.y;
			if(tempX<8){
				ChessPiece tempKing = board[king.x][king.y];
				if(tempBoard[tempX][tempY]==null||tempBoard[tempX][tempY].getColor().compareTo(king.getColor())!=0){
					
					tempBoard[tempX][tempY] = tempKing;
					tempBoard[king.x][king.y] = null;
					tempBoard[oppKing.x][oppKing.y] = null;
					boolean stop = false;
					
					for(int i=0; i<board.length && stop!= true; i++){
						for(int j=0; j<board.length; j++){
							tempPiece = tempBoard[i][j];
							if(tempPiece!=null){
								if(tempPiece.check(tempBoard)){
									checkCount++;
									stop = true;
									break;
								}
							}
						}
					}
					tempBoard[oppKing.x][oppKing.y] = oppKing;
					tempBoard[tempX][tempY]=board[tempX][tempY];
					tempBoard[king.x][king.y]= tempKing;
				}else{
					availableSpace--;
				}
			}else{
				availableSpace--;
			}
			
			
			
			
			if(checkCount==availableSpace && checkCount!=0){
				if(inCheck==0){
					System.out.print(" draw?");
					Scanner scanner = new Scanner(System.in);
					String answer = scanner.next();
					if(answer.compareToIgnoreCase("draw")==0){
						System.out.println("draw");
						end = true;
					}else{
						end = false;
					}
					scanner.close();
					return;
				}
				
				if(king.getColor()=="white"){
					System.out.println("Black wins");
					end = true;
					whiteKing = null;
				}else{
					System.out.println("White wins");
					blackKing = null;
					end = true;
				}
			}
			return;
		}
		/**
		 * Prints the table after each successful move
		 * @param board
		 * @author Pedro Cruz
		 */
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
		return;
		
		}
		/**
		 * Created a board by generatting a double array of ChessPiece setting the pieces by calling the subsets in the correct position. All empty/unoccupied pieces are set to null.
		 * @return
		 * @author Pedro Cruz
		 */
		private static ChessPiece[][] createTable(){
			ChessPiece[][] board= new ChessPiece[8][8];
			
			for(int i= 0; i< board.length ;i++ ){
				
				for(int j= 0; j< board[i].length; j++){
					if(i==1){
						//fill black pawns
						board[i][j]= new pawn("black",1,j);
					}else if(i==6){
						//fills white pawns
						board[i][j] = new pawn("white",6,j);
					}else{
						board[i][j]= null;
					}
				}
				
			}
			board[0][0] = new Rook("black", 0,0);
			board[0][1] = new Knight("black",0,1);
			board[0][2] = new Bishop("black",0,2);
			board[0][3] = new Queen("black", 0,3);
			board[0][4] = new King("black",0,4);
			blackKing = board[0][4];
			board[0][5] = new Bishop("black",0,5);
			board[0][6] = new Knight("black",0,6);
			board[0][7] = new Rook("black",0,7);
			
			board[7][0] = new Rook("white",7,0);
			board[7][1] = new Knight("white",7,1);
			board[7][2] = new Bishop("white",7,2);
			board[7][3] = new Queen("white",7,3);
			board[7][4] = new King("white",7,4);
			whiteKing = board[7][4];
			board[7][5] = new Bishop("white",7,5);
			board[7][6] = new Knight("white",7,6);
			board[7][7] = new Rook("white",7,7);
			
			return board;
		}
		/**
		 * Checks if the piece that the player is trying to move belongs to them.
		 * @param board
		 * @param moveFrom
		 * @param king
		 * @return boolean
		 * @author Pedro Cruz
		 */
		
		private static boolean validPiece(ChessPiece[][] board, String moveFrom, ChessPiece king){

			//column
			if(moveFrom.length()!=2){
				System.out.println("Illegal Move");
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
				System.out.println("Illegal Move");
				return false;
			}
			
			if(board[tx][ty]==null){
				System.out.println("Illegal Move");
				return false;
			}
			
			if(board[tx][ty].getColor()!= king.getColor()){
				System.out.println("Illegal Move");
				return false;
			}
			pieceToMove = board[tx][ty];
			return true;
		}
		
		/**
		 * 
		 * this method checks el_pessant values and sets equals to false depending whose turn it is (parameters)
		 * @param color
		 */
		private static void checkElPessant(String color){
			
			for (int i = 0 ; i< board[0].length; i ++){
				
				for (int j = 0 ; j < board[i].length; j++){
					
					
					if (board[i][j]!=null 
						&& board[i][j].type.equalsIgnoreCase("Pawn")
						&& board[i][j].color.equalsIgnoreCase(color)){
						
						board[i][j].el_pessant = false;
					}
				}
			}
			
			
		}

		
		public static boolean isDraw(){
			String answer = scan.nextLine();
				if(answer.compareToIgnoreCase("draw")==0){
					return true;
				}
				return false;
		}
}

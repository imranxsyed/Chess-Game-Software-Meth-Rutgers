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

		public static void main(String[]args){
			
			
			ChessPiece[][] board= new ChessPiece[8][8];
			for(int i= 0; i< board.length ;i++ ){
				for(int j= 0; j< board[i].length; j++){
					board[i][j]= null;
				}
			}
			
			
			board[0][4] = new King("black");
			
			printTable(board);
			ChessPiece temp = board[0][4];
			temp.movePiece(board, "f8" );
			//updates the board
			board[temp.x][temp.y] = temp;
			board[0][4]=null;
			printTable(board);
			
			
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
		return;
		}
		
}

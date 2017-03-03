package chess;

public class Chess {

		public static void main(String[]args){
			
			//this is just a test
			ChessPiece[][] board= new ChessPiece[8][8];
			for(int i= 0; i< board.length ;i++ ){
				for(int j= 0; j< board[i].length; j++){
					board[i][j]= null;
				}
			}
			
			//comment
			board[7][4] = new King("black");
			boolean emp = true;
			int count = 8;
			for(int i= 0; i< board.length ;i++ ){
				for(int j= 0; j< board[i].length; j++){
					if(board[i][j]==null){
						if(emp){
							System.out.print("  ");
							emp = false;
						}else{
							System.out.print(" ##");
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
		}
		
		
}

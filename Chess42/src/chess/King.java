package chess;

public class King extends ChessPiece {
	
	public King(String color){
		if(color =="white"){
			this.color = color;
			x = 7;
			y = 4;
			location = "e1";
			name = " wK";
		}else{
			this.color = color;
			x = 0;
			y = 4;
			location = "e8";
			name = "bK";
		}
	}
	public  boolean move(int toX, int toY){
	
		System.out.println("this: " +this.x + " "+ this.y);
		System.out.println("input: " + toX+ " "+ toY);
		int newX = Math.abs(this.x - toX);
		int newY = Math.abs(this.y - toY);
		System.out.println(newY);
		if(newX>1 || newY>1){
			return false;
		}else if(this.y ==toY && this.x == toX){
			System.out.println("Error");
			return false;
		}else{
			return true;
		}
	}
	
}

package com.game.othello;

public class Board {
	private char board[][];
	private int boardSize =8;
	private char p1Symbol, p2Symbol;
	private int count; // to track the number of cells filled
	private static final char EMPTY = ' ';
	public static final int PLAYER1WINS = 1;
	public static final int PLAYER2WINS = 2;
	public static final int DRAW =3;
	public static final int INCOMPLETE =4;
	public static final int INVALIDMOVE = 5;
	


	public Board(char p1Symbol, char p2Symbol) {
		board = new char[boardSize][boardSize];
		for(int i=0; i<boardSize; i++) {
			for(int j=0; j< boardSize; j++) {
				board[i][j] = EMPTY;
			}
		}

		this.p1Symbol = p1Symbol;
		this.p2Symbol = p2Symbol;

		board[3][3] = p1Symbol;
		board[3][4] = p2Symbol;
		board[4][3] = p2Symbol;
		board[4][4] = p1Symbol;
		count = 4;
	}

	int[] xDir={-1,-1,0,1,1,1,0,-1};
	int[] yDir={0,1,1,1,0,-1,-1,-1};

	public int move(char symbol, int x, int y){
		
		if(x < 0 || x >= boardSize || y < 0 || y >= boardSize || board[x][y] != EMPTY){
			return INVALIDMOVE;
		}

		boolean movePossible = false;

		for(int i = 0; i < xDir.length; i++){
			int xStep = xDir[i];
			int yStep = yDir[i];
			int currentX = x + xStep;
			int currentY = y + yStep;
			int counter = 0; // count of opponent's pieces in line

			while(currentX >= 0 && currentX < 8 && currentY >= 0 && currentY < 8){

				if(board[currentX][currentY] == EMPTY){
					break;
				}else if(board[currentX][currentY] != symbol){
					currentX += xStep;
					currentY += yStep;
					counter++;
				}else{
					// conversion is possible
					if(counter > 0){
						movePossible = true;
						int convertX = currentX - xStep;
						int convertY = currentY - yStep;

						while(convertX != x || convertY != y){
							board[convertX][convertY] = symbol;
							convertX = convertX - xStep;
							convertY = convertY - yStep;
						}
					}
					break;
				}
			}
		}
		
		if(count == boardSize * boardSize) {
			int p1Count=0, p2Count =0;
			
			for(int i=0; i<boardSize; i++) {
				for(int j=0; j<boardSize; j++) {
					if(board[i][j] == p1Symbol) {
						p1Count++;
					}else if(board[i][j] == p2Symbol){
						p2Count++;
					}
				}
			}
			
			if(p1Count > p2Count) {
				return PLAYER1WINS;
			}else {
				return PLAYER2WINS;
			}
		}
		
		
		if(count < boardSize * boardSize) {
			if(movePossible){
				count++;
				board[x][y] =symbol;
			}else {
				return INVALIDMOVE;
			}
		}
		
		return INCOMPLETE;
		

	}


	public void print() {
		System.out.println();
		//System.out.println("---------------------------------------");
		for(int i=0; i<boardSize; i++) {
			for(int j=0; j< boardSize; j++) {
				System.out.print("| " + board[i][j] + " |");
			}
			
			System.out.println();
			System.out.println("---------------------------------------");
		}
		System.out.println();
	}
}

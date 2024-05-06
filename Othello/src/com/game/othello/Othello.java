package com.game.othello;

import java.util.Scanner;

public class Othello {
	private Player player1, player2;
	private Board board;
	private int numPlayers;

	public static void main(String[] args) {
		Othello o = new Othello();
		o.startGame();
	}
	
	public void startGame(){
		Scanner s = new Scanner(System.in);
		//take players input
		player1 = takePlayerInput(++numPlayers);
		player2 = takePlayerInput(++numPlayers);
		
		//check for having different symbols for player1 and player2
		while(player1.getSymbol() == player2.getSymbol()) {
			System.out.println("Symbol already taken, try entering a different symbol");
			player2.setSymbol(s.next().charAt(0));
		}
		
		//create the board
		board = new Board(player1.getSymbol(), player2.getSymbol());
		
		//play the game
		boolean player1Turn = true;
		int status = board.INCOMPLETE;
		
		while(status == board.INCOMPLETE || status == board.INVALIDMOVE) {
			if(player1Turn) {
				System.out.println("Player 1 - " + player1.getName() + "'s turn");
				System.out.println("Enter x : ");
				int x = s.nextInt();
				System.out.println("Enter y : ");
				int y = s.nextInt();
				
				status = board.move(player1.getSymbol(), x, y);
				if(status == Board.INVALIDMOVE) {
					System.out.println("Invalid move!! try entering again !!");
					continue;
				}
			}else {
				System.out.println("Player 2 - " + player2.getName() + "'s turn");
				System.out.println("Enter x : ");
				int x = s.nextInt();
				System.out.println("Enter y : ");
				int y = s.nextInt();
				
				status = board.move(player2.getSymbol(), x, y);
				if(status == Board.INVALIDMOVE) {
					System.out.println("Invalid move!! try entering again !!");
					continue;
				}
			}
			player1Turn = !player1Turn;
			board.print();
		}
		
		if(status == board.PLAYER1WINS) {
			System.out.println("Player 1 - " + player1.getName() + " wins !!");
		}else if(status == board.PLAYER2WINS) {
			System.out.println("Player 2 - " + player2.getName() + " wins !!");
		}else {
			System.out.println("Game Drawn");
		}
		
	}
	
	private Player takePlayerInput(int num) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter player " + num + " name : ");
		String name = s.nextLine();
		System.out.println("Enter player " + num + " symbol : ");
		char symbol = s.next().charAt(0);
		Player p = new Player(name, symbol);
		return p;
	}
}

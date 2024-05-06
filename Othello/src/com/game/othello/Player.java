package com.game.othello;

public class Player {
	
	private String name;
	private char symbol;
	
	public Player(String name, char symbol) {
		setName(name);
		setSymbol(symbol);
	}
	
	//setter to set the name of a player
	public void setName(String name) {
		if(!name.isEmpty()) {
			this.name = name;
		}
	}
	
	//getter to access the name of a player
	public String getName(){
		return this.name;
	}
	
	//setter to set the symbol of a player
	public void setSymbol(char symbol) {
		if(symbol != '\0') {
			this.symbol = symbol;
		}
	}
	
	//getter to access the symbol of a player
	public char getSymbol() {
		return this.symbol;
	}

}

package card;

public abstract class Card {
	private String cardName;
	private int number;
	private static int count = 0;
	
	public Card(String name, int number){
		this.cardName = name;
		this.number = number;
	}
	
	public Card(String name){
		this(name, count);
		count++;
	}
	
	public int getId(){
		return number;
	}

}

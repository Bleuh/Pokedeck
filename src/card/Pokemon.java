package card;

import java.util.List;

public class Pokemon extends Card{
	private static int[] evolution = {1, 2, 3};
	
	private int hp;
	private String pokemonName;
	private Type type;
	private int stage;
	private List<Abilitie> listAbilitie;
	private static String cardName = "POKEMON";
	
	public Pokemon(int number, String pokemonName, int hp, Type type, int stage, List<Abilitie> listAbilitie) {
		super(cardName, number);
		this.hp = hp;
		this.pokemonName = pokemonName;
		this.type = type;
		this.stage = stage;
		this.listAbilitie = listAbilitie;
	}
	
	public Pokemon(String pokemonName, int hp, Type type, int stage, List<Abilitie> listAbilitie) {
		super(cardName);
		this.hp = hp;
		this.pokemonName = pokemonName;
		this.type = type;
		this.stage = stage;
		this.listAbilitie = listAbilitie;
	}
	
}

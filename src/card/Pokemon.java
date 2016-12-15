package card;

import java.util.List;

public class Pokemon extends Card{
	private static int[] evolution = {1, 2, 3};
	
	private int hp;
	private String pokemonName;
	private Type type;
	private int stage;
	private List<Abilitie> listAbilitie;
	
	public Pokemon(String name, int number, int hp, String pokemonName, Type type, int stage, List<Abilitie> listAbilitie) {
		super(name, number);
		this.hp = hp;
		this.pokemonName = pokemonName;
		this.type = type;
		this.stage = stage;
		this.listAbilitie = listAbilitie;
	}
	
}

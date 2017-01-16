package card;

import java.util.ArrayList;
import java.util.List;

public class Abilitie {
	private String abilitieName;
	private int power;
	private String description;
	public static List<Abilitie> listAbilitie = new ArrayList<Abilitie>();
	
	public Abilitie(String abilitieName, int power, String description) {
		this.abilitieName = abilitieName;
		this.power = power;
		this.description = description;
	}
	
	public static void ajout(String abilitieName, int power, String description){
		Abilitie.listAbilitie.add(new Abilitie(abilitieName, power, description));
	}

}

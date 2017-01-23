package card;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Type{
	private String typeName;
	private Color color;
	public static List<Type> pokemonType = Arrays.asList(
	  new Type("EAU", Color.BLUE), 
	  new Type("FEU", Color.RED), 
	  new Type("PLANTE", Color.GREEN), 
	  new Type("PSY", Color.PINK) 
	);
	
	public Type(String typeName, Color color) {
		this.typeName = typeName;
		this.color = color;
	}

	@Override
	public String toString() {
		return this.typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public Color getColor(){
		return this.color;
	}
	
}

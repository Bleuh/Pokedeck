package card;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Type{
	private String typeName;
	private Color color;
	public static List<Type> pokemonType = Arrays.asList(
	  new Type("EAU", Color.BLUE), 
	  new Type("FEU", Color.ORANGE), 
	  new Type("PLANTE", Color.GREEN), 
	  new Type("PSY", Color.PINK),
	  new Type("NORMAL", Color.GRAY),
	  new Type("POISON", Color.MAGENTA),
	  new Type("ELECTRIK", Color.YELLOW),
	  new Type("GLACE", Color.CYAN),
	  new Type("COMBAT", Color.RED),
	  new Type("SOL", Color.ORANGE),
	  new Type("VOL", Color.CYAN),
	  new Type("INSECTE", Color.GREEN),
	  new Type("ROCHE", Color.ORANGE),
	  new Type("SPECTRE", Color.MAGENTA),
	  new Type("DRAGON", Color.BLUE),
	  new Type("TENEBRES", Color.DARK_GRAY),
	  new Type("ACIER", Color.LIGHT_GRAY)
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

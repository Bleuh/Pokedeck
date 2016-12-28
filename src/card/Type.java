package card;

public class Type{
	private String typeName;
	private String color;
	
	public Type(String typeName, String color) {
		this.typeName = typeName;
		this.color = color;
	}

	@Override
	public String toString() {
		return typeName;
	}
	
}

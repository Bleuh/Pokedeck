package application;

import ui.Window;

public class Application {

	public static void main(String[] args) {
		Window window = new Window("Pokedex");
//		ArrayList<Abilitie> list = new ArrayList<Abilitie>();
//		list.add(new Abilitie("Test1", 20, "desc1"));
//		list.add(new Abilitie("Test2", 30, "desc3"));
//		test.write(new Pokemon("test", 1, new Type("vert", Color.GREEN), 1, list));
//		test.write(new Pokemon("test", 1, null, 1, null));
		window.display();
	}

}

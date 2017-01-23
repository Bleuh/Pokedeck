package application;

import javax.swing.JFrame;

import card.Abilitie;
import card.Pokemon;
import ui.Window;

public class Application {

	public static void main(String[] args) {
		Window window = new Window("Pokedex");
		FileUtil test = new FileUtil("data.txt");
		test.write(new Pokemon("test", 1, null, 1, null));
//		window.display();
	}

}

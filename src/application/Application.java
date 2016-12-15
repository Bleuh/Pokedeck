package application;

import javax.swing.JFrame;

import ui.Window;

public class Application {

	public static void main(String[] args) {
		JFrame jframe = new JFrame ( " Hello world " );
		jframe . setSize (500 , 400);
		jframe . setDefaultCloseOperation ( JFrame . EXIT_ON_CLOSE );
		
		Window window = new Window("Test");
		jframe.setContentPane(window);
		
		jframe.setVisible(true);

	}

}

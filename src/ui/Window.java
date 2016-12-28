package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import card.Type;

public class Window{
	
	private static JPanel cards;
	private static JFrame frame = new JFrame();
    private String name;
    private final int tailleX = 500;
    private final int tailleY = 500;
	
	public Window(String name){
        this.name = name;
        this.frame.setTitle(name);
        this.frame.setSize (tailleX , tailleX);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.addToFrame();
        
	}
	
	private class ChangeCard implements ActionListener {

	    private String name;
	    private ChangeCard(String name) {
	        this.name = name;
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	itemChanged(name);
	    }

	}
	
	private void addToFrame(){
		JPanel accueil = new JPanel();
		JButton buttonAddPokemon = new JButton("Ajouter un pokemon");
		buttonAddPokemon.addActionListener(new ChangeCard("addPokemon"));
		accueil.add(buttonAddPokemon);
		
        this.cards = new JPanel(new CardLayout());
        this.cards.add(accueil, "accueil");
        this.cards.add(getTemplateAddPokemon(), "addPokemon");
        
        this.frame.add(cards);
		
	}
	
	private JPanel getTemplateAddPokemon(){
		
		JPanel template = new JPanel();
		
		template.add(new JLabel("Ajoute un pokemon"));
		
		template.add(new JLabel("Type du pokemon :"));
		
		JComboBox<Object> combo = new JComboBox<Object>();
		combo.setPreferredSize(new Dimension(100, 20));
	    combo.addItem(new Type("Eau", "blue"));
	    template.add(combo);
	    template.add(new JTextField("TextField", 20));
		
		return template;
	}
	
	public void itemChanged(String name) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, name);
    }
	
	public void display(){
        frame.setVisible(true);
	}
}

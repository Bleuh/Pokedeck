package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
	private static JButton buttonHome = new JButton("Accueil");
    private String name;
    private final int tailleX = 500;
    private final int tailleY = 500;
	
	public Window(String name){
        this.name = name;
		this.buttonHome.addActionListener(new ChangeCard("accueil", "Accueil"));
        this.frame.setTitle(name);
        this.frame.setSize (tailleX , tailleX);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.addToFrame();
        
	}
	
	private class ChangeCard implements ActionListener {

	    private String name, title;
	    private ChangeCard(String name, String title) {
	        this.name = name;
	        this.title = title;
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        frame.setTitle(title);
	    	itemChanged(name);
	    }

	}
	
	private void addToFrame(){
		JPanel accueil = new JPanel();
		JButton buttonAddPokemon = new JButton("Ajouter un pokemon");
		buttonAddPokemon.addActionListener(new ChangeCard("addPokemon", "Ajouter un pokemon"));
		accueil.add(buttonAddPokemon);
		
        this.cards = new JPanel(new CardLayout());
        this.cards.add(accueil, "accueil");
        this.cards.add(getTemplateAddPokemon(), "addPokemon");
        
        this.frame.add(cards);
		
	}
	
	private JPanel getTemplateAddPokemon(){
		
		JPanel template = new JPanel();

		template.add(new JLabel("Nom :"));
	    template.add(new JTextField(20));
	    
	    template.add(new JLabel("Point de vie :"));
	    template.add(new JTextField(4));
		
		template.add(new JLabel("Type :"));
		JComboBox<Object> combo = new JComboBox<Object>();
		combo.setPreferredSize(new Dimension(100, 20));
		for (Type type : Type.pokemonType) {
		    combo.addItem(type);
		}
	    template.add(combo);
	    
	    template.add(new JLabel("Evolution :"));
	    JComboBox<Object> comboStage = new JComboBox<Object>();
	    comboStage.setPreferredSize(new Dimension(100, 20));
	    comboStage.addItem(1);
	    comboStage.addItem(2);
	    comboStage.addItem(3);
	    template.add(comboStage);
	    
		template.add(buttonHome);
		
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

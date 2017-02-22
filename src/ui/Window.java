package ui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.FileUtil;
import card.Pokemon;
import card.Type;

public class Window{
	
	private static JPanel cards;
	private static JFrame frame = new JFrame();
	private FileUtil fileUtil;
    private String name;
    private final int tailleX = 500;
    private final int tailleY = 500;
	
	public Window(String name){
        this.name = name;
        this.fileUtil = new FileUtil("data.txt", this);
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
		accueil.setLayout(new GridLayout(3, 3));
		accueil.setName("accueil");
		
		JButton buttonAddPokemon = new JButton("Ajouter un pokemon");
		buttonAddPokemon.addActionListener(new ChangeCard("addPokemon", "Ajouter un pokemon"));
		accueil.add(buttonAddPokemon);
		
		JButton buttonViewPokemon = new JButton("Voir les pokemon");
		buttonViewPokemon.addActionListener(new ChangeCard("viewPokemon", "Les pokemon"));
		accueil.add(buttonViewPokemon);
		
		JButton buttonUpdatePokemon = new JButton("Modifier un pokemon");
		buttonUpdatePokemon.addActionListener(new ChangeCard("updatePokemon", "Modifier un pokemon"));
		accueil.add(buttonUpdatePokemon);
		
        this.cards = new JPanel(new CardLayout());
        this.cards.add(accueil, "accueil");
        this.cards.add(getTemplateAddPokemon("addPokemon"), "addPokemon");
        this.cards.add(getTemplateViewPokemon("viewPokemon"), "viewPokemon");
        
        this.frame.add(cards);
		
	}
	
	private JPanel getTemplateAddPokemon(String nameTemplate){
		
		JPanel template = new JPanel();
		template.setName(nameTemplate);

		Panel nameP = new Panel();
		nameP.add(new JLabel("Nom :"));
		JTextField name = new JTextField(20);
		nameP.add(name);
		template.add(nameP);
	    
		Panel hpP = new Panel();
		hpP.add(new JLabel("Point de vie :"));
		JTextField hp = new JTextField(4);
		hpP.add(hp);
		template.add(hpP);
		
		Panel typeP = new Panel();
		typeP.add(new JLabel("Type :"));
		JComboBox<Object> combo = new JComboBox<Object>();
		combo.setPreferredSize(new Dimension(100, 20));
		for (Type type : Type.pokemonType) {
		    combo.addItem(type);
		}
		typeP.add(combo);
		template.add(typeP);
	    
	    Panel stageP = new Panel();
	    stageP.add(new JLabel("Evolution :"));
	    JComboBox<Object> comboStage = new JComboBox<Object>();
	    comboStage.setPreferredSize(new Dimension(100, 20));
	    comboStage.addItem(1);
	    comboStage.addItem(2);
	    comboStage.addItem(3);
	    stageP.add(comboStage);
	    template.add(stageP);
	    
	    JButton submit = new JButton("Ajouter");
	    submit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String pokemonName = name.getText();
	        	String hpText = hp.getText();
	        	Integer pokemonHp = Integer.parseInt(hpText);
	        	Type pokemonType = (Type)combo.getSelectedItem();
	        	Integer pokemonStage = (Integer)comboStage.getSelectedItem();
	        	fileUtil.write(new Pokemon(pokemonName, pokemonHp ,pokemonType, pokemonStage, null));
	        	
		        frame.setTitle("Accueil");
		    	itemChanged("accueil");
	        }
	    });
	    
	    template.add(submit);

		JButton home = new JButton("Accueil");
		home.addActionListener(new ChangeCard("accueil", "Accueil"));
		template.add(home);
	
		return template;
	}
	
	private JPanel getTemplateViewPokemon(String nameTemplate){
		
		JPanel template = new JPanel();
		template.setName(nameTemplate);
		
		List<Pokemon> pokemons = fileUtil.getPokemon();
		
		String firstName = "";
		String firstHp = "";
		String firstStage = "";
		String firstType = "";
		
		if(!pokemons.isEmpty()){
			Pokemon firstPokemon = pokemons.get(0);
			
			firstName = firstPokemon.getPokemonName();
			firstHp = String.valueOf(firstPokemon.getHp());
			firstStage = String.valueOf(firstPokemon.getStage());
			firstType = firstPokemon.getType().getTypeName();
		}
		
		JLabel name = new JLabel(firstName);
		JLabel hp = new JLabel(firstHp);
		JLabel stage = new JLabel(firstStage);
		JLabel type = new JLabel(firstType);

		Panel dropdownP = new Panel();
		dropdownP.add(new JLabel("Pokemon :"));
		JComboBox<Object> combo = new JComboBox<Object>();
		combo.setPreferredSize(new Dimension(100, 20));
		for (Pokemon pokemon : pokemons) {
		    combo.addItem(pokemon);
		}
		
		combo.addItemListener(new ItemListener () {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			       Pokemon item = (Pokemon) e.getItem();
			       name.setText(item.getPokemonName());
			       hp.setText(String.valueOf(item.getHp()));
				   stage.setText(String.valueOf(item.getStage()));
				   type.setText(item.getType().getTypeName());
			    }
			}
		});
		dropdownP.add(combo);
		template.add(dropdownP);
	    
	    Panel nameP = new Panel();
	    nameP.add(new JLabel("Nom :"));
	    nameP.add(name);
	    template.add(nameP);
		
	    Panel hpP = new Panel();
	    hpP.add(new JLabel("Hp :"));
	    hpP.add(hp);
	    template.add(hpP);
		
	    Panel stageP = new Panel();
	    stageP.add(new JLabel("Stage :"));
	    stageP.add(stage);
	    template.add(stageP);

	    Panel typeP = new Panel();
	    typeP.add(new JLabel("Type :"));
	    typeP.add(type);
	    template.add(typeP);
	    
	    JButton home = new JButton("Accueil");
		home.addActionListener(new ChangeCard("accueil", "Accueil"));
		template.add(home);
		
		return template;
	}
	
	public void pokemonUpdate(){
		Component[] components = this.cards.getComponents();

		for(int i = 0; i < components.length; i++) {
		    if(components[i].getName().equals("viewPokemon")) {
		    	this.cards.remove(components[i]);
		        this.cards.add(getTemplateViewPokemon("viewPokemon"), "viewPokemon");
		    }
		}   
	}
	
	public void itemChanged(String name) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, name);
    }
	
	public void display(){
        frame.setVisible(true);
	}
}

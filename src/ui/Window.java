package ui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.FileUtil;
import card.Pokemon;
import card.Type;

public class Window{
	
	private static JPanel cards;
	private static JFrame frame = new JFrame();
	private FileUtil fileUtil;
    @SuppressWarnings("unused")
	private String name;
    private final int tailleX = 500;
    private final int tailleY = 500;
	
	public Window(String name){
        this.name = name;
        this.fileUtil = new FileUtil("data.txt", this);
        Window.frame.setTitle(name);
        Window.frame.setSize (tailleX , tailleY);
        Window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.frame.setLocationRelativeTo(null);
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
		accueil.setLayout(new GridLayout(2, 3));
		accueil.setName("accueil");
		
		JButton buttonAddPokemon = new JButton("Ajouter un pokemon");
		buttonAddPokemon.addActionListener(new ChangeCard("addPokemon", "Ajouter un pokemon"));
		accueil.add(buttonAddPokemon);
		
		JButton buttonViewUpdatePokemon = new JButton("Voir / Modifier les pokemons");
		buttonViewUpdatePokemon.addActionListener(new ChangeCard("viewUpdatePokemon", "Pokedex"));
		accueil.add(buttonViewUpdatePokemon);
		
        Window.cards = new JPanel(new CardLayout());
        Window.cards.add(accueil, "accueil");
        Window.cards.add(getTemplateAddPokemon("addPokemon"), "addPokemon");
        Window.cards.add(getTemplateViewUpdatePokemon("viewUpdatePokemon"), "viewUpdatePokemon");
        
        Window.frame.add(cards);
		
	}
	
	private JPanel getTemplateAddPokemon(String nameTemplate){
		
		JPanel template = new JPanel();
		template.setName(nameTemplate);
		template.setLayout(new GridLayout(5, 2));

		template.add(new JLabel("Nom :"));
		JTextField name = new JTextField(20);
		template.add(name);
	    
		template.add(new JLabel("Point de vie :"));
		JTextField hp = new JTextField(4);
		template.add(hp);
		
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
	    
	    JButton submit = new JButton("Ajouter");
	    submit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if(name.getText().equals("")){
	        		JOptionPane.showMessageDialog( frame, "Le nom est vide", "Erreur", JOptionPane.ERROR_MESSAGE);
	        	}
	        	else if(hp.getText().equals("")){
	        		JOptionPane.showMessageDialog( frame, "Les hp sont vide", "Erreur", JOptionPane.ERROR_MESSAGE);
	        	}
	        	else{
		        	String pokemonName = name.getText();
		        	String hpText = hp.getText();
		        	Integer pokemonHp = Integer.parseInt(hpText);
		        	Type pokemonType = (Type)combo.getSelectedItem();
		        	Integer pokemonStage = (Integer)comboStage.getSelectedItem();
		        	fileUtil.write(new Pokemon(pokemonName, pokemonHp ,pokemonType, pokemonStage, null));
		        	
			        frame.setTitle("Accueil");
			    	itemChanged("accueil");
	        	}
	        }
	    });
	    
	    template.add(submit);

		JButton home = new JButton("Accueil");
		home.addActionListener(new ChangeCard("accueil", "Accueil"));
		template.add(home);
	
		return template;
	}
	
	private JPanel getTemplateViewUpdatePokemon(String nameTemplate){
		
		JPanel template = new JPanel();
		template.setName(nameTemplate);
		template.setLayout(new GridLayout(10, 2));
		
		List<Pokemon> pokemons = fileUtil.getPokemon();
		
		String firstName = "";
		String firstID = "";
		String firstHp = "";
		int firstStage = 0;
		Type firstType = null;
		
		if(!pokemons.isEmpty()){
			Pokemon firstPokemon = pokemons.get(0);
			
			firstName = firstPokemon.getPokemonName();
			firstID = String.valueOf(firstPokemon.getId());
			firstHp = String.valueOf(firstPokemon.getHp());
			firstStage = firstPokemon.getStage() - 1; //index start to 0
			firstType = firstPokemon.getType();
		}

		JTextField name = new JTextField(firstName);
		JLabel id = new JLabel(firstID);
		JTextField hp = new JTextField(firstHp);
		
	    JComboBox<Object> comboStage = new JComboBox<Object>();
	    comboStage.addItem(1);
	    comboStage.addItem(2);
	    comboStage.addItem(3);
	    comboStage.setSelectedIndex(firstStage);
		
	    JComboBox<Object> comboType = new JComboBox<Object>();
		comboType.setPreferredSize(new Dimension(100, 20));
		int indexType = 0;int index = 0;
		for (Type type : Type.pokemonType) {
		    comboType.addItem(type);
		    if(firstType != null && type.getTypeName().equals(firstType.getTypeName())){
		    	indexType = index;
		    }
		    index++;
		}
		comboType.setSelectedIndex(indexType);

		template.add(new JLabel("Pokemon :"));
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
			       id.setText(String.valueOf(item.getId()));
			       hp.setText(String.valueOf(item.getHp()));
			       comboStage.setSelectedIndex(item.getStage() - 1);
			       int indexType = 0;int index = 0;
			       for (Type type : Type.pokemonType) {
					   if(type.getTypeName().equals(item.getType().getTypeName())){
						   indexType = index;
					   }
					   index++;
			       }
			       comboType.setSelectedIndex(indexType);
			    }
			}
		});
		template.add(combo);
	    
	    template.add(new JLabel("Nom :"));
	    template.add(name);
	    
	    template.add(new JLabel("ID :"));
	    template.add(id);
		
	    template.add(new JLabel("Hp :"));
	    template.add(hp);
		
	    template.add(new JLabel("Stage :"));
	    template.add(comboStage);

	    template.add(new JLabel("Type :"));
	    template.add(comboType);
	    if(firstType != null){
		    JButton submit = new JButton("Modifier");
		    submit.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	String pokemonName = name.getText();
		        	String hpText = hp.getText();
		        	Integer pokemonHp = Integer.parseInt(hpText);
		        	Type pokemonType = (Type)comboType.getSelectedItem();
		        	Integer pokemonStage = (Integer)comboStage.getSelectedItem();
		        	fileUtil.update(new Pokemon(pokemonName, pokemonHp ,pokemonType, pokemonStage, null), combo.getSelectedIndex());
		        	
	        		JOptionPane.showMessageDialog( frame, "Le pokemon a été modifier avec succes.", "Modifier", JOptionPane.INFORMATION_MESSAGE);
		        	
			        frame.setTitle("Accueil");
			    	itemChanged("accueil");
		        }
		    });
		    
		    template.add(submit);
		    
		    JButton delete = new JButton("Supprimer");
		    delete.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	fileUtil.delete(combo.getSelectedIndex());
		        	
	        		JOptionPane.showMessageDialog( frame, "Le pokemon a été supprimer avec succes.", "Supprimer", JOptionPane.INFORMATION_MESSAGE);
		        	
			        frame.setTitle("Accueil");
			    	itemChanged("accueil");
		        }
		    });
		    
		    template.add(delete);
	    	
	    }
	    
	    template.add(new JLabel("Recherche par nom :"));
		JTextField nameSearch = new JTextField(20);
		template.add(nameSearch);
	    
	    template.add(new JLabel("Recherche par id :"));
		JTextField idSearch = new JTextField(4);
		template.add(idSearch);
	    
	    JButton search = new JButton("Recherche");
	    search.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if(nameSearch.getText().equals("") && idSearch.getText().equals("")){
	        		JOptionPane.showMessageDialog( frame, "Le nom et est vide", "Erreur", JOptionPane.ERROR_MESSAGE);
	        	}
	        	else{
		           String pokemonName = nameSearch.getText();
		           String pokemonID = idSearch.getText();
		           boolean doubleSearch = true;
		           if(pokemonName.equals("") || pokemonID.equals("")){
		        	   doubleSearch = false;
		           }

			       int indexPokemon = -1;int index = 0;
			       for (Pokemon pokemon : fileUtil.getPokemon()) {
			    	   if(doubleSearch){
						   if(pokemon.getPokemonName().equals(pokemonName) && String.valueOf(pokemon.getId()).equals(pokemonID)){
							   indexPokemon = index;
						   }
			    	   }
			    	   else{
			    		   if(pokemon.getPokemonName().equals(pokemonName) || String.valueOf(pokemon.getId()).equals(pokemonID)){
							   indexPokemon = index;
						   }
			    		   
			    	   }
					   index++;
			       }
			       
			       if(indexPokemon == -1){
		        		JOptionPane.showMessageDialog( frame, "Pas de pokemon trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
			       }
			       else{
		        		JOptionPane.showMessageDialog( frame, "Un pokemon trouvé !" + System.lineSeparator() + "Critere de recherche :" + System.lineSeparator() + "Nom -> " + pokemonName + System.lineSeparator() + " ID -> " + pokemonID , "Recherche", JOptionPane.INFORMATION_MESSAGE);
				        combo.setSelectedIndex(indexPokemon);
			       }
	        	}
	        }
	    });
	    template.add(search);
	    
	    JButton home = new JButton("Accueil");
		home.addActionListener(new ChangeCard("accueil", "Accueil"));
		template.add(home);
		
		return template;
	}
	
	public void pokemonUpdate(){
		Component[] components = Window.cards.getComponents();

		for(int i = 0; i < components.length; i++) {
		    if(components[i].getName().equals("viewUpdatePokemon")) {
		    	Window.cards.remove(components[i]);
		        Window.cards.add(getTemplateViewUpdatePokemon("viewUpdatePokemon"), "viewUpdatePokemon");
		    }
		}   
	}
	
	public void itemChanged(String name) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, name);
        Window.frame.repaint();
    }
	
	public void display(){
        frame.setVisible(true);
	}
}

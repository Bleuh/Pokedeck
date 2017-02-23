package application;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import card.Abilitie;
import card.Pokemon;
import card.Type;
import ui.Window;

public class FileUtil {

	private String filePath;
	private List<Pokemon> pokemons;
	private Window window;
	private JSONParser parser = new JSONParser();

	public FileUtil(String name, Window window){
		this.filePath = name;
	    this.pokemons = new ArrayList<Pokemon>();
	    this.window = window;

	    this.read();
	}

	public List<Pokemon> getPokemon() {
		return pokemons;
	}

	public void read(){
		try {
			String ligne ;
			BufferedReader fichier = new BufferedReader(new FileReader(this.filePath));
			while ((ligne = fichier.readLine()) != null) {
				JSONObject pokemon = (JSONObject) parser.parse(ligne);
				String name = (String) pokemon.get("name");
			    Integer hp = Math.toIntExact((long) pokemon.get("hp"));
			    
			    JSONObject typeJson = (JSONObject) pokemon.get("type");
			    Type type = null;
			    if(!typeJson.isEmpty()){
				    type = new Type((String) typeJson.get("name"), new Color(Math.toIntExact( (long) typeJson.get("color"))));
			    }
			    
			    Integer stage = Math.toIntExact((long) pokemon.get("stage"));

			    JSONArray abilities = (JSONArray) pokemon.get("abilities");
			    List<Abilitie> listAbilitie = new ArrayList<Abilitie>();
			    if(!abilities.isEmpty()){
				    for (int n = 0; n < abilities.size(); n++){
				    	JSONObject obj = (JSONObject) abilities.get(n);
				    	listAbilitie.add(new Abilitie((String) obj.get("name"), Math.toIntExact( (long) obj.get("power")), (String) obj.get("description")));
				    }
			    }
	        	this.pokemons.add(new Pokemon(name, hp ,type, stage, listAbilitie));
			}
			fichier.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void write(Pokemon pokemon){
		JSONObject obj = jsonmaker(pokemon);
		try (FileWriter file = new FileWriter(filePath, true)) {
			file.write(obj.toJSONString() + System.lineSeparator());
			this.pokemons.add(pokemon);
			file.flush();
			System.out.println("Le pokemon a ete ajouter: " + obj);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		window.pokemonUpdate();
	}
	
	public void update(Pokemon pokemon, int index){
		JSONObject obj = jsonmaker(pokemon);
		
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(filePath));
			lines.set(index, obj.toJSONString());
			Files.write(Paths.get(filePath), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pokemons.clear();
		this.read();
		window.pokemonUpdate();
	}

	public void delete(int index) {
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(filePath));
			lines.remove(index);
			Files.write(Paths.get(filePath), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pokemons.remove(index);
		window.pokemonUpdate();
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject jsonmaker(Pokemon pokemon){
		JSONObject obj = new JSONObject();
		obj.put("name", pokemon.getPokemonName());
		obj.put("hp", pokemon.getHp());

		Type typePokemon = pokemon.getType();
		JSONObject type = new JSONObject();
		if(typePokemon != null){
			type.put("name", typePokemon.getTypeName());
			type.put("color", typePokemon.getColor().getRGB());
		}
		obj.put("type", type);

		obj.put("stage", pokemon.getStage());

		List<Abilitie> abilitiesPokemon = pokemon.getListAbilitie();
		JSONArray abilities = new JSONArray();
		if(abilitiesPokemon != null){
			for (Abilitie abilitiePokemon : abilitiesPokemon){
				JSONObject abilitie = new JSONObject();
				abilitie.put("name", abilitiePokemon.getAbilitieName());
				abilitie.put("power", abilitiePokemon.getPower());
				abilitie.put("description", abilitiePokemon.getDescription());
				abilities.add(abilitie);
			}
		}
		obj.put("abilities", abilities);
		return obj;
		
	}

}

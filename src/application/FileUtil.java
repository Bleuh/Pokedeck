package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import card.Abilitie;
import card.Pokemon;
import card.Type;

public class FileUtil {
	
	private String filePath;
	private List<Pokemon> pokemons;
	private JSONParser parser = new JSONParser();
	
	public FileUtil(String name){
		this.filePath = name;
	    this.pokemons = new ArrayList<Pokemon>();
	    
//	    this.read();
	}
	
	public List getPokemon() {
		return pokemons;
	}
	
	public void read(){
		try {
			Object obj = (JSONArray)parser.parse(new FileReader(filePath));   
			JSONObject jsonObject = (JSONObject) obj;
//			for (Object jsonObjet : jsonArray){
//			    JSONObject pokemon = (JSONObject) jsonObjet;
//
//			    String name = (String) pokemon.get("name");
//			    System.out.println(name);
//			    Integer hp = (Integer) pokemon.get("hp");
//			    System.out.println(hp);
//			    Type type = (Type) pokemon.get("type");
//			    System.out.println(type);
//			    Integer stage = (Integer) pokemon.get("stage");
//			    System.out.println(stage);
//
//			    JSONArray abilities = (JSONArray) pokemon.get("abilities");
//			    List<Abilitie> listAbilitie = new ArrayList<Abilitie>();
//			    if(abilities != null){
//				    for (Object abilitie : abilities){
//				      listAbilitie.add((Abilitie) abilitie);
//				    }
//			    }
//			    System.out.println(listAbilitie);
//	        	this.pokemons.add(new Pokemon(name, hp ,type, stage, listAbilitie));
//			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void write(Pokemon pokemon){
		JSONObject obj = new JSONObject();
		obj.put("name", pokemon.getPokemonName());
		obj.put("hp", pokemon.getHp());
		
		Type typePokemon = pokemon.getType();
		JSONArray type = new JSONArray();
		if(typePokemon != null){
			type.add("name:" + typePokemon.getTypeName());
			type.add("color:" + typePokemon.getColor());
		}
		obj.put("type", type);
		
		List<Abilitie> abilitiesPokemon = pokemon.getListAbilitie();
		JSONObject abilities = new JSONObject();
		if(abilitiesPokemon != null){
			for (Abilitie abilitiePokemon : abilitiesPokemon){
				JSONArray abilitie = new JSONArray();
				abilitie.add("name" + abilitiePokemon.getAbilitieName());
				abilitie.add("power" + abilitiePokemon.getPower());
				abilitie.add("description" + abilitiePokemon.getDescription());
				abilities.put("", abilitie);
			}
		}
		obj.put("abilities", abilities);
		
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
			this.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

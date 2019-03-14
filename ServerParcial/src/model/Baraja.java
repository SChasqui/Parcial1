package model;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
	
	private ArrayList<Carta> miBaraja; 
	
	public Baraja() {
		// TODO Auto-generated constructor stub
	}
	
	private void crearBaraja(){
	      String numero[] = { "As", "2", "3", "4", "5", "6", 
	         "7", "8", "9", "10", "J", "Q", "R" };
	      String palos[] = { "C", "D", "T", "E" };
	      
	        for(int cuenta=0;cuenta<52;cuenta++  ){
	            this.miBaraja.add(new Carta(numero[cuenta%13],palos[cuenta/13]));
	        }
	        Collections.shuffle(this.miBaraja);
	}
}

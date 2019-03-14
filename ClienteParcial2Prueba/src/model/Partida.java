package model;

import java.util.ArrayList;

public class Partida {
	
	public ArrayList<String> cartas;
	
	public Partida(String carta1, String carta2) {
		// TODO Auto-generated constructor stub
		cartas = new ArrayList<String>();
		cartas.add(carta1);
		cartas.add(carta2);
	}
}

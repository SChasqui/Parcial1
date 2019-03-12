package model;

import java.util.ArrayList;

public class Partida {
	
	public String ipTurnoActual;
	public ArrayList masoPublico;
	public ArrayList<Jugador> jugadores;
	public int contadorTurnos;
	
	public void hacerJugada(String jug, String carta) {
		
		for (int i = 0; i < jugadores.size(); i++) {
			if(jug.equals(jugadores.get(i).getIP())){
				jugadores.get(i).añadirCartaAMaso(carta);
				
				if(contadorTurnos == 2) {
					contadorTurnos = 0;
				}else {
					contadorTurnos++;
				}
			}
		}
	}
	
	
	public String getJugadorDeTurno() {
		return jugadores.get(contadorTurnos).getIP();
	}

}

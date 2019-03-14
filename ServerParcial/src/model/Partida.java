package model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Partida {
	
	public String ipTurnoActual;
	public ArrayList<Jugador> jugadores;
	public int contadorTurnos;
	public ArrayList<Carta> miBarajaOriginal;
	public ArrayList<Carta> masoPublico;
	public Random random;
	public int cartasReveladas;
	public String jugadorDeTurno;
	
	public Partida(Jugador jugador1, Jugador jugador2, Jugador jugador3) {
		
		miBarajaOriginal = new ArrayList<Carta>();
		this.crearBaraja();
		jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		
		masoPublico = new ArrayList<Carta>(5);
		random = new SecureRandom();
		contadorTurnos = 0;
		inicializarMasos();
	}
	
	public void inicializarMasos() {
		for (int i = 0; i < jugadores.size(); i++) {
			if(jugadores.get(i) != null) {
				
				if(jugadores.get(i) != null) {
					int indiceCarta1 = random.nextInt(miBarajaOriginal.size());
					Carta tempCarta1 = miBarajaOriginal.get(indiceCarta1);
					miBarajaOriginal.remove(indiceCarta1);
					int indiceCarta2 = random.nextInt(miBarajaOriginal.size());
					Carta tempCarta2 = miBarajaOriginal.get(indiceCarta2);
					miBarajaOriginal.remove(indiceCarta2);
					jugadores.get(i).añadirCartasAMaso(tempCarta1, tempCarta2);
					
				}
			}
		}
		
		for (int i = 0; i < 3; i++) {
			int randomInt = random.nextInt(miBarajaOriginal.size());
			masoPublico.add(miBarajaOriginal.get(randomInt));
			miBarajaOriginal.remove(randomInt);
		}
		masoPublico.add(null);
		masoPublico.add(null);
		
	}
	
	public ArrayList<String> getMasoPublicoString(){
		
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < masoPublico.size(); i++) {
			if(masoPublico.get(i)!= null) {
				
				arr.add(masoPublico.get(i).getNumero()+masoPublico.get(i).getPalo());
			}else {
				arr.add(null);
			}
		}

		
		return arr;
	}
	
	
	public void actualizarEstadoJugador(String jug, String estado) {
		
		for (int i = 0; i < jugadores.size(); i++) {
			if(jug.equals(jugadores.get(i).getID())){
				
				jugadores.get(i).setEstado(estado);
				
				if(jugadores.get(2) != null) {
					if(contadorTurnos == 2) {
						//Envie broadcast con la nueva carta revelada
						//Revelar nueva carta
						
						contadorTurnos =0;
					}else {
						contadorTurnos++;
					}
				}else {
					if(contadorTurnos == 1) {
						contadorTurnos = 0;
					}else {
						contadorTurnos++;
					}
				}
				
			}
		}
	}
	

	public int getContadorTurnos() {
		return contadorTurnos;
	}

	public void setContadorTurnos(int contadorTurnos) {
		this.contadorTurnos = contadorTurnos;
	}

	private void crearBaraja(){
	      String numero[] = { "As", "2", "3", "4", "5", "6", 
	         "7", "8", "9", "10", "J", "Q", "R" };
	      String palos[] = { "C", "D", "T", "E" };
	      
	        for(int cuenta=0;cuenta<52;cuenta++  ){
	            this.miBarajaOriginal.add(new Carta(numero[cuenta%13],palos[cuenta/13]));
	        }
	        Collections.shuffle(this.miBarajaOriginal);
	}
	
	public ArrayList<Jugador> getJugadores(){
		return this.jugadores;
	}

	public int getCartasReveladas() {
		return cartasReveladas;
	}

	public void setCartasReveladas(int cartasReveladas) {
		this.cartasReveladas = cartasReveladas;
	}
	
	public void setJugadorDeTurno() {
		jugadorDeTurno = jugadores.get(0).getID();
	}
	
	public String getJugadorDeTurno() {
		
		return jugadores.get(contadorTurnos).getID();
	}
	
	
	
//	public String getJugadorDeTurno() {
//		return jugadores.get(contadorTurnos).getIP();
//	}
	

}

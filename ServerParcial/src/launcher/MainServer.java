package launcher;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import communication.Connection;
import communication.TCPConnection;
import communication.TCPConnection.ConnectionEvent;
import model.Jugador;
import model.Partida;

public class MainServer implements ConnectionEvent{
	
	private TCPConnection manager; 
	//PST =  Protocolo de solucitud de turno
	public final static String PST = "solTurno";
	private static Partida partida;
	private int turno;
	private String ipDeTurno;
	private boolean serverLleno;
	private ArrayList<String> jugadores;
	
	public static void main(String[] args) {
		
		MainServer m = new MainServer();
		m.execute();
	}

	public void execute() {
		manager = TCPConnection.getInstance();
		manager.addConnectionEvent(this);
		
		
		new Thread(  ()->manager.waitForConnection(5000)  ).start();
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
//			
//			
//			//Broadcast
//			String line = scanner.nextLine();
//			manager.sendBroadcast(line);
		}
		
	}

	@Override
	public void onConnection() {
		
		System.out.println("Clientes: " + manager.getClientsCount());
		
	}

	@Override
	public void onMessage(String uuid, String msj) {
//		// TODO Auto-generated method stub
		String[] splitMessage = msj.split("-");
		
		if(splitMessage[0].equals("updateEstado")) {
			partida.actualizarEstadoJugador(uuid, splitMessage[1]);
			
		}
		
//		System.out.println("Mensaje recibido: " + msj);
//		String[] msjSplit = msj.split("-");
//		if(msjSplit[0].equals(PST)) {
//			
//			ipDeTurno = partida.getJugadorDeTurno();
//			if(ipDeTurno.equals(msjSplit[1])) {
//				partida.hacerJugada(msjSplit[1],msjSplit[2]);
//			}
//			
//		}
		
	}

	@Override
	public void onReady() {
		// TODO Auto-generated method stub
		encenderCronometro();
		
		
	}


	@Override
	public void onFull() {
		// TODO Auto-generated method stub
		serverLleno = true;
	}
	
	private void encenderCronometro() {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run() {
				long timeInicio = System.currentTimeMillis();
				long timeFinal = 0;
				
				while(timeFinal - timeInicio <= 30000 && !serverLleno) {
					timeFinal =  System.currentTimeMillis();
				}
				
				//Cuando termina el hilo: O se inicia con los dos jugadores 
				//O hay un nuevo jugador
				//Iniciar partida
				jugadores = manager.getUuids();
				if(jugadores.size() == 2) {
					jugadores.add(null);
				}
				partida = new Partida(new Jugador(jugadores.get(0)), new Jugador(jugadores.get(1)), new Jugador(jugadores.get(2)));
				manager.sendDirectMessage(jugadores.get(0), "reparticion-" +partida.getJugadores().get(0).getMasoPrivado().get(0).getNumero() +partida.getJugadores().get(0).getMasoPrivado().get(0).getPalo() + "-"+partida.getJugadores().get(0).getMasoPrivado().get(1).getNumero()+partida.getJugadores().get(0).getMasoPrivado().get(1).getPalo());
				manager.sendDirectMessage(jugadores.get(1), "reparticion-"+ partida.getJugadores().get(1).getMasoPrivado().get(0).getNumero() +partida.getJugadores().get(1).getMasoPrivado().get(0).getPalo()+ "-"+partida.getJugadores().get(1).getMasoPrivado().get(1).getNumero()+partida.getJugadores().get(1).getMasoPrivado().get(1).getPalo() );
				if(jugadores.get(2)!= null) {
					manager.sendDirectMessage(jugadores.get(2), "reparticion-"+partida.getJugadores().get(2).getMasoPrivado().get(0).getNumero()+partida.getJugadores().get(2).getMasoPrivado().get(0).getPalo() + "-"+partida.getJugadores().get(2).getMasoPrivado().get(1).getNumero()+partida.getJugadores().get(2).getMasoPrivado().get(1).getPalo());
				}
				
				ArrayList<String> temp = partida.getMasoPublicoString();
				manager.sendBroadcast("masopublico-"+temp.get(0)+"-"+temp.get(1)+"-"+temp.get(2)+"-"+temp.get(3)+"-"+temp.get(4));
				
				
				while(partida.getCartasReveladas() !=5) {
					manager.sendDirectMessage(partida.getJugadorDeTurno(), "autorizacionUpdate");
					//Aquí el de turno me responde
//					actualizoConRespuesta
				}
			}
		}.start();
		

	}
	
}

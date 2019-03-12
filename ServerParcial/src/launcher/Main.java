package launcher;

import java.util.Scanner;

import communication.Connection;
import communication.TCPConnection;
import communication.TCPConnection.ConnectionEvent;
import model.Partida;

public class Main implements ConnectionEvent{
	
	private TCPConnection manager; 
	//PST =  Protocolo de solucitud de turno
	public final static String PST = "solTurno";
	private static Partida partida;
	private int turno;
	private String ipDeTurno;
	
	
	public static void main(String[] args) {
		
		Main m = new Main();
		m.execute();
	}

	public void execute() {
		manager = TCPConnection.getInstance();
		manager.addConnectionEvent(this);
		
		
		new Thread(  ()->manager.waitForConnection(5000)  ).start();
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			
			//Broadcast
			String line = scanner.nextLine();
			manager.sendBroadcast(line);
		}
		
	}

	@Override
	public void onConnection() {
		
		System.out.println("Clientes: " + manager.getClientsCount());
		
	}

	@Override
	public void onMessage(String uuid, String msj) {
		// TODO Auto-generated method stub
		String[] msjSplit = msj.split("-");
		if(msjSplit[0].equals(PST)) {
			
			ipDeTurno = partida.getJugadorDeTurno();
			if(ipDeTurno.equals(msjSplit[1])) {
				partida.hacerJugada(msjSplit[1],msjSplit[2]);
			}
			
		}
		
	}
}

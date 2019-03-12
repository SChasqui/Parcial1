package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.UUID;

import communication.TCPConnection.ConnectionEvent;

public class Connection {
	
	//Si tengo diez clientes, voy a  tener 10 conjutnos de estos elementos, por eso me los traigo a Connection
	private String uuid;
	private Socket socket;
	private Receptor receptor;
	private Emisor emisor;
	private List<ConnectionEvent> listeners;
	
	
	public Connection(Socket socket) {
		
		uuid = UUID.randomUUID().toString();
		this.socket = socket;
	}
	
	public void defineListeners(List<ConnectionEvent> listeners) {
		this.listeners = listeners;
	}


	public void init() {
		try{
			
			receptor = new Receptor(socket.getInputStream());
			receptor.setUuid(uuid);
			receptor.defineListeners(listeners);
			receptor.start();
			emisor = new Emisor(socket.getOutputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendMessage(String msj) {
		emisor.enviarMensaje(msj);
	}
	
	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public String getUuid() {
		
		return uuid;
	}
	
}

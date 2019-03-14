package communication;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TCPConnection {
	
	private static TCPConnection instance = null;
	
	private TCPConnection() {
		listeners = new ArrayList<>();
		connections = new HashMap<String, Connection>();
	}
	
	public synchronized static TCPConnection getInstance() {
		if(instance == null) {
			instance = new TCPConnection();
		}
		return instance;
	}
	

	
	//Si tengo diez clientes, voy a  tener 10 conjutnos de estos elem
	private ServerSocket server;
	private HashMap<String, Connection> connections;
	private List<ConnectionEvent> listeners;

	
	//Metodo del servidor
	public void waitForConnection(int port) {
		try {
			
			server = new ServerSocket(port);
			
			while(true) {
				
				if(connections.size()<=3) {
					
					System.out.println("Esperando cliente");
					Socket socket = server.accept();
					System.out.println("Cliente conectado!");
					Connection connection =  new Connection(socket);
					connection.defineListeners(listeners);
					connection.init();
					
					connections.put(connection.getUuid(), connection);
					if(getClientsCount() == 2) {
						System.out.println("Conectandose con dos clientes");
						for(int i=0 ; i<listeners.size() ; i++) listeners.get(i).onReady();
					}else if(getClientsCount() == 3) {
						for(int i=0 ; i<listeners.size() ; i++) listeners.get(i).onFull();
					}
					for(int i=0 ; i<listeners.size() ; i++) listeners.get(i).onConnection();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//Hacer la clase sea observable
	public interface ConnectionEvent{
		void onConnection();
		void onMessage(String uuid, String msj);
		void onReady();
		void onFull();
	}
	
	
	public void addConnectionEvent(ConnectionEvent listener) {
		listeners.add(listener);
	}

	public int getClientsCount() {
		
		return connections.size();
	}

	public void sendBroadcast(String line) {
		// TODO Auto-generated method stub
		for(String key : connections.keySet()) {
			connections.get(key).sendMessage(line);
		}
	}

	public Connection getConnectionById(String uuid) {
		// TODO Auto-generated method stub
		return connections.get(uuid);
	}

	public void sendDirectMessage(String destinatario, String mensaje) {
		
		getConnectionById(destinatario).sendMessage(mensaje);
	}
	
	public ArrayList<String> getUuids() {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		for(String key : connections.keySet()) {
			list.add(key);
		}
		
		return list;
	}
	
	
	//Estructura de los mensajes TCP
	
	
	
}

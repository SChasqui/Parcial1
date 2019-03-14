package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import launcher.MainServer;

public class MainTest {
	
	
	@Test
	public void cronometroTest() {
		//Cuando no existía un hilo para cronometrar, lo que hacía era medir el tiempo
		//Y que ese tiempo al finalizar el método fuera de 30 segundos
		//Pero como ahora existe un hilo, el tiempo en el que termina el método debe ser muy corto.
		MainServer ms = new MainServer();
		long tiempoAntes = System.currentTimeMillis();
		ms.onReady();
		long tiempoDespues = System.currentTimeMillis();
		assertTrue(tiempoDespues - tiempoAntes <= 29991);
	}
}

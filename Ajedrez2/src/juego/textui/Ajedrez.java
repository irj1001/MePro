package juego.textui;

import java.util.Scanner;

import juego.control.Arbitro;
import juego.modelo.Celda;
import juego.modelo.CoordenadasIncorrectasException;
import juego.modelo.Tablero;

/**
 * Ajedrez en modo texto.
 * 
 * Se abusa del uso de static tanto en atributos como en métodos para comprobar
 * su similitud a variables globales y funciones globales de otros lenguajes.
 *
 * @author Irene Ruiz
 * @since 1.0
 * @version 1.0
 */
public class Ajedrez {
	 // Cambiar el nombre del fichero y de la clase por "Ajedrez.java" y "Ajedrez" respectivamente


	/** Tamaño en caracteres de una jugada. */
	private static final int TAMAÑO_JUGADA = 4;

	/**
	 * Tablero.
	 */
	private static Tablero tablero;

	/**
	 * Arbitro.
	 */
	private static Arbitro arbitro;

	/**
	 * Lector por teclado.
	 */
	private static Scanner scanner;

	/**
	 * metodo raiz.
	 * @param args args
	 */
	public static void main(String[] args) {
		try {
			boolean acabar = false;
			String jugada;
			mostrarMensajeBienvenida();
			inicializarPartida();

			do {
				mostrarTableroEnPantalla();
				jugada = recogerJugada();
				if (comprobarSiFinalizaUsuarioPartida(jugada)) {
					finalizarPartida();
					acabar=true;
				} else {
					if (validarFormato(jugada)) {
						realizarJugada(jugada);
					} else {
						mostrarErrorEnFormatoDeEntrada();
					}
				}

			} while (acabar==false);
		} catch (RuntimeException ex) {
			mostrarInformacionErrorGrave(ex);
		}
	}

	/**
	 * Muestra en pantalla la información de error asociada a una excepción 
	 * que interrumpe la ejecución correcta del programa.
	 * 
	 * @param ex excepción ocurrida
	 */
	private static void mostrarInformacionErrorGrave(Exception ex) {
		System.err.println("Error grave en ejecución en modo texto, no se puede proseguir con la ejecución.");
		System.err.println("Revise la traza del error mostrada a continuación y corrija el error.");
		ex.printStackTrace();
	}

	/**
	 * Realiza la jugada introducida por teclado realizando las correspondientes
	 * comprobaciones relativas a las reglas del juego. Se supone que la jugada en
	 * cuanto al formato ya ha sido validada previamente.
	 * 
	 * @param jugada jugada
	 */
	private static void realizarJugada(String jugada) {
		assert validarFormato(jugada) : "Jugada debería estar validada previamente.";
		try {
			Celda origen = leerOrigen(jugada);
			Celda destino = leerDestino(jugada);

			if (arbitro.esMovimientoLegal(origen, destino)) { // si el movimiento es legal
				if (arbitro.estaEnJaqueTrasSimularMovimientoConTurnoActual(origen, destino)) {
					// si deja al descubierto al rey
					System.out.println("No puede realizar el movimiento porque deja en jaque al rey.");
				} else {
					arbitro.mover(origen, destino);
					arbitro.cambiarTurno();
					if (arbitro.estaEnJaque(arbitro.obtenerTurno())) {
						// si el rey del turno actual está
						// amenazado
						System.out.println("\nJaque al rey\n");
					}
				}
			} else {
				System.out.println("Movimiento ilegal.");
			}
		} catch (CoordenadasIncorrectasException ex) {
			// si las coordenadas de alguna de las celdas son incorrectas...
			// hay algún error grave de programación.
			throw new RuntimeException("Error de implementación.", ex);
		}
	}

	/**
	 * Muestra el mensaje de bienvenida con instrucciones para finalizar la partida.
	 */
	private static void mostrarMensajeBienvenida() {
		System.out.println("Bienvenido al juego del Ajedrez");
		System.out.println(
				"Para finalizar en tablas o rendirse introduzca " + "\"tablas\" o \"rendicion\" respectivamente.");
		System.out.println("Disfrute de la partida...");
	}

	/**
	 * Mostrar al usuario información de error en el formato de entrada, mostrando
	 * ejemplos.
	 */
	private static void mostrarErrorEnFormatoDeEntrada() {
		System.out.println();
		System.out.println("Error en el formato de entrada.");
		System.out.println("El formato debe ser letranumeroletranumero, por ejemplo a7a5 o g1f3");
		System.out.println("Las letras deben estar en el rango [a,h] y los números en el rango [1,8]\n");
	}

	/**
	 * Comprueba si el usuario quiere finalizar la partida.
	 * 
	 * @param jugada jugada en formato texto
	 * @return true si el usuario introduce tablas o salir, false en caso contrario
	 */
	private static boolean comprobarSiFinalizaUsuarioPartida(String jugada) {
		return jugada.equalsIgnoreCase("tablas") || jugada.equalsIgnoreCase("rendicion");
	}

	/**
	 * Finaliza la partida informando al usuario y cerrando recursos abiertos.
	 */
	private static void finalizarPartida() {
		System.out.println("Partida finalizada.");
		scanner.close();
	}

	/**
	 * Muestra el tablero en pantalla con su estado actual.
	 */
	private static void mostrarTableroEnPantalla() {
		System.out.println(tablero.toString());
		System.out.println();
	}

	/**
	 * Inicializa el estado de los elementos de la partida.
	 */
	private static void inicializarPartida() {
		// Inicializaciones
		tablero = new Tablero();
		arbitro = new Arbitro(tablero);
		// Cargar figuras...
		arbitro.colocarPiezas();
		// Abrimos la lectura desde teclado
		scanner = new Scanner(System.in);
	}

	/**
	 * Recoge jugada del teclado.
	 * 
	 * @return jugada jugada en formato texto
	 */
	private static String recogerJugada() {
		System.out.print("Introduce jugada el jugador con color " + arbitro.obtenerTurno() + " (máscara cfcf): ");
		return scanner.next();
	}

	/**
	 * Valida la corrección del formato de la jugada. Solo comprueba la corrección
	 * del formato de entrada en cuanto al tablero, no la validez de la jugada en
	 * cuanto a las reglas del ajedrez. La jugada tiene que tener cuatro caracteres
	 * y contener letras y números de acuerdo a las reglas de la notación
	 * algebraica.
	 * 
	 * Otra mejor solución alternativa es el uso de expresiones regulares (se verán
	 * en la asignatura de 3º Procesadores del Lenguaje).
	 * 
	 * @param jugada a validar
	 * @return true si el formato de la jugada es correcta según las coordenadas
	 *         disponibles del tablero
	 */
	private static boolean validarFormato(String jugada) {
		boolean estado = true;
		if (jugada.length() != TAMAÑO_JUGADA || esLetraInvalida(jugada.charAt(0))
				|| esLetraInvalida(jugada.charAt(2))
				|| esNumeroInvalido(jugada.charAt(1))
				|| esNumeroInvalido(jugada.charAt(3))) {
			estado = false;
		}
		return estado;
	}
	
	/**
	 * Comprueba si la letra está fuera del rango [a,h].
	 * 
	 * @param letra letra a comprobar
	 * @return true si la letra no está en el rango, false en caso contrario
	 */
	private static boolean esLetraInvalida(char letra) {
		return letra < 'a' || letra > 'h';
	}
	
	/**
	 * Comprueba si el número (en formato letra) está fuera del rango [1,8].
	 * 
	 * @param numero numero
	 * @return true si el número no está en el rango, false en caso contrario
	 */
	private static boolean esNumeroInvalido(char numero) {
		return numero < '1' || numero > '8';
	}

	/**
	 * Obtiene la celda origen.
	 * 
	 * @param jugada jugada en formato notación algebraica (e.g. a1)
	 * @return celda origen o null si no es posible extraerla
	 */
	private static Celda leerOrigen(String jugada) {
		try {
			if (jugada.length() != TAMAÑO_JUGADA)
				return null;
			String textoOrigen = jugada.substring(0, TAMAÑO_JUGADA-2); // dos primeros caracteres
			return tablero.obtenerCeldaParaNotacionAlgebraica(textoOrigen);
		} catch (CoordenadasIncorrectasException ex) {
			throw new RuntimeException("Error de programación en validación previa.", ex);
		}
	}

	/**
	 * Obtiene la celda destino.
	 * 
	 * @param jugada jugada en formato notación algebraica (e.g. a1)
	 * @return celda destino o null si no es posible extraerla
	 */
	private static Celda leerDestino(String jugada) {
		try {
			if (jugada.length() != TAMAÑO_JUGADA)
				return null;
			String textoOrigen = jugada.substring(TAMAÑO_JUGADA-2, TAMAÑO_JUGADA); // dos últimos caracteres
			return tablero.obtenerCeldaParaNotacionAlgebraica(textoOrigen);
		} catch (CoordenadasIncorrectasException ex) {
			throw new RuntimeException("Error de programación en validación previa.", ex);
		}
	}

}

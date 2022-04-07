package juego.modelo;
/**
 * Excepción comprobable que se da cuando la celda no pertenece al tablero.
 * @author Irene Ruiz
 *
 */
@SuppressWarnings("serial")
public class CoordenadasIncorrectasException extends Exception{
	/**
	 * Constructor.
	 */
	public CoordenadasIncorrectasException() {
		
		super();
	}
	/**
	 * constructor.
	 * @param message mensaje
	 */
	public CoordenadasIncorrectasException(String message){
		super(message);
	}
	/**
	 * Constructor.
	 * @param cause causa
	 */
	public CoordenadasIncorrectasException(Throwable cause) {
		super(cause);
	}
	/**
	 * Constructor.
	 * @param message mensaje
	 * @param cause causa
	 */
	public CoordenadasIncorrectasException(String message, Throwable cause) {
		super(message,cause);
	}
}
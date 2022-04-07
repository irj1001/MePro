package juego.modelo.pieza;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Interfaz de pieza.
 * @author Irene Ruiz
 *
 */

public interface Pieza{
	/**
	 * comprueba si el movimiento es correcto.
	 * @param destino celda de destino
	 * @param sentido sentido del movimiento
	 * @param hayPiezasEntreMedias si hay piezas en medio
	 * @return si se puede mover
	 */
	public abstract boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias) ;
	/**
	 * comprueba si es el primer movimiento de la pieza.
	 * @return si es primer movimiento
	 */
	public abstract boolean esPrimerMovimiento() ;
	/**
	 * establece la celda.
	 * @param celda celda
	 */
	public abstract void establecerCelda(Celda celda);
	/**
	 * marca el primer movimiento de la pieza.
	 */
	public abstract void marcarPrimerMovimiento() ;
	/**
	 * obtiene la celda. 
	 * @return celda celda
	 */
	public abstract Celda obtenerCelda();
	/**
	 * obtiene el color.
	 * @return color color
	 */
	public abstract Color obtenerColor();
	/**
	 * devuelve el estado de la pieza.
	 * @return el estado
	 */
	public abstract char toChar() ;
	
}
package juego.modelo.pieza;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Pieza abstracta.
 * @author Irene Ruiz
 *
 */

public abstract class PiezaAbstracta implements Pieza{
	/**
	 * color.
	 */
	private Color color;
	/**
	 * celda.
	 */
	private Celda celda;
	/**
	 * si es primer movimiento.
	 */
	public boolean primerMovimiento;
	/**
	 * constructor.
	 * @param color de la pieza
	 */
	public PiezaAbstracta(Color color) {
		this.color=color;
		primerMovimiento=true;
	}
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
	public boolean esPrimerMovimiento() {
		return primerMovimiento;
	}
	/**
	 * establece la celda.
	 * @param celda celda
	 */
	public void establecerCelda(Celda celda) {
		this.celda=celda;
	}
	/**
	 * marca el primer movimiento de la pieza.
	 */
	public void marcarPrimerMovimiento() {
		if (primerMovimiento=true) {
			primerMovimiento=false;
		}
	}
	/**
	 * obtiene la celda. 
	 * @return celda celda
	 */
	public Celda obtenerCelda() {
		return celda;
	}
	/**
	 * obtiene el color.
	 * @return color color
	 */
	public Color obtenerColor() {
		return color;
	}
	/**
	 * devuelve el estado de la pieza.
	 * @return el estado
	 */
	public String toString() {
		return toChar()+"-"+obtenerColor()+"-"+esPrimerMovimiento();
	}
	/**
	 * devuelve el desplazamiento de filas.
	 * @param destino celda destino
	 * @return desplazamiento
	 */
	int desplazamientoFilas(Celda destino) {
		return destino.obtenerFila()-celda.obtenerFila();
	}
	/**
	 * devuelve el desplazamiento de columnas.
	 * @param destino celda destino
	 * @return desplazamiento
	 */
	int desplazamientoColumnas(Celda destino) {
		return destino.obtenerColumna()-celda.obtenerColumna();
	}
	
}
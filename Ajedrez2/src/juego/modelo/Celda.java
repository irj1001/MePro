package juego.modelo;

import juego.modelo.pieza.Pieza;
/**
 * Establece las celdas.
 * @author Irene Ruiz
 *
 */
public class Celda{
	/**
	 * fila de la celda.
	 */
	private int fila;
	/**
	 * columna de la celda.
	 */
	private int columna;
	/**
	 * pieza asociada a la celda.
	 */
	private Pieza pieza;
	/**
	 * Constructor.
	 * @param fila fila
	 * @param columna columna
	 */
	public Celda(int fila, int columna) {
		this.fila=fila;
		this.columna=columna;
	}
	/**
	 * elimina la pieza asociada a la celda.
	 */
	public void eliminarPieza() {
		this.pieza=null;
	}
	/**
	 * establece la pieza.
	 * @param pieza pieza
	 */
	public void establecerPieza(Pieza pieza) {
		this.pieza=pieza;
	}
	/**
	 * comprueba si esta vacia.
	 * @return si esta vacia
	 */
	public boolean estaVacia() {
		return obtenerPieza()==null;
	}
	/**
	 * obtiene el color de la pieza de la celda.
	 * @return el color
	 */
	public Color obtenerColorDePieza() {
		if(estaVacia()) {
			return null;
		}
		else {
		return pieza.obtenerColor();
		}
	}
	/**
	 * devuelve la columna.
	 * @return columna columna
	 */
	public int obtenerColumna() {
		return columna;
	}
	/**
	 * devuelve la fila.
	 * @return fila fila
	 */
	public int obtenerFila() {
		return fila;
	}
	/**
	 * devuelve la pieza.
	 * @return pieza pieza
	 */
	public Pieza obtenerPieza() {
		return pieza;
	}
	/**
	 * comprueba si la celda tiene las mismas coordenadas.
	 * @param celda celda
	 * @return si son iguales
	 */
	public boolean tieneCoordenadasIguales(Celda celda) {
		if (celda.fila==this.fila&&celda.columna==this.columna) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * devuelve el estado.
	 * @return el estado de la celda
	 */
	public String toString() {
		return "("+fila+"/"+columna+")";
	}
	
}
package juego.modelo;

/**
 * Establece las celdas del tablero y las caracteristicas de las piezas en las celdas.
 * 
 * @author Irene Ruiz, Álvaro López
 * @since JDK 11
 * @version 1.3
 *
 */

public class Celda  {
	/**
	 * fila.
	 */
	private int fila;
	/**
	 * columna.
	 */
	private int columna;
	/**
	 * Pieza.
	 */
	private Pieza pieza;
	/**
	 * Constructor.
	 * @param fila fila
	 * @param columna fila
	 */
	public Celda(int fila, int columna) {
		this.fila=fila;
		this.columna=columna;
	}

/**
 * Elimina una pieza del tablero.
 */
public void eliminarPieza() {
	this.pieza=null;
	
}
/**
 * Establece la pieza en la celda.
 * @param pieza pieza
 */
public void establecerPieza(Pieza pieza) {
	this.pieza=pieza;
	
}

/**
 * Indica si la celda esta vacia o no.
 * @return si hay pieza en la celda
 */
public boolean estaVacia() {
	/*boolean flag=false;
	if (obtenerPieza()==null) {
		flag=true;
	}
	return flag;*/
	return obtenerPieza()==null;
	
}

/**
 * Obtiene la fila de la celda.
 * @return la fila de la celda
 */
public int obtenerFila() {
	return fila;
	
}

/**
 * Obtiene el color de la pieza de la celda.
 * @return el color de la pieza
 */
public Color obtenerColorDePieza() {
	if(estaVacia()) {
		return null;
	}
	else {
	return pieza.obtenerColor();}
	
}
/**
 * Obtiene la columna de la celda.
 * @return la columna de la celda
 */
public int obtenerColumna() {
	
	return columna;
}
/**
 * Obtiene la pieza de la celda.
 * @return la pieza de la celda
 */
public Pieza obtenerPieza() {
	return pieza;
	
}
/**
 * compara las coordenadas de la celda actual y la pasada por argumento.
 * @param celda celda
 * @return si las celdas son iguales
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
 * devuelve las coordenadas de la celda.
 * 
 * @return las coordendas de la celda
 */
public String toString() {
	return "("+fila+"/"+columna+")";
	
}
}
package juego.modelo;
/**
 * Indica las caracteristicas de las piezas.
 * 
 * @author Irene Ruiz, Álvaro López
 * @since JDK 11
 * @version 1.3
 *
 */

public class Pieza {
	/**
	 * Color.
	 */
	private Color color;
	/**
	 * Celda.
	 */
	private Celda celda;
	/**
	 * tipo.
	 */
	private Tipo tipo;
	/**
	 * si es primer movimiento.
	 */
	private boolean primerMovimiento;
	/**
	 * Constructor.
	 * @param tipo tipo
	 * @param color color
	 */
	public Pieza(Tipo tipo,Color color) {
		establecerColor(color);
		this.tipo=tipo;
		primerMovimiento=true;
	}
/**
 * Indica si es el primer movimiento de la pieza.
 * 
 * @return si es primer movimiento
 */

public boolean esPrimerMovimiento() {
	return primerMovimiento;
	
}
 /**
  * asigna la celda pasada a la pieza.
  * @param celda celda
  */
public void establecerCelda(Celda celda) {
	this.celda=celda;
	
}
/**
 * Marca si es el primer movimiento de la pieza.
 */
public void marcarPrimerMovimiento() {
	if (primerMovimiento=true) {
		primerMovimiento=false;
	}
	
}
/**
 * Obtiene la celda de la pieza.
 * @return la celda
 */

public Celda obtenerCelda() {
	return celda;
	
}
/**
 * Obtiene el color de la pieza.
 * @return el color de la pieza
 */
public Color obtenerColor() {
	return color;
	
}
/**
 * Obtiene el tipo de la pieza.
 * @return el tipo de la pieza
 */
public Tipo obtenerTipo() {
	return tipo;
	
}
/**
 * Devuelve el estado.
 * @return el estado de la pieza
 */
public String toString() {
	return obtenerTipo()+"-"+obtenerColor()+"-"+primerMovimiento;
}
/**
 * Establece el color de la celda.
 * @param color color
 */

private void establecerColor(Color color) {
	this.color=color;
}}
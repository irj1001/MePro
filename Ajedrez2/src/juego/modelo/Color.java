package juego.modelo;

/**
 * Establece el color de la celda.
 * @author Irene Ruiz
 *
 */

public enum Color{
	/** blanco.*/
	BLANCO ('B'),
	/**negro.*/
	NEGRO ('N');
	/**
	 * caracter.
	 */
	private char caracter;
	/**Constructor.
	 * 
	 * @param car caracter
	 */
	private Color(char car) {
		caracter=car;
	}
	/**
	 * devuelve el caracter asociado a ese color.
	 * @return el caracter asociado a ese color
	 */
	char toChar() {
		return caracter;
	}
}

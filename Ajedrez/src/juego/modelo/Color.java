package juego.modelo;
/**
 * Establece el color de la celda .
 * 
 * @author Irene Ruiz, Álvaro López
 * @since JDK 11
 * @version 1.3
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



package juego.modelo;
/**
 * Establece el tipo de cada pieza.
 * 
 * @author Irene Ruiz, Álvaro López
 * @since JDK 11
 * @version 1.3
 *
 */

public enum Tipo{
	/** caballo. */
	CABALLO('C'),
	/**torre.*/
	TORRE('T'),
	/**dama.*/
	DAMA('D'),
	/**rey.*/
	REY('R'),
	/**alfil.*/
	ALFIL('A'),
	/**peon.*/
	PEON('P');
	

/**
 * letra.
 */
private char letra;

/**
 * Constructor.
 * @param letra letra
 */
private Tipo (char letra) {
	this.letra=letra;
	
	
}
/**
 * Devuelve la letra de la pieza en cuestion.
 * 
 * @return letra letra
 */
public char toChar() {
	return letra;
}

}
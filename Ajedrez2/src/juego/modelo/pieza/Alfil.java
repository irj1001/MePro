package juego.modelo.pieza;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;
/**
 * Pieza de tipo Alfil.
 * @author Irene Ruiz
 *
 */
public class Alfil extends PiezaAbstracta{
	/**
	 * Constructor.
	 * @param color del alfil
	 */
	public Alfil(Color color) {
		super(color);
	}
	
	@Override
	/**
	 * devuelve el estado de la pieza.
	 * 
	 * @return la letra de la pieza
	 */
	public char toChar() {
		return 'A';
	}

	@Override
	 /**
	  * comprueba si es correcto mover la pieza.
	  * 
	  * @param destino celda de destino
	  * @param sentido sentido del movimiento
	  * @param hayPiezasEntreMedias si hay piezas entre medias
	  * 
	  * @return si es correcto moverla
	  */
	public boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias) {
		if(sentido==Sentido.DIAGONAL_NE||sentido==Sentido.DIAGONAL_NO||sentido==Sentido.DIAGONAL_SE||sentido==Sentido.DIAGONAL_SO) {
			return !hayPiezasEntreMedias;
		}
		else {return false;}
	}
	
	
	}
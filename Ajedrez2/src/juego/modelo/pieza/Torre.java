package juego.modelo.pieza;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Pieza de tipo torre.
 * @author Irene Ruiz
 *
 */

public class Torre extends PiezaAbstracta{
	/**
	 * constructor.
	 * @param color color de la pieza
	 */
	public Torre(Color color) {
		super(color);
	}
	/**
	 * devuelve el estado de la pieza.
	 * 
	 * @return la letra de la pieza
	 */
	@Override
	public char toChar() {
		return 'T';
	}
	 /**
	  * comprueba si es correcto mover la pieza.
	  * 
	  * @param destino celda de destino
	  * @param sentido sentido del movimiento
	  * @param hayPiezasEntreMedias si hay piezas entre medias
	  * 
	  * @return si es correcto moverla
	  */
	@Override
	public boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias) {
		if(sentido==Sentido.VERTICAL_N||sentido==Sentido.VERTICAL_S||sentido==Sentido.HORIZONTAL_E||sentido==Sentido.HORIZONTAL_O) {
			return !hayPiezasEntreMedias;
		}
		else {return false;}
	}
	
	
	}
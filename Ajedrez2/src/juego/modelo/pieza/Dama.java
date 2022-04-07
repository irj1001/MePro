package juego.modelo.pieza;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * pieza de tipo dama.
 * @author Irene Ruiz
 *
 */

public class Dama extends PiezaAbstracta{
	/**
	 * Constructor.
	 * @param color color de la pieza
	 */
	public Dama(Color color) {
		super(color);
	}
	/**
	 * devuelve el estado de la pieza.
	 * 
	 * @return la letra de la pieza
	 */
	@Override
	public char toChar() {
		return 'D';
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
		if(sentido!=null&&!hayPiezasEntreMedias) {
			return true;
		}
		else {return false;}
	}
	
	
	}
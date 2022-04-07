package juego.modelo.pieza;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Pieza de tipo rey.
 * @author Irene Ruiz
 *
 */

public class Rey extends PiezaAbstracta{
	/**
	 * constructor.
	 * @param color color de la pieza
	 */
	public Rey(Color color) {
		super(color);
	}
	/**
	 * devuelve el estado de la pieza.
	 * 
	 * @return la letra de la pieza
	 */
	@Override
	public char toChar() {
		return 'R';
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
		if(Math.abs(desplazamientoFilas(destino))<2&&Math.abs(desplazamientoColumnas(destino))<2&&sentido!=null&&!hayPiezasEntreMedias) {
			return true;
		}
		else {return false;}
	}
	
	
	}
package juego.modelo.pieza;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * pieza de tipo caballo.
 * @author Irene Ruiz
 *
 */

public class Caballo extends PiezaAbstracta{
	/**
	 * constructor.
	 * @param color color de la pieza
	 */
	public Caballo(Color color) {
		super(color);
	}
	/**
	 * devuelve el estado de la pieza.
	 * 
	 * @return la letra de la pieza
	 */
	@Override
	public char toChar() {
		return 'C';
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
		if (sentido==null) {
			if((Math.abs(desplazamientoFilas(destino))==1&&Math.abs(desplazamientoColumnas(destino))==2)||(Math.abs(desplazamientoFilas(destino))==2&&Math.abs(desplazamientoColumnas(destino))==1)) {
				return true;
			}
			else {return false;}
		}
		
		else {return false;}
	}
	 
	
	}
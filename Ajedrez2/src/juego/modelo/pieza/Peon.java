package juego.modelo.pieza;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Pieza de tipo peon.
 * @author Irene Ruiz
 *
 */
public class Peon extends PiezaAbstracta{
	/**
	 * constructor.
	 * @param color color de la pieza
	 */
	public Peon(Color color) {
		super(color);
	}
	/**
	 * devuelve el estado de la pieza.
	 * 
	 * @return la letra de la pieza
	 */
	@Override
	public char toChar() {
		return 'P';
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
		if (sentido==Sentido.VERTICAL_N&&obtenerColor()==Color.BLANCO||sentido==Sentido.VERTICAL_S&&obtenerColor()==Color.NEGRO) {
			if(esPrimerMovimiento()==true) {
				return !hayPiezasEntreMedias&&(Math.abs(desplazamientoFilas(destino))==1||Math.abs(desplazamientoFilas(destino))==2);
			}
			else if(Math.abs(desplazamientoFilas(destino))==1) {
				return true;
			}
			else {return false;}
		}
		else if(((sentido==Sentido.DIAGONAL_NE&&obtenerColor()==Color.BLANCO)||(sentido==Sentido.DIAGONAL_NO&&obtenerColor()==Color.BLANCO)||(sentido==Sentido.DIAGONAL_SE&&obtenerColor()==Color.NEGRO)||(sentido==Sentido.DIAGONAL_SO&&obtenerColor()==Color.NEGRO))&&(Math.abs(desplazamientoFilas(destino))==1&&(Math.abs(desplazamientoColumnas(destino))==1))) {
			if(destino.estaVacia()==true) {
				return false;
			}
			else if(destino.obtenerColorDePieza()!=obtenerColor()) {
				return true;
			}
			else {return false;}
		}

		else {
			return false;
		}
			
			
	}
	
	}
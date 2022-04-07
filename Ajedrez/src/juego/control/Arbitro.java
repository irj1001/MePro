package juego.control;
import juego.modelo.Tablero;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.modelo.Tipo;
import juego.util.Sentido;
import juego.modelo.Pieza;
/**
 * Comprueba que se cumplan las normas del juego y determina un ganador.
 * 
 * @author Irene Ruiz, Álvaro López
 * @since JDK 11
 * @version 1.3
 *
 */

public class Arbitro {
	/**
	 * Color.
	 */
	private Color color;
	/**
	 * Tablero.
	 */
	private Tablero tablero;
	/**
	 * Número de jugada.
	 */
	private int jugada;
	
	/**
	 * Constructor.
	 * @param tablero tablero
	 */
	public Arbitro(Tablero tablero) {
		this.tablero=tablero;
		jugada=0;
		
	}
	
	/**
	 * Cambia el turno del jugador que hace el movimiento.Aumentando el numero de la jugada en la que esta.
	 */
	public void cambiarTurno() {
		if (this.color==Color.BLANCO){
			this.color=Color.NEGRO;
		}
		else if (this.color==Color.NEGRO){
			this.color=Color.BLANCO;
		}
		jugada=jugada+1;
		
		
	}
	
	/**
	 * Se colocan las diferentes piezas de los jugadores en el tablero. Peones,caballos,torres, alfiles, rey y reina.
	 */
	public void colocarPiezas() {
		for(int i=0;i<8;i++) {
			//para colocar los peones
			
			
			tablero.colocar(new Pieza(Tipo.PEON, Color.BLANCO), tablero.obtenerCelda(6,i));
			
			
			tablero.colocar(new Pieza(Tipo.PEON, Color.NEGRO), tablero.obtenerCelda(1,i));
		}
		
		//Torres
		tablero.colocar(new Pieza(Tipo.TORRE, Color.NEGRO), tablero.obtenerCelda(0,0));
		tablero.colocar(new Pieza(Tipo.TORRE, Color.NEGRO), tablero.obtenerCelda(0,7));
		tablero.colocar(new Pieza(Tipo.TORRE, Color.BLANCO), tablero.obtenerCelda(7,7));
		tablero.colocar(new Pieza(Tipo.TORRE, Color.BLANCO), tablero.obtenerCelda(7,0));
		
		//Caballo
		tablero.colocar(new Pieza(Tipo.CABALLO, Color.NEGRO), tablero.obtenerCelda(0,1));
		tablero.colocar(new Pieza(Tipo.CABALLO, Color.NEGRO), tablero.obtenerCelda(0,6));
		tablero.colocar(new Pieza(Tipo.CABALLO, Color.BLANCO), tablero.obtenerCelda(7,1));
		tablero.colocar(new Pieza(Tipo.CABALLO, Color.BLANCO), tablero.obtenerCelda(7,6));
		
		//Alfil
		tablero.colocar(new Pieza(Tipo.ALFIL, Color.NEGRO), tablero.obtenerCelda(0,2));
		tablero.colocar(new Pieza(Tipo.ALFIL, Color.NEGRO), tablero.obtenerCelda(0,5));
		tablero.colocar(new Pieza(Tipo.ALFIL, Color.BLANCO), tablero.obtenerCelda(7,2));
		tablero.colocar(new Pieza(Tipo.ALFIL, Color.BLANCO), tablero.obtenerCelda(7,5));
		
		//Rey y dama
		tablero.colocar(new Pieza(Tipo.REY, Color.NEGRO), tablero.obtenerCelda(0,4));
		tablero.colocar(new Pieza(Tipo.DAMA, Color.NEGRO), tablero.obtenerCelda(0,3));
		tablero.colocar(new Pieza(Tipo.REY, Color.BLANCO), tablero.obtenerCelda(7,4));
		tablero.colocar(new Pieza(Tipo.DAMA, Color.BLANCO), tablero.obtenerCelda(7,3));
		this.color=Color.BLANCO;
		
		
		
	}
	
	/**
	 * Crea y Coloca todas las piezas indicadas previamente al comenzar la partida.
	 * 
	 * @param tipo de la pieza
	 * @param color de la pieza
	 * @param coordenadas de la pieza
	 */
	public void colocarPiezas(Tipo tipo[],Color color[],int coordenadas[][]) {
		for(int i=0;i<tipo.length;i++) {
			tablero.colocar(new Pieza(tipo[i],color[i]), tablero.obtenerCelda(coordenadas[i][0],coordenadas[i][1]));
			}
		this.color=Color.BLANCO;
		
		}
	
	/**
	 * Comprueba si el movimiento a realizar esta permitido, devolviendo un true si se puede realizar.
	 * 
	 * @param origen de la pieza(celda)
	 * @param destino de la pieza(celda)
	 * @return la legalidad del movimiento
	 */
	public boolean esMovimientoLegal (Celda origen,Celda destino) {
		
		if(destino.obtenerColorDePieza()==origen.obtenerColorDePieza()||origen.estaVacia()||origen==null) {return false;}

			
		else{
			switch(origen.obtenerPieza().obtenerTipo()){
			case PEON: return esMovimientoLegalPeon(origen, destino); 
			case DAMA: return esMovimientoLegalDama(origen, destino);
			case CABALLO: return esMovimientoLegalCaballo(origen, destino);
			case ALFIL: return esMovimientoLegalAlfil(origen, destino);
			case REY: return esMovimientoLegalRey(origen, destino);
			case TORRE: return esMovimientoLegalTorre(origen,destino);
				default: return false;
			}
		}
		
			
	}
	
	/**
	 * Permita saber si una celda esta vacia o no.
	 * 
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return se esta vacia o no
	 */
	private boolean estanVacias(Celda origen, Celda destino) {
		
		boolean vacias=true;
		
		int df=desplazamientoFilas(origen,destino);
		
		int dc=desplazamientoColumnas(origen,destino);
		if(df==0) {
			
			for(int i=1; i<Math.abs(dc);i++) {
				if(dc>0) {
					if(tablero.obtenerCelda(origen.obtenerFila(), origen.obtenerColumna()+i).estaVacia()==false) {
						vacias=false;
					}
				}
				else {
					if(tablero.obtenerCelda(origen.obtenerFila(), origen.obtenerColumna()-i).estaVacia()==false) {
					vacias=false;
				}}
			}
		}
		else if(dc==0) {
			for(int i=1; i<Math.abs(df);i++) {
				if(df>0) {
					if(tablero.obtenerCelda(origen.obtenerFila()+i, origen.obtenerColumna()).estaVacia()==false) {
						vacias=false;
					}
				}
				else {
					if(tablero.obtenerCelda(origen.obtenerFila()-i, origen.obtenerColumna()).estaVacia()==false) {
					vacias=false;
				}}
				
			}
		}
		else if(Math.abs(df)==Math.abs(dc)){
			for(int i=1; i<Math.abs(dc);i++) {
				if(df>0&&dc>0) {
					if(tablero.obtenerCelda(origen.obtenerFila()+i, origen.obtenerColumna()+i).estaVacia()==false) {
						vacias=false;
					}
				}
				else if(df>0&&dc<0) {
					if(tablero.obtenerCelda(origen.obtenerFila()+i, origen.obtenerColumna()-i).estaVacia()==false) {
						vacias=false;
					}
				}
				else if (df<0&&dc>0) {
					if(tablero.obtenerCelda(origen.obtenerFila()-i, origen.obtenerColumna()+i).estaVacia()==false) {
						vacias=false;
					}
				}
				else {
					if(tablero.obtenerCelda(origen.obtenerFila()-i, origen.obtenerColumna()-i).estaVacia()==false) {
						vacias=false;
					}
				}
			}
		}
		return vacias;
	}
	
	/**
	 * Comprueba si el movimiento del peón es legal.
	 * @param origen celda origen
	 * @param destino celda destino
	 * @return si es legal
	 */
	private boolean esMovimientoLegalPeon(Celda origen,Celda destino) {
		if((tablero.obtenerSentido(origen, destino)==Sentido.VERTICAL_N&&origen.obtenerColorDePieza()==Color.BLANCO)||(tablero.obtenerSentido(origen, destino)==Sentido.VERTICAL_S&&origen.obtenerColorDePieza()==Color.NEGRO)) {
			if(origen.obtenerPieza().esPrimerMovimiento()==true) {
				
			return estanVacias(origen,destino)&&(Math.abs(desplazamientoFilas(origen,destino))==1||Math.abs(desplazamientoFilas(origen,destino))==2);
			}
			else if(Math.abs(desplazamientoFilas(origen,destino))==1) {
				return true;
			}
			else {return false;}
		}
		else if(((tablero.obtenerSentido(origen, destino)==Sentido.DIAGONAL_NE&&origen.obtenerColorDePieza()==Color.BLANCO)||(tablero.obtenerSentido(origen, destino)==Sentido.DIAGONAL_NO&&origen.obtenerColorDePieza()==Color.BLANCO)||(tablero.obtenerSentido(origen, destino)==Sentido.DIAGONAL_SE&&origen.obtenerColorDePieza()==Color.NEGRO)||(tablero.obtenerSentido(origen, destino)==Sentido.DIAGONAL_SO&&origen.obtenerColorDePieza()==Color.NEGRO))&&(Math.abs(desplazamientoFilas(origen,destino))==1&&(Math.abs(desplazamientoColumnas(origen,destino))==1))) {
			if(destino.estaVacia()==true) {
				return false;
			}
			else if(destino.obtenerColorDePieza()!=origen.obtenerColorDePieza()) {
				return true;
			}
			else {return false;}
		}

		else {
			return false;
		}
	}
	
	/**
	 * Comprueba si el movimiento del alfil a mover esta permitido.
	 * 
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return si el movimiento esta permitido
	 */
	private boolean esMovimientoLegalAlfil(Celda origen, Celda destino) {
		if(tablero.obtenerSentido(origen, destino)==Sentido.DIAGONAL_NE||tablero.obtenerSentido(origen, destino)==Sentido.DIAGONAL_NO||tablero.obtenerSentido(origen, destino)==Sentido.DIAGONAL_SE||tablero.obtenerSentido(origen, destino)==Sentido.DIAGONAL_SO) {
			return estanVacias(origen,destino);
		}
		else {return false;}
	}
	
	/**
	 * Comprueba si el movimiento del torre a mover esta permitido.
	 * 
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return si el movimiento esta permitido
	 */
	private boolean esMovimientoLegalTorre(Celda origen, Celda destino) {
		if(tablero.obtenerSentido(origen, destino)==Sentido.VERTICAL_N||tablero.obtenerSentido(origen, destino)==Sentido.VERTICAL_S||tablero.obtenerSentido(origen, destino)==Sentido.HORIZONTAL_E||tablero.obtenerSentido(origen, destino)==Sentido.HORIZONTAL_O) {
			return estanVacias(origen,destino);
		}
		else {return false;}
	}
	
	/**
	 * Comprueba si el movimiento del rey a mover esta permitido.
	 * 
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return si el movimiento esta permitido
	 */
	private boolean esMovimientoLegalRey(Celda origen,Celda destino) {
		if(Math.abs(desplazamientoFilas(origen,destino))<2&&Math.abs(desplazamientoColumnas(origen,destino))<2&&tablero.obtenerSentido(origen, destino)!=null) {
			return estanVacias(origen,destino);
		}
		else {return false;}
	}
	
	/**
	 * Comprueba si el movimiento de la dama a mover esta permitido.
	 * 
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return si el movimiento esta permitido
	 */
	private boolean esMovimientoLegalDama(Celda origen, Celda destino) {
		if(tablero.obtenerSentido(origen, destino)!=null) {
			return estanVacias(origen,destino);
		}
		else {return false;}
	}
	
	/**
	 * Comprueba si el movimiento del caballo a mover esta permitido.
	 * 
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return si el movimiento esta permitido
	 */
	private boolean esMovimientoLegalCaballo(Celda origen,Celda destino) {
		if(tablero.obtenerSentido(origen, destino)==null) {
			if((Math.abs(desplazamientoFilas(origen,destino))==1&&Math.abs(desplazamientoColumnas(origen,destino))==2)||(Math.abs(desplazamientoFilas(origen,destino))==2&&Math.abs(desplazamientoColumnas(origen,destino))==1)) {
				return true;
			}
			else {return false;}
		}
		else {return false;}
	}
	
	/**
	 * Calcula las filas desplazadas de la figura.
	 * 
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return el numero
	 */
	private int desplazamientoFilas(Celda origen, Celda destino) {
		return destino.obtenerFila()-origen.obtenerFila();
	}
	
	/**
	 * Calcula las columnas desplazadas de la figura.
	 * 
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return el numero
	 */
	private int desplazamientoColumnas(Celda origen, Celda destino) {
		return destino.obtenerColumna()-origen.obtenerColumna();
	}

	/**
	 * Comprueba si el rey esta amenazado por una pieza rival.
	 * @param color color
	 * @return si esta en jaque
	 */
	public boolean estaEnJaque (Color color) {
		
		Celda origen;
		
		Celda destino=null;
		
		boolean jaque=false;
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(!tablero.obtenerCelda(i, j).estaVacia()) {
				if(tablero.obtenerCelda(i, j).obtenerPieza().obtenerTipo()==Tipo.REY&&tablero.obtenerCelda(i, j).obtenerColorDePieza()==color)
			destino=tablero.obtenerCelda(i,j);
				}
			}
			}
		
		for(int i=0;i<8;i++) {	
			for(int j=0;j<8;j++) {
				if((!tablero.obtenerCelda(i,j).estaVacia())&&tablero.obtenerCelda(i,j).obtenerColorDePieza()!=destino.obtenerColorDePieza()) {
				origen=tablero.obtenerCelda(i,j);
				if(esMovimientoLegal(origen,destino)==true) {jaque=true;}
				
			}}
		}
		return jaque;
		
	}
	
	
	/**
	 * Simula el siguiente movimiento para ver si el rey quedaria en jaque.
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return posible jaque
	 */
	public boolean estaEnJaqueTrasSimularMovimientoConTurnoActual(Celda origen,Celda destino) {
		
		Pieza piezad=destino.obtenerPieza();
		
		Pieza piezao=origen.obtenerPieza();
		boolean jaque=false;
		mover(origen, destino);
		jaque= estaEnJaque(this.color);
		mover(destino,origen);
		tablero.colocar(piezao, origen);
		if(piezad!=null) {
		tablero.colocar(piezad, destino);}
		return jaque;
		
	}
	
	/**
	 * Mueve la pieza de la celda origen a la celda destino.
	 * @param origen celda
	 * @param destino celda
	 */
	public void mover(Celda origen, Celda destino) {
		if(origen.obtenerPieza().esPrimerMovimiento()==true) {
			origen.obtenerPieza().marcarPrimerMovimiento();
		}
		
		if(destino.estaVacia()==false&&destino.obtenerColorDePieza()!=this.color) {
			destino.eliminarPieza();
		}
		Pieza po=origen.obtenerPieza();
		po.marcarPrimerMovimiento();
		origen.eliminarPieza();
		tablero.colocar(po, destino);
		
		}
	
	/**
	 * Devuelve la celda en coordenadas algebraicas.
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return vector de jugada 
	 */
	public String obtenerJugadaEnNotacionAlgebraica(Celda origen, Celda destino) {
		return tablero.obtenerCoordenadaEnNotacionAlgebraica(origen)+tablero.obtenerCoordenadaEnNotacionAlgebraica(destino);
		
	}
	
	/**
	 * Indica el turno actual de la partida.
	 * @return la jugada
	 */
	public int obtenerNumeroJugada() {
		return jugada;
		
	}
	
	/**
	 * Devuelve el color del jugador en este turno.
	 * @return color
	 */
	public Color obtenerTurno() {
		return this.color;
		
	}
}
package juego.control;
import juego.modelo.Tablero;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.modelo.CoordenadasIncorrectasException;
import juego.modelo.pieza.Pieza;
import juego.modelo.pieza.Alfil;
import juego.modelo.pieza.Caballo;
import juego.modelo.pieza.Dama;
import juego.modelo.pieza.Peon;
import juego.modelo.pieza.Rey;
import juego.modelo.pieza.Torre;

/**
 * Comprueba que se cumplan las normas del juego y determina un ganador.
 * 
 * @author Irene Ruiz
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
	 * @throws CoordenadasIncorrectasException 
	 */
	public void colocarPiezas()  {
		try {
		for(int i=0;i<8;i++) {
			//para colocar los peones
			
			
			tablero.colocar(new Peon( Color.BLANCO), tablero.obtenerCelda(6,i));
			
			
			tablero.colocar(new Peon( Color.NEGRO), tablero.obtenerCelda(1,i));
		}
		
		//Torres
		tablero.colocar(new Torre( Color.NEGRO), tablero.obtenerCelda(0,0));
		tablero.colocar(new Torre( Color.NEGRO), tablero.obtenerCelda(0,7));
		tablero.colocar(new Torre( Color.BLANCO), tablero.obtenerCelda(7,7));
		tablero.colocar(new Torre( Color.BLANCO), tablero.obtenerCelda(7,0));
		
		//Caballo
		tablero.colocar(new Caballo( Color.NEGRO), tablero.obtenerCelda(0,1));
		tablero.colocar(new Caballo( Color.NEGRO), tablero.obtenerCelda(0,6));
		tablero.colocar(new Caballo( Color.BLANCO), tablero.obtenerCelda(7,1));
		tablero.colocar(new Caballo( Color.BLANCO), tablero.obtenerCelda(7,6));
		
		//Alfil
		tablero.colocar(new Alfil( Color.NEGRO), tablero.obtenerCelda(0,2));
		tablero.colocar(new Alfil( Color.NEGRO), tablero.obtenerCelda(0,5));
		tablero.colocar(new Alfil( Color.BLANCO), tablero.obtenerCelda(7,2));
		tablero.colocar(new Alfil( Color.BLANCO), tablero.obtenerCelda(7,5));
	
		//Rey y dama
		tablero.colocar(new Rey( Color.NEGRO), tablero.obtenerCelda(0,4));
		tablero.colocar(new Dama( Color.NEGRO), tablero.obtenerCelda(0,3));
		tablero.colocar(new Rey( Color.BLANCO), tablero.obtenerCelda(7,4));
		tablero.colocar(new Dama( Color.BLANCO), tablero.obtenerCelda(7,3));
		this.color=Color.BLANCO;
		
		}
		catch (CoordenadasIncorrectasException e) {
			System.out.println(e.toString());
		}
		
		
	}
	
	/**
	 * Coloca las piezas para iniciar la partida.
	 * @param piezas array de piezas
	 * @param coordenadas array de coordenadas
	 * @param negro rey negro
	 * @param blanco rey blanco
	 * @throws CoordenadasIncorrectasException lanza la excepcion si las coordenadas no son correctas
	 */
	public void colocarPiezas(Pieza piezas[],int coordenadas[][], Rey negro, Rey blanco)throws CoordenadasIncorrectasException {
		try {
			for(int i=0;i<piezas.length;i++) {
				if(tablero.obtenerCelda(coordenadas[i][0], coordenadas[i][1])==null) {
					throw new CoordenadasIncorrectasException("La celda no pertence al tablero");
				}
			}
		for(int i=0;i<piezas.length;i++) {
			tablero.colocar(piezas[i], tablero.obtenerCelda(coordenadas[i][0],coordenadas[i][1]));
			}
		tablero.colocar(negro, tablero.obtenerCelda(0,4));
		tablero.colocar(blanco, tablero.obtenerCelda(7,4));
		this.color=Color.BLANCO;
		}
		catch(CoordenadasIncorrectasException e){
			throw new CoordenadasIncorrectasException("No se pueden colocar las piezas",e);
		}
		}
	
	/**
	 * Comprueba si el movimiento a realizar esta permitido, devolviendo un true si se puede realizar.
	 * 
	 * @param origen de la pieza(celda)
	 * @param destino de la pieza(celda)
	 * @return la legalidad del movimiento
	 * @throws CoordenadasIncorrectasException cuando la celda no pertence al tablero
	 */
	public boolean esMovimientoLegal (Celda origen,Celda destino) throws CoordenadasIncorrectasException {
		try {
		if(origen.obtenerColumna()>7||origen.obtenerColumna()<0||origen.obtenerFila()>7||origen.obtenerFila()<0||destino.obtenerColumna()>7||destino.obtenerColumna()<0||destino.obtenerFila()>7||destino.obtenerFila()<0) {
			throw new CoordenadasIncorrectasException("La celda no pertence al tablero");}
		
		if(destino.obtenerColorDePieza()==origen.obtenerColorDePieza()||origen.estaVacia()||origen==null) {return false;}

			
		else{
			return origen.obtenerPieza().esCorrectoMoverA(destino, tablero.obtenerSentido(origen,destino), !estanVacias(origen,destino));
			}
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("No se puede comprobar el movimiento",e);
			
		}
		}
		
			
	
	
	/**
	 * Permita saber si una celda esta vacia o no.
	 * 
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return se esta vacia o no
	 * @throws CoordenadasIncorrectasException 
	 */
	private boolean estanVacias(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		
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
	 * @throws CoordenadasIncorrectasException 
	 */
	public boolean estaEnJaque (Color color) throws CoordenadasIncorrectasException {
		
		Celda origen;
		
		Celda destino=null;
		
		boolean jaque=false;
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(!tablero.obtenerCelda(i, j).estaVacia()) {
				if(tablero.obtenerCelda(i, j).obtenerPieza().toChar()=='R'&&tablero.obtenerCelda(i, j).obtenerColorDePieza()==color)
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
	 * @throws CoordenadasIncorrectasException 
	 */
	public boolean estaEnJaqueTrasSimularMovimientoConTurnoActual(Celda origen,Celda destino) throws CoordenadasIncorrectasException {
		try {
		if(origen.obtenerColumna()>7||origen.obtenerColumna()<0||origen.obtenerFila()>7||origen.obtenerFila()<0||destino.obtenerColumna()>7||destino.obtenerColumna()<0||destino.obtenerFila()>7||destino.obtenerFila()<0) {
			throw new CoordenadasIncorrectasException("La celda no pertence al tablero");}	
		
		Pieza piezad=destino.obtenerPieza();
		Pieza piezao=origen.obtenerPieza();
		Pieza copia=copiarPieza(piezao);
		boolean jaque=false;
		mover(origen, destino);
		jaque= estaEnJaque(this.color);
		destino.eliminarPieza();
		tablero.colocar(copia, origen);
		if(piezad!=null) {
		tablero.colocar(piezad, destino);}
		
		return jaque;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("No se puede simular el jaque",e);
		}
	}
	/**
	 * copia la pieza dada.
	 * @param pieza pieza dada
	 * @return pieza copia
	 */
	private Pieza copiarPieza(Pieza pieza) {
		char tipo=pieza.toChar();
		Pieza copia;
		switch(tipo) {
		case 'A':copia=new Alfil(pieza.obtenerColor());break;
		case 'C': copia=new Caballo(pieza.obtenerColor());break;
		case 'D': copia=new Dama(pieza.obtenerColor());break;
		case 'P': copia=new Peon(pieza.obtenerColor());break;
		case 'R': copia=new Rey(pieza.obtenerColor());break;
		case 'T': copia=new Torre(pieza.obtenerColor());break;
		default:copia=null;
		}
		if(pieza.esPrimerMovimiento()==false) {
			copia.marcarPrimerMovimiento();
		}
		return copia;
	}
	
	/**
	 * Mueve la pieza de la celda origen a la celda destino.
	 * @param origen celda
	 * @param destino celda
	 * @throws CoordenadasIncorrectasException 
	 */
	public void mover(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		try {
		if(origen.obtenerColumna()>7||origen.obtenerColumna()<0||origen.obtenerFila()>7||origen.obtenerFila()<0||destino.obtenerColumna()>7||destino.obtenerColumna()<0||destino.obtenerFila()>7||destino.obtenerFila()<0) {
			throw new CoordenadasIncorrectasException("La celda no pertence al tablero");}
		
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
		catch(CoordenadasIncorrectasException e){
			throw new CoordenadasIncorrectasException("No se puede mover la pieza",e);
		}
		}
	
	/**
	 * Devuelve la celda en coordenadas algebraicas.
	 * @param origen (celda)
	 * @param destino (celda)
	 * @return vector de jugada 
	 * @throws CoordenadasIncorrectasException 
	 */
	public String obtenerJugadaEnNotacionAlgebraica(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		try {
		if(origen.obtenerColumna()>7||origen.obtenerColumna()<0||origen.obtenerFila()>7||origen.obtenerFila()<0||destino.obtenerColumna()>7||destino.obtenerColumna()<0||destino.obtenerFila()>7||destino.obtenerFila()<0) {
			throw new CoordenadasIncorrectasException("La celda no pertence al tablero");}
		
		return tablero.obtenerCoordenadaEnNotacionAlgebraica(origen)+tablero.obtenerCoordenadaEnNotacionAlgebraica(destino);
		}
		catch(CoordenadasIncorrectasException e){
			throw new CoordenadasIncorrectasException("No se puede obtener la jugada",e);
		}
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
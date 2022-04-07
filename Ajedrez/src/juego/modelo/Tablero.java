package juego.modelo;

import juego.util.Sentido;
/**
 * Establece el tablero del juego.
 * 
 * @author Irene Ruiz, Álvaro López
 * @since JDK 11
 * @version 1.3
 *
 */
public class Tablero {
	/**
	 * numero de filas.
	 */
	public static final int NUMERO_FILAS = 8;
	/**
	 * numero de columnas.
	 */
	public static final int NUMERO_COLUMNAS = 8;
	/**
	 * array de celdas.
	 */
	private Celda[][] celdas;
	
	/**
	 * Constructor.
	 */
	public Tablero() {
	celdas=new Celda[NUMERO_FILAS][NUMERO_COLUMNAS];
	for(int i=0;i<NUMERO_FILAS;i++) {
		for(int j=0; j<NUMERO_COLUMNAS;j++) {
			celdas[i][j]=new Celda(i,j);
			}
		}
	}
	
	/**
	 * Coloca las piezas en las celdas del tablero.
	 * 
	 * @param pieza jugada
	 * @param celda en juego
	 */
	public void colocar(Pieza pieza, Celda celda) {
		pieza.establecerCelda(celda);
		celda.establecerPieza(pieza);
	
	}
	/**
	 * Coloca una pieza en el tablero.
	 * @param pieza pieza
	 * @param fila fila
	 * @param columna columna
	 */
	public void colocar(Pieza pieza, int fila, int columna) {
		pieza.establecerCelda(obtenerCelda(fila,columna));
		obtenerCelda(fila,columna).establecerPieza(pieza);
	
	}
	
	/**
	 * Devuelve la celda dada una fila y una columna.
	 * 
	 * @param fila fils
	 * @param columna columna
	 * @return la celda con esa fila y columna
	 */
	public Celda obtenerCelda(int fila, int columna) {
		if(fila>7||columna>7||fila<0||columna<0) {
			return null;
		}
		else {
		return celdas[fila][columna];}
	}
	
	/**
	 * Devuelve la celda dadas las coordenadas.
	 * @param texto texto
	 * @return la celda
	 */
	public Celda obtenerCeldaParaNotacionAlgebraica(String texto) {
		    char letra = texto.charAt(0);
			char numero = texto.charAt(1);
									
			if(numero >= 49 && numero < 49 + NUMERO_FILAS&&letra>=97 && letra<97 + NUMERO_COLUMNAS) {
				return this.obtenerCelda((int)(56-numero), (int)(letra-97));
			}
			return null;
	    }

	/**
	 * obtiene un vector de una dimensión con todas las celdas.
	 * 
	 * @return vector con todas las celdas
	 */
	public Celda[] obtenerCeldas() {
		Celda c[]=new Celda[64];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				c[8*i+j]=celdas[i][j];
			}
		}
	return c;
	
	}
	/**
	 * Obtiene las coordenadas de la celda.
	 * 
	 * @param celda celda
	 * @return coordenadas en notación algebraica
	 */
	public String obtenerCoordenadaEnNotacionAlgebraica(Celda celda) {
		String letra="";
		if (celda==null||celda.obtenerColumna()>7||celda.obtenerColumna()<0||celda.obtenerFila()>7||celda.obtenerFila()<0) {return null;}
		else {
		switch(celda.obtenerColumna()) {
		case 0: letra="a";break;
		case 1: letra="b";break;
		case 2: letra="c";break;
		case 3: letra="d";break;
		case 4: letra="e";break;
		case 5: letra="f";break;
		case 6: letra="g";break;
		case 7: letra="h";break;
		}
	return letra+(8-celda.obtenerFila());}
	
	}
	/**
	 * Obtiene el numero de columnas del tablero.
	 * 
	 * @return número de columnas
	 */
	public int obtenerNumeroColumnas() {
	
	return NUMERO_COLUMNAS;
	}
	/**
	 * Obtiene el numero de filas del tablero.
	 * 
	 * @return número de filas
	 */
	public int obtenerNumeroFilas() {
	return NUMERO_FILAS;
	
	}
	/**
	 * Obtiene el numero de piezas de un color.
	 * 
	 * @param color color
	 * @return numero de piezas
	 */
	public int obtenerNumeroPiezas(Color color) {
		int n=0;
		for(int i=0;i<NUMERO_FILAS;i++) {
			for(int j=0;j<NUMERO_COLUMNAS;j++) {
				if(celdas[i][j].obtenerPieza()!=null) {
					if ((celdas[i][j].obtenerPieza()).obtenerColor()==color) {
						n=n+1;
					}
				}
				
			}
		}
	return n;
	}
	/**
	 * Obtiene sentido de movimiento de la pieza.
	 * 
	 * @param origen origen
	 * @param destino destino
	 * @return sentido sentido
	 */
	public Sentido obtenerSentido(Celda origen, Celda destino) {
		int mh=destino.obtenerColumna()-origen.obtenerColumna();
		int mv=destino.obtenerFila()-origen.obtenerFila();
		if(mv==0) {
			if(mh>0) {
				return Sentido.HORIZONTAL_E;
			}
			else {return Sentido.HORIZONTAL_O;}
		}
		else if(mh==0) {
			if(mv>0) {
				return Sentido.VERTICAL_S;
			}
			else {return Sentido.VERTICAL_N;}
		}
		else if(Math.abs(mv)==Math.abs(mh)) {
			if(mv>0) {
				if(mh>0) {
					return Sentido.DIAGONAL_SE;
				}
				else {return Sentido.DIAGONAL_SO;}
			}
			else {
				if(mh>0) {return Sentido.DIAGONAL_NE;}
				else {return Sentido.DIAGONAL_NO;}
			}
		}
		else {
	return null;
		}
	}
	/**
	 * Devuelve el estado del tablero.
	 * @return el estado del tablero
	 */
	public String toString() {
		String t="";
		for(int i=0;i<8;i++) {
			t=t+(8-i)+" ";
			for (int j=0;j<8;j++){
				if(celdas[i][j].estaVacia()==false) {
				t=t+celdas[i][j].obtenerPieza().obtenerTipo().toChar()+celdas[i][j].obtenerColorDePieza().toChar()+" ";}
				else {t=t+"-- ";}
			}
			t=t+"\n";
		}
		t=t+"  a  b  c  d  e  f  g  h";
			return t;
	}
}

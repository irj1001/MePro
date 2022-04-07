package juego.modelo;

import java.util.ArrayList;
import java.util.List;

import juego.modelo.pieza.Pieza;

import juego.util.Sentido;

/**
 * Establece el tablero de juego.
 * @author Irene Ruiz
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
	private List<List<Celda>> celdas;
	
	
	/**
	 * Constructor.
	 */
	public Tablero() {
	celdas=new ArrayList<List<Celda>>();
	List<Celda> c1=new ArrayList<Celda>();
	List<Celda> c2=new ArrayList<Celda>();
	List<Celda> c3=new ArrayList<Celda>();
	List<Celda> c4=new ArrayList<Celda>();
	List<Celda> c5=new ArrayList<Celda>();
	List<Celda> c6=new ArrayList<Celda>();
	List<Celda> c7=new ArrayList<Celda>();
	List<Celda> c8=new ArrayList<Celda>();
	
	for(int i=0;i<NUMERO_FILAS;i++) {
		c1.add(new Celda(0,i));
		c2.add(new Celda(1,i));
		c3.add(new Celda(2,i));
		c4.add(new Celda(3,i));
		c5.add(new Celda(4,i));
		c6.add(new Celda(5,i));
		c7.add(new Celda(6,i));
		c8.add(new Celda(7,i));
	}
	celdas.add(c1);
	celdas.add(c2);
	celdas.add(c3);
	celdas.add(c4);
	celdas.add(c5);
	celdas.add(c6);
	celdas.add(c7);
	celdas.add(c8);
	}
	
	/**
	 * Coloca las piezas en las celdas del tablero.
	 * 
	 * @param pieza jugada
	 * @param celda en juego
	 * @throws CoordenadasIncorrectasException 
	 */
	public void colocar(Pieza pieza, Celda celda) throws CoordenadasIncorrectasException {
		try {
			if(celda.obtenerColumna()>7||celda.obtenerColumna()<0||celda.obtenerFila()>7||celda.obtenerFila()<0) {
				throw new CoordenadasIncorrectasException("La celda no pertenece al tablero");
			}
		pieza.establecerCelda(celda);
		celda.establecerPieza(pieza);
		}
		catch(CoordenadasIncorrectasException e){
			throw new CoordenadasIncorrectasException("No se puede colocar la pieza",e);
		}
	}
	/**
	 * Coloca una pieza en el tablero.
	 * @param pieza pieza
	 * @param fila fila
	 * @param columna columna
	 * @throws CoordenadasIncorrectasException 
	 */
	public void colocar(Pieza pieza, int fila, int columna) throws CoordenadasIncorrectasException {
		try {
			if(fila>7||fila<0||columna>7||columna<0) {
				throw new CoordenadasIncorrectasException("La celda no pertenece al tablero");
			}
		pieza.establecerCelda(obtenerCelda(fila,columna));
		obtenerCelda(fila,columna).establecerPieza(pieza);
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("No se puede colocar la pieza",e);
		}
	}
	/**
	 * Devuelve la celda dada la fila y la columna.
	 * @param fila fila
	 * @param columna columna
	 * @return la celda
	 * @throws CoordenadasIncorrectasException
	 */
	public Celda obtenerCelda(int fila, int columna)throws CoordenadasIncorrectasException  {
		try {
		if(fila>7||columna>7||fila<0||columna<0) {
			
			throw new CoordenadasIncorrectasException("La celda no pertenece al tablero");
		}
		else {
		return celdas.get(fila).get(columna);}
		}
		catch(CoordenadasIncorrectasException e){
			throw new CoordenadasIncorrectasException("No se puede pbtener la celda",e);
		}
	}
	/**
	 * devuelve todas las celdas.
	 * @return celdas celdas
	 */
	public List<Celda> obtenerCeldas (){
		List<Celda> celda= new ArrayList<>();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				celda.add(celdas.get(i).get(j));
			}
		}
		return celda;
	}
	/**
	 * obtiene las celdas entre dos celdas.
	 * @param origen celda
	 * @param destino celda
	 * @return arraylist de celdas
	 * @throws CoordenadasIncorrectasException
	 */
	public List<Celda> obtenerCeldasEntreMedias(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		try {
		int df=destino.obtenerColumna()-origen.obtenerColumna();
		int dc=destino.obtenerFila()-origen.obtenerFila();
		int dfilas=0;
		int dcolumnas=0;
		if(df>0) {
		 dfilas=df/Math.abs(df);}
		if(dc>0) {
		 dcolumnas=dc/Math.abs(dc);}
		Sentido sentido=obtenerSentido(origen,destino);
		List<Celda> celdasEntreMedias= new ArrayList<Celda>();
		if(sentido==Sentido.VERTICAL_N||sentido==Sentido.VERTICAL_S) {
			for(int i=1;i<df;i++) {
				celdasEntreMedias.add(celdas.get(origen.obtenerFila()+i*dfilas).get(origen.obtenerColumna()));
			}
		}
		else if(sentido==Sentido.HORIZONTAL_E||sentido==Sentido.HORIZONTAL_O) {
			for(int i=1;i<dc;i++) {
				celdasEntreMedias.add(celdas.get(origen.obtenerFila()).get(origen.obtenerColumna()+i*dcolumnas));
			}
			
		}
		else if(sentido!=null) {
			for(int i=1;i<dc;i++) {
				celdasEntreMedias.add(celdas.get(origen.obtenerFila()+i*dfilas).get(origen.obtenerColumna()+i*dcolumnas));
			}
		}
		return celdasEntreMedias;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("No se pueden obtener las celdas entre medias", e);
		}
	}
	/**
	 * obtiene la celda a partir de la notacon algebraica.
	 * @param texto texto
	 * @return celda celda
	 * @throws CoordenadasIncorrectasException
	 */
	public Celda obtenerCeldaParaNotacionAlgebraica(String texto) throws CoordenadasIncorrectasException {
		try {
		 char letra = texto.charAt(0);
			char numero = texto.charAt(1);
									
			if(numero >= 49 && numero < 49 + NUMERO_FILAS&&letra>=97 && letra<97 + NUMERO_COLUMNAS) {
				return this.obtenerCelda((int)(56-numero), (int)(letra-97));
			}
			else {
				throw new CoordenadasIncorrectasException("La celda no pertenece al tablero");
			}
	}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("No se puede obtener la celda",e);
		}
		}
	/**
	 * obtiene la coordenadas en notacion algebraica de una celda.
	 * @param celda celda
	 * @return coordenadas de la celda
	 * @throws CoordenadasIncorrectasException
	 */
	public String obtenerCoordenadaEnNotacionAlgebraica(Celda celda) throws CoordenadasIncorrectasException{
		try {
		String letra="";
		if (celda==null||celda.obtenerColumna()>7||celda.obtenerColumna()<0||celda.obtenerFila()>7||celda.obtenerFila()<0) {throw new CoordenadasIncorrectasException("La celda no está en el tablero");}
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
		catch(CoordenadasIncorrectasException e){
			throw new CoordenadasIncorrectasException("No se pueden obtener las coordenadas",e);
		}
	}
	/**
	 * obtiene el sentido.
	 * @param origen origen
	 * @param destino destino
	 * @return sentido sentido
	 * @throws CoordenadasIncorrectasException
	 */
	public Sentido obtenerSentido(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		try {
			if(origen.obtenerColumna()>7||origen.obtenerColumna()<0||origen.obtenerFila()>7||origen.obtenerFila()<0||destino.obtenerColumna()>7||destino.obtenerColumna()<0||destino.obtenerFila()>7||destino.obtenerFila()<0) {
				throw new CoordenadasIncorrectasException("La celda no pertence al tablero");}
			else {
				
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
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("No se puede obtener el senitdo",e);
		}
	}
	/**
	 * devuelve el numero de columnas.
	 * @return columnas columnas
	 */
	public int obtenerNumeroColumnas() {
		return NUMERO_COLUMNAS;
	}
	/**
	 * devuelve el numero de filas.
	 * @return filas filas
	 */
	public int obtenerNumeroFilas() {
		return NUMERO_FILAS;
	}
	/**
	 * obtiene el numero de piezas del tablero.
	 * @param color color
	 * @return numero de piezas
	 */
	public int obtenerNumeroPiezas(Color color) {
		int n=0;
		for(int i=0;i<NUMERO_FILAS;i++) {
			for(int j=0;j<NUMERO_COLUMNAS;j++) {
				if(celdas.get(i).get(j).obtenerPieza()!=null) {
					if ((celdas.get(i).get(j).obtenerPieza()).obtenerColor()==color) {
						n=n+1;
					}
				}
				
			}
		}
	return n;
	}
	/**
	 * devuelve el estado del tablero.
	 * @return estado del tablero
	 */
	public String toString() {
		String t="";
		for(int i=0;i<8;i++) {
			t=t+(8-i)+" ";
			for (int j=0;j<8;j++){
				if(celdas.get(i).get(j).estaVacia()==false) {
				t=t+celdas.get(i).get(j).obtenerPieza().toChar()+celdas.get(i).get(j).obtenerColorDePieza().toChar()+" ";}
				else {t=t+"-- ";}
			}
			t=t+"\n";
		}
		t=t+"  a  b  c  d  e  f  g  h";
			return t;
	}
	
	
	
}
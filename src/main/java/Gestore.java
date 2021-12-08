import java.util.ArrayList;

public class Gestore extends Personale {

	/**
	 * lista degli chalet posseduti da un gestore
	 */
	private ArrayList<Chalet> listaChalet;

	/**
	 * aggiunge uno chalet alla lista degli chalet posseduti dal gestore
	 * @param c ...;
	 */
	public boolean aggiungChalet(Chalet c) {
		//todo inserire controlli Chalet
		return this.listaChalet.remove(c);
	}

	public ArrayList<Chalet> getListaChalet() {
		return this.listaChalet;
	}

	/**
	 * 
	 * @param listaChalet ...;
	 */
	public void setListaChalet(ArrayList<Chalet> listaChalet) {
		//todo inserire controlli listaChalet
		this.listaChalet = listaChalet;
	}

	/**
	 * rimuove uno chalet dalla lista
	 * @param c ...;
	 */
	public boolean rimuoviChalet(Chalet c) {
		//todo inserire controlli Chalet
		return this.listaChalet.remove(c);
	}
	/**
	 * permette di impostare le tariffe degli ombrelloni per fila.
	 *
	 * @param tariffa cifra che corrisponde al costo dell'ombrellone nella fila inserita;
	 * @param fila    numero che identifica la fila;
	 * @param c ...;
	 */
	public void setTariffe(float tariffa, int fila, Casotto c){
		//TODO controllare parametri
		ArrayList<Ombrellone> ombrelloni = new ArrayList<>(c.getOmbrelloni());
		for (Ombrellone ombrellone : ombrelloni){
			if (ombrellone.getFila() == fila){
				ombrellone.setTariffa(tariffa);
			}
		}
		c.setOmbrelloni(ombrelloni);
	}
}
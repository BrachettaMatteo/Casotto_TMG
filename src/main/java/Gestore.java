import java.util.ArrayList;

public class Gestore extends Personale {

	/**
	 * lista degli chalet posseduti da un gestore
	 */
	private ArrayList<Chalet> listaChalet;

	/**
	 * aggiunge uno chalet alla lista degli chalet posseduti dal gestore
	 * @param c
	 */
	public boolean aggiungChalet(Chalet c) {
		// TODO - implement Gestore.aggiungChalet
		throw new UnsupportedOperationException();
	}

	public ArrayList<Chalet> getListaChalet() {
		return this.listaChalet;
	}

	/**
	 * 
	 * @param listaChalet
	 */
	public void setListaChalet(ArrayList<Chalet> listaChalet) {
		this.listaChalet = listaChalet;
	}

	/**
	 * rimuove uno chalet dalla lista
	 * @param c
	 */
	public boolean rimuoviChalet(Chalet c) {
		// TODO - implement Gestore.rimuoviChalet
		throw new UnsupportedOperationException();
	}

	public void setTariffe(float tariffa, int fila) {
		//todo controllo paramteri


	}

}
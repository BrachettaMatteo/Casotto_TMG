import java.util.ArrayList;

public class ComandaRistorazione extends Comanda {

	private ArrayList<Prodotto> prodottiOrdinati;

	public ArrayList<Prodotto> getProdottiOrdinati() {
		return this.prodottiOrdinati;
	}

	/**
	 * 
	 * @param prodottiOrdinati
	 */
	public void setProdottiOrdinati(ArrayList<Prodotto> prodottiOrdinati) {
		this.prodottiOrdinati = prodottiOrdinati;
	}

	/**
	 * aggiunge un prodotto alla lista dei prodotti ordinati
	 * @param p
	 */
	public boolean aggiungiProdotto(Prodotto p) {
		// TODO - implement ComandaRistorazione.aggiungiProdotto
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 */
	public boolean rimuoviProdotto(Prodotto p) {
		// TODO - implement ComandaRistorazione.rimuoviProdotto
		throw new UnsupportedOperationException();
	}

}
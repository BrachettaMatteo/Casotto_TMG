import java.util.ArrayList;

public class ComandaRistorazione extends Comanda {

	public ComandaRistorazione(Cliente c, Ombrellone o, ArrayList<Prodotto> prodottiOrdinati) {
		super(c, o);
		this.prodottiOrdinati = prodottiOrdinati;
	}

	private ArrayList<Prodotto> prodottiOrdinati;

	public ArrayList<Prodotto> getProdottiOrdinati() {
		return this.prodottiOrdinati;
	}

	/**
	 * 
	 * @param prodottiOrdinati ...;
	 */
	public void setProdottiOrdinati(ArrayList<Prodotto> prodottiOrdinati) {
		this.prodottiOrdinati = prodottiOrdinati;
	}

	/**
	 * aggiunge un prodotto alla lista dei prodotti ordinati
	 * @param p ...;
	 */
	public boolean aggiungiProdotto(Prodotto p) {
		// TODO - controllo prodotto
		return this.prodottiOrdinati.add(p);
	}

	/**
	 * 
	 * @param p ...;
	 */
	public boolean rimuoviProdotto(Prodotto p) {
		//todo controllo p
		return this.prodottiOrdinati.remove(p);
	}

}
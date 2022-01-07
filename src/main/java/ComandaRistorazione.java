import java.awt.color.ProfileDataException;
import java.util.ArrayList;

public class ComandaRistorazione extends Comanda {

	private Status status;
	private ArrayList<Prodotto> prodottiOrdinati;

	public ComandaRistorazione(Cliente c, Ombrellone o, ArrayList<Prodotto> prodottiOrdinati) {
		//todo - controlo
		super(c, o);
		this.prodottiOrdinati = prodottiOrdinati;
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ArrayList<Prodotto> getProdottiOrdinati() {
		return this.prodottiOrdinati;
	}

	/**
	 * 
	 * @param prodottiOrdinati ...;
	 */
	public void setProdottiOrdinati(ArrayList<Prodotto> prodottiOrdinati) {
		//todo - controlli
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
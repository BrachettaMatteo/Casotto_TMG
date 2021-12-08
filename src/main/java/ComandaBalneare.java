import java.util.ArrayList;

public class ComandaBalneare extends Comanda {

	public ComandaBalneare(Cliente c, Ombrellone o) {
		super(c, o);
	}

	/**
	 * aggiunge del materiale all'ombrellone, es. Lettino, sdraio ecc
	 * @param materiale
	 */
	public void aggiungiMateriale(ArrayList<Materiale> materiale) {
		// TODO - controllo materiale
		super.getO().setComposizione(materiale);
	}

	/**
	 * 
	 * @param attivita
	 */
	public boolean aggiungiAttivita(Attivita attivita) {
		// TODO - implement ComandaBalneare.aggiungiAttivita
		throw new UnsupportedOperationException();
	}

}
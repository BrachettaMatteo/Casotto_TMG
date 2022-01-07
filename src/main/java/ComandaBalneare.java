import java.util.ArrayList;
import java.util.Date;

public class ComandaBalneare extends Comanda {

	private Date durata;
	private ArrayList<Materiale> materiali;

	public ComandaBalneare(Cliente c, Ombrellone o) {
		super(c, o);
		this.materiali = new ArrayList<>();
	}

	/**
	 * aggiunge del materiale all'ombrellone, es. Lettino, sdraio ecc
	 * @param materiale
	 */
	public void aggiungiMateriale(ArrayList<Materiale> materiale, Date d) {
		//todo - controllo materiale e durata
		this.materiali.addAll(materiale);
		this.durata=d;
	}


}
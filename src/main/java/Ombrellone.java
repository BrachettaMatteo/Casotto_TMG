import java.util.ArrayList;
import java.util.Date;

public class Ombrellone {

	private int fila;
	private float tariffa;
	private ArrayList<Materiale> composizione;
	private String QRCode;
	private int numero;
	private boolean disponibilita;
	/**indica la fine della prenotazione dell'ombrellone*/
	private Date fine;

	public Ombrellone(int fila, float tariffa, int numero) {
		this.fila = fila;
		this.tariffa = tariffa;
		this.numero = numero;
		this.disponibilita = true;
	}

	public int getFila() {
		return this.fila;
	}

	/**
	 * 
	 * @param fila ...;
	 */
	public void setFila(int fila) {
		//todo controllo fila
		this.fila = fila;
	}

	public float getTariffa() {
		return this.tariffa;
	}

	/**
	 * 
	 * @param tariffa ...;
	 */
	public void setTariffa(float tariffa) {
		this.tariffa = tariffa;
	}

	public String getQRCode() {
		// TODO - implement Ombrellone.getQRCode
		throw new UnsupportedOperationException();
	}

	public int getNumero() {
		return this.numero;
	}

	/**
	 * 
	 * @param numero ...;
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean getDisponibilita() {
		return this.disponibilita;
	}

	/**
	 * 
	 * @param disponibilita ...;
	 */
	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	/**
	 * 
	 * @param composizione ...;
	 */
	public void setComposizione(ArrayList<Materiale> composizione) {
		this.composizione = composizione;
	}

	/**
	 * aggiunge elemento alla lista che contiene tutto il materiale dell'ombrellone
	 * @param materiali ...;
	 */
	public void aggiungiAllaComposizione(ArrayList<Materiale> materiali) {
		this.composizione.addAll(materiali);
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}


	@Override
	public String toString() {
		return "Ombrellone{" +
				"fila=" + fila +
				", tariffa=" + tariffa +
				", QRCode='" + QRCode + '\'' +
				", numero=" + numero +
				", disponibilita=" + disponibilita +
				'}';
	}
}
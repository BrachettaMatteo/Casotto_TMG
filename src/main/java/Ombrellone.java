import java.util.ArrayList;

public class Ombrellone {

	private int fila;
	private float tariffa;
	private ArrayList<Materiale> composizione;
	private String QRCode;
	private int numero;
	private boolean disponibilita;

	public int getFila() {
		return this.fila;
	}

	/**
	 * 
	 * @param fila
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	public float getTariffa() {
		return this.tariffa;
	}

	/**
	 * 
	 * @param tariffa
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
	 * @param numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean getDisponibilita() {
		return this.disponibilita;
	}

	/**
	 * 
	 * @param disponibilita
	 */
	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	/**
	 * 
	 * @param composizione
	 */
	public void setComposizione(ArrayList<Materiale> composizione) {
		this.composizione = composizione;
	}

	/**
	 * aggiunge elemento alla lista che contiene tutto il materiale dell'ombrellone
	 * @param m
	 */
	public boolean aggiungiAllaComposizione(Materiale m) {
		// TODO - implement Ombrellone.aggiungiAllaComposizione
		throw new UnsupportedOperationException();
	}

}
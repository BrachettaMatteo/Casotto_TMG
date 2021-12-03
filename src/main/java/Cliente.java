public class Cliente {

	private String nome;
	private String cognome;
	private int id;

	public String getNome() {
		return this.nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public int getId() {
		return this.id;
	}

	/**
	 * richiesta prenotazione ombrellone, restituira true se è stata accettata altrimenti false.
	 * @param ombrellone
	 * @param durata indica la durat della prenotazione
	 */
	public boolean richiestaPrenotazioneOmbrellone(Ombrellone ombrellone, int durata) {
		// TODO - implement Cliente.richiestaPrenotazioneOmbrellone
		throw new UnsupportedOperationException();
	}

	/**
	 * richiesta prenotazione servizio, restituira true se è stata accettata altrimenti false.
	 * @param comanda
	 */
	public boolean richiestaPrenotazioneServizio(Comanda comanda) {
		// TODO - implement Cliente.richiestaPrenotazioneServizio
		throw new UnsupportedOperationException();
	}

}
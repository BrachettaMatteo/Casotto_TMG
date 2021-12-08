public class Prodotto {

	private String nome;
	private float prezzo;
	private String descrizione;
	/**
	 * numero che identifica la quantita selezionata del prodotto,
	 */
	private int quantita;

	public Prodotto(String nome, float prezzo, String descrizione) {
		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.quantita = 0;
	}

	public String getNome() {
		return this.nome;
	}

	/**
	 * 
	 * @param nome ...;
	 */
	public void setNome(String nome) {
		//todo controllo nome
		this.nome = nome;
	}

	public float getPrezzo() {
		return this.prezzo;
	}

	/**
	 * 
	 * @param prezzo ...;
	 */
	public void setPrezzo(float prezzo) {
		//todo controllo prezzo
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * 
	 * @param descrizione ...;
	 */
	public void setDescrizione(String descrizione) {
		//todo controllo descrizione
		this.descrizione = descrizione;
	}

	public int getQuantita() {
		return this.quantita;
	}

	/**
	 * 
	 * @param quantita ...;
	 */
	public void setQuantita(int quantita) {
		//todo controllo quantita
		this.quantita = quantita;
	}

}
import java.util.ArrayList;

public class Attivita {

	private int idAttivita;
	private String nome;
	/**
	 * numero che indentifica i componenti massimi
	 */
	private int postiMax;
	/**
	 * identifica i posti minimi per far cominciare l'attività
	 */
	private int postiMin;
	/**
	 * identifica i componenti inscritti all'attività
	 */
	private ArrayList<Cliente> componenti;
	private float costo;
	private String descrizione;
	private int oraAttivita;

	public Attivita(int idAttivita, String nome, int postiMax, int postiMin, ArrayList<Cliente> componenti, float costo, String descrizione, int oraAttivita) {
		//todo controllo eccezioni
		this.idAttivita = idAttivita;
		this.nome = nome;
		this.postiMax = postiMax;
		this.postiMin = postiMin;
		this.componenti = componenti;
		this.costo = costo;
		this.descrizione = descrizione;
		this.oraAttivita = oraAttivita;
	}

	public String getNome() {
		return this.nome;
	}

	/**
	 * @param nome
	 */
	public void setNome(String nome) {
		//todo controllo nome.empty nome.length <= 1
		this.nome = nome;
	}

	public int getPostiMax() {
		return this.postiMax;
	}

	public int getPostiMin() {
		return this.postiMin;
	}

	public float getCosto() {
		return this.costo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getIdAttivita() {
		return this.idAttivita;
	}

	public ArrayList<Cliente> getComponenti() {
		return this.componenti;
	}

	/**
	 * aggiunge un componente
	 * @param cliente
	 */
	public boolean aggiungiComponente(Cliente cliente) {
		if (cliente == null) throw new NullPointerException("il cliente inserito è nullo");
		return this.componenti.add(cliente);
	}

	public int getOraAttivita() {
		return this.oraAttivita;
	}

}
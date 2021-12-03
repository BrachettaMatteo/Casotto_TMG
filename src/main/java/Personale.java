public class Personale {

	private String nome;
	private String cognome;
	private String residenza;
	private int id;
	private int telefono;

	public String getNome() {
		return this.nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public String getResidenza() {
		return this.residenza;
	}

	/**
	 * 
	 * @param residenza
	 */
	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public int getId() {
		return this.id;
	}

	public int getTelefono() {
		return this.telefono;
	}

	/**
	 * 
	 * @param telefono
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
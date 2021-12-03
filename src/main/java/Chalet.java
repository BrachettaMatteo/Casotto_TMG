import java.util.Date;

public class Chalet {

	private String nome;
	private String indirizzo;
	private int telefono;
	private Date orarioApertura;

	public String getNome() {
		return this.nome;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	/**
	 * 
	 * @param indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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

	public Date getOrarioApertura() {
		return this.orarioApertura;
	}

	/**
	 * 
	 * @param orarioApertura
	 */
	public void setOrarioApertura(Date orarioApertura) {
		this.orarioApertura = orarioApertura;
	}

}
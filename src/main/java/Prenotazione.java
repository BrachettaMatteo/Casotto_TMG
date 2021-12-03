public class Prenotazione {

	private Cliente cliente;
	private Ombrellone ombrellone;
	private Comanda comanda;

	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * 
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Ombrellone getOmbrellone() {
		return this.ombrellone;
	}

	/**
	 * 
	 * @param ombrellone
	 */
	public void setOmbrellone(Ombrellone ombrellone) {
		this.ombrellone = ombrellone;
	}

	public Comanda getComanda() {
		return this.comanda;
	}

	/**
	 * 
	 * @param comanda
	 */
	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

}
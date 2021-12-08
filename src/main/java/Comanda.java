public class Comanda{

	private int idComanda;
	private Status status;

	private Cliente c;
	private Ombrellone o;

	public Cliente getC() {
		return c;
	}

	public void setC(Cliente c) {
		this.c = c;
	}

	public Ombrellone getO() {
		return o;
	}

	public void setO(Ombrellone o) {
		this.o = o;
	}

	public Comanda(Cliente c, Ombrellone o) {
		this.c = c;
		this.o = o;
		this.status = Status.inLavorazione;
	}

	public int getIdComanda() {
		return this.idComanda;
	}

	public Status getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param status ...;
	 */
	public void setStatus(Status status) {
		if(status == null)
			throw new NullPointerException("status null");
		this.status = status;
	}

}
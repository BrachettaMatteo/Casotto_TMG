public class Comanda {

	private int idComanda;
	private Status status;

	public int getIdComanda() {
		return this.idComanda;
	}

	public Status getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(Status status) {
		if(status == null)
			throw new NullPointerException("status null");
		this.status = status;
	}

}
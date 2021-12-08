public class GestionePrenotazione {

    private Cliente Cliente;
    private Ombrellone ombrellone;
    private Comanda Comanda;

    public GestionePrenotazione(Cliente cliente, Ombrellone ombrellone, Comanda comanda) {
        //todo controllare parametri
        Cliente = cliente;
        this.ombrellone = ombrellone;
        Comanda = comanda;
    }

    public Cliente getCliente() {
        return this.Cliente;
    }

    /**
     * @param Cliente ...;
     */
    public void setCliente(Cliente Cliente) {
		//todo controllo cliente
       this.Comanda.setC(Cliente);
    }

    public Ombrellone getOmbrellone() {
        return this.ombrellone;
    }

    /**
     * @param ombrellone ...;
     */
    public void setOmbrellone(Ombrellone ombrellone) {
        //controllo ombrelloni
		this.ombrellone = ombrellone;
    }

    public Comanda getComanda() {
        return  this.Comanda;
    }

    /**
     * @param comanda ...;
     */
    public void setComanda(Comanda comanda) {
		//todo controllo comanda
		this.Comanda = comanda;
    }

}
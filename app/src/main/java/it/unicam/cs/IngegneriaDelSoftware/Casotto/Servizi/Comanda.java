package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;

import java.util.UUID;

/**
 * rappresneta una comanda e le sue caratteristiche
 */
public abstract class Comanda {

    private static Casotto casotto;

    private final String idOmbrellone;
    private final String idCliente;

    private Ombrellone o;
    private Cliente c;
    private final UUID idComanda;
    private Status status;

    /**
     * crea una nuova comanda
     *
     * @param o ombrelone, destinazione della comanda
     * @param c cliente, proprietario della comanda
     */
    public Comanda(Ombrellone o, Cliente c) {

        casotto = Casotto.getInstance();
        this.o = o;
        this.c = c;
        this.idOmbrellone=o.getId();
        this.idCliente=c.getId();
        this.idComanda = UUID.randomUUID();
        this.status = Status.daElaborare;
    }

    public Comanda(String id, String Ombrellone, String Cliente,String status) {
        this.idComanda = UUID.fromString(id);
        this.o=Casotto.getInstance().getOmbrellone(Ombrellone);
        this.idOmbrellone = Ombrellone;
        this.idCliente = Cliente;
        this.status= Status.valueOf(status);
    }


    public Comanda(String Ombrellone, String Cliente) {
        this.idComanda = UUID.randomUUID();
        this.idOmbrellone = Ombrellone;
        this.idCliente = Cliente;
    }

    public Comanda(String id, Ombrellone ombrellone, Cliente cliente) {
        this.idComanda=UUID.fromString(id);
        this.o=ombrellone;
        this.idOmbrellone=o.getId();
        this.c=cliente;
        this.idCliente=c.getId();
    }

    /**
     * @return il cassotto
     */
    public static Casotto getCasotto() {
        return casotto;
    }

    /**
     * @return l'ombrellone della comanda
     */
    public Ombrellone getO() {
        return o;
    }

    public String getIdCliente() {
        return idCliente;
    }


    /**
     * @return il cliente della comanda
     */
    public Cliente getC() {
        return c;
    }

    /**
     * @return l'indetificativo della comanda
     */
    public UUID getIdComanda() {
        return idComanda;
    }

    /**
     * permette di crere il conto tatake della comanda
     *
     * @return l'importo della comanda
     */
    public abstract float calcolaConto();

    /**
     * peemette di chiudere una comanda
     */
    public abstract void chiudiComanda();

    /**
     * permette di aggiornare lo stato di una comanda
     *
     * @param s stato da aggiornare;
     */
    public void aggiornaComanda(Status s) {
        this.status = s;
    }

    public String getStatus() {
        return status.toString();
    }

    public String getIdOmbrellone() {
        return idOmbrellone;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
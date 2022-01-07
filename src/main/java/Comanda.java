public class Comanda {

    private int idComanda;
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
    }

    public int getIdComanda() {
        return this.idComanda;
    }
}
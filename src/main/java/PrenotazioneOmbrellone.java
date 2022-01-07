import java.util.Date;

public class PrenotazioneOmbrellone {

    private int idPrenotazione;
    private Cliente c;
    private Ombrellone o;
    private Date durata;

    public PrenotazioneOmbrellone(Cliente c, Ombrellone o, Date durata) {

        //todo - controllo dati

        this.c = c;
        this.o = o;
        this.durata = durata;
        //todo - creazione id
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public Cliente getC() {
        return c;
    }

    public Ombrellone getO() {
        return o;
    }

    public Date getDurata() {
        return durata;
    }

    public void setC(Cliente c) {
        //todo - controllo c
        this.c = c;
    }

    public void setO(Ombrellone o) {
        //todo - controllo o
        this.o = o;
    }

    public void setDurata(Date durata) {
        //todo - controllo durata
        this.durata = durata;
    }
}
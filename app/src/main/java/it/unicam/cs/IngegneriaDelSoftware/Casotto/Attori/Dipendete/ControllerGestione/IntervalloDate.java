package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.ControllerGestione;

import java.sql.Timestamp;

public class IntervalloDate {
    Timestamp inizio;
    Timestamp fine;

    public IntervalloDate(Timestamp inizio, Timestamp fine) {
        this.inizio = inizio;
        this.fine = fine;
    }

    public Timestamp getInizio() {
        return inizio;
    }

    public void setInizio(Timestamp inizio) {
        this.inizio = inizio;
    }

    public Timestamp getFine() {
        return fine;
    }

    public void setFine(Timestamp fine) {
        this.fine = fine;
    }
}

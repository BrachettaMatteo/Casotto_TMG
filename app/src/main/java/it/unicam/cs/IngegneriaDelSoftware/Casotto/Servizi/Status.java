package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

/**
 * Stabilisce lo stato della prenoatzione
 */
public enum Status {
    /**
     * &egrave; stato effettuato il pagamento
     * e la comanda aspeta di essere presa in carico
     */
    daElaborare,
    /**
     * Comanda presa in carico, il personale sta lavorando
     * per evaderla il primo possibile
     */
    InLavorazione,
    /**
     * La prenotazione &egrave; pronta per
     * essere ritirata
     */
    ProntaPerIlRitiro,

    Consegnato

}
package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

/**
 * formato della bibita, espressa in cl.
 */
public enum Formato {
    /**
     * formati piccolo da 33cl
     */
    piccola,
    /**
     * formato medio da 55cl.
     */
    media,
    /**
     * formato grande da 100cl
     */
    grande;

    int getFormato(Formato f) {
        return switch (f) {
            case piccola -> 33;
            case media -> 55;
            case grande -> 100;
        };
    }

}
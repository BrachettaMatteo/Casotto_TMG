/**
 * formato della bibita, espressa in cl.
 */
public enum Formato {
    media,
    piccola,
    grande;

    int getFormato(Formato f) {
        switch (f) {
            case piccola:
                return 33;
            case media:
                return 55;
            case grande:
                return 100;

            default:
                return -1;
        }
    }

}
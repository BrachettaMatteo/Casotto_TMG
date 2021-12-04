public class Bevanda extends Prodotto {

    private Formato formato;
    private int gradoAlcolico;

    public Bevanda(Formato formato, int gradoAlcolico) {
        if (gradoAlcolico < 0)
            throw new IllegalArgumentException("il grado alcolico non puo essere negativo");
        this.formato = formato;
        this.gradoAlcolico = gradoAlcolico;
    }

    public Formato getFormato() {
        return this.formato;
    }

    public int getGradoAlcolico() {
        return this.gradoAlcolico;
    }

}
public class Cibo extends Prodotto {

    private String ingredienti;
    private String allergeni;

    public Cibo(String ingredienti, String allergeni) {
        //controllo
        if (ingredienti == null)
            throw new NullPointerException("ingredienti null");
        if (allergeni == null)
            throw new NullPointerException("allergeni null");

        if (ingredienti.length() <= 1)
            throw new IllegalArgumentException("ingredienti errati");

        this.ingredienti = ingredienti;
        this.allergeni = allergeni;
    }

    public String getIngredienti() {
        return this.ingredienti;
    }

    public String getAllergeni() {
        return this.allergeni;
    }

}

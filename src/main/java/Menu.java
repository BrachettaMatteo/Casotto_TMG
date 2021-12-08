import java.util.ArrayList;

public class Menu {

	private ArrayList<Prodotto> menu;

	public ArrayList<Prodotto> getMenu() {
		return this.menu;
	}

	/**
	 * 
	 * @param menu ...;
	 */
	public void setMenu(ArrayList<Prodotto> menu) {
		// TODO controllo menu
		this.menu = menu;
	}

	/**
	 * 
	 * @param prodotto  ...;
	 */
	public boolean aggiungiProdotto(Prodotto prodotto) {
		// TODO - controllo prodotto
		return this.menu.add(prodotto);
	}

	/**
	 * 
	 * @param prodotto ...;
	 */
	public boolean rimuoviProdotto(Prodotto prodotto) {
		// TODO - controllo prodotto
		return this.menu.remove(prodotto);
	}

}
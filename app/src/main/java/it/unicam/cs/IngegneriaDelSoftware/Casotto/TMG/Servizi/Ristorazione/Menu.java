package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Ristorazione;

import java.util.ArrayList;

/**
 * rapresenta il menu
 */
public class Menu {
    private Menu instance;
    private ArrayList<Prodotto> menu;

    /**
     * @return il menu
     */
    public Menu getInstance() {
        if (instance == null)
            return new Menu();
        else
            return instance;
    }

    /**
     * permette di creare un nuovo menu
     */
    public Menu() {
        this.menu = new ArrayList<>();
        this.instance = this;
    }

    /**
     * @return la lista dei prodotti del menu
     */
    public ArrayList<Prodotto> getMenu() {
        return this.menu;
    }

    /**
     * permette di sostituire un menu
     *
     * @param menu da sostituire.
     * @return <code>True</code> se il menu &egrave; stato sostituito altrimenti <code>false</code>
     * @throws NullPointerException     se il men&ugrave; null
     * @throws IllegalArgumentException se il men&ugrave;  &egrave; vuoto
     * @throws NullPointerException     se  un prodotto &egrave; null
     */
    public boolean setMenu(ArrayList<Prodotto> menu) {
        if (menu.isEmpty())
            throw new IllegalArgumentException("menu vuoto");
        if (menu == null)
            throw new NullPointerException("menu null");
        for (Prodotto p : menu)
            if (p == null)
                throw new NullPointerException("il prodotto è null");
        this.menu.clear();
        return this.menu.addAll(menu);
    }


    /**
     * permette di aggiungere una portata al men%ugrave;
     *
     * @param prodotto da aggiungere
     * @return <code>true</code> se il prodotto &egrave; stato aggiunti altrimenti <code>false</code>
     * @throws NullPointerException     se il prodotto è null
     * @throws IllegalArgumentException se il prodtto &egrave; gi&agrave; contenuto nel men&ugrave;
     */
    public boolean aggiungiProdotto(Prodotto prodotto) {
        if (prodotto == null)
            throw new NullPointerException("il prodotto è null");
        if (menu.contains(prodotto))
            throw new IllegalArgumentException("il piatto appratiene gia al menu");
        return this.menu.add(prodotto);
    }

    /**
     * permette di rimuovere un prodotto dalla lista
     *
     * @param prodotto prodotto da rimuovere
     * @return <code>True</code> se il prdotto &egrave; stato rimosso altrimenti <code>false</code>
     * @throws IllegalArgumentException se il prodotto non &egrave; contenuto.
     */
    public boolean rimuoviProdotto(Prodotto prodotto) {
        if (this.menu.contains(prodotto))
            return this.menu.remove(prodotto);
        else throw new IllegalArgumentException("il prodotto non è contenuto nel meni");
    }

}
//classe che caratterizza la mappa dell'intero progetto
class Tabella{
    //attributi
    //numero delle caselle per ogni riga e per ogni colonna
    public final static int NUM_CASELLE = 15;
    //matrice di caselle
    Casella[][] tabella = new Casella[NUM_CASELLE][NUM_CASELLE];

    //metodi
    //costruttore con parametri
    public Tabella(Casella[][] _tabella){
        this.tabella=_tabella;
    }
}
//classe che caratterizza la mappa dell'intero progetto
class Tabella {
    // attributi
    // numero delle caselle per ogni riga e per ogni colonna
    public final static int NUM_CASELLE = 15;
    // matrice di caselle
    Casella[][] tabella = new Casella[NUM_CASELLE][NUM_CASELLE];

    // metodi
    // costruttore con parametri
    public Tabella(Casella[][] _tabella) {
        this.tabella = _tabella;
    }

    // metodo che assegna il valore ad ogni casella della tabella (valore relativo al moltiplicatore di ogni singola casella)
    public void assegnaValori() {
        //variabile d'appoggio
        int moltiplicatore = 1;

        //scorrimento righe
        /*for (int row = 0; row <= 14; row++) {
            for (int col = 0; col <= 14; col++) {
                if (x == 8 && y == 8)
                if (y == row || x == col)
                    Moltiplicatore = 3;
            }
        }
        if (x == y) {
            Moltiplicatore = 2;
        }*/
    }
}
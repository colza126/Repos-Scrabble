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

    public boolean controlloLibera(int x,int y,char lettera){
        if(tabella[x][y] == null || tabella[x][y].lettera == lettera){
            return true;
        }
        return false;
    }
/* 
    public boolean controlloCentrale(int x,int y,char lettera){
        if(tabella[x][y] == null || tabella[x][y].lettera == lettera){
            return true;
        }
        return false;
    }
*/
    public boolean controlloMaster(int x, int y,String parola,char direzione){
        for (int i = 0; i < parola.length(); i++) {
            if(direzione == 'r'){
                if(!controlloLibera(x+i,y,parola.charAt(i))){
                    return false;
                }
            }else if(direzione == 'd'){
                if(!controlloLibera(x,y+i,parola.charAt(i))){
                    return false;
                }
            }
            
        }
        return true;
    }

    public void aggiungiParola(int x, int y,String parola,char direzione){
        if(controlloMaster(x, y, parola, direzione)){
            for (int i = 0; i < parola.length(); i++) {
                if(direzione == 'r'){
                        tabella[x+1][y].lettera = parola.charAt(i);
                }else if(direzione == 'd'){
                    tabella[x][y+1].lettera = parola.charAt(i);
                }
                
            }
        }
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
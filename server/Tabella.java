//classe che caratterizza la mappa dell'intero progetto
class Tabella {
    // attributi
    // numero delle caselle per ogni riga e per ogni colonna
    public final static int NUM_CASELLE = 15;
    // matrice di caselle
    Casella[][] tabella;

    // metodi
    // costruttore di default
    public Tabella() {
        this.tabella = new Casella[NUM_CASELLE][NUM_CASELLE];
    }

    // costruttore con parametri
    public Tabella(Casella[][] _tabella) {
        this.tabella = _tabella;
    }

    /*public boolean controlloLibera(int x,int y,char lettera){
        if(tabella[x][y] == null || tabella[x][y].lettera == lettera){
            return true;
        }
        return false;
    }*/

    //metodo nel quale si richiamano tutti i controlli
    public boolean controlloMaster(Richiesta _richiesta){
        //variabile d'appoggio
        boolean work=true;
        char direction= ' ';

        //1. controllo direzione di inserimento della parola
        //verifica direzione
        direction = _richiesta.checkDirezione();
        //controllo integrità
        if(direction == ' ')
            return false;

        //2. controllo che la casella centrale non sia vuota
        //controllo se ci si trova nel primo inserimento --> in questo caso la casella centrale deve obbligatoriamente essere occupata
        work = controllaCasellaCentrale(_richiesta, direction);
            
        
        return true;
    }

    //controllo che la casella centrale della tabella sia piena
    public boolean controllaCasellaCentrale(Richiesta _richiesta, char _direzione){
        //variabile di lavoro
        boolean work=true;

        //controllo se ci si trova al primo inserimento
        //scorrimento righe
        for(int i=0; i < NUM_CASELLE; i++){
            //scorrimento colonne
            for(int j=0; j < NUM_CASELLE;j++){
                //controllo se le caselle sono tutte vuote
                if(tabella[i][j].lettera != ' ')
                    //casella piena
                    work=false;
            }
        }
        
        //se le caselle non sono tutte vuote è già avvenuto il primo inserimento e lì è stato effettuato questo controllo, se l'esecuzione 
        //è proseguita significa che la centrale è occupata
        if(!work)
            return true;
        //se ci si trova nel primo inserimento bisogna controllare che si voglia inserire qualcosa nella casella centrale
        else{
            //se la parola deve essere insirita in verticale
            if(_direzione == 'l')
            {
                //controllo che la colonna di inserimento sia quella centrale (altrimenti è impossibile passare dal centro)
                if(_richiesta.xInizio != 7)
                    //casella centrale non inserita
                    return false;
            }
        }
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
        //int moltiplicatore = 1;

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
//classe che caratterizza la mappa dell'intero progetto
class Tabella {
    //attributi
    //numero delle caselle per ogni riga e per ogni colonna
    public final static int NUM_CASELLE = 15;
    //casella centrale (sia in verticale che in orizzontale)
    public final static int CASELLA_CENTRALE = 7;
    //matrice di caselle
    Casella[][] tabella = new Casella[NUM_CASELLE][NUM_CASELLE];

    //metodi
    //costruttore di default
    public Tabella() {
        this.tabella = new Casella[NUM_CASELLE][NUM_CASELLE];
    }

    //costruttore con parametri
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
    public boolean controlloMaster(Parola _parola){
        //variabile d'appoggio
        boolean work=true;
        char direction= ' ';

        //1. controllo direzione di inserimento della parola
        //verifica direzione
        direction = _parola.checkDirezione();
        //controllo integrità
        if(direction == ' ')
            return false;

        //2. controllo che la parola sia stata inserita in casell consecutive (verticae o orizzontale)
        work = controllaLinearita(_parola, direction);
        //controllo integrità
        if(work == false)
            return false;

        //3. controllo che la casella centrale non sia vuota
        //controllo se ci si trova nel primo inserimento --> in questo caso la casella centrale deve obbligatoriamente essere occupata
        work = controllaCasellaCentrale(_parola, direction);
        //controllo integrità
        if(work == false)
            return false;
        
        //4. controllo che non si esca dalla tabella
        //verifica della posizione delle varie caselle
        work = controlloPosizioniCaselle(_parola);
        //controllo integrità
        if(work == false)
            return false;
        
        //5. controllo che non si intersechino caselle già occupate
        //verifica della posizione delle varie caselle
        //work = controlloCasellePiene(_parola, direction);
        //controllo integrità
        if(work == false)
            return false;



        return true;
    }

    //controllo che la casella centrale della tabella sia piena
    public boolean controllaLinearita(Parola _parola, char _direzione){
        //controllo che il giocatore abbia inserito la parola in maniera adeguata per quel che riguarda la consecutività delle caselle
        //(verifico che le lettere si trovino in caselle successive e in una singola riga/colonna)

        //parola inserita in verticale
        if(_direzione == 'l'){
            //scorrimento intera parola
            for(int i=0; i<_parola.lunghezza-1;i++){
                //controllo che le x siano sempre uguali mentre la y vari di +1 per ogni lettera
                //controlli negati per conrassegnare le parole inserite in mniera errata
                if((_parola.vettore.get(i).x != _parola.vettore.get(i+1).x) || (_parola.vettore.get(i).y != _parola.vettore.get(i+1).y + 1))
                    //parola inserita in maniera errata
                    return false;
            }
            //inserimento corretto
            return true;
        }

        //parola inserita in orizzontale
        else{
            //scorrimento intera parola
            for(int i=0; i<_parola.lunghezza-1;i++){
                //controllo che le y siano sempre uguali mentre la x vari di +1 per ogni lettera
                //controlli negati per conrassegnare le parole inserite in mniera errata
                if((_parola.vettore.get(i).y != _parola.vettore.get(i+1).y) || (_parola.vettore.get(i).x != _parola.vettore.get(i+1).x + 1))
                    //parola inserita in maniera errata
                    return false;
            }
            //inserimento corretto
            return true;
        }
    }
    

    //controllo che la casella centrale della tabella sia piena
    public boolean controllaCasellaCentrale(Parola _parola, char _direzione){
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
            //se la parola deve essere inserita in verticale
            if(_direzione == 'l')
            {
                //controllo sulle x (la x è uguale per tutte le caselle, per cui deve essere quella centrale)
                if(_parola.vettore.get(0).x  != CASELLA_CENTRALE)
                    //casella centrale non inserita
                    return false;
                //controllo sulle  y
                else{
                    //controllo se le lettere passano per il centro
                    if((_parola.vettore.get(0).y  <= CASELLA_CENTRALE)&&(_parola.vettore.get(_parola.vettore.size()).y >= CASELLA_CENTRALE))
                        //casella centrale inserita
                        return true;
                    //se non passano per il centro
                    else    
                        return false;
                }
            }
            //se la parola deve essere inserita in orizzontale
            else {
                //controllo sulle y (la y è uguale per tutte le caselle, per cui deve essere quella centrale)
                if(_parola.vettore.get(0).y  != CASELLA_CENTRALE)
                    //casella centrale non inserita
                    return false;
                //controllo sulle  x
                else{
                    //controllo se le lettere passano per il centro
                    if((_parola.vettore.get(0).x  <= CASELLA_CENTRALE)&&(_parola.vettore.get(_parola.vettore.size()).x >= CASELLA_CENTRALE))
                        //casella centrale inserita
                        return true;
                    //se non passano per il centro
                    else    
                        return false;
                }
            }
        }

        //return true--> parola inserita correttamente per quel che riguarda la casella centrale (che deve essere sempre riempita)
        //return false--> parola non inserita correttamente (casella centrale vuota)
    }

    //controllo che la casella centrale della tabella sia piena
    public boolean controlloPosizioniCaselle(Parola _parola){
        //----------per effettuare questo controllo basta verificare che x e y della prima e dell'ultima casella siano nei limiti----------\\
        //doppio controllo sulle x e le y della prima e dell'ultima casella
        if((_parola.vettore.get(0).x >= 0) && (_parola.vettore.get(0).x < NUM_CASELLE) && (_parola.vettore.get(_parola.vettore.size()).x >= 0) && (_parola.vettore.get(_parola.vettore.size()).x  < NUM_CASELLE) && (_parola.vettore.get(0).y >= 0) && (_parola.vettore.get(0).y < NUM_CASELLE) && (_parola.vettore.get(_parola.vettore.size()).y >= 0) && (_parola.vettore.get(_parola.vettore.size()).y  < NUM_CASELLE))
            return true;
        return false;  

        //return true--> parola inserita correttamente per quel che riguarda la posizione (nessuna lettera esce dalla tabella)
        //return false--> parola non inserita correttamente (una o pù lettere scono dalla tabella)
    }

    /*public void aggiungiParola(int x, int y,String parola,char direzione){
        if(controlloMaster(x, y, parola, direzione)){
            for (int i = 0; i < parola.length(); i++) {
                if(direzione == 'r'){
                        tabella[x+1][y].lettera = parola.charAt(i);
                }else if(direzione == 'd'){
                    tabella[x][y+1].lettera = parola.charAt(i);
                }
                
            }
        }
    }*/

    //controllo che le caselle in cui si vogliono inserire le lettere non siano già piene
    /*public boolean controlloCasellePiene(Richiesta _parola, char _direzione){
        //
    }*/

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
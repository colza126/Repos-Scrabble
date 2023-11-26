//classe che caratterizza la mappa dell'intero progetto

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Tabella {
    //attributi
    //numero delle caselle per ogni riga e per ogni colonna
    public final static int NUM_CASELLE = 15;
    //casella centrale (sia in verticale che in orizzontale)
    public final static int CASELLA_CENTRALE = 7;
    //matrice di caselle
    public Casella[][] tabella = new Casella[NUM_CASELLE][NUM_CASELLE];

    //metodi
    //costruttore di default
    public Tabella() {
        this.tabella = new Casella[NUM_CASELLE][NUM_CASELLE];
        for (int i = 0; i < NUM_CASELLE; i++) {
            for (int j = 0; j < NUM_CASELLE; j++) {
                this.tabella[i][j] = new Casella();
            }
            
        }
    }

    //costruttore con parametri
    public Tabella(Casella[][] _tabella) {
        this.tabella = _tabella;
    }

    

    public String controlloMaster(Parola _parola){
        char dir = _parola.checkDirezione();


        String parolaFinale = "";


        int j = 0;
        if(dir == 'l'){
            int x = _parola.vettore.get(0).x;
            
            for (int i = 0; i < tabella.length; i++) {

                
                if(tabella[x][i].lettera != ' '){
                    parolaFinale += tabella[x][i].lettera;
                }
                if (j < _parola.vettore.size()){

                    if(_parola.vettore.get(j).y ==  i){
                        parolaFinale += _parola.vettore.get(j).contenuto;
                        j++;
                    }
                }
            }

            
        }else if(dir == 'r'){
            int y = _parola.vettore.get(0).y;
            for (int i = 0; i < tabella.length; i++) {
                if(tabella[i][y].lettera != ' '  ){
                    
                    parolaFinale += tabella[i][y].lettera;

                }
                if (j < _parola.vettore.size()){

                    if(_parola.vettore.get(j).x ==  i){
                        parolaFinale += _parola.vettore.get(j).contenuto;
                        j++;
                    }
                }
            }
            
            
        }

        System.out.println();
        if(!checkDizionario(parolaFinale)){
            return "-1";
        }


        return "";

    }

    //metodo nel quale si richiamano tutti i controlli
    public String controlloMasterPP(Parola _parola){
        //variabile d'appoggio
        boolean work=true;
        char direction= ' ';

        //1. controllo che la parola sia composta da almeno 2 lettere
        //verifica lunghezza
        if(_parola.lunghezza<=1)
            //inserita una singola lettera (input non adatto)
            return "La parola deve essere lunga almeno 2 caratteri";
        
        //2. controllo direzione di inserimento della parola
        //verifica direzione
        direction = _parola.checkDirezione();
        //controllo integrità
        if(direction == ' ')
            return "La parola non e stata inserita in caselle conseutive ne in orizzontale, ne in verticale";

        //3. controllo che la parola sia stata inserita in caselle consecutive (verticale o orizzontale)
        work = controllaLinearita(_parola, direction);
        //controllo integrità
        if(work == false)
            return "La parola non e stata inserita in caselle conseutive ne in orizzontale, ne in verticale";

        //4. controllo che la casella centrale non sia vuota
        //controllo se ci si trova nel primo inserimento --> in questo caso la casella centrale deve obbligatoriamente essere occupata
        work = controllaCasellaCentrale(_parola, direction);
        //controllo integrità
        if(work == false)
            return "La casella centrale non puo essere lasciata libera";
        
        //5. controllo che la casella precedente all'inizio e quella successiva alla fine della parola siano vuote (oppure che esse siano parte del bordo)
        //verifica delle caselle
        //work = controllaEstremi(_parola, direction);
        //controllo integrità
        if(work == false)
            return "La parola deve essere prceduta e seguita da una casella vuota o parte del bordo della tabella";

        //6. controllo che non si esca dalla tabella
        //verifica della posizione delle varie caselle
        //work = controlloPosizioniCaselle(_parola);
        //controllo integrità
        //if(work == false)
           // return false;
        
        //6. controllo che non si intersechino caselle già occupate
        //verifica della posizione delle varie caselle
        work = controlloCasellePiene(_parola, direction);
        //controllo integrità
        if(work == false)
            return "Non si possono sovrascrivere delle caselle gia riempite";

        //7. controllo che la parola inserita sia salvata nel dizionario
        //verifica dell'esistenza della parola
        work = checkDizionario(_parola);
        //controllo integrità
        if(work == false)
            return "La parola inserita non esiste";

        //parol inserita correttamente
        return "a";
    }

    //3. controllo che la casella centrale della tabella sia piena
    public boolean controllaLinearita(Parola _parola, char _direzione){
        //controllo che il giocatore abbia inserito la parola in maniera adeguata per quel che riguarda la consecutività delle caselle
        //(verifico che le lettere si trovino in caselle successive e in una singola riga/colonna)

        //parola inserita in verticale
        if(_direzione == 'l'){
            //scorrimento intera parola
            for(int i=0; i<_parola.lunghezza-1;i++){
                //controllo che le x siano sempre uguali mentre la y vari di +1 per ogni lettera
                //controlli negati per conrassegnare le parole inserite in mniera errata
                if(_parola.vettore.get(i).y != _parola.vettore.get(i+1).y - 1)
                    //parola inserita in maniera errata
                    return false;
            }
            //inserimento corretto
            return true;
        }else{
            //scorrimento intera parola
            for(int i=0; i<_parola.lunghezza-1;i++){
                //controllo che le y siano sempre uguali mentre la x vari di +1 per ogni lettera
                //controlli negati per conrassegnare le parole inserite in mniera errata
                if(_parola.vettore.get(i).x != _parola.vettore.get(i+1).x - 1)
                    //parola inserita in maniera errata
                    return false;
            }
            //inserimento corretto
            return true;
        }
    }
    /*
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
         */

        //return true--> parola inserita correttamente per quel che riguarda la casella centrale (che deve essere sempre riempita)
        //return false--> parola non inserita correttamente (casella centrale vuota)
    //4. controllo che la casella centrale della tabella sia piena
    public boolean controllaCasellaCentrale(Parola _parola, char _direzione){
        boolean condizione = false;
        for (int i = 0; i < _parola.vettore.size(); i++) {
            if(_parola.vettore.get(i).x == 8 && _parola.vettore.get(i).y == 8){
                condizione = true;
            }
        }
        return condizione;
    }

    //5. controllo che la casella precedente all'inizio e quella successiva alla fine della parola siano vuote (oppure che esse siano parte del bordo)
    public boolean controllaEstremi(Parola _parola, char _direzione){
        //metodo che controlla se la casella precedente all'inizio e quella successiva alla fine siano vuote

        //parola inserita in verticale
        if(_direzione == 'l'){
            //controllo che la casella superiore alla prima non sia parte del bordo
            if(_parola.vettore.get(0).y != 0)
                //se la casella precedente alla prima non è parte del bordo
                //controllo se la stassa casella è piena
                if(this.tabella[_parola.vettore.get(0).x][_parola.vettore.get(0).y-1].lettera != ' ')
                    //x errata
                    return false;
            
            //controllo che la casella inferiore all'ultima non sia parte del bordo
            if(_parola.vettore.get(_parola.lunghezza-1).y != NUM_CASELLE)
                //se la casella superiore all'ultima non è parte del bordo
                //controllo se la stassa casella è piena
                if(this.tabella[_parola.vettore.get(_parola.lunghezza-1).x][_parola.vettore.get(_parola.lunghezza-1).y+1] .lettera != ' ')
                    //y errata
                    return false;
        }

        //parola inserita in orizzontale
        else{
            //controllo che la casella a sinistra della prima non sia parte del bordo
            if(_parola.vettore.get(0).x != 0)
                //se la casella precedente alla prima non è parte del bordo
                //controllo se la stassa casella è piena
                if(this.tabella[_parola.vettore.get(0).x-1][_parola.vettore.get(0).y].lettera != ' ')
                    //x errata
                    return false;
            
            //controllo che la casella a destra dell'ultima non sia parte del bordo
            if(_parola.vettore.get(_parola.lunghezza-1).x != NUM_CASELLE)
                //se la casella superiore all'ultima non è parte del bordo
                //controllo se la stassa casella è piena
                if(this.tabella[_parola.vettore.get(_parola.lunghezza-1).x+1][_parola.vettore.get(_parola.lunghezza-1).y].lettera != ' ')
                    //y errata
                    return false;
        }

        //se si passano tutti i controlli la parola passata è giusta sotto questo punto di vista
        return true;

        //return true--> parola inserita correttamente per quel che riguarda le caselle agli estremi (prima e dopo c'è una casella vuota o il bordo)
        //return false--> parola non inserita correttamente (estremi pieni e non arte del bordo)
    }

    /*//6. controllo che la casella centrale della tabella sia piena
    public boolean controlloPosizioniCaselle(Parola _parola){
        //----------per effettuare questo controllo basta verificare che x e y della prima e dell'ultima casella siano nei limiti----------\\
        //doppio controllo sulle x e le y della prima e dell'ultima casella
        if((_parola.vettore.get(0).x >= 0) && (_parola.vettore.get(0).x < NUM_CASELLE) && (_parola.vettore.get(_parola.vettore.size()).x >= 0) && (_parola.vettore.get(_parola.vettore.size()).x  < NUM_CASELLE) && (_parola.vettore.get(0).y >= 0) && (_parola.vettore.get(0).y < NUM_CASELLE) && (_parola.vettore.get(_parola.vettore.size()).y >= 0) && (_parola.vettore.get(_parola.vettore.size()).y  < NUM_CASELLE))
            return true;
        return false;  

        //return true--> parola inserita correttamente per quel che riguarda la posizione (nessuna lettera esce dalla tabella)
        //return false--> parola non inserita correttamente (una o pù lettere scono dalla tabella)
    }*/

    //6. controllo che le caselle in cui si vogliono inserire le lettere non siano già piene
    /*public boolean controlloCasellePiene(Richiesta _parola, char _direzione){
        //
    }*/

    //6. controllo che non si intersechino caselle già occupate
    public boolean controlloCasellePiene(Parola _parola, char _direzione){
        //scorrimento di tutte le lettere  e delle rispettive posizioni, confronto di queste con le corrispondenti posizioni della tabella

        //scorrimento di tutte le lettere della parola
        for(int i=0; i < _parola.lunghezza;i++){
            //controllo per ogni lettera se la tabella, nella casella in cui si vuole inserire quella lettera non è piena
            if(this.tabella[_parola.vettore.get(i).x][_parola.vettore.get(i).y].lettera != _parola.vettore.get(i).contenuto && this.tabella[_parola.vettore.get(i).x][_parola.vettore.get(i).y].lettera != ' ' )
                
            return false;
        }

        //se si passano i vari controlli significa che la parola è stta inserita correttamente
        return true;

        //return true--> parola inserita correttamente per quel che riguarda le caselle scelte (tutte le caselle erano vuote)
        //return false--> parola non inserita correttamente (una o più caselle già occupata)
    }

    //7. controllo che la parola inserita sia presente nel dizionario
    public boolean checkDizionario(Parola _parola){
        //salvataggio della parola inserita in una stringa
        //variabili di lavoro
        String parola = "";
        String parolaDizionario = "";

        //scorrimento di tutte le lettere
        for(int i=0; i < _parola.lunghezza; i++)
            //aggiunta di ogni carattere alla parola
            parola+=_parola.vettore.get(i).contenuto;

        //scorrimento di tutto il dizionario e confronta della parola inserita con quelle del dizionario stesso

        //gestione errori
        try {
            //percorso del file dizionario
            File file = new File("parole.txt");
            
            //creazione scanner per la lettura del file
            Scanner scanner = new Scanner(file);
            
            //scorrimento di tutte le parole del file
            while (scanner.hasNext()) {
                //lettura di ogni parola del dizionario
                parolaDizionario=scanner.nextLine();

                //confronto della parola inserita con ogni parola del dizionario
                if(parola.equals(parolaDizionario))
                    //parola esistente
                    return true;
            }
            
            //chiusura sanner
            scanner.close();
        } catch (FileNotFoundException e) { //gestione errori
            e.printStackTrace();
        }

        //se si passano i vari controlli significa che la parola inseerita non è corretta
        return false;

        //return true--> parola inserita correttamente (parola esistente nel dizionario italiano)
        //return false--> parola non inserita correttamente (non sensata)
    }

    public boolean checkDizionario(String _parola){
        //salvataggio della parola inserita in una stringa
        //variabili di lavoro
        String parola = _parola;
        String parolaDizionario = "";

        

        //scorrimento di tutto il dizionario e confronta della parola inserita con quelle del dizionario stesso

        //gestione errori
        try {
            //percorso del file dizionario
            File file = new File("parole.txt");
            
            //creazione scanner per la lettura del file
            Scanner scanner = new Scanner(file);
            
            //scorrimento di tutte le parole del file
            while (scanner.hasNext()) {
                //lettura di ogni parola del dizionario
                parolaDizionario=scanner.nextLine();

                //confronto della parola inserita con ogni parola del dizionario
                if(parola.equals(parolaDizionario))
                    //parola esistente
                    return true;
            }
            
            //chiusura sanner
            scanner.close();
        } catch (FileNotFoundException e) { //gestione errori
            e.printStackTrace();
        }

        //se si passano i vari controlli significa che la parola inseerita non è corretta
        return false;

        //return true--> parola inserita correttamente (parola esistente nel dizionario italiano)
        //return false--> parola non inserita correttamente (non sensata)
    }

    //inserimento delle lettere all'interno della tabella 
    public void aggiungiParola(Parola _parola){
        //----------a questo punto parola è sicuramente corretta per cui viene inserita nella tabella----------\\

        //scorrimento di tutte le lettere
        for(int i=0; i < _parola.lunghezza; i++){
            //inserimento di ogni lettera nella posizione corretta della casella
            this.tabella[_parola.vettore.get(i).x][_parola.vettore.get(i).y].lettera = _parola.vettore.get(i).contenuto;
            //_parola.vettore.get(i).x;

        }
        //la parola è stata inserita all'interno della tabella
    }

    //assegnamento del valore del moltiplicatore ad ogni casella della tabella
    public void assegnaMoltiplicatori(){
        //assegnamento dei moltiplicatori ad ogni casella (inserimento manuale :( )
        //3P
        this.tabella[0][0].moltiplicatore="3P";
        this.tabella[0][7].moltiplicatore="3P";
        this.tabella[0][14].moltiplicatore="3P";
        this.tabella[7][0].moltiplicatore="3P";
        this.tabella[7][14].moltiplicatore="3P";
        this.tabella[14][0].moltiplicatore="3P";
        this.tabella[14][7].moltiplicatore="3P";
        this.tabella[14][14].moltiplicatore="3P";

        //2P
        this.tabella[1][1].moltiplicatore="2P";
        this.tabella[1][13].moltiplicatore="2P";
        this.tabella[2][2].moltiplicatore="2P";
        this.tabella[2][12].moltiplicatore="2P";
        this.tabella[3][3].moltiplicatore="2P";
        this.tabella[3][11].moltiplicatore="2P";
        this.tabella[4][4].moltiplicatore="2P";
        this.tabella[4][10].moltiplicatore="2P";
        this.tabella[10][4].moltiplicatore="2P";
        this.tabella[10][10].moltiplicatore="2P";
        this.tabella[11][3].moltiplicatore="2P";
        this.tabella[11][11].moltiplicatore="2P";
        this.tabella[12][2].moltiplicatore="2P";
        this.tabella[12][12].moltiplicatore="2P";
        this.tabella[13][1].moltiplicatore="2P";
        this.tabella[13][13].moltiplicatore="2P";

        //3L
        this.tabella[1][5].moltiplicatore="3L";
        this.tabella[1][9].moltiplicatore="3L";
        this.tabella[5][1].moltiplicatore="3L";
        this.tabella[5][5].moltiplicatore="3L";
        this.tabella[5][9].moltiplicatore="3L";
        this.tabella[5][13].moltiplicatore="3L";
        this.tabella[9][1].moltiplicatore="3L";
        this.tabella[9][5].moltiplicatore="3L";
        this.tabella[9][9].moltiplicatore="3L";
        this.tabella[9][13].moltiplicatore="3L";
        this.tabella[13][5].moltiplicatore="3L";
        this.tabella[13][9].moltiplicatore="3L";


        //2L
        this.tabella[0][3].moltiplicatore="2L";
        this.tabella[0][11].moltiplicatore="2L";
        this.tabella[2][6].moltiplicatore="2L";
        this.tabella[2][8].moltiplicatore="2L";
        this.tabella[3][0].moltiplicatore="2L";
        this.tabella[3][7].moltiplicatore="2L";
        this.tabella[3][14].moltiplicatore="2L";
        this.tabella[6][2].moltiplicatore="2L";
        this.tabella[6][6].moltiplicatore="2L";
        this.tabella[6][8].moltiplicatore="2L";
        this.tabella[6][12].moltiplicatore="2L";
        this.tabella[7][3].moltiplicatore="2L";
        this.tabella[7][11].moltiplicatore="2L";
        this.tabella[8][2].moltiplicatore="2L";
        this.tabella[8][6].moltiplicatore="2L";
        this.tabella[8][8].moltiplicatore="2L";
        this.tabella[8][12].moltiplicatore="2L";
        this.tabella[11][0].moltiplicatore="2L";
        this.tabella[11][7].moltiplicatore="2L";
        this.tabella[11][14].moltiplicatore="2L";
        this.tabella[12][6].moltiplicatore="2L";
        this.tabella[12][8].moltiplicatore="2L";
        this.tabella[14][3].moltiplicatore="";
        this.tabella[14][11].moltiplicatore="2L";
    }

    //calcolo del numero di moltiplicatori di parola
    public int contaMoltiplicatoriParola(Parola _parola, String xP ){
        //variabile d'appoggio
        int contatore = 0;

        //scorrimento di tutta la parola
        for(int i=0; i<_parola.lunghezza;i++){
            //controllo se c'è un il moltiplicatore di parola cercato nelle casella in cui è inserita ogni singola lettera
            if(this.tabella[_parola.vettore.get(i).x][_parola.vettore.get(i).y].moltiplicatore == xP)
                //incremento del numero di contatori
                contatore++;
        }

        //restituzione del numero di moltiplicatori di parola cercati
        return contatore;
    }

    public String tabellaInStringa(){
        String output = "";
        for (int i = 0; i < tabella.length; i++) {
            for (int j = 0; j < tabella.length; j++) {
                if(tabella[i][j].lettera != ' '){
                    output+= tabella[i][j].lettera+";"+i+";"+j+";";
                }
            }
        }
        return output;
    }
}
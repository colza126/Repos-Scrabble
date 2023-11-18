//cose da fare: comunicazione client server, gestione giocatori e turni
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//gestione del server
class server{
    //elementi per la comunicazione con il client
    private static final int SOCKET_PORT = 666;
    //private static final int BYTE_BUFFER = 1500;

    //dichiarazione oggetti globali
    static DatagramSocket socket;
    static DatagramPacket packet;

    //main
    public static void main(String[] args) throws IOException{
        //giocatori
        GestioneGiocatori listaGiocatori = new GestioneGiocatori();

        //variabili
        //booleano che identifica lo status della comunicazione
        boolean running = true;
        //richiesta del client
        String parola;
        
        //dichiarazione tabella
        Tabella tab = new Tabella();

        //server in attesa di una comunicazione da parte del client
        socket = new DatagramSocket(SOCKET_PORT);
        
        //fase di attesa (ascolto all'interno del canale di comunicazione)
        while(running)
        {
            //lettura della richiesta del client e salvataggio in apposita variabile
            parola = attendiParola(socket, listaGiocatori);

            //----------a questo punto parola contiene una stringa nel seguente formato "lunghezza/lettera,x,y; altre lettere..."----------\\

            if(parola != ""){
                //creazione di una parola (la stringa precedente viene convertita in un oggetto)
                Parola parolaClient = new Parola(parola);

                //----------a questo punto parola contiene l'input del client suddiviso in un insieme di attributi----------\\
                //metodo che controlla l'integrità dell'input del client (direzione della parola, inserimento nelle caselle consecutive, lettere che non escono dalla tabella ecc.)
                String statoInserimento = tab.controlloMaster(parolaClient);

                //controllo se è stato passato un messaggio d'errore
                if(statoInserimento != "")
                    //scrittura al client del tipo di errore effettuato
                    inviaRisposta(statoInserimento);
                
                //----------a questo punto la parola ha passato tutti i controlli, per cui lla si può inserire nella tabella e svolgere le operazioni successive----------\\
                //inserimento della parola corretta all'interno della tabella
                tab.aggiungiParola(parolaClient);

                //conteggio dei punti effettuati dalla giocata
                int punteggio = calcolaPuntiOttenuti(parolaClient, tab);
            }
        }

        //chiudo la socket
        socket.close();
    }

    //calcolo del punteggio ottenuto dal giocatore con l'intera giocata corrente
    public static int calcolaPuntiOttenuti(Parola _parola, Tabella _tab) throws IOException
    {
        int punteggio = 0;
        int cont2P=0;
        int cont3P=0;

        //assegnamento del moltiplicatore ad ogni casella
        _tab.assegnaMoltiplicatori();

        //scorrimento di tutte le lettere
        for(int i=0; i < _parola.lunghezza; i++)
            //calcolo del punteggio di ogni singola casella 
            punteggio += _tab.tabella[_parola.vettore.get(i).x][_parola.vettore.get(i).y].calcolaLettera();

        //verifico se nelle caselle in cui si inserisce la parola è presente uno o più moltiplicatori di parola
        //----------si effettuano i seguenti due controlli perchè se una parola passa su diversi moltiplicatori di parola essi si moltiplicano tra loro----------\\

        //moltiplicatori2P
        cont2P = _tab.contaMoltiplicatoriParola(_parola, "2P");
        //moltiplicatori3P
        cont3P = _tab.contaMoltiplicatoriParola(_parola, "3P");

        //----------a questo punto punteggio contiene la somma dei punti di ogni casella, in più sono salvati i numeri riguardanti quanti sono i moltiplicatori di parola----------\\
        
        //calcolo definitivo (punteggio * moltiplicatori)
        //1. moltiplicazione del punteggio per tutti i 2P
        //scorrimento di tutti i moltiplicatori di questo tipo
        for(int i=0; i<cont2P; i++)
            //moltiplicazione per ogni moltiplicatore 2P
            punteggio = punteggio*2;
        //2. moltiplicazione del punteggio per tutti i 3P
        //scorrimento di tutti i moltiplicatori di questo tipo
        for(int i=0; i<cont3P; i++)
            //moltiplicazione per ogni moltiplicatore 3P
            punteggio = punteggio*3;

        //restituzione del punteggio ottenuto con la mossa dell'utente
        return punteggio;
    }

    //ricezione del messaggio dal client
    public static String attendiParola(DatagramSocket socket, GestioneGiocatori giocatori) throws IOException
    {
        //variabile d'appoggio
        String tmp="";

        //----------implementare la ricezione del messaggio del client----------\\

        //----------il messaggio ppuò essere di due tipi: ingresso in partita / esecuzione mossa----------\\
        //controllo se il messaggio è un richiesta di ingresso in partita
        if(tmp.split(";")[0].charAt(0) == 'i'){       //la frase del client è "i;nome"
            //creazione del nuovo giocatore 
            Giocatore giocatore = new Giocatore(tmp.split(";")[1]);
            //assegnamento delle lettere a disposizione
            giocatore.assegnaLettere();
            //aggiunta all'interno della lista
            giocatori.vettore.add(giocatore);

            //inserimento giocatore avvenuto
            inviaRisposta(giocatore.restituisciLettere());
            //uscita dal metodo
            return "";
        }

        //restituzione del messaggio letto
        return tmp;
    }

    //invio di un messaggio al client
    public static void inviaRisposta(String risposta) throws IOException
    {
        //----------implementare il metodo che comunica con il client----------\\

    }
}
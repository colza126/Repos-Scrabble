//cose da fare: gestione del file punteggi; punteggi per lettera e per casella; comunicazione punteggi
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
            parola = attendiParola(socket);

            //----------a questo punto parola contiene una stringa nel seguente formato "lunghezza/lettera,x,y; altre lettere..."----------\\

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
            //int punteggio = calcolaPuntiOttenuti(parolaClient, tab);
        }

        //chiudo la socket
        socket.close();
    }

    /**
     * metodo per aspettare la richiesta del client
     * @param socket socket da cui prendere la richiesta del client
     * @return String contenente la richiesta del client
     * @throws IOException
     */
    public static String attendiParola(DatagramSocket socket) throws IOException
    {
        //stringa in cui verrà immagazzinato il messaggio
        String buff = "";

        //ricezione del messaggio
        socket.receive(packet);

        //salvataggio del messaggio del client in apposita variabile
        buff = new String(packet.getData(), 0, packet.getLength());

        //----------il messaggio del client è salvato in buff con il seguente formato: "3/C,1,1;I,1,2;A,1,3;o,1,4" (numero caratteri/lettera, x, y/ ... altre lettere ...)----------\
        
        //restituzione della stringa ottenuta
        return buff;
    }

    /**
     * metodo per inviare la risposta al client
     * @param risposta String contenente la risposta da inviare al client
     * @throws IOException
     */
    public static void inviaRisposta(String risposta) throws IOException
    {
        //implementare il metodo che comunica con il client
    }

    //calcolo del punteggio ottenuto dal giocatore con l'intera giocata corrente
    /*public static int calcolaPuntiOttenuti(Parola _parola, Tabella _tab) throws IOException
    {
        int punteggio = 0;

        //assegnamento del moltiplicatore ad ogni casella
        _tab.assegnaMoltiplicatori();

        //scorrimento di tutte le lettere
        for(int i=0; i < _parola.lunghezza; i++){
            punteggio+=_parola.vettore.get(i).calcolaLettera();
        }
    }*/
}
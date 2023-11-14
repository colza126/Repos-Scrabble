import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
        String richiesta;
        //direzione della parola (in base a come la inserisce l'utente)
        char direzione;
        //dichiarazione tabella
        Tabella tab = new Tabella();

        //server in attesa di una comunicazione da parte del client
        socket = new DatagramSocket(SOCKET_PORT);
        
        //fase di attesa (ascolto all'interno del canale di comunicazione)
        while(running)
        {
            //lettura della richiesta del client e salvataggio in apposita variabile
            richiesta = attendiRichiesta(socket);

            //----------a questo punto richiesta contiene una stringa nel seguente formato "parola;xInizio;yInizio;xFine;yFine"----------\\

            //creazione di una richiesta (la stringa precedente viene convertita in un oggetto con 5 attributi)
            Richiesta richiestaClient = new Richiesta();
            //salvataggio degli attributi passati dal client nella richiesta
            richiestaClient.creaRichiesta(richiesta);

            //----------a questo punto richiesta contiene l'input del client suddiviso in un insieme di attributi----------\\
            //metodo che controlla l'integrità dell'input del client (direzione della parola, inserimento nelle caselle consecutive, lettere che non escono dalla tabella ecc.)
            boolean statoInserimento = tab.controlloMaster(richiestaClient);
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
    public static String attendiRichiesta(DatagramSocket socket) throws IOException
    {
        //stringa in cui verrà immagazzinato il messaggio
        String buff = "";

        //ricezione del messaggio
        socket.receive(packet);

        //salvataggio del messaggio del client in apposita variabile
        buff = new String(packet.getData(), 0, packet.getLength());

        //stampo il messagio ricevuto
        //System.out.println(ricevuto);
        
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
        /////implementare metodo che invii una stringa di risposta al client
    }
}
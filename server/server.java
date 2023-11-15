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
            parola = attendiRichiesta(socket);

            //----------a questo punto parola contiene una stringa nel seguente formato "lunghezza/lettera,x,y; altre lettere..."----------\\

            //creazione di una parola (la stringa precedente viene convertita in un oggetto)
            Parola parolaClient = new Parola(parola);

            //----------a questo punto parola contiene l'input del client suddiviso in un insieme di attributi----------\\
            //metodo che controlla l'integrità dell'input del client (direzione della parola, inserimento nelle caselle consecutive, lettere che non escono dalla tabella ecc.)
            boolean statoInserimento = tab.controlloMaster(parolaClient);
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
        /////implementare metodo che invii una stringa di risposta al client
    }
}
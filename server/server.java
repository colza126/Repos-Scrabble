//gestione del server
class server{
    //elementi per la comunicazione con il client
    private static final int SOCKET_PORT = 666;
    private static final int BYTE_BUFFER = 1500;

    //dichiarazione oggetti globali
    static DatagramSocket socket;
    static DatagramPacket packet;

    //main
    public static void main(String[] args){
        //variabili
        //booleano che identifica lo status della comunicazione
        boolean running = true;
        //richiesta del client
        String richiesta="";

        //server in attesa di una comunicazione da parte del client
        socket = new DatagramSocket(SOCKET_PORT);
        
        //fase di attesa (ascolto all'interno del canale di comunicazione)
        while(running)
        {
            //lettura della richiesta del client e salvataggio in apposita variabile
            richiesta = attendiRichiesta(socket);

            //-----------a questo punto richiesta contiene una stringa nel seguente formato "parola;xInizio;yInizio;xFine;yFine"----------//

            //creazione di una richiesta (la stringa precedente viene convertita in un oggetto con 5 attributi)
            Richiesta richiestaClient = new Richiesta();
            //salvataggio degli attributi passati dal client nella richiesta
            richiestaClient.creaRichiesta(richiesta);

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
}
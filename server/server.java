import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    //variabili per la comunicazione
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public server() {

    }

    //attesa di una connessione con il client
    public void start(int port) throws IOException {
        //creazione oggetto che attende la connessione con un client
        serverSocket = new ServerSocket(port);
        //attesa di un client e salvataggio della stesso
        clientSocket = serverSocket.accept();
        //creazione oggetto per inviare dati al client
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        //creazione oggetto per leggere i dati inviati dal client
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    //chiusura delle risorse associate alla connessione
    public void stop() throws IOException {
        //chiusura dell'oggetto che attende un client
        serverSocket.close();
        //chiusura dell'oggetto in cui si salva il client
        clientSocket.close();
        //chiusura dell'oggetto per inviare dati al client
        out.close();
        //chiusura dell'oggetto per la lettura dei dati inviati dal client
        in.close();           
    }

    //lettura del messaggio del client
    public String ricevi() throws IOException {
        //variabile d'appoggio
        StringBuilder stringBuilder = new StringBuilder();
        //buffer per la lettura dei dati inviati dal client
        char[] buffer = new char[1024]; 

        //variabile in cui tenere conto del numero di byte inviati dal client
        int bytesRead;

        //lettura di tutti i byte ricevuti
        while ((bytesRead = in.read(buffer)) != -1) {
            //aggiunta di tutti i byte alla variabile d'appoggio
            stringBuilder.append(buffer, 0, bytesRead);

            //controllo se il byte corrente segnala la fine del messaggio
            if (stringBuilder.toString().contains("\n")) 
                //fine del ciclo
                break;
        }

        //restituzione della variabile d'appoggio (eliminati gli spazi vuoti)
        return stringBuilder.toString().trim();
    }

    //invio di un messaggio al client
    public void inviaMessaggio(String messaggio) {
        //invio del messaggio passato al client (accapo finale)
        out.println(messaggio);
        //svuotamento buffer
        out.flush();
    }
}

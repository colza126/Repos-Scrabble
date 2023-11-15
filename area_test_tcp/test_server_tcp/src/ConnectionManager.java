import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ConnectionManager{

    int porta;
    String indirizzo_ip;
    ServerSocket serverSocket;
    Socket clientSocket;

    public ConnectionManager(int porta,String indirizzo_ip){
        this.porta = porta;
        this.indirizzo_ip = indirizzo_ip;
    }

    public void initializeServer() {
        try {
            // Inizializza il server sulla porta specificata
            this.serverSocket = new ServerSocket(porta,50 ,InetAddress.getByName(indirizzo_ip));
            System.out.println("Server in ascolto su " + indirizzo_ip + ":" + porta);

            // Accetta una connessione in ingresso
            this.clientSocket = serverSocket.accept();            
            System.out.println("Connessione accettata da " + clientSocket.getInetAddress());


            // Chiudi il ServerSocket dopo la connessione
            //serverSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean inviaMessaggio(String messaggio) {
        try {
            // Ottiene il flusso di output dal Socket
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            // Invia il messaggio al client
            output.println(messaggio);
            System.out.println("Messaggio inviato al client: " + messaggio);

            return true; // Restituisce true se l'invio è avvenuto con successo

        } catch (IOException e) {
            e.printStackTrace();
            return false; // Restituisce false in caso di errore
        }
    }
    public String receiveMessage() {
        
        while(true){
            try {
                // Ottieni il flusso di input dal Socket
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    
                StringBuilder messageBuilder = new StringBuilder();
                String line;
    
                // Leggi fino alla fine del messaggio
                while ((line = input.readLine()) != null) {
                    messageBuilder.append(line).append("\n");
                }
    
                // Restituisci il messaggio completo
                return messageBuilder.toString().trim();
    
            } catch (SocketException se) {
                // Gestisci l'eccezione SocketException
                System.out.println("Eccezione SocketException: " + se.getMessage());
                return null;
    
            } catch (IOException e) {
                // Gestisci l'eccezione IOException
                e.printStackTrace();
                return null;
            }
        }
        
    }

}
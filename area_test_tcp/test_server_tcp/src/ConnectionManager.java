import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager{

    int porta;
    String indirizzo_ip;

    public ConnectionManager(int porta,String indirizzo_ip){
        this.porta = porta;
        this.indirizzo_ip = indirizzo_ip;
    }

    public void initializeServer() {
        try {
            // Inizializza il server sulla porta specificata
            ServerSocket serverSocket = new ServerSocket(porta,50 ,InetAddress.getByName(indirizzo_ip));
            System.out.println("Server in ascolto su " + indirizzo_ip + ":" + porta);

            // Accetta una connessione in ingresso
            Socket clientSocket = serverSocket.accept();            
            System.out.println("Connessione accettata da " + clientSocket.getInetAddress());


            // Chiudi il ServerSocket dopo la connessione
            serverSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
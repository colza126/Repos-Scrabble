import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class server {
    private ServerSocket serverSocket;
    private ArrayList<Socket> clientSockets;
    private ArrayList<PrintWriter> outs;
    private ArrayList<BufferedReader> ins;
    private int timeout = 10000; // timeout in milliseconds (10 seconds)

    public server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.clientSockets = new ArrayList<>();
        this.outs = new ArrayList<>();
        this.ins = new ArrayList<>();
        serverSocket.setSoTimeout(timeout);
    }

    public boolean start() {
        try {
            Socket newClientSocket = serverSocket.accept();
            newClientSocket.setSoTimeout(0); // No timeout on client socket

            PrintWriter newOut = new PrintWriter(newClientSocket.getOutputStream(), true);
            BufferedReader newIn = new BufferedReader(new InputStreamReader(newClientSocket.getInputStream()));

            clientSockets.add(newClientSocket);
            outs.add(newOut);
            ins.add(newIn);

            return true;
        } catch (IOException e) {
            // Handle the exception and return false
            return false;
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
        for (int i = 0; i < clientSockets.size(); i++) {
            clientSockets.get(i).close();
            outs.get(i).close();
            ins.get(i).close();
        }
    }

   // Lettura del messaggio del client con un timeout di 100 secondi
    public String ricevi(int giocatore) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int bytesRead;

        // Set the socket timeout to 100 seconds
        clientSockets.get(giocatore).setSoTimeout(100000);
        System.out.println("inizio ad aspettare");
        try {
            while ((bytesRead = ins.get(giocatore).read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, bytesRead);
                if (stringBuilder.toString().contains("\n")) {
                    break;
                }
            }
        } catch (SocketTimeoutException e) {
            // Handle the timeout if necessary (you can choose to do nothing here)
        }

        return stringBuilder.toString().trim();
    }




    public void inviaMessaggio(String message, int id) {
        

        outs.get(id).println(message);
        outs.get(id).flush();
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ConnectionManager() {

    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public String ricevi() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024]; // dimensione del buffer arbitraria, puoi regolarla a seconda delle tue esigenze

        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            stringBuilder.append(buffer, 0, bytesRead);
            if (stringBuilder.toString().contains("\n")) {
                break;
            }
        }

        return stringBuilder.toString().trim();
    }

    public void inviaMessaggio(String messaggio) {
        out.println(messaggio);
        out.flush();
    }
}

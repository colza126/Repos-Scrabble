public class App {
    public static void main(String[] args) throws Exception {
        int porta = 666;
        String ip = "127.0.0.1";

        ConnectionManager c = new ConnectionManager(porta, ip);

        c.initializeServer();
        
    }
}

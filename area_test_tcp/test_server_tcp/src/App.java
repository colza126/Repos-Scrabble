public class App {
    public static void main(String[] args) throws Exception {
        int porta = 666;

        ConnectionManager c = new ConnectionManager();
            while (true) {
            c.start(porta);

            String messaggio = c.ricevi();
            String[] valori = messaggio.split(";");
            c.inviaMessaggio("Ciao client finalmente ci conosciamo "+ valori);
            c.stop();
            
        }
    }
}

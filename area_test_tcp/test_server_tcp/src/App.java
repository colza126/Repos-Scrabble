public class App {
    public static void main(String[] args) throws Exception {
        int porta = 666;

        ConnectionManager c = new ConnectionManager();

        c.start(porta);

        System.out.println(c.ricevi());
        c.inviaMessaggio("Ciao client finalmente ci conosciamo");
        c.stop();
    }
}

//toString della tabella, restituzione lettere necessarie, assegnamento punti ai vari giocatori, vittoria, return tabella e punti
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        //porta di comunicazione
        int porta = 666;
        //creazione server
        server server = new server();

        //variabili
        //giocatori
        GestioneGiocatori listaGiocatori = new GestioneGiocatori();
        //richiesta del client
        String input;
        //dichiarazione tabella
        Tabella tab = new Tabella();

        //attesa di un client per la comunicazione
        server.start(porta);
        //ricezione del messaggio
        int numeroGiocatori = 0;
        int circologiocatori = 1;
        
        while (true) {
            System.out.println("inizio ciclo");
            if(circologiocatori <= numeroGiocatori){
                circologiocatori = 1;
                System.out.println(circologiocatori);
            }
        
            input = server.ricevi();
            System.out.println(input);

            //----------a questo punto input può contenere due informazioni diverse: numero di giocatori o parola inserita----------\\
            //1. input iniziale, comunicazione del numero di giocatori nella partita --> formato: n%4
            //controllo sull'input
            /*
            if(input.split("%")[0].charAt(0) == 'n'){
                //i giocatori possono essere 2 o 4
                //giocatore 1
                Giocatore g1 = new Giocatore("player1");
                //giocatore 2
                Giocatore g2 = new Giocatore("player2");

                //controllo quanti devono essere i giocatori
                if(input.split(";")[0].charAt(0) == '4'){
                    //giocatore 3
                    Giocatore g3 = new Giocatore("player3");
                    //giocatore 4
                    Giocatore g4 = new Giocatore("player4");
                }

            } */
            //risposta dell'avvenuto inserimento del numero di giocatori
            if(input.equals("Nuovo Giocatore")){
                String nomegiocatore = "";
                numeroGiocatori++;
                nomegiocatore ="giocatore numero: " + listaGiocatori.vettore.size()+1; 
                listaGiocatori.aggiungiGiocatore(new Giocatore(nomegiocatore,listaGiocatori.vettore.size()+1));
                listaGiocatori.cercaGiocatore(numeroGiocatori).assegnaLettere();
                System.out.println(nomegiocatore+";"+listaGiocatori.cercaGiocatore(numeroGiocatori).valoreLettere());
                server.inviaMessaggio(nomegiocatore+";"+listaGiocatori.cercaGiocatore(numeroGiocatori).valoreLettere()+"\n");
            }else {
                System.out.println("entro nell'else");
            //if(numeroGiocatori >= 2){
               //procedi con il gioco

                //a questo punto la partita è iniziata e si devono gestire le azioni dei giocatori
                //2. inserimento di una parola --> formato: p1%4/s,1,1;i,1,2;u,1,3;m,1,4 (nome giocatore%lunghezza parola/lettera,x,y;altre lettere ... )
                //controllo sull'input
                    String[] paroleValori = input.split("%");

                    String[] valori = paroleValori[1].split(";");

                    String parolaTotale = "";

                    for (int i = 0; i < valori.length; i+=3) {
                        parolaTotale += valori[i];
                    }
                    
                    //creazione di una parola (la stringa precedente viene convertita in un oggetto)
                    Parola parolaClient = new Parola(parolaTotale);

                    //metodo che controlla l'integrità dell'input del client (direzione della parola, inserimento nelle caselle consecutive, lettere che non escono dalla tabella ecc.)
                    String statoInserimento = tab.controlloMaster(parolaClient);

                    //controllo se è stato passato un messaggio d'errore
                    if(statoInserimento != "")
                        //scrittura al client del tipo di errore effettuato
                        server.inviaMessaggio(statoInserimento);
                    else{
                        //----------a questo punto la parola ha passato tutti i controlli, per cui la si può inserire nella tabella e svolgere le operazioni successive----------\\
                        //inserimento della parola corretta all'interno della tabella
                        tab.aggiungiParola(parolaClient);
    
                        //conteggio dei punti effettuati dalla giocata
                        int punteggio = calcolaPuntiOttenuti(parolaClient, tab);
    
                        //invio del messaggio contenente la tabella, il punteggio del giocatore e le lettere necessarie
                        server.inviaMessaggio(listaGiocatori.nomeGiocatore(circologiocatori) +";"+listaGiocatori.cercaGiocatore(circologiocatori).valoreLettere()+"\n");

                    }
                circologiocatori++;
            }

            

            
        }
        //chiusura ddel canale di comunicazione
        //server.stop();         
    }

    //calcolo del punteggio ottenuto dal giocatore con l'intera giocata corrente
    public static int calcolaPuntiOttenuti(Parola _parola, Tabella _tab) throws IOException
    {
        int punteggio = 0;
        int cont2P=0;
        int cont3P=0;

        //assegnamento del moltiplicatore ad ogni casella
        _tab.assegnaMoltiplicatori();

        //scorrimento di tutte le lettere
        for(int i=0; i < _parola.lunghezza; i++)
            //calcolo del punteggio di ogni singola casella 
            punteggio += _tab.tabella[_parola.vettore.get(i).x][_parola.vettore.get(i).y].calcolaLettera();

        //verifico se nelle caselle in cui si inserisce la parola è presente uno o più moltiplicatori di parola
        //----------si effettuano i seguenti due controlli perchè se una parola passa su diversi moltiplicatori di parola essi si moltiplicano tra loro----------\\

        //moltiplicatori2P
        cont2P = _tab.contaMoltiplicatoriParola(_parola, "2P");
        //moltiplicatori3P
        cont3P = _tab.contaMoltiplicatoriParola(_parola, "3P");

        //----------a questo punto punteggio contiene la somma dei punti di ogni casella, in più sono salvati i numeri riguardanti quanti sono i moltiplicatori di parola----------\\
        
        //calcolo definitivo (punteggio * moltiplicatori)
        //1. moltiplicazione del punteggio per tutti i 2P
        //scorrimento di tutti i moltiplicatori di questo tipo
        for(int i=0; i<cont2P; i++)
            //moltiplicazione per ogni moltiplicatore 2P
            punteggio = punteggio*2;
        //2. moltiplicazione del punteggio per tutti i 3P
        //scorrimento di tutti i moltiplicatori di questo tipo
        for(int i=0; i<cont3P; i++)
            //moltiplicazione per ogni moltiplicatore 3P
            punteggio = punteggio*3;

        //restituzione del punteggio ottenuto con la mossa dell'utente
        return punteggio;
    }

    //ricezione del messaggio dal client
    /* 
    public static String attendiParola(GestioneGiocatori giocatori) throws IOException
    {
        //variabile d'appoggio
        String tmp="";

        //----------implementare la ricezione del messaggio del client----------\\

        //----------il messaggio ppuò essere di due tipi: ingresso in partita / esecuzione mossa----------\\
        //controllo se il messaggio è un richiesta di ingresso in partita
        if(tmp.split(";")[0].charAt(0) == 'i'){       //la frase del client è "i;nome"
            //creazione del nuovo giocatore 
            Giocatore giocatore = new Giocatore(tmp.split(";")[1]);
            //assegnamento delle lettere a disposizione
            giocatore.assegnaLettere();
            //aggiunta all'interno della lista
            giocatori.vettore.add(giocatore);

            //inserimento giocatore avvenuto
            //inviaRisposta(giocatore.restituisciLettere());
            //uscita dal metodo
            return "";
        }

        //restituzione del messaggio letto
        return tmp;
        
    }*/
}

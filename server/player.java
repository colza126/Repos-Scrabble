import java.util.Random;

//classe che gestisce i giocatori
public class Player {


    //attributi
    public String nome;
    public char[] lettere;
    public int punteggio;

    //metodi
    //costruttore di default
    public Player(){
        //numero di lettere che ha a disposizione ogni giocatore
        int lettereOgniGiocatore = 8;

        //punteggio iniziale
        punteggio = 0;
        //inizializzazione del vettore di lettere
        lettere = new char[lettereOgniGiocatore]; 
        //scorrimento del vettore di lettere
        for (int i = 0; i < lettereOgniGiocatore; i++) 
            //inizializzazione di ogni lettera
            lettere[i] = 0;
    }

    //generazione di una consonante casuale
    public char randomConsonante(){
        //oggetto per l'estrazione casuale
        Random random = new Random();

        //generazione di un intero casuale a 32 bit 
        int randomNumber = random.nextInt(25) + 65; 
        //controllo dei casi limite (numeri non validi)
        while(randomNumber == 65 || randomNumber == 69 || randomNumber == 73 || randomNumber == 79 ||  randomNumber == 85)
            //generazione di un intero casuale a 32 bit 
            randomNumber = random.nextInt(25) + 65; 
        
        //restituzione della lettera generata casualmente
        return (char)randomNumber;
    }

    //generazione di una vocale casuale
    public char randomVocale(){
        //oggetto per l'estrazione casuale
        Random random = new Random();

        //generazione di un intero casuale a 32 bit
        int randomNumber = random.nextInt(25) + 65; 
        //controllo dei casi limite (numeri non validi)
        while(!(randomNumber == 65 || randomNumber == 69 || randomNumber == 73 || randomNumber == 79 ||  randomNumber == 85))
            //generazione di un intero casuale a 32 bit
            randomNumber = random.nextInt(25) + 65; // Generates a random 32-bit signed integer
        
        //restituzione della lettera generata casualmente
        return (char)randomNumber;
    }

    //assegnamento delle lettere all'interno del vettore
    public void assegnaLettere(){
        //scorrimento di tutte le lettere del vettore
        for (int i = 0; i < lettere.length; i++) {

            //controllo se alla posizione corrente non è stata assegnata la lettera
            if(lettere[i] == 0){
                //se la posizione corrente è pari (casella 8/ casella 22 ecc.)
                if(i % 2 == 0)
                    //assegnamento di una vocale alla posizione corrente
                    lettere[i] = randomVocale();
                //se la posizione corrente è dispari (casella 1/ casella 13 ecc.)
                else
                    //assegnamento di una consonante alla posizione corrente
                    lettere[i] = randomConsonante();

                //output delle lettere generate
                System.out.println(lettere[i]);
            }
        }
    }
}

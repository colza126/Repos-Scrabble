//gestione delle singole caselle che compongono la tabella

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Casella{
    //attributi
    public char lettera;           //lettera inserita nella casella
    public int valore;             //valore corrispondente alla determinata lettera
    public int x;                  //posizione (x)
    public int y;                  //posizione (y)
    public String moltiplicatore;  //moltiplicatore della casella (ci sono delle caselle speciali che, indipendentemente dall'andamento della partita hanno dei valori speciali)

    //metodi
    //costruttore di default
    public Casella(){
        this.lettera = ' ';       
        this.valore = 0;         
        this.x = 0;              
        this.y = 0;
    }

    //costruttore parametrico
    public Casella(char _lettera, int _val, int _x, int _y){
        this.lettera = _lettera;       
        this.valore = _val;         
        this.x = _x;              
        this.y = _y;
    }
    //calcolo del valore della singola riga (mediante lo scorrimento dell'apposito file)
    public int calcolaLettera(){
        //----------scorrimento di tutte le lettere del file e confronto con la lettera corrente----------\\

        //variabili d'appoggio
        String tmp = "";
        char lettera = ' ';
        int punti = 0;

        //gestione errori
        try {
            //percorso del file dizionario
            File file = new File("valoriLettere.txt");
            
            //creazione scanner per la lettura del file
            Scanner scanner = new Scanner(file);
            
            //scorrimento di tutte le lettere del file
            while (scanner.hasNext()) {
                //lettura di ogni lettera del dizionario
                tmp=scanner.nextLine();
                System.out.println(tmp);

                //----------a questo punto tmp contiene una stringa del tipo "A,4"----------\\

                //split della stringa
                //lettera
                
                lettera = tmp.split(",")[0].charAt(0);
                //punteggio
                //confronto della lettera inserita con ogni lettera del dizionario
                if(this.lettera == lettera+32){
                    
                    punti += Integer.parseInt(tmp.split(",")[1]);
                    System.out.println(punti);
                    //verifico se la casella corrente ha un moltiplicatore di lettere ("2L" o "3L")
                    //controllo sul moltiplicatore
                    //3L
                    if(this.moltiplicatore == "3L")
                        return punti * 3;
                    //2L
                    if(this.moltiplicatore == "2L")
                        return punti * 2;

                    //----------se si arriva a questo punto significa che non c'è alcun moltiplicatore di lettera sulla casella corrente----------\\
                    return punti;
                }
            }
            
            //chiusura sanner
            scanner.close();
        } catch (FileNotFoundException e) { //gestione errori
            e.printStackTrace();
        }

        //il metodo non arriverà mai a questo punto perchè tutte le lettere dell'alfabeto sono presenti nel file contenente i diversi 
        //valori, per cui, qualsiasi lettera inserita prima o poi troverà il suo valore 
        return 0;
    }

}
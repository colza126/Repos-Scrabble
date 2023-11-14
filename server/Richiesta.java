//gestione di una simgola richiesta (contenente la parola, le x e le y della prima casella e dell'ultima casella)
public class Richiesta {
    //attributi
    String parola;
    int xInizio;
    int xFine;
    int yInizio;
    int yFine;

    //metodi
    //costruttore di default
    public Richiesta(){
        this.parola = "";
        this.xInizio = 0;
        this.xFine = 0;
        this.yInizio = 0;
        this.yFine = 0;
    }
    //costruttore parametrico 
    public Richiesta(String _parola, int _xI, int _yI, int _xF, int _yF){
        this.parola = _parola;
        this.xInizio = _xI;
        this.xFine = _xF;
        this.yInizio = _yI;
        this.yFine = _yF;
    }

    //realizzazione dell'oggetto richiesta mediante una stringa in input
    public Richiesta creaRichiesta(String _input){
        //split della stringa passata (parametri suddivisi da ';')
        String[] risultato = _input.split(";");

        //assegnamento degli attributi passati all'interno dell'oggetto (dove necessario le stringhe vengono convertite a intero)
        Richiesta r = new Richiesta(risultato[0], Integer.parseInt(risultato[1]), Integer.parseInt(risultato[2]), Integer.parseInt(risultato[3]), Integer.parseInt(risultato[4]));

        //restituzione
        return r;
    }

    //metodo che controlla il verso d'inserimento della parola (alto-basso / sinistra-destra)
    public char checkDirezione(){
        //-----------bisogna effettuare un controllo sulle x e le y che permetta di capire la direzione della parola----------\\

        //variabile di return
        char direzione = ' ';

        //parola inserita in verticale
        if((this.xInizio == this.xFine) && (this.yInizio != this.yFine))
            direzione='l';      //l = low         

        //parola inserita in orizzontale
        else if((this.yInizio == this.yFine) && (this.xInizio != this.xFine))
            direzione='r';      //r = right
        
        //----------a questo punto se non c'è stato alcun return vuol dire che quantomeno la prima e l'ultima lettera non sono state inserite in maniera corretta----------\\
        else
            //notifica dell'inserimento errato da parte del giocatore
            direzione = ' ';

        //restituzione direzione
        return direzione;
        
    }
}

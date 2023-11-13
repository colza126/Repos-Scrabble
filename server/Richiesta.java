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
    public void creaRichiesta(String _input){
        //split della stringa passata (parametri suddivisi da ';')
        String[] risultato = _input.split(";");

        //assegnamento degli attributi passati all'interno dell'oggetto (dove necessario le stringhe vengono convertite a intero)
        Richiesta r = new Richiesta(risultato[0], Integer.parseInt(risultato[1]), Integer.parseInt(risultato[2]), Integer.parseInt(risultato[3]), Integer.parseInt(risultato[4]));
    }
}

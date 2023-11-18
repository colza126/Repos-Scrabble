//gestione di un insieme di giocatori (visto che non è predefinito il numero di giocatori)

import java.util.ArrayList;
import java.util.List;

public class GestioneGiocatori {
    //attributi
    List<Giocatore> vettore = new ArrayList<Giocatore>();
    
    //costruttore di default
    public GestioneGiocatori(){
        //costruzione vettore
        this.vettore = new ArrayList<Giocatore>();
    }
}

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
    public void aggiungiGiocatore(Giocatore g){
        vettore.add(g);
    }
    public Giocatore cercaGiocatore(int id){
        for (int i = 0; i < vettore.size(); i++) {
            if(vettore.get(i).id == id){
                return vettore.get(i);
            }
        }
        return null;
    }
    public String nomeGiocatore(int id){
            for (int i = 0; i < vettore.size(); i++) {
                if(vettore.get(i).id == id){
                    return vettore.get(i).nome;
                }
            }
            return "non trovato";
    }
}

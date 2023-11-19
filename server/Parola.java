import java.util.ArrayList;
import java.util.List;

//gestione di una singola parola 
public class Parola {
    //attributi
    int lunghezza;
    List<Lettera> vettore = new ArrayList<Lettera>();

    //metodi
    //costruttore di default
    public Parola(){
        this.lunghezza = 0;
        //costruzione vettore
        this.vettore = new ArrayList<Lettera>();
    }
    //costruttore parametrico 
    public Parola(String _stringa){
        //----------_lettere contiene una stringa in questo formato "4/s,1,1;i,1,2;u,1,3;m,1,4"----------\\

        //lunghezza
        //this.lunghezza= Integer.parseInt(_stringa.split("/")[0]);
        //divisione stringa nelle varie parole
        String[] vettLettere=_stringa.split(";");


        //variabile d'appoggio
        Lettera tmp = new Lettera();

        //scorrimento di tutte le lettere
        for(int i=0; i < vettLettere.length;i+=3){
            //creazione di un oggetto lettera
            tmp=new Lettera(vettLettere[i],vettLettere[i+1],vettLettere[i+2]);
            //aggiunta della lettera al vettore corrispondente alla parola
            this.vettore.add(tmp);
            
        }
        lunghezza = vettore.size();
    }

    //metodo che controlla il verso d'inserimento della parola (alto-basso / sinistra-destra)
    public char checkDirezione(){
        //-----------bisogna effettuare un controllo sulle x e le y che permetta di capire la direzione della parola----------\\

        //variabile di return
        char direzione = ' ';
        
        //parola inserita in verticale
        if((this.vettore.get(0).x == this.vettore.get(vettore.size()-1).x) && (this.vettore.get(0).y != this.vettore.get(vettore.size()-1).y))
            direzione='l';      //l = low         

        //parola inserita in orizzontale
        else if((this.vettore.get(0).x != this.vettore.get(vettore.size()-1).x) && (this.vettore.get(0).y == this.vettore.get(vettore.size()-1).y))
            direzione='r';      //r = right
        
        //----------a questo punto se non c'è stato alcun return vuol dire che quantomeno la prima e l'ultima lettera non sono state inserite in maniera corretta----------\\
        else
            //notifica dell'inserimento errato da parte del giocatore
            direzione = ' ';

        //restituzione direzione
        return direzione;
    }
}

//gestione di tutte le lettere di una parola (ogni lettera è caratterizzata dalla x e la y all'interno della tabella oltre al char)
public class Lettera {
    //attributi
    public char contenuto;
    public int x;
    public int y;

    //metodi
    //costruttore di default
    public Lettera(){
        this.contenuto=' ';
        this.x=-1;
        this.y=-1;
    }
    //costruttore parametrico
    public Lettera(String _lettera,String x, String y){
        //split della lettera e delle coordinate
        

        //salvataggio attributi
        //lettera
        this.contenuto= _lettera.charAt(0); 
        //x
        this.x= Integer.parseInt(x);
        //y
        this.y = Integer.parseInt(y);
    }
}

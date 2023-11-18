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
    public Lettera(String _info){
        //split della lettera e delle coordinate
        String[] split =_info.split(",");

        //salvataggio attributi
        //lettera
        this.contenuto=split[0].charAt(0);
        //x
        this.x=split[1].charAt(0);
        //y
        this.y=split[2].charAt(0);
    }
}

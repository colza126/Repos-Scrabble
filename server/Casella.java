//gestione delle singole caselle che compongono la tabella
class Casella{
    //attributi
    char lettera;       //lettera inserita nella casella
    int valore;         //valore corrispondente alla determinata lettera
    int x;              //posizione (x)
    int y;              //posizione (y)

    //metodi
    //costruttore di default
    public Casella(){
        this.lettera = ' ';       
        this.valore = 0;         
        this.x = 0;              
        this.y = 0;
    }
    //costruttore parametrico
    public Casella(char _lettera, int _val, int _x, int_y){
        this.lettera = _lettera;       
        this.valore = _val;         
        this.x = _x;              
        this.y = _y;
    }
}
import java.util.Random;

public class player {


    //attributi
    public String nome;
    public char[] lettere;
    public int punteggio;


    //costruttore per il momento vuoto
    public player(){
        int lettereOgniGiocatore = 8;
        punteggio = 0;
        lettere = new char[lettereOgniGiocatore]; 
        for (int i = 0; i < lettereOgniGiocatore; i++) {
            lettere[i] = 0;
        }
    }


    public char randomConsonante(){

        Random random = new Random();
        int randomNumber = random.nextInt(25) + 65; // Generates a random 32-bit signed integer
        while(randomNumber == 65 || randomNumber == 69 || randomNumber == 73 || randomNumber == 79 ||  randomNumber == 85){
            randomNumber = random.nextInt(25) + 65; // Generates a random 32-bit signed integer
        }
        return (char)randomNumber;
    }
    public char randomVocale(){
        
        Random random = new Random();
        int randomNumber = random.nextInt(25) + 65; // Generates a random 32-bit signed integer
        while(!(randomNumber == 65 || randomNumber == 69 || randomNumber == 73 || randomNumber == 79 ||  randomNumber == 85)){
            randomNumber = random.nextInt(25) + 65; // Generates a random 32-bit signed integer
        }
        return (char)randomNumber;

    }

    public void assegnaLettere(){

        for (int i = 0; i < lettere.length; i++) {
            if(lettere[i] == 0){
                if(i % 2 == 0){
                    lettere[i] = randomVocale();
                }else{
                    
                    lettere[i] = randomConsonante();
                }
                System.out.println(lettere[i]);
            }
        }
    }





}

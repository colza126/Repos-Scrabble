# Repository del progetto SCARABEO di Colzani Lorenzo e Finazzi Andrea

### Informazioni
- Progetto base: https://github.com/colza126/Repos-Scrabble

### Struttura directory

##### client : contiene il client in c#
- Coordinate.cs --> contiene le informazioni riguardanti la posizione di ogni casella
- Game1.cs --> che permette agli utenti di usufruire delle funzionalità del gioco
- Globals.cs --> contiene le informazioni riguardanti i click degli utenti
- Program.cs --> manda in esecuzione il gioco
- Usings.cs --> in cui vengono dichiarate delle librerie necessarie

##### client : contiene il client in c#
- (le classi sono interamente specificate nel class diagram)

### Funzionalità del gioco
- Ingresso in partita dei giocatori
- 8 lettere a disposizione per creare una parola nella tabella
- verificare la correttezza della parola inserita
- Inserimento della parola nella tabella nel caso in cui sia giusta
- Cambio turno 
- Vittoria del primo giocatore che raggiunge 50 punti

### PER GIOCARE
- trascinare le lettere all'interno della tabella nelle pposizioni desiderate
- terminato l'inserimento cliccare il tasto "+" in bsso a destra per verificare la correttezza della parola
- se le lettere dopo il click del pulsante non appaiono sulla tabella significa che l'input è errato
- dopo il click d'inserimento della parola il turno passa all'altro giocatore
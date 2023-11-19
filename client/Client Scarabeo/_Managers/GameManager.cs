using Client_Scarabeo;
using Client_Scarabeo._Managers;

namespace ClientScarabeo;

public  class  GameManager
{
    public static List<Lettera> letters = new();
    public static List<Casella> casels = new();

    private readonly UIManager _ui = new();



    public GameManager(List<string> lettereLista, List<Coordinate> posizioni)
    {
        //aggiungi i bottoni
        _ui.AddButton(new(250+384, 800-24));
        //carico nella variabile la texture della casella 
        var casella = Globals.Content.Load<Texture2D>("Casella");
        //aggiungo nelle caselle gli elementi casella (che sono draggable)

        //per tutte le colonne
        for (int i = 1; i <= 15; i++)
        {
            //per tutte le caselle di quelle righe
            for (int j = 1; j <= 15; j++)
            {
                Boolean targettable = true;
                for (int k = 0; k < posizioni.Count;k++)
                {
                    if (j == posizioni.ElementAt(k).x && i == posizioni.ElementAt(k).y)
                    {
                        targettable = false;
                    }

                }
                
                //aggiungi la casella
                casels.Add(new(casella, new(21 + ((j - 1) * 49), 21 + ((i - 1) * 50)),new(j,i), targettable));
            }
        }
        //per il numero di lettere presenti nella lista
        for (int i = 0; i < lettereLista.Count; i++)
        {
            //inizializza ogni lettere che serve (presa dalla lettera lista)
            var lettere = Globals.Content.Load<Texture2D>(lettereLista[i]);

            //se e' specificata una posizione significa che
            //e' una lettere gia posizionata
            if (posizioni.Count-1 > i)
            {
                letters.Add(new(lettere, new(posizioni[i].x * 48 - 21, posizioni[i].y * 48 - 21), lettereLista[i], false)) ;

            }
            else
            {
                //altrimenti le restanti alla fine sono lettere che il giocatore puo utilizzare
                letters.Add(new(lettere, new(200 + (i * 48), 800), lettereLista[i], true));
            }

        }
    }

    public void Update()
    {
        InputManager.Update();
        DragDropManager.Update();
        _ui.Update();
    }



    public string aggiornaMessaggio()
    {
        return DragDropManager.messaggio;
    }

    public void Draw()
    {
        _ui.Draw();
        foreach (var item in casels)
        {
            item.Draw();
        }

        foreach (var item in letters)
        {
            item.Draw();
        }
    }

    public static void cleanUp()
    {
        letters = new();
        casels = new();
    }
}

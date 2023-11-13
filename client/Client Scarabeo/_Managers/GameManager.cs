namespace ClientScarabeo;

public class GameManager
{
    private readonly List<Lettera> letters = new();
    private readonly List<Casella> casels = new();

    

    public GameManager(String[] lettereLista)
    {
        var casella = Globals.Content.Load<Texture2D>("Casella");

        for (int i = 1; i <= 15; i++)
        {
            for (int j = 1; j <= 15; j++)
            {

                casels.Add(new(casella, new(21 + ((j - 1) * 49), 21 + ((i - 1) * 50)),new(j,i)));
            }
        }
        for (int i = 0; i < lettereLista.Length; i++)
        {
            var lettere = Globals.Content.Load<Texture2D>(lettereLista[i]);

            letters.Add(new(lettere, new(250 + (i * 48), 800), lettereLista[i]));

        }
    }

    public void Update()
    {
        InputManager.Update();
        DragDropManager.Update();
    }

    public void Draw()
    {
        foreach (var item in casels)
        {
            item.Draw();
        }

        foreach (var item in letters)
        {
            item.Draw();
        }
    }
}

using Client_Scarabeo;
using Client_Scarabeo._Managers;

namespace ClientScarabeo;

public class GameManager
{
    private readonly List<Lettera> letters = new();
    private readonly List<Casella> casels = new();

    private readonly UIManager _ui = new();



    public GameManager(String[] lettereLista, Coordinate[] posizioni)
    {

        _ui.AddButton(new(250+384, 800-24));
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


            if (posizioni.Length-1 > i)
            {
                letters.Add(new(lettere, new(posizioni[i].x * 48 - 21, posizioni[i].y * 48 - 21), lettereLista[i]));

            }
            else
            {

                letters.Add(new(lettere, new(200 + (i * 48), 800), lettereLista[i]));
            }

        }
    }

    public void Update()
    {
        InputManager.Update();
        DragDropManager.Update();
        _ui.Update();
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
}

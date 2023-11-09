namespace ClientScarabeo;

public class GameManager
{
    private readonly List<Lettera> _gems = new();
    private readonly List<Casella> _sockets = new();

    public GameManager()
    {
        var gemTexture = Globals.Content.Load<Texture2D>("A");
        var socketTexture = Globals.Content.Load<Texture2D>("Casella");

        for (int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {

                _sockets.Add(new(socketTexture, new(32 + (j * 72), 32 + (i * 73))));
            }
        }
        for (int i = 0; i < 8; i++)
        {

            _gems.Add(new(gemTexture, new(250 + (i * 72), 1150 )));

        }
    }

    public void Update()
    {
        InputManager.Update();
        DragDropManager.Update();
    }

    public void Draw()
    {
        foreach (var item in _sockets)
        {
            item.Draw();
        }

        foreach (var item in _gems)
        {
            item.Draw();
        }
    }
}

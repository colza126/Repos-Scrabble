using Client_Scarabeo;
using Client_Scarabeo._Managers;
using Microsoft.Xna.Framework.Graphics;
using test_client_tcp;

namespace ClientScarabeo;

public class Game1 : Game
{
    private readonly GraphicsDeviceManager _graphics;
    private SpriteBatch _spriteBatch;
    public static GameManager _gameManager;
    public static ConnectionManager connectionManager;
    private static String nomeGiocatore;
    private static String punteggio = "0";
    public static List<Coordinate> cords;
    public static List<string> letters;


    

    static bool IsLettera(string carattere)
    {
        // Assicurati che la stringa contenga esattamente un carattere
        if (carattere.Length == 1)
        {
            char charValue = carattere[0];

            // Verifica se il carattere è una lettera (sia maiuscola che minuscola)
            if ((charValue >= 'A' && charValue <= 'Z') || (charValue >= 'a' && charValue <= 'z'))
            {
                return true;
            }
        }

        // Se non è una lettera, o la stringa contiene più di un carattere, restituisci false
        return false;
    }

    public Game1()
    {

        _graphics = new GraphicsDeviceManager(this);
        Content.RootDirectory = "Content";
        IsMouseVisible = true;
    }

    protected override void Initialize()
    {
        //set up della connesione 
        int port = 666;
        String ip = "127.0.0.1";
        connectionManager = new(ip, port);
        //connessione
        connectionManager.Connect();
        connectionManager.SendMessage("Nuovo Giocatore");

        Globals.Content = Content;
        aggiornaGioco();



        _graphics.PreferredBackBufferWidth = 735;
        _graphics.PreferredBackBufferHeight = 853;
        _graphics.ApplyChanges();


        base.Initialize();
    }



    protected override void LoadContent()
    {
        _spriteBatch = new SpriteBatch(GraphicsDevice);  
        Globals.SpriteBatch = _spriteBatch;
    }

    protected override void Update(GameTime gameTime)
    {
        if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed || Keyboard.GetState().IsKeyDown(Keys.Escape))
            Exit();

        Globals.Update(gameTime);
        _gameManager.Update();


        base.Update(gameTime);
    }

    protected override void Draw(GameTime gameTime)
    {
        GraphicsDevice.Clear(Color.Black);

        _spriteBatch.Begin();
        _spriteBatch.Draw(Globals.Content.Load<Texture2D>("printable-scrabble-board_326273-3013729147"),new Vector2(0,0),Color.White);
        _spriteBatch.DrawString(Globals.Content.Load<SpriteFont>("font"), punteggio, new Vector2(100,750), Color.Black);
        _gameManager.Draw();
        _spriteBatch.End();

        base.Draw(gameTime);
    }
    public static void aggiornaGioco()
    {
        //prendo valori e lettere
        String[] valori = connectionManager.ReceiveMessage().Split(";");

        punteggio = valori[0];

        letters = new List<string>();
        cords = new List<Coordinate>();

        DragDropManager.messaggio += nomeGiocatore + "%";
        int j = 1;
        //riempio l'array lettere di valori in modo da aggiornalo
        int lunghezzaAttuale = letters.Count;
        for (int i = 0; i < 8 - lunghezzaAttuale && valori[j] != null; i++)
        {
            if (IsLettera(valori[j]))
            {
                letters.Add(valori[j]);
                j++;
            }
            else
            {
                cords.Add(new(int.Parse(valori[j]), int.Parse(valori[j + 1])));
                j += 2;
                i = i - 2;
            }


        }
        _gameManager = new(letters, cords);



    }
}

using Client_Scarabeo;
using Client_Scarabeo._Managers;
using Microsoft.Xna.Framework.Graphics;
using test_client_tcp;

namespace ClientScarabeo;

public class Game1 : Game
{
    private readonly GraphicsDeviceManager _graphics;
    private SpriteBatch _spriteBatch;
    private GameManager _gameManager;
    private ConnectionManager connectionManager;


    public Game1()
    {

        _graphics = new GraphicsDeviceManager(this);
        Content.RootDirectory = "Content";
        IsMouseVisible = true;
    }

    protected override void Initialize()
    {
        /*
        //set up della connesione 
        int port = 666;
        String ip = "127.0.0.1";
        connectionManager = new(ip, port);
        //connessione
        connectionManager.Connect();

        //prendo valori e lettere
        String[] valori = connectionManager.ReceiveMessage().Split(";");
        String[] letters = {};
        int j = 0;
        //riempio l'array lettere di valori in modo da aggiornalo
        for (int i = letters.Length; i < 8; i++)
        {
            letters[i] = valori[j].Trim();
            j++;
        } 
        */

        String[] Letters = ["a", "b", "b", "a","c"];
        Coordinate[] cords = [new (1,1), new(1, 2), new(1, 3), new(1, 4)];
        _graphics.PreferredBackBufferWidth = 735;
        _graphics.PreferredBackBufferHeight = 853;
        _graphics.ApplyChanges();

        Globals.Content = Content;
        _gameManager = new(Letters, cords);

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
        _spriteBatch.DrawString(Globals.Content.Load<SpriteFont>("font"), DragDropManager.messaggio, new Vector2(100,750), Color.Black);
        _gameManager.Draw();
        _spriteBatch.End();

        base.Draw(gameTime);
    }
}

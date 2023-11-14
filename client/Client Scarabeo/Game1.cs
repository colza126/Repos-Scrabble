using Client_Scarabeo._Managers;

namespace ClientScarabeo;

public class Game1 : Game
{
    private readonly GraphicsDeviceManager _graphics;
    private SpriteBatch _spriteBatch;
    private GameManager _gameManager;
    private connectionManager connectionManager;

    public Game1()
    {

        _graphics = new GraphicsDeviceManager(this);
        Content.RootDirectory = "Content";
        IsMouseVisible = true;
    }

    protected override void Initialize()
    {
        String[] letters = { "a", "b","c","d","e","f","g","h"};

        _graphics.PreferredBackBufferWidth = 735;
        _graphics.PreferredBackBufferHeight = 853;
        _graphics.ApplyChanges();

        Globals.Content = Content;

        _gameManager = new(letters);

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
        
        _gameManager.Draw();
        _spriteBatch.End();

        base.Draw(gameTime);
    }
}

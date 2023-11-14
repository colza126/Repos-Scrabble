using Client_Scarabeo._Models;
using ClientScarabeo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Client_Scarabeo._Managers
{
    public class UIManager
    {
        private Texture2D ButtonTexture { get; }
        private SpriteFont Font { get; }
        private readonly List<Button> _buttons = new();

        public UIManager()
        {
            ButtonTexture = Globals.Content.Load<Texture2D>("button");
            Font = Globals.Content.Load<SpriteFont>("font");
        }

        public Button AddButton(Vector2 pos)
        {
            Button b = new(ButtonTexture, pos);
            _buttons.Add(b);

            return b;
        }

        public void Update()
        {
            foreach (var item in _buttons)
            {
                item.Update();
            }
        }

        public void Draw()
        {
            foreach (var item in _buttons)
            {
                item.Draw();
            }

        }
    }
}

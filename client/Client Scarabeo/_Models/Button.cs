using ClientScarabeo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Client_Scarabeo._Models
{
    public class Button
    {
        private readonly Texture2D _texture;
        private Vector2 _position;
        private readonly Rectangle _rect;
        private Color _shade = Color.White;

        public Button(Texture2D t, Vector2 p)
        {
            _texture = t;
            _position = p;
            _rect = new((int)p.X, (int)p.Y, t.Width, t.Height);
        }

        public void Update()
        {
            if (Globals.MouseCursor.Intersects(_rect))
            {
                _shade = Color.DarkGray;
                if (Globals.Clicked)
                {
                    Click();
                }
            }
            else
            {
                _shade = Color.White;
            }
        }

        public event EventHandler OnClick;

        private void Click()
        {
            OnClick?.Invoke(this, EventArgs.Empty);
        }

        public void Draw()
        {
            Globals.SpriteBatch.Draw(_texture, _position, _shade);
        }
    }
}

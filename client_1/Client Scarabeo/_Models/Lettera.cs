namespace ClientScarabeo;

public class Lettera : Sprite, IDraggable
{
    public String lettera;

    public Lettera(Texture2D tex, Vector2 pos, string lettera, Boolean draggable) : base(tex, pos)
    {
        if (draggable)
        {
            this.lettera = lettera;
            (this as IDraggable).RegisterDraggable(lettera);

        }
        else
        {

        }

    }
}

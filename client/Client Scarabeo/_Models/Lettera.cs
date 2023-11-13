namespace ClientScarabeo;

public class Lettera : Sprite, IDraggable
{
    public String lettera;

    public Lettera(Texture2D tex, Vector2 pos, string lettera) : base(tex, pos)
    {
        this.lettera = lettera;
        (this as IDraggable).RegisterDraggable(lettera);

    }
}

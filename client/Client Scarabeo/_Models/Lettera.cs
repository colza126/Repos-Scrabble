namespace ClientScarabeo;

public class Lettera : Sprite, IDraggable
{
    public Lettera(Texture2D tex, Vector2 pos) : base(tex, pos)
    {
        (this as IDraggable).RegisterDraggable();
    }
}

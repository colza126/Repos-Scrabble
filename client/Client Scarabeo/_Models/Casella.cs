namespace ClientScarabeo;

public class Casella : Sprite, ITargetable
{


    public Casella(Texture2D tex, Vector2 pos) : base(tex, pos)
    {
        (this as ITargetable).RegisterTargetable();
    }
}

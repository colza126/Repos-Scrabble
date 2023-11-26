using Client_Scarabeo;

namespace ClientScarabeo;

public class Casella : Sprite, ITargetable
{
    public Coordinate cor;

    public Casella(Texture2D tex, Vector2 pos, Coordinate c,Boolean targettable) : base(tex, pos)
    {
        if(targettable)
        {

            cor = c;
            (this as ITargetable).RegisterTargetable(c);
        }
        else
        {

        }
    }
    
}

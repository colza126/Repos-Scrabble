using Client_Scarabeo;

namespace ClientScarabeo;

public interface ITargetable
{
    Rectangle Rectangle { get; }
    Vector2 Position { get; set; }

    void RegisterTargetable(Coordinate c)
    {
        DragDropManager.AddTarget(this,c);
    }
}

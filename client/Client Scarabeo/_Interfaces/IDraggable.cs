using Client_Scarabeo;

namespace ClientScarabeo;

public interface IDraggable
{
    Rectangle Rectangle { get; }
    Vector2 Position { get; set; }

    void RegisterDraggable(String c)
    {
        DragDropManager.AddDraggable(this,c);
    }
}

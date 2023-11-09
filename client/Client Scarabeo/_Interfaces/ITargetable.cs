namespace ClientScarabeo;

public interface ITargetable
{
    Rectangle Rectangle { get; }
    Vector2 Position { get; set; }

    void RegisterTargetable()
    {
        DragDropManager.AddTarget(this);
    }
}

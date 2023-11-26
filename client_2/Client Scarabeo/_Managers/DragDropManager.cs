using Client_Scarabeo;
using Client_Scarabeo._Managers;
using System.Linq;

namespace ClientScarabeo;

public static class DragDropManager
{
    //creo lista degli intem prendibili e posizioni associati
    private static readonly List<IDraggable> _draggables = new();
    public static List<String> letters = new();
    //lista delle caselle
    private static readonly List<ITargetable> _targets = new();
    public static List<Coordinate> _cord_targ = new();
    //item attualmente draggato
    private static IDraggable _dragItem;
    public static int indexItem;
    public static string messaggio = "";

    //aggiungi alla lista gli item prendibli
    public static void AddDraggable(IDraggable item, String let)
    {
        _draggables.Add(item);
        letters.Add(let);
    }

    //aggiungi alla lista gli item target
    public static void AddTarget(ITargetable item, Coordinate c)
    {
        _targets.Add(item);
        _cord_targ.Add(c);
    }

    //controllo l'inizio del drag
    private static void CheckDragStart()
    {
        //se il mouse e' cliccato
        if (InputManager.MouseClicked)
        {
            //per ogni item nella lista
            int i = 0;
            foreach (var item in _draggables)
            {
                //trova quello preso
                if (item.Rectangle.Contains(InputManager.MousePosition))
                {
                    indexItem = i;
                    //mettilo nella variabile
                    _dragItem = item;
                    //letters.ElementAt(i);

                    break;
                }
                i++;
            }
        }
    }
    public static void Cleanup()
    {
        // Add any cleanup logic here, if needed
        _draggables.Clear();
        letters.Clear();
        _targets.Clear();
        _cord_targ.Clear();
        messaggio = "";
    }

    //controllo target
    private static void CheckTarget()
    {
        //per ogni target nella lista
        int i = 0;
        foreach (var item in _targets)
        {
            //se presente dentro il grabber
            if (item.Rectangle.Contains(InputManager.MousePosition))
            {
                
                //metti l'item nella stessa posizione
                _dragItem.Position = item.Position;


                messaggio += letters.ElementAt(indexItem)+";";
                messaggio += _cord_targ.ElementAt(i).x + ";" + _cord_targ.ElementAt(i).y + ";";

                letters.RemoveAt(indexItem);
                _cord_targ.RemoveAt(i);


                _draggables.RemoveAt(indexItem);
                _targets.RemoveAt(i);
                break;
            }
            i++;
        }
    }

    //funzione che controlla la fine dello spostamento
    private static void CheckDragStop()
    {
        //se il muose e' stato rilasciato
        if (InputManager.MouseReleased)
        {
            //richiama la funzione gia descritta sopra
            CheckTarget();


            //item attualmente in presa nullo
            _dragItem = null;
            indexItem = 0;
        }
    }

    //funzione update che applica tutte quelle prima
    public static void Update()
    {
        CheckDragStart();

        if (_dragItem is not null)
        {
            _dragItem.Position = InputManager.MousePosition;
            CheckDragStop();
        }
    }
}

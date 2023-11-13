

using ClientScarabeo;

namespace Client_Scarabeo._Managers
{

    public class coordManager
    {
        public static List<Coordinate> listaCordinate;

        public coordManager()
        {
            listaCordinate = new();
        }
        public void addCordinata(Coordinate c)
        {
            listaCordinate.Add(c);
        }

    }
}

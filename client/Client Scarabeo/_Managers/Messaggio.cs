using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Client_Scarabeo._Managers
{
    public static class Messaggio
    {
        public static string stringa_messaggio = "";


        public static void aggiungiCoordinate(Coordinate aggiungere, string lettera)
        {
            stringa_messaggio += lettera + ";" + aggiungere.x + ";" + aggiungere.y + ";";
        }
    }
}

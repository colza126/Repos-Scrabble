using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Client_Scarabeo._Managers
{
    public class connectionManager
    {

        //attributi per il tcp
        private TcpClient tcpClient;
        private NetworkStream stream;

        //server ip e porta
        private string serverIp;
        private int serverPort;

        //costruttore con paramenti 
        public connectionManager(string ip, int port)
        {
            serverIp = ip;
            serverPort = port;
        }

        //procedura che inizializza la socket tcp
        public void Connect()
        {

            //try catch per gestire gli errori 
            try
            {
                //inizializzo un client tcp
                tcpClient = new TcpClient(serverIp, serverPort);
                //inizializzo lo stream
                stream = tcpClient.GetStream();

                //ora il server e' connesso
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errore durante la connessione al server: " + ex.Message);
            }
        }

        //manda un messagio che metto come parametro
        public void SendMessage(string message)
        {
            //try catch per gestire gli errori 
            try
            {
                // Invia dati al server
                byte[] messageBytes = Encoding.UTF8.GetBytes(message);
                // mando il messaggio
                stream.Write(messageBytes, 0, messageBytes.Length);

                //dati inviati al server
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errore durante l'invio del messaggio: " + ex.Message);
            }
        }

        //funzione che riceve un messaggio e lo ritorna come stringa
        public string ReceiveMessage()
        {
            //try catch per gestire gli errori 
            try
            {
                // Ricevi una risposta dal server
                // Creazione client con un buffer
                byte[] buffer = new byte[1024];
                // Intero di byte che legge dallo stream tcp
                int bytesRead = stream.Read(buffer, 0, buffer.Length);

                // Salva la risposta
                string response = Encoding.UTF8.GetString(buffer, 0, bytesRead);

                //Ritorno la risposta
                return response;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errore durante la ricezione del messaggio: " + ex.Message);
                return null;
            }
        }

        //chiudo la connessione
        public void CloseConnection()
        {
            //try e catch per evitare le eccezioni
            try
            {
                // Chiudi la connessione
                stream.Close();
                tcpClient.Close();
                //connessione chiusa
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errore durante la chiusura della connessione: " + ex.Message);
            }
        }
    }
}

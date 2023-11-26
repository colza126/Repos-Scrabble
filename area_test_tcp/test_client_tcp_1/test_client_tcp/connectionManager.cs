using System;
using System.Net.Sockets;
using System.Text;

namespace test_client_tcp
{
    public class ConnectionManager
    {
        private TcpClient tcpClient;
        private NetworkStream stream;

        private string serverIp;
        private int serverPort;

        public ConnectionManager(string ip, int port)
        {
            serverIp = ip;
            serverPort = port;
        }

        public void Connect()
        {
            try
            {
                tcpClient = new TcpClient(serverIp, serverPort);
                stream = tcpClient.GetStream();
                Console.WriteLine("Connesso");
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errore durante la connessione al server: " + ex.Message);
            }
        }

        public void SendMessage(string message)
        {
            try
            {
                byte[] messageBytes = Encoding.UTF8.GetBytes(message);

                // Invia la lunghezza del messaggio
                byte[] lengthBytes = BitConverter.GetBytes(messageBytes.Length);
                stream.Write(lengthBytes, 0, lengthBytes.Length);

                // Invia il messaggio effettivo
                stream.Write(messageBytes, 0, messageBytes.Length);

                Console.WriteLine("Messaggio inviato al server: " + message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errore durante l'invio del messaggio: " + ex.Message);
            }
        }

        public string ReceiveMessage()
        {
            Boolean condizione = true;
            while(true) {
                try
                {
                    byte[] receivedBytes = new byte[4096];
                    int byteCount = stream.Read(receivedBytes, 0, receivedBytes.Length);

                    if (byteCount > 0)
                    {
                        string receivedMessage = Encoding.UTF8.GetString(receivedBytes, 0, byteCount);
                        return receivedMessage;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Exception: " + e);
                    return "null";
                } 
            }
            return "null2";
        }

        public void CloseConnection()
        {
            try
            {
                stream.Close();
                tcpClient.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errore durante la chiusura della connessione: " + ex.Message);
            }
        }
    }
}

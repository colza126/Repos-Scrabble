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
                stream.Write(BitConverter.GetBytes(messageBytes.Length), 0, 4);
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
            try
            {
                byte[] lengthBytes = new byte[4];
                stream.Read(lengthBytes, 0, 4);
                int messageLength = BitConverter.ToInt32(lengthBytes, 0);

                byte[] messageBytes = new byte[messageLength];
                stream.Read(messageBytes, 0, messageLength);

                string receivedMessage = Encoding.UTF8.GetString(messageBytes);
                return receivedMessage;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errore durante la ricezione del messaggio: " + ex.Message);
                return null;
            }
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

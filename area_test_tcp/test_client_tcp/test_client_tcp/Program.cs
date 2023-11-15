

using test_client_tcp;

Console.WriteLine("Sto partendo!");

String ip = "127.0.0.1";
int port = 666;

connectionManager c1 = new connectionManager(ip, port);
c1.Connect();
c1.SendMessage("viva la figa");
String messaggio = "";
while (messaggio == "")
{
    messaggio = c1.ReceiveMessage();
    Console.WriteLine(messaggio);

}


Console.WriteLine("Sto finendo!");

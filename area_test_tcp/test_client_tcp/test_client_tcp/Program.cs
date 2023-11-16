

using test_client_tcp;

Console.WriteLine("Sto partendo!");

String ip = "127.0.0.2";
int port = 666;

ConnectionManager c1 = new ConnectionManager(ip, port);
c1.Connect();
c1.SendMessage("viva la figa");
String messaggio;
messaggio = c1.ReceiveMessage();
Console.WriteLine(messaggio);
c1.CloseConnection();


Console.WriteLine("Sto finendo!");

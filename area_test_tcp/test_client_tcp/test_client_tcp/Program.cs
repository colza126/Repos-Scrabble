

using test_client_tcp;

Console.WriteLine("Sto partendo!");

String ip = "127.0.0.1";
int port = 666;

connectionManager c1 = new connectionManager(ip, port);
c1.Connect();

Console.WriteLine("Sto finendo!");

﻿

using test_client_tcp;

Console.WriteLine("Sto partendo!\n");

String ip = "127.0.0.2";
int port = 666;

ConnectionManager c1 = new ConnectionManager(ip, port);
c1.Connect();
c1.SendMessage("Ciao server;sono; client 1\n");
String messaggio;


messaggio = c1.ReceiveMessage();
Console.WriteLine(messaggio);

while(true)
{

}

Console.WriteLine("Sto finendo!");
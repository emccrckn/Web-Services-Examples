package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) throws Exception {
	    int port = 90;
	    ServerSocket serverSocket = new ServerSocket(port);
	    System.err.println("Serveur lancé sur le port : " + port);

	    // repeatedly wait for connections, and process
	    while (true) {
	        Socket clientSocket = serverSocket.accept();
	        System.err.println("New client connection");


	        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


	        String s;
	        while ((s = in.readLine()) != null) {
	            System.out.println(s);
	            if (s.isEmpty()) {
	                break;
	            }
	        }

	        out.write("HTTP/1.0 200 OK\r\n");
	        out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
	        out.write("Server: Apache/0.8.4\r\n");
	        out.write("Content-Type: text/html\r\n");
	        //out.write("Content-Length: 59\r\n");
	        out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
	        out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
	        out.write("\r\n");
	        out.write("<TITLE>Exemple</TITLE>");
	        out.write("<P>This is a page example.</P>");

	        System.err.println("Connection with Client Terminated");
	        out.close();
	        in.close();
	        clientSocket.close();
	    }
	}

}

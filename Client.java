package com.uic.ids520;

import java.net.Socket;

public class Client{
public static void main(String arg[]){
	String hostName="10.1.235.71";
	int portNumber=4520;
	try{
		    Socket clientSocket = new Socket(hostName, portNumber);
		    new Frame1(clientSocket);
	}
catch(Exception e){
	e.printStackTrace();
}
}
}
package com.uic.ids520;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{

	public static void main(String[] args) throws IOException {
			try {
				@SuppressWarnings("resource")
				ServerSocket serverSocket = new ServerSocket(4520);
				while(true){
			    Socket clientSocket = serverSocket.accept();
			    Thread dbConnect = new Database(clientSocket);
			    dbConnect.start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
}}
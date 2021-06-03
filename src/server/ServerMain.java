package server;

import java.net.*;
import java.io.*;
import java.util.*;

// http://carfield.com.hk/document/java/tutorial/socket_chat.pdf
//https://stackoverflow.com/questions/27910285/java-echotcpserver-send-to-all-clients
public class ServerMain{

  public static void main(String[] args){

    ArrayList<ServerThread> threadList = new ArrayList<>();
    try{
      @SuppressWarnings("resource")
	ServerSocket serverSocket = new ServerSocket(7000);
      while(true){
        System.out.println("\nReady to accept client connection...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Accepted connection from -> " + clientSocket);
        ServerThread serverThread = new ServerThread(clientSocket, threadList);
        threadList.add(serverThread);
        serverThread.start();
      }
    } catch(IOException ex){
      ex.printStackTrace();
    }
  }
}

package server;

import java.net.*;
import java.io.*;
import java.util.*;

public class ServerThread extends Thread{
    private Socket clientSocket;
    private ArrayList<ServerThread> threadList;
	private DataOutputStream douts;
	private DataInputStream dins;

    public ServerThread(Socket client, ArrayList<ServerThread> threads){
      this.clientSocket = client;
      this.threadList = threads;
      // this.name = "empty";
    }

    @Override
    public void run(){
      try{
        douts = new DataOutputStream(clientSocket.getOutputStream());
        dins = new DataInputStream(clientSocket.getInputStream());

        while(true){
          String outputString = dins.readUTF();
//          if(outputString.equals("name")){
//        	String unique = "ok";
//        	String name = dins.readUTF();
//        	for(ServerThread threads: threadList){
//        		threads.douts.writeUTF(threads.getName());
//        		if(name.equals(threads.getName())) {
//        			unique = "notok";
//        			break;
//        		}
//        	}
//			douts.writeUTF(unique);
//          }
          if(outputString.equals("exit")){
              break;
          }
//          for(ServerThread threads: threadList){
//        	threads.douts.writeUTF(outputString);
//          }
          printToAllClients(outputString);
          System.out.println(outputString);
        }
      } catch (IOException ex){
        ex.printStackTrace();
      }
    }

  private void printToAllClients(String outputString) throws IOException{
    for(ServerThread threads: threadList){
      threads.douts.writeUTF(outputString);
    }
  }
}

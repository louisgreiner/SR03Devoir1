package client;

import java.net.*;
import java.io.*;

// https://www.youtube.com/watch?v=cRfsUrU3RjE
// https://fullstackmastery.com/page/13/12-accepting-multiple-connections

  public class ClientThread extends Thread{
    private Socket clientSocket;
    private DataOutputStream douts;
    private DataInputStream dins;

    public ClientThread(Socket client) throws IOException{
      this.clientSocket = client;
      this.dins = new DataInputStream(clientSocket.getInputStream());
      this.douts = new DataOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run(){
      try{
        while(true){
          System.out.println(dins.readUTF());
        }
      } catch (IOException ex){
        ex.printStackTrace();
      }
    }
  }

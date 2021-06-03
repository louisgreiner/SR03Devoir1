package client;

import java.net.*;
import java.io.*;
import java.util.*;

// https://www.youtube.com/watch?v=cRfsUrU3RjE
// https://fullstackmastery.com/page/13/12-accepting-multiple-connections

public class ClientMain{

  public static void main(String[] args){
    try{
      Socket clientSocket = new Socket("localhost", 7000);
      System.out.println("Successful connection to " + clientSocket);

      DataOutputStream douts = new DataOutputStream(clientSocket.getOutputStream());
      DataInputStream dins = new DataInputStream(clientSocket.getInputStream());
      Scanner scan = new Scanner(System.in);

      String userInput;
      String clientName = "empty";

      ClientThread clientThread = new ClientThread(clientSocket);
      clientThread.start();

      do{
        if(clientName.equals("empty")){
          System.out.print("Enter your name: ");
//          douts.writeUTF("name");
          userInput = scan.nextLine();
//          douts.writeUTF(userInput);
//          if("ok".equals(dins.readUTF())) {
//              clientName = userInput;
//              System.out.print("Name is unique.");
//          }
//          else{
//              System.out.print("Name is not ok, try again.");
//          }
    //      douts.writeUTF(userInput);
          clientName = userInput;
          if(userInput.equals("exit")){
        	douts.writeUTF("exit");
            break;
          }
        }
        else{
          String message = ( "(" + clientName + ")" + " message:");
          userInput = scan.nextLine();
          if(userInput.equals("exit")){
        	douts.writeUTF("exit");
            break;
          }
          douts.writeUTF(message + " " + userInput);
        }
      } while (!userInput.equals("exit"));
      douts.close();
      dins.close();
      scan.close();
    } catch(IOException ex){
      ex.printStackTrace();
    }
  }
}

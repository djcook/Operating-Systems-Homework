//CS4345, Spring 2015, Programming 1, Dylan Cook
import java.net.*;
import java.io.*;
import java.util.*;

public class MyClientChat
{
   public static void main(String[] args)
   {
      try
      {
		   	//create a socket to make connection to server socket
			   Socket sock = new Socket("127.0.0.1", 7000);
            
		   	DataOutputStream data2server = new DataOutputStream(sock.getOutputStream());
			   DataInputStream result4mserver = new DataInputStream(sock.getInputStream());
            
            while(true)
            {
			      Scanner inp = new Scanner(System.in);
			      System.out.println("Enter a message(say End to close): ");
			      String x = inp.nextLine();  
         
	   		   //send the data to the server
			      data2server.writeUTF(x);
			      data2server.flush();
			
		   	   //receive the result from the server	
		   	   String result = result4mserver.readUTF();
			      System.out.println("Server: " + result);
			
              if(x.equals("End"))
              {
                  sock.close();
                  break;
              }  
           }
      } 
      catch(IOException ioe)
      {
			System.err.println("Server Closed!");
	   }
   }
}
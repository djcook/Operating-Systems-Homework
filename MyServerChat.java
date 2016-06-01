//CS4345, Spring 2015, Programming 1, Dylan Cook
import java.net.*;
import java.io.*;
import java.util.*;

public class MyServerChat
{
	public static void main(String[] args)
   {
		try{
   			ServerSocket servSock = new ServerSocket(7000);
   			System.out.println("Server started at "+ new Date() + '\n');
			
   			//Listen for a connection request
   			Socket sock = servSock.accept();
			
   			//create data input and data output streams
   			DataInputStream input4mclient = new DataInputStream(sock.getInputStream());
   			DataOutputStream output2client = new DataOutputStream(sock.getOutputStream());

            while(true)
            {
			   	//receive data from client
				   String i = input4mclient.readUTF();
               System.out.println("Client: " + i);
               
               if(i.equals("End"))
               {   
			   	   sock.close();
                  break;
               }
			   	//send result back to client
               Scanner inp = new Scanner(System.in);
               System.out.println("Enter a message: ");
               String inp2cli = inp.nextLine();
               
				   output2client.writeUTF(inp2cli);
			   }
		   } 
         catch(IOException ioe)
         {
				System.err.println("Server Closed!");
			}

	}//End-of-main
}//End-of-class
//CS4345, Spring 2015, Programming 5, Dylan Cook
import java.util.*;
import java.io.*;

public class SingleTrack
{

   public static void main(String[] args)
   {
      Tunnel tunnel = new Tunnel();
      
      int rightTrain[] = {1,3,5,7,9,11,13,15,17,19};
      int leftTrain[] = {0,2,4,6,8,10,12,14,16,18};
      Thread trains1[] = new Thread[10];
      Thread trains2[] = new Thread[10];
      for(int x=0; x<10; x++)
      {
         trains1[x] = new Thread(new LeftBoundTrain(leftTrain[x],tunnel));
         trains2[x] = new Thread(new RightBoundTrain(rightTrain[x],tunnel));        
      }
      for(int x=0; x<10; x++)
      {
         try
         {
            trains1[x].start();
            trains2[x].start();
           // trains1[x].sleep(1500);
           // trains2[x].sleep(1500);
            trains1[x].join();
            trains2[x].join();  
         }catch(Exception e){}        
      }
   }
}

class LeftBoundTrain implements Runnable
{
   int x;
   Tunnel tunnel;
   public LeftBoundTrain(int x, Tunnel tunnel)
   {
      this.x = x;
      this.tunnel = tunnel;
   }
   
   synchronized public void run()
   {
      System.out.printf("Left-Bound Train " + x + " wants to enter the tunnel.\n");
     
      while(tunnel.isEmpty(tunnel) != true)
      {
         try
         {
            tunnel.wait();
         }
         catch(Exception e){}
      }

         System.out.printf("Left-Bound Train " + x + " wants to enter the tunnel.\n");
         tunnel.enter(tunnel);
      
      
      try
      {
         Thread.sleep(1000);
      }catch(Exception e){}
      
      try{
         System.out.printf("Left-Bound Train " + x + " is exiting the tunnel.\n");
         tunnel.exit(tunnel);
         tunnel.notifyAll();
      }catch(Exception e){}
   }
}

class RightBoundTrain implements Runnable
{
   int x;
   Tunnel tunnel;
   public RightBoundTrain(int x, Tunnel tunnel)
   {
      this.x = x;
      this.tunnel = tunnel;
   }
   
   synchronized public void run()
   {
      System.out.printf("Right-Bound Train " + x + " wants to enter the tunnel.\n");
      while(tunnel.isEmpty(tunnel) != true)
      {
         try
         {
            tunnel.wait();
         }
         catch(Exception e){}
      }
   
         System.out.printf("Right-Bound Train " + x + " is in the tunnel.\n");
         tunnel.enter(tunnel); 
      
      try
      {
         Thread.sleep(500);
      }catch(Exception e){}
      
      try{
         System.out.printf("Right-Bound Train " + x + " is exiting the tunnel.\n");
         tunnel.exit(tunnel);
         tunnel.notifyAll();
      }catch(Exception e){}
   }
}

class Tunnel
{
   private boolean empty;
   
   public Tunnel()
   {
      empty = true;
   }
   synchronized public void enter(Tunnel tunnel)
   {
      empty = false;
   }
   synchronized public void exit(Tunnel tunnel)
   {
      empty = true;
   }
   public boolean isEmpty(Tunnel tunnel)
   {
      return empty;
   }
}
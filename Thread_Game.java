//CS4345, Spring 2015, Programming 4, Dylan Cook
import java.util.*;

public class Thread_Game
{
   public static int p1,p2,p3,dealers,card1,card2;
   public  static boolean win;
   public static String cards[] = {"Ace","King","Queen"};
   public static void main(String[] args)
   {
      while(true)
      {
         Random r = new Random();
         Random c = new Random();
         p1 = 0;
         p2 = 1;
         p3 = 2;
         win = false;
         
         card1 = r.nextInt(3);
         card2 = c.nextInt(3);
         if(card1 == card2)
         {
            card2 += 1;
            card2 = card2 % 3;
         }
         
         Deal d = new Deal(cards, card1, card2);
         Player1 play1 = new Player1(cards, p1, card1, card2, win);
         Player2 play2 = new Player2(cards, p2, card1, card2, win);
         Player3 play3 = new Player3(cards, p3, card1, card2, win);
         
         Thread dealer = new Thread(d);
         Thread seat1 = new Thread(play1);
         Thread seat2 = new Thread(play2);
         Thread seat3 = new Thread(play3);
         try
         {
            dealer.start();
                    
            if(!win)
            {
               try
               {
                  seat1.start();
                  seat1.yield();
               }catch(Exception e){}
            }
            if(!win)
            {
               try
               {
                  seat2.start();
                  seat2.yield();
               }catch(Exception e){}
            }
            if(!win)
            {
               try
               {
                  seat3.start();
                  seat3.yield();
               }catch(Exception e){}
            }
            dealer.sleep(1500);
         }catch(Exception e){};
      }
   }
}

class Deal implements Runnable
{
   int c1,c2;
   String[] card;
   
   public Deal(String[] card, int c1, int c2)
   {
      this.c1 = c1; 
      this.c2 = c2;
      this.card = card;
   }
   
   public void run()
   {     
        System.out.printf("Dealer places " + card[c1] + " and " + card[c2] + " on the table\n");        
   }

}


class Player1 implements Runnable
{
   int p1, c1, c2;
   boolean win;
   String[] card;
   
   public Player1(String[] card, int p1, int c1, int c2, boolean win)
   {
      this.p1 = p1;
      this.c1 = c1;
      this.c2 = c2;
      this.card = card;
      this.win = win;
   }
   
   public void run()
   {
      if((c1 != p1) & (c2 != p1))
      {
        System.out.printf("Player with '" + card[p1] + "' places his card on the table\n");
        System.out.printf("Player with " + card[p1] + " wins the current deal\n");
        System.out.printf("Game continues.....\n\n");
        win = true;
      }
   }
}


class Player2 implements Runnable
{
   int p2, c1, c2;
   boolean win;
   String[] card;
   
   public Player2(String[] card, int p2, int c1, int c2, boolean win)
   {
      this.p2 = p2;
      this.c1 = c1;
      this.c2 = c2;
      this.card = card;
      this.win = win;
   }
   
   public void run()
   {
      if((c1 != p2) & (c2 != p2))
      {
        System.out.printf("Player with '" + card[p2] + "' places his card on the table\n");
        System.out.printf("Player with " + card[p2] + " wins the current deal\n");
        System.out.printf("Game continues.....\n\n");
        win = true;
      }
   }
}


class Player3 implements Runnable
{
   int p3, c1, c2;
   boolean win;
   String[] card;
   
   public Player3(String[] card, int p3, int c1, int c2, boolean win)
   {
      this.p3 = p3;
      this.c1 = c1;
      this.c2 = c2;
      this.card = card;
      this.win = win;
   }
   
   public void run()
   {
      if((c1 != p3) & (c2 != p3))
      {
        System.out.printf("Player with '" + card[p3] + "' places his card on the table\n");
        System.out.printf("Player with " + card[p3] + " wins the current deal\n");
        System.out.printf("Game continues.....\n\n");
        win = true;
      }
   }
}

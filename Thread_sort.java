import java.util.*;

public class Thread_sort
{
	public static void main(String[] args)
   {
      //creating initial random array	
      Random rand = new Random();
      int size = 50 + rand.nextInt(11);
   
      int array[] = new int[size];
	   for(int x = 0; x < array.length; x++)
      {
         array[x] = rand.nextInt(50) + 1;
      }
	   System.out.printf("Array: ");
      for(int x = 0; x <array.length; x++)
      {
         System.out.printf(array[x] + " ");
      }
      System.out.printf("\n\n");
      
      //getting sizes for halfing the arrays
      int a1Size = array.length/2;
      int a2Size = array.length/2;
      
      if((array.length % 2) == 1)
      {
         a2Size = a2Size + 1;
      }
      
      //populating the split arrays
      int a1[] = new int[a1Size];
      int a2[] = new int[a2Size];
      
      for(int i = 0; i < a1Size; i++)
      {
         a1[i] = array[i];
      }
      
      for(int i = a1Size; i < size; i++)
      {
         a2[i - a1Size] = array[i];
      }  
      
      //created threads for sorting arrays
      Sorting s = new Sorting(a1,a1Size);
      Sorting k = new Sorting(a2,a2Size);
      Thread t1 = new Thread(s);
      Thread t2 = new Thread(k);

      t1.start();
      t2.start();
      //printing sorted arrays
      try{
         t1.join();
         System.out.printf("Sorting a1 is now complete\n");
         System.out.printf("A1: ");
         for(int x = 0; x< a1.length; x++)
         {
            System.out.printf(a1[x] + " ");
         }
      }catch(Exception e){}
      System.out.printf("\n\n");
      
      try{
         t2.join();
         System.out.printf("Sorting a2 is now complete\n");
         System.out.printf("A2: ");
         for(int x = 0; x< a2.length; x++)
         {
            System.out.printf(a2[x] + " ");
         }
      }catch(Exception e){}
      System.out.printf("\n\n");
      
      //created array and thread for merging    
      int result[] = new int[size];     
      Merging z = new Merging(a1,a2,result);
      Thread t3 = new Thread(z);
      t3.start();
      
      //printing merged array
      try{
         t3.join();
         System.out.printf("Result: ");
         for(int x = 0; x< result.length; x++)
         {
            System.out.printf(result[x] + " ");
         }
      }catch(Exception e){}
        
	}
}

class Sorting implements Runnable
{  int a[];
   int s;
   public Sorting(int array[], int size){a = array; s = size;}

   public void run()
   {
      Arrays.sort(a);
      
   }
}

class Merging implements Runnable
{
   int a1[];
   int a2[];
   int result[];
   public Merging(int array1[], int array2[], int array3[])
   {
      a1 = array1;
      a2 = array2;
      result = array3;     
   }

   public void run()
   {
      int i = 0;
      int x = 0;
      int k = 0;
      
      while(i < a1.length && x < a2.length)
      {
         if(a1[i] <= a2[x])
         {
            result[k] = a1[i];
            i++;
            k++;
         }
         else
         {
            result[k] = a2[x];
            x++;
            k++;
         }
         if(i == a1.length)
         {
            while(x < a2.length)
            {
               result[k] = a2[x];
               x++;
               k++;
            }
         }
         if(x == a2.length)
         {
            while(i < a1.length)
            {
               result[k] = a1[i];
               i++;
               k++;
            }
         }
         
      }
      System.out.printf("Merging is now complete\n");
   }
}
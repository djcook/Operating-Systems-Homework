//CS4345, Spring 2015, Programming 3, Dylan Cook
import java.util.*;
import java.io.*;

public class Thread_Matrix
{
   public static int A[][];
   public static int B[][];
   public static int P[][];
   public static Thread[][] matrix;
    
	public static void main(String[] args)throws FileNotFoundException
   {
      Scanner file = new Scanner(new File(args[0]));
      //getting dimensions of each matrix
      int r1, r2, c1, c2;
      
      r1= file.nextInt();
      r2= file.nextInt();
      c1= file.nextInt();
      c2= file.nextInt();
      
      A = new int [r1][c1];
      B = new int [r2][c2];
      P = new int [r1][c2];
      matrix = new Thread[r1][c2];
      
      if(r2 == c1)
      {
         System.out.printf("\nThe matrices can be multiplied!\n\n");
         
         //insert values of each matrix
         for(int i=0; i<r1; i++)
         {
            for(int x=0; x<c1; x++)
            {
               A[i][x] = file.nextInt();  
            }
         }
         
         for(int i=0; i<r2; i++)
         {
            for(int x=0; x<c2; x++)
            {
               B[i][x] = file.nextInt();
            }
         }
         
         //create a thread for each position
         for(int i=0; i<r1; i++)
         {
            for(int x=0; x<c2; x++)
            {
               matrix[i][x] = new Thread(new Worker(A, B, P, c1, i, x));
            }
         }
         //multiply the values in each thread
         for(int i=0; i<r1; i++)
         {
            for(int x=0; x<c2; x++)
            {
               try
               {
                  matrix[i][x].start();
                  matrix[i][x].join();
               }
               catch(Exception e){}
            }
         }
         
         //Print the result
         System.out.printf("The resulting matrix from multiplication: \n");
         for(int i=0; i<r1; i++)
         {
            System.out.printf("\n");
            for(int x=0; x<c2; x++)
            {
               if(P[i][x] >= 0)
               {
                  System.out.printf(" ");
               }
               System.out.printf(P[i][x] + "  ");
            }
         }
      }
      else
      {
         System.out.printf("\nThe matrices can't be multiplied!\n");
      }
       
	}
}

class Worker implements Runnable
{
   int[][] matrix1;
   int[][] matrix2;
   int[][] product;
   int a, r, c;
   
   public Worker(int[][]matrix1, int[][]matrix2, int[][]product, int a, int r, int c)
   {
      this.matrix1 = matrix1;
      this.matrix2 = matrix2;
      this.product = product;
      this.a = a;
      this.r = r;
      this.c = c;
   }
   
   public void run()
   {
        for(int x=0; x<a; x++)
        {
            product[r][c] += matrix1[r][x] * matrix2[x][c];
        }
   }
}
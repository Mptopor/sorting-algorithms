import java.util.Arrays;
import java.io.PrintWriter;
import java.io.File;

public class SortTimes
{
   static int[] sizes = {8, 32, 64, 128, 256, 512, 1024, 2*1024, 4*1024, 8*1024,
         16*1024, 32*1024, 64*1024};  
   public static void main(String[] args) throws Exception
   {
      int sIdx = 0;
      long startTime;
      long endTime;
      double time;
      PrintWriter pw = new PrintWriter(new File(lastName + "_sortTimes.csv"));
      pw.println( "Size, BubbleSort, MergeSort, QuickSort, JavaSort" );
      while( sIdx < sizes.length ) 
      {
         int[] A = new int[sizes[sIdx]];
         int[] B = new int[sizes[sIdx]];  
         int[] C = new int[sizes[sIdx]]; 
         int[] D = new int[sizes[sIdx]]; 
         int[] E = new int[sizes[sIdx]]; 
         for( int i=0; i<A.length; i++ ) 
         {
            A[i] = (int)(Math.random()*sizes[sIdx]*2);
            B[i] = A[i];
            C[i] = A[i];
            D[i] = A[i];
            E[i] = A[i];
         }
         System.out.println();
         System.out.println("--------------------------------------------------");
         System.out.println("Initial Values: ");
         printArray(A);
         pw.print(A.length + ", ");

         System.out.println( "Starting bubble sort ---------------- Size = " + B.length);
         startTime = System.nanoTime();
         bubbleSort(B);
         endTime = System.nanoTime();
         time = (endTime - startTime) / 1000000000.;
         System.out.println( "Bubble sort finished - time = " + time + " seconds" );
         pw.print(time + ", ");
         printArray(B);
         System.out.println();

         System.out.println( "Starting merge sort ---------------- Size = " + C.length);
         startTime = System.nanoTime();         
         C = mergeSort(C);         
         endTime = System.nanoTime();
         time = (endTime - startTime) / 1000000000.;
         System.out.println( "Merge sort finished - time = " + time + " seconds.");
         pw.print(time + ", ");
         printArray(C);
         System.out.println();

         System.out.println( "Starting quick sort ---------------- Size = " + D.length);
         startTime = System.nanoTime();
         D = quickSort(D);
         endTime = System.nanoTime();
         time = (endTime - startTime) / 1000000000.;
         System.out.println( "quickSort finished - time = " + time  + " seconds." );
         pw.print(time + ", ");
         printArray(D);
         System.out.println();
         
         System.out.println( "Starting java's Array sort ---------------- Size = " + E.length);
         startTime = System.nanoTime();
         E = javaSort(E);
         endTime = System.nanoTime();
         time = (endTime - startTime) / 1000000000.;
         System.out.println( "Java's Array sort finished - time = " + time  + " seconds." );
         pw.println(time);
         printArray(E);
         System.out.println();

         sIdx = sIdx + 1;   
      }
      pw.close();
   }  
   public static void printArray(int[] X)
   {
      System.out.print("[ ");
      int i=0;
      for( ; i<15 && i<X.length; i++ )  
      {
         System.out.print( X[i] + " " );
      }
      if( i < X.length ) System.out.print("... ");
      System.out.println("]");
   }
   
   public static void bubbleSort(int[] A)
   {
      boolean weSwapped = false;
      
      for( int i=0; i<A.length-1; i++ ) 
      {
         weSwapped = false;
         for( int j=0; j<A.length-i-1; j++ ) 
         {
            if( A[j] > A[j+1] ) 
            {
               weSwapped = true;
               int t = A[j];
               A[j] = A[j+1];
               A[j+1] = t;
            }
         }
         if( weSwapped == false )  /* array is ordered, so early exit */
         {
            return;
         }
      }
   }
   
   public static int[] mergeSort(int[] A)
   {
      if( A.length <= 1 ) return A;
      
      int[] L = new int[A.length/2];
      int[] R = new int[A.length-L.length];
      
      for( int i=0; i<A.length/2; i++ ) 
      {
         L[i] = A[i];
      }
      for( int i=L.length; i<A.length; i++ ) 
      {
         R[i-L.length] = A[i];         
      }
      L = mergeSort(L);   
      R = mergeSort(R);
      
      A = merge(L, R); 
      return A; 
   }
   
   
   public static int[] merge(int[] A, int[] B)
   {
      int[] C = new int[A.length + B.length];
      int c = 0;
      int a = 0;
      int b = 0;
      while (a < A.length && b < B.length ) {
         if(A[a] <= B[b]){
            C[c] = A[a];
            a++;
            c++;
         }
         else {
            C[c] = B[b];
            b++;
            c++;
         }
      }  
      while (a < A.length) {
         C[c] = A[a];
         a++;
         c++;
      }
      while (b < B.length) {
         C[c] = B[b];
         b++;
         c++;
      }
     
      return C;
   }
   public static int[] javaSort(int[] X)
   {
      Arrays.sort(X);
      return X;
   }
   public static int[] quickSort(int[] X)
   {
      return quickSort(X, 0, X.length-1);   
   }
   public static int[] quickSort(int[] X, int s, int e)
   {
      if(s < e) {
         int pivot = partition(X,s,e);
         quickSort(X,s,pivot-1);
         quickSort(X,pivot+1,e);
      }
      return X;
   }
   public static int partition( int[]A, int low, int high )
   {
      int pivot = low;
      for(int i=low; i <=high; i++) {
         if(A[i]<A[low]){
            pivot++;
            int tmp = A[i];
            A[i] = A[pivot];
            A[pivot] = tmp;
         }
      }
      int temp = A[pivot];
      A[pivot] = A[low];
      A[low] = temp;
      return pivot;
   }
}
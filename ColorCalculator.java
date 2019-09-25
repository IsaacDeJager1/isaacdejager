import java.util.*;
import java.lang.*;

public class ColorCalculator {

   public static int[] sequence = new int[8];
   public static int[] firstCol = new int[8];
   public static int[] xValues;
   public static int[] yValues;
   public static int[] values;
   
   public static int percent = 0;
   
   public static int[][] triangle = new int[10][10];
   
   public static void main(String[] args) {

      Scanner input = new Scanner(System.in);
   
      System.out.println("Input the sequence (8 terms).");
      for(int i = 0; i < 8; i++) {
      
         sequence[i] = input.nextInt();
      
      }
   
      System.out.println("Input a value for m.");
      int m = input.nextInt();
      int l = m + 1;
      
      xValues = new int[m];
      yValues = new int[m];
      
      values = new int[2 * m];
      
      int count = -1;
      double temp = Math.pow(2, xValues.length + 1) + 1;
      int max = (int)temp;
      
      temp = Math.pow(max, 2 * m);
      int maxComb = (int)temp;
      
      generateXY(l, m, count, max, maxComb);
      
   }
   
   public static boolean check = false;
   public static void generateXY(int l, int m, int count, int max, int maxComb) {
      
      count++;
      
      for(int i = 0; i < max; i++) {
         
         if(check == true) {
            break;
         }
         
         values[count] = i;
         
         if(count < (2 * m - 1))  {
            generateXY(l, m, count, max, maxComb);
         }
         
         else {
            
            for(int j = 0; j < m; j++) {
               xValues[j] = values[j];
            }
            
            for(int j = 0; j < m; j++) {
               yValues[j] = values[m + j];
            }
            
            fillTriangle(m);
            findFirstCol();
            
            
            
            if(sequenceCheck(maxComb) == true) {

               check = true;
               
               for(int j = 0; j < m; j++) {
                  System.out.println("x" + j + " = " + xValues[j]);
               }
               for(int j = 0; j < m; j++) {
                  System.out.println("y" + j + " = " + yValues[j]);
               }
            }
            
            if(percent == maxComb && check != true) {
               System.out.println("No combination found.");
               System.exit(0);
            }
            
            emptyTriangle();
            
         }
      
      }
      
   }
               
   
   public static void fillTriangle(int m) {
   
      triangle[0][0] = 1;
   
      for(int n = 1; n < triangle.length; n++) {
         
         for(int k = 0; k < n + 1; k++) {
            
            if(k == 0) {

               for(int i = 0; i < m && i < triangle.length; i++) {
                  
                  triangle[n][k] += (xValues[i] * triangle[n - 1][i]);
                  
               }
               
               if(m < triangle.length) {
               
                  triangle[n][k] += triangle[n - 1][m];
                  
               }
               
            }
            
            else {

               triangle[n][k] = triangle[n - 1][k - 1];
               
               for(int i = 0; i < m && (k + i) < triangle.length; i++) {
               
                  triangle[n][k] += (yValues[i] * triangle[n - 1][k + i]);
               
               }
               
               if((k + m) < triangle.length) {
               
                  triangle[n][k] += triangle[n - 1][k + m];
                  
               }
            
            }
         
         }
         
      }
   
   }
   
   public static void emptyTriangle() {
   
      for(int i = 0; i < triangle.length; i++) {
         
         for(int j = 0; j < triangle.length; j++) {
            
            triangle[i][j] = 0;
            
         }
         
      }
   
   }
   
   public static void findFirstCol() {
   
      for(int i = 0; i < 8; i++) {
      
         firstCol[i] = triangle[i][0];
         
      }
   
   }
   
   public static boolean sequenceCheck(int max) {
      
      percent++;
      
      if((max / 5) == percent) {
         System.out.println("80% of possible combinations remain");
      }
      
      if((2 * max / 5) == percent) {
         System.out.println("60% of possible combinations remain");
      }
      
      if((3 * max / 5) == percent) {
         System.out.println("40% of possible combinations remain");
      }
      
      if((4 * max / 5) == percent) {
         System.out.println("20% of possible combinations remain");
      }
      
      if(max == percent) {
         System.out.println("0% of possible combinations remain");
      }
      
      return Arrays.equals(firstCol, sequence);
      
   }
   
   public static void printTable() {
      
      System.out.println("Motzkin Triangle");
      System.out.println("----------------");
      
      for(int n = 0; n < 6; n++) {
      
         for(int k = 0; k < n + 1; k++) {
         
            System.out.print(triangle[n][k]);
            if(triangle[n][k] < 100) {
               System.out.print("\t\t");
            }
            else {
               System.out.print("\t");
            }
         
         }
         
         System.out.println();
         System.out.println();
         System.out.println();
         
      }
      
   }
     
}

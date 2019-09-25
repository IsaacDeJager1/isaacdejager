import java.util.*;

public class MotzkinTriangle {
   
   public static int[][] triangle = new int[100][100];
   
   public static void main(String[] args) {
      
      int userChoice = 0;
      
      do {
      
         fillTriangle();
      
         Scanner input = new Scanner(System.in);
      
         System.out.println(" 1 - print the triangle");
         System.out.println(" 2 - print the first column");
         System.out.println(" 3 - print the row sums");
         System.out.println(" 4 - print alternating row sums, every row starts positive");
         System.out.println(" 5 - print alternating row sums, the sign of each row alternates");
         System.out.println(" 6 - print the binomial transformation of the first column");
         System.out.println(" 7 - print the anti-diagonals");
         System.out.println(" 8 - print the central coefficients");
         System.out.println(" 9 - make a new triangle");
         System.out.println("10 - exit");
      
         //While loop for menu      
         while(userChoice != 10) {
         
            userChoice = input.nextInt();
         
            if(userChoice == 1) {
               printTable();
            }
            else if(userChoice == 2) {
               printFirstCol();
            }
            else if(userChoice  == 3) {
               printRowSums();
            }
            else if(userChoice == 4) {
               printAltRowSumsA();
            }
            else if(userChoice == 5) {
               printAltRowSumsB();
            }
            else if(userChoice == 6) {
               printBinomialTransformation();
            }
            else if(userChoice == 7) {
               printAntiDiagonals();
            }
            else if(userChoice == 8) {
               printCentralCoefficients();
            }
            else if(userChoice == 9) {
               
               for(int i = 0; i < triangle.length; i++) {
                  for(int j = 0; j < i + 1; j++) {
                     triangle[i][j] = 0;
                   }
               }
               break;
               
            }
            else if(userChoice == 10) {
               System.exit(0);
            }
            else {
               System.out.println("Please enter a value associated with the menu.");
            }
            
         }
         
      }while(userChoice == 9);

   }
   
   //This method takes the inputed values and generates a triangle
   public static void fillTriangle() {
   
      Scanner input = new Scanner(System.in);
      
      int m;
      System.out.print("m = ");
      m = input.nextInt();
      
      //(x,y) coloring arrays
      int[] x = new int[m];
      int[] y = new int[m];

      triangle[0][0] = 1;
      
      for(int i = 0; i < m; i++) {
         System.out.print("x" + i + " = ");
         x[i] = input.nextInt();
      }
      
      for(int i = 0; i < m; i++) {
         System.out.print("y" + i + " = ");
         y[i] = input.nextInt();
      }
      
      System.out.println();
      System.out.println();
      
      //Outer for loop for every row
      for(int n = 1; n < triangle.length; n++) {
         
         //Inner for loop for every column
         for(int k = 0; k < n + 1; k++) {
            
            //Calculating the first column
            if(k == 0) {

               for(int i = 0; i < m && i < triangle.length; i++) {
                  
                  triangle[n][k] += (x[i] * triangle[n - 1][i]);
                  
               }
               
               //We add this later to ensure we dont try to add an element outside
               //the bounds of the array
               if(m < triangle.length) {
               
                  triangle[n][k] += triangle[n - 1][m];
                  
               }
               
            }
            
            //Calculating all other columns
            else {

               triangle[n][k] = triangle[n - 1][k - 1];
               
               for(int i = 0; i < m && (k + i) < triangle.length; i++) {
               
                  triangle[n][k] += (y[i] * triangle[n - 1][k + i]);
               
               }
               
               if((k + m) < triangle.length) {
               
                  triangle[n][k] += triangle[n - 1][k + m];
                  
               }
            
            }
         
         }
         
      }
   
   }
   
   //This method prints the triangle
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
   
   //This method prints the first column
   public static void printFirstCol() {
      
      System.out.println("First column");
      System.out.println("------------");
      
      for(int i = 0; i < 10; i++) {
      
         System.out.print(triangle[i][0]);
         if(i != 9) {
            System.out.print(", ");
         }
         else {
            System.out.print(", ...");
         }
      
      }
      System.out.println();
      System.out.println();
      System.out.println();
      
   }
   
   //This method prints the row sums
   public static void printRowSums() {
      
      System.out.println("Row sums");
      System.out.println("--------");
      
      int sum = 0;
      
      for(int i = 0; i < 10; i++) {
      
         for(int j = 0; j < i + 1; j++) {
         
            sum += triangle[i][j];
         
         }
         
         System.out.print(sum);
         if(i != 9) {
            System.out.print(", ");
         }
         else {
            System.out.print(", ...");
         }
         sum = 0;
         
      }
      System.out.println();
      System.out.println();
      System.out.println();
   
   }
   
   //This method prints the alternating row sums
   public static void printAltRowSumsA() {
   
      System.out.println("Alternating row sums - all rows start positive");
      System.out.println("----------------------------------------------");
      
      int sum = 0;
      
      for(int i = 0; i < 10; i++) {
         
         for(int j = 0; j < i + 1; j++) {
            
            sum += (Math.pow(-1, j)) * triangle[i][j];
           
         }
         
         System.out.print(sum);
         if(i != 9) {
            System.out.print(", ");
         }
         else {
            System.out.print(", ...");
         }
         sum = 0;
         
      }
      
      
      System.out.println();
      System.out.println();
      System.out.println();
   
   }

   
   public static void printAltRowSumsB() {
   
      System.out.println("Alternating row sums - the start of each row alternates");
      System.out.println("-------------------------------------------------------");
      
      int sum = 0;
      
      for(int i = 0; i < 10; i++) {
         
         for(int j = 0; j < i + 1; j++) {
            
            sum += (Math.pow(-1, i)) * (Math.pow(-1, j)) * triangle[i][j];
            
         }
         
         System.out.print(sum);
         if(i != 9) {
            System.out.print(", ");
         }
         else {
            System.out.print(", ...");
         }
         sum = 0;
         
       }
       
       System.out.println();
       System.out.println();
       System.out.println();
   
   }
   
   
   public static void printBinomialTransformation() {
      
      System.out.println("Binomial Transformation");
      System.out.println("-----------------------");
      
      int[][] pascal = new int[15][15];
      for(int i = 0; i < pascal.length; i++) {
         
         for(int j = 0; j < i + 1; j++) {
            
            if(j == 0) {
               
               pascal[i][j] = 1;
               
            }
            
            else {
               
               pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
               
            }
            
         }  
          
      }
      
      int[] sequence = new int[10];
      for(int i = 0; i < sequence.length; i++) {
         sequence[i] = triangle[i][0];
         /*for(int j  = 0; j < i + 1; j++) {
            sequence[i] += triangle[i][j];
         }*/
      }
      
      int x;
      for(int i = 0; i < sequence.length; i++) {
         
         x = 0;
         for(int j  = 0; j < i + 1; j++) {

            x += (pascal[i][j] * sequence[j]);
            
         }

         System.out.print(x);
         if(i < sequence.length - 1) {
            System.out.print(", ");
         }
         else {
            System.out.print(", ...");
         }
         
      }
      
      System.out.println();
      System.out.println();
      System.out.println();
      
   }
   
   
   public static void printAntiDiagonals() {
   
      System.out.println("AntiDiagonals");
      System.out.println("-------------");
      
      
      int sum = 0;
      int c;
      int n;
      int k;
      
      for(int i = 0; i < 10; i++) {
      
         if(i == 0) {
            System.out.print(triangle[0][0] + ", ");
         }
         
         else {
            
            if(i % 2 == 0) {
               c = (i / 2) + 1;
               n = i / 2;
               k = i / 2;
            }
            
            else {
               c = (i + 1) / 2;
               n = (i + 1) / 2;
               k = (i - 1) / 2;
            }
            
            
            
            for(int j = 0; j < c; j++) {
               
               sum += triangle[n + j][k - j];
               
            }
            
            System.out.print(sum);
            if(i != 9) {
               System.out.print(", ");
            }
            else {
               System.out.print(", ...");
            }
            
            sum = 0;
            
         }
         
      }
      
      System.out.println();
      System.out.println();
      System.out.println();
   
   }
   
   
   public static void printCentralCoefficients() {
   
      System.out.println("Central Coefficients");
      System.out.println("--------------------");
      
      for(int i = 0; i < 20; i = i + 2) {
         
         System.out.print(triangle[i][i / 2]);
         
         if(i != 18) {
            System.out.print(", ");
         }
         else {
            System.out.print(", ...");
         }
         
      }
      
      System.out.println();
      System.out.println();
      System.out.println();
   
   }
 
}

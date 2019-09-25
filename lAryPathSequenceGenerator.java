import java.util.*;

public class lAryPathSequenceGenerator {

   public static int[] path;
   public static int numOfPaths;
   
   public static void main(String[] args) {
   
      Scanner input = new Scanner(System.in);
      
      System.out.print("l = ");
      int l = input.nextInt();
      
      System.out.print("a = ");
      int a = input.nextInt();
      System.out.println();
      
      //Include for r-Fine paths
      System.out.print("r = ");
      int r = input.nextInt();
      System.out.println();
      if(r >= l) {
         System.out.println("l must be greater than r.");
         System.exit(0);
      }
      
      if(a >= l) {
         System.out.println("l must be greater than a.");
         System.exit(0);
      }
      
      
      for(int n = 0; n < 10; n++) {
      
         numOfPaths = 0;
      
         if(n == 0) {
         
            System.out.print("1, ");
            continue;
            
         }
         
         path = new int[n * l];
         int count;
         
         for(int i = 0; i < l - 1 - a; i++) {
               path[i] = 0;
         }
         
            
         count = -1;
         combinationCalc(count, l, a, n, r);

         
         System.out.print(numOfPaths);
         if(n < 9) {
            System.out.print(", ");
         }

      }
   
   }
   
   public static void combinationCalc(int count, int l, int a, int n, int r) {
   
      count++;
      
      for(int i = 0; i < 2; i++) {
      
         path[count + l - 1 - a] = i;
         
         if(count < (n * l - l + a)) {
            combinationCalc(count, l, a, n, r);
         }
         
         else {

            combinationCheck(n, l, a, r);
            
         }
      
      } 
   
   }
   
   public static void combinationCheck(int n, int l, int a, int r) {
   
      int m = 1 - l;
      
      int height = 0;
      int ups = 0;
      int downs = 0;
      boolean check = true;
         
      for(int i = 0; i < path.length ; i++) {
         
         if(path[i] == 0) {
            
            ups++;
            height++;
            
         }
            
         else {
            
            downs++;
            height += m;
            
            //Include for strong fine paths
            /*if(height == 0 && path[i - 1] == 0) {
               check = false;
               break;
            } */
            
            //Include for weak fine paths
            /*if(height == 0) {
               int tempHeight = height;
               for(int j = 0; j < l - 1; j++) {
                  tempHeight -= path[i - j - 1];
               }
               if(tempHeight == 0) {
                  check = false;
                  break;
               }
            }*/
            
            if(i != 0) {
            
               //Include for no peaks at even heights
               /*if((height - m) % 2 == 0 && path[i - 1] == 0) {
                  check = false;
                  break;
               }*/
            
               //Include for no peaks at odd heights
               /*if((height - m) % 2 == 1 && path[i - 1] == 0) {
                  check = false;
                  break;
               }*/
            
               //Include for no peaks at multiples of l
               /*if((height - m) % l == 0 && path[i - 1] == 0) {
                  check = false;
                  break;
               }*/
               
               //Include for no peaks at 1(mod l)   
               /*if((height - m) % l == 1 && path[i - 1] == 0) {
                  check = false;
                  break;
               }*/
            
               //Include for no peaks at -1(mod l)   
               /*if((height - m) % l == (l - 1) && path[i - 1] == 0) {
                  check = false;
                  break;
               }*/
               //Include for all peaks at multiples of l
               /*if((height - m) % l != 0 && path[i - 1] == 0) {
                  check = false;
                  break;
               }*/
               
               //Include for r-Fine paths             
               if(height == 0) {
                  int t = 1;
                  for(int p = 1; p < r + 1; p++) {
                     if(path[i - p] == 1) {
                        break;
                     }
                     t++;
                  }
                  if(t == r + 1) {
                     check = false;
                  }
               }
            }
            
         }
            
         if(height < -a || height > n * (l - 1)) {
            check = false;
            break;
         }
              
      }
         
      if(ups != n * (l - 1)) {
         check = false;
      }
         
      if(downs != n) {
         check = false;
      }
      
      
      if(check == true) {
         numOfPaths++;
      }
   
   }

}

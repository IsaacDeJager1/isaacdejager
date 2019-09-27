import java.util.*;

public class Customer1 {
   
   //true = regular, false = business
   public boolean cusType;
   public int transactionTime;
   public int waitTime = 0;
   
   public static Random rand = new Random();
   
   public void findCusType(int peakTime) {
      
      cusType = true;
      int odds = rand.nextInt(10);
      if(peakTime == 0) {
         
         if(odds < 2) {
            cusType = false;
         }
         
      }
      
      else {
         
         if(odds < 4) {
            
            cusType = false;
            
         }
         
      }
      
   }
   
   
   public void findTransactionTime() {
      
      transactionTime = 0;
      int numOfTransactions = 0;
      int odds1 = rand.nextInt(10);
      
      if(odds1 < 4) {
         
         numOfTransactions = 1;
      
      }
      
      else if(odds1 < 7) {
         
         numOfTransactions = 2;
         
      }
      
      else if(odds1 < 9) {
      
         numOfTransactions = 3;
         
      }
      
      else {
         
         numOfTransactions = 4;
         
      }
      
      
      if(cusType == true) {
      
         transactionTime = numOfTransactions * 2;
         
      }
      
      else {
         
         transactionTime = numOfTransactions * 3;
         
      }
      
      
      int odds2 = rand.nextInt(5);
      
      if(odds2 == 0) {
         
         transactionTime += 5;
         
      }

   }

}

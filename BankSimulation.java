import java.util.*;
import java.io.*;

public class BankSimluation {
   
   //Declaring public teller arrays to be called by multiple methods
   public static int[] regTellers;
   public static int[] busTellers;
   
   public static int avgRegWait = 0;
   public static int longestRegWait = 0;
   public static int avgBusWait = 0;
   public static int longestBusWait = 0;
   
   public static void main(String[] args) throws IOException {
      
      boolean runAgain = true;
      String userRunAgain = "";
      
      File ofile = new File("outputData.txt");
      FileWriter fw = new FileWriter(ofile);
      
      Scanner input = new Scanner(System.in);
      
      System.out.println("Welcome to This Bank!");
      System.out.println();
      
      int simRun = 1;
      while(runAgain == true) {
         
         System.out.print("Please enter the number of regular tellers: ");
         regTellers = new int[input.nextInt()];
         System.out.print("Please enter the number of business tellers: ");
         busTellers = new int[input.nextInt()];
         System.out.println();
         
         if(simRun != 1) {
            fw.write("\n");
         }
         fw.write("Simulation " + simRun + ":\n\n");
         
         fw.write(regTellers.length + " regular teller");
         if(regTellers.length != 1) {
            fw.write("s");
         }
         fw.write("\n");
         fw.write(busTellers.length + " business teller");
         if(busTellers.length != 1) {
            fw.write("s");
         }
         fw.write("\n\n");

         for(int i = 0; i < 2; i++) {
         
            simulation(i);
            
            fw.write(calcClock((11 * 60 + 30) + i * (5 * 60)) + " - " + calcClock((13 * 60) + i * (5 * 60)));
            if(i == 1) {
               fw.write(" ");
            }
            fw.write("\t");
            fw.write("|\t\tRegular\t\t|\t\tBusiness\t\t\n");
            fw.write("----------------------------------------------------\n");
            fw.write("Average wait: \t|\t\t" + avgRegWait + "\t\t\t\t|\t\t" + avgBusWait + "\n");
            fw.write("Longest wait: \t|\t\t" + longestRegWait + "\t\t\t\t|\t\t" + longestBusWait + "\n\n");
            
         }
         fw.write("====================================================");
         
         System.out.println("==============================");
         
         System.out.println("Simulation finished.");
         System.out.println("Additional data sent to outputData.txt.");
         System.out.println();
         
         System.out.print("Would you like to run another simulation? (Y / N) ");
         while(userRunAgain.equals("Y") == false || userRunAgain.equals("N") == false) {
         
            userRunAgain = input.next();
         
            if(userRunAgain.equals("Y") == true) {
               System.out.println();
               break;
            }
            else if(userRunAgain.equals("N") == true) {
               
               System.out.println();
               System.out.println("Thank you for working with Peppered Fries Software Consulting!");
               System.out.println("Have a nice day!");
               runAgain = false;
               fw.close();
               break;
               
            }
            else {
               System.out.println("Please enter either Y or N");
               System.out.println();
            }
            
         }
         
         simRun++;
            
      }
      
   }
   
   public static void simulation(int peakTime) {
      
      //Creating two lines of customers
      Queue<Customer1> regCus = new LinkedList<Customer1>();
      Queue<Customer1> busCus = new LinkedList<Customer1>();
      
      Random rand = new Random();
      
      //Declaring variables
      int time = 0;
      int odds;
      int numOfCus = 0;
      int totalNumOfCus = 0;
      
      int regWaitTotal = 0;
      int numOfRegCus = 0;
      
      int busWaitTotal = 0;
      int numOfBusCus = 0;
      
      int regTellersServed = 0;
      int busTellersServed = 0;
      
      //This segment finds the time and displays opening output
      int timeSince0 = 0;
      if(peakTime == 0) {
         timeSince0 = 11 * 60 + 30;
      }
      else {
         timeSince0 = 16 * 60 + 30;
      }
      System.out.println("=======================================");
      System.out.println("Starting simluation " + calcClock(timeSince0) + " - " + 
         calcClock(timeSince0 + 90) + ":");
      System.out.println("Time  |  Regular line  |  Business line");
      System.out.println("---------------------------------------");
      
      
      //true = regular, false =  business
      boolean turn = true;
      boolean activeTellers = true;
      
      //Each iteration of this while loop represents one second
      //If the loop has run less that 90 times, or if there are still tellers active, or if there
      //are customers in either line, then this loop runs again
      while(time < 90 || activeTellers == true || regCus.size() != 0 || busCus.size() != 0) {
         
         //Every 30 seconds, display a new line of output
         if((time % 30) == 0) {
            
            System.out.print(calcClock(timeSince0 + time) + "\t" + "|  ");
            System.out.print(regCus.size());
            for(int i = 0; i < 4; i++) {
               System.out.print("\t");
            }
            System.out.print("  |  " + busCus.size());
            for(int i = 0; i < 4; i++) {
               System.out.print("\t");
            }
            System.out.print("  |  " + regTellersServed);
            for(int i = 0; i < 11; i++) {
               System.out.print("\t");
            }
            System.out.println("  |  " + busTellersServed);
         }
         
         //If the simulation has been running for less than 90 seconds, then continue adding customers
         if(time < 90) {
         
            odds = rand.nextInt(100);
            if(odds < 60) {
               numOfCus = 0;
            }
            else if(odds < 80) {
               numOfCus = 1;
            }
            else if(odds < 93) {
               numOfCus = 2;
            }
            else if(odds < 97) {
               numOfCus = 3;
            }
            else {
               numOfCus = 4;
            }
            
            //Creating new customers
            for(int i = 0; i < numOfCus; i++) {
            
               Customer1 cust = new Customer1();
               cust.findCusType(peakTime);
               cust.findTransactionTime();
               
               //Adding customer to appropriate queue
               if(cust.cusType == true) {   
                  regCus.add(cust);
                  numOfRegCus++;
               }
            
               else {
                  busCus.add(cust);
                  numOfBusCus++;
               }
               
               totalNumOfCus++;
         
            }
            
         }
         
         //Looping through the business teller array 
         for(int i = 0; i < busTellers.length; i++) {
            
            if(busTellers[i] == 0 && busCus.size() != 0) {
               
               //If a teller is unactive, it recieves a customer unless the line is empty
               Customer1 c = new Customer1();
               c = busCus.remove();
               busTellers[i] = c.transactionTime;
               busTellersServed++;
               
            }
            
            //Subtracting one from each element in the teller array unless the element is 0
            if(busTellers[i] != 0) {
               busTellers[i]--;
            }
         
         }
         
         //Looping throught the regular teller array
         for(int i = 0; i < regTellers.length; i++) {
            
            if(regTellers[i] == 0 && (regCus.size() != 0 || busCus.size() != 0)) {
            
               Customer1 c = new Customer1();
               
               //Finding which line to remove a customer from
               if((turn == true && regCus.size() != 0) || (turn == false && busCus.size() == 0)) {
                  
                  c = regCus.remove();
                  turn = false;
                  regWaitTotal += c.waitTime;
                  
                  if(longestRegWait < c.waitTime) {
                     longestRegWait = c.waitTime;
                  }
                  
               }
               
               else {
                  
                  c = busCus.remove();
                  turn = true;
                  busWaitTotal += c.waitTime;
                  
                  if(longestBusWait < c.waitTime) {
                     longestBusWait = c.waitTime;
                  }
                  
               }

               regTellers[i] = c.transactionTime;
               regTellersServed++;
               
            }
            
            if(regTellers[i] != 0) {
               regTellers[i]--;
            }
            
         }
       
         //Looping through the lines and adding 1 to the wait time of all customers in line
         for(int i = 0; i < busCus.size(); i++) {
            
            Customer1 c = new Customer1();
            
            //Removing the top element of the queue, changing it, and putting it at the bottom
            c = busCus.remove();
            c.waitTime++;
            busCus.add(c);
            
         }
         
         for(int i = 0; i < regCus.size(); i++) {  
            
            Customer1 c = new Customer1();
            c = regCus.remove();
            c.waitTime++;
            regCus.add(c);
            
         }
         
         //Assume the tellers are not active. Looping through both arrays. If there is an element that
         //is not zero, then the tellers are active
         activeTellers = false;
         for(int i = 0; i < busTellers.length; i++) {
            if(busTellers[i] != 0) {
               activeTellers = true;
               break;
            }
         }
         for(int i = 0; i < regTellers.length; i++) {
            if(regTellers[i] != 0) {
               activeTellers = true;
               break;
            }
         }
               
    
         time++;
         
         //If this while loop has run 300 times, display the message and end the while loop
         if(time == 300) {
            System.out.println("Overflowed into next period or the bank has closed.");
            break;
         }
         
         //To represent real time, this while loop runs once every second(1000 milliseconds)
         try {
            Thread.sleep(1000);
         }
         catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
         }
      
      }
      
      //Calculating output
      avgRegWait = regWaitTotal / numOfRegCus;
      avgBusWait = busWaitTotal / numOfBusCus;
      
      //Displaying output of one peak time of a simulation
      System.out.println("Total number of customers: " + totalNumOfCus);
      System.out.println("Time finished serving: " + calcClock(timeSince0 + time));
      System.out.println("======================================================================================================================");
      System.out.println();

   }
   
   
   //This method takes in the number of minutes since time 00:00 and returns a string that states
   //the current time
   public static String calcClock(int time) {
      
      String clock;
      String clock0;
      int clock0int;
      String clock1;
      int clock1int;
      
      clock0int = (time / 60) % 12;
      if(clock0int == 0) {
         clock0int = 12;
      }
      
      if(clock0int == 0) {
         clock0 = String.format("%02d", clock0int);
      }
      else {
         clock0 = Integer.toString(clock0int);
      }
      
      clock1int = time % 60;
      clock1 = String.format("%02d", clock1int);
      
      clock = clock0 + ":" + clock1;
      
      return clock;
      
   }
   
}

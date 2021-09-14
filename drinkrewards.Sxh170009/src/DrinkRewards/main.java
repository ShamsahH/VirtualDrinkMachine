//Shamsah Hossain
//SXH170009

package DrinkRewards;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class main {


	public static void main(String[] args) throws FileNotFoundException, IOException 
	
	{ 	
	
	final int small = 12;
	final int medium = 20; 
	final int large = 32; 
	final float soda = .20f; 
	final float tea = .12f;
	final float fruitPunch = .15f;
	float totPrice = 0;
	
	
	//create object arrays 
	Customer[] customerData = readCustomer();
    Customer[] platinumData = readPrefered();
    
    orders[] orders = getOrders(customerData,platinumData);

    Customer[][] updatedData;
    
    
    for (int i = 0; i < orders.length; i++)
    {
        //Check for type of drink
        
      //Check for type of drink
        if (orders[i].getType().equals("tea"))
        {
            //Check for size of drink
            if (orders[i].getSize() == 's' || orders[i].getSize() == 'S')
            {
                //Calculate price
                totPrice = small * orders[i].getPrice() + (12 * tea);
                totPrice *= orders[i].getQuantity();
            }//End if
          
            //Check for size of drink
            else if (orders[i].getSize() == 'm' || orders[i].getSize() == 'M')
            {
                //Calculate price
                totPrice = medium * orders[i].getPrice() + (20 * tea);
                totPrice *= orders[i].getQuantity();
            }//End else if
          
            //Check for size of drink
            else if (orders[i].getSize() == 'l' || orders[i].getSize() == 'L')
            {
                //Calculate price
                totPrice = large * orders[i].getPrice() + (32 * tea);
                totPrice *= orders[i].getQuantity();
            }//End else if
        } //End else if
        else if (orders[i].getType().equals("soda"))
        {
            //Check for size of drink
            if (orders[i].getSize() == 'S' || orders[i].getSize() == 's')
            {
                //Calculate the price
                totPrice = small * orders[i].getPrice() + (12 * soda);
                totPrice *= orders[i].getQuantity();
            }
          
            //Check for size of drink
            else if (orders[i].getSize() == 'M' || orders[i].getSize() == 'm')
            {
                //Calculate the price
                totPrice = medium * orders[i].getPrice() + (20 * soda);
                totPrice *= orders[i].getQuantity();
            }//End else if
          
            //Check for size of drink
            else if (orders[i].getSize() == 'L' || orders[i].getSize() == 'l')
            {
                totPrice = large * orders[i].getPrice() + (32 * soda);
                totPrice *= orders[i].getQuantity();
            }
        }
        
        else
        {
        	if(orders[i].getType().equals("fruit Punch"))
        		if (orders[i].getSize() == 'S' || orders[i].getSize() == 's')
                {
                    //Calculate price
                    totPrice = small * orders[i].getPrice() + (12 * fruitPunch);
                    totPrice *= orders[i].getQuantity();
                }//End if
              
                //Check for size of drink
                else if (orders[i].getSize() == 'M' || orders[i].getSize() == 'm')
                {
                    //Calculate price
                    totPrice = medium * orders[i].getPrice() + (20 * fruitPunch);
                    totPrice *= orders[i].getQuantity();
                }//End else if
              
                //Check for size of drink
                else if (orders[i].getSize() == 'L' || orders[i].getSize() == 'l')
                {
                    //Calculate price
                    totPrice = large * orders[i].getPrice() + (32 * fruitPunch);
                    totPrice *= orders[i].getQuantity();
                }//End else if
        		
        }
        
       
        int currentCustomer = existingCustomer(customerData, orders[i].getGuestID());
        
        if(currentCustomer != -1)
        {
        	totPrice -= platinumData[currentCustomer].getPercentDiscount();               
            platinumData[currentCustomer].setDiscountPercent((int) ((int) totPrice / 5));
            platinumData[currentCustomer].purchase(totPrice);
        }
        else 
        {
        	currentCustomer = existingCustomer(platinumData, orders[i].getGuestID());
            //If the customer is gold apply the discount rate
            if (platinumData[currentCustomer].getStatus().equals("gold"))
            {
                totPrice -= (totPrice * platinumData[currentCustomer].getPercentDiscount());
                platinumData[currentCustomer].purchase(totPrice);
              
                //Check to see if they are eligible for platinum status
                if (platinumData[currentCustomer].getAmountSpent() >= 200)
                {
                    platinumData = toPlatinum(currentCustomer, platinumData);
                }
              
                //Check to see if their discount increases
                else if (platinumData[currentCustomer].getAmountSpent() >= 150f)
                {
                    platinumData[currentCustomer].setDiscountPercent(.15f);
                }
                      
                else if (platinumData[currentCustomer].getAmountSpent() >= 100f)
                {
               
                	platinumData[currentCustomer].setDiscountPercent(.10f);
                }
        
            else
            {
                float Upgrade = totPrice + customerData[currentCustomer].getAmountSpent();
                customerData[currentCustomer].purchase(totPrice);
                
                if(customerData[currentCustomer].getAmountSpent() - (totPrice * .15) >= 150)
                {
                    customerData[currentCustomer].purchase(-1 * totPrice);
                    totPrice -= totPrice * .15;
                    customerData[currentCustomer].purchase(totPrice);
                    updatedData = toGold(currentCustomer, customerData, platinumData, .15f);
                    customerData = updatedData[0];
                    platinumData = updatedData[1];
                }//End promote customer to gold with 15%
                
                else if (customerData[currentCustomer].getAmountSpent() >= 200)
                {
                    customerData[currentCustomer].purchase(totPrice);
                    updatedData = regularToPlatinum (currentCustomer, customerData, platinumData);
                    customerData = updatedData[0];
                    platinumData = updatedData[1];
                }
              
                   
                else if (customerData[currentCustomer].getAmountSpent() - (totPrice * .05) >= 50)
                {
                    customerData[currentCustomer].purchase(-1 * totPrice);
                    totPrice -= totPrice * .05;
                    customerData[currentCustomer].purchase(totPrice);
                    updatedData = toGold(currentCustomer, customerData, platinumData, .05f);
                    customerData = updatedData[0];
                    platinumData = updatedData[1];
                }
                

                else if (customerData[currentCustomer].getAmountSpent() - (totPrice * .10) >= 100)
                {
                    customerData[currentCustomer].purchase(-1 * totPrice);
                    totPrice -= totPrice * .10;
                    customerData[currentCustomer].purchase(totPrice);
                    updatedData = toGold(currentCustomer, customerData, platinumData, .10f);
                    customerData = updatedData[0];
                    platinumData = updatedData[1];
                }//End promote customer to gold with 10%
              
                else
                {
                    customerData[currentCustomer].purchase(totPrice);
                }
            }
        }
       
            writeIntoNewFile(platinumData, customerData);
      
    }//End main
              
}
}

	
	//method that writes customer to gold file 
	// data type of class Customer
	
	
	public static Customer[][] toGold (int index, Customer[]customers, Customer[]platinum, float rate)
    {
        
        Customer [] newPlatinum = new Customer [(platinum.length + 1)];
        Customer [] newCustomer = new Customer [(customers.length - 1)];
       
       
        for (int i = 0; i < newCustomer.length; i++)
        {
            if (i < index)
            {
                newCustomer[i] = customers[i];
            }//End if
            else if (i >= index)
            {
                newCustomer[i] = customers[(i + 1)];
            }//End else if
        }//End for
        for (int i = 0; i < newPlatinum.length; i++)
        {
            if (i == (platinum.length))
            	newPlatinum [i] = new goldCustomer(customers[index].getFirstName(), customers[index].getLastName(), customers[index].getGuestID(), customers[index].getAmountSpent(), rate);
            else
            	newPlatinum[i] = platinum[i];
        }
        
        Customer[][] temp = {newCustomer, newPlatinum};
        return temp;
    }
	
	//method that writes to files according to amount spent
	//void return type 
	
	 public static void writeIntoNewFile(Customer[] platinum, Customer[] customers) throws FileNotFoundException
     {
         PrintWriter output = new PrintWriter(new File("preferred.dat"));
         BufferedWriter bw = null;
         
         for (int i = 0; i < platinum.length; i++)
         {
             if (platinum[i].getStatus().equals("gold"))
             {
                 if (platinum[i].getPercentDiscount() == .05f)
                 {
                      output.println(platinum[i].getGuestID() + " " + platinum[i].getFirstName() + " " + platinum[i].getLastName() + " " + platinum[i].getAmountSpent() + " 5%");
                 }//End 5%
               
                 else if (platinum[i].getPercentDiscount() == .10f)
                 {
                      output.println(platinum[i].getGuestID() + " " + platinum[i].getFirstName() + " " + platinum[i].getLastName() + " " + platinum[i].getAmountSpent() + " 10%");
                 }//End 10%
               
                 else if (platinum[i].getPercentDiscount() == .15f)
                 {
                      output.println(platinum[i].getGuestID() + " " + platinum[i].getFirstName() + " " + platinum[i].getLastName() + " " + platinum[i].getAmountSpent() + " 15%");
                 }//End 15%
             }
             else
                 output.println(platinum[i].getGuestID() + " " + platinum[i].getFirstName() + " " + platinum[i].getLastName() + " " + platinum[i].getAmountSpent() + " " + platinum[i].getPercentDiscount());
         }//End for
         output.close();
         
         //System.out.println(" something wrong"); debug
         
         output = new PrintWriter(new File("customers.dat"));
         
         for (int i = 0; i < customers.length; i++)
         {
             output.println(customers[i].getGuestID() + " " + customers[i].getFirstName() + " " + customers[i].getLastName() + " " + customers[i].getAmountSpent());
         }
         output.close();
     }
	 
	 private static int getDiscount(double totalSpent)
	   {
	       //Calculate the discount using if-else
	       if(totalSpent >= 150 && totalSpent < 200)
	           return 5;
	       else if(totalSpent >= 200 && totalSpent < 350)
	           return 7;
	       else if(totalSpent >= 350)
	           return 10;
	       return 0;
	   }
	   
	//EnterDataIntoPreferredCustomersFile() method to enter the data in the preferred.txt file.
	   static void DataToPreferredCustomersFile(String[] currentData, File PreferredFile) throws IOException
	   {
	       PrintWriter pw=new PrintWriter(new FileWriter(PreferredFile,true));
	       pw.println(currentData[0]+" "+currentData[1]+" "+currentData[2]+" "+ currentData[3]+" "+currentData[4]);
	       pw.close();
	   }
	  
	   //CurrentDataExistsInPreferredCustomersFile() method to check if the data already exists in preferred.txt
	   static boolean CurrentDataExistsInPreferredCustomersFile(int cust, File preferredFile) throws IOException
	   {
	       //Read the file
	       BufferedReader br = new BufferedReader(new FileReader(preferredFile));
	       String line;
	      
	       //Check if the data exists.
	       while((line = br.readLine()) != null)
	       {
	           String[] custId = line.split(" +");
	           if(cust == Integer.parseInt(custId[0]))
	               return true;        
	       }
	       return false;
	   }
	   
	 public static orders[] getOrders(Customer[] customers, Customer[] platinum) throws FileNotFoundException
     {
        File Orders = new File("orders.dat");
        String [] parse;
        String [] ordersData = new String [60];        
        Scanner sc = new Scanner(Orders);
        int amountOrders = 0;
        try
		{
		   if(!Orders.exists())
		       Orders.createNewFile();
		}
		catch(Exception e)
			{
				e.printStackTrace();
			}
    try {
        while (sc.hasNext())
        {
            ordersData [amountOrders] = sc.nextLine();
            parse = ordersData[amountOrders].split(" +");
           
            if (parse.length == 5)
            {
                //Make sure data matches an existing customer
                if (((existingCustomer(customers, parse[0]) != -1) || (existingCustomer(platinum, parse[0]) != -1)))
                {
                    //Make sure data contains a valid size
                    if (parse[1].toUpperCase().equals("M") || parse[1].toUpperCase().equals("S") || parse[1].toUpperCase().equals("L"))
                    {
                        //Make sure data contains a valid type
                        if (parse[2].equals("soda") || parse[2].equals("tea") || parse[2].equals("fruit punch"))
                        {
                             boolean isNum = true;
                             char[] container = parse[3].toCharArray();
                        
                             for (int i = 0; i < container.length; i++)
                             {
                                 //Make sure data contains a valid price per square inch
                                 if (container[i] == 47 || container[i] > 57 || container[i] < 46)
                                 {
                                     isNum = false;
                                 }
                             }
                           
                             if (isNum == true)
                             {
                                 container = parse[4].toCharArray();
                                 for (int i = 0; i < container.length; i++)
                                 {
                                     
                                     if (container[i] > 57 || container[i] < 48)
                                     {
                                         isNum = false;
                                     }//End if
                                 }//End for
                               
                                 if (isNum == true)
                                 {
                                     amountOrders++;
                                 }
                             }//End if
                        }//End if
                    }//End if
                }//End if
            }//End if
        }//End while
    }
    catch(Exception e)
{
	e.printStackTrace();	}
    
    
        orders[] orders = new orders[amountOrders];
        
        for (int i = 0; i < orders.length; i++)
        {
            parse = ordersData[i].split(" +");
            orders[i] = new orders(parse[0], parse[1].charAt(0), parse[2], Float.parseFloat(parse[3]), Integer.parseInt(parse[4]));
        }//End for
        sc.close();
        return orders;
     }//End get orders   
	 
     public static Customer[] toPlatinum (int index, Customer[] platinum)
     {
         int bonusBucks;
         bonusBucks = (int) (( (int) platinum[index].getAmountSpent() - 200) / 5);
         platinum[index] = new platinumCustomer(platinum[index].getFirstName(), platinum[index].getLastName(), platinum[index].getGuestID(), platinum[index].getAmountSpent(), bonusBucks);
         
         return platinum;
         
     }
   
     
     public static int existingCustomer(Customer[] customerColl, String guestID)
     {
       int index = -1;
       for(int i = 0; i < customerColl.length; i++)
       {
          if (customerColl[i].getGuestID() == guestID)
          {
              index = i;
              break;
          }
       }
       return index;
     }
     
     public static Customer[][] regularToPlatinum (int index, Customer[] customers , Customer[] platinum)
     {
         Customer[] newCustomers = new Customer [(customers.length - 1)];
         Customer[] newPlatinum = new Customer [(platinum.length + 1)];
         for (int i = 0; i < newCustomers.length; i++)
         {
             if (i < index)
             {
                 newCustomers[i] = customers[i];
             }
             else if (i >= index)
             {
                 newCustomers[i] = customers[(i + 1)];
             }
        }
       
         for (int i = 0; i < newPlatinum.length; i++)
         {
            if (i == (platinum.length))
            	 
                 newPlatinum [i] = new platinumCustomer(customers[index].getFirstName(), customers[index].getLastName(), customers[index].getGuestID(), customers[index].getAmountSpent(), 5);
             else
            	 
                 newPlatinum[i] = platinum[i];
         }
         
         newPlatinum[platinum.length].setDiscountPercent(0f);
         
         newPlatinum[platinum.length].amountSpentSetter(newPlatinum[platinum.length].getAmountSpent() - newPlatinum[platinum.length].getPercentDiscount());
         
         newPlatinum[platinum.length].setDiscountPercent((int) ((int) (customers[index].getAmountSpent() - 200) / 5));
         
         Customer[][] temp = {newCustomers, newPlatinum};
         return temp;
         
     }
     
     public static Customer[] readCustomer() throws FileNotFoundException,IOException
     {
         Scanner read = new Scanner(new File("customers.dat"));
         BufferedReader br = new BufferedReader(new FileReader("customers.dat"));
	       
         String [] container = new String[20];
         int amountCustomers = 0;
         
         while (read.hasNext())
         {
             container[amountCustomers] = read.nextLine();
             amountCustomers++;
         }
         
         Customer [] regularList = new Customer[amountCustomers];
         String [] parse;
        
         for (int i = 0; i < amountCustomers; i++)
         {
             parse = container[i].split(" +");
             regularList [i] = new Customer(parse[1], parse[2], parse[0], Float.parseFloat(parse[3]));
         }
         
         read.close();
         br.close();
         
         return regularList;
     }
     static boolean CurrentDataExistsInCustomersFile(int cust, File customersFile) throws IOException
	   {
	      //Read the customers.txt file.
	      BufferedReader br = new BufferedReader(new FileReader(customersFile));
	      String line;
	     
	      //Check if the data exists.
	      while((line = br.readLine()) != null)
	      {
	          String[] customerId = line.split(" +");
	          if(cust == Integer.parseInt(customerId[0]))
	              return true;        
	      }
	      br.close();
	      return false;
	   }
     
     private static void AddToPreferred(String[] currentData, File customersFile, File PreferredFile) throws IOException
	   {
	       //Read the customers.txt file
	       BufferedReader br = new BufferedReader(new FileReader(customersFile));
	       
	       ArrayList<Customer> customers = new ArrayList<Customer>();
	       
	       String input;
	      
	       //Read the files by line
	       while((input = br.readLine()) != null)
	       {
	           String[] fileData = input.split(" +");
	           if(Integer.parseInt(currentData[0]) == Integer.parseInt(fileData[0]))
	               continue;
	  
	       }
	      
	       //Close the file
	       br.close();
	       customersFile.delete();
	  
	       //Write into the customers.txt file
	       PrintWriter pw = new PrintWriter(new FileWriter(customersFile));
	      
	           for(int i=0; i < customers.size();i++)
	           {
	               pw.println(customers.get(i).guestID+" "+customers.get(i).firstName+" "+customers.get(i).lastName+" "+customers.get(i).amountSpent);
	           }
	           pw.close();
	      
	       //Call the method recursively.
	   }
	   
   
     
     public static Customer[] readPrefered() throws FileNotFoundException,IOException
     {
         Scanner read = new Scanner(new File("preferred.dat"));
         BufferedReader br = new BufferedReader(new FileReader("preferred.dat"));
	       
         String [] container = new String[20];
         int totalCustomers = 0;
        
         while (read.hasNext())
         {
             container[totalCustomers] = read.nextLine();
             totalCustomers++;
         }
         
         String [] parse;

         Customer [] preferred = new Customer[totalCustomers];
       
         for (int i = 0; i < totalCustomers; i++)
         {
             parse = container[i].split(" ");
             int length = parse[4].length();
             length -= 1;
             
             if (parse[4].charAt(length) == '%')
             {
                 parse[4] = parse[4].replace("%","");
                 if (Float.parseFloat(parse[4]) == 15.0f)
                 {
                	 preferred[i] = new goldCustomer(parse[1], parse[2], parse[0], Float.parseFloat(parse[3]), .15f);
                 }
               
                 else if (Float.parseFloat(parse[4]) == 10.0f)
                 {
                	 preferred[i] = new goldCustomer(parse[1], parse[2], parse[0], Float.parseFloat(parse[3]), .10f);
                 }
               
                 else if (Float.parseFloat(parse[4]) == 5.0f)
                 {
                	 preferred[i] = new goldCustomer(parse[1], parse[2], parse[0], Float.parseFloat(parse[3]), .05f);
                 }
               
                 
                 else
                	 preferred[i] = new goldCustomer(parse[1], parse[2], parse[0], Float.parseFloat(parse[3]), Float.parseFloat(parse[4]));
               
             }
           
             else
             {
                 parse[4] = parse[4].replace("$","");
                 preferred[i] = new platinumCustomer(parse[1], parse[2], parse[0], Float.parseFloat(parse[3]), Integer.parseInt(parse[4]));
             }
           
           
         }
         read.close();
         br.close();
         return preferred;
     }
     private static void storingCustomerOrdersData(String[] orders, File customersFile) throws IOException
   		{
   		   //Write in the file.
   		   PrintWriter pw=new PrintWriter(new FileWriter(customersFile,true));
   		   
   		   pw.println(orders[0]+" "+orders[1]+" "+orders[2]+" "+orders[3]+" "+orders[4]+" "+orders[5]+" "+orders[6]+" ");
   		   
   		   pw.close();
   		}
     //......
}

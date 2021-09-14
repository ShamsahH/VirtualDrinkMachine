//Shamsah Hossain
//SXH170009




package DrinkRewards;

public class goldCustomer extends Customer
{

   //Member variable discount
   float percentDiscount;

   //Default constructor
   public goldCustomer()
   {
       super();
  
   }

   //Parameterized overloaded constructor
   public goldCustomer(String firstName, String lastName, String guestID, float amountSpent,float percentDiscount)
   {
       super(firstName, lastName, guestID, amountSpent);
       this.percentDiscount=percentDiscount;
   }
   
   public String getStatus()
   {
       String status = "gold";
       return status;
   }

   //Getter and setter
   public float getPercentDiscount()
   {
       return percentDiscount;
   }

   public void setDiscountPercentage(float percentDiscount)
   {
      this.percentDiscount = percentDiscount;
   }
}

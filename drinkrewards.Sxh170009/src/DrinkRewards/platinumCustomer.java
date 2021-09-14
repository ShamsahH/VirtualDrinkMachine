//Shamsah Hossain
//SXH170009


package DrinkRewards;


public class platinumCustomer extends Customer {
	
	//member variable bonus bucks
	
	float bonusBucks; 
	
	//default constructor
	
	public platinumCustomer ()
	{
		super();
	}
	
	public platinumCustomer(String firstName, String lastName, String guestID, float amountSpent,int percentDiscount)
	   {
	       super(firstName, lastName, guestID, amountSpent);
	       this.bonusBucks = bonusBucks;
	   }

	 public float getPercentDiscount()
	   {
	       return bonusBucks;
	   }

	   public void setPercentDiscount(double percentDiscount)
	   {
		   this.bonusBucks = bonusBucks;
	   }
	 

}

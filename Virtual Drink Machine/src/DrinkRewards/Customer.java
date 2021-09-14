//Shamsah Hossain
//SXH170009


package DrinkRewards;

public class Customer {
		
	//member variables
	String firstName;
	String lastName;
	String guestID;
	float amountSpent; 
	
	//default constructor 

	public Customer()
	{
		
	}
	
	
	//overloaded constructor 
	public Customer (String firstName, String lastName, String guestID, float amountSpent)
	{
		this.firstName= firstName;
		this.lastName= lastName;
		this.guestID=  guestID;
		this.amountSpent= amountSpent;
	}
	
	//mutators
	public void firstNameSetter()
	{
		this.firstName = firstName;
	}
	public void lastNameSetter()
	{
		this.lastName = lastName; 
	}
	
	public void guestIDSetter()
	{
		this.guestID = guestID;
	}
	public void amountSpentSetter(float amountSpent)
	{
		this.amountSpent = amountSpent;
	}
	//Accessors
	
	public String getFirstName()
	{
		return firstName; 
	}
	
	public String getLastName()
	{
		return lastName;
	}
	public String getGuestID()
	{
		return guestID;
	}
	public float getAmountSpent()
	{
		return amountSpent; 
	}
	public void purchase(float cost)
    {
        amountSpent += cost;
    }
	public String getStatus()
    {
        String status = "regular";
        return status;
    }  
	
	public void setDiscountPercent(float benefitIn)
	{}
	
	public float getPercentDiscount()
	   {
	       return amountSpent;
	   }


	
	
	
}


//Shamsah Hossain
//SXH170009




package DrinkRewards;

public class orders
{
	String guestID;
	char size; 
	String type; 
	float price; 
	int quantity; 


	//default 
	
	orders()
	{
		
	}
	
	orders(String GuestID, char size, String type, float price, int quantity)
	{
		this.guestID = guestID;
		this.size = size; 
		this.type = type;
		this.price = price;
		this.quantity = quantity; 
	}
	
	public String getGuestID()
    {
        return guestID;
    }
  
    public char getSize()
    {
        return size;
    }
  
    public String getType()
    {
        return type;
    }  
    public float getPrice()
    {
        return price;
    }  
    public int getQuantity()
    {
        return quantity;
    }	
}

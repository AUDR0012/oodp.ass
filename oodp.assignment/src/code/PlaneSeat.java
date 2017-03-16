package code;

public class PlaneSeat {

	private int seatId;
	private boolean assigned;
	private int customerId;
	
	//PlaneSeat() - is the constructor for the class.
	public PlaneSeat(int s)
	{
		seatId = s;
		customerId = 0;
		assigned = false;
	}
	
	//getSeatID() – a get method that returns the seat number.
	public int getSeatID()
	{
		return seatId;
	}
	
	//getCustomerID() – a get method that returns the customer number.
	public int getCustomerID()
	{
		return customerId;
	}
	
	//isOccupied() – a method that returns a boolean on whether the seat is occupied.
	public boolean isOccupied()
	{
		return assigned;
	}
	
	//assign() – a method that assigns a seat to a customer.
	public void assign(int cust_id)
	{
		customerId = cust_id;
		assigned = true;
	}
	
	//unAssign() – a method that unassigns a seat.
	public void unAssign()
	{
		customerId = 0;
		assigned = false;
	}

}

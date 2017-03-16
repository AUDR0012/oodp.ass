package code;

public class Plane {
	
	private PlaneSeat[] seat; //seat – instance variable containing information on the seats in the plane. It is declared as an array of 12 seat objects.
	private int numEmptySeat; //numEmptySeat – instance variable containing information on the number of empty seats.
	
	//Plane() – a constructor for the class Plane.
	public Plane()
	{
		seat = new PlaneSeat[12];
		for (int i = 0; i < 12; i++)
		{
			seat[i] = new PlaneSeat(i+1);
		}
		
		numEmptySeat = 12;
	}
	
	//sortSeats() – a method to sort the seats according to ascending order of customerID. A copy of the original seat array is used for sorting instead of the original.
	private PlaneSeat[] sortSeats()
	{
		PlaneSeat[] temp_seat = new PlaneSeat[12];
		for (int i = 0; i < 12; i++)
		{
			temp_seat[i] = seat[i];
		}
		
		PlaneSeat seat;
		int pos;
		for (int i = 0; i < 12; i++)
		{
			pos = i;
			for (int j = i; j < 12; j++)
			{
				if (temp_seat[j].getCustomerID() < temp_seat[pos].getCustomerID())
				{
					pos = j;
				}
			}
			seat = temp_seat[i];
			temp_seat[i] = temp_seat[pos];
			temp_seat[pos] = seat;
		}
		
		return temp_seat;
	}
	
	//showNumEmptySeats() – a method to display the number of empty seats.
	public void showNumEmptySeats()
	{
		System.out.println("There are " + numEmptySeat + " empty seats.");
	}
	
	//showEmptySeats() – a method to display the list of empty seats.
	public void showEmptySeats()
	{
		System.out.println("The following seats are empty:");
		for (int i = 0; i < 12; i++)
		{
			if (!seat[i].isOccupied())
			{
				System.out.println("SeatID " + seat[i].getSeatID());
			}
		}
	}
	
	//showAssignedSeat() – a method to display the assigned seats with seat ID and customer ID. If bySeatId is true, the order will be by seatID, else order is by customerID.
	public void showAssignedSeats(boolean bySeatId)
	{
		PlaneSeat[] temp_seat;
		if (bySeatId)
		{
			temp_seat = seat;
		}
		else
		{
			temp_seat = sortSeats();
		}
		
		System.out.println("The seat assignments are as follow:");
		for (int i = 0; i < 12; i++)
		{
			if (temp_seat[i].isOccupied())
			{
				System.out.println("SeatID " + temp_seat[i].getSeatID() + " assigned to CustomerID " + temp_seat[i].getCustomerID() + ".");
			}
		}
	}
	
	//assignSeat() – a method that assigns a customer ID to an empty seat.
	public void assignSeat(int seatId, int cust_id)
	{
		for (int i = 0; i < 12; i++)
		{
			if (seat[i].getSeatID() == seatId)
			{
				if (seat[i].isOccupied())
				{
					System.out.println("Seat already assigned to a customer.");
				}
				else
				{
					seat[i].assign(cust_id);
					numEmptySeat--;
					System.out.println("Seat Assigned!");
				}
			}
		}
	}
	
	//unAssignSeat() – a method that unassigns a seat.
	public void unAssignSeat(int seatId)
	{
		for (int i = 0; i < 12; i++)
		{
			if (seat[i].getSeatID() == seatId)
			{
				if (seat[i].isOccupied())
				{
					seat[i].unAssign();
					numEmptySeat++;
				}
				System.out.println("Seat Unassigned!");
			}
		}
	}

}

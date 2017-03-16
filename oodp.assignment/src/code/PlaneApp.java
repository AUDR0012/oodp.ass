package code;
import java.util.Scanner;

public class PlaneApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Plane plane = new Plane();
		int choice, seat_id, cust_id;
		Scanner in = new Scanner(System.in);
		
		do {
		System.out.println("(1) Show number of empty seats");
		System.out.println("(2) Show the list of empty seats");
		System.out.println("(3) Show the list of seat assignments by seat ID");
		System.out.println("(4) Show the list of seat assignments by customer ID");
		System.out.println("(5) Assign a customer to a seat");
		System.out.println("(6) Remove a seat assignment");
		System.out.println("(7) Exit Enter the number of your choice:");

		System.out.println();
		System.out.print("Enter the number of your choice: ");
		choice = in.nextInt();
		
		switch(choice)
		{
		case 1: 
			plane.showNumEmptySeats();
			break;
		case 2: 
			plane.showEmptySeats();
			break;
		case 3: 
			plane.showAssignedSeats(true);
			break;
		case 4: 
			plane.showAssignedSeats(false);
			break;
		case 5: 
			System.out.println("Assigning Seat ..");
			System.out.print("Please enter SeatID: ");
			seat_id = in.nextInt();
			System.out.print("Please enter Customer ID: ");
			cust_id = in.nextInt();
			plane.assignSeat(seat_id, cust_id);
			break;
		case 6: 
			System.out.print("Enter SeatID to unassign customer from: ");
			seat_id = in.nextInt();
			plane.unAssignSeat(seat_id);
			break;
		case 7: 
			break;
		}
		
		System.out.println();
		} while (choice != 7);
	}

}

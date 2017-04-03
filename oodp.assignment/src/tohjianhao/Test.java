package tohjianhao;

import java.util.Scanner;

public class Test 
{
	public static void main(String[] args)
	{
		//Notifier.sendNotification("TOHJ0032@e.ntu.edu.sg", "CZ2002", 12001, Notifier.MessageType.REGISTRATION_NOTIFICATION);		
		Verifier verifier = new Verifier();
		String email;
		String number;
		Scanner sc = new Scanner(System.in);
		
		do
		{
			//email = sc.nextLine();
			//System.out.println(verifier.validateEmail(email));
			
			number =sc.nextLine();
			System.out.println(verifier.validatePhoneNumber(number));
		}
		while(!(number.equals("NULL")));
	}
}

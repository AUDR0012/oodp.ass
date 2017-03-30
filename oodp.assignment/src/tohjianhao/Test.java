package tohjianhao;

public class Test 
{
	public static void main(String[] args)
	{
		//Notifier.sendNotification("TOHJ0032@e.ntu.edu.sg", "CZ2002", 12001, Notifier.MessageType.WAITLIST_NOTIFICATION);		
		Verifier verifier = new Verifier();
		System.out.println(verifier.validate("TOHJ0032@e.ntu.edu.sg"));
	}
}

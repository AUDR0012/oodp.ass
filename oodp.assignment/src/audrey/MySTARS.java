package audrey;

import java.util.Scanner;
import java.io.Console;
import java.util.Arrays;
import java.lang.String;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//java -Xmx1024m -jar run.jar
public class MySTARS {

	static Scanner in = new Scanner(System.in);
	Student[] student_list = null;
	Course[] course_list = null;
	
	public static void main(String[] args)
	{
		boolean is_student = true;

		/** Read Password **/
		System.out.print("Enter Password: ");
		Console console = System.console();
		char[] input = console.readPassword();

		/** Compare Password **/
		String pass = "audreyho";
		char[] p_Ar = pass.toCharArray();
		if (Arrays.equals(input, p_Ar))
			System.out.println("abc");
		else
			System.out.println("def");
		System.out.println(input);

		/** Hash Password **/
		System.out.println(input.toString().hashCode());
		System.out.println(pass.hashCode());
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String i_Ha = String.copyValueOf(input);
			md.update(i_Ha.getBytes(), 0, i_Ha.length());
			System.out.println(new BigInteger(1, md.digest()).toString());
		} catch (NoSuchAlgorithmException x) {
			System.out.println(x);
		}
		
		/** Main **/
		
		if (is_student)
		{
			System.out.println("===== Student Menu =====");
			System.out.println("1. Add Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check/ Print Courses Registered");
			System.out.println("4. Check Vacancies");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swop Index Number with Another Student");
			System.out.println("0. Exit");
		}
		else
		{
			System.out.println("===== Admin Menu =====");
			System.out.println("1. Edit Student Access Period");
			System.out.println("2. Add a Student");
			System.out.println("3. Add/ Update a Course");
			System.out.println("4. Check Available Slot for an Index Number");
			System.out.println("5. Print Student List by Index Number");
			System.out.println("6. Print Student List of Course");
			System.out.println("0. Exit");
		}
	}
	
	public void check_student()
	{
		//Scanner in = new Scanner(System.in);
		Console console = System.console();
		int pos;
		
		String username, password;
		System.out.print("Enter Username: ");
		username = in.nextLine();
		System.out.print("Enter Password: ");
		//password = String.copyValueOf(console.readPassword());
		password = in.nextLine();
		
		for (pos = 0; pos < student_list.length; pos++)
		{
			
			
		}
	}
}

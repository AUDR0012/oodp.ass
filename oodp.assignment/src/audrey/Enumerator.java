package audrey;

import java.util.Scanner;

public class Enumerator {

	enum Alternate_Week { // Does Session occurs on Alternate Weeks
		ODD, EVEN, NONE;
	}

	enum Course_Type { // Course Type
		CORE, CORE_ELECTIVE, GER_CORE, GER_ELECTIVE, UNRESTRICTED_ELECTIVE;
	}

	enum Day { // Day of the Week
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
	}

	enum Gender { // Gender of the Student
		MALE, FEMALE;
	}

	enum Group_Status { // Student's Status in the Group
		REGISTERED, WAITLIST, EXEMPTED, NOT_FOUND;
	}

	enum Notification_Status { // How the student wish to be notified
		SMS, EMAIL
	}

	enum Session_Type { // Type of Session
		LECTURE, TUTORIAL, LAB;
	}

	private static int pos = 1;

	/*
	 * Enumerator.printAll(Course_Type.class);
	 * System.out.println(Enumerator.nextEnum(Course_Type.class, in.nextInt()));
	 * System.out.println(Course_Type.CORE_ELECTIVE.toString());
	 */

	public static <E extends Enum<E>> E nextEnum(Class<E> obj, Scanner in)
	{
		int value;
		do
		{
			value = in.nextInt();
			for (E constant : obj.getEnumConstants())
			{
				if ((value - pos) == constant.ordinal())
				{
					return constant;
				}
			}
			
			System.out.println("Please enter a valid input.");
		} while (true);
	}

	public static <E extends Enum<E>> void printAll(Class<E> obj)
	{
		int i = pos;
		System.out.println(FormatString.replaceString(obj.getSimpleName(), "_", " ") + ": ");
		for (E constant : obj.getEnumConstants())
		{
			System.out.println("\t" + i + ". " + string(constant));
			i++;
		}
		System.out.print("Enter your option: ");
	}

	public static <E extends Enum<E>> String string(E constant)
	{
		return FormatString.replaceString(constant.toString(), "_", " ");
	}
}

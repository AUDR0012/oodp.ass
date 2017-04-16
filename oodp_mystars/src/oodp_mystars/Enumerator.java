package oodp_mystars;

import java.util.Scanner;

/**
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Enumerator {

	public enum Alternate_Week { // Does Session occurs on Alternate Weeks
		ODD, EVEN, NONE;
	}

	public enum Course_Type { // Course Type
		CORE, CORE_ELECTIVE, GER_CORE, GER_ELECTIVE, UNRESTRICTED_ELECTIVE;
	}

	public enum Day { // Day of the Week
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
	}

	public enum Gender { // Gender of the Student
		MALE, FEMALE;
	}

	public enum Group_Status { // Student's Status in the Group
		REGISTERED, WAITLIST, NOT_FOUND;
	}

	public enum Notification_Status { // How the student wish to be notified
		SMS, EMAIL;
	}

	public enum Session_Type { // Type of Session
		LECTURE, TUTORIAL, LAB;
	}

	/**
	 * Gets the enum that was chose based on input
	 * 
	 * @param obj
	 *            Enum Class
	 * @return enum constant
	 */
	public static <E extends Enum<E>> E nextEnum(Class<E> obj)
	{
		printAll(obj);

		int value;
		do
		{
			value = Formatter.getIntegerInput("");
			for (E constant : obj.getEnumConstants())
			{
				if ((value - 1) == constant.ordinal())
				{
					return constant;
				}
			}

			System.out.println("Please enter a valid input.");
		} while (true);
	}

	/**
	 * Prints the enum from the class with an index
	 * 
	 * @param obj
	 *            Enum Class
	 */
	public static <E extends Enum<E>> void printAll(Class<E> obj)
	{
		int i = 1;
		System.out.println(Formatter.replaceString(obj.getSimpleName(), "_", " ") + ": ");
		for (E constant : obj.getEnumConstants())
		{
			System.out.println("\t" + i + ". " + string(constant));
			i++;
		}
	}

	/**
	 * 
	 * @param constant
	 * @return
	 */
	public static <E extends Enum<E>> String string(E constant)
	{
		return Formatter.replaceString(constant.toString(), "_", " ");
	}
}

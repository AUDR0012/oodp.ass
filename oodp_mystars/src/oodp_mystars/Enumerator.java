package oodp_mystars;

import java.util.Scanner;

/**
 * Represent a class containing different Enumerators
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Enumerator {

	/**
	 * Represent whether the session should occurs on Alternate Weeks or none
	 */
	public enum Alternate_Week { // Does Session 
		ODD, EVEN, NONE;
	}

	/**
	 * Represent the type of course
	 */
	public enum Course_Type { // Course Type
		CORE, CORE_ELECTIVE, GER_CORE, GER_ELECTIVE, UNRESTRICTED_ELECTIVE;
	}

	/**
	 * Represent the list of day a session should be held
	 */
	public enum Day { // Day of the Week
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
	}

	/**
	 * Represent the type of gender a person should be
	 */
	public enum Gender { // Gender of the Student
		MALE, FEMALE;
	}

	/**
	 * Represent the status of of a student in the group
	 */
	public enum Group_Status { // Student's Status in the Group
		REGISTERED, WAITLIST, NOT_FOUND;
	}

	/**
	 * Represent how the student wish to be notified
	 */
	public enum Notification_Status { // How the student wish to be notified
		SMS, EMAIL;
	}

	/**
	 * Represent the type of session
	 */
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
	 * @return enum constant after replacing the character
	 */
	public static <E extends Enum<E>> String string(E constant)
	{
		return Formatter.replaceString(constant.toString(), "_", " ");
	}
}

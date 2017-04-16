package oodp_mystars;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Represent a class that provide methods to format data
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Formatter {

	/**
	 * Convert clear text password to hashed
	 * 
	 * @param password
	 *            The password in plain text
	 * @return hashed password
	 */
	public static String hashPassword(String password)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes(), 0, password.length());
			return (new BigInteger(1, md.digest()).toString());
		}
		catch (NoSuchAlgorithmException x)
		{
			// return x.toString();
			return null;
		}
	}

	/**
	 * Parse string to date based on format given
	 * 
	 * @param dateTime
	 *            The date object to be formatted
	 * @param format
	 *            The formatted to be changed to
	 * @return string parsed date
	 */
	public static String getString(Date dateTime, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (!Objects.equals(null, dateTime))
			return sdf.format(dateTime);
		else
			return "";
	}

	/**
	 * Parse string to date based on format given
	 * 
	 * @param dateTime
	 *            The date object to be retrieved
	 * @param format
	 *            The format requested
	 * @return date parsed string
	 */
	public static Date getDate(String dateTime, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		if (!Objects.equals(null, dateTime))
		{
			try
			{
				return sdf.parse(dateTime);
			}
			catch (ParseException e)
			{
				// e.printStackTrace();
				System.out.println(e);
			}
		}
		return null;
	}

	/**
	 * Prompt user for input for adding of date
	 * 
	 * @param type
	 *            The type of date / time to be added
	 * @return date based on the input
	 */
	public static Date enterDateTime(String type)
	{
		int day = 0, month = 0, year = 0, hour = 0, minute = 0;
		if (type.contains("date"))
		{
			day = withinRange("Day", 1, 31);
			month = withinRange("Month", 1, 12);
			year = withinRange("Year", 1900, 2020);
		}

		if (type.contains("time"))
		{
			hour = withinRange("Hour", 0, 23);
			minute = withinRange("Minute", 0, 59);
		}

		return getDate(String.format("%02d", day) + "-" + String.format("%02d", month) + "-" + year + " " + hour + ":"
				+ minute, "dd-MM-yyyy hh:mm");
	}

	/**
	 * Adding hours to the date given
	 * 
	 * @param date
	 *            The date object to add hours to
	 * @param hours
	 *            The number of hourse to add to the date object
	 * @return date after certain hours
	 */
	public static Date addHours(Date date, int hours)
	{
		return new Date(date.getTime() + TimeUnit.HOURS.toMillis(hours));
	}

	/**
	 * Gets string of 2 date joint together based on format given
	 * 
	 * @param date1
	 *            first date
	 * @param date2
	 *            second date
	 * @param format
	 *            date format
	 * @param joint
	 *            conjunction word
	 * @return string concatenated of date parsed string
	 */
	public static String getPeriod(Date date1, Date date2, String format, String joint)
	{
		if (!Objects.equals(null, date1) && !Objects.equals(null, date2))
		{
			return Formatter.getString(date1, format) + joint + Formatter.getString(date2, format);
		}
		return "";
	}

	/**
	 * Adding tabs to text fit column
	 * 
	 * @param count
	 *            The number of tab to be executed
	 * @param delimiter
	 *            The character to separate data fields
	 * @param text
	 *            The value or data to be displayed
	 * @return string after tab based on length
	 */
	public static String tabs(int count, String delimiter, String text)
	{
		int length = 8;
		String tabs = "\t";
		int size = (length * count) - (text.length() + delimiter.length() + 1);
		while (size > length - 1)
		{
			tabs += "\t";
			size -= length;
		}
		return text + tabs + delimiter;
	}

	/**
	 * Replace c1 with c2 that is found in text
	 * 
	 * @param text
	 *            The string of text
	 * @param c1
	 *            The existing text to be replaced
	 * @param c2
	 *            The new text to replace the old text
	 * @return replaced string
	 */
	public static String replaceString(String text, String c1, String c2)
	{
		return text.replaceAll(c1, c2);
	}

	/**
	 * Loops till user input a integer
	 * 
	 * @param message
	 *            The message to prompt before asking the user for a integer input
	 * @return valid int input
	 */
	public static int getIntegerInput(String message)
	{
		Scanner in = new Scanner(System.in);
		while (true)
		{
			try
			{
				if (message == "")
				{
					System.out.print("Enter your choice: ");
				}
				else
				{
					System.out.print(message);
				}
				return in.nextInt();
			}
			catch (Exception e)
			{
				System.out.println("Invalid value! Please re-enter your choice");
				in.nextLine(); // this consumes the invalid token
			}
		}
	}

	/**
	 * Checks if item is within the range
	 * 
	 * @param item
	 *            The message to be displayed before asking for input
	 * @param min
	 *            The minimum value that the input are allowed to be
	 * @param max
	 *            The maximum value that the input are allowed to be
	 * @return valid int input within range
	 */
	public static int withinRange(String item, int min, int max)
	{
		int input;
		while (true)
		{
			input = getIntegerInput("Enter " + item + ": ");
			if (input >= min)
			{
				if (max == -1 || input <= max)
				{
					break;
				}
				else
				{
					System.out.println("Please enter a valid " + item + " from " + min + " to " + max + ".");
				}
			}
			else
			{
				if (max == -1)
				{
					System.out.println("Please enter a valid " + item + " more than " + (min - 1) + ".");
				}
				else
				{
					System.out.println("Please enter a valid " + item + " from " + min + " to " + max + ".");
				}
			}
		}
		return input;
	}
}

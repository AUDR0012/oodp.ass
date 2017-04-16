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
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Formatter {

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

	public static Date addHours(Date date, int hours)
	{
		return new Date(date.getTime() + TimeUnit.HOURS.toMillis(hours));
	}

	public static String getString(Date dateTime, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (!Objects.equals(null, dateTime))
			return sdf.format(dateTime);
		else
			return "";
	}

	public static String getTimePeriod(Date time1, Date time2, String format, String joint)
	{
		if (!Objects.equals(null, time1) && !Objects.equals(null, time2))
		{
			return Formatter.getString(time1, format) + joint + Formatter.getString(time2, format);
		}
		return "";
	}

	public static String tabs(int count, String delimiter, String text)
	{
		int length = 8;
		String tabs = "\t";
		int size = length * count - text.length() - delimiter.length() - 1;
		while (size > length - 1)
		{
			tabs += "\t";
			size -= length;
		}
		return text + tabs + delimiter;
	}

	public static String replaceString(String text, String tCur, String tNew)
	{
		return text.replaceAll(tCur, tNew);
	}

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
				in.next(); // this consumes the invalid token
			}
		}
	}

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

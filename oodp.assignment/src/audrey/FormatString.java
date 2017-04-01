package audrey;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FormatString {

	public static Date getDate(String dateTime, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		if (!dateTime.equals(null))
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

	public static Date enterDateTime(Scanner in, String type)
	{
		int day = 0, month = 0, year = 0, hour = 0, minute = 0;
		if (type.contains("date"))
		{
			System.out.print("Day: ");
			day = in.nextInt();
			System.out.print("Month: ");
			month = in.nextInt();
			System.out.print("Year: ");
			year = in.nextInt();
		}
		
		if (type.contains("time"))
		{
			System.out.print("Hour: ");
			hour = in.nextInt();
			System.out.print("Minute: ");
			minute = in.nextInt();
		}
		
		return getDate(String.format("%02d", day) + "-" + String.format("%02d", month) + "-" + year + " " + hour + ":" + minute,
				"dd-MM-yyyy hh:mm");
	}
	
	public static Date addHours(Date date, int hours)
	{
		return new Date(date.getTime() + TimeUnit.HOURS.toMillis(hours));
	}

	public static String getString(Date dateTime, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (!dateTime.equals(null))
			return sdf.format(dateTime);
		else
			return "";
	}

	public static String tabs(int length, String delimiter, String text)
	{
		String tabs = "\t";
		int size = length - text.length() - delimiter.length() - 1;
		while (size > 7)
		{
			tabs += "\t";
			size -= 8;
		}
		return text + tabs + delimiter;
	}

	public static String replaceString(String text, String tCur, String tNew)
	{
		return text.replaceAll(tCur, tNew);
	}

	public static String getTimePeriod(Date time1, Date time2, String format)
	{
		if (!Objects.equals(null, time1) && !Objects.equals(null, time2))
		{
			return FormatString.getString(time1, format) + "-" + FormatString.getString(time2, format);
		}
		return "";
	}

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
}

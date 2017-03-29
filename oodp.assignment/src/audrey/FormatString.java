package audrey;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String getString(Date dateTime, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (dateTime != null)
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

package audrey;

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

	public static String tabString(String text, int length, String delimiter)
	{
		String tabs = "\t";
		int size = length - text.length();
		while (size > 7)
		{
			tabs += "\t";
			size -= 7;
		}
		return text + tabs + delimiter;
	}

	public static String getBar(int count, String character)
	{
		String bar = character;
		for (int i = 1; i < count; i++)
		{
			bar += character;
		}
		return bar;
	}
}

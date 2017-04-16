package oodp_mystars;

/**
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Menu {

	public static void printMenu(Comparable user)
	{
		printHeader(user);
		if (user instanceof Student)
		{
			System.out.println("===== Student Menu =====");
			System.out.println("1. Register Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check/ Print Courses Registered");
			System.out.println("4. Check Vacancies");
			System.out.println("5. Change Index Number of Group");
			System.out.println("6. Swop Index Number with Another Student");
			System.out.println("7. Update Particulars");
		}
		else // if (user instanceof Admin)
		{
			System.out.println("===== Admin Menu =====");
			System.out.println("1. Edit Student Access Period");
			System.out.println("2. Add a Student");
			System.out.println("3. Add a Course");
			System.out.println("4. Update a Course");
			System.out.println("5. Check Available Slot for an Index Number");
			System.out.println("6. Print Student List by Index Number");
			System.out.println("7. Print Student List of Course");
		}
		System.out.println("0. Exit");
	}

	public static void printHeader(Comparable user)
	{
		System.out.println("   _____   _____   _____   _____   _____   _____   _____  ");
		System.out.println(
				" _|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| ");
		System.out.println(" \"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\' ");

		if (user instanceof Student)
		{
			Student st = (Student) user;
			System.out.println(Formatter.tabs(4, "", "Name: " + st.getName()) + "Matric Number: " + st.getMatricNo());
		}
		else if (user instanceof Admin)
		{
			Admin ad = (Admin) user;
			System.out.println(Formatter.tabs(4, "", "Name: " + ad.getName()));
		}
		else
		{
			System.out.println("Welcome to MySTARS!");
		}
	}

	public static String getTableHeader(String delimiter, String table)
	{
		String header = delimiter;
		if (table.equals("course"))
		{ //8 tabs
			header += Formatter.tabs(2, delimiter, "Course")
					+ Formatter.tabs(1, delimiter, "AU")
					+ Formatter.tabs(3, delimiter, "Title")
					+ Formatter.tabs(2, delimiter, "Course Type");
		}
		else if (table.equals("coursegroup"))
		{ //12 tabs
			header += Formatter.tabs(2, delimiter, "Course")
					+ Formatter.tabs(1, delimiter, "AU")
					+ Formatter.tabs(3, delimiter, "Title")
					+ Formatter.tabs(2, delimiter, "Course Type")
					+ Formatter.tabs(1, delimiter, "Index")
					+ Formatter.tabs(3, delimiter, "Status");
		}
		else if (table.equals("group"))
		{ //12 tabs
			header += Formatter.tabs(2, delimiter, "Class Type")
					+ Formatter.tabs(1, delimiter, "Group")
					+ Formatter.tabs(2, delimiter, "Day")
					+ Formatter.tabs(2, delimiter, "Time")
					+ Formatter.tabs(2, delimiter, "Venue")
					+ Formatter.tabs(3, delimiter, "Remark");
		}
		else if (table.equals("student"))
		{ //6 tabs
			header += Formatter.tabs(3, delimiter, "Name")
					+ Formatter.tabs(1, delimiter, "Gender")
					+ Formatter.tabs(2, delimiter, "Nationality");
		}
		return header;
	}

	public static String getBar(int tabs, String character)
	{
		int count = 8 * tabs + 1;
		String bar = character;
		for (int i = 1; i < count; i++)
		{
			bar += character;
		}
		return bar;
	}

}

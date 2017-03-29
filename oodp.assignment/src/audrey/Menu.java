package audrey;

public class Menu {

	public static void printMenu(Comparable user)
	{
		printHeader(user);
		if (user instanceof Student)
		{
			System.out.println("===== Student Menu =====");
			System.out.println("1. Add Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check/ Print Courses Registered");
			System.out.println("4. Check Vacancies");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swop Index Number with Another Student");
		}
		else // if (user instanceof Student)
		{
			System.out.println("===== Admin Menu =====");
			System.out.println("1. Edit Student Access Period");
			System.out.println("2. Add a Student");
			System.out.println("3. Add/ Update a Course");
			System.out.println("4. Check Available Slot for an Index Number");
			System.out.println("5. Print Student List by Index Number");
			System.out.println("6. Print Student List of Course");
		}
		System.out.println("0. Exit");
		System.out.print("Enter your option: ");
	}

	public static void printHeader(Comparable user)
	{
		System.out.println("   _____   _____   _____   _____   _____   _____   _____  ");
		System.out.println(" _|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| ");
		System.out.println(" \"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\' ");

		if (user instanceof Student)
		{
			Student st = (Student) user;
			System.out.println(FormatString.tabs(32, "", "Name: " + st.getName())
					+ "Matric Number: " + st.getMatricNo());
		}
		else if (user instanceof Admin)
		{
			Admin ad = (Admin) user;
			System.out.println(FormatString.tabs(32, "", "Name: " + ad.getName()));
		}
		else
		{
			System.out.println("Welcome to MySTARS!");
		}
	}

	public static String getTableHeader(int length, String delimiter, String table)
	{
		String header = delimiter;
		if (table.equals("course"))
		{
			header += FormatString.tabs(length * 2, delimiter, "Course")
					+ FormatString.tabs(length * 1, delimiter, "AU")
					+ FormatString.tabs(length * 3, delimiter, "Title")
					+ FormatString.tabs(length * 2, delimiter, "Course Type")
					+ FormatString.tabs(length * 1, delimiter, "Index")
					+ FormatString.tabs(length * 3, delimiter, "Status");
		}
		else // if (table.equals("group"))
		{
			header += FormatString.tabs(length * 2, delimiter, "Class Type")
					+ FormatString.tabs(length * 1, delimiter, "Group")
					+ FormatString.tabs(length * 2, delimiter, "Day")
					+ FormatString.tabs(length * 2, delimiter, "Time")
					+ FormatString.tabs(length * 2, delimiter, "Venue")
					+ FormatString.tabs(length * 3, delimiter, "Remark");
		}
		return header;
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
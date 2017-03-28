package audrey;

import java.io.Console;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import audrey.Course.Course_Type;
import audrey.Session.Session_Day;
import audrey.Session.Session_Type;
import audrey.Student.Student_Gender;

// java -Xmx1024m -jar run.jar
public class MySTARS {

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		final int PARSE_LENGTH = 13;
		final String PARSE_DELIMITER = "| ";

		ArrayList<Logger> userList = new ArrayList<Logger>();
		ArrayList<Student> studentList = new ArrayList<Student>();
		ArrayList<Course> courseList = new ArrayList<Course>();
		Comparable user = null;
		int option, in_int;
		String bar = "";
		Group foundGroup = null;

		System.out.print("add data = 1"); // TODO:TEMPORARY
		if (in.nextInt() == 1) // TODO:TEMPORARY
			addData(userList, studentList, courseList); // TODO:TEMPORARY

		printHeader(user);
		readData(userList, studentList, courseList);

		System.out.print("login = 1"); // TODO:TEMPORARY
		if (in.nextInt() == 1) // TODO:TEMPORARY
			user = login(in, userList);
		else // TODO:TEMPORARY
			user = userList.get(6).getUser(); // TODO:TEMPORARY

		if (user != null)
		{
			do
			{
				printHeader(user);
				printMenu(user instanceof Student);
				option = in.nextInt();
				switch (option)
				{
					case 1:
					{ // Add Course
						System.out.print("Enter Index Number of the Course: ");
						in_int = in.nextInt();
						for (Course co : courseList)
						{
							if ((foundGroup = co.findGroup(in_int)) != null)
							{
								if (!co.findStudent((Student) user)) // Student already registered in Course
								// if (!(boolean) foundGroup.findStudent((Student) user, "boolean")) // Student already registered in Group
								{
									co.printGroup(foundGroup, PARSE_LENGTH, PARSE_DELIMITER);
									System.out.println("Course Type: " + co.getType());
									System.out.print("Confirm to Add Course? (Y/N) ");
									if (in.next().equalsIgnoreCase("Y"))
									{
										foundGroup.addStudentToGroup((Student) user);
										((Student) user).getCourseGroups().add(foundGroup);
									}
								}
								else
								{
									System.out.println("Course has already been registered.");
								}
								break;
							}
						}
						if (foundGroup == null)
						{
							System.out.println("Course Index Number do not exist.");
						}
						break;
					}
					case 2:
					{// Drop Course
						break;
					}
					case 3:
					{ // Check/Print Courses Registered
						ArrayList<Group> groups = ((Student) user).getCourseGroups();
						int totalAU = 0;
						if (groups.size() != 0)
						{
							bar = FormatString.getBar(105, "=");
							System.out.println(bar + "\n| Course\t| AU\t| Title\t\t\t\t| Course Type\t| Index\t\t| Status\t|\n" + bar);
							for (Group gr : groups)
							{
								for (Course co : courseList)
								{
									if (co.findGroup(gr.getIndexNo()) != null)
									{
										co.printCourse(gr, PARSE_LENGTH, PARSE_DELIMITER, (Student) user);
										totalAU += co.getCredit();
										break;
									}
								}
							}
							System.out.println(bar);
							System.out.println("Total AU Registered: " + totalAU);
						}
						else
						{
							System.out.println("No courses has been added.");
						}
						break;
					}
					case 4:
					{ // Check Vacancies Available
						System.out.print("Enter Index Number of the Course: ");
						in_int = in.nextInt();
						do
						{
							for (Course co : courseList)
							{
								if ((foundGroup = co.findGroup(in_int)) != null)
								{
									co.printGroup(foundGroup, PARSE_LENGTH, PARSE_DELIMITER);
									System.out.println(FormatString.tabString("Places Available: " + foundGroup.getVacancy(), 40, "")
											+ "Length of WaitList: " + foundGroup.getWaitlist().size());
									break;
								}
							}
							if (foundGroup == null)
							{
								System.out.println("Course Index Number do not exist.");
							}

							System.out.println("\nCheck Vacancy of another Index Number");
							System.out.print("Index Number (or 0 to Exit): ");
							in_int = in.nextInt();
						} while (in_int != 0);
						break;
					}
					case 5:
					{ // Change Index Number of Course
						break;
					}
					case 6:
					{ // Swap Index Number with Another Student
						break;
					}
					default:
					{
						System.out.println("Option entered is invalid.");
						break;
					}
				}
			} while (option != 0);
		}
		else
		{
			System.out.println("The username or password you entered is incorrect.");
		}
	}

	public static void printMenu(boolean isStudent)
	{
		if (isStudent)
		{
			System.out.println("===== Student Menu =====");
			System.out.println("1. Add Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check/ Print Courses Registered");
			System.out.println("4. Check Vacancies");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swop Index Number with Another Student");
		}
		else
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
		// TODO: clear console

		System.out.println("   _____   _____   _____   _____   _____   _____   _____  ");
		System.out.println(
				" _|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| ");
		System.out.println(" \"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\' ");

		if (user instanceof Student)
		{
			Student st = (Student) user;
			System.out.println(FormatString.tabString("Name: " + st.getName(), 31, "") 
				+ "Matric Number: " + st.getMatricNo());
		}
		else if (user instanceof Admin)
		{
			Admin ad = (Admin) user;
			System.out.println(FormatString.tabString("Name: " + ad.getName(), 31, ""));
		}
		else
		{
			System.out.println("Welcom to MySTARS!");
		}
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

	public static Comparable login(Scanner in, ArrayList<Logger> userList)
	{
		Console console = System.console();
		String username, password;

		System.out.print("Username: ");
		username = in.next();
		System.out.print("Password: ");
		//password = String.copyValueOf(console.readPassword()); //TODO: MAIN
		password = in.next(); // TODO:TEMPORARY

		for (Logger l : userList)
		{
			if (l.getUsername().equalsIgnoreCase(username))
			{
				if (l.getPassword().equals(hashPassword(password)))
				{
					return l.getUser();
				}
			}
		}
		return null;
	}

	public static void readData(ArrayList<Logger> userList, ArrayList<Student> studentList,
			ArrayList<Course> courseList)
	{
		// Logger
		userList.addAll((ArrayList<Logger>) FileIO.readSerializedObject("list_user"));

		// Student
		for (Logger l : userList)
		{
			if (l.getUser() instanceof Student)
			{
				studentList.add((Student) l.getUser());
			}
		}

		// Course
		courseList.addAll((ArrayList<Course>) FileIO.readSerializedObject("list_course"));
	}

	public static void writeData(ArrayList<Logger> userList, ArrayList<Course> courseList)
	{
		FileIO.writeSerializedObject("list_user", userList);
		FileIO.writeSerializedObject("list_course", courseList);
	}

	public static void addData(ArrayList<Logger> userList, ArrayList<Student> studentList, ArrayList<Course> courseList)
	{
		ArrayList<Group> grs = new ArrayList<Group>();
		ArrayList<Session> ses = new ArrayList<Session>();

		Course co;
		Group gr;
		Session se;
		Comparable us;
		Logger lo;

		se = new Session(Session_Type.LECTURE, "CS2", Session_Day.TUESDAY, "08:30", 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS2", Session_Day.THURSDAY, "09:30", 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "SSR1", Session_Day.MONDAY, "09:30", 1, "TR+37", "Wk2-13");
		ses.add(se);
		se = new Session(Session_Type.LAB, "SSR1", Session_Day.MONDAY, "10:30", 2, "SWLAB1", "Wk2,4,6,8,10,12");
		ses.add(se);
		gr = new Group(10191, 0, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS2", Session_Day.TUESDAY, "08:30", 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS2", Session_Day.THURSDAY, "09:30", 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "SSR2", Session_Day.TUESDAY, "14:30", 1, "TR+45", "Wk2-13");
		ses.add(se);
		se = new Session(Session_Type.LAB, "SSR2", Session_Day.TUESDAY, "10:30", 2, "SWLAB1", "Wk2,4,6,8,10,12");
		ses.add(se);
		gr = new Group(10192, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS2", Session_Day.TUESDAY, "08:30", 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS2", Session_Day.THURSDAY, "09:30", 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "SSR3", Session_Day.MONDAY, "16:30", 1, "TR+37", "Wk2-13");
		ses.add(se);
		se = new Session(Session_Type.LAB, "SSR3", Session_Day.MONDAY, "10:30", 2, "SWLAB1", "Wk1,3,5,7,9,11,13");
		ses.add(se);
		gr = new Group(10193, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS2", Session_Day.TUESDAY, "08:30", 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS2", Session_Day.THURSDAY, "09:30", 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "BCG2", Session_Day.FRIDAY, "13:30", 1, "TR+45", "Wk2-13");
		ses.add(se);
		se = new Session(Session_Type.LAB, "BCG2", Session_Day.FRIDAY, "10:30", 2, "SWLAB1", "Wk2,4,6,8,10,12");
		ses.add(se);
		gr = new Group(10194, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("CZ2005", "OPERATING SYSTEMS", Course_Type.CORE, 3, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.MONDAY, "10:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.THURSDAY, "14:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "FS1", Session_Day.TUESDAY, "09:30", 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "FS1", Session_Day.TUESDAY, "08:30", 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10201, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.MONDAY, "10:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.THURSDAY, "14:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "FS2", Session_Day.THURSDAY, "11:30", 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "FS2", Session_Day.THURSDAY, "10:30", 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10202, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.MONDAY, "10:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.THURSDAY, "14:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "FS3", Session_Day.TUESDAY, "15:30", 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "FS3", Session_Day.TUESDAY, "14:30", 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10203, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.MONDAY, "10:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.THURSDAY, "14:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "DD1", Session_Day.TUESDAY, "09:30", 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "DD1", Session_Day.TUESDAY, "08:30", 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10403, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.MONDAY, "10:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.THURSDAY, "14:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "FS4", Session_Day.FRIDAY, "15:30", 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "FS4", Session_Day.FRIDAY, "14:30", 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10442, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.MONDAY, "10:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Session_Day.THURSDAY, "14:30", 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "DD2", Session_Day.FRIDAY, "09:30", 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "DD2", Session_Day.FRIDAY, "08:30", 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10457, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("CZ1007", "DATA STRUCTURES", Course_Type.CORE, 3, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		se = new Session(Session_Type.TUTORIAL, "G1", Session_Day.WEDNESDAY, "13:30", 2, "LHS-TR+29", "Wk3-13");
		ses.add(se);
		gr = new Group(20201, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.TUTORIAL, "G2", Session_Day.WEDNESDAY, "15:30", 2, "LHS-TR+29", "Wk3-13");
		ses.add(se);
		gr = new Group(20202, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("HW0001", "ENGLISH PROFICIENCY", Course_Type.GER_CORE, 0, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		se = new Session(Session_Type.TUTORIAL, "SCE1", Session_Day.WEDNESDAY, "14:30", 2, "LHS-TR+45", "Wk2-13");
		ses.add(se);
		gr = new Group(10411, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.TUTORIAL, "SCE2", Session_Day.WEDNESDAY, "14:30", 2, "LHS-TR+9", "Wk2-13");
		ses.add(se);
		gr = new Group(10412, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("HW0288", "ENGINEERING COMMUNICATION II", Course_Type.GER_CORE, 2, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		se = new Session(Session_Type.LECTURE, "CS3", Session_Day.FRIDAY, "08:30", 1, "LT19", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "CS3", Session_Day.FRIDAY, "09:30", 1, "SCSE LABS", "");
		ses.add(se);
		gr = new Group(10411, 30, ses, (new ArrayList<Student>()), (new ArrayList<Student>()));
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("CZ3004", "MULTIDISCIPLINARY DESIGN PROJECT", Course_Type.CORE, 4, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		us = new Admin("LIANG QIANHUI");
		lo = new Logger("QHLIANG0", hashPassword("QHLIANG0"), us);
		userList.add(lo);
		us = new Admin("TAN KHENG LEONG");
		lo = new Logger("KHENGLEO", hashPassword("KHENGLEO"), us);
		userList.add(lo);
		us = new Admin("PHAN CONG MINH");
		lo = new Logger("PHAN0050", hashPassword("PHAN0050"), us);
		userList.add(lo);
		us = new Student("ANG CHIN SIONG", Student_Gender.MALE, "U1642357D", 2, "CHINSO01@E.NTU.EDU.SG", "11-02-93",
				"Singaporean", "91254632", (new ArrayList<Group>()));
		lo = new Logger("CHINSONG", hashPassword("CHINSONG"), us);
		userList.add(lo);
		us = new Student("AUDREY HO HAI YI", Student_Gender.FEMALE, "U1624153E", 1, "AUDREY02@E.NTU.EDU.SG", "02-04-95",
				"Singaporean", "97216542", (new ArrayList<Group>()));
		lo = new Logger("AUDREY", hashPassword("AUDREY"), us);
		userList.add(lo);
		us = new Student("ONG WEI FENG KELVIN", Student_Gender.MALE, "U1637974J", 3, "KELVIN03@E.NTU.EDU.SG",
				"23-09-96", "Malaysian", "94415325", (new ArrayList<Group>()));
		lo = new Logger("KELVIN", hashPassword("KELVIN"), us);
		userList.add(lo);
		us = new Student("TOH JIAN HAO", Student_Gender.MALE, "U1647253G", 2, "JIANHA04@E.NTU.EDU.SG", "11-01-92",
				"Singaporean", "92141632", (new ArrayList<Group>()));
		lo = new Logger("JIANHAO", hashPassword("JIANHAO"), us);
		userList.add(lo);
		us = new Student("WONG KIN SUMG", Student_Gender.MALE, "U1624152R", 1, "KINSUM05@E.NTU.EDU.SG", "21-08-94",
				"Singaporean", "94522516", (new ArrayList<Group>()));
		lo = new Logger("KINSUM", hashPassword("KINSUM"), us);
		userList.add(lo);
		us = new Student("JESSIE WONG PEI XIN", Student_Gender.FEMALE, "U1524482P", 4, "CZ2002.FSP2.AGRP3@GMAIL.COM",
				"07-05-95", "Japanese", "81801268", (new ArrayList<Group>()));
		lo = new Logger("JESSIE", hashPassword("JESSIE"), us);
		userList.add(lo);
		us = new Student("LESLIE LAU WEI QI", Student_Gender.MALE, "U1622987E", 2, "LLAU8122@E.NTU.EDU.SG", "28-04-93",
				"Malaysian", "83421088", (new ArrayList<Group>()));
		lo = new Logger("LESLIE", hashPassword("LESLIE"), us);
		userList.add(lo);
		us = new Student("LIM HONG SIONG BENJAMIN", Student_Gender.MALE, "U1687459J", 3, "BENLIM03@E.NTU.EDU.SG",
				"04-05-91", "Chinese", "81643759", (new ArrayList<Group>()));
		lo = new Logger("BENLIM", hashPassword("BENLIM"), us);
		userList.add(lo);
		us = new Student("TAN YI HONG", Student_Gender.MALE, "U1647953K", 2, "TANYH019@E.NTU.EDU.SG", "28-04-90",
				"Singaporean", "98763544", (new ArrayList<Group>()));
		lo = new Logger("YIHONG", hashPassword("YIHONG"), us);
		userList.add(lo);
		us = new Student("LOW GUO WANG", Student_Gender.MALE, "U1636745L", 2, "GUOWANG6@E.NTU.EDU.SG", "17-02-92",
				"Chinese", "87329563", (new ArrayList<Group>()));
		lo = new Logger("GUOWANG", hashPassword("GUOWANG"), us);
		userList.add(lo);
		us = new Student("WONG JIA MIN", Student_Gender.FEMALE, "U1636974M", 3, "WONGJM93@E.NTU.EDU.SG", "26-08-96",
				"Singaporean", "97356971", (new ArrayList<Group>()));
		lo = new Logger("JIAMIN", hashPassword("JIAMIN"), us);
		userList.add(lo);

		for (Logger l : userList)
		{
			if (l.getUser() instanceof Student)
			{
				studentList.add((Student) l.getUser());
			}
		}

		writeData(userList, courseList);
	}
}
package audrey;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import audrey.Course.Course_Type;
import audrey.Group.Group_StudentStatus;
import audrey.Session.Session_Day;
import audrey.Session.Session_Type;
import audrey.Student.Student_Gender;

// java -Xmx1024m -jar run.jar
public class MySTARS {

	public static void main(String[] args)
	{

		Scanner in = new Scanner(System.in);

		final int PARSE_LENGTH = 8;
		final String PARSE_DELIMITER = "| ";

		ArrayList<Logger> userList = new ArrayList<Logger>();
		ArrayList<Student> studentList = new ArrayList<Student>();
		ArrayList<Course> courseList = new ArrayList<Course>();

		Comparable user = null;
		Course course = null;
		Group group = null, group2 = null;
		int option, in_int, in_int2;

		System.out.print("add data = 1"); // TODO:TEMPORARY
		if (in.nextInt() == 1) // TODO:TEMPORARY
			addData(userList, studentList, courseList); // TODO:TEMPORARY

		Menu.printHeader(user);
		readData(userList, studentList, courseList);

		System.out.print("login = 1"); // TODO:TEMPORARY
		if (in.nextInt() == 1) // TODO:TEMPORARY
			user = login(in, userList);
		else // TODO:TEMPORARY
			user = userList.get(3).getUser(); // TODO:TEMPORARY

		if (user instanceof Admin)
		{
			do
			{
				Menu.printMenu(user);
				option = in.nextInt();
				switch (option)
				{
					case 1:
					{ // Edit Student Access Period
						break;
					}
					case 2:
					{ // Add a Student
						break;
					}
					case 3:
					{ // Add/ Update a Course
						break;
					}
					case 4:
					{ // Check Available Slot for an Index Number
						break;
					}
					case 5:
					{ // Print Student List by Index Number
						break;
					}
					case 6:
					{ // Print Student List of Course
						break;
					}
					case 0:
					{ // Exit
						System.out.print("write = 1"); // TODO:TEMPORARY
						if (in.nextInt() == 1) // TODO:TEMPORARY
							writeData(userList, courseList);
						System.out.println("Exiting.");
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
		else if (user instanceof Student)
		{
			Student student = (Student) user;
			do
			{
				Menu.printMenu(user);
				option = in.nextInt();
				switch (option)
				{
					case 1:
					{ // Add Course
						System.out.print("Enter Index Number of the Course: ");
						in_int = in.nextInt();
						for (Course co : courseList)
						{
							if ((group = co.findGroup(in_int)) != null)
							{
								if (!co.findStudent(student))
								{
									co.printGroup(group, PARSE_LENGTH, PARSE_DELIMITER);
									System.out.println("Course Type: " + co.getType());
									System.out.print("Confirm to Add Course? (Y/N) ");
									if (in.next().equalsIgnoreCase("Y"))
									{
										group.addStudentToGroup(student);
										student.getCourseGroups().add(group);
									}
									else
									{
										System.out.println("Index Number " + in_int + " is not added.");
									}
								}
								else
								{
									System.out.println("Course has already been registered.");
								}
								break;
							}
						}
						if (group == null)
						{
							System.out.println("Index Number " + in_int + " do not exist.");
						}
						break;
					}
					case 2:
					{ // Drop Course
						if (student.getCourseGroups().size() > 0)
						{
							student.listCourses(courseList, PARSE_LENGTH, PARSE_DELIMITER);
							System.out.print("Choose the Course to drop: ");
							in_int = in.nextInt();
							if (in_int > 1 && in_int <= student.getCourseGroups().size())
							{
								group = student.getCourseGroups().get(in_int - 1);
								course = group.getCourse(courseList);
								course.printGroup(group, PARSE_LENGTH, PARSE_DELIMITER);
								System.out.println(FormatString.tabs(50, "", "Course Type: " + course.getType())
										+ "Status: " + group.findStudent(student, "status"));
								System.out.print("Confirm to Add Course? (Y/N) ");
								if (in.next().equalsIgnoreCase("Y"))
								{
									group.dropStudentFromGroup(student);
									student.getCourseGroups().remove(group);
								}
								else
								{
									System.out.println("Index Number " + in_int + " is not dropped.");
								}
							}
						}
						else
						{
							System.out.println("No courses has been added.");
						}
						break;
					}
					case 3:
					{ // Check/Print Courses Registered
						if (student.getCourseGroups().size() > 0)
						{
							student.printCourses(courseList, PARSE_LENGTH, PARSE_DELIMITER);
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
								if ((group = co.findGroup(in_int)) != null)
								{
									co.printGroup(group, PARSE_LENGTH, PARSE_DELIMITER);
									System.out.println(FormatString.tabs(50, "", "Places Available: " + group.getVacancy())
											+ "Length of WaitList: " + group.getWaitlist().size());
									break;
								}
							}
							if (group == null)
							{
								System.out.println("Index Number " + in_int + " do not exist.");
							}

							System.out.println("\nCheck Vacancy of another Index Number");
							System.out.print("Index Number (or 0. Back to Menu): ");
							in_int = in.nextInt();
						} while (in_int != 0);
						break;
					}
					case 5:
					{ // Change Index Number of Course
						System.out.print("Enter Current Index Number of the Course: ");
						in_int = in.nextInt();
						if ((group = student.findGroup(in_int)) != null)
						{
							if (Enumerator.string(Group_StudentStatus.REGISTERED).equals(group.findStudent(student, "status")))
							{
								System.out.print("Enter New Index Number of the Course: ");
								in_int2 = in.nextInt();
								if (in_int != in_int2)
								{
									course = group.getCourse(courseList);
									if ((group2 = course.findGroup(in_int2)) != null)
									{
										if (group2.getVacancy() > 0)
										{
											course.print2Groups(group, group2, PARSE_LENGTH, PARSE_DELIMITER);
											System.out.println(FormatString.tabs(100, "", "Subject Type: " + course.getType())
													+ "Status: " + Enumerator.string(Group_StudentStatus.REGISTERED));
											System.out.print("Confirm to Change Index Number? (Y/N) ");
											if (in.next().equalsIgnoreCase("Y"))
											{
												group.dropStudentFromGroup(student);
												group2.addStudentToGroup(student);
												student.replaceGroup(group, group2);
												System.out.println("Index Number " + group.getIndexNo() + " has been changed to " + group2.getIndexNo());
											}
											else
											{
												System.out.println("Index Number " + in_int + " is not changed.");
											}
										}
										else
										{
											System.out.println("Index Number " + in_int2 + " has no vacancy.");
										}
										break;
									}
									else
									{
										for (Course co : courseList)
										{
											if ((group2 = course.findGroup(in_int2)) != null)
											{
												System.out.println("Index Number " + in_int2 + " exist in a different Course.");
											}
										}
										if (group2 == null)
										{
											System.out.println("Index Number " + in_int2 + " do not exist.");
										}
									}
								}
								else
								{
									System.out.println("Current and New Index Number is the same.");
								}
							}
							else
							{
								System.out.println("Index Number " + in_int + " is in waitlist.");
							}
						}
						else
						{
							System.out.println("Index Number " + in_int + " is not registered.");
						}
						break;
					}
					case 6:
					{ // Swap Index Number with Another Student
						break;
					}
					case 0:
					{ // Exit
						System.out.print("write = 1"); // TODO:TEMPORARY
						if (in.nextInt() == 1) // TODO:TEMPORARY
							writeData(userList, courseList);
						System.out.println("Exiting.");
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
				if (l.getPassword().equals(FormatString.hashPassword(password)))
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
		lo = new Logger("QHLIANG0", FormatString.hashPassword("QHLIANG0"), us);
		userList.add(lo);
		us = new Admin("TAN KHENG LEONG");
		lo = new Logger("KHENGLEO", FormatString.hashPassword("KHENGLEO"), us);
		userList.add(lo);
		us = new Admin("PHAN CONG MINH");
		lo = new Logger("PHAN0050", FormatString.hashPassword("PHAN0050"), us);
		userList.add(lo);
		us = new Student("ANG CHIN SIONG", Student_Gender.MALE, "U1642357D", 2, "CHINSO01@E.NTU.EDU.SG", "11-02-93",
				"Singaporean", "91254632", (new ArrayList<Group>()));
		lo = new Logger("CHINSONG", FormatString.hashPassword("CHINSONG"), us);
		userList.add(lo);
		us = new Student("AUDREY HO HAI YI", Student_Gender.FEMALE, "U1624153E", 1, "AUDREY02@E.NTU.EDU.SG", "02-04-95",
				"Singaporean", "97216542", (new ArrayList<Group>()));
		lo = new Logger("AUDREY", FormatString.hashPassword("AUDREY"), us);
		userList.add(lo);
		us = new Student("ONG WEI FENG KELVIN", Student_Gender.MALE, "U1637974J", 3, "KELVIN03@E.NTU.EDU.SG",
				"23-09-96", "Malaysian", "94415325", (new ArrayList<Group>()));
		lo = new Logger("KELVIN", FormatString.hashPassword("KELVIN"), us);
		userList.add(lo);
		us = new Student("TOH JIAN HAO", Student_Gender.MALE, "U1647253G", 2, "JIANHA04@E.NTU.EDU.SG", "11-01-92",
				"Singaporean", "92141632", (new ArrayList<Group>()));
		lo = new Logger("JIANHAO", FormatString.hashPassword("JIANHAO"), us);
		userList.add(lo);
		us = new Student("WONG KIN SUM", Student_Gender.MALE, "U1624152R", 1, "KINSUM05@E.NTU.EDU.SG", "21-08-94",
				"Singaporean", "94522516", (new ArrayList<Group>()));
		lo = new Logger("KINSUM", FormatString.hashPassword("KINSUM"), us);
		userList.add(lo);
		us = new Student("JESSIE WONG PEI XIN", Student_Gender.FEMALE, "U1524482P", 4, "JESSIE13@E.NTU.EDU.SG",
				"07-05-95", "Japanese", "81801268", (new ArrayList<Group>()));
		lo = new Logger("JESSIE", FormatString.hashPassword("JESSIE"), us);
		userList.add(lo);
		us = new Student("LESLIE LAU WEI QI", Student_Gender.MALE, "U1622987E", 2, "LLAU8122@E.NTU.EDU.SG", "28-04-93",
				"Malaysian", "83421088", (new ArrayList<Group>()));
		lo = new Logger("LESLIE", FormatString.hashPassword("LESLIE"), us);
		userList.add(lo);
		us = new Student("LIM HONG SIONG BENJAMIN", Student_Gender.MALE, "U1687459J", 3, "BENLIM03@E.NTU.EDU.SG",
				"04-05-91", "Chinese", "81643759", (new ArrayList<Group>()));
		lo = new Logger("BENLIM", FormatString.hashPassword("BENLIM"), us);
		userList.add(lo);
		us = new Student("TAN YI HONG", Student_Gender.MALE, "U1647953K", 2, "TANYH019@E.NTU.EDU.SG", "28-04-90",
				"Singaporean", "98763544", (new ArrayList<Group>()));
		lo = new Logger("YIHONG", FormatString.hashPassword("YIHONG"), us);
		userList.add(lo);
		us = new Student("LOW GUO WANG", Student_Gender.MALE, "U1636745L", 2, "GUOWANG6@E.NTU.EDU.SG", "17-02-92",
				"Chinese", "87329563", (new ArrayList<Group>()));
		lo = new Logger("GUOWANG", FormatString.hashPassword("GUOWANG"), us);
		userList.add(lo);
		us = new Student("WONG JIA MIN", Student_Gender.FEMALE, "U1636974M", 3, "WONGJM93@E.NTU.EDU.SG", "26-08-96",
				"Singaporean", "97356971", (new ArrayList<Group>()));
		lo = new Logger("JIAMIN", FormatString.hashPassword("JIAMIN"), us);
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
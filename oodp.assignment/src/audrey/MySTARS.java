package audrey;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import audrey.Enumerator.*;

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

		Notifier notify = new Notifier();

		Logger logger = null;
		Comparable user = null, peer = null;
		Course course = null;
		Group group = null, group2 = null;
		Session session;
		int option, in_int, in_int2, overlaps, overlaps2;
		String in_string;
		boolean found;

		// Developer.addData(userList, studentList, courseList);

		Menu.printHeader(user);
		readData(userList, studentList, courseList);

		logger = login(userList);
		user = logger.getUser();

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
						Date start, end;

						System.out.println("Enter the Start Date of Access Time");
						start = Formatter.enterDateTime(in, "date_time");

						System.out.println("Enter End Date of Access Time");
						end = Formatter.enterDateTime(in, "date_time");

						for (Logger lo : userList)
						{
							lo.setAccessSTime(start);
							lo.setAccessETime(end);
						}
						System.out.println("Access Time has been updated to " + Formatter.getTimePeriod(start, end, "dd-MM-yy hh:mm"));
						break;
					}
					case 2:
					{ // Add a Student
						Logger newLogger = new Logger();
						Student newStudent = new Student();

						// Username
						System.out.print("Enter Username: ");
						newLogger.setUsername(in.next());
						// Password
						System.out.print("Enter Password: ");
						newLogger.setPassword(in.next());

						// Name
						System.out.print("Enter Name: ");
						in.nextLine();
						newStudent.setName(in.nextLine());
						// Gender
						Enumerator.printAll(Gender.class);
						newStudent.setGender(Enumerator.nextEnum(Gender.class, in));
						// Matric Number
						System.out.print("Enter Matric Number: ");
						newStudent.setMatricNo(in.next());
						// Study Year
						System.out.print("Enter Study Year: ");
						newStudent.setStudyYear(in.nextInt());
						// Email
						do
						{
							if (newStudent.getEmail() != null)
							{
								System.out.println("Please enter a valid Email Address.");
							}
							System.out.print("Enter Email Address: ");
							newStudent.setEmail(in.next());
						} while (!Formatter.emailValid(newStudent.getEmail()));
						// Date of Birth
						System.out.print("Enter Date of Birth: ");
						newStudent.setDob(Formatter.enterDateTime(in, "date"));
						// Nationality
						System.out.print("Enter Nationality: ");
						newStudent.setNationality(in.next());
						// Phone Number
						do
						{
							if (newStudent.getPhoneNo() != null)
							{
								System.out.println("Please enter a valid Local Phone Number.");
							}
							System.out.print("Enter Phone Number: ");
							newStudent.setPhoneNo(in.next());
						} while (newStudent.getPhoneNo().length() != 8);
						// Notification
						Enumerator.printAll(Notification_Status.class);
						newStudent.setNotification(Enumerator.nextEnum(Notification_Status.class, in));

						newLogger.setUser(newStudent);

						studentList.add(newStudent);
						userList.add(newLogger);
						break;
					}
					case 3:
					{ // Add/ Update a Course
						course = new Course();
						// Id
						do
						{
							System.out.print("Enter Course Id: ");
							course.setId(in.next());
						} while (dupCourseId(courseList, course.getId()));
						// Name
						System.out.print("Enter Course Name: ");
						in.nextLine();
						course.setName(in.nextLine());
						// Type
						Enumerator.printAll(Course_Type.class);
						course.setType(Enumerator.nextEnum(Course_Type.class, in));
						// Credit
						System.out.print("Enter Number of Credits: ");
						course.setCredit(in.nextInt());
						// Number of Groups
						System.out.print("Enter the Number of Groups: ");
						in_int = in.nextInt();
						// Number of Sessions
						System.out.print("Enter the Number of Sessions: ");
						in_int2 = in.nextInt();

						while (course.getGroups().size() < in_int)
						{
							System.out.println("Group #" + course.getGroups().size());
							group = new Group();
							// Index Number
							do
							{
								System.out.print("Enter Index Number: ");
								group.setIndexNo(in.nextInt());
							} while (course.dupIndexNo(group.getIndexNo()));
							// Vacancy
							System.out.print("Enter Size of Group: ");

							while (group.getSessions().size() < in_int2)
							{
								System.out.println("Group #" + course.getGroups().size()
										+ " Session #" + group.getSessions().size());
								session = new Session();
								// Type
								Enumerator.printAll(Session_Type.class);
								session.setType(Enumerator.nextEnum(Session_Type.class, in));
								// Group
								System.out.print("Enter Group name: ");
								session.setGroup(in.next());
								// Day
								Enumerator.printAll(Day.class);
								session.setDay(Enumerator.nextEnum(Day.class, in));
								// Start Time
								System.out.print("Enter Start Time: ");
								session.setSTime(Formatter.enterDateTime(in, "date"));
								// Hours
								System.out.print("Enter Number of Hours: ");
								session.setETime(Formatter.addHours(session.getSTime(), in.nextInt()));
								// Venue
								System.out.print("Enter Venue: ");
								in.nextLine();
								session.setVenue(in.nextLine());
								// Remark
								System.out.print("Enter Remarks: ");
								in.nextLine();
								session.setRemark(in.nextLine());

								group.addSession(session);
							}
							course.addGroup(group);
						}

						break;
					}
					case 4:
					{ // Check Available Slot for an Index Number
						System.out.print("Enter Index Number of the Course: ");
						in_int = in.nextInt();
						if (!Objects.equals(null, (group = groupExist(courseList, in_int))))
						{
							System.out.println("Index Number " + in_int + " has " + group.getVacancy() + " vacancies.");
						}
						else
						{
							System.out.println("Index Number " + in_int + " is not found.");
						}
						break;
					}
					case 5:
					{ // Print Student List by Index Number
						System.out.print("Enter Index Number of the Course: ");
						in_int = in.nextInt();
						if (!Objects.equals(null, (group = groupExist(courseList, in_int))))
						{
							if (group.getRegistered().size() > 0)
							{
								group.printStudents(PARSE_LENGTH, PARSE_DELIMITER);
							}
							else
							{
								System.out.println("Index Number " + in_int + " has no students.");
							}
						}
						else
						{
							System.out.println("Index Number " + in_int + " is not found.");
						}
						break;
					}
					case 6:
					{ // Print Student List of Course
						found = false;
						System.out.print("Enter Course Id: ");
						in_string = in.next();
						for (Course co : courseList)
						{
							if (co.getId().equalsIgnoreCase(in_string))
							{
								if (co.countStudentInGroups() > 0)
								{
									co.printStudents(PARSE_LENGTH, PARSE_DELIMITER);
								}
								else
								{
									System.out.println("Course Id " + in_string.toUpperCase() + " has no students.");
								}
								found = true;
								break;
							}
						}
						if (!found)
						{
							System.out.println("Course Id " + in_string.toUpperCase() + " is not found.");
						}
						break;
					}
					case 0:
					{ // Exit
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
						if (!Objects.equals(null, (group = groupExist(courseList, in_int))))
						{
							course = group.getCourse(courseList);
							if (!course.findStudent(student))
							{
								if ((overlaps = student.isOverlap(group)) == -1)
								{
									System.out.println(Formatter.tabs(40, "", "Index Number: " + group.getIndexNo()) + "Course: " + course.getId());
									course.printGroup(group, PARSE_LENGTH, PARSE_DELIMITER);
									System.out.println("Course Type: " + course.getType());

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
									System.out.println("Overlaps with Index Number " + overlaps + ".");
								}
							}
							else
							{
								System.out.println("Course has already been registered.");
							}
						}
						else
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
							if (in_int >= 1 && in_int <= student.getCourseGroups().size())
							{
								group = student.getCourseGroups().get(in_int - 1);
								course = group.getCourse(courseList);

								System.out.println(Formatter.tabs(40, "", "Index Number: " + group.getIndexNo()) + "Course: " + course.getId());
								course.printGroup(group, PARSE_LENGTH, PARSE_DELIMITER);
								System.out.println(Formatter.tabs(50, "", "Course Type: " + course.getType()) + "Status: " + group.findStudent(student, "status"));

								System.out.print("Confirm to Add Course? (Y/N) ");
								if (in.next().equalsIgnoreCase("Y"))
								{
									if (group.dropStudentFromGroup(student))
									{
										group.updateWaitlist(student, course, notify);
									}

									student.getCourseGroups().remove(group);
								}
								else
								{
									System.out.println("Index Number " + in_int + " is not dropped.");
								}
							}
							else
							{
								System.out.println("Option entered is invalid.");
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
							if (!Objects.equals(null, (group = groupExist(courseList, in_int))))
							{
								course = group.getCourse(courseList);
								System.out.println(Formatter.tabs(40, "", "Index Number: " + group.getIndexNo()) + "Course: " + course.getId());
								course.printGroup(group, PARSE_LENGTH, PARSE_DELIMITER);
								System.out.println(Formatter.tabs(50, "", "Places Available: " + group.getVacancy())
										+ "Length of WaitList: " + group.getWaitlist().size());
								break;
							}
							else
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
						if (!Objects.equals(null, (group = student.findGroup(in_int))))
						{
							if (Enumerator.string(Group_Status.REGISTERED).equals(group.findStudent(student, "status")))
							{
								System.out.print("Enter New Index Number of the Course: ");
								in_int2 = in.nextInt();
								if (in_int != in_int2)
								{
									course = group.getCourse(courseList);
									if (!Objects.equals(null, (group2 = course.findGroup(in_int2))))
									{
										if (group2.getVacancy() > 0)
										{
											if ((overlaps = student.isOverlap(group2)) == -1)
											{
												System.out.println("Subject: " + course.getId());
												System.out.println(Formatter.tabs(100, "", "Current Index Number: " + String.valueOf(group.getIndexNo()))
														+ "New Index Number: " + String.valueOf(group2.getIndexNo()));
												course.print2Groups(group, group2, PARSE_LENGTH, PARSE_DELIMITER);
												System.out.println(Formatter.tabs(100, "", "Subject Type: " + course.getType())
														+ "Status: " + Enumerator.string(Group_Status.REGISTERED));
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
												System.out.println("Overlaps with Index Number " + overlaps + ".");
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
										if (!Objects.equals(null, groupExist(courseList, in_int2)))
										{
											System.out.println("Index Number " + in_int2 + " exist in a different Course.");
										}
										else
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
							if (!Objects.equals(null, groupExist(courseList, in_int)))
							{
								System.out.println("Index Number " + in_int + " is not registered.");
							}
							else
							{
								System.out.println("Index Number " + in_int + " do not exist.");
							}
						}
						break;
					}
					case 6:
					{ // Swap Index Number with Another Student
						System.out.print("Enter your Index Number: ");
						in_int = in.nextInt();
						if (!Objects.equals(null, (group = student.findGroup(in_int))))
						{
							if (Enumerator.string(Group_Status.REGISTERED).equals(group.findStudent(student, "status")))
							{
								System.out.println("Please ask your Peer to Log in");
								if (!Objects.equals(null, (peer = login(userList).getUser()))
										&& peer instanceof Student)
								{
									System.out.print("Enter Peer's Index Number: ");
									in_int2 = in.nextInt();

									if (!Objects.equals(null, (group2 = ((Student) peer).findGroup(in_int2))))
									{
										if (Enumerator.string(Group_Status.REGISTERED).equals(group2.findStudent(student, "status")))
										{
											if (Objects.equals((course = group.getCourse(courseList)), group2.getCourse(courseList)))
											{
												if ((overlaps = student.isOverlap(group2)) == -1
														&& (overlaps2 = ((Student) peer).isOverlap(group)) == -1)
												{
													System.out.println("Subject: " + course.getId());
													System.out.println(Formatter.tabs(100, "", "Student #1 ")
															+ "Student #2");
													System.out.println(Formatter.tabs(100, "", "Matric Number: " + student.getMatricNo() + " Index Number: " + group.getIndexNo())
															+ "Matric Number: " + ((Student) peer).getMatricNo() + " Index Number: " + group2.getIndexNo());
													course.print2Groups(group, group2, PARSE_LENGTH, PARSE_DELIMITER);
													System.out.println(Formatter.tabs(100, "", "Subject Type: " + course.getType())
															+ "Subject Type: " + course.getType());
													System.out.print("Confirm to Swop Index Number? (Y/N) ");
													if (in.next().equalsIgnoreCase("Y"))
													{
														group.dropStudentFromGroup(student);
														group.addStudentToGroup((Student) peer);
														student.replaceGroup(group, group2);

														group2.addStudentToGroup(student);
														group2.dropStudentFromGroup((Student) peer);
														((Student) peer).replaceGroup(group2, group);
														System.out.println(student.getMatricNo() + "-Index Number " + group.getIndexNo() + " has successfully swopped with "
																+ ((Student) peer).getMatricNo() + "-Index Number " + group2.getIndexNo());
													}
													else
													{
														System.out.println(student.getMatricNo() + " and " + ((Student) peer).getMatricNo() + " is not swoped");
													}
												}
												else
												{
													if (!Objects.equals(null, group))
													{
														System.out.println("Student " + student.getMatricNo() + "'s Course Index Number "
																+ group.getIndexNo() + "overlaps with " + in_int2 + ".");
													}
													else
													{
														System.out.println("Student " + ((Student) peer).getMatricNo() + "'s Course Index Number "
																+ group2.getIndexNo() + "overlaps with " + in_int + ".");
													}
												}
											}
											else
											{
												System.out.println("Index Number " + in_int + " and Index Number " + in_int2 + " exist in a different Course.");
											}
										}
										else
										{
											System.out.println("Student " + ((Student) peer).getMatricNo() + "'s Course Index Number " + in_int2 + " is in waitlist.");
										}
									}
									else
									{
										if (!Objects.equals(null, groupExist(courseList, in_int)))
										{
											System.out.println("Student " + ((Student) peer).getMatricNo() + " is not registerd in Index Number " + in_int2 + ".");
										}
										else
										{
											System.out.println("Index Number " + in_int2 + " do not exist.");
										}
									}
								}
								else
								{
									System.out.println("The username or password you entered is incorrect.");
								}
							}
							else
							{
								System.out.println("Student " + student.getMatricNo() + "'s Course Index Number " + in_int + " is in waitlist.");
							}
						}
						else
						{
							if (!Objects.equals(null, groupExist(courseList, in_int)))
							{
								System.out.println("Student " + student.getMatricNo() + " is not registerd in Index Number " + in_int + ".");
							}
							else
							{
								System.out.println("Index Number " + in_int + " do not exist.");
							}
						}
						break;
					}
					case 7:
					{ // Update Particulars
						System.out.println("\t1. Email");
						System.out.println("\t2. Phone Number");
						System.out.println("\t3. Notification Status");
						System.out.println("\t4. Password");
						System.out.println("\t0. Back to Menu");
						System.out.print("Choose your option: ");
						in_int = in.nextInt();
						switch (in_int)
						{
							case 1:
							{ // Email
								System.out.println("Current Email:" + student.getEmail());
								System.out.print("Enter the new Email:");
								in_string = in.next();
								if (Formatter.emailValid(in_string))
								{
									student.setEmail(in_string);
									System.out.println("Email has been updated successfully.");
								}
								else
								{
									System.out.println(in_string + " is not valid Email.");
								}
								break;
							}
							case 2:
							{ // Phone Number
								System.out.println("Current Phone Number:" + student.getPhoneNo());
								System.out.print("Enter the new Phone Number:");
								in_string = in.next();
								if (in_string.length() == 8)
								{
									student.setPhoneNo(in_string);
									System.out.println("Phone Number has been updated successfully.");
								}
								else
								{
									System.out.println(in_string + " is not valid Local Phone Number.");
								}
								break;
							}
							case 3:
							{ // Notification Status
								System.out.println("Current Notification Status: " + student.getNotification());
								Enumerator.printAll(Notification_Status.class);
								student.setNotification(Enumerator.nextEnum(Notification_Status.class, in));
								System.out.println("Notification Status has been updated successfully.");
								break;
							}
							case 4:
							{ // Password
								System.out.print("Enter your current Password: ");
								if (logger.getPassword().equals(inputPassword()))
								{
									System.out.print("Enter your new Password: ");
									logger.setPassword(inputPassword());
									System.out.println("Password has been updated successfully.");
								}
								else
								{
									System.out.println("Current Password entered incorrectly.");
								}
								break;
							}
							case 0:
							{ // Back to Menu
								break;
							}
							default:
							{
								System.out.println("Option entered is invalid.");
								break;
							}
						}
						break;
					}
					case 0:
					{ // Exit
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

	public static String inputPassword()
	{
		//return Formatter.hashPassword(String.copyValueOf(System.console().readPassword()));
		return Formatter.hashPassword((new Scanner(System.in)).next());
	}

	public static Logger login(ArrayList<Logger> userList)
	{
		String username, password;

		System.out.print("Username: ");
		username = (new Scanner(System.in)).next();
		System.out.print("Password: ");
		password = inputPassword();

		for (Logger lo : userList)
		{
			if (lo.getUsername().equalsIgnoreCase(username))
			{
				if (lo.getPassword().equals(password))
				{
					if (lo.getUser() instanceof Student)
					{
						Date today = new Date();
						if (today.after(lo.getAccessSTime()) && today.before(lo.getAccessETime()))
						{
							return lo;
						}
						else
						{
							System.out.println("You can only access between " + Formatter.getString(lo.getAccessSTime(), "dd-MM-yyyy hh:mm a")
									+ " to " + Formatter.getString(lo.getAccessETime(), "dd-MM-yyyy hh:mm a"));
							return null;
						}
					}
					else
					{
						return lo;
					}
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

	public static Group groupExist(ArrayList<Course> courseList, int indexNo)
	{
		Group gr = null;
		for (Course co : courseList)
		{
			if (!Objects.equals(null, (gr = co.findGroup(indexNo))))
			{
				return gr;
			}
		}
		return gr;
	}

	public static boolean dupCourseId(ArrayList<Course> courseList, String courseId)
	{
		for (Course co : courseList)
		{
			if (co.getId().equalsIgnoreCase(courseId))
			{
				System.out.println("Course Id " + courseId.toUpperCase() + " has already been used.");
				return true;
			}
		}
		return false;
	}
}
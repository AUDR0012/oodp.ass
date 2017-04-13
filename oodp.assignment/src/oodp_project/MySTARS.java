package oodp_project;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import oodp_project.Enumerator.*;

/**
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
// java -Xmx1024m -jar run.jar
public class MySTARS
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		final int PARSE_LENGTH = 8;
		final String PARSE_DELIMITER = "| ";

		ArrayList<Logger> userList = new ArrayList<Logger>();
		ArrayList<Student> studentList = new ArrayList<Student>();
		ArrayList<Course> courseList = new ArrayList<Course>();

		Notifier notify = new Notifier();
		Group_Status groupStatus;

		Comparable user = null, peer = null;
		Course course = null;
		Group group1 = null, group2 = null;
		Session session;
		int option = 0, in_int1 = 0, in_int2 = 0, in_int3 = 0;
		int choice1 = 0, choice2 = 0, overlaps = 0, overlaps2 = 0;
		String in_string;
		boolean found;

		Developer.addData(userList, studentList, courseList);
		do
		{
			if (userList.isEmpty() || studentList.isEmpty() || courseList.isEmpty())
			{
				readData(userList, studentList, courseList);
			}

			Menu.printHeader(null);
			user = login(userList);

			if (user instanceof Admin)
			{
				do
				{
					Menu.printMenu(user);
					option = Formatter.getIntegerInput("Enter your option: ");
					switch (option)
					{
					case 1:
					{ // Edit Student Access Period
						Date start, end;

						System.out.println("Enter the Start Date of Access Time");
						start = Formatter.enterDateTime("date_time");

						System.out.println("Enter End Date of Access Time");
						end = Formatter.enterDateTime("date_time");

						for (Logger lo : userList)
						{
							lo.setAccessSTime(start);
							lo.setAccessETime(end);
						}
						System.out.println("Access Time has been updated to "
								+ Formatter.getTimePeriod(start, end, "dd-MM-yy hh:mm"));
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
						newLogger.setPassword(Formatter.hashPassword(in.next()));

						// Name
						System.out.print("Enter Name: ");
						in.nextLine();
						newStudent.setName(in.nextLine());
						// Gender
						Enumerator.printAll(Gender.class);
						newStudent.setGender(Enumerator.nextEnum(Gender.class));
						// Matric Number
						System.out.print("Enter Matric Number: ");
						newStudent.setMatricNo(in.next());
						// Study Year
						newStudent.setStudyYear(Formatter.getIntegerInput("Enter Study Year: "));
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
						newStudent.setDob(Formatter.enterDateTime("date"));
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
						newStudent.setNotification(Enumerator.nextEnum(Notification_Status.class));

						newLogger.setAccessSTime(new Date());
						newLogger.setAccessETime(new Date());

						newLogger.setUser(newStudent);

						studentList.add(newStudent);
						userList.add(newLogger);
						break;
					}
					case 3:
					{ // Add a Course
						course = new Course();
						// Id
						do
						{
							if (course.getId() != null)
							{
								System.out.println(
										"Course Id " + course.getId().toUpperCase() + " has already been used.");
							}
							System.out.print("Enter Course Id: ");
							course.setId(in.next());
						} while (!Objects.equals(null, courseExist(courseList, course.getId())));
						// Name
						System.out.print("Enter Course Name: ");
						in.nextLine();
						course.setName(in.nextLine());
						// Type
						Enumerator.printAll(Course_Type.class);
						course.setType(Enumerator.nextEnum(Course_Type.class));
						// Credit
						course.setCredit(Formatter.getIntegerInput("Enter Number of Credits"));
						// Number of Groups
						in_int1 = Formatter.getIntegerInput("Enter the Number of Groups: ");
						// Number of Sessions
						in_int2 = Formatter.getIntegerInput("Enter the Number of Sessions: ");

						while (course.getGroups().size() < in_int1)
						{
							System.out.println("Group #" + course.getGroups().size() + 1);
							group1 = new Group();
							// Index Number
							do
							{
								group1.setIndexNo(Formatter.getIntegerInput("Enter Index Number: "));
							} while (dupIndexNo(courseList, group1.getIndexNo()));
							// Vacancy
							group1.setVacancy(Formatter.getIntegerInput("Enter Size of Group: "));

							while (group1.getSessions().size() < in_int2)
							{
								System.out.println("Group #" + course.getGroups().size() + 1 + " Session #"
										+ group1.getSessions().size() + 1);
								if (!Objects.equals(null, session = addSession(group1, -1)))
								{
									group1.getSessions().add(session);
								} else
								{
									System.out.println("Session overlaps with an session in this Group.");
								}
							}
							course.addGroup(group1);
						}
						break;
					}

					case 4:
					{ // Update a Course
						System.out.print("Enter Course Id: ");
						in_string = in.next();
						if (!Objects.equals(null, course = courseExist(courseList, in_string)))
						{
							do
							{
								in_int1 = Formatter.getIntegerInput("\t1. Update Course Name\n"
										+ "\t2. Update Course Group\n" + "\t0. Back to Menu\n" + "Enter your choice: ");

								switch (in_int1)
								{
								case 1:
								{ // Update Course Name
									System.out.println("Current Course Name: " + course.getName());
									System.out.print("New Course Name: ");
									in.nextLine();
									course.setName(in.nextLine());
									System.out.println("Course Name has been changed to :" + course.getName());
									break;
								}
								case 2:
								{ // Update Course Group
									do
									{
										in_int2 = Formatter.getIntegerInput("\t\t1. Add Group" + "\t\t2. Edit Group\n"
												+ "\t\t3. Delete Group\n" + "\t\t0. Back\n" + "Enter your choice: ");
										switch (in_int2)
										{
										case 1:
										{ // Add Group
											group1 = new Group();
											group1.setIndexNo(Formatter.getIntegerInput("Enter Index Number: "));
											group1.setVacancy(Formatter.withinRange("Vancancies", 1, -1));
											course.addGroup(group1);
											System.out.println("Group has been added.");
											break;
										}
										case 2:
										{ // Edit Group
											do
											{
												System.out.println(
														"Current Groups in Course Id: " + in_string.toUpperCase());
												for (int i = 0; i < course.getGroups().size(); i++)
												{
													System.out.println("\t" + i + 1 + ". "
															+ course.getGroups().get(i).getIndexNo());
												}
												System.out.println("\t0. Back");
												if ((choice1 = Formatter.withinRange("choice", 0,
														course.getGroups().size() + 1)) != 0)
												{
													group1 = course.getGroups().get(choice1 - 1);
													in_int3 = Formatter.getIntegerInput("\t\t\t1. Edit Group Vacancy\n"
															+ "\t\t\t2. Add Session\n" + "\t\t\t3. Edit Session\n"
															+ "\t\t\t4. Delete Session\n" + "\t\t\t0. Back\n"
															+ "Enter your choice: ");
													switch (in_int3)
													{
													case 1:
													{ // Edit Group Vacancy
														System.out.println("Current Vacancy in group "
																+ group1.getIndexNo() + ": " + group1.getVacancy());
														group1.setVacancy(Formatter.withinRange("Vacancies", 1, -1));
														updateStudentGroups(studentList, group1);
														System.out.println(
																"Vacancy has been changed to :" + group1.getVacancy());
														break;
													}
													case 2:
													{ // Add Session
														if (!Objects.equals(null, session = addSession(group1, -1)))
														{
															group1.getSessions().add(session);
															updateStudentGroups(studentList, group1);
															System.out.println("Session has been added.");
														} else
														{
															System.out.println(
																	"Session overlaps with an session in this Group.");
														}
														break;
													}
													case 3:
													{ // Edit Session
														System.out.println("Current Session in Course Id: "
																+ in_string.toUpperCase() + " Index Number: "
																+ group1.getIndexNo());
														for (int i = 0; i < group1.getSessions().size(); i++)
														{
															session = group1.getSessions().get(i);
															System.out.println("\t\t\t" + i + 1 + ". "
																	+ session.getType() + " Venue: "
																	+ session.getVenue() + "Time: " + session.getDay()
																	+ session.getSTime() + session.getETime());
														}
														System.out.println("\t\t\t0. Back");
														if ((choice2 = Formatter.withinRange("choice", 0,
																group1.getSessions().size() + 1)) != 0)
														{
															if (Objects.equals(null,
																	session = addSession(group1, choice2 - 1)))
															{
																group1.getSessions().set(choice2 - 1, session);
																updateStudentGroups(studentList, group1);
																System.out.println("Session is updated.");
															} else
															{
																System.out.println(
																		"Session overlaps with an session in this Group.");
															}
														}
														break;
													}
													case 4:
													{ // Delete Session
														System.out.println("Current Session in Course Id: "
																+ in_string.toUpperCase() + " Index Number: "
																+ group1.getIndexNo());
														for (int i = 0; i < group1.getSessions().size(); i++)
														{
															session = group1.getSessions().get(i);
															System.out.println("\t\t\t" + i + 1 + ". "
																	+ session.getType() + " Venue: "
																	+ session.getVenue() + "Time: " + session.getDay()
																	+ session.getSTime() + session.getETime());
														}
														System.out.println("\t\t\t0. Back");
														if ((choice2 = Formatter.withinRange("choice", 0,
																group1.getSessions().size() + 1)) != 0)
														{
															group1.getSessions().remove(choice2 - 1);
															updateStudentGroups(studentList, group1);
															System.out.println("Session has been deleted.");
														}
														break;
													}
													}
												}
											} while (in_int3 != 0);
											break;
										}
										case 3:
										{ // Delete Group
											System.out.println("Groups in CourseID: " + in_string);
											for (int i = 0; i < course.getGroups().size(); i++)
											{
												System.out.println(
														"\t" + i + 1 + ". " + course.getGroups().get(i).getIndexNo());
											}
											System.out.println("\t0. Back");
											if ((choice1 = Formatter.withinRange("choice", 0,
													course.getGroups().size() + 1)) != 0)
											{
												course.getGroups().remove(choice1 - 1);
												// Update on student side
												System.out.println("Group has been deleted.");
											}
											break;
										}
										case 0:
										{
											break;
										}
										default:
										{
											System.out.println("Invalid choice.");
											break;
										}
										}
									} while (in_int2 != 0);
									break;
								}
								case 0:
								{
									break;
								}
								default:
								{
									System.out.println("Invalid choice.");
									break;
								}
								}
							} while (in_int1 != 0);
						} else
						{
							System.out.println("Course Id " + in_string.toUpperCase() + " does not exist.");
						}
						break;
					}
					case 5:
					{ // Check Available Slot for an Index Number
						in_int1 = Formatter.getIntegerInput("Enter Index Number of Group: ");
						if (!Objects.equals(null, group1 = groupExist(courseList, in_int1)))
						{
							System.out
									.println("Index Number " + in_int1 + " has " + group1.getVacancy() + " vacancies.");
						} else
						{
							System.out.println("Index Number " + in_int1 + " is not found.");
						}
						break;
					}
					case 6:
					{ // Print Student List by Index Number
						in_int1 = Formatter.getIntegerInput("Enter Index Number of Group: ");
						if (!Objects.equals(null, group1 = groupExist(courseList, in_int1)))
						{
							if (group1.getRegistered().size() > 0)
							{
								group1.printStudents(PARSE_LENGTH, PARSE_DELIMITER);
							} else
							{
								System.out.println("Index Number " + in_int1 + " has no students.");
							}
						} else
						{
							System.out.println("Index Number " + in_int1 + " is not found.");
						}
						break;
					}
					case 7:
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
								} else
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
						userList = new ArrayList<Logger>();
						studentList = new ArrayList<Student>();
						courseList = new ArrayList<Course>();

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
			} else if (user instanceof Student)
			{
				Student student = (Student) user;
				do
				{
					Menu.printMenu(user);
					option = Formatter.getIntegerInput("Enter your option: ");
					switch (option)
					{
					case 1:
					{ // Add Course
						in_int1 = Formatter.getIntegerInput("Enter Index Number of Group: ");

						Boolean courseAlreadyExist = false;
						if (!Objects.equals(null, group1 = groupExist(courseList, in_int1)))
						{
							course = group1.getCourse(courseList);

							for (Group cGroup : course.getGroups())
							{
								if (student.findGroup(cGroup.getIndexNo()) != null)
								{
									courseAlreadyExist = true;
									break;
								}
							}
							if (!courseAlreadyExist)
							{
								if ((overlaps = isOverlap(student, group1, null)) == -1)
								{
									System.out.println(Formatter.tabs(40, "", "Index Number: " + group1.getIndexNo())
											+ "Course: " + course.getId());
									course.printGroups(group1, null, PARSE_LENGTH, PARSE_DELIMITER);
									System.out.println("Course Type: " + course.getType());

									System.out.print("Confirm to Add Course? (Y/N) ");
									if (in.next().equalsIgnoreCase("Y"))
									{
										if ((groupStatus = group1.addStudentToGroup(student))
												.equals(Group_Status.REGISTERED))
										{
											System.out
													.println("Registered to Index Number " + group1.getIndexNo() + ".");
										} else
										{
											System.out.println(
													"Adding to Index Number " + group1.getIndexNo() + "'s waitlist.");
										}
										updateCourseList(courseList, group1);
										student.notifyMe(course.getId(), group1.getIndexNo(), notify, groupStatus);
										student.getCourseGroups().add(group1);
									} else
									{
										System.out.println("Index Number " + in_int1 + " is not added.");
									}
								} else
								{
									System.out.println("Overlaps with Index Number " + overlaps + ".");
								}
							} else
							{
								System.out.println("Course has already been registered.");
							}
						} else
						{
							System.out.println("Index Number " + in_int1 + " do not exist.");
						}
						break;
					}
					case 2:
					{ // Drop Course
						if (student.getCourseGroups().size() > 0)
						{
							student.listCourses(courseList, PARSE_LENGTH, PARSE_DELIMITER);
							if ((in_int1 = Formatter.withinRange("index number", 0,
									student.getCourseGroups().size() + 1)) > 0)
							{
								group1 = groupExist(courseList,
										student.getCourseGroups().get(in_int1 - 1).getIndexNo());
								course = group1.getCourse(courseList);

								System.out.println(Formatter.tabs(40, "", "Index Number: " + group1.getIndexNo())
										+ "Course: " + course.getId());
								course.printGroups(group1, null, PARSE_LENGTH, PARSE_DELIMITER);
								System.out.println(Formatter.tabs(50, "", "Course Type: " + course.getType())
										+ "Status: " + group1.findStudent(student, "status"));

								System.out.print("Confirm to Drop Course? (Y/N) ");

								if (in.next().equalsIgnoreCase("Y"))
								{
									if (group1.dropStudentFromGroup(student).equals(Group_Status.REGISTERED))
									{
										Student s = group1.updateWaitlist();
										if (!Objects.equals(null, s))
										{
											String matricNo = s.getMatricNo();

											for (Student tStudent : studentList)
											{
												if (tStudent.getMatricNo().equalsIgnoreCase(matricNo))
												{
													for (Group cg : tStudent.getCourseGroups())
													{
														if (cg.getIndexNo() == group1.getIndexNo())
														{
															tStudent.getCourseGroups()
																	.remove(tStudent.getCourseGroups().indexOf(cg));
															tStudent.getCourseGroups().add(group1);
															tStudent.notifyMe(course.getId(), group1.getIndexNo(),
																	notify, Group_Status.REGISTERED);
															break;
														}
													}
												}
											}
										}
										System.out.println(
												"Removing student from " + group1.getIndexNo() + "'s registered list.");
									} else
									{
										System.out.println(
												"Removing student from " + group1.getIndexNo() + "'s waitlist.");
									}

									for (Student st : studentList)
									{
										if (st.getMatricNo().equalsIgnoreCase(student.getMatricNo()))
										{
											for (Group g : st.getCourseGroups())
											{
												if (g.getIndexNo() == group1.getIndexNo())
												{
													st.getCourseGroups().remove(st.getCourseGroups().indexOf(g));
													break;
												}
											}
										}
									}
									// student.getCourseGroups().remove(group1);
									updateCourseList(courseList, group1);
								} else
								{
									System.out.println("Index Number " + in_int1 + " is not dropped.");
								}
							}
						} else
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
						} else
						{
							System.out.println("No courses has been added.");
						}
						break;
					}
					case 4:
					{ // Check Vacancies Available
						in_int1 = Formatter.getIntegerInput("Enter Index Number of Group: ");
						do
						{
							if (!Objects.equals(null, group1 = groupExist(courseList, in_int1)))
							{
								course = group1.getCourse(courseList);
								System.out.println(Formatter.tabs(40, "", "Index Number: " + group1.getIndexNo())
										+ "Course: " + course.getId());
								course.printGroups(group1, null, PARSE_LENGTH, PARSE_DELIMITER);
								System.out.println(Formatter.tabs(50, "", "Places Available: " + group1.getVacancy())
										+ "Length of WaitList: " + group1.getWaitlist().size());
							} else
							{
								System.out.println("Index Number " + in_int1 + " do not exist.");
							}

							in_int1 = Formatter.getIntegerInput("\nCheck Vacancy of another Index Number"
									+ "\nIndex Number (or 0. Back to Menu): ");
						} while (in_int1 != 0);
						break;
					}
					case 5:
					{ // Change Index Number of Group
						in_int1 = Formatter.getIntegerInput("Enter Current Index Number of Group: ");

						if (!Objects.equals(null, group1 = student.findGroup(in_int1)))
						{
							if (Enumerator.string(Group_Status.REGISTERED)
									.equals(group1.findStudent(student, "status")))
							{
								in_int2 = Formatter.getIntegerInput("Enter New Index Number of Group: ");
								if (in_int1 != in_int2)
								{
									course = group1.getCourse(courseList);
									if (!Objects.equals(null, group2 = course.findGroup(in_int2)))
									{
										if (group2.getVacancy() > 0)
										{
											if ((overlaps = isOverlap(student, group2, group1)) == -1)
											{
												if (group2.getIndexNo() != group1.getIndexNo())
												{
													System.out.println("Subject: " + course.getId());
													System.out.println(Formatter.tabs(100, "",
															"Current Index Number: "
																	+ String.valueOf(group1.getIndexNo()))
															+ "New Index Number: "
															+ String.valueOf(group2.getIndexNo()));
													course.printGroups(group1, group2, PARSE_LENGTH, PARSE_DELIMITER);
													System.out.println(
															Formatter.tabs(100, "", "Subject Type: " + course.getType())
																	+ "Status: "
																	+ Enumerator.string(Group_Status.REGISTERED));
													System.out.print("Confirm to Change Index Number? (Y/N) ");
													if (in.next().equalsIgnoreCase("Y"))
													{
														group1.dropStudentFromGroup(student);
														group1.updateWaitlist();
														groupStatus = group2.addStudentToGroup(student);
														student.notifyMe(course.getId(), group2.getIndexNo(), notify,
																groupStatus);

														student.replaceGroup(group1, group2);

														updateCourseList(courseList, group1);
														System.out.println("Index Number " + group1.getIndexNo()
																+ " has been changed to " + group2.getIndexNo());
													} else
													{
														System.out.println(
																"Index Number " + in_int1 + " is not changed.");
													}
												} else
												{
													System.out.println("You are already in this group.");
												}
											} else
											{
												System.out.println("Overlaps with Index Number " + overlaps + ".");
											}
										} else
										{
											System.out.println("Index Number " + in_int2 + " has no vacancy.");
										}
										break;
									} else
									{
										if (!Objects.equals(null, groupExist(courseList, in_int2)))
										{
											System.out.println(
													"Index Number " + in_int2 + " exist in a different Course.");
										} else
										{
											System.out.println("Index Number " + in_int2 + " do not exist.");
										}
									}
								} else
								{
									System.out.println("Current and New Index Number is the same.");
								}
							} else
							{
								System.out.println("Index Number " + in_int1 + " is in waitlist.");
							}
						} else
						{
							if (!Objects.equals(null, groupExist(courseList, in_int1)))
							{
								System.out.println("Index Number " + in_int1 + " is not registered.");
							} else
							{
								System.out.println("Index Number " + in_int1 + " do not exist.");
							}
						}
						break;
					}
					case 6:
					{ // Swap Index Number with Another Student
						in_int1 = Formatter.getIntegerInput("Enter your Index Number: ");

						if (!Objects.equals(null, group1 = student.findGroup(in_int1)))
						{
							if (Enumerator.string(Group_Status.REGISTERED)
									.equals(group1.findStudent(student, "status")))
							{
								System.out.println("Please ask your Peer to Log in");
								if (!Objects.equals(null, peer = login(userList)) && peer instanceof Student)
								{
									in_int2 = Formatter.getIntegerInput("Enter Peer's Index Number: ");

									if (!Objects.equals(null, group2 = groupExist(courseList,
											((Student) peer).findGroup(in_int2).getIndexNo())))
									{
										if (Enumerator.string(Group_Status.REGISTERED)
												.equals(group2.findStudent(((Student) peer), "status")))
										{
											if (Objects.equals((course = group1.getCourse(courseList)),
													group2.getCourse(courseList)))
											{
												if ((overlaps = isOverlap(student, group2, group1)) == -1
														&& (overlaps2 = isOverlap(((Student) peer), group1,
																group2)) == -1)
												{
													if (group1.getIndexNo() != group2.getIndexNo())
													{
														System.out.println("Subject: " + course.getId());
														System.out.println(
																Formatter.tabs(100, "", "Student #1 ") + "Student #2");
														System.out.println(Formatter.tabs(100, "",
																"Matric Number: " + student.getMatricNo()
																		+ " Index Number: " + group1.getIndexNo())
																+ "Matric Number: " + ((Student) peer).getMatricNo()
																+ " Index Number: " + group2.getIndexNo());
														course.printGroups(group1, group2, PARSE_LENGTH,
																PARSE_DELIMITER);
														System.out.println(Formatter.tabs(100, "",
																"Subject Type: " + course.getType()) + "Subject Type: "
																+ course.getType());
														System.out.print("Confirm to Swop Index Number? (Y/N) ");
														if (in.next().equalsIgnoreCase("Y"))
														{
															group1.dropStudentFromGroup(student);
															groupStatus = group1.addStudentToGroup((Student) peer);
															student.replaceGroup(group1, group2);
															student.notifyMe(course.getId(), group2.getIndexNo(),
																	notify, groupStatus);

															groupStatus = group2.addStudentToGroup(student);
															group2.dropStudentFromGroup((Student) peer);
															((Student) peer).replaceGroup(group2, group1);
															((Student) peer).notifyMe(course.getId(),
																	group1.getIndexNo(), notify, groupStatus);

															updateCourseList(courseList, group1);
															updateCourseList(courseList, group2);
															System.out.println(student.getMatricNo() + "-Index Number "
																	+ group1.getIndexNo()
																	+ " has successfully swopped with "
																	+ ((Student) peer).getMatricNo() + "-Index Number "
																	+ group2.getIndexNo());
														} else
														{
															System.out.println(student.getMatricNo() + " and "
																	+ ((Student) peer).getMatricNo()
																	+ " is not swoped");
														}
													} else
													{
														System.out.println("You are already in this group.");
													}
												} else
												{
													if (overlaps != -1)
													{
														System.out.println("Student " + student.getMatricNo()
																+ "'s Course Index Number " + overlaps
																+ " overlaps with " + in_int2 + ".");
													} else
													{
														System.out.println("Student " + ((Student) peer).getMatricNo()
																+ "'s Course Index Number " + overlaps2
																+ " overlaps with " + in_int1 + ".");
													}
												}
											} else
											{
												System.out.println("Index Number " + in_int1 + " and Index Number "
														+ in_int2 + " exist in a different Course.");
											}
										} else
										{
											System.out.println("Student " + ((Student) peer).getMatricNo()
													+ "'s Course Index Number " + in_int2 + " is in waitlist.");
										}
									} else
									{
										if (!Objects.equals(null, groupExist(courseList, in_int1)))
										{
											System.out.println("Student " + ((Student) peer).getMatricNo()
													+ " is not registerd in Index Number " + in_int2 + ".");
										} else
										{
											System.out.println("Index Number " + in_int2 + " do not exist.");
										}
									}
								} else
								{
									System.out.println("The username or password you entered is incorrect.");
								}
							} else
							{
								System.out.println("Student " + student.getMatricNo() + "'s Course Index Number "
										+ in_int1 + " is in waitlist.");
							}
						} else
						{
							if (!Objects.equals(null, groupExist(courseList, in_int1)))
							{
								System.out.println("Student " + student.getMatricNo()
										+ " is not registerd in Index Number " + in_int1 + ".");
							} else
							{
								System.out.println("Index Number " + in_int1 + " do not exist.");
							}
						}
						break;
					}
					case 7:
					{ // Update Particulars
						in_int1 = Formatter
								.getIntegerInput("\t1. Email\n" + "\t2. Phone Number\n" + "\t3. Notification Status\n"
										+ "\t4. Password\n" + "\t0. Back to Menu\n" + "Choose your option: ");

						switch (in_int1)
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
							} else
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
							} else
							{
								System.out.println(in_string + " is not valid Local Phone Number.");
							}
							break;
						}
						case 3:
						{ // Notification Status
							System.out.println("Current Notification Status: " + student.getNotification());
							Enumerator.printAll(Notification_Status.class);
							student.setNotification(Enumerator.nextEnum(Notification_Status.class));
							System.out.println("Notification Status has been updated successfully.");
							break;
						}
						case 4:
						{ // Password
							Logger logger = null;
							String pass;
							for (Logger lo : userList)
							{
								if (Objects.equals(lo.getUser(), user))
								{
									logger = lo;
								}
							}
							System.out.print("Enter your current Password: ");
							if (logger.getPassword().equals(inputPassword()))
							{
								System.out.print("Enter your new Password: ");
								logger.setPassword(inputPassword());
								System.out.println("Password has been updated successfully.");
							} else
							{
								System.out.println("Current Password entered incorrectly.");
							}
							break;
						}
						}
						break;
					}

					case 0:
					{ // Exit
						writeData(userList, courseList);
						userList = new ArrayList<Logger>();
						studentList = new ArrayList<Student>();
						courseList = new ArrayList<Course>();

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
			} else
			{
				System.out.println("The username or password you entered is incorrect.");
			}

			System.out.print("Next user login? (Y/N) ");

		} while (in.next().equalsIgnoreCase("Y"));

	}

	public static String inputPassword()
	{
		return Formatter.hashPassword(String.copyValueOf(System.console().readPassword()));
		// return Formatter.hashPassword((new Scanner(System.in)).next());
	}

	public static Comparable login(ArrayList<Logger> userList)
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
							return lo.getUser();
						} else
						{
							System.out.println("You can only access between "
									+ Formatter.getString(lo.getAccessSTime(), "dd-MM-yyyy hh:mm a") + " to "
									+ Formatter.getString(lo.getAccessETime(), "dd-MM-yyyy hh:mm a"));
							return null;
						}
					} else
					{
						return lo.getUser();
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

	public static boolean dupIndexNo(ArrayList<Course> courseList, int indexNo)
	{
		for (Course co : courseList)
		{
			for (Group gr : co.getGroups())
			{
				if (gr.getIndexNo() == indexNo)
				{
					System.out.println("Index Number " + indexNo + " has already been used.");
					return true;
				}
			}
		}
		return false;
	}

	public static Group groupExist(ArrayList<Course> courseList, int indexNo)
	{
		Group gr;
		for (Course co : courseList)
		{
			if (!Objects.equals(null, gr = co.findGroup(indexNo)))
			{
				return gr;
			}
		}
		return null;
	}

	public static Course courseExist(ArrayList<Course> courseList, String courseId)
	{
		for (Course co : courseList)
		{
			if (co.getId().equalsIgnoreCase(courseId))
			{
				return co;
			}
		}
		return null;
	}

	public static void updateCourseList(ArrayList<Course> courseList, Group group)
	{
		for (Course co : courseList)
		{
			for (int i = 0; i < co.getGroups().size(); i++)
			{
				if (co.getGroups().get(i).getIndexNo() == group.getIndexNo())
				{
					co.getGroups().set(i, group);
					break;
				}
			}
		}
	}

	public static Session addSession(Group gr, int index)
	{
		Scanner in = new Scanner(System.in);
		Session session;
		if (index == -1)
		{
			session = new Session();
		} else
		{
			session = gr.getSessions().get(index);
		}
		// Type
		Enumerator.printAll(Session_Type.class);
		session.setType(Enumerator.nextEnum(Session_Type.class));
		// Group
		System.out.print("Enter Group name: ");
		session.setGroup(in.next());
		// Day
		Enumerator.printAll(Day.class);
		session.setDay(Enumerator.nextEnum(Day.class));
		// Start Time
		System.out.println("Enter Start Time: ");
		session.setSTime(Formatter.enterDateTime("time"));
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

		for (Session se : gr.getSessions())
		{
			if (Objects.equals(se, session))
			{
				continue;
			}
			if (sessionOverlaps(se, session))
			{
				return null;
			}
		}

		return session;
	}

	public static void updateStudentGroups(ArrayList<Student> studentList, Group group)
	{
		for (Student st : studentList)
		{
			for (int i = 0; i < st.getCourseGroups().size(); i++)
			{
				if (st.getCourseGroups().get(i).getIndexNo() == group.getIndexNo())
				{
					st.getCourseGroups().set(i, group);
				}
			}
		}
	}

	public static int isOverlap(Student st, Group gr, Group exclude)
	{
		for (Group group : st.getCourseGroups())
		{
			for (Session sCur : group.getSessions())
			{
				for (Session sNew : gr.getSessions())
				{
					if (sessionOverlaps(sCur, sNew))
					{
						if (exclude != null)
						{
							if (group.getIndexNo() == exclude.getIndexNo())// &&
																			// exclude.getIndexNo()
																			// !=
																			// gr.getIndexNo()
							{
								break;
							}
						}
						return group.getIndexNo();
					}
				}
			}
		}
		return -1;
	}

	public static boolean sessionOverlaps(Session se1, Session se2)
	{
		if (se1.getDay().equals(se2.getDay()) && (se1.getAlternateWeek().equals(se2.getAlternateWeek())
				|| se1.getAlternateWeek().equals(Alternate_Week.NONE)
				|| se2.getAlternateWeek().equals(Alternate_Week.NONE)))
		{
			if ((se1.getSTime().equals(se2.getSTime()) && se1.getETime()
					.equals(se2.getETime())) /*
												 * sCur [__] sNew [__]
												 */
					|| (se1.getSTime().before(se2.getSTime()) && se1.getETime()
							.after(se2.getSTime())) /*
													 * sCur [__] sNew [__]
													 */
					|| (se1.getSTime().after(se2.getETime()) && se1.getETime()
							.before(se2.getETime())) /*
														 * sCur [__] sNew [__]
														 */
					|| (se1.getSTime().after(se2.getSTime()) && se1.getETime().before(
							se2.getETime())) /*
												 * sCur [__] sNew [______]
												 */
					|| (se1.getSTime().before(se2.getSTime()) && se1.getETime().after(
							se2.getETime()))) /*
												 * sCur [______] sNew [__]
												 */
			{
				return true;
			}
		}
		return false;
	}
}
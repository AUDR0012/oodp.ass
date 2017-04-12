package audrey;

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
		int option = -10, in_int = -10, in_int2 = -10, overlaps, overlaps2;
		String in_string;
		boolean found;

		//Developer.addData(userList, studentList, courseList);

		//readData(userList, studentList, courseList);
		do
		{
			if(userList.isEmpty() || studentList.isEmpty() || courseList.isEmpty())
			{
				readData(userList, studentList, courseList);
			}
			Menu.printHeader(null);
			user = login(userList);

				if (user instanceof Admin)
				{
					do
					{
						
						boolean valid = false;
						do
						{
							try {  
								Menu.printMenu(user);
								option = in.nextInt();
								valid = true;
								}catch (Exception e) {
									System.out.println("Invalid value!");
									in.next(); // this consumes the invalid token
								} 
						}while(!valid);
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
								newStudent.setGender(Enumerator.nextEnum(Gender.class, in));
								// Matric Number
								System.out.print("Enter Matric Number: ");
								newStudent.setMatricNo(in.next());
								// Study Year
								newStudent.setStudyYear(Formatter.getIntegerInput("Enter Study Year"));
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
								course.setType(Enumerator.nextEnum(Course_Type.class, in));
								// Credit
								course.setCredit(Formatter.getIntegerInput("Enter Number of Credits"));
								// Number of Groups
								//System.out.print("Enter the Number of Groups: ");
								in_int = Formatter.getIntegerInput("Enter the Number of Groups: ");
								// Number of Sessions
								System.out.print("Enter the Number of Sessions: ");
								in_int2 = Formatter.getIntegerInput("Enter the Number of Sessions: ");

								while (course.getGroups().size() < in_int)
								{
									System.out.println("Group #" + course.getGroups().size()+1);
									group = new Group();
									// Index Number
									do
									{
										group.setIndexNo(Formatter.getIntegerInput("Enter Index Number: "));
									} while (course.dupIndexNo(group.getIndexNo()));
									// Vacancy

									in_int2 = Formatter.getIntegerInput("Enter Size of Group");
									while (group.getSessions().size() < in_int2)
									{
										System.out.println("Group #" + course.getGroups().size()+1 + " Session #"
												+ group.getSessions().size()+1);
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
										System.out.println("Enter Start Time: ");
										session.setSTime(Formatter.enterDateTime(in, "time"));
										// Hours
										System.out.print("Enter Number of Hours: ");
										session.setETime(Formatter.addHours(session.getSTime(), in.nextInt()));
										// Venue
										System.out.print("Enter Venue: ");
										in.nextLine();
										session.setVenue(in.nextLine());
										// Remark
										System.out.print("Enter Remarks: ");
										session.setRemark(in.nextLine());

										group.addSession(session);
									}
									course.addGroup(group);
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
										int layer1 = Formatter.getIntegerInput(
												"   1. Update Course Name\n"
												+ "   2. Group\n"
												+ "   0. Back to Menu\n"
												+ "Enter your choice 0-2: ");
									
									if(layer1 == 1)//1
									{
										//Edit Course Name Done
										System.out.println("Current Course Name: " + course.getName());
										System.out.print("New Course Name: ");
										in.nextLine();
										course.setName(in.nextLine());
										System.out.println("Course Name has been changed to :" + course.getName());
									}
									else if(layer1 == 2)//2 Edit Course Groups
									{
										do
										{
										int layer2 = Formatter.getIntegerInput(
												"   \t1. Add new Group\n"
												+ "   \t2. Edit Group\n"
												+ "   \t3. Delete Group\n"
												+ "   \t0. Back\n"
												+ "Enter your choice 0-3: ");
										if(layer2 == 1) //2-1 Add Group
										{
											Group nGroup = new Group();
											nGroup.setIndexNo(Formatter.getIntegerInput("Enter Group ID: "));
											int vac;
											do
											{
												vac = Formatter.getIntegerInput("Enter Number of Vacancies in Group: ");
												if(vac < 0)
												{
													System.out.println("Vacancy need to be 0 or more");
												}
											}while(vac <0);
											nGroup.setVacancy(vac);
											course.addGroup(nGroup);
											System.out.println("Group has been added");
										}//End 2-1 Add Group
										else if(layer2 == 2) //2-2 Edit Group
										{
											do
											{
											System.out.println("Current Groups in CourseID: " + in_string);
											for(Group nGroup : course.getGroups())
											{
												System.out.println(course.getGroups().indexOf(nGroup)+1 + ". " + nGroup.getIndexNo());
											}
											System.out.println("0. Back");
											int editChoice = Formatter.getIntegerInput("Key in your choice 0-" + course.getGroups().size() + ": ");
											if(editChoice > 0 && editChoice <= course.getGroups().size() + 1)
											{
												Group targetGroup = course.getGroups().get(editChoice-1);
												int layer3 = Formatter.getIntegerInput(
														"   \t\t1. Edit Group Vacancy\n"
																+ "   \t\t2. Add Session\n"
																+ "   \t\t3. Edit Session\n"
																+ "   \t\t4. Delete Session\n"
																+ "   \t\t0. Back\n"
																+ "Enter your choice 0-4: ");
												if(layer3 == 1) //2-2-1 Edit Vacancy
												{
													System.out.println("Current Vacancy in group " + targetGroup.getIndexNo() + ": " + targetGroup.getVacancy());
													
													int vac;
													do
													{
														vac = Formatter.getIntegerInput("Enter the new number of vacancy for the group:");
														if(vac < 0)
														{
															System.out.println("Vacancy need to be 0 or more");
														}
													}while(vac <0);
													targetGroup.setVacancy(vac);
													
													//Update on student side
													for(Student stu : studentList)
													{
														for(Group g : stu.getCourseGroups())
														{
															if(g.getIndexNo() == targetGroup.getIndexNo())
															{
																targetGroup.setVacancy(targetGroup.getVacancy());
																break;
															}
														}
													}
													
													System.out.println("Vacancy has been changed to :" + targetGroup.getVacancy());
												}//End 2-2-1 Edit Vacancy
												else if(layer3 == 2) //2-2-2 Add Session
												{
													Session nSession = new Session();
													Enumerator.printAll(Session_Type.class);
													nSession.setType(Enumerator.nextEnum(Session_Type.class, in));
													// Group
													System.out.print("Enter Group name: ");
													nSession.setGroup(in.next());
													// Day
													Enumerator.printAll(Day.class);
													nSession.setDay(Enumerator.nextEnum(Day.class, in));
													// Start Time
													System.out.println("Enter Start Time: ");
													nSession.setSTime(Formatter.enterDateTime(in, "time"));
													// Hours
													nSession.setETime(Formatter.addHours(nSession.getSTime(), Formatter.getIntegerInput("Enter Number of Hours: ")));
													// Venue
													System.out.print("Enter Venue: ");
													in.nextLine();
													nSession.setVenue(in.nextLine());
													// Remark
													System.out.print("Enter Remarks: ");
													nSession.setRemark(in.nextLine());

													targetGroup.addSession(nSession);
													
													for(Student stu : studentList)
													{
														for(Group g : stu.getCourseGroups())
														{
															if(g.getIndexNo() == targetGroup.getIndexNo())
															{
																g.addSession(nSession);
																break;
															}
														}
													}
													System.out.println("Session has been added");
												}//End 2-2-2 Add Session
												else if(layer3 == 3) //2-2-3 Edit Session
												{
													//Display all sessions in the group
													System.out.println("Current Session in CourseID: " + in_string + " GroupID: " + targetGroup.getIndexNo());
													for(Session eSession : targetGroup.getSessions())
													{
														System.out.println(targetGroup.getSessions().indexOf(eSession)+1 + ". " + eSession.getType() + " Venue: " + eSession.getVenue() + "Time: " + eSession.getDay() + eSession.getSTime() + eSession.getETime());
													}
													System.out.println("0. Back");
													int editChoice2 = Formatter.getIntegerInput("Key in your choice 0-" + targetGroup.getSessions().size() + ": ");
													if(editChoice2 > 0 && editChoice2 <= targetGroup.getSessions().size() + 1)
													{
														
														Enumerator.printAll(Session_Type.class);
														targetGroup.getSessions().get(editChoice2-1).setType(Enumerator.nextEnum(Session_Type.class, in));
														// Group
														System.out.print("Enter Group name: ");
														targetGroup.getSessions().get(editChoice2-1).setGroup(in.next());
														// Day
														Enumerator.printAll(Day.class);
														targetGroup.getSessions().get(editChoice2-1).setDay(Enumerator.nextEnum(Day.class, in));
														// Start Time
														System.out.println("Enter Start Time: ");
														targetGroup.getSessions().get(editChoice2-1).setSTime(Formatter.enterDateTime(in, "time"));
														// Hours
														targetGroup.getSessions().get(editChoice2-1).setETime(Formatter.addHours(targetGroup.getSessions().get(editChoice2-1).getSTime(), Formatter.getIntegerInput("Enter Number of Hours: ")));
														// Venue
														System.out.print("Enter Venue: ");
														in.nextLine();
														targetGroup.getSessions().get(editChoice2-1).setVenue(in.nextLine());
														// Remark
														System.out.print("Enter Remarks: ");
														targetGroup.getSessions().get(editChoice2-1).setRemark(in.nextLine());
														
														for(Student stu : studentList)
														{
															for(Group g : stu.getCourseGroups())
															{
																if(g.getIndexNo() == targetGroup.getIndexNo())
																{
																	g.getSessions().set(editChoice2-1, targetGroup.getSessions().get(editChoice2-1));
																	break;
																}
															}
														}
														
														System.out.println("Session has been updated");
													}
													else if(editChoice2 == 0)
													{
														//break;
													}
													else
													{
														System.out.println("Invalid Choice");
													}
												}//End 2-2-3 Edit Session
												else if(layer3 == 4) //2-2-4 Delete Session
												{
													//Display all sessions in the group
													System.out.println("Current Session in CourseID: " + in_string + " GroupID: " + targetGroup.getIndexNo());
													for(Session dSession : targetGroup.getSessions())
													{
														System.out.println(targetGroup.getSessions().indexOf(dSession)+1 + ". " + dSession.getType() + " Venue: " + dSession.getVenue() + "Time: " + dSession.getDay() + dSession.getSTime() + dSession.getETime());
													}
													System.out.println("0. Back");
													int deleteChoice = Formatter.getIntegerInput("Key in your choice 0-" + targetGroup.getSessions().size() + ": ");
													if(deleteChoice > 0 && deleteChoice <= targetGroup.getSessions().size() + 1)
													{
														targetGroup.getSessions().remove(deleteChoice-1);
														System.out.println("The Session has been deleted");
													}
													else if(deleteChoice == 0)
													{
														//break;
													}
													else
													{
														System.out.println("Invalid Choice");
													}
													
												}//End 2-2-4 Delete Session
												else if(layer3 == 0) //2-2-0 Up One Layer
												{
													break;
												}//End 2-2-0 Up One Layer
												
											}
											else if(editChoice == 0)
											{
												break;
											}
											else
											{
												System.out.println("Invalid Choice of Group");
											}
											}while(true);
										}//End 2-2 Edit Group
										else if(layer2 == 3) //2-3 Delete Group
										{
											//Display all group in the course
											System.out.println("Current Groups in CourseID: " + in_string);
											for(Group nGroup : course.getGroups())
											{
												System.out.println(course.getGroups().indexOf(nGroup)+1 + ". " + nGroup.getIndexNo());
											}
											System.out.println("0. Back");
											int deleteChoice = Formatter.getIntegerInput("Key in your choice 0-" + course.getGroups().size() + ": ");
											if(deleteChoice > 0 && deleteChoice <= course.getGroups().size() + 1)
											{
												int index = course.getGroups().get(deleteChoice-1).getIndexNo();
												course.getGroups().remove(deleteChoice-1);
												for(Student stu : studentList)
												{
													for(Group g : stu.getCourseGroups())
													{
														if(g.getIndexNo() == index)
														{
															stu.getCourseGroups().remove(g);
															break;
														}
													}
												}
												System.out.println("The Group has been deleted");
											}
											else if(deleteChoice == 0)
											{
												//break;
											}
											else
											{
												System.out.println("Invalid Choice");
											}
										}//End 2-3 Delete Group
										else if(layer2 == 0) //2-0 Up One Layer
										{
											break;
										}//End 2-0 Up One Layer
										}while(true);
									}
									else if(layer1 == 0) //0 Back to main Menu
									{
										break;
										//Exit
									}//End 0 Back to main menu
									}while(true);
								}//End Compare Course Exist
								else
								{
									System.out.println("Course Id " + in_string.toUpperCase() + " does not exist.");
								}
								break;
							}
							case 5:
							{ // Check Available Slot for an Index Number
								
								valid = false;
								do
								{
									try {  
										
										//System.out.print("Enter Index Number of the Course: ");
										in_int = Formatter.getIntegerInput("Enter Index Number of the Course");
										valid = true;
										}catch (Exception e) {
											System.out.println("Invalid value!");
											in.next(); // this consumes the invalid token
										} 
								}while(!valid);
								if (!Objects.equals(null, (group = groupExist(courseList, in_int))))
								{
									System.out.println(
											"Index Number " + in_int + " has " + group.getVacancy() + " vacancies.");
								}
								else
								{
									System.out.println("Index Number " + in_int + " is not found.");
								}
								break;
							}
							case 6:
							{ // Print Student List by Index Number
								
								in_int = Formatter.getIntegerInput("Enter Index Number of the Course");

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
										}
										else
										{
											System.out
													.println("Course Id " + in_string.toUpperCase() + " has no students.");
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
				}
				else if (user instanceof Student)
				{
					Student student = (Student) user;
					do
					{
						boolean valid = false;
						do
						{
							try {  
								Menu.printMenu(user);
								option = in.nextInt();
								valid = true;
								}catch (Exception e) {
									System.out.println("Invalid value!");
									in.next(); // this consumes the invalid token
								} 
						}while(!valid);
						//option = in.nextInt();
						switch (option)
						{
							case 1:
							{ // Add Course
								
								in_int = Formatter.getIntegerInput("Enter Index Number of the Course: ");
								
								
								Boolean courseAlreadyExist = false;
								if (!Objects.equals(null, (group = groupExist(courseList, in_int))))
								{
									course = group.getCourse(courseList);

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
										if ((overlaps = student.isOverlap(group,null)) == -1)
										{
											System.out.println(Formatter.tabs(40, "", "Index Number: " + group.getIndexNo())
													+ "Course: " + course.getId());
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
									in_int = Formatter.getIntegerInput("Choose the Course to drop: ");
									

									if (in_int >= 1 && in_int <= student.getCourseGroups().size())
									{
										group = student.getCourseGroups().get(in_int - 1);
										course = group.getCourse(courseList);

										System.out.println(Formatter.tabs(40, "", "Index Number: " + group.getIndexNo())
												+ "Course: " + course.getId());
										course.printGroup(group, PARSE_LENGTH, PARSE_DELIMITER);
										System.out.println(Formatter.tabs(50, "", "Course Type: " + course.getType())
												+ "Status: " + group.findStudent(student, "status"));

										System.out.print("Confirm to Drop Course? (Y/N) ");

										if (in.next().equalsIgnoreCase("Y"))
										{
											if (group.dropStudentFromGroup(student))
											{
												group.updateWaitlist(student, course, notify);
											}
											updateCourseList(courseList, group);
											student.getCourseGroups().remove(group);
										}
										else
										{
											System.out.println("Index Number " + in_int + " is not dropped.");
										}
									}
									else
									{
										if (in_int != 0)
										{
											System.out.println("Option entered is invalid.");
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
								in_int = Formatter.getIntegerInput("Enter Index Number of the Course: ");

								do
								{
									if (!Objects.equals(null, (group = groupExist(courseList, in_int))))
									{
										course = group.getCourse(courseList);
										System.out.println(Formatter.tabs(40, "", "Index Number: " + group.getIndexNo())
												+ "Course: " + course.getId());
										course.printGroup(group, PARSE_LENGTH, PARSE_DELIMITER);
										System.out.println(Formatter.tabs(50, "", "Places Available: " + group.getVacancy())
												+ "Length of WaitList: " + group.getWaitlist().size());
									}
									else
									{
										System.out.println("Index Number " + in_int + " do not exist.");
									}

									in_int = Formatter.getIntegerInput("\nCheck Vacancy of another Index Number\nIndex Number (or 0. Back to Menu): ");
								} while (in_int != 0);
								break;
							}
							case 5:
							{ // Change Index Number of Course
								in_int = Formatter.getIntegerInput("Enter Current Index Number of the Course: ");

								if (!Objects.equals(null, (group = student.findGroup(in_int))))
								{
									if (Enumerator.string(Group_Status.REGISTERED)
											.equals(group.findStudent(student, "status")))
									{

										in_int2 = Formatter.getIntegerInput("Enter New Index Number of the Course");

										if (in_int != in_int2)
										{
											course = group.getCourse(courseList);
											if (!Objects.equals(null, (group2 = course.findGroup(in_int2))))
											{
												if (group2.getVacancy() > 0)
												{
													if ((overlaps = student.isOverlap(group2, group)) == -1)
													{
														if(group2.getIndexNo() != group.getIndexNo())
														{
															System.out.println("Subject: " + course.getId());
															System.out.println(Formatter.tabs(100, "",
																"Current Index Number: "
																		+ String.valueOf(group.getIndexNo()))
																+ "New Index Number: "
																+ String.valueOf(group2.getIndexNo()));
															course.print2Groups(group, group2, PARSE_LENGTH, PARSE_DELIMITER);
															System.out.println(
																Formatter.tabs(100, "", "Subject Type: " + course.getType())
																		+ "Status: "
																		+ Enumerator.string(Group_Status.REGISTERED));
															System.out.print("Confirm to Change Index Number? (Y/N) ");
															if (in.next().equalsIgnoreCase("Y"))
															{
																group.dropStudentFromGroup(student);
																group2.addStudentToGroup(student);
																student.replaceGroup(group, group2);
															
																updateCourseList(courseList, group);
																System.out.println("Index Number " + group.getIndexNo()
																		+ " has been changed to " + group2.getIndexNo());
															}
															else
															{
																System.out.println("Index Number " + in_int + " is not changed.");
															}
														}
														else
														{
															System.out.println("You are already in this group.");
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
													System.out.println(
															"Index Number " + in_int2 + " exist in a different Course.");
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
								in_int = Formatter.getIntegerInput("Enter your Index Number: ");
								
								if (!Objects.equals(null, (group = student.findGroup(in_int))))
								{
									if (Enumerator.string(Group_Status.REGISTERED)
											.equals(group.findStudent(student, "status")))
									{
										System.out.println("Please ask your Peer to Log in");
										if (!Objects.equals(null, (peer = login(userList)))
												&& peer instanceof Student)
										{
											valid = false;
											do
											{
												try {  
													//System.out.print("Enter Peer's Index Number: ");
													in_int2 = Formatter.getIntegerInput("Enter Peer's Index Number: ");
													valid = true;
													}catch (Exception e) {
														System.out.println("Invalid value!");
														in.next(); // this consumes the invalid token
													} 
											}while(!valid);


											if (!Objects.equals(null, (group2 = ((Student) peer).findGroup(in_int2))))
											{
												if (Enumerator.string(Group_Status.REGISTERED)
														.equals(group2.findStudent(((Student) peer), "status")))
												{
													if (Objects.equals((course = group.getCourse(courseList)),
															group2.getCourse(courseList)))
													{
														if ((overlaps = student.isOverlap(group2,group)) == -1
																&& (overlaps2 = ((Student) peer).isOverlap(group,group2)) == -1)
														{
															if(group.getIndexNo() != group2.getIndexNo())
															{
																System.out.println("Subject: " + course.getId());
																System.out.println(
																	Formatter.tabs(100, "", "Student #1 ") + "Student #2");
																System.out.println(Formatter.tabs(100, "",
																	"Matric Number: " + student.getMatricNo()
																			+ " Index Number: " + group.getIndexNo())
																	+ "Matric Number: " + ((Student) peer).getMatricNo()
																	+ " Index Number: " + group2.getIndexNo());
																course.print2Groups(group, group2, PARSE_LENGTH,
																	PARSE_DELIMITER);
																System.out.println(Formatter.tabs(100, "",
																	"Subject Type: " + course.getType()) + "Subject Type: "
																	+ course.getType());
																System.out.print("Confirm to Swop Index Number? (Y/N) ");
																if (in.next().equalsIgnoreCase("Y"))
																{
																	group.dropStudentFromGroup(student);
																	group.addStudentToGroup((Student) peer);
																	student.replaceGroup(group, group2);
																
																	group2.addStudentToGroup(student);
																	group2.dropStudentFromGroup((Student) peer);
																	((Student) peer).replaceGroup(group2, group);
																
																	updateCourseList(courseList, group);
																	updateCourseList(courseList, group2);
																	System.out.println(student.getMatricNo() + "-Index Number "
																		+ group.getIndexNo()
																		+ " has successfully swopped with "
																		+ ((Student) peer).getMatricNo() + "-Index Number "
																		+ group2.getIndexNo());
																}
																else
																{
																	System.out.println(student.getMatricNo() + " and "
																		+ ((Student) peer).getMatricNo()
																		+ " is not swoped");
																}
															}
															else
															{
																System.out.println("You are already in this group.");
															}
														}
														else
														{
															if (!Objects.equals(null, group))
															{
																System.out.println("Student " + student.getMatricNo()
																		+ "'s Course Index Number " + group.getIndexNo()
																		+ " overlaps with " + in_int2 + ".");
															}
															else
															{
																System.out.println("Student "
																		+ ((Student) peer).getMatricNo()
																		+ "'s Course Index Number " + group2.getIndexNo()
																		+ " overlaps with " + in_int + ".");
															}
														}
													}
													else
													{
														System.out.println("Index Number " + in_int + " and Index Number "
																+ in_int2 + " exist in a different Course.");
													}
												}
												else
												{
													System.out.println("Student " + ((Student) peer).getMatricNo()
															+ "'s Course Index Number " + in_int2 + " is in waitlist.");
												}
											}
											else
											{
												if (!Objects.equals(null, groupExist(courseList, in_int)))
												{
													System.out.println("Student " + ((Student) peer).getMatricNo()
															+ " is not registerd in Index Number " + in_int2 + ".");
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
										System.out.println("Student " + student.getMatricNo() + "'s Course Index Number "
												+ in_int + " is in waitlist.");
									}
								}
								else
								{
									if (!Objects.equals(null, groupExist(courseList, in_int)))
									{
										System.out.println("Student " + student.getMatricNo()
												+ " is not registerd in Index Number " + in_int + ".");
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
								
								valid = false;
								do
								{
									try {  
										System.out.println("   1. Email");
										System.out.println("   2. Phone Number");
										System.out.println("   3. Notification Status");
										System.out.println("   4. Password");
										System.out.println("   0. Back to Menu");
										System.out.print("Choose your option: ");
										in_int = in.nextInt();
										valid = true;
										}catch (Exception e) {
											System.out.println("Invalid value!");
											in.next(); // this consumes the invalid token
										} 
								}while(!valid);
								

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
				}
				else
				{
					System.out.println("The username or password you entered is incorrect.");
				}

			System.out.print("Next user login? (Y/N) ");

		} while (in.next().equalsIgnoreCase("Y"));
	}

	public static String inputPassword()
	{
		// return
		// Formatter.hashPassword(String.copyValueOf(System.console().readPassword()));
		return Formatter.hashPassword((new Scanner(System.in)).next());
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
						}
						else
						{
							System.out.println("You can only access between "
									+ Formatter.getString(lo.getAccessSTime(), "dd-MM-yyyy hh:mm a") + " to "
									+ Formatter.getString(lo.getAccessETime(), "dd-MM-yyyy hh:mm a"));
							return null;
						}
					}
					else
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

	public static Group groupExist(ArrayList<Course> courseList, int indexNo)
	{
		Group gr;
		for (Course co : courseList)
		{
			if (!Objects.equals(null, (gr = co.findGroup(indexNo))))
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
	

}
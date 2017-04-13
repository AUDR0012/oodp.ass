package oodp_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import oodp_project.Enumerator.*;

/**
 * Represents a student enrolled in the school.
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Student implements Comparable, Serializable
{
	/**
	 * The first and last name of this Student
	 */
	private String name;

	/**
	 * The gender of this Student
	 */
	private Gender gender;

	/**
	 * The matriculation number of this Student
	 */
	private String matricNo;

	/**
	 * The study year of this Student
	 */
	private int studyYear;

	/**
	 * The email address of this Student
	 */
	private String email;

	/**
	 * The date of birth of this Student
	 */
	private Date dob;

	/**
	 * The nationality of this Student
	 */
	private String nationality;

	/**
	 * The mobile number of this Student
	 */
	private String phoneNo;

	/**
	 * The choice of notification of this Student
	 */
	private Notification_Status notification;

	/**
	 * The course groups this Student is enrolled in
	 */
	private ArrayList<Group> courseGroups;

	public Student()
	{
		this.name = null;
		this.gender = null;
		this.matricNo = null;
		this.studyYear = 0;
		this.email = null;
		this.dob = null;
		this.nationality = null;
		this.phoneNo = null;
		this.notification = null;
		this.courseGroups = new ArrayList<Group>();
	}

	public Student(String name, Gender gender, String matricNo, int studyYear, String email, Date dob,
			String nationality, String phoneNo, Notification_Status notification)
	{
		this.name = name;
		this.gender = gender;
		this.matricNo = matricNo;
		this.studyYear = studyYear;
		this.email = email;
		this.dob = dob;
		this.nationality = nationality;
		this.phoneNo = phoneNo;
		this.notification = notification;
		this.courseGroups = new ArrayList<Group>();
	}

	/**
	 * Display the courses that are registered by the student
	 * 
	 * 
	 * @param couseList
	 *            List of all existing course
	 * @param length
	 *            For formatting
	 * @param delimiter
	 *            To seperate the text using it
	 */
	public void printCourses(ArrayList<Course> courseList, int length, String delimiter)
	{
		int total_au = 0;
		String bar = Menu.getBar(97, "=");
		System.out.println(bar + "\n" + Menu.getTableHeader(length, delimiter, "course") + "\n" + bar);
		for (Group gr : courseGroups)
		{
			for (Course co : courseList)
			{
				if (!Objects.equals(null, co.findGroup(gr.getIndexNo())))
				{
					co.printCourse(gr, length, delimiter, this);
					System.out.println();
					total_au += co.getCredit();
					break;
				}
			}
		}
		System.out.println(bar);
		System.out.println("Total AU Registered: " + total_au);
	}

	/**
	 * Display the courses
	 * 
	 * @param couseList
	 *            List of all existing course
	 * @param length
	 *            For formatting
	 * @param delimiter
	 *            To seperate the text using it
	 */
	public void listCourses(ArrayList<Course> courseList, int length, String delimiter)
	{
		int i = 1;
		Course co;
		for (Group gr : courseGroups)
		{
			if (!Objects.equals(null, (co = gr.getCourse(courseList))))
			{
				System.out.println(
						"\t" + i++ + ". " + gr.getIndexNo() + " " + co.getId() + " " + gr.findStudent(this, "status"));
			}
		}
		System.out.println("\t0. Back to Menu");
	}

	/**
	 * Search through the group in students
	 * 
	 * @param indexNo
	 *            Pointer to get the specified group
	 * @return Group Return the group once it is found
	 */
	public Group findGroup(int indexNo)
	{
		for (Group gr : courseGroups)
		{
			if (gr.getIndexNo() == indexNo)
			{
				return gr;
			}
		}
		return null;
	}

	public void replaceGroup(Group oldGroup, Group newGroup)
	{
		for (int i = 0; i < courseGroups.size(); i++)
		{
			if (courseGroups.get(i).getIndexNo() == oldGroup.getIndexNo())
			{
				courseGroups.set(i, newGroup);
			}
		}
	}

	public void printStudent(int length, String delimiter)
	{
		System.out.println(delimiter + Formatter.tabs(length * 3, delimiter, this.getName())
				+ Formatter.tabs(length * 1, delimiter, this.getGender())
				+ Formatter.tabs(length * 2, delimiter, this.getNationality()));
	}

	public void notifyMe(String courseId, int indexNo, Notifier notify, Group_Status type)
	{
		if (notification.equals(Notification_Status.SMS))
		{
			notify.sendSMS(phoneNo);
		} else
		{
			notify.sendEmail(email, courseId, indexNo, type);
		}
	}

	@Override
	public int compareTo(Object arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getGender()
	{
		return Enumerator.string(gender);
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	public String getMatricNo()
	{
		return matricNo;
	}

	public void setMatricNo(String matricNo)
	{
		this.matricNo = matricNo;
	}

	public int getStudyYear()
	{
		return studyYear;
	}

	public void setStudyYear(int studyYear)
	{
		this.studyYear = studyYear;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public String getNationality()
	{
		return nationality;
	}

	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}

	public String getPhoneNo()
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}

	public String getNotification()
	{
		return Enumerator.string(notification);
	}

	public void setNotification(Notification_Status notification)
	{
		this.notification = notification;
	}

	public ArrayList<Group> getCourseGroups()
	{
		return courseGroups;
	}

	public void setCourseGroups(ArrayList<Group> courseGroups)
	{
		this.courseGroups = courseGroups;
	}
}

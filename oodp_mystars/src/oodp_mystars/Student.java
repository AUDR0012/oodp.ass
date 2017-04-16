package oodp_mystars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import oodp_mystars.Enumerator.*;

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

	private ArrayList<Integer> registeredGroup;

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
		this.registeredGroup = new ArrayList<Integer>();
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
		this.registeredGroup = new ArrayList<Integer>();
	}

	public void printStudent(String delimiter)
	{
		System.out.print(delimiter + Formatter.tabs(3, delimiter, this.getName())
				+ Formatter.tabs(1, delimiter, this.getGender())
				+ Formatter.tabs(2, delimiter, this.getNationality()));
	}

	public int findGroup(int indexNo)
	{
		for (int gr : registeredGroup)
		{
			if (gr == indexNo)
			{
				return gr;
			}
		}
		return -1;
	}

	public void listCourses(ArrayList<Course> courseList)
	{
		int i = 1;
		Course co;
		for (int rg : registeredGroup)
		{
			for (Course course : courseList)
			{
				if (course.findGroup(rg) != null)
				{
					System.out.println("\t" + i++ + ". " + course.findGroup(rg).getIndexNo() + " " + course.getId()
							+ " " + course.findGroup(rg).findStudent(this.matricNo, "status"));
				}
			}

		}
		System.out.println("\t0. Back to Menu");
	}

	public void printCourses(ArrayList<Course> courseList, String delimiter)
	{
		int total_au = 0;
		String bar = Menu.getBar(13, "="), header = Menu.getTableHeader(delimiter, "coursegroup");
		System.out.println(bar + "\n" + header + "\n" + bar);
		for (int gr : registeredGroup)
		{
			for (Course co : courseList)
			{
				if (!Objects.equals(null, co.findGroup(gr)))
				{
					co.printCourseGroup(Integer.toString(gr), delimiter, 
							(String) co.findGroup(gr).findStudent(this.getMatricNo(), "status"));
					System.out.println();
					total_au += co.getCredit();
					break;
				}
			}
		}
		System.out.println(bar);
		System.out.println("Total AU Registered: " + total_au);
	}

	public void replaceGroup(int oldGroup, int newGroup)
	{
		for (int i = 0; i < registeredGroup.size(); i++)
		{
			if (registeredGroup.get(i) == oldGroup)
			{
				registeredGroup.set(i, newGroup);
			}
		}
	}

	@Override
	public int compareTo(Object arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the gender
	 */
	public String getGender()
	{
		return Enumerator.string(gender);
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	/**
	 * @return the matricNo
	 */
	public String getMatricNo()
	{
		return matricNo;
	}

	/**
	 * @param matricNo
	 *            the matricNo to set
	 */
	public void setMatricNo(String matricNo)
	{
		this.matricNo = matricNo;
	}

	/**
	 * @return the studyYear
	 */
	public int getStudyYear()
	{
		return studyYear;
	}

	/**
	 * @param studyYear
	 *            the studyYear to set
	 */
	public void setStudyYear(int studyYear)
	{
		this.studyYear = studyYear;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the dob
	 */
	public Date getDob()
	{
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality()
	{
		return nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo()
	{
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the notification
	 */
	public String getNotification()
	{
		return Enumerator.string(notification);
	}

	/**
	 * @param notification
	 *            the notification to set
	 */
	public void setNotification(Notification_Status notification)
	{
		this.notification = notification;
	}

	/**
	 * @return the registeredGroup
	 */
	public ArrayList<Integer> getRegisteredGroup()
	{
		return registeredGroup;
	}

	/**
	 * @param registeredGroup
	 *            the registeredGroup to set
	 */
	public void setRegisteredGroup(ArrayList<Integer> registeredGroup)
	{
		this.registeredGroup = registeredGroup;
	}

}

package audrey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import audrey.Enumerator.*;

public class Student implements Comparable, Serializable {

	private String name;
	private Gender gender;
	private String matricNo;
	private int studyYear;
	private String email;
	private Date dob;
	private String nationality;
	private String phoneNo;
	private Notification_Status notification;
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

	public void printCourses(ArrayList<Course> courseList, int length, String delimiter)
	{
		int total_au = 0;
		String bar = Menu.getBar(97, "=");
		for (Group gr : courseGroups)
		{
			System.out.println(bar + "\n" + Menu.getTableHeader(length, delimiter, "course") + "\n" + bar);
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

	public void listCourses(ArrayList<Course> courseList, int length, String delimiter)
	{
		int i = 1;
		Course co;
		for (Group gr : courseGroups)
		{
			if (!Objects.equals(null, (co = gr.getCourse(courseList))))
			{
				System.out.println("\t" + i++ + ". " + gr.getIndexNo() + " " + co.getId() + " " + gr.findStudent(this, "status"));
			}
		}
		System.out.println("0. Back to Menu");
	}

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
		System.out.println(delimiter
				+ Formatter.tabs(length * 3, delimiter, this.getName())
				+ Formatter.tabs(length * 1, delimiter, this.getGender())
				+ Formatter.tabs(length * 2, delimiter, this.getNationality()));
	}

	public int isOverlap(Group gr)
	{
		for (Group group : this.getCourseGroups())
		{
			for (Session sCur : group.getSessions())
			{
				for (Session sNew : gr.getSessions())
				{
					if (sCur.getDay().equals(sNew.getDay())
							&& sCur.getAlternateWeek() == sNew.getAlternateWeek()
							&& sCur.getAlternateWeek() != Alternate_Week.NONE)
					{
						if ((sCur.getSTime().equals(sNew.getSTime()) && sCur.getETime().equals(sNew.getETime())) /*	sCur [__]
																														sNew [__]
																													*/
								|| (sCur.getSTime().before(sNew.getSTime()) && sCur.getETime().after(sNew.getSTime())) /*	sCur [__]
																															sNew   [__]
																														*/
								|| (sCur.getSTime().after(sNew.getETime()) && sCur.getETime().before(sNew.getETime())) /*	sCur   [__]
																															sNew [__]
																														*/
								|| (sCur.getSTime().after(sNew.getSTime()) && sCur.getETime().before(sNew.getETime())) /*	sCur   [__]
																															sNew [______]
																														*/
								|| (sCur.getSTime().before(sNew.getSTime()) && sCur.getETime().after(sNew.getETime()))) /*	sCur [______]
																															sNew   [__]
																														*/
						{
							return group.getIndexNo();
						}
					}
				}
			}
		}
		return -1;
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

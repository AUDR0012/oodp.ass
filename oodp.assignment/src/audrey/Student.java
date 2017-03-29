package audrey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Student implements Comparable, Serializable {

	enum Student_Gender {
		MALE, FEMALE;
	}

	private String name;
	private Student_Gender gender;
	private String matricNo;
	private int studyYear;
	private String email;
	private Date dob;
	private String nationality;
	private String phoneNo;
	private ArrayList<Group> courseGroups;

	public Student(String name, Student_Gender gender, String matricNo, int studyYear, String email, String dob,
			String nationality, String phoneNo, ArrayList<Group> courseGroups)
	{
		this.name = name;
		this.gender = gender;
		this.matricNo = matricNo;
		this.studyYear = studyYear;
		this.email = email;
		this.dob = FormatString.getDate(dob, "dd-MM-yy");
		this.nationality = nationality;
		this.phoneNo = phoneNo;
		this.courseGroups = courseGroups;
	}

	public void printCourses(ArrayList<Course> courseList, int length, String delimiter)
	{
		int total_au = 0;
		String bar = Menu.getBar(97, "=");
		for (Group gr : courseGroups)
		{
			System.out.println(bar + "\n" + Menu.getTableHeader(length, delimiter, "column") + "\n" + bar);
			for (Course co : courseList)
			{
				if (co.findGroup(gr.getIndexNo()) != null)
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
			if ((co = gr.getCourse(courseList)) != null)
			{
				System.out.println(i++ + ". " + gr.getIndexNo() + " "
						+ co.getId() + " " + gr.findStudent(this, "status"));
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

	public void setGender(Student_Gender gender)
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

	public ArrayList<Group> getCourseGroups()
	{
		return courseGroups;
	}

	public void setCourseGroups(ArrayList<Group> courseGroups)
	{
		this.courseGroups = courseGroups;
	}
}

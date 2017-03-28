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
		return Enumerator.replaceString(gender);
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

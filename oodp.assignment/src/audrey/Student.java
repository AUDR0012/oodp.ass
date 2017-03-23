package audrey;

import java.util.Date;
import java.util.ArrayList;

public class Student implements User {

	private String username;
	private String password;

	private String name;
	private char gender;
	private String matric_no;
	private int study_year;
	private Date dob;
	private String nationality;
	private String phone_no;
	private ArrayList<Course> course_list; // how check registered or waitlist

	public Student(String username, String password, String name, char gender, String matric_no, int study_year, Date dob, String nationality, String phone_no)
	{
		this.username = username;
		this.password = password;
		
		this.name = name;
		this.gender = gender;
		this.matric_no = matric_no;
		this.study_year = study_year;
		this.dob = dob;
		this.nationality = nationality;
		this.phone_no = phone_no;
		this.course_list = null;
	}

	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return password;
	}
}

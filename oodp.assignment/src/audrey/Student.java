package audrey;

import java.util.Date;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Student implements User, Serializable {

	private String username;
	private String password;

	private String name;
	private char gender;
	private String matric_no;
	private int study_year;
	private Date dob;
	private String nationality;
	private String phone_no;
	private ArrayList<Course> course_list;

	public Student(String username, String password, String name, char gender, String matric_no, int study_year,
			String dob, String nationality, String phone_no)
	{
		this.username = username;
		this.password = password;

		this.name = name;
		this.gender = gender;
		this.matric_no = matric_no;
		this.study_year = study_year;
		// this.dob = dob;
		this.nationality = nationality;
		this.phone_no = phone_no;
		this.course_list = new ArrayList<Course>();

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
		try
		{
			this.dob = df.parse(dob);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public void print()
	{
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
		
		System.out.println(username + " : " + password + " : " + name + " : " + gender + " : " + matric_no + " : " + study_year + " : " + df.format(dob) + " : " + nationality + " : " + phone_no);
		for (Course e : course_list)
		{
			e.print();
		}
	}
}

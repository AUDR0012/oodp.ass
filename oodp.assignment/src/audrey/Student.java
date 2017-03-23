package audrey;
import java.util.Date;

public class Student {
	
	private String name;
	private char gender;
	private String matric_no;
	private int study_year;
	private Date dob;
	private String nationality;
	private String phone_no;
	private Course[] course_list; //how check registered or waitlist
	
	public Student(String name, char gender, String matric_no, int study_year, Date dob, String nationality, String phone_no)
	{
		this.name = name;
		this.gender = gender;
		this.matric_no = matric_no;
		this.study_year = study_year;
		this.dob = dob;
		this.nationality = nationality;
		this.phone_no = phone_no;
		this.course_list = null;
	}
}

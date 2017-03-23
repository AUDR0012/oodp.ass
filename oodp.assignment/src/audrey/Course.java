package audrey;

public class Course {
	
	private String code;
	private String name;
	private int type; //0 - Core; 1 - Unrestricted; 2 - Prescribed;
	private int credit;
	private String school;
	private CourseGroup[] group_list;
	
	public Course(String code, String name, int type, int credit, String school)
	{
		this.code = code;
		this.name = name;
		this.type = type;
		this.credit = credit;
		this.school = school;
		this.group_list = null;
	}
}

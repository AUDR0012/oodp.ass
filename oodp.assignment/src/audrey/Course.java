package audrey;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

	private String code;
	private String name;
	private int type; // 0 - Core; 1 - Unrestricted; 2 - Prescribed;
	private int credit;
	private String school;
	private ArrayList<CourseGroup> group_list;

	public Course(String code, String name, int type, int credit, String school)
	{
		this.code = code;
		this.name = name;
		this.type = type;
		this.credit = credit;
		this.school = school;
		this.group_list = new ArrayList<CourseGroup>();
	}

	public void add_group(int index_no, String group_name, int vacancy)
	{
		CourseGroup cg = new CourseGroup(index_no, group_name, vacancy);
		group_list.add(cg);
	}
	
	public void print()
	{
		System.out.println(code + " : " + name + " : " + type + " : " + credit + " : " + school);
		
		for(CourseGroup e : group_list)
		{
			e.print();
		}
	}
}

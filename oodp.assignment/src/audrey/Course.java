package audrey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Course implements Serializable {

	enum Course_Type {
		CORE, CORE_ELECTIVE, GER_CORE, GER_ELECTIVE, UNRESTRICTED_ELECTIVE;
	}

	private String id;
	private String name;
	private Course_Type type;
	private int credit;
	private ArrayList<Group> groups;

	public Course(String id, String name, Course_Type type, int credit, ArrayList<Group> groups)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.credit = credit;
		this.groups = groups;
	}

	public Group findGroup(int indexNo)
	{
		for (Group gr : groups)
		{
			if (gr.getIndexNo() == indexNo)
			{
				return gr;
			}
		}
		return null;
	}
	
	public boolean findStudent(Student st)
	{
		for (Group gr : groups)
		{
			if ((boolean)gr.findStudent(st, "boolean"))
			{
				return true;
			}
		}
		return false;
	}

	public void printCourse(Group gr, int length, String delimiter, Student user)
	{
		System.out.println(delimiter + FormatString.tabString(this.getId(), length, delimiter)
				+ FormatString.tabString(String.valueOf(this.getCredit()), length - 7, delimiter)
				+ FormatString.tabString(this.getName(), length + 15, delimiter)
				+ FormatString.tabString(this.getType(), length, delimiter)
				+ FormatString.tabString(String.valueOf(gr.getIndexNo()), length, delimiter)
				+ FormatString.tabString(gr.findStudent(user, "status").toString(), length, delimiter));
	}

	public void printGroup(Group gr, int length, String delimiter)
	{
		String bar = FormatString.getBar(105, "=");
		if (gr.getSessions().size() > 0)
		{
			System.out.println(FormatString.tabString("Index Number: " + String.valueOf(gr.getIndexNo()), 40, "")
					+ "Course: " + this.getId());
			System.out.println(bar + "\n| Class Type\t| Group\t\t| Day\t\t| Time\t\t| Venue\t\t| Remark\t\t|\n" + bar);
			for (Session s : gr.getSessions())
			{
				s.print(length, delimiter);
			}
			System.out.println(bar);
		}
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return Enumerator.replaceString(type);
	}

	public void setType(Course_Type type)
	{
		this.type = type;
	}

	public int getCredit()
	{
		return credit;
	}

	public void setCredit(int credit)
	{
		this.credit = credit;
	}

	public ArrayList<Group> getGroups()
	{
		return groups;
	}

	public void setGroups(ArrayList<Group> groups)
	{
		this.groups = groups;
	}
}

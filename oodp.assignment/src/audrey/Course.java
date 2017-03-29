package audrey;

import java.io.Serializable;
import java.util.ArrayList;

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
			if ((boolean) gr.findStudent(st, "boolean"))
			{
				return true;
			}
		}
		return false;
	}

	public void printCourse(Group gr, int length, String delimiter, Student user)
	{
		System.out.print(delimiter
				+ FormatString.tabs(length * 2, delimiter, this.getId())
				+ FormatString.tabs(length * 1, delimiter, String.valueOf(this.getCredit()))
				+ FormatString.tabs(length * 3, delimiter, this.getName())
				+ FormatString.tabs(length * 2, delimiter, this.getType())
				+ FormatString.tabs(length * 1, delimiter, String.valueOf(gr.getIndexNo()))
				+ FormatString.tabs(length * 3, delimiter, gr.findStudent(user, "status").toString()));
	}

	public void printGroup(Group gr, int length, String delimiter)
	{
		String bar = Menu.getBar(97, "="), header = Menu.getTableHeader(length, delimiter, "group");
		if (gr.getSessions().size() > 0)
		{
			System.out.println(FormatString.tabs(40, "", "Index Number: " + String.valueOf(gr.getIndexNo()))
					+ "Course: " + this.getId());
			System.out.println(bar + "\n" + header + "\n" + bar);
			for (Session s : gr.getSessions())
			{
				s.printSession(length, delimiter);
				System.out.println();
			}
			System.out.println(bar);
		}
	}
	
	public void print2Groups(Group gr1, Group gr2, int length, String delimiter)
	{
		String bar = Menu.getBar(97, "="), header = Menu.getTableHeader(length, delimiter, "group");
		System.out.println("Subject: " + this.getId());
		System.out.println(FormatString.tabs(100, "", "Current Index Number: " + String.valueOf(gr1.getIndexNo()))
				+ "New Index Number: " + String.valueOf(gr2.getIndexNo()));
		System.out.println(bar + "\t" + bar
				+ "\n" + header + "\t" + header
				+ "\n" + bar + "\t" + bar);
		for (int i = 0; i < gr1.getSessions().size(); i++)
		{
			gr1.getSessions().get(i).printSession(length, delimiter); System.out.print("\t");
			gr2.getSessions().get(i).printSession(length, delimiter); System.out.println();
		}
		System.out.println(bar + "\t" + bar);
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
		return Enumerator.string(type);
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

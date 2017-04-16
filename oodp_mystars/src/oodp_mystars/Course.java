package oodp_mystars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import oodp_mystars.Enumerator.*;

/**
 * Represents a course offered by the school.
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Course implements Serializable {
	/**
	 * The Course Id of this Course
	 */
	private String id;

	/**
	 * The Course Id of this Course
	 */
	private String name;

	/**
	 * The Course Id of this Course
	 */
	private Course_Type type;

	/**
	 * The Course Id of this Course
	 */
	private int credit;

	/**
	 * The Groups within this Course
	 */
	private ArrayList<Group> groups;

	public Course()
	{
		this.id = null;
		this.name = null;
		this.type = null;
		this.credit = 0;
		this.groups = new ArrayList<Group>();
	}

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

	public boolean findStudent(String matricNo)
	{
		for (Group gr : groups)
		{
			if ((boolean) gr.findStudent(matricNo, "boolean"))
			{
				return true;
			}
		}
		return false;
	}

	public void printCourse(String delimiter)
	{
		String bar = Menu.getBar(65, "="), header = Menu.getTableHeader(delimiter, "course");
		System.out.println(bar + "\n" + header + "\n" + bar);
		System.out.println(delimiter 
				+ Formatter.tabs(2, delimiter, this.getId())
				+ Formatter.tabs(1, delimiter, String.valueOf(this.getCredit()))
				+ Formatter.tabs(3, delimiter, this.getName())
				+ Formatter.tabs(2, delimiter, this.getType()));
		System.out.println(bar);
	}

	public void printCourseGroup(String gr, String delimiter, String status)
	{
		System.out.print(delimiter 
				+ Formatter.tabs(2, delimiter, this.getId())
				+ Formatter.tabs(1, delimiter, String.valueOf(this.getCredit()))
				+ Formatter.tabs(3, delimiter, this.getName())
				+ Formatter.tabs(2, delimiter, this.getType())
				+ Formatter.tabs(1, delimiter, gr)
				+ Formatter.tabs(3, delimiter, status));
	}

	public void printGroups(int gr1, int gr2, String delimiter)
	{
		String bar = Menu.getBar(97, "="), header = Menu.getTableHeader(delimiter, "group");
		if (gr2 == -1)
		{
			System.out.println(bar + "\n" + header + "\n" + bar);

			for (Session s : this.findGroup(gr1).getSessions())
			{
				s.printSession(delimiter);
				System.out.println();
			}
			System.out.println(bar);
		}
		else
		{
			System.out.println(bar + "\t" + bar + "\n" + header + "\t" + header + "\n" + bar + "\t" + bar);
			for (Session s : this.findGroup(gr1).getSessions())
			{
				s.printSession(delimiter);
				System.out.print("\t");
				s.printSession(delimiter);
				System.out.println();
			}
			System.out.println(bar + "\t" + bar);
		}
	}

	// Print all student in current course
	public ArrayList<String> getStudentList()
	{
		ArrayList<String> studentList = new ArrayList<String>();
		for (Group g : groups)
		{
			studentList.addAll(g.getRegistered());
		}
		return studentList;
	}

	public int countStudentInGroups()
	{
		int count = 0;
		for (Group gr : groups)
		{
			count += gr.getRegistered().size();
		}
		return count;
	}

	public void addGroup(Group gr)
	{
		boolean duplicate = false;
		for (Group g : groups)
		{
			if (Objects.equals(g, gr))
			{
				duplicate = true;
				System.out.println("Group already exist.");
			}
		}
		if (!duplicate)
		{
			groups.add(gr);
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

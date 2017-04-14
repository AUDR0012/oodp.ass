package oodp_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import oodp_project.Enumerator.*;

/**
 * Represents a course offered by the school.
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Course implements Serializable
{
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
		System.out.print(delimiter + Formatter.tabs(length * 2, delimiter, this.getId())
				+ Formatter.tabs(length * 1, delimiter, String.valueOf(this.getCredit()))
				+ Formatter.tabs(length * 3, delimiter, this.getName())
				+ Formatter.tabs(length * 2, delimiter, this.getType())
				+ Formatter.tabs(length * 1, delimiter, String.valueOf(gr.getIndexNo()))
				+ Formatter.tabs(length * 3, delimiter, gr.findStudent(user, "status").toString()));
	}

	public void printGroups(Group gr1, Group gr2, int length, String delimiter)
	{
		String bar = Menu.getBar(97, "="), header = Menu.getTableHeader(length, delimiter, "group");
		if (Objects.equals(null, gr2))
		{
			System.out.println(bar + "\n" + header + "\n" + bar);
			for (Session s : gr1.getSessions())
			{
				s.printSession(length, delimiter);
				System.out.println();
			}
			System.out.println(bar);
		} else
		{
			System.out.println(bar + "\t" + bar + "\n" + header + "\t" + header + "\n" + bar + "\t" + bar);
			for (int i = 0; i < gr1.getSessions().size(); i++)
			{
				gr1.getSessions().get(i).printSession(length, delimiter);
				System.out.print("\t");
				gr2.getSessions().get(i).printSession(length, delimiter);
				System.out.println();
			}
			System.out.println(bar + "\t" + bar);
		}
	}

	public void printStudents(int length, String delimiter)
	{
		String bar = Menu.getBar(48, "="), header = Menu.getTableHeader(length, delimiter, "student");
		System.out.println(bar + "\n" + header + "\n" + bar);
		for (Group gr : groups)
		{
			for (Student st : gr.getRegistered())
			{
				st.printStudent(length, delimiter);
			}
		}
		System.out.println(bar);
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

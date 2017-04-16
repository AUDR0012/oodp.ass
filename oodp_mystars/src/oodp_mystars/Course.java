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

	/**
	 * Default Constructor
	 */
	public Course()
	{
		this.id = null;
		this.name = null;
		this.type = null;
		this.credit = 0;
		this.groups = new ArrayList<Group>();
	}

	/**
	 * Constructor with parameter
	 * 
	 * @param id
	 * @param name
	 * @param type
	 * @param credit
	 * @param groups
	 */
	public Course(String id, String name, Course_Type type, int credit, ArrayList<Group> groups)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.credit = credit;
		this.groups = groups;
	}

	/**
	 * Search for group within the course
	 * 
	 * @param indexNo
	 * @return
	 */
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

	/**
	 * Search for specified student within the course
	 * 
	 * @param matricNo
	 * @return
	 */
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

	/**
	 * Print course information
	 * 
	 * @param delimiter
	 */
	public void printCourse(String delimiter)
	{
		System.out.print(delimiter
				+ Formatter.tabs(2, delimiter, this.getId())
				+ Formatter.tabs(1, delimiter, String.valueOf(this.getCredit()))
				+ Formatter.tabs(3, delimiter, this.getName())
				+ Formatter.tabs(3, delimiter, this.getType()));
	}

	/**
	 * Print Course information
	 * 
	 * @param gr
	 * @param delimiter
	 * @param status
	 */
	public void printCourseGroup(String gr, String delimiter, String status)
	{
		System.out.print(delimiter
				+ Formatter.tabs(2, delimiter, this.getId())
				+ Formatter.tabs(1, delimiter, String.valueOf(this.getCredit()))
				+ Formatter.tabs(3, delimiter, this.getName())
				+ Formatter.tabs(3, delimiter, this.getType())
				+ Formatter.tabs(1, delimiter, gr)
				+ Formatter.tabs(3, delimiter, status));
	}

	/**
	 * Print all the group that are in the course
	 * 
	 * @param gr1
	 * @param gr2
	 * @param delimiter
	 */
	public void printGroups(int gr1, int gr2, String delimiter)
	{
		String bar = Menu.getBorder(97, "="), header = Menu.getTableHeader(delimiter, "group");
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
			System.out.println(bar + "\t\t" + bar + "\n"
					+ header + "\t\t" + header + "\n" 
					+ bar + "\t\t" + bar);
			for (Session s : this.findGroup(gr1).getSessions())
			{
				s.printSession(delimiter);
				System.out.print("\t");
				s.printSession(delimiter);
				System.out.println();
			}
			System.out.println(bar + "\t\t" + bar);
		}
	}

	/**
	 * Get the list of student in the course
	 * 
	 * @return
	 */
	public ArrayList<String> getStudentList()
	{
		ArrayList<String> studentList = new ArrayList<String>();
		for (Group g : groups)
		{
			studentList.addAll(g.getRegistered());
		}
		return studentList;
	}

	/**
	 * Get the number of student in the course
	 * 
	 * @return
	 */
	public int countStudentInGroups()
	{
		int count = 0;
		for (Group gr : groups)
		{
			count += gr.getRegistered().size();
		}
		return count;
	}

	/**
	 * Add group to the course
	 * 
	 * @param gr
	 */
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

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType()
	// public Course_Type getType()
	{
		return Enumerator.string(type);
		// return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Course_Type type)
	{
		this.type = type;
	}

	/**
	 * @return the credit
	 */
	public int getCredit()
	{
		return credit;
	}

	/**
	 * @param credit
	 *            the credit to set
	 */
	public void setCredit(int credit)
	{
		this.credit = credit;
	}

	/**
	 * @return the groups
	 */
	public ArrayList<Group> getGroups()
	{
		return groups;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(ArrayList<Group> groups)
	{
		this.groups = groups;
	}

}

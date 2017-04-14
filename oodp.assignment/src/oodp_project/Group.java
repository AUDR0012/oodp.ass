package oodp_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import oodp_project.Enumerator.*;

/**
 * Represents a group within a course.
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Group implements Serializable
{
	/**
	 * The index number of this Group
	 */
	private int indexNo;

	/**
	 * The vacancies in this Group
	 */
	private int vacancy;

	/**
	 * The sessions (lecture, tutorial, lab) in this Group
	 */
	private ArrayList<Session> sessions;

	/**
	 * The student registered in this Group
	 */
	private ArrayList<Student> registered;

	/**
	 * The students waiting to be registered in this Group
	 */
	private ArrayList<Student> waitlist;

	public Group()
	{
		this.indexNo = 0;
		this.vacancy = 0;
		this.sessions = new ArrayList<Session>();
		this.registered = new ArrayList<Student>();
		this.waitlist = new ArrayList<Student>();
	}

	public Group(int indexNo, int vacancy, ArrayList<Session> sessions)
	{
		this.indexNo = indexNo;
		this.vacancy = vacancy;
		this.sessions = sessions;
		this.registered = new ArrayList<Student>();
		this.waitlist = new ArrayList<Student>();
	}

	public Course getCourse(ArrayList<Course> courseList)
	{
		for (Course co : courseList)
		{
			if (!Objects.equals(null, co.findGroup(indexNo)))
			{
				return co;
			}
		}
		return null;
	}

	public Group_Status addStudentToGroup(Student st)
	{
		if (vacancy > 0)
		{
			registered.add(st);
			vacancy--;
			return Group_Status.REGISTERED;
		} else
		{
			waitlist.add(st);
			return Group_Status.WAITLIST;
		}
	}

	public Group_Status dropStudentFromGroup(Student st)
	{
		if (Enumerator.string(Group_Status.REGISTERED).equals(this.findStudent(st, "status")))
		{
			for (Student r : registered)
			{
				if (r.getMatricNo().equalsIgnoreCase(st.getMatricNo()))
				{
					registered.remove(r);
					break;
				}
			}
			vacancy++;
			return Group_Status.REGISTERED;
		} else
		{
			for (Student w : waitlist)
			{
				if (w.getMatricNo().equalsIgnoreCase(st.getMatricNo()))
				{
					waitlist.remove(w);
					break;
				}
			}
			return Group_Status.WAITLIST;
		}
	}

	public Student updateWaitlist()
	{
		if (waitlist.size() > 0)
		{
			vacancy--;
			Student st = waitlist.remove(0);
			registered.add(st);
			return st;
		}
		return null;
	}

	public Comparable findStudent(Student st, String returning)
	{
		Group_Status status = Group_Status.NOT_FOUND;
		for (Student s : registered)
		{
			if (s.getMatricNo().equalsIgnoreCase(st.getMatricNo()))
			{
				status = Group_Status.REGISTERED;
			}
		}
		for (Student s : waitlist)
		{
			if (s.getMatricNo().equalsIgnoreCase(st.getMatricNo()))
			{
				status = Group_Status.WAITLIST;
			}
		}
		return returning.equals("boolean") ? status != Group_Status.NOT_FOUND : Enumerator.string(status);
	}

	public void printStudents(int length, String delimiter)
	{
		String bar = Menu.getBar(48, "="), header = Menu.getTableHeader(length, delimiter, "student");
		System.out.println(bar + "\n" + header + "\n" + bar);
		for (Student st : registered)
		{
			st.printStudent(length, delimiter);
		}
		System.out.println(bar);
	}

	public int getIndexNo()
	{
		return indexNo;
	}

	public void setIndexNo(int indexNo)
	{
		this.indexNo = indexNo;
	}

	public int getVacancy()
	{
		return vacancy;
	}

	public void setVacancy(int vacancy)
	{
		this.vacancy = vacancy;
	}

	public ArrayList<Session> getSessions()
	{
		return sessions;
	}

	public void setSessions(ArrayList<Session> sessions)
	{
		this.sessions = sessions;
	}

	public ArrayList<Student> getRegistered()
	{
		return registered;
	}

	public void setRegistered(ArrayList<Student> registered)
	{
		this.registered = registered;
	}

	public ArrayList<Student> getWaitlist()
	{
		return waitlist;
	}

	public void setWaitlist(ArrayList<Student> waitlist)
	{
		this.waitlist = waitlist;
	}
}

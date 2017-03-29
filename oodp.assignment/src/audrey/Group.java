package audrey;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {

	enum Group_StudentStatus {
		REGISTERED, WAITLIST, EXEMPTED;
	}

	private int indexNo;
	private int vacancy;
	private ArrayList<Session> sessions;
	private ArrayList<Student> registered;
	private ArrayList<Student> waitlist;

	public Group(int indexNo, int vacancy, ArrayList<Session> sessions, ArrayList<Student> registered,
			ArrayList<Student> waitlist)
	{
		this.indexNo = indexNo;
		this.vacancy = vacancy;
		this.sessions = sessions;
		this.registered = registered;
		this.waitlist = waitlist;
	}
	
	public Course getCourse(ArrayList<Course> courseList)
	{
		for (Course co : courseList)
		{
			if (co.findGroup(this.getIndexNo()) != null)
			{
				return co;
			}
		}
		return null;
	}

	public void addStudentToGroup(Student st)
	{
		if (vacancy > 0)
		{
			registered.add(st);
			vacancy--;
			System.out.println("Registering " + this.getIndexNo() + ".");
		}
		else
		{
			waitlist.add(st);
			System.out.println("Adding " + this.getIndexNo() + " to waitlist.");
		}
	}

	public void dropStudentFromGroup(Student st)
	{
		if (Enumerator.string(Group_StudentStatus.REGISTERED).equals((String) findStudent(st, "status")))
		{
			registered.remove(st);
			vacancy++;
			System.out.println("Removing " + this.getIndexNo() + " from course.");

			updateWaitlist();
		}
		else
		{
			waitlist.remove(st);
			System.out.println("Removing " + this.getIndexNo() + "from waitlist.");
		}
	}

	public Comparable findStudent(Student st, String returning)
	{
		for (Student s : registered)
		{
			if (s.equals(st))
			{
				if (returning.equals("boolean"))
					return true;
				else // if (returning.equals("status"))
					return Enumerator.string(Group_StudentStatus.REGISTERED);
			}
		}
		for (Student s : waitlist)
		{
			if (s.equals(st))
			{
				if (returning.equals("boolean"))
					return true;
				else // if (returning.equals("status"))
					return Enumerator.string(Group_StudentStatus.WAITLIST);
			}
		}
		if (returning.equals("boolean"))
			return false;
		else // if (returning.equals("status"))
			return "";
	}

	public void updateWaitlist()
	{

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

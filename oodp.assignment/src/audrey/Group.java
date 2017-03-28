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

	public void addStudentToGroup(Student st)
	{
		if (vacancy > 0)
		{
			registered.add(st);
			vacancy--;
			System.out.println("Registering.");
		}
		else
		{
			waitlist.add(st);
			System.out.println("Adding to waitlist.");
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
					return Enumerator.replaceString(Group_StudentStatus.REGISTERED);
			}
		}
		for (Student s : waitlist)
		{
			if (s.equals(st))
			{
				if (returning.equals("boolean"))
					return true;
				else // if (returning.equals("status"))
					return Enumerator.replaceString(Group_StudentStatus.WAITLIST);
			}
		}
		if (returning.equals("boolean"))
			return false;
		else // if (returning.equals("status"))
			return "";
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

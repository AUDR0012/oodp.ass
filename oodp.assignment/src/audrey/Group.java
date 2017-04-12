package audrey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import audrey.Enumerator.*;

public class Group implements Serializable {

	private int indexNo;
	private int vacancy;
	private ArrayList<Session> sessions;
	private ArrayList<Student> registered;
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

	public void addStudentToGroup(Student st)
	{
		if (vacancy > 0)
		{
			registered.add(st);
			vacancy--;
			System.out.println("Registered to Index Number " + this.getIndexNo() + ".");
		}
		else
		{
			waitlist.add(st);
			System.out.println("Adding to Index Number " + this.getIndexNo() + "'s waitlist.");
		}
	}

	public boolean dropStudentFromGroup(Student st)
	{
		if (Enumerator.string(Group_Status.REGISTERED).equals(this.findStudent(st, "status")))
		{
			for(Student r : registered)
			{
				if(r.getMatricNo().equalsIgnoreCase(st.getMatricNo()))
				{
					registered.remove(r);
					break;
				}
			}
			
			//registered.remove(st);
			vacancy++;
			System.out.println("Removing " + this.getIndexNo() + " from course.");
			return true;
		}
		else
		{
			waitlist.remove(st);
			System.out.println("Removing " + this.getIndexNo() + " from waitlist.");
			return false;
		}
	}
	
	public void updateWaitlist(Student st, Course co, Notifier notify)
	{
		if (waitlist.size() > 0)
		{
			Student curSt = waitlist.get(0);
			if (curSt.getNotification().equals(Notification_Status.SMS))
			{
				notify.sendSMS(st.getPhoneNo());
			}
			else
			{
				notify.sendEmail(st.getEmail(), co.getId(), indexNo, Notifier_Type.REGISTERED);
			}
		}
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

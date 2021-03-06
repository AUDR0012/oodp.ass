package oodp_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

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
	private ArrayList<String> registered;

	/**
	 * The students waiting to be registered in this Group
	 */
	private ArrayList<String> waitlist;

	public Group()
	{
		this.indexNo = 0;
		this.vacancy = 0;
		this.sessions = new ArrayList<Session>();
		this.registered = new ArrayList<String>();
		this.waitlist = new ArrayList<String>();
	}

	public Group(int indexNo, int vacancy, ArrayList<Session> sessions)
	{
		this.indexNo = indexNo;
		this.vacancy = vacancy;
		this.sessions = sessions;
		this.registered = new ArrayList<String>();
		this.waitlist = new ArrayList<String>();
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

	public Group_Status addStudentToGroup(String matricNumber)
	{
		if (vacancy > 0)
		{
			registered.add(matricNumber);
			vacancy--;
			return Group_Status.REGISTERED;
		} else
		{
			waitlist.add(matricNumber);
			return Group_Status.WAITLIST;
		}
	}

	public Group_Status dropStudent(String matricNumber)
	{
		if (Enumerator.string(Group_Status.WAITLIST).equals(this.findStudent(matricNumber, "status")))
		{
			for (String w : waitlist)
			{
				if (w.equalsIgnoreCase(matricNumber))
				{
					waitlist.remove(w);
					break;
				}
			}
			return Group_Status.WAITLIST;
		} else
		{
			for (String r : registered)
			{
				if (r.equalsIgnoreCase(matricNumber))
				{
					registered.remove(r);
					vacancy++;
					break;
				}
			}
			return Group_Status.REGISTERED;
		}
	}

	public void updateWaitlist(ArrayList<Student> studentList)
	{
		while (waitlist.size() > 0 && vacancy > 0)
		{
			this.addStudentToGroup(this.getWaitlist().get(0));
			this.dropStudent(this.getWaitlist().get(0));

			for (Student student : studentList)
			{
				if (student.getMatricNo().equalsIgnoreCase(this.getRegistered().get(this.getRegistered().size() - 1)))
				{
					this.notify(student);
					break;
				}
			}
		}
	}

	public void notify(Student student)
	{
		if (student.getNotification().equals(Notification_Status.SMS))
		{
			Notifier.sendSMS(student.getPhoneNo());
		} else
		{
			Notifier.sendEmail(student.getPhoneNo(), "Course Index FIX", indexNo, Group_Status.REGISTERED); // Change
		}
	}

	public Comparable findStudent(String matricNo, String returning)
	{
		Group_Status status = Group_Status.NOT_FOUND;
		for (String s : registered)
		{
			if (s.equalsIgnoreCase(matricNo))
			{
				status = Group_Status.REGISTERED;
			}
		}
		for (String s : waitlist)
		{
			if (s.equalsIgnoreCase(matricNo))
			{
				status = Group_Status.WAITLIST;
			}
		}
		return returning.equals("boolean") ? status != Group_Status.NOT_FOUND : Enumerator.string(status);
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

	public ArrayList<String> getRegistered()
	{
		return registered;
	}

	public void setRegistered(ArrayList<String> registered)
	{
		this.registered = registered;
	}

	public ArrayList<String> getWaitlist()
	{
		return waitlist;
	}

	public void setWaitlist(ArrayList<String> waitlist)
	{
		this.waitlist = waitlist;
	}
}

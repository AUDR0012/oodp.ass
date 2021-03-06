package oodp_mystars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import oodp_mystars.Enumerator.*;

/**
 * Represent a group within a course
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Group implements Serializable {
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

	/**
	 * Default constructor
	 */
	public Group()
	{
		this.indexNo = 0;
		this.vacancy = 0;
		this.sessions = new ArrayList<Session>();
		this.registered = new ArrayList<String>();
		this.waitlist = new ArrayList<String>();
	}

	/**
	 * Constructor with specified parameter
	 * 
	 * @param indexNo
	 *            The index number attached to this group
	 * @param vacancy
	 *            The number of vacancy in this group
	 * @param sessions
	 *            The list of session inside the group
	 */
	public Group(int indexNo, int vacancy, ArrayList<Session> sessions)
	{
		this.indexNo = indexNo;
		this.vacancy = vacancy;
		this.sessions = sessions;
		this.registered = new ArrayList<String>();
		this.waitlist = new ArrayList<String>();
	}

	/**
	 * Get the course that this group is currently in
	 * 
	 * @param courseList
	 *            The list of all available courses
	 * @return course the group resides in
	 */
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

	/**
	 * Add student to the group
	 * 
	 * @param matricNumber
	 *            The matric number of student to be added to the group
	 * @return the student's status that has added to
	 */
	public Group_Status addStudentToGroup(String matricNumber)
	{
		if (vacancy > 0)
		{
			registered.add(matricNumber);
			vacancy--;
			return Group_Status.REGISTERED;
		}
		else
		{
			waitlist.add(matricNumber);
			return Group_Status.WAITLIST;
		}
	}

	/**
	 * Remove student from the group
	 * 
	 * @param matricNumber
	 *            The matric number of student to be dropped from the group
	 * @return the student's status that has dropped
	 */
	public Group_Status dropStudent(String matricNumber)
	{
		if (Enumerator.string(Group_Status.WAITLIST).equals(this.findStudentStatus(matricNumber)))
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
		}
		else
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

	/**
	 * Update the waitlist of the group
	 * 
	 * @param studentList
	 *            The list of all available students
	 * @param courseList
	 *            The list of all available courses
	 */
	public void updateWaitlist(ArrayList<Student> studentList, ArrayList<Course> courseList)
	{
		while (waitlist.size() > 0 && vacancy > 0)
		{
			this.addStudentToGroup(this.getWaitlist().get(0));
			this.dropStudent(this.getWaitlist().get(0));

			for (Student student : studentList)
			{
				if (student.getMatricNo().equalsIgnoreCase(this.getRegistered().get(this.getRegistered().size() - 1)))
				{
					this.notify(student, courseList);
					break;
				}
			}
		}
	}

	/**
	 * Notify student that move to registered from the waitlist
	 * 
	 * @param student
	 *            The list of all available students
	 * @param courseList
	 *            The list of all available courses
	 */
	public void notify(Student student, ArrayList<Course> courseList)
	{
		if (student.getNotification().equals(Enumerator.string(Notification_Status.SMS)))
		{
			Notifier.sendSMS(student.getPhoneNo());
		}
		else
		{
			Notifier.sendEmail(student.getEmail(), this.getCourse(courseList).getId(), indexNo, Group_Status.REGISTERED); // Change
		}
	}

	/**
	 * Find the student's status currently in the group
	 * 
	 * @param matricNo
	 *            The matric number of student to search inside the group
	 * @return student's status in group
	 */
	public Comparable findStudentStatus(String matricNo)
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
		return Enumerator.string(status);
	}

	/**
	 * Add a session for the group
	 * 
	 * @param index
	 *            The index number of the session to be added to the group
	 * @return true if session is added successfully
	 */
	public Boolean addSession(int index)
	{
		Scanner in = new Scanner(System.in);
		Session session;
		if (index == -1)
		{
			session = new Session();
		}
		else
		{
			session = this.getSessions().get(index);
		}
		// Type
		session.setType(Enumerator.nextEnum(Session_Type.class));
		// Group
		System.out.print("Enter Group Name: ");
		session.setGroup(in.next());
		// Day
		session.setDay(Enumerator.nextEnum(Day.class));
		// Start Time
		System.out.println("Enter Start Time: ");
		session.setSTime(Formatter.enterDateTime("time"));
		// Hours
		session.setETime(Formatter.addHours(session.getSTime(), Formatter.withinRange("Session Hours", 1, 10)));
		// Venue
		System.out.print("Enter Venue: ");
		in.nextLine();
		session.setVenue(in.nextLine());
		// Remark
		System.out.print("Enter Remarks: ");
		session.setRemark(in.nextLine());

		for (Session se : this.getSessions())
		{
			if (Objects.equals(se, session))
			{
				continue;
			}
			if (Checker.overlapSession(se, session))
			{
				System.out.println("Session overlaps with an session in this Group.");
				return false;
			}
		}

		if (index == -1)
		{
			sessions.add(session);
		}
		else
		{
			sessions.set(index, session);
		}
		return true;
	}

	/**
	 * @return the indexNo
	 */
	public int getIndexNo()
	{
		return indexNo;
	}

	/**
	 * @param indexNo
	 *            the indexNo to set
	 */
	public void setIndexNo(int indexNo)
	{
		this.indexNo = indexNo;
	}

	/**
	 * @return the vacancy
	 */
	public int getVacancy()
	{
		return vacancy;
	}

	/**
	 * @param vacancy
	 *            the vacancy to set
	 */
	public void setVacancy(int vacancy)
	{
		this.vacancy = vacancy;
	}

	/**
	 * @return the sessions
	 */
	public ArrayList<Session> getSessions()
	{
		return sessions;
	}

	/**
	 * @param sessions
	 *            the sessions to set
	 */
	public void setSessions(ArrayList<Session> sessions)
	{
		this.sessions = sessions;
	}

	/**
	 * @return the registered
	 */
	public ArrayList<String> getRegistered()
	{
		return registered;
	}

	/**
	 * @param registered
	 *            the registered to set
	 */
	public void setRegistered(ArrayList<String> registered)
	{
		this.registered = registered;
	}

	/**
	 * @return the waitlist
	 */
	public ArrayList<String> getWaitlist()
	{
		return waitlist;
	}

	/**
	 * @param waitlist
	 *            the waitlist to set
	 */
	public void setWaitlist(ArrayList<String> waitlist)
	{
		this.waitlist = waitlist;
	}

}

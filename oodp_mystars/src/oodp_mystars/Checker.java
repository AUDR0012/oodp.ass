package oodp_mystars;

import java.util.ArrayList;
import java.util.regex.Pattern;
import oodp_mystars.Enumerator.*;

/**
 * Represent a custom class to check items
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Checker {

	/**
	 * Check overlap between overlap
	 * 
	 * @param registeredGroup
	 *            Currently registered group
	 * @param newGroup
	 *            The new group that is going to be added in
	 * @param exclude
	 *            Which group to be exclude from checking
	 * @return true if overlap/ false if never overlap
	 */
	public static boolean overlapGroup(Group registeredGroup, Group newGroup, Group exclude)
	{
		if (exclude != null)
		{
			if (registeredGroup.getIndexNo() == exclude.getIndexNo())
			{
				return false;
			}
		}

		for (Session currentSession : registeredGroup.getSessions())
		{
			for (Session newSession : newGroup.getSessions())
			{
				if (overlapSession(currentSession, newSession))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check overlap between session
	 * 
	 * @param session1
	 *            The 1st session to be compared to
	 * @param session2
	 *            The 2nd session to be compared to
	 * @return true if overlap/ false if never overlap
	 */
	public static boolean overlapSession(Session session1, Session session2)
	{
		if (session1.getDay().equals(session2.getDay())
				&& (session1.getAlternateWeek().equals(session2.getAlternateWeek())
						|| session1.getAlternateWeek().equals(Alternate_Week.NONE)
						|| session2.getAlternateWeek().equals(Alternate_Week.NONE)))
		{
			if ((session1.getSTime().equals(session2.getSTime()) && session1.getETime().equals(session2.getETime())) /* session1 [__] 
																														 * session2 [__]
																														 */
					|| (session1.getSTime().before(session2.getSTime()) && session1.getETime().after(session2.getSTime())) /* session1 [__] 
																															 * session2   [__]
																															 */
					|| (session1.getSTime().before(session2.getETime()) && session1.getETime().after(session2.getETime())) /* session1   [__] 
																															 * session2 [__]
																															 */
					|| (session1.getSTime().after(session2.getSTime()) && session1.getETime().before(session2.getETime())) /* session1   [__] 
																															 * session2 [______]
																															 */
					|| (session1.getSTime().before(session2.getSTime()) && session1.getETime().after(session2.getETime()))) /* session1 [______] 
																															 * session2   [__]
																															 */
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if the course already exist
	 * 
	 * @param courseList
	 *            The list of all available courses
	 * @param indexNo
	 *            The index number of the new course that is going to be created
	 * @return true if indexNo exist/ false if indexNo do not exist
	 */
	public static boolean isDuplicate(ArrayList<Course> courseList, int indexNo)
	{
		for (Course co : courseList)
		{
			for (Group gr : co.getGroups())
			{
				if (gr.getIndexNo() == indexNo)
				{
					System.out.println("Index Number " + indexNo + " has already been used.");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check if the email is in correct format
	 * 
	 * @param email
	 *            The email address to be checked
	 * @return true if valid email/ false if invalid email
	 */
	public static boolean emailValid(String email)
	{
		// "^(.+)@(.+)$"
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		return Pattern.compile(pattern).matcher(email).matches();
	}
}

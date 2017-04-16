package oodp_mystars;

import java.util.ArrayList;
import java.util.regex.Pattern;
import oodp_mystars.Enumerator.*;

/**
 * 
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
	 * @param newGroup
	 * @param exclude
	 * @return
	 */
	public static boolean isOverlap(Group registeredGroup, Group newGroup, Group exclude)
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
				if (isOverlap(currentSession, newSession))
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
	 * @param session2
	 * @return
	 */
	public static boolean isOverlap(Session session1, Session session2)
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
	 * @param indexNo
	 * @return
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
	 * @param email
	 * @return
	 */
	public static boolean emailValid(String email)
	{
		// "^(.+)@(.+)$"
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		return Pattern.compile(pattern).matcher(email).matches();
	}
}

package oodp_project;

import java.util.ArrayList;

import oodp_project.Enumerator.Alternate_Week;

public class Checker
{

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

	public static boolean isOverlap(Session session1, Session session2)
	{
		if (session1.getDay().equals(session2.getDay())
				&& (session1.getAlternateWeek().equals(session2.getAlternateWeek())
						|| session1.getAlternateWeek().equals(Alternate_Week.NONE)
						|| session2.getAlternateWeek().equals(Alternate_Week.NONE)))
		{
			if ((session1.getSTime().equals(session2.getSTime()) && session1.getETime()
					.equals(session2.getETime())) /*
													 * sCur [__] sNew [__]
													 */
					|| (session1.getSTime().before(session2.getSTime()) && session1.getETime().after(
							session2.getSTime())) /*
													 * sCur [__] sNew [__]
													 */
					|| (session1.getSTime().after(session2.getETime()) && session1.getETime().before(
							session2.getETime())) /*
													 * sCur [__] sNew [__]
													 */
					|| (session1.getSTime().after(session2.getSTime()) && session1.getETime().before(
							session2.getETime())) /*
													 * sCur [__] sNew [______]
													 */
					|| (session1.getSTime().before(session2.getSTime()) && session1.getETime().after(
							session2.getETime()))) /*
													 * sCur [______] sNew [__]
													 */
			{
				return true;
			}
		}
		return false;
	}

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
}

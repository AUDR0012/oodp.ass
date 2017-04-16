package oodp_mystars;

import java.io.Serializable;
import java.util.Date;
import oodp_mystars.Enumerator.*;

/**
 * Represents a session in the group for the student to attend.
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Session implements Serializable {
	/**
	 * The type of this Session (Lecture, Tutorial, Lab)
	 */
	private Session_Type type;

	/**
	 * The group name of this Session
	 */
	private String group;

	/**
	 * The day this Session falls on
	 */
	private Day day;

	/**
	 * The time this Session starts
	 */
	private Date sTime;

	/**
	 * The time this Session ends
	 */
	private Date eTime;

	/**
	 * The venue of this Session
	 */
	private String venue;
	/**
	 * The extra information students need to take note of this Session
	 */
	private String remark;

	/**
	 * Default constructor
	 */
	public Session()
	{
		this.type = null;
		this.group = null;
		this.day = null;
		this.sTime = null;
		this.eTime = null;
		this.venue = null;
		this.remark = null;
	}

	/**
	 * Constructor with specified parameter
	 * 
	 * @param type
	 * @param group
	 * @param day
	 * @param sTime
	 * @param hours
	 * @param venue
	 * @param remark
	 */
	public Session(Session_Type type, String group, Day day, Date sTime, int hours, String venue, String remark)
	{
		this.type = type;
		this.group = group;
		this.day = day;
		this.sTime = sTime;
		this.eTime = Formatter.addHours(this.getSTime(), hours);
		this.venue = venue;
		this.remark = remark;
	}

	/**
	 * Print the session information
	 * 
	 * @param delimiter
	 */
	public void printSession(String delimiter)
	{
		System.out.print(delimiter + Formatter.tabs(2, delimiter, this.getType())
				+ Formatter.tabs(1, delimiter, this.getGroup())
				+ Formatter.tabs(2, delimiter, this.getDay())
				+ Formatter.tabs(2, delimiter, Formatter.getPeriod(sTime, eTime, "hhmm", "-"))
				+ Formatter.tabs(2, delimiter, this.getVenue())
				+ Formatter.tabs(3, delimiter, this.getRemark()));
	}

	/**
	 * Check if session is in odd or even week or every
	 * 
	 * @return
	 */
	public Alternate_Week getAlternateWeek()
	{
		if (remark.equalsIgnoreCase("Wk1,3,5,7,9,11,13"))
		{
			return Alternate_Week.ODD;
		}
		else if (remark.equalsIgnoreCase("Wk2,4,6,8,10,12"))
		{
			return Alternate_Week.EVEN;
		}
		else
		{
			return Alternate_Week.NONE;
		}
	}

	/**
	 * @return the type
	 */
	public String getType()
	// public Session_Type getType()
	{
		return Enumerator.string(type);
		// return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Session_Type type)
	{
		this.type = type;
	}

	/**
	 * @return the group
	 */
	public String getGroup()
	{
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(String group)
	{
		this.group = group;
	}

	/**
	 * @return the day
	 */
	public String getDay()
	// public Day getDay()
	{
		return Enumerator.string(day);
		// return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(Day day)
	{
		this.day = day;
	}

	/**
	 * @return the sTime
	 */
	public Date getSTime()
	{
		return sTime;
	}

	/**
	 * @param sTime
	 *            the sTime to set
	 */
	public void setSTime(Date sTime)
	{
		this.sTime = sTime;
	}

	/**
	 * @return the eTime
	 */
	public Date getETime()
	{
		return eTime;
	}

	/**
	 * @param eTime
	 *            the eTime to set
	 */
	public void setETime(Date eTime)
	{
		this.eTime = eTime;
	}

	/**
	 * @return the venue
	 */
	public String getVenue()
	{
		return venue;
	}

	/**
	 * @param venue
	 *            the venue to set
	 */
	public void setVenue(String venue)
	{
		this.venue = venue;
	}

	/**
	 * @return the remark
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

}

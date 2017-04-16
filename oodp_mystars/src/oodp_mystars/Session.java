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
public class Session implements Serializable
{
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

	public void printSession(String delimiter)
	{
		System.out.print(delimiter + Formatter.tabs(2, delimiter, this.getType())
				+ Formatter.tabs(1, delimiter, this.getGroup())
				+ Formatter.tabs(2, delimiter, this.getDay())
				+ Formatter.tabs(2, delimiter, Formatter.getTimePeriod(sTime, eTime, "hhmm", "-"))
				+ Formatter.tabs(2, delimiter, this.getVenue())
				+ Formatter.tabs(3, delimiter, this.getRemark()));
	}

	public Alternate_Week getAlternateWeek()
	{
		if (remark.equalsIgnoreCase("Wk1,3,5,7,9,11,13"))
		{
			return Alternate_Week.ODD;
		} else if (remark.equalsIgnoreCase("Wk2,4,6,8,10,12"))
		{
			return Alternate_Week.EVEN;
		} else
		{
			return Alternate_Week.NONE;
		}
	}

	public String getType()
	{
		return Enumerator.string(type);
	}

	public void setType(Session_Type type)
	{
		this.type = type;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public String getDay()
	{
		return Enumerator.string(day);
	}

	public void setDay(Day day)
	{
		this.day = day;
	}

	public Date getSTime()
	{
		return sTime;
	}

	public void setSTime(Date sTime)
	{
		this.sTime = sTime;
	}

	public Date getETime()
	{
		return eTime;
	}

	public void setETime(Date eTime)
	{
		this.eTime = eTime;
	}

	public String getVenue()
	{
		return venue;
	}

	public void setVenue(String venue)
	{
		this.venue = venue;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}
}

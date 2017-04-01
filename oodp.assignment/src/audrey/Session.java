package audrey;

import java.io.Serializable;
import java.util.Date;
import audrey.Enumerator.*;

public class Session implements Serializable {

	private Session_Type type;
	private String group;
	private Day day;
	private Date sTime;
	private Date eTime;
	private String venue;
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

	public Session(Session_Type type, String group, Day day, Date sTime, int hours, String venue,
			String remark)
	{
		this.type = type;
		this.group = group;
		this.day = day;
		this.sTime = sTime;
		this.eTime = FormatString.addHours(this.getSTime(), hours);
		this.venue = venue;
		this.remark = remark;
	}

	public void printSession(int length, String delimiter)
	{
		System.out.print(delimiter
				+ FormatString.tabs(length * 2, delimiter, this.getType())
				+ FormatString.tabs(length * 1, delimiter, this.getGroup())
				+ FormatString.tabs(length * 2, delimiter, this.getDay())
				+ FormatString.tabs(length * 2, delimiter, FormatString.getTimePeriod(sTime, eTime, "hhmm"))
				+ FormatString.tabs(length * 2, delimiter, this.getVenue())
				+ FormatString.tabs(length * 3, delimiter, this.getRemark()));
	}
	
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

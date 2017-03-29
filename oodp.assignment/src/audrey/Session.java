package audrey;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Session implements Serializable {

	enum Session_Type {
		LECTURE, TUTORIAL, LAB;
	}

	enum Session_Day {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
	}

	private Session_Type type;
	private String group;
	private Session_Day day;
	private Date startTime;
	private Date endTime;
	private String venue;
	private String remark;

	public Session(Session_Type type, String group, Session_Day day, String startTime, int hours, String venue,
			String remark)
	{
		this.type = type;
		this.group = group;
		this.day = day;
		this.startTime = FormatString.getDate(startTime, "hh:mm");
		this.endTime = new Date(this.startTime.getTime() + TimeUnit.HOURS.toMillis(hours));
		this.venue = venue;
		this.remark = remark;
	}

	public void printSession(int length, String delimiter)
	{
		System.out.print(delimiter
				+ FormatString.tabs(length * 2, delimiter, this.getType())
				+ FormatString.tabs(length * 1, delimiter, this.getGroup())
				+ FormatString.tabs(length * 2, delimiter, this.getDay())
				+ FormatString.tabs(length * 2, delimiter, this.getTimePeriod())
				+ FormatString.tabs(length * 2, delimiter, this.getVenue())
				+ FormatString.tabs(length * 3, delimiter, this.getRemark()));
	}

	public String getTimePeriod()
	{
		String format = "hhmm";
		if (startTime != null)
		{
			return FormatString.getString(this.getStartTime(), "hhmm") + "-" + FormatString.getString(this.getEndTime(), "hhmm");
		}
		return "";
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

	public void setDay(Session_Day day)
	{
		this.day = day;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
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

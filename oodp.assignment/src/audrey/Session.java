package audrey;

import java.io.Serializable;
import java.sql.Time;

public class Session implements Serializable {

	private int class_type;
	private int day;
	private Time start_time;
	private int hours;
	private String venue;
	private int is_odd_week;
	private String remark;

	public Session(int class_type, int day, Time start_time, int hours, String venue, int is_odd_week)
	{
		this.class_type = class_type;	// 0 - Lecture; 1 - Tutorial; 2 - Lab;
		this.day = day;					// 0 - None; 1 - Mon; 2 - Tue; 3 - Wed; 4 - Thu; 5 - Fri;
		this.start_time = start_time;
		this.hours = hours;
		this.venue = venue;
		this.is_odd_week = is_odd_week;	// 0 - None; 1 - Odd; 2 - Even;
		this.remark = "";
	}
	
	public void print()
	{
		System.out.println(class_type + " : " + day + " : " + start_time.toString() + " : " + hours + " : " + venue + " : " + is_odd_week + " : " + remark);
	}
}

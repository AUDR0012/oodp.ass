package audrey;
import java.sql.Time;

public class Session {
	
	private int class_type;
	private int day;
	private Time start_time;
	private int hours;
	private String venue;
	private int is_odd_week;
	private String remark;
	
	public Session()
	{
		this.class_type = 0; //0 - None; 1 - Lecture; 2 - Tutorial; 3 - Lab;
		this.day = 0; // 0 - None; 1 - Mon; 2 - Tue; 3 - Wed; 4 - Thu; 5 - Fri;
		this.start_time = null;
		this.hours = 0;
		this.venue = "";
		this.is_odd_week = 0; //0 - None; 1 - Odd; 2 - Even; 
		this.remark = "";
	}
}

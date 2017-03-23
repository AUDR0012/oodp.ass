package audrey;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseGroup implements Serializable {

	private int index_no;
	private String group_name;
	private int vacancy;
	private ArrayList<Session> session_list;
	private ArrayList<Student> registered;
	private Queue<Student> waitlist;

	public CourseGroup(int index_no, String group_name, int vacancy)
	{
		this.index_no = index_no;
		this.group_name = group_name;
		this.vacancy = vacancy;
		this.session_list = new ArrayList<Session>();
		this.registered = new ArrayList<Student>();
		this.waitlist = new LinkedList<Student>();
	}

	public void add_session(int class_type, int day, Time start_time, int hours, String venue, int is_odd_week)
	{
		Session s = new Session(class_type, day, start_time, hours, venue, is_odd_week);
		session_list.add(s);
	}

	public void add_student(Student s)
	{
		if (vacancy > 0)
		{
			registered.add(s);
			vacancy--;
		}
		else
		{
			waitlist.add(s);
		}
	}

	public void drop_student(Student s)
	{
		// if ()
	}

	public boolean found_student()
	{

		return true;
	}
	
	public void print()
	{
		System.out.println(index_no + " : " + group_name + " : " + vacancy);

		for(Session e : session_list)
		{
			e.print();
		}
		for(Student e : registered)
		{
			e.print();
		}
		for(Student e : waitlist)
		{
			e.print();
		}
	}
}

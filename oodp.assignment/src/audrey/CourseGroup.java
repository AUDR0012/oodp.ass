package audrey;

public class CourseGroup {
	
	private int index_no;
	private String group_name;
	private int vacancy;
	private Session[] session_list;
	private Student[] registered;
	private Student[] waitlist;
	
	public CourseGroup(int index_no, String group_name, int vacancy)
	{
		this.index_no = index_no;
		this.group_name = group_name;
		this.vacancy = vacancy;
		this.session_list = null;
		this.registered = null;
		this.waitlist = null;
	}
}

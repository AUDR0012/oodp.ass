package audrey;

public class User {
	
	private String username;
	private String hash_password;
	private boolean is_student;
	
	public User(String username, String hash_password, boolean is_student)
	{
		this.username = username;
		this.hash_password = hash_password;
		this.is_student = is_student;
	}
}

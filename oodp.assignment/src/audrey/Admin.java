package audrey;

public class Admin implements User {

	private String username;
	private String password;

	private String name;

	public Admin(String username, String password, String name)
	{
		this.username = username;
		this.password = password;
		
		this.name = name;
	}

	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return password;
	}
}

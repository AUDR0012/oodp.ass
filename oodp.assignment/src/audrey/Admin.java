package audrey;

import java.io.Serializable;

public class Admin implements User, Serializable {

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
		return username;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public void print()
	{
		System.out.println(username + " : " + password + " : " + name);
	}
}

package audrey;

import java.io.Serializable;
import java.util.Date;

public class Logger implements Serializable {

	private String username;
	private String password;
	private Comparable user;
	private Date accessTime;
	
	public Logger(String username, String password, Comparable user)
	{
		this.username = username;
		this.password = password;
		this.user = user;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Comparable getUser()
	{
		return user;
	}

	public void setUser(Comparable user)
	{
		this.user = user;
	}

	public Date getAccessTime()
	{
		return accessTime;
	}

	public void setAccessTime(Date accessTime)
	{
		this.accessTime = accessTime;
	}
}

package audrey;

import java.io.Serializable;
import java.util.Date;

public class Logger implements Serializable {

	private String username;
	private String password;
	private Comparable user;
	private Date accessSTime;
	private Date accessETime;
	
	public Logger()
	{
		this.username = null;
		this.password = null;
		this.user = null;
		this.accessSTime = null;
		this.accessETime = null;
	}
	
	public Logger(String username, String password, Comparable user)
	{
		this.username = username;
		this.password = password;
		this.user = user;
		this.accessSTime = null;
		this.accessETime = null;
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

	public Date getAccessSTime()
	{
		return accessSTime;
	}

	public void setAccessSTime(Date accessSTime)
	{
		this.accessSTime = accessSTime;
	}

	public Date getAccessETime()
	{

		return accessETime;
	}

	public void setAccessETime(Date accessETime)
	{
		this.accessETime = accessETime;
	}
}

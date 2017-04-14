package oodp_project;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a logger that stores the user's credentials and access period.
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Logger implements Serializable
{
	/**
	 * The username of this Logger
	 */
	private String username;

	/**
	 * The password of this Logger
	 */
	private String password;

	/**
	 * The user of this Logger
	 */
	private Comparable user;

	/**
	 * The start date and time this Logger is able access
	 */
	private Date accessSTime;

	/**
	 * The end date and time this Logger is able access
	 */
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

package oodp_mystars;

import java.io.Serializable;
import java.util.Date;

/**
 * Represent a logger that stores the user's credentials and access period
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Logger implements Serializable {
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

	private Date accessSTime;

	/**
	 * The end date and time this Logger is able access
	 */
	private Date accessETime;

	/**
	 * Default constructor
	 */
	public Logger()
	{
		this.username = null;
		this.password = null;
		this.user = null;
		this.accessSTime = null;
		this.accessETime = null;
	}

	/**
	 * Constructor with specified parameter
	 * 
	 * @param username
	 *            The user name of the user
	 * @param password
	 *            The password of the user
	 * @param user
	 *            The user type, either admin or student
	 */
	public Logger(String username, String password, Comparable user)
	{
		this.username = username;
		this.password = password;
		this.user = user;
		this.accessSTime = null;
		this.accessETime = null;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the user
	 */
	public Comparable getUser()
	{
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(Comparable user)
	{
		this.user = user;
	}

	/**
	 * @return the accessSTime
	 */
	public Date getAccessSTime()
	{
		return accessSTime;
	}

	/**
	 * @param accessSTime
	 *            the accessSTime to set
	 */
	public void setAccessSTime(Date accessSTime)
	{
		this.accessSTime = accessSTime;
	}

	/**
	 * @return the accessETime
	 */
	public Date getAccessETime()
	{
		return accessETime;
	}

	/**
	 * @param accessETime
	 *            the accessETime to set
	 */
	public void setAccessETime(Date accessETime)
	{
		this.accessETime = accessETime;
	}

}

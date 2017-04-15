package oodp_project;

import java.io.Serializable;

/**
 * Represents a administrator working in the school.
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class Admin implements Comparable, Serializable
{
	/**
	 * The first and last name of this Administrator
	 */
	private String name;

	/**
	 * Creates a new Administrator with no name. This Administrator's name will
	 * be set to null by default.
	 */
	public Admin()
	{
		this.name = null;
	}

	/**
	 * Creates a new Administrator with the given name. The name should include
	 * both first and last name.
	 * 
	 * @param name
	 *            This Administrator's name.
	 */
	public Admin(String name)
	{
		this.name = name;
	}

	@Override
	public int compareTo(Object arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Gets the first and last name of this Administrator.
	 * 
	 * @return this Administrator's name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Changes the name of this Administrator.
	 * 
	 * @param newName
	 *            This Administrator's new name. Should include both first and
	 *            last name.
	 */
	public void setName(String newName)
	{
		this.name = newName;
	}
}

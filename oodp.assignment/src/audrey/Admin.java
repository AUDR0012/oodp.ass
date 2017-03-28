package audrey;

import java.io.Serializable;

public class Admin implements Comparable, Serializable {

	private String name;

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}

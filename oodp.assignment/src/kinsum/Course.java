package kinsum;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable
{
	private String name;
	private String[] test;
	private ArrayList<Group> groups;

	public Course(String name)
	{
		groups = new ArrayList<Group>();
		this.name = name;
		test = new String[10];
		test[0] = "1";
		test[1] = "2";
	}

	public void addGroup(Group group)
	{
		groups.add(group);
	}

	public String getName()
	{
		return name;
	}

	public ArrayList<Group> getGroups()
	{
		return groups;
	}

	public String[] getTest()
	{
		return test;
	}
}

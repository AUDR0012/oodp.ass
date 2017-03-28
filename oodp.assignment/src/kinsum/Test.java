package kinsum;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// Login

		Scanner sc = new Scanner(System.in);

		ArrayList<Course> courses = new ArrayList<Course>();

		courses.add(new Course("English"));
		courses.add(new Course("Chinese"));
		courses.add(new Course("Maths"));
		courses.add(new Course("Science"));

		for (Course e : courses)
		{

			e.addGroup(new Group());
		}

		SerializeDB.writeSerializedObject("courses", courses);

		System.out.println("Written");

		// Retrieve

		ArrayList<Course> coursesTemp = new ArrayList<Course>();

		coursesTemp = (ArrayList<Course>) SerializeDB.readSerializedObject("courses");

		for (Course e : courses)
		{

			System.out.println(e.getName());
			for (Group g : e.getGroups())
			{
				System.out.println(g.getGroupName());
			}

		}

	}

}

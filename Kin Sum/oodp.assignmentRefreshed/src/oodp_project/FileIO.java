package oodp_project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class FileIO
{

	public static Object readSerializedObject(String filename)
	{
		Object pDetails = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream in = null;
		try
		{
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			in = new ObjectInputStream(bis);
			pDetails = in.readObject();
			in.close();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		} catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		return pDetails;
	}

	public static void writeSerializedObject(String filename, Object obj)
	{
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(filename);
			bos = new BufferedOutputStream(fos);
			out = new ObjectOutputStream(fos);
			out.writeObject(obj);
			out.close();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void readData(ArrayList<Logger> userList, ArrayList<Course> courseList)
	{
		// Logger
		userList.addAll((ArrayList<Logger>) FileIO.readSerializedObject("list_user"));
		// Student
		/*
		 * for (Logger l : userList) { if (l.getUser() instanceof Student) {
		 * studentList.add((Student) l.getUser()); } }
		 */
		// Course
		courseList.addAll((ArrayList<Course>) FileIO.readSerializedObject("list_course"));
	}

	public static void writeData(ArrayList<Logger> userList, ArrayList<Course> courseList)
	{
		FileIO.writeSerializedObject("list_user", userList);

		FileIO.writeSerializedObject("list_course", courseList);
	}

}

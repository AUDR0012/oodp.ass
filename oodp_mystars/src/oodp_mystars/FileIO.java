package oodp_mystars;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Represent a class that handles reading and writing of data
 * 
 * @author Audrey KinSum Kelvin JianHao
 * @version 1.0
 * @since 2017-04-13
 */
public class FileIO {

	/**
	 * Read Object from file
	 * 
	 * @param filename
	 *            The file name of where the serialized object should be stored
	 * @return deserialize object
	 */
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
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		return pDetails;
	}

	/**
	 * Write object to file
	 * 
	 * @param filename
	 *            The file name of the serialized object should be retrieved from
	 * @param obj The object to be serialized
	 */
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
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Retrieve information
	 * 
	 * @param userList
	 *            The list of all user including admin and student
	 * @param courseList The list of all courses available
	 */
	public static void readData(ArrayList<Logger> userList, ArrayList<Course> courseList)
	{
		// Logger
		if (userList.size() == 0)
		{
			userList.clear();
		}
		userList.addAll((ArrayList<Logger>) FileIO.readSerializedObject("list_user"));

		// Course
		if (courseList.size() == 0)
		{
			courseList.clear();
		}
		courseList.addAll((ArrayList<Course>) FileIO.readSerializedObject("list_course"));
	}

	/**
	 * Write info
	 * 
	 * @param userList
	 *            The list of all user
	 * @param courseList
	 *            The list of all the available courses
	 */
	public static void writeData(ArrayList<Logger> userList, ArrayList<Course> courseList)
	{
		FileIO.writeSerializedObject("list_user", userList);
		FileIO.writeSerializedObject("list_course", courseList);
	}
}

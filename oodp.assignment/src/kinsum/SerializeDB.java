package kinsum;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class SerializeDB {

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
		// print out the size
		// System.out.println(" Details Size: " + pDetails.size());
		// System.out.println();
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
			// System.out.println("Object Persisted");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

}

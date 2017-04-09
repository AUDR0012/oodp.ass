package tohjianhao;

import java.util.Scanner;

public class Test 
{
	public static void main(String[] args)
	{
		int in_int;
		
		Scanner in = new Scanner (System.in);
		String testString = in.nextLine();
		
		int lengthOfString = testString.length();
			
		for(int i = 0; i < 5 ; i++)
		{
			if((!Character.isDigit(testString.charAt(i))))
			{
				in_int = -1;
				break;
			}
				
		}
	}
}

package tohjianhao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verifier {
	public boolean validateEmail(String email)
	{	
		//Regular expression Pattern to validate Email
		
		/*
		 ^			#start of the line
  		'[_A-Za-z0-9-\\+]+'	#  must start with string in the bracket [ ], must contains one or more (+)
  		(					#  start of group #1
    	'\\.[_A-Za-z0-9-]+'	#  follow by a dot "." and string in the bracket [ ], must contains one or more (+)
  		)'*'				#  end of group #1, this group is optional (*)
    	
    	'@'					#  must contains a "@" symbol
     	
     	'[A-Za-z0-9-]+'     #  follow by string in the bracket [ ], must contains one or more (+)
      	(					#  start of group #2 - first level TLD checking
       	'\\.[A-Za-z0-9]+'  	#  follow by a dot "." and string in the bracket [ ], must contains one or more (+)
      	)'*'				#  end of group #2, this group is optional (*)
      
      	(					#  start of group #3 - second level TLD checking
       	'\\.[A-Za-z]{2,}' 	#  follow by a dot "." and string in the bracket [ ], with minimum length of 2
      	)					#  end of group #3
		$					#  end of the line
		*/
		
		final String EMAIL_PATTERN =
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		
		//Setup pattern to be used for comparing to the input string for email
		Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
		
		//Check if input email matches pattern
		Matcher matcher = emailPattern.matcher(email);
		
		//Obtain results. Return true if pattern matches, otherwise return false
		return matcher.matches();
	}
	
	public boolean validatePhoneNumber(String phoneNumber)
	{
		//Regular expression Pattern to validate Singapore handphone numbers
		/*
		 '[89]' #Check if first digit start with 8 or 9
		 
		 '\\d\\d\\d\\d\\d\\d\\d' #Check any combination of seven digits at the back
		 */
		final String PHONE_NUMBER = "[89]\\d\\d\\d\\d\\d\\d\\d";
		
		//Setup pattern to be used for comparing to the input string for phone numbers
		Pattern phoneNumberPattern = Pattern.compile(PHONE_NUMBER);
		
		//Check if input phone number matches pattern
		Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
		
		//Obtain results. Return true if pattern matches, otherwise return false
		return matcher.matches();
	}
}

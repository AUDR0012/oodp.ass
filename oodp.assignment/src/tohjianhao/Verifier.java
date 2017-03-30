package tohjianhao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verifier {
	//Pattern is to hold the pattern of the email address
	//Matcher is to check if the email address matches the pattern
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	//Default Constructor sets up pattern for verifying email
	public Verifier()
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	public boolean validate(String email)
	{
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}

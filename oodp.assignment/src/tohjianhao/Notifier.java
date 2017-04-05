package tohjianhao;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class Notifier
{
	//Options to select which type of notification should be sent.
	public static enum MessageType
	{
		WAITLIST_NOTIFICATION, REGISTRATION_NOTIFICATION
	}
	
	//Email address and password of mail system used
	private static String senderEmailAddress = "cz2002.fsp2.agrp3@gmail.com";
	private static String senderPassword = "Assignmentgrp3";
	
	//javax.mail.Session object use to establish session for account
	private static javax.mail.Session emailSession;
	
	//Properties object to state which SMTP host is to be used and what port number to be
	//connected to
	private static Properties properties;
	
	//Regular Expression for Email Verification
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	//Regular Expression for validating Phone Numbers
	private final String PHONE_NUMBER = "[89]\\d\\d\\d\\d\\d\\d\\d";
	
	public Notifier()
	{
		
	}
	
	//Send notificiton to student to notif them that they are in waitlit or officially registered under course.
	//Returns false if message cannot be sent.
	public static boolean sendNotification(String studentEmail, String courseID, int groupID, MessageType typeOfNotification)
	{
		//Setup Properties for the email session
		properties = new Properties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "25");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		//Establish connection with the account you wish to login to
		emailSession = Session.getDefaultInstance(properties,  
			    new javax.mail.Authenticator() {  
			      protected PasswordAuthentication getPasswordAuthentication() {  
			    return new PasswordAuthentication(senderEmailAddress,senderPassword);  
			      }  
			    });  
		try
		{
			MimeMessage msg = new MimeMessage(emailSession);
			msg.setFrom(senderEmailAddress);
			msg.addRecipient(RecipientType.TO, new InternetAddress(studentEmail));
			
			//Switch between Messages for waitlist or notificaion depending on typeOfNotificaition
			switch (typeOfNotification)
			{
			//Set subject and message for waitlist notification
			case WAITLIST_NOTIFICATION:
				msg.setSubject("Waitlist notification");
				msg.setText("Dear Student,"
							+ "\n"
							+ "\n"
							+ "This message has been sent to inform you that you have been put into the waiting list "
							+ "of the Course " + courseID + " for Group " + groupID + "."
							+ "\n"
							+ "\n"
							+ "We hope that you can obtain the Course of your choice."
							+ "\n"
							+ "\n"
							+ "Thank you."
							+ "\n"
							+ "\n"
							+ "Signed"
							+ "\n"
							+ "\n"
							+ "STARS System");
			break;
			
			//Set subject and message for Registration notification
			case REGISTRATION_NOTIFICATION:
				msg.setSubject("Registration Notification");
				msg.setText("Dear Student,"
							+ "\n"
							+ "\n"
							+ "This message has been sent to inform you that you have successful been registered under "
							+ "Course " + courseID + " for Group " + groupID + "."
							+ "\n"
							+ "\n"
							+ "We hope you manage to recieve the schedule of you choice."
							+ "\n"
							+ "\n"
							+ "Thank you."
							+ "\n"
							+ "\n"
							+ "Signed"
							+ "\n"
							+ "\n"
							+ "STARS System");
			break;
			}
			
			Transport.send(msg);
		}
		catch(MessagingException e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static void sendSMS (String num)
	{
		System.out.println("SMS was sent to " + num);
	}
}

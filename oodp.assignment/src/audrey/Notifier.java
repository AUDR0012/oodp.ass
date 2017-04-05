package audrey;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import audrey.Enumerator.*;

public class Notifier {

	private static String senderEmail;
	private static String senderPassword;
	private static javax.mail.Session emailSession;

	public Notifier()
	{
		this.senderEmail = "cz2002.fsp2.agrp3@gmail.com";
		this.senderPassword = "Assignmentgrp3";

		setupEmail();
	}

	public Notifier(String senderEmail, String senderPassword)
	{
		this.senderEmail = senderEmail;
		this.senderPassword = senderPassword;

		setupEmail();
	}

	private static void setupEmail()
	{
		// Setup Properties for the email session
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "25");
		properties.setProperty("mail.smtp.starttls.enable", "true");

		// Establish connection with the account you wish to login to
		emailSession = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});
	}

	// Send notification to student to notify them that they are in waitlist or officially registered under course.
	public static void sendEmail(String studentEmail, String courseId, int indexNo, Notifier_Type notifyType)
	{
		MimeMessage email;
		try
		{
			email = new MimeMessage(emailSession);
			email.setFrom(senderEmail);
			email.addRecipient(RecipientType.TO, new InternetAddress(studentEmail));

			// Switch between Messages for waitlist or notification depending on Notifier_Type
			switch (notifyType)
			{
				case WAITLIST:
				{ // Set subject and message for Waitlist Notification
					email.setSubject("Waitlist Notification");
					email.setText("Dear Student,"
							+ "\n"
							+ "\n"
							+ "This message has been sent to inform you that you have been put into the waiting list "
							+ "of the Course " + courseId + " for Group " + indexNo + ".\n"
							+ "\n"
							+ "We hope that you can obtain the Course of your choice.\n"
							+ "\n"
							+ "Thank you.\n"
							+ "\n"
							+ "Signed\n"
							+ "\n"
							+ "MySTARS System");
					break;
				}
				case REGISTERED:
				{ // Set subject and message for Registration Notification
					email.setSubject("Registration Notification");
					email.setText("Dear Student,"
							+ "\n"
							+ "\n"
							+ "This message has been sent to inform you that you have successful been registered under "
							+ "Course " + courseId + " for Group " + indexNo + ".\n"
							+ "\n"
							+ "We hope you manage to recieve the schedule of you choice.\n"
							+ "\n"
							+ "Thank you.\n"
							+ "\n"
							+ "Signed\n"
							+ "\n"
							+ "MySTARS System");
					break;
				}
			}
			Transport.send(email);
			//System.out.println("[System] Mail have been sent successfully."); // Required?
		}
		catch (MessagingException e)
		{
			// e.printStackTrace();
			//System.out.println("[System] Mail cannot be sent."); // Required?
		}
	}

	public static void sendSMS(String phoneNo)
	{
		System.out.println("SMS was sent to " + phoneNo);
	}
}

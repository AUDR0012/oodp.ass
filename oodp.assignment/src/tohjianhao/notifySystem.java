package tohjianhao;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

public class notifySystem implements notification {
	
	/**
	 * Method to send an Sms
	 * @param number mobile number
	 */
	public static void Sms(String number)
	{
		System.out.println("A SMS is sent to " + number);
	}
	
	/**
	 * Method to send an Email
	 * @param to email to
	 * @param content content
	 */
	public static void Email(String to,String content)
	{		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "25");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		
		Session session = Session.getDefaultInstance(properties,  
			   /* new javax.mail.Authenticator() {  
			      protected PasswordAuthentication getPasswordAuthentication() {  
			    return new PasswordAuthentication("cz2002.fsp2.ag2@gmail.com","leslierocks");  
			      }  
			    });  */
				new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication("cz2002.fsp2.agrp3@gmail.com","Assignmentgrp3");  
		      }  
		    });
			    
		try
		{
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom("cz2002.fsp2.ag2@gmail.com");
			msg.addRecipient(RecipientType.TO, new InternetAddress(to));
			msg.setSubject("Waitlist notification");
			msg.setText(content);
			
			Transport.send(msg);
			System.out.println("[System] Mail have been sent successfully");
		}catch(MessagingException e)
		{
			e.printStackTrace();
		}
		
	}
}

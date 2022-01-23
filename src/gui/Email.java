package gui;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import TownRecommendationsTraveler.City;
import TownRecommendationsTraveler.PerceptronTraveller;
import TownRecommendationsTraveler.WriteManyLogs;

public class Email {

	public static void sentEmail(String toEmail, PerceptronTraveller newTraveller) {

		final String username = "recommendcities@gmail.com";
		final String password = "**********";

		String fromEmail = "recommendcities@gmail.com";

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(username, password);

			}
		});

		MimeMessage msg = new MimeMessage(session);

		try {

			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			msg.setSubject("Reccomend Cities");

			Multipart emailContent = new MimeMultipart();

			ArrayList<City> x = newTraveller.getCitiesRecommend();

			// BODY
			String text = "";

			MimeBodyPart textBodyPart = new MimeBodyPart();
			int len = x.size();

			for (int i = 0; i < len; i++) {

				text += x.get(i).getCityName() + "  " + String.format("%.2f", x.get(i).getDistance() * 15325)
						+ "  kilometers \n";//
			}

			textBodyPart.setText(text);
			emailContent.addBodyPart(textBodyPart);

			msg.setContent(emailContent);

			Transport.send(msg);

			WriteManyLogs.getObj().writeToLog(Level.INFO, " Email has been sent successfully ");

		} catch (MessagingException e) {

			e.printStackTrace();

			WriteManyLogs.getObj().writeToLog(Level.INFO, "This email was not sent! ");
		}

	}

}
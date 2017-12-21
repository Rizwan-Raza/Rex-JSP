package com.rex.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	private static final String USERNAME = "postmaster@tomcat-rex.7e14.starter-us-west-2.openshiftapps.com";
	private static final String PASSWORD = "Rizwan.Raza365";

	public static void send(String to, String sub, String msg) {
		String host = "smtp.mailgun.org";// change accordingly

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME, "Admin | R.E.X"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject(sub);

			message.setContent(msg, "text/html");

			Transport.send(message);
			System.out.println("Activation Message Sent Successfully.");
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getActivationMsg(String id, String name) {
		return "<html>\r\n" + "<body style=\"margin: 0px;padding: 0px;background-color: #eee;\">\r\n"
				+ "  <div style=\"background-color:#343a40;color:#fff;font-family:Arial,Helvetica,sans-serif; padding: 12px 12px; font-size: 1.625rem\">\r\n"
				+ "    Hello " + name + "from Real Estate eXplorer\r\n" + "  </div>\r\n"
				+ "  <div style=\"padding: 10px; background-color: #eee;\">\r\n"
				+ "    <font color=\"#4f4f4f\" face=\"Arial,Helvetica,sans-serif\" style=\"font-size:16px; line-height:28px;\">Welcome to R.E.X! Thanks for creating an account! Once you verify your email, you are ready to access our client section.</font>\r\n"
				+ "    <br>\r\n"
				+ "    <a href=\"tomcat-rex.7e14.starter-us-west-2.openshiftapps.com/Activate?activate=true&id=" + id
				+ "\"\r\n" + "    class=\"button button-green\"\r\n"
				+ "    style=\"background: #53b662;border-radius: 0px;border: none;color: #FFFFFF;cursor: pointer;font-size: 1rem;float: left;line-height: 1.5;margin: 8px 0;padding: 6px 12px;vertical-align: middle;text-align: center;text-decoration: none;font-family: Arial,Helvetica,sans-serif;\"\r\n"
				+ "    >Verify your email</a>\r\n" + "    <br clear=\"all\">\r\n" + "    <br>\r\n"
				+ "    <font color=\"#4f4f4f\" face=\"Arial,Helvetica,sans-serif\" style=\"font-size:16px; line-height:28px;\">Thank you, and welcome to the Real Estate eXplorer!\r\n"
				+ "      <br><br>Sincerely,<br/>\r\n" + "      <b>The R.E.X</b></font>\r\n" + "    </div>\r\n"
				+ "    <div style=\"background-color:#343a40;color:#fff;font-family:Arial,Helvetica,sans-serif; padding: 12px 12px; font-size: 1.625rem\">\r\n"
				+ "      <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\r\n"
				+ "        <tbody>\r\n" + "          <tr>\r\n" + "            <td width=\"40%\">\r\n"
				+ "              <span style=\"color: #fff; font-size:1.25rem; font-weight: bold; padding: .3125rem 0px;\">R.E.X</span>\r\n"
				+ "            </td>\r\n" + "            <td>\r\n"
				+ "              <a href=\"tomcat-rex.7e14.starter-us-west-2.openshiftapps.com/#\"\r\n"
				+ "              face=\"Arial,Helvetica,sans-serif\"\r\n"
				+ "              style=\"color: rgba(255, 255, 255, .5);font-size:1rem;font-weight: 400;line-height:1.5; text-decoration: none;text-align: center;display:block;cursor: pointer\"\r\n"
				+ "              >About Us</a>\r\n" + "            </td>\r\n" + "\r\n" + "            <td>\r\n"
				+ "              <a href=\"tomcat-rex.7e14.starter-us-west-2.openshiftapps.com/#\"\r\n"
				+ "              face=\"Arial,Helvetica,sans-serif\"\r\n"
				+ "              style=\"color: rgba(255, 255, 255, .5);font-size:1rem;font-weight: 400;line-height:1.5; text-decoration: none;text-align: center;display:block;cursor: pointer\"\r\n"
				+ "              >Gallery</a>\r\n" + "            </td>\r\n" + "            <td>\r\n"
				+ "              <a href=\"tomcat-rex.7e14.starter-us-west-2.openshiftapps.com/#\"\r\n"
				+ "              face=\"Arial,Helvetica,sans-serif\"\r\n"
				+ "              style=\"color: rgba(255, 255, 255, .5);font-size:1rem;font-weight: 400;line-height:1.5; text-decoration: none;text-align: center;display:block;cursor: pointer\"\r\n"
				+ "              >Contact Us</a>\r\n" + "            </td>\r\n" + "          </tr>\r\n"
				+ "        </tbody>\r\n" + "      </table>\r\n" + "    </div>\r\n" + "  </body>\r\n" + "  </html>\r\n";
	}
}

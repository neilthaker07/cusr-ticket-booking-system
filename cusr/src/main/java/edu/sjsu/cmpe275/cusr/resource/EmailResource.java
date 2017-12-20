package edu.sjsu.cmpe275.cusr.resource;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.model.User;
import edu.sjsu.cmpe275.cusr.service.EmailService;
import edu.sjsu.cmpe275.cusr.service.TicketService;
import edu.sjsu.cmpe275.cusr.service.UserService;

@RestController
@CrossOrigin
public class EmailResource {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;
	
	private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

	
	@RequestMapping(method=RequestMethod.POST, value="/confirmEmail/{id}")
	public void sendConfrimationEmail(@PathVariable Long ticketId){
		
		Ticket ticket= ticketService.getTicketById(ticketId);
		
		User user=userService.getUserById(ticket.getUserId());
		
		String email="afreens.patel@gmail.com";
		
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(user.getEmailAddress()));
                mimeMessage.setFrom(new InternetAddress("mail@mycompany.com"));
                mimeMessage.setText(
                        "Dear user " + user + " "
                            + "User id"
                            + ", thank you for buying new tickets. Your ticket number is :  "
                             );
            }
        };

        try {
            this.mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
		
		
	}
	

	@RequestMapping(method=RequestMethod.POST, value="/cancelEmail/{id}")
	public void sendCancellationEmail(@PathVariable Long id){
		
		long userId= emailService.findUserbyTicketId(id);
		
		User user=userService.getUserById(userId);
		
		String email="afreens.patel@gmail.com";
		
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(user.getEmailAddress()));
                mimeMessage.setFrom(new InternetAddress("mail@mycompany.com"));
                mimeMessage.setText(
                        "Alert!! Dear user" + user + " "
                            + "User id"
                            + ", Please take into considration is that due technical glitch we are cancelling train scheduled on  "
                            + "train time " );
            }
        };

        try {
            this.mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
		
		
	}
	
}

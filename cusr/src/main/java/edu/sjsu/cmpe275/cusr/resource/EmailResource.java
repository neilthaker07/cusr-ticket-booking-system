package edu.sjsu.cmpe275.cusr.resource;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.model.Transaction;
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
	
	 @Autowired
	 private JavaMailSender javaMailSender;

    @Autowired
    public EmailResource(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

	
	@RequestMapping(method=RequestMethod.POST, value="/confirmEmail/{ticketId}")
	public void sendConfrimationEmail(@PathVariable Long ticketId) throws Exception {
		
		Ticket ticket= ticketService.getTicketById(ticketId);
		
		User user=userService.getUserById(ticket.getUserId());
		
		Transaction transaction = ticket.getTransaction();

	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo("afreens.patel@gmail.com");
	        mailMessage.setSubject("Your CUSR itinerary");
	        mailMessage.setText("Dear User,"+"\n"+"Thanks for your booking"+
	        " – your train is confirmed and your itinerary is below. \n \n" + 
	        		"Primary Email:"+user.getEmailAddress() +"\n"+
	        "Price: $"+transaction.getPrice()+"\n"+
	        		"Departure Station:" +ticket.getJourneyList().get(0).getSource() + "\n"+
	        "Arrival Station: "+ticket.getJourneyList().get(0).getDestination() +"\n" +
	        		"Number of Passenger:" + ticket.getPassengerList().size() + "\n"+
	        "Name of Passengers 1:" +ticket.getPassengerList().get(0).getFirstname() + " "
	        		+ ticket.getPassengerList().get(0).getLastname() );
	        mailMessage.setFrom("noreply@cusr.com");
	        javaMailSender.send(mailMessage);
	    
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
            this.javaMailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
		
		
	}
	
}

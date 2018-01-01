package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "message")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	private int id;
	
	@Column(name="message")
	private String messageText;
	
	@Column(name="author")
	private int author;
	
	@ManyToMany(mappedBy = "messages")
    private Set<UserProfile> userProfiles = new HashSet<>();
	
	public Message() {
	}

	public Message(String message, int authorId) {
		super();
		this.messageText = message;
		this.author = authorId;
	}
	
	    
	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	public String getAuthorName() {
		return UserProfile.getUserProfileFromDatabase(author).getUserName();
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public void send(int senderId, int recipientId) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Message.class)
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();	
		try {			
			session.beginTransaction();

			//UserProfile sender = session.get(UserProfile.class, senderId);
			UserProfile recipient = session.get(UserProfile.class, recipientId);
			recipient.getMessages().add(this);
			
			this.userProfiles.add(recipient);
			session.save(this);
		

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			factory.close();
		}
	}
	
	@Override
	public String toString() {
		return "author = " + author + " " + messageText;
	}
}

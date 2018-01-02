package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "colleague_request")
public class ColleagueRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="colleague_request_id")
	private int id;
	
	@Column(name="requester_id")
	private int requesterId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserProfile recipient;
	
	

	public ColleagueRequest() {	
	}
	

	public ColleagueRequest(int requester, UserProfile recipient) {
		super();
		this.requesterId = requester;
		this.recipient = recipient;
	}
	
	public static void sendCollegueRequest (int requesterId,int recipientId) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(ColleagueRequest.class)
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();	
		try {			
			session.beginTransaction();
						
			UserProfile requester = session.get(UserProfile.class, requesterId);
			UserProfile recipient = session.get(UserProfile.class, recipientId);
			
			ColleagueRequest colleagueRequest = new ColleagueRequest(requester.getId(), recipient);

			recipient.getColleagueRequests().add(colleagueRequest);	
			System.out.print("halo");

			session.save(colleagueRequest);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			factory.close();
		}		
	}

	public String getRequesterName() {
		return UserProfile.getUserProfileFromDatabase(requesterId).getUserName();
	}

	public int getRequesterId() {
		return requesterId;
	}


	public void setRequesterId(int requesterId) {
		this.requesterId = requesterId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserProfile getUserProfile() {
		return recipient;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.recipient = userProfile;
	}
	
	@Override
	public String toString() {
		return "requester = " + UserProfile.getUserProfileFromDatabase(requesterId);
	}
	
}

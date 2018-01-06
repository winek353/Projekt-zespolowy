package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class FriendRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="colleague_request_id")
	private int id;
	
	@Column(name="requester_id")
	private int requesterId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private UserProfile recipient;
	
	

	public FriendRequest() {	
	}
	

	public FriendRequest(int requester, UserProfile recipient) {
		super();
		this.requesterId = requester;
		this.recipient = recipient;
	}
	
	public static void sendFriendRequest (int requesterId,int recipientId) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(FriendRequest.class)
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();	
		try {			
			session.beginTransaction();
						
			UserProfile requester = session.get(UserProfile.class, requesterId);
			UserProfile recipient = session.get(UserProfile.class, recipientId);
			
			FriendRequest friendRequest = new FriendRequest(requester.getId(), recipient);

			recipient.getFriendRequests().add(friendRequest);	
			
			session.save(friendRequest);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			factory.close();
		}		
	}
	
	public static FriendRequest getFriendRequestFromDatabase (int id) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(FriendRequest.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		FriendRequest friendRequest = null;
		try {			
		session.beginTransaction();

		friendRequest = session.get(FriendRequest.class, id);
		
		session.getTransaction().commit();
		}finally {
			factory.close();
			
		}
		return friendRequest;
	}
	
	public void deleteFriendRequestFromDatabase () {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(FriendRequest.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		FriendRequest friendRequest = null;
		try {			
		session.beginTransaction();

		friendRequest = session.get(FriendRequest.class, id);
		
		session.delete(friendRequest);
		
		session.getTransaction().commit();
		}finally {
			factory.close();	
		}
	}
	

	public UserProfile getRecipient() {
		return recipient;
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

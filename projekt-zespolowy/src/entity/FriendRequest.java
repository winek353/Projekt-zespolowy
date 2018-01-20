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
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import logic.AlreadyFriends;
import logic.AlreadySentByFriend;
import logic.AlreadySentByUser;
import logic.CorrectFriend;
import logic.FriendWithSelf;
import logic.NotExistingFriend;
import logic.SendFriendRequestStrategy;

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
	
	public static SendFriendRequestStrategy selectSendFriendRequestStrategy
			(UserProfile userProfile,UserProfile friendProfile, HttpServletResponse response) {
		
		if(friendProfile == null)
			return new NotExistingFriend(userProfile, friendProfile, response);
		if(userProfile.getId() == friendProfile.getId())
			return new FriendWithSelf(userProfile, friendProfile, response);
		if(userProfile.isFriendRequestSent(friendProfile.getId())) 
			return new AlreadySentByFriend(userProfile, friendProfile, response);
		if(userProfile.isFriend(friendProfile.getId()))
			return new AlreadyFriends(userProfile, friendProfile, response);
		if(friendProfile.isFriendRequestSent(userProfile.getId()))
			return new AlreadySentByUser(userProfile, friendProfile, response);
		
		else
			return new CorrectFriend(userProfile, friendProfile, response);
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
		return UserProfile.getHibernateFacade().getFromDatabase(requesterId).getUserName();
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
		return "requester = " + UserProfile.getHibernateFacade().getFromDatabase(requesterId);
	}
	
}

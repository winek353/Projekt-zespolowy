package entity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import logic.PasswordHasher;

@Entity
@Table(name="user_profile")
public class UserProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_profile_id")
	private int id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password; //salt:hash
	
	//friend request
	@OneToMany(fetch = FetchType.EAGER, mappedBy="recipient")
    private Set<FriendRequest> friendRequests;
	
	
	//friends
	@ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
	@JoinTable(name="user_colleague",
		joinColumns={@JoinColumn(name="user_id")},
		inverseJoinColumns={@JoinColumn(name="colleague_id")})
	private Set<UserProfile> friends = new HashSet<UserProfile>();
	
	//messages
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(name = "user_profile_message", 
        joinColumns = { @JoinColumn(name = "user_profile_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "message_id") }
    )
    private Set<Message> messages = new HashSet<Message>();
	
	public UserProfile()  {		
		
	}

	public UserProfile(String userName, String email, String password)  {		
		this.userName = userName;
		this.email = email;
		try {
			this.password = PasswordHasher.generatePasswordHash(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}
	
	public  FriendRequest getFriendRequest (int requesterId) {
		for(FriendRequest friendRequest : friendRequests) {
			if(friendRequest.getRequesterId() == requesterId)
				return friendRequest;
		}
		return null; //exception???
	}

	public Set<FriendRequest> getFriendRequests() {
		return friendRequests;
	}

	public void setFriendRequests(Set<FriendRequest> friendRequests) {
		this.friendRequests = friendRequests;
	}

	public Set<UserProfile> getFriends() {
		return friends;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public void setfriendss(Set<UserProfile> friends) {
		this.friends = friends;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isFriendRequestSent(int requesterId) {

		for(FriendRequest friendRequest : this.friendRequests) {
			if(friendRequest.getRequesterId() == requesterId)
				return true;
		}
		return false;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", userName=" + userName + ", email=" + email + "]";
	}
	
	public void saveToDatabase () {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		try {			
			session.beginTransaction();
			
			session.save(this);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			factory.close();
		}
	}
	
	public static boolean isEmailInDatabase (String email) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		boolean result = false;
		try {			
		session.beginTransaction();

		result = !session.createQuery("from UserProfile up where up.email='" +email + "'").list().isEmpty();
		
		session.getTransaction().commit();
		}finally {
			factory.close();
		}
		return result;
	}
	public static boolean isUserNameInDatabase (String userName) {
		if(userName == null)
			return false;
		System.out.println("tutaj " + userName);
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		boolean result = false;
		try {			
		session.beginTransaction();
		

		result = !session.createQuery("from UserProfile up where up.userName='" + userName + "'").list().isEmpty();
		
		session.getTransaction().commit();
		}finally {
			factory.close();
		}
		return result;
	}
	public boolean isFriend (int friendId) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		boolean result = false;
		try {			
		session.beginTransaction();
		
		UserProfile userProfile = session.get(UserProfile.class, this.getId());
		UserProfile friendProfile = session.get(UserProfile.class, friendId);
		result = userProfile.friends.contains(friendProfile);
		
		session.getTransaction().commit();
		}finally {
			factory.close();
		}
		return result;
	}
	
	public void addFriend (UserProfile friendProfile) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();	
		try {			
			session.beginTransaction();

			UserProfile me = session.get(UserProfile.class, this.getId());
			UserProfile friend = session.get(UserProfile.class, friendProfile.getId());

			 me.getFriends().add(friend);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			factory.close();
		}
	}
	
	public static UserProfile getUserProfileFromDatabase (String userName) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		List <UserProfile> profile;
		try {			
		session.beginTransaction();

		profile = session.createQuery("from UserProfile up where up.userName='" + userName + "'").list();
		
		session.getTransaction().commit();
		}finally {
			factory.close();
		}
		if(profile.isEmpty())//do przerobienia
			return null;
		else {
			return profile.get(0);
		}
	}
	public static UserProfile getUserProfileFromDatabase (int id) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		UserProfile profile = null;
		try {			
		session.beginTransaction();

		profile = session.get(UserProfile.class, id);
		session.getTransaction().commit();
		}finally {
			factory.close();
			
		}
		return profile;
	}
}

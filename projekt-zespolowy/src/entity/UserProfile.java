package entity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import logic.PasswordHasher;

@Entity
@Table(name="user_profile")
public class UserProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password; //iterations:salt:hash

	
	public UserProfile() {
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + "]";
	}
	
	public void saveToDatabase () {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		try {			
			
			System.out.println(toString());

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
		System.out.println();
		if(profile.isEmpty())//do przerobienia
			return null;
		else {
			return profile.get(0);
		}
			
	}
	
}

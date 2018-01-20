package entity;

import java.util.HashSet;
import java.util.List;
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
@Table(name="event")
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="event_id")
	private int id;
	
	@Column(name="event_name")
	private String eventName;
	
	@Column(name="host")
	private int hostId;
	
	@ManyToMany(mappedBy = "events")
    private Set<UserProfile> userProfiles = new HashSet<>();
	
	public Event() {
	}

	
	public Event(String eventName, int hostId) {
		super();
		this.eventName = eventName;
		this.hostId = hostId;
	}
	
	public void create() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Message.class)
				.addAnnotatedClass(UserProfile.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();	
		try {			
			session.beginTransaction();

			UserProfile host = session.get(UserProfile.class, hostId);
			host.getEvents().add(this);
			
			this.userProfiles.add(host);
			session.save(this);
		
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			factory.close();
		}
	}
	
	public String getHostName() {
		return UserProfile.getHibernateFacade().getFromDatabase(hostId).getUserName();
	}
	
	public int getId() {
		return id;
	}
	
	public static List <Event> getAllEvents () {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Event.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		List <Event> events;
		try {			
		session.beginTransaction();

		events = session.createQuery("from Event").list();
		
		session.getTransaction().commit();
		}finally {
			factory.close();
		}
		if(events.isEmpty())
			return null;
		else {
			return events;
		}
	}

	public String getEventName() {
		return eventName;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	public int getHostId() {
		return hostId;
	}


	public void setHostId(int hostId) {
		this.hostId = hostId;
	}


	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}


	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	
	
	

}

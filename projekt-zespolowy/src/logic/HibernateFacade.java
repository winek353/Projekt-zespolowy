package logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.UserProfile;

public class HibernateFacade <T>{
	
	private final Class<T> type;
	
	public HibernateFacade(Class<T> type) {
		this.type = type;
	}

	public T getFromDatabase(int id) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(type)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		T takenObject = null;
		try {			
		session.beginTransaction();

		takenObject = (T) session.get(type, id);
		
		session.getTransaction().commit();
		}finally {
			factory.close();
			
		}
		return takenObject;
	}
	
	public void saveToDatabase (T objectToSave) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(type)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		try {			
			session.beginTransaction();
			
			session.save(objectToSave);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			factory.close();
		}
	}
}

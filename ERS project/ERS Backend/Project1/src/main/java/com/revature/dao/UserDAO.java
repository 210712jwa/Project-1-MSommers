package com.revature.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.model.User;
import com.revature.util.SessionFactorySingleton;

public class UserDAO {
	
	public User getUserByCredentials(String username, String password) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
//		Transaction tx = session.beginTransaction();
		
		try {
			User user = (User) session.createQuery("From User u WHERE u.username = :username AND u.password = :password")
					.setParameter("username", username)
					.setParameter("password", password)
					.getSingleResult();
			
//			tx.commit();
//			session.close();
			
			return user;
			
		} catch(NoResultException e) {
			
//			tx.commit();
//			session.close();
			
			return null;
		} finally {
			session.close();
		}

	}

}

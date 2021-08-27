package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.model.UserRole;

public class PopulateDataInDB {
	
	public static void main(String[] args) {
		popUserRoleReimStatReimType();
		popSampleUsers();
		
		
		//just adding reimbursments for bdavis
		addReimbursementBDavis();
	}

	private static void popUserRoleReimStatReimType() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		UserRole admin = new UserRole("admin");
		UserRole user = new UserRole("user");
		session.persist(user);
		session.persist(admin);
		
		ReimbursementStatus pending = new ReimbursementStatus("pending");
		ReimbursementStatus aproved = new ReimbursementStatus("approved");
		ReimbursementStatus denied = new ReimbursementStatus("denied");
		session.persist(aproved);
		session.persist(denied);
		session.persist(pending);
		
		ReimbursementType food = new ReimbursementType("food");
		ReimbursementType travel = new ReimbursementType("travel");
		ReimbursementType lodging = new ReimbursementType("lodging");
		ReimbursementType other = new ReimbursementType("other");
		session.persist(food);
		session.persist(travel);
		session.persist(lodging);
		session.persist(other);
		
		tx.commit();
		session.close();
	}
	
	private static void popSampleUsers() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User user1 = new User("Bob", "Davis", "BDavis@email.com", "bdavis","1234bd");
		UserRole user = (UserRole) session.createQuery("FROM UserRole ur WHERE ur.role ='user'").getSingleResult();
		user1.setUserRole(user);
		session.persist(user1);
		
		User user2 = new User("Jerry", "Smith", "JSmith@email.com", "jsmith","1234js");
		user2.setUserRole(user);
		session.persist(user2);
		
		User admin1 = new User("Sarah", "Lang", "SLang@email.com", "slang","1234sl");
		UserRole admin = (UserRole) session.createQuery("FROM UserRole ur WHERE ur.role ='admin'").getSingleResult();
		admin1.setUserRole(admin);
		session.persist(admin1);
		
		tx.commit();
		session.close();
		
	}
	
	private static void addReimbursementBDavis() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User bdavis = (User) session.createQuery("From User u WHERE u.username = 'bdavis'").getSingleResult();
		User jsmith = (User) session.createQuery("From User u WHERE u.username = 'jsmith'").getSingleResult();
		
		Reimbursement reim1 = new Reimbursement(123.22, "7/13/20", "7/17/20", "plane ticket for work purposes", "ticket receipt");
		Reimbursement reim2 = new Reimbursement(55.05, "8/5/20", "8/7/20", "business lunch", "lunch receipt");
		Reimbursement reim3 = new Reimbursement(3.45, "10/10/20", "10/12/20", "needed a lightbulb for my house", "bulb receipt");
		
		ReimbursementStatus pending = (ReimbursementStatus) session.createQuery("FROM ReimbursementStatus r WHERE r.rStatus = 'pending'").getSingleResult();		
		ReimbursementStatus approved = (ReimbursementStatus) session.createQuery("FROM ReimbursementStatus r WHERE r.rStatus = 'approved'").getSingleResult();
		ReimbursementStatus denied = (ReimbursementStatus) session.createQuery("FROM ReimbursementStatus r WHERE r.rStatus = 'denied'").getSingleResult();
		ReimbursementType food = (ReimbursementType) session.createQuery("FROM ReimbursementType t WHERE t.rType = 'food'").getSingleResult();
		ReimbursementType travel = (ReimbursementType) session.createQuery("FROM ReimbursementType t WHERE t.rType = 'travel'").getSingleResult();
		ReimbursementType other = (ReimbursementType) session.createQuery("FROM ReimbursementType t WHERE t.rType = 'other'").getSingleResult();
		
		reim1.setAuthor(bdavis);
		reim1.setrStatus(pending);
		reim1.setrType(travel);
		
		reim2.setAuthor(bdavis);
		reim2.setrStatus(approved);
		reim2.setrType(food);
		
		reim3.setAuthor(jsmith);
		reim3.setrStatus(denied);
		reim3.setrType(other);
				
		
		session.persist(reim1);
		session.persist(reim2);
		session.persist(reim3);
		
		
		tx.commit();
		session.close();
		
	}

}

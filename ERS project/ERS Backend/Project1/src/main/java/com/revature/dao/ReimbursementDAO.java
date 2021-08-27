package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.dto.AddOrEditReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.util.SessionFactorySingleton;

public class ReimbursementDAO {
	
	
	public List<Reimbursement> getAllReimbursementsFromUserId(int id) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Reimbursement> reims = session.createQuery("SELECT r FROM Reimbursement r JOIN r.author u WHERE u.uID = :userid").setParameter("userid", id).getResultList();
		
		tx.commit();
		
		session.close();
		
		return reims;
	}

	public List<Reimbursement> getAllReimbursements() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Reimbursement> reims = session.createQuery("SELECT r FROM Reimbursement r").getResultList();
		
		tx.commit();
		
		session.close();
		
		return reims;
	}

	public List<Reimbursement> getAllReimbursementsByStatus(int rStatusID) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Reimbursement> reims = session.createQuery("SELECT r FROM Reimbursement r JOIN r.rStatus s WHERE s.rSID = :statusID").setParameter("statusID", rStatusID).getResultList();
		
		tx.commit();
		
		session.close();
		
		return reims;
	}

	public Reimbursement editReimbursementById(int uID, int rID, AddOrEditReimbursementDTO reimStatusDto) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Reimbursement reims = session.get(Reimbursement.class, rID);
		
		reims.setResolver(session.get(User.class, uID));
		
		reims.setrStatus(session.get( ReimbursementStatus.class, reimStatusDto.getReimStatus() ) );
		
		
//where i would add timestamp resolved stuff
		
		
		session.saveOrUpdate(reims);
		
		tx.commit();
		
		session.close();
		
		return reims;
	}

	public Reimbursement createReimbursementById(int uID, AddOrEditReimbursementDTO reimToCreate) {
		//timestamp stuff
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Reimbursement createdReim = new Reimbursement();
		
			
		createdReim.setrSub(null);
		createdReim.setrRes(null);
		createdReim.setResolver(null);
		createdReim.setReceipt(null);
				
		createdReim.setDesc(reimToCreate.getReimDesc());
		createdReim.setrSub(reimToCreate.getrSub());
		createdReim.setrAmount(reimToCreate.getReimAmount());
		createdReim.setrType(session.get(ReimbursementType.class, reimToCreate.getReimType()));
		
		createdReim.setAuthor(session.get(User.class, uID));
		createdReim.setrStatus(session.get(ReimbursementStatus.class, 3));
			
		session.persist(createdReim);
		
		tx.commit();
		
		session.close();
		
		return createdReim;
	}

}

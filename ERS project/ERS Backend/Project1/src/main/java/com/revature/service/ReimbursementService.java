package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dto.AddOrEditReimbursementDTO;
import com.revature.model.Reimbursement;

public class ReimbursementService {
	
	private ReimbursementDAO reimDao;
	
	public ReimbursementService() {
		this.reimDao = new ReimbursementDAO();
	}

	public List<Reimbursement> getAllReimbursementsFromUserId(String userId) {
		
		int id = Integer.parseInt(userId);
		
		List<Reimbursement> reims = reimDao.getAllReimbursementsFromUserId(id);
		
		return reims;
	}

	public List<Reimbursement> getAllReimbursements() {
		
		List<Reimbursement> reims = reimDao.getAllReimbursements();
		
		return reims;
	}

	public List<Reimbursement> getAllReimbursementsByStatus(String rStatus) {
		int rStatusID = Integer.parseInt(rStatus);
		
		List<Reimbursement> reimbursements = reimDao.getAllReimbursementsByStatus(rStatusID);
		
		return reimbursements;
	}

	public Reimbursement editReimById(String uId, String reimbId, AddOrEditReimbursementDTO reimStatusDto) {

		int rID = Integer.parseInt(reimbId);
		
		int uID = Integer.parseInt(uId);
		
		Reimbursement editedReim = reimDao.editReimbursementById(uID, rID, reimStatusDto);
		
		return editedReim;
	}

	public Reimbursement creatReimbursementById(String uId, AddOrEditReimbursementDTO reimToCreate) {
		
		int uID = Integer.parseInt(uId);
		
		Reimbursement createdReim = reimDao.createReimbursementById(uID, reimToCreate);
		
		return createdReim;
	}

	

}

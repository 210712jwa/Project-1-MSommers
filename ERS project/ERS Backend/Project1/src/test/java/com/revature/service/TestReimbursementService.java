package com.revature.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.UserDAO;
import com.revature.dto.AddOrEditReimbursementDTO;
//import com.revature.dto.AddOrEditClientDTO;
//import com.revature.exception.ClientNotFoundException;
//import com.revature.exception.DBException;
import com.revature.exception.InvalidInputException;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class TestReimbursementService {

	private ReimbursementService reimbursementService;
	private UserDAO userDao;
	private ReimbursementDAO reimbursementDao;

	@Before
	public void setUp() throws Exception {
		this.userDao = mock(UserDAO.class);

		this.reimbursementDao = mock(ReimbursementDAO.class);

		this.reimbursementService = new ReimbursementService(); 
	}

	@Test(expected = NumberFormatException.class)
	public void test_getAllReimbursementsFromUser_negative()
			throws InvalidInputException, SQLException {

		when(userDao.getUserByCredentials(eq("aa"),eq("bb"))).thenReturn(new User("Bob", "Davis", "BDavis@email.com", "bdavis","1234bd"));

		List<Reimbursement> mockReimbursements = new ArrayList<>();
		mockReimbursements.add(new Reimbursement(123.22, "10:30", "10:31", "plane ticket for work purposes", "ticket receipt"));
		mockReimbursements.add(new Reimbursement(55.05, "10:35", "10:36", "business lunch", "lunch receipt"));

		when(reimbursementDao.getAllReimbursementsFromUserId(eq(888))).thenReturn(mockReimbursements);

		List<Reimbursement> actual = reimbursementService.getAllReimbursementsFromUserId("bbb");

		assertEquals(mockReimbursements, actual);
	}
	
	
	
	@Test(expected = NumberFormatException.class)
	public void test_editReimById_negative()throws InvalidInputException, SQLException {
	
		AddOrEditReimbursementDTO reimStatusDto = new AddOrEditReimbursementDTO();	
		
	reimbursementService.editReimById("bob", "1", reimStatusDto);
	}
	
	
	@Test(expected = NumberFormatException.class)
	public void test_editReimById_negative2()throws InvalidInputException, SQLException {
	
		AddOrEditReimbursementDTO reimStatusDto = new AddOrEditReimbursementDTO();	
		
	reimbursementService.editReimById("bob", "troy", reimStatusDto);
	}
	
	@Test(expected = NumberFormatException.class)
	public void test_editReimById_negative3()throws InvalidInputException, SQLException {
	
		AddOrEditReimbursementDTO reimStatusDto = new AddOrEditReimbursementDTO();	
		
	reimbursementService.editReimById("1", "troy", reimStatusDto);
	}
	
	@Test
	public void test_editReimById_positive()throws InvalidInputException, SQLException {
	
		AddOrEditReimbursementDTO reimStatusDto = new AddOrEditReimbursementDTO();	
		
	reimbursementService.editReimById("1", "1", reimStatusDto);
	}
		
	
	@Test(expected = NumberFormatException.class)
	public void test_createReimById_negative()throws InvalidInputException, SQLException {
	
		AddOrEditReimbursementDTO reimToCreate = new AddOrEditReimbursementDTO();	
		
		reimbursementService.creatReimbursementById("bob", reimToCreate);
	}
	
	
	@Test(expected = NumberFormatException.class)
	public void test_getReimByStatus_negative()throws InvalidInputException, SQLException {
		
		reimbursementService.getAllReimbursementsByStatus("bob");
	}
	
	

}

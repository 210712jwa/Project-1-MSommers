package com.revature.service;

import static org.junit.Assert.*;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.exceptions.base.MockitoException;

import com.revature.dao.UserDAO;
import com.revature.dto.LoginDTO;
import com.revature.exception.InvalidCredentialException;
import com.revature.exception.InvalidInputException;
import com.revature.model.User;

public class TestLoginService {

	private LoginService loginService;
	private UserDAO userDao;

	@Before
	public void setUp() throws Exception {
		this.userDao = mock(UserDAO.class);

		this.loginService = new LoginService(); 
	}
		
	
	@Test
	public void test_getUserByCredentials_positive() throws SQLException, InvalidCredentialException, InvalidInputException {
		when(userDao.getUserByCredentials(eq("www"),eq("eee"))).thenReturn(new User("Bob", "Davis", "BDavis@email.com", "bdavis","1234bd"));
		
		User actual = userDao.getUserByCredentials("www", "eee");
		
		User expected = new User("Bob", "Davis", "BDavis@email.com", "bdavis","1234bd");;
		
		assertEquals(expected, actual);
	}
	
	
	@Test(expected = InvalidInputException.class)
	public void test_getAccountsById_negative() throws InvalidInputException, InvalidCredentialException {
		LoginDTO loginDto = new LoginDTO();
		loginDto.setPassword("");
		loginDto.setUsername("");
		
		
		loginService.login(loginDto);
	}
	
	@Test(expected = InvalidInputException.class)
	public void test_getAccountsById_blankpassword() throws InvalidInputException, InvalidCredentialException {
		LoginDTO loginDto = new LoginDTO();
		loginDto.setPassword("");
		loginDto.setUsername("dgsd");
		
		
		loginService.login(loginDto);
	}
	
	@Test(expected = InvalidInputException.class)
	public void test_getAccountsById_blankusername() throws InvalidInputException, InvalidCredentialException {
		LoginDTO loginDto = new LoginDTO();
		loginDto.setPassword("sdfg");
		loginDto.setUsername("");
		
		
		loginService.login(loginDto);
	}
	
	@Test(expected = InvalidCredentialException.class)
	public void test_getAccountsById_invalidcredentials() throws InvalidInputException, InvalidCredentialException {
		LoginDTO loginDto = new LoginDTO();
		loginDto.setPassword("sdfg");
		loginDto.setUsername("szdf");
		
		
		loginService.login(loginDto);
	}
	
	
//	@Test(expected = MockitoException.class)
//	public void test_getUserByCredentials_negative() throws InvalidInputException {
//		when(userDao.getUserByCredentials("a","a")).thenThrow(InvalidInputException.class);
//		userDao.getUserByCredentials("a","a");
//	}
	
	
	

		

}

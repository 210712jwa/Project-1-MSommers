package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dto.LoginDTO;
import com.revature.exception.InvalidCredentialException;
import com.revature.exception.InvalidInputException;
import com.revature.model.User;

public class LoginService {
	
	private UserDAO userDao;
	
	public LoginService() {
		this.userDao = new UserDAO();
	}
	
	public User login(LoginDTO loginDTO) throws InvalidInputException, InvalidCredentialException {
		
		if(loginDTO.getUsername().trim().equals("") && loginDTO.getPassword().equals("")) {
			throw new InvalidInputException("Username and password can not be left blank");
		}
		if(loginDTO.getUsername().trim().equals("")) {
			throw new InvalidInputException("Username can not be left blank");
		}
		if(loginDTO.getPassword().equals("")) {
			throw new InvalidInputException("Password can not be left blank");
		}
		
		User user = userDao.getUserByCredentials(loginDTO.getUsername(), loginDTO.getPassword());
		
		if(user == null) {
			throw new InvalidCredentialException("The password and/or username you have entered is incorrect");
		}
		
		return user;
	}

}

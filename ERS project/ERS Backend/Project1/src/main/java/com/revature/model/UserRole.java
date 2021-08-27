package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userRole")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uID")
	private int uID;
	
	@Column(name = "role", nullable = false, unique = true)
	private String role; //for "admin" or "user"
	
	

	public UserRole() {
		super();
	}



	public UserRole(String role) {
		super();
		this.role = role;
	}



	public int getuID() {
		return uID;
	}



	public void setuID(int uID) {
		this.uID = uID;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + uID;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserRole other = (UserRole) obj;
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (uID != other.uID) {
			return false;
		}
		return true;
	}



	@Override
	public String toString() {
		return "UserRole [uID=" + uID + ", role=" + role + "]";
	}

	
	
	

}

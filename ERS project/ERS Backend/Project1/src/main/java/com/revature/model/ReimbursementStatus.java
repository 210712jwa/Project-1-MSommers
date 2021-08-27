package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursementStatus")
public class ReimbursementStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rSID")
	private int rSID;
	
	@Column(name = "rStatus")
	private String rStatus; //pending, denied, aproved
	
	
	

	public ReimbursementStatus() {
		super();
	}




	public ReimbursementStatus(String rStatus) {
		super();
		this.rStatus = rStatus;
	}




	public int getrSID() {
		return rSID;
	}




	public void setrSID(int rSID) {
		this.rSID = rSID;
	}




	public String getrStatus() {
		return rStatus;
	}




	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rSID;
		result = prime * result + ((rStatus == null) ? 0 : rStatus.hashCode());
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
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (rSID != other.rSID) {
			return false;
		}
		if (rStatus == null) {
			if (other.rStatus != null) {
				return false;
			}
		} else if (!rStatus.equals(other.rStatus)) {
			return false;
		}
		return true;
	}




	@Override
	public String toString() {
		return "ReimbursementStatus [rSID=" + rSID + ", rStatus=" + rStatus + "]";
	}
	
	
	
	

}

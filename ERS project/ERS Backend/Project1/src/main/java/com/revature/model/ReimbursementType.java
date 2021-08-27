package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursementType")
public class ReimbursementType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rTID")
	private int rTID;
	
	@Column(name = "rType")
	private String rType; //lodging, travel, food, other
	
	
	

	public ReimbursementType() {
		super();
	}



	public ReimbursementType(String rType) {
		super();
		this.rType = rType;
	}



	public int getrTID() {
		return rTID;
	}



	public void setrTID(int rTID) {
		this.rTID = rTID;
	}



	public String getrType() {
		return rType;
	}



	public void setrType(String rType) {
		this.rType = rType;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rTID;
		result = prime * result + ((rType == null) ? 0 : rType.hashCode());
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
		ReimbursementType other = (ReimbursementType) obj;
		if (rTID != other.rTID) {
			return false;
		}
		if (rType == null) {
			if (other.rType != null) {
				return false;
			}
		} else if (!rType.equals(other.rType)) {
			return false;
		}
		return true;
	}



	@Override
	public String toString() {
		return "ReimbursementType [rTID=" + rTID + ", rType=" + rType + "]";
	}
	
	
	

}

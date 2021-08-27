package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rID")
	private int rID;
	
	@Column(name = "Amount")
	private double rAmount;
	
	@Column(name = "rSubmitted")
	private String rSub;
	
	@Column(name = "rResolved")
	private String rRes;
	
	@Column(name = "description")
	private String desc;
	
	@Column(name = "receipt")
	private String receipt;
	
	
	// , nullable = false
	//was added below in joincolumn for author
	
	@ManyToOne
	@JoinColumn(name = "authorID")
	private User author;
	
	@ManyToOne
	@JoinColumn(name = "resolverID")
	private User resolver;
	
	@ManyToOne
	@JoinColumn(name = "rStatusID")
	private ReimbursementStatus rStatus;
	
	@ManyToOne
	@JoinColumn(name = "rTypeID")
	private ReimbursementType rType;
	
	

	public Reimbursement() {
		super();
	}

	
	
	
	public Reimbursement(double rAmount, String rSub, String rRes, String desc, String receipt) {
		super();
		this.rAmount = rAmount;
		this.rSub = rSub;
		this.rRes = rRes;
		this.desc = desc;
		this.receipt = receipt;
	}




	public int getrID() {
		return rID;
	}




	public void setrID(int rID) {
		this.rID = rID;
	}




	public double getrAmount() {
		return rAmount;
	}




	public void setrAmount(double rAmount) {
		this.rAmount = rAmount;
	}




	public String getrSub() {
		return rSub;
	}




	public void setrSub(String rSub) {
		this.rSub = rSub;
	}




	public String getrRes() {
		return rRes;
	}




	public void setrRes(String rRes) {
		this.rRes = rRes;
	}




	public String getDesc() {
		return desc;
	}




	public void setDesc(String desc) {
		this.desc = desc;
	}




	public String getReceipt() {
		return receipt;
	}




	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}




	public User getAuthor() {
		return author;
	}




	public void setAuthor(User author) {
		this.author = author;
	}




	public User getResolver() {
		return resolver;
	}




	public void setResolver(User resolver) {
		this.resolver = resolver;
	}




	public ReimbursementStatus getrStatus() {
		return rStatus;
	}




	public void setrStatus(ReimbursementStatus rStatus) {
		this.rStatus = rStatus;
	}




	public ReimbursementType getrType() {
		return rType;
	}




	public void setrType(ReimbursementType rType) {
		this.rType = rType;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + rID;
		result = prime * result + ((rRes == null) ? 0 : rRes.hashCode());
		result = prime * result + ((rStatus == null) ? 0 : rStatus.hashCode());
		result = prime * result + ((rSub == null) ? 0 : rSub.hashCode());
		result = prime * result + ((rType == null) ? 0 : rType.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (author == null) {
			if (other.author != null) {
				return false;
			}
		} else if (!author.equals(other.author)) {
			return false;
		}
		if (desc == null) {
			if (other.desc != null) {
				return false;
			}
		} else if (!desc.equals(other.desc)) {
			return false;
		}
		if (Double.doubleToLongBits(rAmount) != Double.doubleToLongBits(other.rAmount)) {
			return false;
		}
		if (rID != other.rID) {
			return false;
		}
		if (rRes == null) {
			if (other.rRes != null) {
				return false;
			}
		} else if (!rRes.equals(other.rRes)) {
			return false;
		}
		if (rStatus == null) {
			if (other.rStatus != null) {
				return false;
			}
		} else if (!rStatus.equals(other.rStatus)) {
			return false;
		}
		if (rSub == null) {
			if (other.rSub != null) {
				return false;
			}
		} else if (!rSub.equals(other.rSub)) {
			return false;
		}
		if (rType == null) {
			if (other.rType != null) {
				return false;
			}
		} else if (!rType.equals(other.rType)) {
			return false;
		}
		if (receipt == null) {
			if (other.receipt != null) {
				return false;
			}
		} else if (!receipt.equals(other.receipt)) {
			return false;
		}
		if (resolver == null) {
			if (other.resolver != null) {
				return false;
			}
		} else if (!resolver.equals(other.resolver)) {
			return false;
		}
		return true;
	}




	@Override
	public String toString() {
		return "Reimbursement [rID=" + rID + ", rAmount=" + rAmount + ", rSub=" + rSub + ", rRes=" + rRes + ", desc="
				+ desc + ", receipt=" + receipt + ", author=" + author + ", resolver=" + resolver + ", rStatus="
				+ rStatus + ", rType=" + rType + "]";
	}


	
	
}

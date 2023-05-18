package sn.intouch.gu.api.ejb.models;

import java.io.Serializable;
import java.util.Date;

public class ActiviteModel implements Serializable {
	private Date dateOp;
    private String typeOperation;
    private String referenceTransaction;
    private Double montant;
    private String operateur;
	public Date getDateOp() {
		return dateOp;
	}
	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}
	public String getTypeOperation() {
		return typeOperation;
	}
	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}
	public String getReferenceTransaction() {
		return referenceTransaction;
	}
	public void setReferenceTransaction(String referenceTransaction) {
		this.referenceTransaction = referenceTransaction;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public String getOperateur() {
		return operateur;
	}
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
    
    

}

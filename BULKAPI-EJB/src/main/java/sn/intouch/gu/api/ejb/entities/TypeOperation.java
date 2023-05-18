package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="typeoperation")
public class TypeOperation implements Serializable{
	
	private Long idTypeOp;
	private String codeTypeOp,libelleTypeOp;
	Boolean supprime = false;
	
	
	@Id
	@Column(name = "idTypeOp")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdTypeOp() {
		return idTypeOp;
	}
	public void setIdTypeOp(Long idTypeOp) {
		this.idTypeOp = idTypeOp;
	}
	
	@Column(name = "codeTypeOp")
	public String getCodeTypeOp() {
		return codeTypeOp;
	}
	public void setCodeTypeOp(String codeTypeOp) {
		this.codeTypeOp = codeTypeOp;
	}
	
	@Column(name = "libelleTypeOp")
	public String getLibelleTypeOp() {
		return libelleTypeOp;
	}
	public void setLibelleTypeOp(String libelleTypeOp) {
		this.libelleTypeOp = libelleTypeOp;
	}
	
	@Column(name = "typeOpSupp")
	public Boolean getSupprime() {
		return supprime;
	}
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	@Override
	public String toString() {
		return "TypeOperation [libelleTypeOp=" + libelleTypeOp + "]";
	}
	
	
	
}

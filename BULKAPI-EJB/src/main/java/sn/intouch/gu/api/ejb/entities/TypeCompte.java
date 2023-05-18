package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="typecompte")
public class TypeCompte implements Serializable{

	private Long idtypeCompte;
	private Integer codeTypeCompte;
	private String typeCompte;
	
	@Id
	@Column(name = "idtypeCompte")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdtypeCompte() {
		return idtypeCompte;
	}
	public void setIdtypeCompte(Long idtypeCompte) {
		this.idtypeCompte = idtypeCompte;
	}
	@Column(name = "codeTypeCompte")
	public Integer getCodeTypeCompte() {
		return codeTypeCompte;
	}
	public void setCodeTypeCompte(Integer codeTypeCompte) {
		this.codeTypeCompte = codeTypeCompte;
	}
	@Column(name = "typeCompte")
	public String getTypeCompte() {
		return typeCompte;
	}
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	
	
}

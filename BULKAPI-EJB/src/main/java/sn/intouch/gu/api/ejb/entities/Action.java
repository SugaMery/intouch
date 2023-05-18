package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "actions")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Action implements Serializable {
	String code, description;
	Fonctionnalite fonctionnalite;
	Boolean supprime = false ;

	@Id
	@Column(name = "ACT_CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "ACT_DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "ACT_FCNT_CODE")
	public Fonctionnalite getFonctionnalite() {
		return fonctionnalite;
	}

	public void setFonctionnalite(Fonctionnalite fonctionnalite) {
		this.fonctionnalite = fonctionnalite;
	}
	

	@Column( name = "ACT_SUPPRIME")
	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	
	@Override
	public int hashCode() {
	return code.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
	if (obj == null) {
	return false;
	}
	if (! (obj instanceof Action)) {
	return false;
	}
	return this.code == ((Action)obj).getCode();
	}

}
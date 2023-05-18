package sn.intouch.gu.api.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "service_sub_category")
public class ServiceSubCategory implements Serializable {
	
	private String service_SC_nom, service_SC_code;
	private Long service_SC_id; 
	private CategoryS service_C_id;
	Boolean supprime = false ;
	

	@Id
	@Column(name = "service_sc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getService_SC_id() {
		return service_SC_id;
	}
	public void setService_SC_id(Long service_SC_id) {
		this.service_SC_id = service_SC_id;
	}
	
	@Column(name = "service_sc_code")
	public String getService_SC_code() {
		return service_SC_code;
	}
	public void setService_SC_code(String service_SC_code) {
		this.service_SC_code = service_SC_code;
	}
	@Column(name = "service_sc_nom")
	public String getService_SC_nom() {
		return service_SC_nom;
	}
	public void setService_SC_nom(String service_SC_nom) {
		this.service_SC_nom = service_SC_nom;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_C_id")
	public CategoryS getService_C_id() {
		return service_C_id;
	}
	
	public void setService_C_id(CategoryS service_C_id) {
		this.service_C_id = service_C_id;
	}
	
	@Column(name = "fnct_sc_sup")
	public Boolean getSupprime() {
		return supprime;
	}
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	
	
}
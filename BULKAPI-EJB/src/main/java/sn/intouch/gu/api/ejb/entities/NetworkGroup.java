package sn.intouch.gu.api.ejb.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "networkgroup")
public class NetworkGroup implements Serializable{
	private Long network_group_id;
	private String network_group_nom, network_group_code;
	private String network_group_nomenclature;
	Boolean supprime =false;
	private PaysISO paysISO;
	
	@Id
	@Column(name = "network_group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getNetwork_group_id() {
		return network_group_id;
	}
	public void setNetwork_group_id(Long network_group_id) {
		this.network_group_id = network_group_id;
	}
	
	@Column(name = "network_group_nom")
	public String getNetwork_group_nom() {
		return network_group_nom;
	}
	public void setNetwork_group_nom(String network_group_nom) {
		this.network_group_nom = network_group_nom;
	}
	
	@Column(name = "network_group_code")
	public String getNetwork_group_code() {
		return network_group_code;
	}
	public void setNetwork_group_code(String network_group_code) {
		this.network_group_code = network_group_code;
	}
	
	@Column(name = "supprime")
	public Boolean getSupprime() {
		return supprime;
	}
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	public String getNetwork_group_nomenclature() {
		return network_group_nomenclature;
	}
	@Column(name = "network_group_nomenclature")
	public void setNetwork_group_nomenclature(String network_group_nomenclature) {
		this.network_group_nomenclature = network_group_nomenclature;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pays_iso_id")
	public PaysISO getPaysISO() {
		return paysISO;
	}

	public void setPaysISO(PaysISO paysISO) {
		this.paysISO = paysISO;
	}
}

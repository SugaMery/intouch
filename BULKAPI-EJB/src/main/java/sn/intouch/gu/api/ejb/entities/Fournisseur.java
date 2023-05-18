package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "fournisseur")
public class Fournisseur implements Serializable {
	
	private Long fournisseur_id;
	private String fournisseur_nom;
	private String fournisseur_ccu;
	private String fournisseur_ccb;
	private String fournisseur_code;
	private String fournisseur_image;
	private String intouch_compte;
	Boolean supprime = false ;
	private NetworkGroup networkgroup_id;
	
	@Id
	@Column(name = "fournisseur_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getFournisseur_id() {
		return fournisseur_id;
	}
	public void setFournisseur_id(Long fournisseur_id) {
		this.fournisseur_id = fournisseur_id;
	}
	@Column(name = "fournisseur_code")
	public String getFournisseur_code() {
		return fournisseur_code;
	}
	public void setFournisseur_code(String fournisseur_code) {
		this.fournisseur_code = fournisseur_code;
	}
	@Column(name = "fournisseur_image")
	public String getFournisseur_image() {
		return fournisseur_image;
	}
	public void setFournisseur_image(String fournisseur_image) {
		this.fournisseur_image = fournisseur_image;
	}
	@Column(name = "fournisseur_ccu")
	public String getFournisseur_ccu() {
		return fournisseur_ccu;
	}
	public void setFournisseur_ccu(String fournisseur_ccu) {
		this.fournisseur_ccu = fournisseur_ccu;
	}
	
	@Column(name = "fournisseur_ccb")
	public String getFournisseur_ccb() {
		return fournisseur_ccb;
	}
	public void setFournisseur_ccb(String fournisseur_ccb) {
		this.fournisseur_ccb = fournisseur_ccb;
	}
	@Column(name = "fournisseur_nom")
	public String getFournisseur_nom() {
		return fournisseur_nom;
	}
	public void setFournisseur_nom(String fournisseur_nom) {
		this.fournisseur_nom = fournisseur_nom;
	}
	@Column(name = "intouch_compte")
	public String getIntouch_compte() {
		return intouch_compte;
	}
	public void setIntouch_compte(String intouch_compte) {
		this.intouch_compte = intouch_compte;
	}
	@Column(name = "fnct_f_sup")
	public Boolean getSupprime() {
		return supprime;
	}
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "networkgroup_id")
	public NetworkGroup getNetworkgroup_id() {
		return networkgroup_id;
	}
	public void setNetworkgroup_id(NetworkGroup networkgroup_id) {
		this.networkgroup_id = networkgroup_id;
	}
}

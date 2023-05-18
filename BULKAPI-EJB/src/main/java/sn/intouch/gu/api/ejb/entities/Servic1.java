package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
@Entity
@Table(name = "gestionservice")
public class Servic1 implements Serializable{
	
	private Long service1_id;
	Boolean service1_caissier, service1_itinerant, service1_pompiste;
	Boolean supprime = false;
	Boolean defaut;
	private String statut, commissionnement;
	private Network network_id;
	private Servic service_id;
	private List <SalePoint> salePoints ;
	
	@Id
	@Column(name = "service1_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getService1_id() {
		return service1_id;
	}
	public void setService1_id(Long service1_id) {
		this.service1_id = service1_id;
	}
	@Column(name = "service1_statut")
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	@Column(name = "service1_commissionnement")
	public String getCommissionnement() {
		return commissionnement;
	}
	public void setCommissionnement(String commissionnement) {
		this.commissionnement = commissionnement;
	}
	@Column(name = "service1_caissier")
	public Boolean getService1_caissier() {
		return service1_caissier;
	}
	public void setService1_caissier(Boolean service1_caissier) {
		this.service1_caissier = service1_caissier;
	}
	@Column(name = "service1_defaut")
	public Boolean getDefaut() {
		return defaut;
	}
	public void setDefaut(Boolean defaut) {
		this.defaut = defaut;
	}
	@Column(name = "service1_itinerant")
	public Boolean getService1_itinerant() {
		return service1_itinerant;
	}
	public void setService1_itinerant(Boolean service1_itinerant) {
		this.service1_itinerant = service1_itinerant;
	}
	@Column(name = "service1_pompiste")
	public Boolean getService1_pompiste() {
		return service1_pompiste;
	}
	public void setService1_pompiste(Boolean service1_pompiste) {
		this.service1_pompiste = service1_pompiste;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "network_id")
	public Network getNetwork_id() {
		return network_id;
	}
	public void setNetwork_id(Network network_id) {
		this.network_id = network_id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_id")
	public Servic getService_id() {
		return service_id;
	}
	public void setService_id(Servic service_id) {
		this.service_id = service_id;
	}
	@Column(name = "service1_supprime")
	public Boolean getSupprime() {
		return supprime;
	}
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	@OneToMany
	@Column(name = "service1_salePoints")
	public List<SalePoint> getSalePoints() {
		return salePoints;
	}
	public void setSalePoints(List<SalePoint> salePoints) {
		this.salePoints = salePoints;
	}
	
}

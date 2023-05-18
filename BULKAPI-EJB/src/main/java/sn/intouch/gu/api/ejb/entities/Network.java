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
@Table(name = "network")
@NamedQuery(name=Network.FIND_BY_NAME, query="SELECT n FROM Network n WHERE n.network_nom=:nom AND n.supprime=false")
public class Network implements Serializable{
	public static final String FIND_BY_NAME = "findByNetworkName"; 
	private Long network_id, network_numeroFax, network_telephone_C;
	private String network_nom, network_code, network_prenom_C, network_nom_C, network_mail_C, network_adresse,
	network_nom_commercial, network_num_ninea;
	private Double network_Plafond , network_solde_UV , network_DMA;
	private List<SalePoint> salePoints;
	Boolean supprime =false;
	Boolean type;
	
	
	@Id
	@Column(name = "network_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getNetwork_id() {
		return network_id;
	}
	public void setNetwork_id(Long network_id) {
		this.network_id = network_id;
	}
	@Column(name = "network_code")
	public String getNetwork_code() {
		return network_code;
	}
	public void setNetwork_code(String network_code) {
		this.network_code = network_code;
	}
	@Column(name = "network_numeroFax")
	public Long getNetwork_numeroFax() {
		return network_numeroFax;
	}
	public void setNetwork_numeroFax(Long network_numeroFax) {
		this.network_numeroFax = network_numeroFax;
	}
	@Column(name = "network_telephone_C")
	public Long getNetwork_telephone_C() {
		return network_telephone_C;
	}
	public void setNetwork_telephone_C(Long network_telephone_C) {
		this.network_telephone_C = network_telephone_C;
	}
	@Column(name = "network_nom")
	public String getNetwork_nom() {
		return network_nom;
	}
	public void setNetwork_nom(String network_nom) {
		this.network_nom = network_nom;
	}
	@Column(name = "network_prenom_C")
	public String getNetwork_prenom_C() {
		return network_prenom_C;
	}
	public void setNetwork_prenom_C(String network_prenom_C) {
		this.network_prenom_C = network_prenom_C;
	}
	@Column(name = "network_nom_C")
	public String getNetwork_nom_C() {
		return network_nom_C;
	}
	public void setNetwork_nom_C(String network_nom_C) {
		this.network_nom_C = network_nom_C;
	}
	@Column(name = "network_mail_C")
	public String getNetwork_mail_C() {
		return network_mail_C;
	}
	public void setNetwork_mail_C(String network_mail_C) {
		this.network_mail_C = network_mail_C;
	}
	@Column(name = "network_adresse")
	public String getNetwork_adresse() {
		return network_adresse;
	}
	public void setNetwork_adresse(String network_adresse) {
		this.network_adresse = network_adresse;
	}
	@Column(name = "network_nom_commercial")
	public String getNetwork_nom_commercial() {
		return network_nom_commercial;
	}
	public void setNetwork_nom_commercial(String network_nom_commercial) {
		this.network_nom_commercial = network_nom_commercial;
	}
	@Column(name = "network_num_ninea")
	public String getNetwork_num_ninea() {
		return network_num_ninea;
	}
	public void setNetwork_num_ninea(String network_num_ninea) {
		this.network_num_ninea = network_num_ninea;
	}

	@Column(name = "network_solde_UV")
	public Double getNetwork_solde_UV() {
		return network_solde_UV;
	}
	public void setNetwork_solde_UV(Double network_solde_UV) {
		this.network_solde_UV = network_solde_UV;
	}

	@Column(name = "fnct_network_sup")
	public Boolean getSupprime() {
		return supprime;
	}
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="network_id")
	public List<SalePoint> getSalePoints() {
		return salePoints;
	}
	public void setSalePoints(List<SalePoint> salePoints) {
		this.salePoints = salePoints;
	}
	@Column(name = "network_plafond")
	public Double getNetwork_Plafond() {
		return network_Plafond;
	}
	public void setNetwork_Plafond(Double network_Plafond) {
		this.network_Plafond = network_Plafond;
	}
	
	@Column(name = "network_dma")
	public Double getNetwork_DMA() {
		return network_DMA;
	}
	public void setNetwork_DMA(Double network_DMA) {
		this.network_DMA = network_DMA;
	}
	@Column(name = "network_type")
	public Boolean getType() {
		return type;
	}
	public void setType(Boolean type) {
		this.type = type;
	}
	
	
	

}

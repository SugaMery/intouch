package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gerant")
public class Supervisor extends Utilisateur implements Serializable {

	private SalePoint salePoint;
	private String gerTelephone, codeSapGerant;
	private String gerNom, gerPrenom, gerLogin, gerPassword, gerEtat = "ACTIF", simNumber = "777777777";
	private Boolean supprime = false;
	private int gerStatut = 1, gerHasConnect = 0;
	private String statut;
	private String reseaux;

	// private Network network_id;
	public Supervisor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "statut")
	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "network_id")
	// public Network getNetwork_id() {
	// return network_id;
	// }
	//
	// public void setNetwork_id(Network network_id) {
	// this.network_id = network_id;
	// }

	@Column(name = "ger_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ger_login")
	public String getGerLogin() {
		return gerLogin;
	}

	public void setGerLogin(String gerLogin) {
		this.gerLogin = gerLogin;
	}

	@Column(name = "ger_nom")
	public String getGerNom() {
		return gerNom;
	}

	public void setGerNom(String gerNom) {
		this.gerNom = gerNom;
	}

	@Column(name = "ger_prenom")
	public String getGerPrenom() {
		return gerPrenom;
	}

	public void setGerPrenom(String gerPrenom) {
		this.gerPrenom = gerPrenom;
	}

	@Column(name = "ger_statut")
	public int getGerStatut() {
		return gerStatut;
	}

	public void setGerStatut(int gerStatut) {
		this.gerStatut = gerStatut;
	}

	@Column(name = "ger_telephone")
	public String getGerTelephone() {
		return gerTelephone;
	}

	public void setGerTelephone(String gerTelephone) {
		this.gerTelephone = gerTelephone;
	}

	@Column(name = "ger_password")
	public String getGerPassword() {
		return gerPassword;
	}

	public void setGerPassword(String gerPassword) {
		this.gerPassword = gerPassword;
	}

	@Column(name = "ger_etat")
	public String getGerEtat() {
		return gerEtat;
	}

	public void setGerEtat(String gerEtat) {
		this.gerEtat = gerEtat;
	}

	@Column(name = "sup_supprime")
	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "sup_station")
	public SalePoint getSalePoint() {
		return salePoint;
	}

	public void setSalePoint(SalePoint salePoint) {
		this.salePoint = salePoint;
	}

	@Column(name = "sim_number")
	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	@Column(name = "ger_hasconnect")
	public int getGerHasConnect() {
		return gerHasConnect;
	}

	public void setGerHasConnect(int gerHasConnect) {
		this.gerHasConnect = gerHasConnect;
	}

	@Column(name = "code_sap_gerant")
	public String getCodeSapGerant() {
		return codeSapGerant;
	}

	public void setCodeSapGerant(String codeSapGerant) {
		this.codeSapGerant = codeSapGerant;
	}
	
	@Column(name="reseaux")
	public String getReseaux() {
		return reseaux;
	}
	public void setReseaux(String reseaux) {
		this.reseaux = reseaux;
	}

}

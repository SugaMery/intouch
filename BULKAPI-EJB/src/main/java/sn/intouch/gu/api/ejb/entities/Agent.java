package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "agent")
@DiscriminatorValue("Agent")
public class Agent extends Utilisateur implements Serializable{
	
	private SalePoint salepoint;
	private Boolean supprime = false;
	private String type;
	private int agtId, agtStatut = 1, agtHasConnect = 0, gerStatut = 1;
	private double agtSoldeUV;
	private String agtNom, agtPrenom,  agtLogin,  agtPassword, agtEtat="ACTIF", agtPosition,agtTelephone;
	private String reseaux;
	private String statut;

	
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Agent(Long id, String nom, String prenom, Long numero, String mail, SalePoint salepoint_id) {
		super(id, nom, prenom, numero, mail);
		this.salepoint = salepoint_id;
	}
	
	@Column( name = "ag_supprime")
	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	
	@Column( name = "ag_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	@Column(name="agt_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getAgtId() {
		return agtId;
	}
	public void setAgtId(int agtId) {
		this.agtId = agtId;
	}
	
	@Column(name="agt_login")
	public String getAgtLogin() {
		return agtLogin;
	}
	public void setAgtLogin(String agtLogin) {
		this.agtLogin = agtLogin;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER )
	@JoinColumn(name="agt_station")
	public SalePoint getSalepoint() {
		return salepoint;
	}
	public void setSalepoint(SalePoint salePoint) {
		this.salepoint = salePoint;
	}
	
	
	@Column(name="agt_statut")
	public int getAgtStatut() {
		return agtStatut;
	}
	public void setAgtStatut(int agtStatut) {
		this.agtStatut = agtStatut;
	}
	
	@Column(name="agt_nom")
	public String getAgtNom() {
		return agtNom;
	}
	public void setAgtNom(String agtNom) {
		this.agtNom = agtNom;
	}
	
	@Column(name="agt_prenom")
	public String getAgtPrenom() {
		return agtPrenom;
	}
	public void setAgtPrenom(String agtPrenom) {
		this.agtPrenom = agtPrenom;
	}
	
	@Column(name="agt_telephone")
	public String getAgtTelephone() {
		return agtTelephone;
	}
	public void setAgtTelephone(String agtTelephone) {
		this.agtTelephone = agtTelephone;
	}

	
	@Column(name="agt_soldeUV")
	public double getAgtSoldeUV() {
		return agtSoldeUV;
	}
	public void setAgtSoldeUV(double agtSoldeUV) {
		this.agtSoldeUV = agtSoldeUV;
	}

	
	@Column(name="agt_password")
	public String getAgtPassword() {
		return agtPassword;
	}
	public void setAgtPassword(String agtPassword) {
		this.agtPassword = agtPassword;
	}

	
	@Column(name="agt_etat")
	public String getAgtEtat() {
		return agtEtat;
	}
	public void setAgtEtat(String agtEtat) {
		this.agtEtat = agtEtat;
	}
	
	@Column(name="agt_hasconnect")
	public int getAgtHasConnect() {
		return agtHasConnect;
	}
	public void setAgtHasConnect(int agtHasConnect) {
		this.agtHasConnect = agtHasConnect;
	}
	
	@Column(name="agt_position")
	public String getAgtPosition() {
		return agtPosition;
	}
	public void setAgtPosition(String agtPosition) {
		this.agtPosition = agtPosition;
	}
	@Column(name="ger_statut")
	public int getGerStatut() {
		return gerStatut;
	}
	public void setGerStatut(int gerStatut) {
		this.gerStatut = gerStatut;
	}
	@Column(name="reseaux")
	public String getReseaux() {
		return reseaux;
	}
	public void setReseaux(String reseaux) {
		this.reseaux = reseaux;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
}

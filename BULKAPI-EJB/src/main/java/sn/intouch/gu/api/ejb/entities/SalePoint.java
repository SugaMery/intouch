package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "station")
@NamedQuery(name = SalePoint.FIND_BY_NAME, query = "SELECT sp FROM SalePoint sp WHERE sp.salepoint_nom=:nom AND sp.supprime=false")
public class SalePoint implements Serializable {
	public static final String FIND_BY_NAME = "findSalePointByName";

	private String salepoint_nom, salepoint_code, latitude, longitude;
	private Long salepoint_id, salepoint_numO_gerant, salepoint_numOM_gerant;
	private Double salepoint_DMA, salepoint_solde_UV, salepoint_Plafond;
	private Network network_id;
	Boolean supprime = false;
	private int commissionVariable, commissionFixe = 0, limiteDecouvert, staStatut = 1, rouge =0;
	private int quotePartComByArticle, deposit, codeClientSap;
	private Date dateCreation, lastMaj;
	private int tovalid = 0;
	private long dmaTigo, soldeTigo;
	private long dmaWA, soldeWA;
	private String actif = "ACTIF";
	private String codeSAP;
	int etat;
	private String statut;


	/* Déclarations des nouvelles variables */
	private String activitePrincipale;

	private String activiteSecondaire;

	private String adresse;

	private String nomCompletProprietaire;

	private String telProprietaire;
	
	private String contexte;

	@Id
	@Column(name = "sta_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getSalepoint_id() {
		return salepoint_id;
	}

	public void setSalepoint_id(Long salepoint_id) {
		this.salepoint_id = salepoint_id;
	}

	@Column(name = "salepoint_latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "salepoint_longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Column(name="code_client_sap")
	public int getCodeClientSap() {
		return codeClientSap;
	}
	public void setCodeClientSap(int codeClientSap) {
		this.codeClientSap = codeClientSap;
	}
	
	@Column(name="datecreation")
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	@Column(name="lastmaj")
	public Date getLastMaj() {
		return lastMaj;
	}
	public void setLastMaj(Date lastMaj) {
		this.lastMaj = lastMaj;
	}

	@Column(name="tovalid")
	public int getTovalid() {
		return tovalid;
	}
	public void setTovalid(int tovalid) {
		this.tovalid = tovalid;
	}
	
	@Column(name="dma_tigo")
	public long getDmaTigo() {
		return dmaTigo;
	}
	public void setDmaTigo(long dmaTigo) {
		this.dmaTigo = dmaTigo;
	}
	
	@Column(name="solde_tigo")
	public long getSoldeTigo() {
		return soldeTigo;
	}
	public void setSoldeTigo(long soldeTigo) {
		this.soldeTigo = soldeTigo;
	}
	
	@Column(name="dma_wa")
	public long getDmaWA() {
		return dmaWA;
	}
	public void setDmaWA(long dmaWA) {
		this.dmaWA = dmaWA;
	}
	
	@Column(name="solde_wa")
	public long getSoldeWA() {
		return soldeWA;
	}
	public void setSoldeWA(long soldeWA) {
		this.soldeWA = soldeWA;
	}
	
	
	@Column(name="code_sap")
	public String getCodeSAP() {
		return codeSAP;
	}
	public void setCodeSAP(String codeSAP) {
		this.codeSAP = codeSAP;
	}

	@Column(name = "rouge")
	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	@Column(name="sta_statut")
	public int getStaStatut() {
		return staStatut;
	}
	public void setStaStatut(int staStatut) {
		this.staStatut = staStatut;
	}

	@Column(name = "sta_libelle")
	public String getSalepoint_nom() {
		return salepoint_nom;
	}

	public void setSalepoint_nom(String salepoint_nom) {
		this.salepoint_nom = salepoint_nom;
	}

	@Column(name = "sta_code")
	public String getSalepoint_code() {
		return salepoint_code;
	}

	public void setSalepoint_code(String salepoint_code) {
		this.salepoint_code = salepoint_code;
	}

	@Column(name = "salepoint_plafond")
	public Double getSalepoint_Plafond() {
		return salepoint_Plafond;
	}

	public void setSalepoint_Plafond(Double salepoint_Plafond) {
		this.salepoint_Plafond = salepoint_Plafond;
	}

	@Column(name = "salepoint_DMA")
	public Double getSalepoint_DMA() {
		return salepoint_DMA;
	}

	public void setSalepoint_DMA(Double salepoint_DMA) {
		this.salepoint_DMA = salepoint_DMA;
	}

	@Column(name = "salepoint_solde_UV")
	public Double getSalepoint_solde_UV() {
		return salepoint_solde_UV;
	}

	public void setSalepoint_solde_UV(Double salepoint_solde_UV) {
		this.salepoint_solde_UV = salepoint_solde_UV;
	}

	@Column(name = "salepoint_numO_gerant")
	public Long getSalepoint_numO_gerant() {
		return salepoint_numO_gerant;
	}

	public void setSalepoint_numO_gerant(Long salepoint_numO_gerant) {
		this.salepoint_numO_gerant = salepoint_numO_gerant;
	}

	@Column(name = "salepoint_numOM_gerant")
	public Long getSalepoint_numOM_gerant() {
		return salepoint_numOM_gerant;
	}

	public void setSalepoint_numOM_gerant(Long salepoint_numOM_gerant) {
		this.salepoint_numOM_gerant = salepoint_numOM_gerant;
	}

	/*
	 * @Column(name="sp_statut") public int getSpStatut() { return spStatut; }
	 * public void setSpStatut(int spStatut) { this.spStatut = spStatut; }
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "network_id")
	public Network getNetwork_id() {
		return network_id;
	}

	public void setNetwork_id(Network network_id) {
		this.network_id = network_id;
	}


	@Column(name = "fnct_salepoint_sup")
	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}

	@Column(name = "activite_principale")
	public String getActivitePrincipale() {
		return activitePrincipale;
	}

	public void setActivitePrincipale(String activitePrincipale) {
		this.activitePrincipale = activitePrincipale;
	}

	@Column(name = "activite_secondaire")
	public String getActiviteSecondaire() {
		return activiteSecondaire;
	}

	public void setActiviteSecondaire(String activiteSecondaire) {
		this.activiteSecondaire = activiteSecondaire;
	}

	@Column(name = "adresse")
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "nom_complet_proprietaire")
	public String getNomCompletProprietaire() {
		return nomCompletProprietaire;
	}

	public void setNomCompletProprietaire(String nomCompletProprietaire) {
		this.nomCompletProprietaire = nomCompletProprietaire;
	}

	@Column(name = "tel_proprietaire")
	public String getTelProprietaire() {
		return telProprietaire;
	}

	public void setTelProprietaire(String telProprietaire) {
		this.telProprietaire = telProprietaire;
	}
	
	@Column(name="com_variable")
	public int getCommissionVariable() {
		return commissionVariable;
	}
	public void setCommissionVariable(int commissionVariable) {
		this.commissionVariable = commissionVariable;
	}
	
	@Column(name="com_fixe")
	public int getCommissionFixe() {
		return commissionFixe;
	}
	public void setCommissionFixe(int commissionFixe) {
		this.commissionFixe = commissionFixe;
	}
	
	@Column(name="limite_decouvert")
	public int getLimiteDecouvert() {
		return limiteDecouvert;
	}
	public void setLimiteDecouvert(int limiteDecouvert) {
		this.limiteDecouvert = limiteDecouvert;
	}
	
	@Column(name="quote_part_comm_by_art")
	public int getQuotePartComByArticle() {
		return quotePartComByArticle;
	}
	public void setQuotePartComByArticle(int quotePartComByArticle) {
		this.quotePartComByArticle = quotePartComByArticle;
	}
	
	@Column(name="deposit")
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	
	@Column(name="actif")
	public String getActif() {
		return actif;
	}
	public void setActif(String actif) {
		this.actif = actif;
	}

	@Column(name = "contexte", unique = true)
	public String getContexte() {
		return contexte;
	}

	public void setContexte(String contexte) {
		this.contexte = contexte;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
    
}
package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="operationgu30j")
public class OperationMois implements Serializable{

	private Long idOp;
	private Date dateOp, dateDemandeAnnulationOp, dateTraitementAnnulationOp, dateDemandeOpSuspecte, dateDayReg;
	private String partenaireOp,telephoneContactOp,cniContactOp,prenomContactOp,nomContactOp,codeOrdreRetraitOp;
	private String loginUserOp,nomUserOp,prenomUserOp;
	private Long numUserOp;

	private String loginDemandeurAnnulationOp,prenomDemandeurAnnulationOp,nomDemandeurAnnulationOp ;
	private Long  numDemandeurAnnulationOp;
	private String loginDemandeurOpSuspecte,prenomDemandeurOpSuspecte,nomDemandeurOpSuspecte;
	private Long numDemandeurOpSuspecte ,numTraiteurAnnulationOp;
	private String loginTraiteurAnnulationOp,prenomTraiteurAnnulationOp,nomTraiteurAnnulationOp;
	private String commentaireDemandeur,commentaireTraiteur,commentaireDemandeurSus;
	private Double montantOp,fraisRetraitOp;
	private SalePoint agenceOp;
	private Network reseauOP;
	private Transaction transactionIdOp;
	private TypeOperation typeOperationOp;
	private Statut statutOp;
	private String spareOp1, spareOp2, spareOp3, spareOp4, spareOp5, spareOp6, spareOp7, spareOp8, spareOp9, spareOp10;
	private Long idCompte, idCompteDest;
	private Double montantTransaction, montantTotalCommission;
	private Date dateBordereau, dateTraitement;
	private String banque;
	private String numBordereau;
	private String numTransactionGu;
	private String nomBordereau;
	private String proprietaire;
	private String network_groupe_code;
	private String loginDemandeurAppro, loginValideurAppro, motifRejetAppro;
	Boolean supprime = false;
	
	@PersistenceContext(unitName = "guPU")
	EntityManager em;
	
	@Id
	@Column(name = "idOp")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOp() {
		return idOp;
	}
	public void setIdOp(Long idOp) {
		this.idOp = idOp;
	}
	
	@Column(name = "network_groupe_code")
	public String getNetwork_groupe_code() {
		return network_groupe_code;
	}
	public void setNetwork_groupe_code(String network_groupe_code) {
		this.network_groupe_code = network_groupe_code;
	}
	@Column(name = "dateOp")
	public Date getDateOp() {
		return dateOp;
	}
	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}
	@Column(name = "nomBordereau")
	public String getNomBordereau() {
		return nomBordereau;
	}
	public void setNomBordereau(String nomBordereau) {
		this.nomBordereau = nomBordereau;
	}
	@Column(name = "partenaireOp")
	public String getPartenaireOp() {
		return partenaireOp;
	}
	public void setPartenaireOp(String partenaireOp) {
		this.partenaireOp = partenaireOp;
	}
	
	@Column(name = "TelephoneContactOp")
	public String getTelephoneContactOp() {
		return telephoneContactOp;
	}
	public void setTelephoneContactOp(String telephoneContactOp) {
		this.telephoneContactOp = telephoneContactOp;
	}
	
	@Column(name = "CniContactOp")
	public String getCniContactOp() {
		return cniContactOp;
	}
	public void setCniContactOp(String cniContactOp) {
		this.cniContactOp = cniContactOp;
	}
	
	@Column(name = "PrenomContactOp")
	public String getPrenomContactOp() {
		return prenomContactOp;
	}
	public void setPrenomContactOp(String prenomContactOp) {
		this.prenomContactOp = prenomContactOp;
	}
	
	@Column(name = "NomContactOp")
	public String getNomContactOp() {
		return nomContactOp;
	}
	public void setNomContactOp(String nomContactOp) {
		this.nomContactOp = nomContactOp;
	}
	
	@Column(name = "CodeOrdreRetraitOp")
	public String getCodeOrdreRetraitOp() {
		return codeOrdreRetraitOp;
	}
	public void setCodeOrdreRetraitOp(String codeOrdreRetraitOp) {
		this.codeOrdreRetraitOp = codeOrdreRetraitOp;
	}
	
	@Column(name = "MontantOp")
	public Double getMontantOp() {
		return montantOp;
	}
	public void setMontantOp(Double montantOp) {
		this.montantOp = montantOp;
	}
	@Column(name = "fraisRetraitOp")
	public Double getFraisRetraitOp() {
		return fraisRetraitOp;
	}
	public void setFraisRetraitOp(Double fraisRetraitOp) {
		this.fraisRetraitOp = fraisRetraitOp;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AgenceOp")
	public SalePoint getAgenceOp() {
		return agenceOp;
	}
	public void setAgenceOp(SalePoint agenceOp) {
		this.agenceOp = agenceOp;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reseauOP")
	public Network getReseauOP() {
		return reseauOP;
	}
	public void setReseauOP(Network reseauOP) {
		this.reseauOP = reseauOP;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transactionIdOp")
	public Transaction getTransactionIdOp() {
		return transactionIdOp;
	}
	public void setTransactionIdOp(Transaction transactionIdOp) {
		this.transactionIdOp = transactionIdOp;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "typeOperationOp")
	public TypeOperation getTypeOperationOp() {
		return typeOperationOp;
	}
	public void setTypeOperationOp(TypeOperation typeOperationOp) {
		this.typeOperationOp = typeOperationOp;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "statutOp")
	public Statut getStatutOp() {
		return statutOp;
	}
	public void setStatutOp(Statut statutOp) {
		this.statutOp = statutOp;
	}
	
	@Column(name = "loginUserOp")
	public String getLoginUserOp() {
		return loginUserOp;
	}
	public void setLoginUserOp(String loginUserOp) {
		this.loginUserOp = loginUserOp;
	}
	
	@Column(name = "nomUserOp")
	public String getNomUserOp() {
		return nomUserOp;
	}
	public void setNomUserOp(String nomUserOp) {
		this.nomUserOp = nomUserOp;
	}
	
	@Column(name = "prenomUserOp")
	public String getPrenomUserOp() {
		return prenomUserOp;
	}
	public void setPrenomUserOp(String prenomUserOp) {
		this.prenomUserOp = prenomUserOp;
	}
	
	@Column(name = "numUserOp")
	public Long getNumUserOp() {
		return numUserOp;
	}
	public void setNumUserOp(Long numUserOp) {
		this.numUserOp = numUserOp;
	}
	
	@Column(name = "dateDemandeAnnulationOp")
	public Date getDateDemandeAnnulationOp() {
		return dateDemandeAnnulationOp;
	}
	public void setDateDemandeAnnulationOp(Date dateDemandeAnnulationOp) {
		this.dateDemandeAnnulationOp = dateDemandeAnnulationOp;
	}
	
	@Column(name = "dateTraitementAnnulationOp")
	public Date getDateTraitementAnnulationOp() {
		return dateTraitementAnnulationOp;
	}
	public void setDateTraitementAnnulationOp(Date dateTraitementAnnulationOp) {
		this.dateTraitementAnnulationOp = dateTraitementAnnulationOp;
	}
	
	@Column(name = "dateDemandeOpSuspecte")
	public Date getDateDemandeOpSuspecte() {
		return dateDemandeOpSuspecte;
	}
	public void setDateDemandeOpSuspecte(Date dateDemandeOpSuspecte) {
		this.dateDemandeOpSuspecte = dateDemandeOpSuspecte;
	}
	
	@Column(name = "loginDemandeurAnnulationOp")
	public String getLoginDemandeurAnnulationOp() {
		return loginDemandeurAnnulationOp;
	}
	public void setLoginDemandeurAnnulationOp(String loginDemandeurAnnulationOp) {
		this.loginDemandeurAnnulationOp = loginDemandeurAnnulationOp;
	}
	
	@Column(name = "prenomDemandeurAnnulationOp")
	public String getPrenomDemandeurAnnulationOp() {
		return prenomDemandeurAnnulationOp;
	}
	public void setPrenomDemandeurAnnulationOp(String prenomDemandeurAnnulationOp) {
		this.prenomDemandeurAnnulationOp = prenomDemandeurAnnulationOp;
	}
	
	@Column(name = "nomDemandeurAnnulationOp")
	public String getNomDemandeurAnnulationOp() {
		return nomDemandeurAnnulationOp;
	}
	public void setNomDemandeurAnnulationOp(String nomDemandeurAnnulationOp) {
		this.nomDemandeurAnnulationOp = nomDemandeurAnnulationOp;
	}
	
	@Column(name = "numDemandeurAnnulationOp")
	public Long getNumDemandeurAnnulationOp() {
		return numDemandeurAnnulationOp;
	}
	public void setNumDemandeurAnnulationOp(Long numDemandeurAnnulationOp) {
		this.numDemandeurAnnulationOp = numDemandeurAnnulationOp;
	}
	
	@Column(name = "loginDemandeurOpSuspecte")
	public String getLoginDemandeurOpSuspecte() {
		return loginDemandeurOpSuspecte;
	}
	public void setLoginDemandeurOpSuspecte(String loginDemandeurOpSuspecte) {
		this.loginDemandeurOpSuspecte = loginDemandeurOpSuspecte;
	}
	
	@Column(name = "prenomDemandeurOpSuspecte")
	public String getPrenomDemandeurOpSuspecte() {
		return prenomDemandeurOpSuspecte;
	}
	public void setPrenomDemandeurOpSuspecte(String prenomDemandeurOpSuspecte) {
		this.prenomDemandeurOpSuspecte = prenomDemandeurOpSuspecte;
	}
	
	@Column(name = "nomDemandeurOpSuspecte")
	public String getNomDemandeurOpSuspecte() {
		return nomDemandeurOpSuspecte;
	}
	public void setNomDemandeurOpSuspecte(String nomDemandeurOpSuspecte) {
		this.nomDemandeurOpSuspecte = nomDemandeurOpSuspecte;
	}
	
	@Column(name = "numDemandeurOpSuspecte")
	public Long getNumDemandeurOpSuspecte() {
		return numDemandeurOpSuspecte;
	}
	public void setNumDemandeurOpSuspecte(Long numDemandeurOpSuspecte) {
		this.numDemandeurOpSuspecte = numDemandeurOpSuspecte;
	}
	
	@Column(name = "loginTraiteurAnnulationOp")
	public String getLoginTraiteurAnnulationOp() {
		return loginTraiteurAnnulationOp;
	}
	public void setLoginTraiteurAnnulationOp(String loginTraiteurAnnulationOp) {
		this.loginTraiteurAnnulationOp = loginTraiteurAnnulationOp;
	}
	
	@Column(name = "prenomTraiteurAnnulationOp")
	public String getPrenomTraiteurAnnulationOp() {
		return prenomTraiteurAnnulationOp;
	}
	public void setPrenomTraiteurAnnulationOp(String prenomTraiteurAnnulationOp) {
		this.prenomTraiteurAnnulationOp = prenomTraiteurAnnulationOp;
	}
	
	@Column(name = "nomTraiteurAnnulationOp")
	public String getNomTraiteurAnnulationOp() {
		return nomTraiteurAnnulationOp;
	}
	public void setNomTraiteurAnnulationOp(String nomTraiteurAnnulationOp) {
		this.nomTraiteurAnnulationOp = nomTraiteurAnnulationOp;
	}
	
	@Column(name = "numTraiteurAnnulationOp")
	public Long getNumTraiteurAnnulationOp() {
		return numTraiteurAnnulationOp;
	}
	public void setNumTraiteurAnnulationOp(Long numTraiteurAnnulationOp) {
		this.numTraiteurAnnulationOp = numTraiteurAnnulationOp;
	}
	
	@Column(name = "commentaireDemandeur")
	public String getCommentaireDemandeur() {
		return commentaireDemandeur;
	}
	public void setCommentaireDemandeur(String commentaireDemandeur) {
		this.commentaireDemandeur = commentaireDemandeur;
	}
	
	@Column(name = "commentaireTraiteur")
	public String getCommentaireTraiteur() {
		return commentaireTraiteur;
	}
	public void setCommentaireTraiteur(String commentaireTraiteur) {
		this.commentaireTraiteur = commentaireTraiteur;
	}
	
	@Column(name = "commentaireDemandeurSus")
	public String getCommentaireDemandeurSus() {
		return commentaireDemandeurSus;
	}
	public void setCommentaireDemandeurSus(String commentaireDemandeurSus) {
		this.commentaireDemandeurSus = commentaireDemandeurSus;
	}
	
	
	@Column(name = "opSupprime")
	public Boolean getSupprime() {
		return supprime;
	}
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	public String getSpareOp1() {
		return spareOp1;
	}
	public void setSpareOp1(String spareOp1) {
		this.spareOp1 = spareOp1;
	}
	public String getSpareOp2() {
		return spareOp2;
	}
	public void setSpareOp2(String spareOp2) {
		this.spareOp2 = spareOp2;
	}
	public String getSpareOp3() {
		return spareOp3;
	}
	public void setSpareOp3(String spareOp3) {
		this.spareOp3 = spareOp3;
	}
	public String getSpareOp4() {
		return spareOp4;
	}
	public void setSpareOp4(String spareOp4) {
		this.spareOp4 = spareOp4;
	}
	public String getSpareOp5() {
		return spareOp5;
	}
	public void setSpareOp5(String spareOp5) {
		this.spareOp5 = spareOp5;
	}
	
	public Date getDateTraitement() {
		return dateTraitement;
	}
	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement;
	}
	public Long getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(Long idCompte) {
		this.idCompte = idCompte;
	}
	
	@Column(name = "proprietaire")
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public Long getIdCompteDest() {
		return idCompteDest;
	}
	public void setIdCompteDest(Long idCompteDest) {
		this.idCompteDest = idCompteDest;
	}
	public Double getMontantTransaction() {
		return montantTransaction;
	}
	public void setMontantTransaction(Double montantTransaction) {
		this.montantTransaction = montantTransaction;
	}
	public Double getMontantTotalCommission() {
		return montantTotalCommission;
	}
	public void setMontantTotalCommission(Double montantTotalCommission) {
		this.montantTotalCommission = montantTotalCommission;
	}
	
	public Date getDateBordereau() {
		return dateBordereau;
	}
	public void setDateBordereau(Date dateBordereau) {
		this.dateBordereau = dateBordereau;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public String getNumBordereau() {
		return numBordereau;
	}
	public void setNumBordereau(String numBordereau) {
		this.numBordereau = numBordereau;
	}
	
	public String getSpareOp6() {
		return spareOp6;
	}
	public void setSpareOp6(String spareOp6) {
		this.spareOp6 = spareOp6;
	}
	public String getSpareOp7() {
		return spareOp7;
	}
	public void setSpareOp7(String spareOp7) {
		this.spareOp7 = spareOp7;
	}
	
	
	public String getSpareOp8() {
		return spareOp8;
	}
	public void setSpareOp8(String spareOp8) {
		this.spareOp8 = spareOp8;
	}
	public String getSpareOp9() {
		return spareOp9;
	}
	public void setSpareOp9(String spareOp9) {
		this.spareOp9 = spareOp9;
	}
	public String getSpareOp10() {
		return spareOp10;
	}
	public void setSpareOp10(String spareOp10) {
		this.spareOp10 = spareOp10;
	}
	
	public Date getDateDayReg() {
		return dateDayReg;
	}
	public void setDateDayReg(Date dateDayReg) {
		this.dateDayReg = dateDayReg;
	}
	@Override
	public String toString() {
		return "Operation [dateOp=" + dateOp + ", spareOp4=" + spareOp4 + ", spareOp5=" + spareOp5 + "]";
	}

	@Column(name = "numTransactionGu",unique=true)
	public String getNumTransactionGu() {
		return numTransactionGu;
	}
	public void setNumTransactionGu(String numTransactionGu) {
		this.numTransactionGu = numTransactionGu;
	}
	
	public String getLoginDemandeurAppro() {
		return loginDemandeurAppro;
	}
	public void setLoginDemandeurAppro(String loginDemandeurAppro) {
		this.loginDemandeurAppro = loginDemandeurAppro;
	}
	public String getLoginValideurAppro() {
		return loginValideurAppro;
	}
	public void setLoginValideurAppro(String loginValideurAppro) {
		this.loginValideurAppro = loginValideurAppro;
	}
	public String getMotifRejetAppro() {
		return motifRejetAppro;
	}
	public void setMotifRejetAppro(String motifRejetAppro) {
		this.motifRejetAppro = motifRejetAppro;
	}
	
	
}

package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import sn.intouch.gu.api.ejb.utils.Utils;

@Entity
@Table(name = "transactiongu")
public class Transaction implements Serializable {

	private Long transaction_id;
	private Double transaction_montant;
	private String numTransFromGu;
	private String numTransFromService;
	private String numTransFromPartenaire;
	private Date transaction_date, dateDemandeAnnulationTrans, dateTraitementAnnulationTrans, dateDemandeTransSuspecte;
	private String customer_code;
	private String payement_mode_code;
	private String agent_code;
	private String salepoint_code;
	private String supervisor_code;
	private String network_code;
	private String pda_code;
	private String website_id;
	private String service_code;
	private Double commissionAgence;
	private Double commissionGu;
	private String commentaireDemandeur, commentaireTraiteur, commentaireDemandeurSus;
	private String loginDemandeurAnnulationTrans, prenomDemandeurAnnulationTrans, nomDemandeurAnnulationTrans;
	private Long numDemandeurAnnulationTrans, numTraiteurAnnulationTrans, numDemandeurTransSuspecte;

	private String prenomDemandeurTransSuspecte, nomDemandeurTransSuspecte;
	private String loginTraiteurAnnulationTrans, prenomTraiteurAnnulationTrans, nomTraiteurAnnulationTrans;
	private String transactionStatut;
	private String type;
	private String specificitesService;
	private Integer codeRetourMoteur;
	private String messageRetourMoteur;
	private String network_groupe_code;
	private String baseCalculCommission;
	
	Boolean supprime = false;

	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(Long transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	@Column(name = "network_groupe_code")
	public String getNetwork_groupe_code() {
		return network_groupe_code;
	}
	public void setNetwork_groupe_code(String network_groupe_code) {
		this.network_groupe_code = network_groupe_code;
	}
	
	public String getSpecificitesService() {
		return specificitesService;
	}

	public void setSpecificitesService(String specificitesService) {
		this.specificitesService = specificitesService;
	}

	@Column(name = "baseCalculCommission")
	public String getBaseCalculCommission() {
		return baseCalculCommission;
	}
	public void setBaseCalculCommission(String baseCalculCommission) {
		this.baseCalculCommission = baseCalculCommission;
	}
	@Column(name = "transaction_montant")
	public Double getTransaction_montant() {
		return transaction_montant;
	}

	public void setTransaction_montant(Double transaction_montant) {
		Utils.formatLabel(this.transaction_montant = transaction_montant);
	}
	
	@Column(name = "transaction_date")
	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	@Column(name = "customer_code")
	public String getCustomer_code() {
		return customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	@Column(name = "payement_mode_code")
	public String getPayement_mode_code() {
		return payement_mode_code;
	}

	public void setPayement_mode_code(String payement_mode_code) {
		this.payement_mode_code = payement_mode_code;
	}

	@Column(name = "agent_code")
	public String getAgent_code() {
		return agent_code;
	}

	public void setAgent_code(String agent_code) {
		this.agent_code = agent_code;
	}

	@Column(name = "salepoint_code")
	public String getSalepoint_code() {
		return salepoint_code;
	}

	public void setSalepoint_code(String salepoint_code) {
		this.salepoint_code = salepoint_code;
	}

	@Column(name = "supervisor_code")
	public String getSupervisor_code() {
		return supervisor_code;
	}

	public void setSupervisor_code(String supervisor_code) {
		this.supervisor_code = supervisor_code;
	}

	@Column(name = "network_code")
	public String getNetwork_code() {
		return network_code;
	}

	public void setNetwork_code(String network_code) {
		this.network_code = network_code;
	}

	@Column(name = "pda_code")
	public String getPda_code() {
		return pda_code;
	}

	public void setPda_code(String pda_code) {
		this.pda_code = pda_code;
	}

	@Column(name = "website_id")
	public String getWebsite_id() {
		return website_id;
	}

	public void setWebsite_id(String website_id) {
		this.website_id = website_id;
	}

	@Column(name = "service_code")
	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	@Column(name = "num_transaction_gu",unique=true)
	public String getNumTransFromGu() {
		return numTransFromGu;
	}

	public void setNumTransFromGu(String numTransFromGu) {
		this.numTransFromGu = numTransFromGu;
	}

	@Column(name = "num_transaction_service",unique=true)
	public String getNumTransFromService() {
		return numTransFromService;
	}

	public void setNumTransFromService(String numTransFromService) {
		this.numTransFromService = numTransFromService;
	}

	@Column(name = "num_transaction_partenaire",unique=true)
	public String getNumTransFromPartenaire() {
		return numTransFromPartenaire;
	}

	public void setNumTransFromPartenaire(String numTransFromPartenaire) {
		this.numTransFromPartenaire = numTransFromPartenaire;
	}

	@Column(name = "date_demande_annulationTrans")
	public Date getDateDemandeAnnulationTrans() {
		return dateDemandeAnnulationTrans;
	}

	public void setDateDemandeAnnulationTrans(Date dateDemandeAnnulationTrans) {
		this.dateDemandeAnnulationTrans = dateDemandeAnnulationTrans;
	}

	@Column(name = "date_traitement_annulationTrans")
	public Date getDateTraitementAnnulationTrans() {
		return dateTraitementAnnulationTrans;
	}

	public void setDateTraitementAnnulationTrans(Date dateTraitementAnnulationTrans) {
		this.dateTraitementAnnulationTrans = dateTraitementAnnulationTrans;
	}

	@Column(name = "date_demande_trans_suspecte")
	public Date getDateDemandeTransSuspecte() {
		return dateDemandeTransSuspecte;
	}

	public void setDateDemandeTransSuspecte(Date dateDemandeTransSuspecte) {
		this.dateDemandeTransSuspecte = dateDemandeTransSuspecte;
	}

	@Column(name = "prenomDemandeurTransSuspecte")
	public String getPrenomDemandeurTransSuspecte() {
		return prenomDemandeurTransSuspecte;
	}

	public void setPrenomDemandeurTransSuspecte(String prenomDemandeurTransSuspecte) {
		this.prenomDemandeurTransSuspecte = prenomDemandeurTransSuspecte;
	}

	@Column(name = "nomDemandeurTransSuspecte")
	public String getNomDemandeurTransSuspecte() {
		return nomDemandeurTransSuspecte;
	}

	public void setNomDemandeurTransSuspecte(String nomDemandeurTransSuspecte) {
		this.nomDemandeurTransSuspecte = nomDemandeurTransSuspecte;
	}

	@Column(name = "numDemandeurTransSuspecte")
	public Long getNumDemandeurTransSuspecte() {
		return numDemandeurTransSuspecte;
	}

	public void setNumDemandeurTransSuspecte(Long numDemandeurTransSuspecte) {
		this.numDemandeurTransSuspecte = numDemandeurTransSuspecte;
	}

	@Column(name = "commission_agence")
	public Double getCommissionAgence() {
		return commissionAgence;
	}

	public void setCommissionAgence(Double commissionAgence) {
		this.commissionAgence = commissionAgence;
	}

	@Column(name = "commission_gu")
	public Double getCommissionGu() {
		return commissionGu;
	}

	public void setCommissionGu(Double commissionGu) {
		this.commissionGu = commissionGu;
	}

	@Column(name = "commentaires_demandeur")
	public String getCommentaireDemandeur() {
		return commentaireDemandeur;
	}

	public void setCommentaireDemandeur(String commentaireDemandeur) {
		this.commentaireDemandeur = commentaireDemandeur;
	}

	@Column(name = "commentaires_demandeurSus")
	public String getCommentaireDemandeurSus() {
		return commentaireDemandeurSus;
	}

	public void setCommentaireDemandeurSus(String commentaireDemandeurSus) {
		this.commentaireDemandeurSus = commentaireDemandeurSus;
	}

	@Column(name = "commentaires_traiteur")
	public String getCommentaireTraiteur() {
		return commentaireTraiteur;
	}

	public void setCommentaireTraiteur(String commentaireTraiteur) {
		this.commentaireTraiteur = commentaireTraiteur;
	}

	@Column(name = "loginDemandeurAnnulationTrans")
	public String getLoginDemandeurAnnulationTrans() {
		return loginDemandeurAnnulationTrans;
	}

	public void setLoginDemandeurAnnulationTrans(String loginDemandeurAnnulationTrans) {
		this.loginDemandeurAnnulationTrans = loginDemandeurAnnulationTrans;
	}

	@Column(name = "loginTraiteurAnnulationTrans")
	public String getLoginTraiteurAnnulationTrans() {
		return loginTraiteurAnnulationTrans;
	}

	public void setLoginTraiteurAnnulationTrans(String loginTraiteurAnnulationTrans) {
		this.loginTraiteurAnnulationTrans = loginTraiteurAnnulationTrans;
	}

	@Column(name = "statut_code")
	public String getTransactionStatut() {
		return transactionStatut;
	}

	public void setTransactionStatut(String transactionStatut) {
		this.transactionStatut = transactionStatut;
	}

	@Column(name = "prenomDemandeurAnnulationTrans")
	public String getPrenomDemandeurAnnulationTrans() {
		return prenomDemandeurAnnulationTrans;
	}

	public void setPrenomDemandeurAnnulationTrans(String prenomDemandeurAnnulationTrans) {
		this.prenomDemandeurAnnulationTrans = prenomDemandeurAnnulationTrans;
	}

	@Column(name = "nomDemandeurAnnulationTrans")
	public String getNomDemandeurAnnulationTrans() {
		return nomDemandeurAnnulationTrans;
	}

	public void setNomDemandeurAnnulationTrans(String nomDemandeurAnnulationTrans) {
		this.nomDemandeurAnnulationTrans = nomDemandeurAnnulationTrans;
	}

	@Column(name = "numDemandeurAnnulationTrans")
	public Long getNumDemandeurAnnulationTrans() {
		return numDemandeurAnnulationTrans;
	}

	public void setNumDemandeurAnnulationTrans(Long numDemandeurAnnulationTrans) {
		this.numDemandeurAnnulationTrans = numDemandeurAnnulationTrans;
	}

	@Column(name = "prenomTraiteurAnnulationTrans")
	public String getPrenomTraiteurAnnulationTrans() {
		return prenomTraiteurAnnulationTrans;
	}

	public void setPrenomTraiteurAnnulationTrans(String prenomTraiteurAnnulationTrans) {
		this.prenomTraiteurAnnulationTrans = prenomTraiteurAnnulationTrans;
	}

	@Column(name = "nomTraiteurAnnulationTrans")
	public String getNomTraiteurAnnulationTrans() {
		return nomTraiteurAnnulationTrans;
	}

	public void setNomTraiteurAnnulationTrans(String nomTraiteurAnnulationTrans) {
		this.nomTraiteurAnnulationTrans = nomTraiteurAnnulationTrans;
	}

	@Column(name = "numTraiteurAnnulationTrans")
	public Long getNumTraiteurAnnulationTrans() {
		return numTraiteurAnnulationTrans;
	}

	public void setNumTraiteurAnnulationTrans(Long numTraiteurAnnulationTrans) {
		this.numTraiteurAnnulationTrans = numTraiteurAnnulationTrans;
	}

	@Column(name = "fnct_transaction_sup")
	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	@Column(name = "service_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "code_retour_moteur")
	public Integer getCodeRetourMoteur() {
		return codeRetourMoteur;
	}

	public void setCodeRetourMoteur(Integer codeRetourMoteur) {
		this.codeRetourMoteur = codeRetourMoteur;
	}

	@Column(name = "message_retour_moteur")
	public String getMessageRetourMoteur() {
		return messageRetourMoteur;
	}

	public void setMessageRetourMoteur(String messageRetourMoteur) {
		this.messageRetourMoteur = messageRetourMoteur;
	}
	
}

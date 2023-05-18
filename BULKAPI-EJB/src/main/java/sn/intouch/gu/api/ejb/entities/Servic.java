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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "service")
@NamedQuery(name = Servic.FIND_BY_NAME, query = "SELECT s FROM Servic s WHERE s.service_nom=:nom AND s.supprime=false")
public class Servic implements Serializable {
	public static final String FIND_BY_NAME = "findServiceByName";
	private Long service_id;
	private String service_nom, service_code;
	private Fournisseur fournisseur_id;
	private ServiceSubCategory service_SC_id;
	private String statut;
	private String type;
	private String categorieService;// Service standard -service soumis à un contrat de distribution
	Boolean supprime = false;
	private Boolean initiatedByCustomer;

	/////// Nouvelle définition pour les types de commission du service
	private Boolean isPlanCommPourcentage;
	private Double valPlanCommPourcentage;
	private Boolean isPlanCommFixe;
	private Double valPlanCommFixe;
	private Boolean isPlanPalier;
	private String sparePlanComm;

	/////// Nouvelle définition pour la clé de répartition du service
	private Double commGeneralGU, commGeneralReseau, commGeneralAgence, commGeneralAgent;
	private Double commItinerantGU, commItinerantReseau, commItinerantAgence, commItinerantAgent;
	private Double montantMin;
	private Double montantMax;
	private String spareOp1;
	private String spareOp2;
	private String spareOp3;
	private String spareOp4;
	private String spareOp5;

	@Id
	@Column(name = "service_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getService_id() {
		return service_id;
	}

	public void setService_id(Long service_id) {
		this.service_id = service_id;
	}

	@Column(name = "initiated_by_customer")
	public Boolean getInitiatedByCustomer() {
		return initiatedByCustomer;
	}

	public void setInitiatedByCustomer(Boolean initiatedByCustomer) {
		this.initiatedByCustomer = initiatedByCustomer;
	}

	@Column(name = "service_code")
	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	@Column(name = "service_nom")
	public String getService_nom() {
		return service_nom;
	}

	public void setService_nom(String service_nom) {
		this.service_nom = service_nom;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fournisseur_id")
	public Fournisseur getFournisseur_id() {
		return fournisseur_id;
	}

	public void setFournisseur_id(Fournisseur fournisseur_id) {
		this.fournisseur_id = fournisseur_id;
	}

	@Column(name = "service_statut")
	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_SC_id")
	public ServiceSubCategory getService_SC_id() {
		return service_SC_id;
	}

	public void setService_SC_id(ServiceSubCategory service_SC_id) {
		this.service_SC_id = service_SC_id;
	}

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

	////// Ajout de nouveaux attributs
	public Boolean getIsPlanCommPourcentage() {
		return isPlanCommPourcentage;
	}

	public void setIsPlanCommPourcentage(Boolean isPlanCommPourcentage) {
		this.isPlanCommPourcentage = isPlanCommPourcentage;
	}

	public Double getValPlanCommPourcentage() {
		return valPlanCommPourcentage;
	}

	public void setValPlanCommPourcentage(Double valPlanCommPourcentage) {
		this.valPlanCommPourcentage = valPlanCommPourcentage;
	}

	public Boolean getIsPlanCommFixe() {
		return isPlanCommFixe;
	}

	public void setIsPlanCommFixe(Boolean isPlanCommFixe) {
		this.isPlanCommFixe = isPlanCommFixe;
	}

	public Double getValPlanCommFixe() {
		return valPlanCommFixe;
	}

	public void setValPlanCommFixe(Double valPlanCommFixe) {
		this.valPlanCommFixe = valPlanCommFixe;
	}

	public Boolean getIsPlanPalier() {
		return isPlanPalier;
	}

	public void setIsPlanPalier(Boolean isPlanPalier) {
		this.isPlanPalier = isPlanPalier;
	}

	public String getSparePlanComm() {
		return sparePlanComm;
	}

	public void setSparePlanComm(String sparePlanComm) {
		this.sparePlanComm = sparePlanComm;
	}

	//////// Ajout des attributs pour la clé de répartition des commissions
	public Double getCommGeneralGU() {
		return commGeneralGU;
	}

	public void setCommGeneralGU(Double commGeneralGU) {
		this.commGeneralGU = commGeneralGU;
	}

	public Double getCommGeneralReseau() {
		return commGeneralReseau;
	}

	public void setCommGeneralReseau(Double commGeneralReseau) {
		this.commGeneralReseau = commGeneralReseau;
	}

	public Double getCommGeneralAgence() {
		return commGeneralAgence;
	}

	public void setCommGeneralAgence(Double commGeneralAgence) {
		this.commGeneralAgence = commGeneralAgence;
	}

	public Double getCommGeneralAgent() {
		return commGeneralAgent;
	}

	public void setCommGeneralAgent(Double commGeneralAgent) {
		this.commGeneralAgent = commGeneralAgent;
	}

	public Double getCommItinerantGU() {
		return commItinerantGU;
	}

	public void setCommItinerantGU(Double commItinerantGU) {
		this.commItinerantGU = commItinerantGU;
	}

	public Double getCommItinerantReseau() {
		return commItinerantReseau;
	}

	public void setCommItinerantReseau(Double commItinerantReseau) {
		this.commItinerantReseau = commItinerantReseau;
	}

	public Double getCommItinerantAgence() {
		return commItinerantAgence;
	}

	public void setCommItinerantAgence(Double commItinerantAgence) {
		this.commItinerantAgence = commItinerantAgence;
	}

	public Double getCommItinerantAgent() {
		return commItinerantAgent;
	}

	public void setCommItinerantAgent(Double commItinerantAgent) {
		this.commItinerantAgent = commItinerantAgent;
	}

	@Column(name = "service_categorie")
	public String getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(String categorieService) {
		this.categorieService = categorieService;
	}

	@Override
	public String toString() {
		return service_code;
	}

	@Column(name = "montant_min")
	public Double getMontantMin() {
		return montantMin;
	}

	public void setMontantMin(Double montantMin) {
		this.montantMin = montantMin;
	}

	@Column(name = "montant_max")
	public Double getMontantMax() {
		return montantMax;
	}

	public void setMontantMax(Double montantMax) {
		this.montantMax = montantMax;
	}

	@Column(name = "spare_op1")
	public String getSpareOp1() {
		return spareOp1;
	}

	public void setSpareOp1(String spareOp1) {
		this.spareOp1 = spareOp1;
	}

	@Column(name = "spare_op2")
	public String getSpareOp2() {
		return spareOp2;
	}

	public void setSpareOp2(String spareOp2) {
		this.spareOp2 = spareOp2;
	}

	@Column(name = "spare_op3")
	public String getSpareOp3() {
		return spareOp3;
	}

	public void setSpareOp3(String spareOp3) {
		this.spareOp3 = spareOp3;
	}

	@Column(name = "spare_op4")
	public String getSpareOp4() {
		return spareOp4;
	}

	public void setSpareOp4(String spareOp4) {
		this.spareOp4 = spareOp4;
	}

	@Column(name = "spare_op5")
	public String getSpareOp5() {
		return spareOp5;
	}

	public void setSpareOp5(String spareOp5) {
		this.spareOp5 = spareOp5;
	}

}

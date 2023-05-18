package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="compte")
public class Compte implements Serializable{

	private Long idCompte;
	private Double soldeCompte, montantLastIncrement;
	private Date dateDermaj;
	private TypeCompte typeCompte_idtypeCompte;
	private String codeGUBeneficiaire, codeNetworkBeneficiaire, codeAgenceBeneficiaire, codeBanqueBeneficiaire, codeService, proprietaire, codeAgentBeneficiaire;
	private String listCodesServices, spare1, spare2, numeroCompte, spare3, spare4;
	Boolean supprime = false ;
	private String network_groupe_code;
	private Date dateCreation;
	
	@Id
	@Column(name = "idCompte")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(Long idCompte) {
		this.idCompte = idCompte;
	}
	@Column(name = "soldeCompte")
	public Double getSoldeCompte() {
		return soldeCompte;
	}
	public void setSoldeCompte(Double soldeCompte) {
		this.soldeCompte = soldeCompte;
	}
	@Column(name = "proprietaire")
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	@Column(name = "montantLastIncrement")
	public Double getMontantLastIncrement() {
		return montantLastIncrement;
	}
	public void setMontantLastIncrement(Double montantLastIncrement) {
		this.montantLastIncrement = montantLastIncrement;
	}
	@Column(name = "spare3")
	public String getSpare3() {
		return spare3;
	}
	public void setSpare3(String spare3) {
		this.spare3 = spare3;
	}
	@Column(name = "spare4")
	public String getSpare4() {
		return spare4;
	}
	public void setSpare4(String spare4) {
		this.spare4 = spare4;
	}
	@Column(name = "dateDermaj")
	public Date getDateDermaj() {
		return dateDermaj;
	}
	public void setDateDermaj(Date dateDermaj) {
		this.dateDermaj = dateDermaj;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "typeCompte_idtypeCompte")
	public TypeCompte getTypeCompte_idtypeCompte() {
		return typeCompte_idtypeCompte;
	}
	public void setTypeCompte_idtypeCompte(TypeCompte typeCompte_idtypeCompte) {
		this.typeCompte_idtypeCompte = typeCompte_idtypeCompte;
	}
	@Column(name = "codeGUBeneficiaire")
	public String getCodeGUBeneficiaire() {
		return codeGUBeneficiaire;
	}
	public void setCodeGUBeneficiaire(String codeGUBeneficiaire) {
		this.codeGUBeneficiaire = codeGUBeneficiaire;
	}
	@Column(name = "codeNetworkBeneficiaire")
	public String getCodeNetworkBeneficiaire() {
		return codeNetworkBeneficiaire;
	}
	public void setCodeNetworkBeneficiaire(String codeNetworkBeneficiaire) {
		this.codeNetworkBeneficiaire = codeNetworkBeneficiaire;
	}
	@Column(name = "codeAgenceBeneficiaire")
	public String getCodeAgenceBeneficiaire() {
		return codeAgenceBeneficiaire;
	}
	public void setCodeAgenceBeneficiaire(String codeAgenceBeneficiaire) {
		this.codeAgenceBeneficiaire = codeAgenceBeneficiaire;
	}
	@Column(name = "codeAgentBeneficiaire")
	public String getCodeAgentBeneficiaire() {
		return codeAgentBeneficiaire;
	}
	public void setCodeAgentBeneficiaire(String codeAgentBeneficiaire) {
		this.codeAgentBeneficiaire = codeAgentBeneficiaire;
	}
	@Column(name = "codeBanqueBeneficiaire")
	public String getCodeBanqueBeneficiaire() {
		return codeBanqueBeneficiaire;
	}
	public void setCodeBanqueBeneficiaire(String codeBanqueBeneficiaire) {
		this.codeBanqueBeneficiaire = codeBanqueBeneficiaire;
	}
	@Column(name = "codeService")
	public String getCodeService() {
		return codeService;
	}
	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}
	@Column(name = "listCodesServices")
	public String getListCodesServices() {
		return listCodesServices;
	}
	public void setListCodesServices(String listCodesServices) {
		this.listCodesServices = listCodesServices;
	}
	@Column(name = "spare1")
	public String getSpare1() {
		return spare1;
	}
	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}
	@Column(name = "spare2")
	public String getSpare2() {
		return spare2;
	}
	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}
	@Column(name = "numeroCompte")
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	@Column(name = "supprime")
	public Boolean getSupprime() {
		return supprime;
	}
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}
	@Override
	public String toString() {
		return "Compte [idCompte=" + idCompte + ", soldeCompte=" + soldeCompte + ", montantLastIncrement="
				+ montantLastIncrement + ", proprietaire=" + proprietaire + "]";
	}
	public String getNetwork_groupe_code() {
		return network_groupe_code;
	}
	public void setNetwork_groupe_code(String network_groupe_code) {
		this.network_groupe_code = network_groupe_code;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
}

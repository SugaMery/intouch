package sn.intouch.gu.api.ejb.models;

import java.io.Serializable;
import java.util.Date;

public class USSD implements Serializable{
	private String destinataire;
	private Double montant;
	private Double frais;
	private String codeService;
	private String tagUssd;
	private String tagBulk;
	private String prenom;
	private String nom;
	private String token;
	private Date date;
	private String numSurfichier;
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public String getCodeService() {
		return codeService;
	}
	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}
	public String getTagUssd() {
		return tagUssd;
	}
	public void setTagUssd(String tagUssd) {
		this.tagUssd = tagUssd;
	}
	public String getTagBulk() {
		return tagBulk;
	}
	public void setTagBulk(String tagBulk) {
		this.tagBulk = tagBulk;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNumSurfichier() {
		return numSurfichier;
	}
	public void setNumSurfichier(String numSurfichier) {
		this.numSurfichier = numSurfichier;
	}
	public Double getFrais() {
		return frais;
	}
	public void setFrais(Double frais) {
		this.frais = frais;
	}
	
	
	
	

}

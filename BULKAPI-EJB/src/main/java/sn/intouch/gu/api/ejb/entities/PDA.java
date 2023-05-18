package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="pda")
public class PDA implements Serializable
{
	
	private int x;
	private int y;
	private String numeroSerie, type, fournisseur, divisionAffectation;
	private Date dateAchat, dateAffectation, dateDeclaration, dateRemplacement; //Date remplacement prévue = date déclaration + X mois
	private Date dateNextEntretien, dateLastEntretien, dateCreation, dateLastMaj;  
	private int valeurAchat;	
	
	private int pdaId, statut=1;
	private String codePda;
    private String numeroGerant;
    private String codeIMSI;
    private String numero;
    private String mdpOM="0000";
    private String etat = "ACTIF"; //actif, en panne, en maintenance, inactif
    private String codeStation;
    private String nomStation;
    private Date lastConnection;
	private Date lastAlive;
	private String lastUserPhone;
	private String pda_fcm_token;
	
	@Id
	@Column(name="pdaid", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getPdaId() {
		return pdaId;
	}	
	public void setPdaId(int pdaId) {
		this.pdaId = pdaId;
	}
	
	@Column(name="numero_serie")
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="fournisseur")
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	@Column(name="division_affectation")
	public String getDivisionAffectation() {
		return divisionAffectation;
	}
	public void setDivisionAffectation(String divisionAffectation) {
		this.divisionAffectation = divisionAffectation;
	}
	
	@Column(name="date_achat")
	public Date getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	@Column(name="date_affectation")
	public Date getDateAffectation() {
		return dateAffectation;
	}
	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}
	
	@Column(name="date_declaration")
	public Date getDateDeclaration() {
		return dateDeclaration;
	}
	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}
	
	@Column(name="date_remplacement")
	public Date getDateRemplacement() {
		return dateRemplacement;
	}
	public void setDateRemplacement(Date dateRemplacement) {
		this.dateRemplacement = dateRemplacement;
	}
	
	@Column(name="date_next_entretien")
	public Date getDateNextEntretien() {
		return dateNextEntretien;
	}
	public void setDateNextEntretien(Date dateNextEntretien) {
		this.dateNextEntretien = dateNextEntretien;
	}
	
	@Column(name="date_last_entretien")
	public Date getDateLastEntretien() {
		return dateLastEntretien;
	}
	public void setDateLastEntretien(Date dateLastEntretien) {
		this.dateLastEntretien = dateLastEntretien;
	}
	
	@Column(name="datecreation")
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	@Column(name="datelastmaj")
	public Date getDateLastMaj() {
		return dateLastMaj;
	}
	public void setDateLastMaj(Date dateLastMaj) {
		this.dateLastMaj = dateLastMaj;
	}
	
	@Column(name="valeur_achat")
	public int getValeurAchat() {
		return valeurAchat;
	}
	public void setValeurAchat(int valeurAchat) {
		this.valeurAchat = valeurAchat;
	}
	
	
	@Column(name="pda_code")
    public String getCodePda() {
        return codePda;
    }
	public void setCodePda(String codePda) {
        this.codePda = codePda;
    }
    
    @Column(name="pda_numerogerant")
    public String getNumeroGerant() {
        return numeroGerant;
    }
	public void setNumeroGerant(String numeroGerant) {
        this.numeroGerant = numeroGerant;
    }

    @Column(name="pda_codeimsi")
    public String getCodeIMSI() {
        return codeIMSI;
    }
    public void setCodeIMSI(String codeIMSI) {
        this.codeIMSI = codeIMSI;
    }

    @Column(name="pda_numero")
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    @Column(name="pda_mdpom")
    public String getMdpOM() {
        return mdpOM;
    }
    public void setMdpOM(String mdpOM) {
        this.mdpOM = mdpOM;
    }


    @Column(name="statut")
	public int getStatut() {
		return statut;
	}
	public void setStatut(int statut) {
		this.statut = statut;
	}
    
	
	@Column(name="etat")
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Column(name="code_station")
	public String getCodeStation() {
		return codeStation;
	}
	public void setCodeStation(String codeStation) {
		this.codeStation = codeStation;
	}
	
	@Column(name="nom_station")
	public String getNomStation() {
		return nomStation;
	}
	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
	}
	
	@Column(name="x")
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	@Column(name="y")
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Column(name="last_connection")
	public Date getLastConnection() {
		return lastConnection;
	}
	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}
	
	@Column(name="last_alive")
	public Date getLastAlive() {
		return lastAlive;
	}
	public void setLastAlive(Date lastAlive) {
		this.lastAlive = lastAlive;
	}
	@Column(name="pda_fcm_token")
	public String getPda_fcm_token() {
		return pda_fcm_token;
	}
	public void setPda_fcm_token(String pda_fcm_token) {
		this.pda_fcm_token = pda_fcm_token;
	}
		
}
package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "utilisateurs")
@NamedQuery(name=Utilisateur.FIND_BY_NUM, query="SELECT u FROM Utilisateur u WHERE u.numero=:numero AND u.supprime=false")
@Inheritance(strategy=InheritanceType.JOINED)
public class Utilisateur implements Serializable {
	public static final String FIND_BY_NUM = "findUserByNumber";
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	Long id, compteur, numero;
 	String login, password, nom, prenom, poste, imageProfil, mail; 	
 	private String langue;
	Boolean supprime = false ;
	Set<Utilisateur> utilisateursLogges = new HashSet<Utilisateur>() ;	
	Fonction fonction;
	private NetworkGroup networkgroup_id;


 

	public Utilisateur(Long id, String nom, String prenom, Long numero, String mail) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
		this.mail = mail;
	}

	@Id
	@Column(name = "usr_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "usr_login")
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column
	public String getImageProfil() {
		return imageProfil;
	}

	public void setImageProfil(String imageProfil) {
		this.imageProfil = imageProfil;
	}

	@Column(name="usr_poste")
	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	
	@Column(name = "usr_compteur")
	public Long getCompteur() {
		if(compteur == null ) compteur = 0l ;
		return compteur;
	}

	public void setCompteur(Long compteur) {
		this.compteur = compteur;
	}

	@Column(name = "usr_password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "usr_nom")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "usr_prenom")
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	@Column(name = "usr_numero")
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}
	@Column(name = "usr_mail")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	

	@Column( name = "usr_supprime")
	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "utilisateurs_logges", 
		joinColumns = { @JoinColumn(name = "ul_controleur_id") }, inverseJoinColumns = { @JoinColumn(name = "ul_controle_id") })
	public Set<Utilisateur> getUtilisateursLogges() {
		return utilisateursLogges;
	}

	public void setUtilisateursLogges(Set<Utilisateur> utilisateursLogges) {
		this.utilisateursLogges = utilisateursLogges;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usr_fct_code")
	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "networkgroup_id")
	public NetworkGroup getNetworkgroup_id() {
		return networkgroup_id;
	}
	public void setNetworkgroup_id(NetworkGroup networkgroup_id) {
		this.networkgroup_id = networkgroup_id;
	}

    
	
}

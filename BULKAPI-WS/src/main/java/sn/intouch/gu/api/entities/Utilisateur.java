package sn.intouch.gu.api.entities;

import java.io.Serializable;

public class Utilisateur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private String telephone;
	private String profil;
	private String prenom;
	private String nom;
	private int userHasConnected;
	private String position;
	private int id;
	private long pmPrecedent;
	private long cashoutPrecedent;
	private long encaissementPrecedent;
	private long pm;
	private long cashout;
	private long encaissement;
	private long datemaj;
	private String mail;
	private String poste;
	private String langue;
	private String welcomeBookmark;
	private String currency;

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(int id, String profil, String telephone, String prenom, String nom, String login,
			String password, int userHasConnected, String position) {
		super();
		this.id = id;
		this.login = login;
		if (password != null)
			this.password = password;
		this.profil = profil;
		this.prenom = prenom;
		this.nom = nom;
		this.setTelephone(telephone);
		this.userHasConnected = userHasConnected;
		this.position = position;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getUserHasConnected() {
		return userHasConnected;
	}

	public void setUserHasConnected(int userHasConnected) {
		this.userHasConnected = userHasConnected;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public long getPmPrecedent() {
		return pmPrecedent;
	}

	public void setPmPrecedent(long pmPrecedent) {
		this.pmPrecedent = pmPrecedent;
	}

	public long getCashoutPrecedent() {
		return cashoutPrecedent;
	}

	public void setCashoutPrecedent(long cashoutPrecedent) {
		this.cashoutPrecedent = cashoutPrecedent;
	}

	public long getEncaissementPrecedent() {
		return encaissementPrecedent;
	}

	public void setEncaissementPrecedent(long encaissementPrecedent) {
		this.encaissementPrecedent = encaissementPrecedent;
	}

	public long getPm() {
		return pm;
	}

	public void setPm(long pm) {
		this.pm = pm;
	}

	public long getCashout() {
		return cashout;
	}

	public void setCashout(long cashout) {
		this.cashout = cashout;
	}

	public long getEncaissement() {
		return encaissement;
	}

	public void setEncaissement(long encaissement) {
		this.encaissement = encaissement;
	}

	public long getDatemaj() {
		return datemaj;
	}

	public void setDatemaj(long datemaj) {
		this.datemaj = datemaj;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getWelcomeBookmark() {
		return welcomeBookmark;
	}

	public void setWelcomeBookmark(String welcomeBookmark) {
		this.welcomeBookmark = welcomeBookmark;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
    
}
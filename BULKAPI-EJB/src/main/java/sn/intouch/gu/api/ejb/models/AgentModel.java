package sn.intouch.gu.api.ejb.models;

public class AgentModel {
	private String login;
	private String password;
	private String telephone;
	private String profil;
	private String prenom;
	private String nom;
	private String userHasConnected;
	private String position;

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getUserHasConnected() {
		return userHasConnected;
	}

	public void setUserHasConnected(String userHasConnected) {
		this.userHasConnected = userHasConnected;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public AgentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*public AgentModel(Agent agent){
		this.login=agent.getAgtLogin();
		this.password=agent.getAgtPassword();
		this.telephone=agent.getAgtTelephone();
		this.profil=agent.;
		private String prenom;
		private String nom;
		private String userHasConnected;
		private String position;
	}*/
}

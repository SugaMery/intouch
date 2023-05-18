package sn.intouch.gu.api.entities;

import java.io.Serializable;
import java.util.List;

import sn.intouch.gu.api.utils.Service;


public class TokenAuthModelWeb extends ExchangeAbstract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String password;
	private String tokenSms;
	private Utilisateur utilisateur;
	private List<Utilisateur> agents;
	private List<Service> services;
	private String touchWebLogoFolder;
	private List<Utilisateur> agentsvalidateur;
	private List<Utilisateur> agentsenregistreur;

	


	public TokenAuthModelWeb() {
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTokenSms() {
		return tokenSms;
	}

	public void setTokenSms(String tokenSms) {
		this.tokenSms = tokenSms;
	}

	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Utilisateur> getAgents() {
		return agents;
	}

	public void setAgents(List<Utilisateur> agents) {
		this.agents = agents;
	}

	public String getTouchWebLogoFolder() {
		return touchWebLogoFolder;
	}

	public void setTouchWebLogoFolder(String touchWebLogoFolder) {
		this.touchWebLogoFolder = touchWebLogoFolder;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	public List<Utilisateur> getAgentsvalidateur() {
		return agentsvalidateur;
	}

	public void setAgentsvalidateur(List<Utilisateur> agentsvalidateur) {
		this.agentsvalidateur = agentsvalidateur;
	}

	public List<Utilisateur> getAgentsenregistreur() {
		return agentsenregistreur;
	}

	public void setAgentsenregistreur(List<Utilisateur> agentsenregistreur) {
		this.agentsenregistreur = agentsenregistreur;
	}
    
}

package sn.intouch.gu.api.entities;

import java.io.Serializable;

public class UserExchange extends ExchangeAbstract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;

	public UserExchange() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
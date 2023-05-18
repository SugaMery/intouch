package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import sn.intouch.gu.api.ejb.models.TokenAPIModel;

@Entity
@Table(name = "token_api")
public class TokenAPI implements Serializable{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "partner_id")
	private String partner_id;
	@Column(name = "supprime")
	private Boolean supprime;

	public TokenAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}

	public TokenAPI(TokenAPIModel tokenModel) {
		this.username = tokenModel.getUsername();
		this.password = tokenModel.getPassword();
		this.partner_id = tokenModel.getPartner_id();
	}
}

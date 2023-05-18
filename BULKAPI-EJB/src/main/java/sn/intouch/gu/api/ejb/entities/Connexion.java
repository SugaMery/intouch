package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe de gestion des connexions avec token
 * @author Diop Abd.
 *
 */
@Entity
@Table(name = "connexions")
public class Connexion implements Serializable {

	private Long id;
	private String login;
	private String password;
	private String mac;
	private Date date;
	private String token;
	private boolean tokenValidated;
	private Date dateUpdate;

	public Connexion() {
		super();
	}

	@Id
	@Column(name = "conn_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "conn_login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "conn_password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "conn_mac")
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "conn_date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "conn_token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name="conn_token_validated")
	public boolean isTokenValidated() {
		return tokenValidated;
	}

	public void setTokenValidated(boolean tokenValidated) {
		this.tokenValidated = tokenValidated;
	}

	public Connexion(String login, String password, String mac) {
		super();
		this.login = login;
		this.password = password;
		this.mac = mac;
		this.date = new Date();
		this.dateUpdate=new Date();
	}
	
	@Column(name = "conn_date_update")
	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	@Override
	public String toString() {
		return "Connexion [id=" + id + ", login=" + login + ", password=" + password + ", mac=" + mac + ", date=" + date
				+ ", token=" + token + ", tokenValidated="+tokenValidated+"]";
	}

}

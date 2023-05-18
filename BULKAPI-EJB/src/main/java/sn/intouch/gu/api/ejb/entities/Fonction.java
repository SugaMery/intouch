package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "fonctions")
public class Fonction implements Serializable {
	
	String code, description, welcomeBookmark;
	Set<Action> actions = new HashSet<Action>();
//	private NetworkGroup networkgroup_id;
	
	Boolean supprime = false ;

	@Id
	@Column(name = "FCT_CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "networkgroup_id")
//	public NetworkGroup getNetworkgroup_id() {
//		return networkgroup_id;
//	}
//
//	public void setNetworkgroup_id(NetworkGroup networkgroup_id) {
//		this.networkgroup_id = networkgroup_id;
//	}

	@Column(name = "FCT_DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "FCT_WELCOME_BOOKMARK")
	public String getWelcomeBookmark() {
		return welcomeBookmark;
	}

	public void setWelcomeBookmark(String welcomeBookmark) {
		this.welcomeBookmark = welcomeBookmark;
	}

	@ManyToMany
	@JoinTable(name = "droits_fonctions", joinColumns = { @JoinColumn(name = "df_fct_code") }, inverseJoinColumns = { @JoinColumn(name = "df_act_code") })
	public Set<Action> getActions() {
		return actions;
	}

	public void setActions(Set<Action> actions) {
		this.actions = actions;
	}
	


	@Column( name = "FCT_SUPPRIME")
	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}

	@Override
	public String toString() {
		return  code ;
	}

}
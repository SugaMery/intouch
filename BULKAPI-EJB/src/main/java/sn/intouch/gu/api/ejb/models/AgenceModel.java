package sn.intouch.gu.api.ejb.models;

public class AgenceModel {
	private String salepoint_nom, salepoint_code, latitude, longitude;
	private String network_nom, network_code;
	private String codeCollectivite;
	private String gerTelephone;
	private String gerNom, gerPrenom, gerLogin;
	private String contexte;

	public AgenceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSalepoint_nom() {
		return salepoint_nom;
	}

	public void setSalepoint_nom(String salepoint_nom) {
		this.salepoint_nom = salepoint_nom;
	}

	public String getSalepoint_code() {
		return salepoint_code;
	}

	public void setSalepoint_code(String salepoint_code) {
		this.salepoint_code = salepoint_code;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getNetwork_nom() {
		return network_nom;
	}

	public void setNetwork_nom(String network_nom) {
		this.network_nom = network_nom;
	}

	public String getNetwork_code() {
		return network_code;
	}

	public void setNetwork_code(String network_code) {
		this.network_code = network_code;
	}

	public String getCodeCollectivite() {
		return codeCollectivite;
	}

	public void setCodeCollectivite(String codeCollectivite) {
		this.codeCollectivite = codeCollectivite;
	}

	public String getGerTelephone() {
		return gerTelephone;
	}

	public void setGerTelephone(String gerTelephone) {
		this.gerTelephone = gerTelephone;
	}

	public String getGerNom() {
		return gerNom;
	}

	public void setGerNom(String gerNom) {
		this.gerNom = gerNom;
	}

	public String getGerPrenom() {
		return gerPrenom;
	}

	public void setGerPrenom(String gerPrenom) {
		this.gerPrenom = gerPrenom;
	}

	public String getGerLogin() {
		return gerLogin;
	}

	public void setGerLogin(String gerLogin) {
		this.gerLogin = gerLogin;
	}

	public String getContexte() {
		return contexte;
	}

	public void setContexte(String contexte) {
		this.contexte = contexte;
	}

}

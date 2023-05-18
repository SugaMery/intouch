package sn.intouch.gu.api.ejb.models;

public abstract class ExchangeAbstract {
	private Long token;
	private Long date;
	private String adresseMAC;
	private String codePDA;
	private String loginAgent;
	private String salePointCode;
	private String errorCode;
	private String errorMessage;

	public Long getToken() {
		return token;
	}

	public void setToken(Long token) {
		this.token = token;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getAdresseMAC() {
		return adresseMAC;
	}

	public void setAdresseMAC(String adresseMAC) {
		this.adresseMAC = adresseMAC;
	}

	public String getCodePDA() {
		return codePDA;
	}

	public void setCodePDA(String codePDA) {
		this.codePDA = codePDA;
	}

	public String getLoginAgent() {
		return loginAgent;
	}

	public void setLoginAgent(String loginAgent) {
		this.loginAgent = loginAgent;
	}

	public String getSalePointCode() {
		return salePointCode;
	}

	public void setSalePointCode(String salePointCode) {
		this.salePointCode = salePointCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}

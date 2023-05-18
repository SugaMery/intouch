package sn.intouch.gu.api.entities;

import java.io.Serializable;

public class TokenAuthModel extends ExchangeAbstract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String password;
	private String tokenSms;

	public TokenAuthModel() {
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

}

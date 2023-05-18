package sn.intouch.gu.api.entities;

public abstract class AbstractRequest {
	private String partnerId;
	private String loginApi;
	private String passwordApi;
	private String callBackUrl;
	private String infosClient;

	public AbstractRequest() {
		super();
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getLoginApi() {
		return loginApi;
	}

	public void setLoginApi(String loginApi) {
		this.loginApi = loginApi;
	}

	public String getPasswordApi() {
		return passwordApi;
	}

	public void setPasswordApi(String passwordApi) {
		this.passwordApi = passwordApi;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getInfosClient() {
		return infosClient;
	}

	public void setInfosClient(String infosClient) {
		this.infosClient = infosClient;
	}

}

package sn.intouch.gu.api.entities;

public class GetFraisRequest {
	private String service_id;
	private String recipient_country_code;
	private String partner_id;
	private String login_api;
	private String password_api;
	private Double amount;
	private String recipient_phone_number;
	private String typeOperation;

	public GetFraisRequest() {
		super();
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getRecipient_country_code() {
		return recipient_country_code;
	}

	public void setRecipient_country_code(String recipient_country_code) {
		this.recipient_country_code = recipient_country_code;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getLogin_api() {
		return login_api;
	}

	public void setLogin_api(String login_api) {
		this.login_api = login_api;
	}

	public String getPassword_api() {
		return password_api;
	}

	public void setPassword_api(String password_api) {
		this.password_api = password_api;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRecipient_phone_number() {
		return recipient_phone_number;
	}

	public void setRecipient_phone_number(String recipient_phone_number) {
		this.recipient_phone_number = recipient_phone_number;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}

}

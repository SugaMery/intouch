package sn.intouch.gu.api.entities;

public class GetFraisResponse {
	private String service_id;
	private Double amount;
	private String status;
	private Double frais;
	private String recipient_country_code;
	private String message;

	public GetFraisResponse() {
		super();
	}

	public GetFraisResponse(GetFraisExchange exchange) {
		this.amount = exchange.getMontant();
		this.frais = exchange.getFrais();
		this.recipient_country_code = exchange.getCountryCode();
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getFrais() {
		return frais;
	}

	public void setFrais(Double frais) {
		this.frais = frais;
	}

	public String getRecipient_country_code() {
		return recipient_country_code;
	}

	public void setRecipient_country_code(String recipient_country_code) {
		this.recipient_country_code = recipient_country_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

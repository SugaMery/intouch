package sn.intouch.gu.api.entities;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ExchangeResponse", description = "ExchangeResponse description")
public class ExchangeResponse {

	@ApiModelProperty(value = "service_id", dataType = "java.lang.String", required = true)
	private String service_id;
	@ApiModelProperty(value = "gu_transaction_id", dataType = "java.lang.String", required = true)
	private String gu_transaction_id;
	@ApiModelProperty(value = "status", dataType = "java.lang.String", required = true)
	private String status;
	private Date transaction_date;
	private String recipient_phone_number;
	private Float amount;
	private String partner_transaction_id;

	public ExchangeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getGu_transaction_id() {
		return gu_transaction_id;
	}

	public void setGu_transaction_id(String gu_transaction_id) {
		this.gu_transaction_id = gu_transaction_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getRecipient_phone_number() {
		return recipient_phone_number;
	}

	public void setRecipient_phone_number(String recipient_phone_number) {
		this.recipient_phone_number = recipient_phone_number;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getPartner_transaction_id() {
		return partner_transaction_id;
	}

	public void setPartner_transaction_id(String partner_transaction_id) {
		this.partner_transaction_id = partner_transaction_id;
	}

	public static ExchangeResponse fromTransactionExchangeToAirtimeResponse(TransactionExchange transactionExchange) {
		ExchangeResponse airtimeResponse = new ExchangeResponse();
		airtimeResponse.setAmount(Float.valueOf(String.valueOf(transactionExchange.getTransactionMontant())));
		airtimeResponse.setGu_transaction_id(String.valueOf(transactionExchange.getToken()));
		if (transactionExchange.getServiceExchange() != null) {
			if (transactionExchange.getServiceExchange().getInfosSup() != null) {
				airtimeResponse.setRecipient_phone_number(
						transactionExchange.getServiceExchange().getInfosSup().get("destinataire"));
			}
			airtimeResponse.setService_id(transactionExchange.getServiceExchange().getServiceCode());
		}
		// airtimeResponse.setTransaction_date(new
		// Date(transactionExchange.getDate()));
		return airtimeResponse;
	}

//	public static ExchangeResponse fromTransactionToExchangeResponse(Transaction transaction) {
//		ExchangeResponse exchangeResponse = new ExchangeResponse();
//		exchangeResponse.setAmount(Float.valueOf(String.valueOf(transaction.getTransaction_montant())));
//		exchangeResponse.setGu_transaction_id(transaction.getNumTransFromGu());
//		exchangeResponse.setPartner_transaction_id(transaction.getNumTransFromPartenaire());
//		exchangeResponse.setRecipient_phone_number(transaction.getCustomer_code());
//		exchangeResponse.setService_id(transaction.getService_code());
//		exchangeResponse.setTransaction_date(transaction.getTransaction_date());
//		return exchangeResponse;
//	}
}

package sn.intouch.gu.api.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ExchangeRequest", description = "ExchangeRequest description")
public class ExchangeRequest {

	private String service_id;
	private String ticket;
	private String recipient_phone_number;
	private String recipient_country_code;
	private Float amount;
	private String partner_id;
	private String partner_transaction_id;

	public ExchangeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ApiModelProperty(value = "service_id", required = true)
	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getRecipient_phone_number() {
		return recipient_phone_number;
	}

	public void setRecipient_phone_number(String recipient_phone_number) {
		this.recipient_phone_number = recipient_phone_number;
	}

	public String getRecipient_country_code() {
		return recipient_country_code;
	}

	public void setRecipient_country_code(String recipient_country_code) {
		this.recipient_country_code = recipient_country_code;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getPartner_transaction_id() {
		return partner_transaction_id;
	}

	public void setPartner_transaction_id(String partner_transaction_id) {
		this.partner_transaction_id = partner_transaction_id;
	}

	public static TransactionExchange fromExchangeRequestToTransactionExchange(ExchangeRequest exchangeRequest) {
		TransactionExchange tre = new TransactionExchange();
		tre.setSalePointCode("INTDK0004");
		tre.setLoginAgent("773893078");
		// tre.setLoginAgent("766450058");
		ServiceExchange srve = new ServiceExchange();
		MoyenExchange moy = new MoyenExchange();
		Map<String, String> hmap = new HashMap<String, String>();
		hmap.put("destinataire", exchangeRequest.getRecipient_phone_number());
		srve.setInfosSup(hmap);
		srve.setServiceCode(exchangeRequest.getService_id());
		moy.setPayementModeCode("CASH");
		tre.setCodePDA(exchangeRequest.getPartner_id());
		try {
			tre.setTransactionMontant(Double.valueOf(exchangeRequest.getAmount()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (exchangeRequest.getService_id().equalsIgnoreCase("AIRTIMEORANGE")) {
			tre.setService_type("ORANGE");
		}
		if (exchangeRequest.getService_id().equalsIgnoreCase("AIRTIMETIGO")) {
			tre.setService_type("TIGO");
		}
		try {
			tre.setToken(Long.valueOf(exchangeRequest.getPartner_transaction_id()));
		} catch (Exception e) {
			e.printStackTrace();
			tre.setToken(new Date().getTime());
		}
		tre.setServiceExchange(srve);
		tre.setMoyenExchange(moy);
		return tre;
	}

//	public static Transaction fromExhangeRequestToTransaction(ExchangeRequest exchangeRequest) {
//
//		Transaction transaction = new Transaction();
//		transaction.setAgent_code("773893078");
//		transaction.setSalepoint_code("INTDK0004");
//		transaction.setNetwork_code("INTDK");
//		transaction.setCustomer_code(exchangeRequest.getRecipient_phone_number());
//		transaction.setNumTransFromGu(String.valueOf(new Date().getTime()));
//		transaction.setNumTransFromPartenaire(exchangeRequest.getPartner_transaction_id());
//		transaction.setNumTransFromService(exchangeRequest.getPartner_transaction_id());
//		transaction.setPayement_mode_code("CASH");
//		transaction.setPda_code(exchangeRequest.getPartner_id());
//		transaction.setService_code("TOUCHCASH");
//		transaction.setTransaction_date(new Date());
//		transaction.setTransaction_montant(Double.valueOf(exchangeRequest.getAmount()));
//		transaction.setTransactionStatut("TRANSACTION_NORMALE");
//		if (exchangeRequest.getService_id().contains("CASHOUT")) {
//			transaction.setType("DECAISSEMENT");
//		} else {
//			transaction.setType("ENCAISSEMENT");
//		}
//		transaction.setCommissionAgence(0D);
//		transaction.setCommissionGu(0D);
//		return transaction;
//	}

}

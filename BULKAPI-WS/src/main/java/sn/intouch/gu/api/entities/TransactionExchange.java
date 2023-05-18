package sn.intouch.gu.api.entities;

import sn.intouch.gu.api.utils.Utils;

public class TransactionExchange extends ExchangeAbstract {

	private ServiceExchange serviceExchange;
	private MoyenExchange moyenExchange;
	private Double transactionMontant;
	private Double transactionRemise;
	private String service_type;

	public ServiceExchange getServiceExchange() {
		return serviceExchange;
	}

	public void setServiceExchange(ServiceExchange serviceExchange) {
		this.serviceExchange = serviceExchange;
	}

	public MoyenExchange getMoyenExchange() {
		return moyenExchange;
	}

	public void setMoyenExchange(MoyenExchange moyenExchange) {
		this.moyenExchange = moyenExchange;
	}

	public Double getTransactionMontant() {
		return transactionMontant;
	}

	public void setTransactionMontant(Double transactionMontant) {
		this.transactionMontant = transactionMontant;
	}

	public Double getTransactionRemise() {
		return transactionRemise;
	}

	public void setTransactionRemise(Double transactionRemise) {
		this.transactionRemise = transactionRemise;
	}

	public TransactionExchange() {
		super();
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	 @Override
	    public String toString() {
	        return Utils.gson.toJson(this);
	    }
	
}

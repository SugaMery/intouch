package sn.intouch.gu.api.entities;

import java.util.List;

import sn.intouch.gu.api.ejb.entities.Transaction;
import sn.intouch.gu.api.ejb.models.USSD;

public class TransactionModel  extends ExchangeAbstract {
	private List<USSD> transactions;

	public List<USSD> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<USSD> transactions) {
		this.transactions = transactions;
	}

	
	

}

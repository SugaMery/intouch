package sn.intouch.gu.api.ejb.services;

import java.util.List;

import javax.ejb.Remote;

import sn.intouch.gu.api.ejb.entities.Transaction;
@Remote
public interface TransactionService {
	
	public List<Transaction> getTransactionByBulkPay(String idBulk);

}

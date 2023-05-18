package sn.intouch.gu.api.ejb.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import sn.intouch.gu.api.ejb.entities.Transaction;

@Stateless
public class TransactionServiceBean  implements TransactionService {
	
	private static Logger log = Logger.getLogger(TransactionServiceBean.class);
	
	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;



	@Override
	public List<Transaction> getTransactionByBulkPay(String idBulk) {
		
		List<Transaction> liste=new ArrayList<Transaction>();
		
		try {
			String sql="from Transaction t where t.transactionStatut ='TRANSACTION_NORMALE'  and  t.numTransFromGu in (select u.token from USSDOperation u where "
					+ " u.spareOp3 LIKE :idBulk)";
			Query query = em.createQuery(sql);
			query.setParameter("idBulk", "%"+idBulk+"%");
			liste=query.getResultList();
		} catch (Exception e) {
			log.error("",e);
		}
		
		return liste;
	}

}

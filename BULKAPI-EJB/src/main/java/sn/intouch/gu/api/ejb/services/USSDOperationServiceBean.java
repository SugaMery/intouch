package sn.intouch.gu.api.ejb.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import sn.intouch.gu.api.ejb.entities.USSDOperation;
import sn.intouch.gu.api.ejb.entities.USSDOperationMois;
@Stateless
public class USSDOperationServiceBean implements USSDOperationService {
	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;
	private static Logger log = Logger.getLogger(USSDOperationServiceBean.class);

	@Override
	public USSDOperation getOperationByToken(String token) {
		List<USSDOperation> liste=new ArrayList<USSDOperation>();
		USSDOperation ussd=null;
		
		try {
			String sql="from  USSDOperation u where u.token =:token";
			Query query = em.createQuery(sql);
			query.setParameter("token", token);
			liste=query.getResultList();
			
			if( liste!=null && !liste.isEmpty()){
				ussd=liste.get(0);
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return ussd;
	}

	@Override
	public USSDOperation getJumUssd(String token, String idbulk) {
		
		List<USSDOperation> liste=new ArrayList<USSDOperation>();
		USSDOperation ussd=null;
		try {
			String sql=" from USSDOperation u where u.token !=:token and u.spareOp3 =:idbulk";
			Query query = em.createQuery(sql);
			query.setParameter("token", token);
			liste=query.getResultList();
			
			if( liste!=null && !liste.isEmpty()){
				ussd=liste.get(0);
			}
			
		} catch (Exception e) {
			log.error("", e);
		}
		
		return ussd;
	}

	@Override
	public List<USSDOperation> getSuccessUssd(String idbulk) {
		List<USSDOperation> liste = new ArrayList<USSDOperation>();
		
		try {
			String sql="from USSDOperation u where u.tag ='finished'  and u.spareOp3 LIKE :idbulk and  u.codeService != 'BULKPAYMENT' and "
					+ " u.token in (select t.numTransFromGu from Transaction t where t.transactionStatut ='TRANSACTION_NORMALE')";
			
			Query query = em.createQuery(sql);
			query.setParameter("idbulk", "%"+idbulk+"%");
			liste=query.getResultList();
		} catch (Exception e) {
			log.error("", e);
		}
		return liste;
	}

	@Override
	public List<USSDOperation> getFaildeUssd(String idbulk) {
		List<USSDOperation> liste = new ArrayList<USSDOperation>();

		try {
			String sql="from USSDOperation u where u.tag ='finished'  and u.spareOp3 LIKE :idbulk   and  u.codeService != 'BULKPAYMENT' and "
					+ " u.token not  in (select t.numTransFromGu from Transaction t where t.transactionStatut ='TRANSACTION_NORMALE')";
			
			Query query = em.createQuery(sql);
			query.setParameter("idbulk", "%"+idbulk+"%");
			liste=query.getResultList();
		} catch (Exception e) {
			log.error("", e);
		}
		return liste;
	}

	@Override
	public List<USSDOperation> getEncoursUssd(String idbulk) {
		List<USSDOperation> liste = new ArrayList<USSDOperation>();

		try {
			String sql="from USSDOperation u where u.tag ='sended' or  u.tag ='initiated'  and u.spareOp3 LIKE :idbulk  and  u.codeService != 'BULKPAYMENT'";
			Query query = em.createQuery(sql);
			query.setParameter("idbulk", "%"+idbulk+"%");
			liste=query.getResultList();
		} catch (Exception e) {
			log.error("", e);
		}
		return liste;
	}

	@Override
	public List<USSDOperation> getAllUssd(String idbulk,String codeAgence) {
		List<USSDOperation> liste = new ArrayList<USSDOperation>();

		try {
			String sql="from USSDOperation u  where  u.spareOp3 LIKE :idbulk  and  u.codeService != 'BULKPAYMENT'  and u.codeSalePoint =:codeAgence"
					+ " and u.token in ( select max(u.token) from  USSDOperation u  where  u.spareOp3 LIKE :idbulk  and  u.codeService != 'BULKPAYMENT' and u.codeSalePoint =:codeAgence  group by "
					+ " u.spareOp3 ) ";
			Query query = em.createQuery(sql);
			query.setParameter("idbulk", "%"+idbulk+"%");
			query.setParameter("codeAgence", codeAgence);
			liste=query.getResultList();
		} catch (Exception e) {
			log.error("", e);
		}
		return liste;
	}

	@Override
	public List<USSDOperation> getSuccess(String idbulk) {
		List<USSDOperation> liste = new ArrayList<USSDOperation>();

		try {
			String sql="from USSDOperation u  where  u.spareOp3 LIKE :idbulk  and  u.actionDone  is null and t.tag ='finished'";
			Query query = em.createQuery(sql);
			query.setParameter("idbulk", "%"+idbulk+"%");
			liste=query.getResultList();
		} catch (Exception e) {
			log.error("", e);
		}
		return liste;
	}

	@Override
	public Long getNumberFailed(String idbulk,String codeAgence) {
		Long nb=0L;
		
		try {
			String sql="select count(u.token) from USSDOperation u  where  u.spareOp3 LIKE :idbulk  and  u.tag ='finished' and  u.actionDone LIKE :valeur "
					+ " and u.codeSalePoint =:codeAgence  and u.date in ( select max(u.date) from  USSDOperation u  where  u.spareOp3 LIKE :idbulk "
					+ " u.codeSalePoint =:codeAgence  group by u.spareOp3) ";
			Query query = em.createQuery(sql);
			query.setParameter("idbulk", "%"+idbulk+"%");
			query.setParameter("valeur", "%Echec%");
			query.setParameter("codeAgence", codeAgence);
			nb=(Long) query.getSingleResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return nb;
	}

	@Override
	public List<USSDOperationMois> getAllUssdMois(String idbulk, String codeAgence) {
		List<USSDOperationMois> liste = new ArrayList<USSDOperationMois>();

		try {
			String sql="from USSDOperationMois u  where  u.spareOp3 LIKE :idbulk  and  u.codeService != 'BULKPAYMENT'  and u.codeSalePoint =:codeAgence"
					+ " and u.token in ( select max(u.token) from  USSDOperationMois u  where  u.spareOp3 LIKE :idbulk  and  u.codeService != 'BULKPAYMENT' and u.codeSalePoint =:codeAgence  group by "
					+ " u.spareOp3 ) ";
			Query query = em.createQuery(sql);
			query.setParameter("idbulk", "%"+idbulk+"%");
			query.setParameter("codeAgence", codeAgence);
			liste=query.getResultList();
		} catch (Exception e) {
			log.error("", e);
		}
		return liste;
	}

}

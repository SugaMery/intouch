package sn.intouch.gu.api.ejb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import sn.intouch.gu.api.ejb.entities.Operation;
import sn.intouch.gu.api.ejb.entities.OperationMois;

@Stateless
public class OperationServiceBean implements OperationService{

	private static Logger log = Logger.getLogger(OperationServiceBean.class);

	/**
	 * Session Bean implementation class SalePointServiceBean
	 */
	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;


	@Override
	public List<Operation> getOperations(String codeAgence, Date dateDeb, Date dateFin) {
		List<Operation> operations =new ArrayList<Operation>();

		try {
			String sql=" from Operation o where o.typeOperationOp.codeTypeOp = 'APPROVISIONNEMENT_DISTRIBUTEUR' "+
					" and o.spareOp1 =:codeAgence";

			if(dateDeb!=null){
				sql += " and DATE(o.dateOp) >=:dateDeb";
			}

			if(dateFin!=null){
				sql += " and DATE(o.dateOp) <=:dateFin";
			}

			Query query = em.createQuery(sql);

			query.setParameter("codeAgence", codeAgence);

			if (dateDeb != null)
				query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
			if (dateFin != null)
				query.setParameter("dateFin", dateFin, TemporalType.DATE);

			operations=query.getResultList();
		} catch (Exception e) {
			log.error("", e);
		}
		return operations;
	}


	@Override
	public List<OperationMois> getOperationsMois(String codeAgence, Date dateDeb, Date dateFin) {
		List<OperationMois> operations =new ArrayList<OperationMois>();

		try {
			String sql=" from OperationMois o where o.typeOperationOp.codeTypeOp = 'APPROVISIONNEMENT_DISTRIBUTEUR' "+
					" and o.spareOp1 =:codeAgence";

			if(dateDeb!=null){
				sql += " and DATE(o.dateOp) >=:dateDeb";
			}

			if(dateFin!=null){
				sql += " and DATE(o.dateOp) <=:dateFin";
			}

			Query query = em.createQuery(sql);

			query.setParameter("codeAgence", codeAgence);

			if (dateDeb != null)
				query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
			if (dateFin != null)
				query.setParameter("dateFin", dateFin, TemporalType.DATE);

			operations=query.getResultList();
		} catch (Exception e) {
			log.error("", e);
		}
		return operations;
	}

}

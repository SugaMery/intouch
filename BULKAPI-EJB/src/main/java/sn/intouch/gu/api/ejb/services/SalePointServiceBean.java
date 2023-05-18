package sn.intouch.gu.api.ejb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.intouch.gu.api.ejb.entities.Network;
import sn.intouch.gu.api.ejb.entities.SalePoint;


@Stateless
public class SalePointServiceBean implements SalePointService {

	private static final Logger LOG = Logger.getLogger(SalePointServiceBean.class.getName());

	/**
	 * Session Bean implementation class SalePointServiceBean
	 */
	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;

	@Resource
	SessionContext session;
	
	/**
	 * Default constructor.
	 */
	public SalePointServiceBean() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalePoint> findAll() {
		Query query = em.createQuery("from SalePoint sp where sp.supprime = false order by sp.salepoint_nom ");
		return (List<SalePoint>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalePoint> findAll(String login) {
		Query query = em.createQuery("from SalePoint sp where sp.supervisor_id.login =:login "
				+ "or sp.network_id.manager_id.login =:login "
				+ "order by sp.salepoint_nom");
		query.setParameter("login",login);
		return (List<SalePoint>) query.getResultList();
	}

	@Override
	public List<SalePoint> listerSalePoint() {
		// TODO Auto-generated method stub
		List<SalePoint> salepoints = new ArrayList<SalePoint>();
		try {
			javax.persistence.Query query = em.createQuery("from SalePoint s where s.supprime = false and s.salepoint_nom != ' TOUTES'");
			salepoints = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return salepoints;
	}
	@Override
	public List<SalePoint> listerAllSalePoint() {
		// TODO Auto-generated method stub
		List<SalePoint> salepoints = new ArrayList<SalePoint>();
		try {
			javax.persistence.Query query = em.createQuery("from SalePoint s where s.salepoint_nom != ' TOUTES'");
			salepoints = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return salepoints;
	}
	@Override
	public List<SalePoint> listerSalePointTotal() {
		// TODO Auto-generated method stub
		List<SalePoint> salepoints = new ArrayList<SalePoint>();
		try {
			javax.persistence.Query query = em.createQuery("from SalePoint");
			salepoints = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return salepoints;
	}
	public void CreerSalePoint(SalePoint sp) {
		// TODO Auto-generated method stub
		em.persist(sp);
		

	}

	public SalePoint ModifierSalePoint(SalePoint sp) {
		// TODO Auto-generated method stub
		return em.merge(sp);

	}

	@Override
	public SalePoint SupprimerSalePoint(SalePoint sp) {
		// TODO Auto-generated method stub
		sp.setSupprime(true);
		return em.merge(sp);

	}

	@Override
	public SalePoint getSalePoint(Long code) {
		// TODO Auto-generated method stub
		SalePoint sp = em.find(SalePoint.class, code);
		if (sp != null)
			return sp;
		else
			return null;

	}
	@Override
	public SalePoint getSalePointByName(String nom) {
		SalePoint sp = (SalePoint) em.createNamedQuery(SalePoint.FIND_BY_NAME).setParameter("nom", nom).getSingleResult();
		if(sp==null) throw new RuntimeException("Aucune agence trouv�e avec ce nom");
		return sp;
	}
	@Override
	public SalePoint getSalePointByCode(String code) {
		SalePoint salePoint = null;
		try {
			Query query = em.createQuery("from SalePoint s where s.supprime = false and s.salepoint_code = :code");
			salePoint= (SalePoint) query.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return salePoint;

	}
	@Override
	public SalePoint getSalePointByNom(String code) {
		SalePoint salePoint = null;
		try {
			Query query = em.createQuery("from SalePoint s where s.supprime = false and s.salepoint_nom = :code");
			salePoint= (SalePoint) query.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return salePoint;

	}
	@Override
	public SalePoint getSalePointByNumSIM1(Long code) {
		SalePoint salePoint = null;
		try {
			Query query = em.createQuery("from SalePoint s where s.supprime = false and s.salepoint_numO_gerant = :code");
			salePoint= (SalePoint) query.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return salePoint;

	}
	@Override
	public SalePoint getSalePointByEtat(Boolean code) {
		SalePoint salePoint = null;
		try {
			Query query = em.createQuery("from SalePoint s where s.supprime = false and s.etat = :code");
			salePoint= (SalePoint) query.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return salePoint;

	}
	@Override
	public SalePoint getSalePointByNumSIM2(Long code) {
		SalePoint salePoint = null;
		try {
			Query query = em.createQuery("from SalePoint s where s.supprime = false and s.salepoint_numOM_gerant = :code");
			salePoint= (SalePoint) query.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return salePoint;

	}

	@Override
	public List<SalePoint> listerSalePointNetwork(String reseau) {
		return em.createQuery("select s from SalePoint s where s.network_id.network_nom=:reseau and s.supprime=false").setParameter("reseau", reseau).getResultList();
	}
	
	@Override
	public List<SalePoint> listerSalePoints(Network networks) {
		// TODO Auto-generated method stub
		List<SalePoint> salepoints = new ArrayList<SalePoint>();
		try {
			javax.persistence.Query query = em.createQuery("from SalePoint s where s.supprime = false and s.salepoint_nom != ' TOUTES' and s.network_id = :networks order by s.salepoint_nom");
			salepoints = (List<SalePoint>) query.setParameter("networks", networks).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return salepoints;
	}
	@Override
	public SalePoint getSalePointByCodeSupervisor(String login) {
		SalePoint salePoint = null;
		List<SalePoint> salePoints = null;
		try {
			Query query = em.createQuery("from SalePoint s where s.supprime = false and s.supervisor_id.login = :login");
			salePoints = query.setParameter("login", login).getResultList();
			if(!salePoints.isEmpty()){
				salePoint = salePoints.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return salePoint;
	}
	@Override
	public List<SalePoint> findSalePointByNetwork(Long idNetwork) {
		List<SalePoint> salePoints = new ArrayList<SalePoint>();
		try {
			Query query = em.createQuery("from SalePoint s where s.supprime = false and s.network_id.network_id=:idNetwork").setParameter("idNetwork", idNetwork);
			salePoints= query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salePoints;
	}
	@Override
	public List<SalePoint> findSalePointsBySupervisor(String login) {
		List<SalePoint> salePoints = null;
		try {
			Query query = em.createQuery("from SalePoint s where s.supprime = false and s.supervisor_id.login = :login");
			salePoints = query.setParameter("login", login).getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return salePoints;
	}
}


package sn.intouch.gu.api.ejb.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.entities.SalePoint;
import sn.intouch.gu.api.ejb.entities.Supervisor;


/**
 * Session Bean implementation class UtilisateurServiceBean
 */
@Stateless
public class AgentServiceBean implements AgentService {

	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;

	@Override
	public List<Agent> listerAgent() {
		List<Agent> agents = new ArrayList<Agent>();
		try {
			Query query = em.createQuery("from Agent a where a.supprime = false ");
			agents= query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agents;
	}

	@Override
	public void ajouterAgent(Agent agent) {
		em.persist(agent);
	}

	@Override
	public Agent supprimerAgent(Agent agent) {
		agent.setSupprime(true);
		return em.merge(agent);
	}

	@Override
	public Agent modifierAgent(Agent agent) {
		return em.merge(agent);
	}

	@Override
	public Agent find(Agent ag) {
		return em.find(Agent.class, ag.getId());
	}
	
	@Override
	public Agent findByLogin(String login) {
		Agent agent = null;
		try {
			Query query = em.createQuery("from Agent a where a.agtLogin = :login ");
			agent=  (Agent) query.setParameter("login", login).getSingleResult();
			//agents = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return agent;
	}
	@Override
	public List<Agent> listerAgents(String code) {
		// TODO Auto-generated method stub
		List<Agent> agents = new ArrayList<Agent>();
		try {
			Agent agent = null;
			Query query = em.createQuery("from Agent a where a.supprime = false and a.salepoint_id.salepoint_code = :code ");
			agents=  (List<Agent>) query.setParameter("code", code).getResultList();
			//agents = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return agents;
	}
	@Override
	public List<Agent> filtrerByNetworkAndSalepoint(String requete) {
		Query query = em.createQuery(requete);
		return (List<Agent>)query.getResultList();
	}

	@Override
	public Agent findAgentBySalepoint(SalePoint sp) {
		Agent agent=null;
		try {
			agent=(Agent) em.createQuery("from Agent a where a.salepoint_id.salepoint_id=:idSP").setParameter("idSP", sp.getSalepoint_id()).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agent;
	}
	public Agent getAgent(Long code) {
		Agent m = em.find(Agent.class, code);
		if(m != null ) return m ;
		else return null;
		
	}
	
	@Override
	public Agent getAgentByCredentials(String login, String password) {
		List<Agent> agents= new ArrayList<Agent>();
		try {
			Query query = em.createQuery("from Agent a where a.agtLogin = :login and a.agtPassword=:password and a.supprime = false ");
			query.setParameter("login", login);
			query.setParameter("password", password);
			agents = (List<Agent>) query.getResultList();
			// agents = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

		return (!agents.isEmpty() && agents!=null)?agents.get(0):null;
	}
	
	@Override
	public Supervisor getSupervisorByCredentials(String login, String password) {
		List<Supervisor> superviseurs= new ArrayList<Supervisor>();
		try {
			Query query = em.createQuery("from Supervisor s where s.gerLogin = :login and s.gerPassword=:password and s.supprime = false ");
			query.setParameter("login", login);
			query.setParameter("password", password);
			superviseurs = (List<Supervisor>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return (!superviseurs.isEmpty() && superviseurs!=null)?superviseurs.get(0):null;
	}

	@Override
	public List<Agent> getAgentByFonction(String codeAgence, String fonction) {
		
		List<Agent> liste=new ArrayList<Agent>();
		try {
			Query query = em.createQuery("from Agent a where a.supprime = false and a.salepoint.salepoint_code = :code and a.fonction.code=:fonction ");
			query.setParameter("fonction", fonction);
			query.setParameter("code", codeAgence);

			liste=query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return liste;
	}

	
	
}

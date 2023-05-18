package sn.intouch.gu.api.ejb.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.entities.Connexion;
import sn.intouch.gu.api.ejb.entities.Supervisor;
import sn.intouch.gu.api.ejb.entities.Utilisateur;


/**
 * Session Bean implementation class AuthentificationServiceBean
 */
@Stateless
public class AuthentificationServiceBean implements AuthentificationService {

	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;

	@Override
	public Utilisateur findUtilisateurByLoginPassword(String login, String password) {
		Utilisateur utilisateur = null;
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		try {
			String jpql = "from Utilisateur u where u.login = :login and u.password = :password and u.supprime = false and u.statutUser = 'ACTIF' ";
			Query query = em.createQuery(jpql);
			query.setParameter("login", login);
			query.setParameter("password", getEncodedPassword(password));
			utilisateurs = (List<Utilisateur>) query.getResultList();
			if (utilisateurs.size() != 0) {
				utilisateur = utilisateurs.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	public static String getEncodedPassword(String key) throws NoSuchAlgorithmException {
		byte[] uniqueKey = key.getBytes();
		byte[] hash = null;
		hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		StringBuffer hashString = new StringBuffer();
		for (int i = 0; i < hash.length; ++i) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			} else {
				hashString.append(hex.substring(hex.length() - 2));
			}
		}
		return hashString.toString();
	}

	@Override
	public void save(Utilisateur u) {
		em.merge(u);

	}

	

	

	@Override
	public void saveConnexion(Connexion conn) {
		em.merge(conn);
	}

	@Override
	public Connexion getConnexionByParams(String login, String pass, String mac, Date date) {
		Connexion conn = null;
		try {
			String jpql = "from Connexion c where c.login = :log and c.password = :pass and c.mac = :adMac and c.date = :dat";
			Query query = em.createQuery(jpql);
			query.setParameter("log", login);
			query.setParameter("pass", getEncodedPassword(pass));
			query.setParameter("adMac", mac);
			query.setParameter("dat", date, TemporalType.DATE);
			conn = (Connexion) query.getSingleResult();
		} catch (Exception e) {
		}
		return conn;

	}

	@Override
	public Agent getAgentByParams(String login, String password, String codeAgence) {
		Agent agent = null;
		try {
			String jpql = "from Agent a where a.agtLogin = :log and a.agtPassword = :pass and a.salepoint.salepoint_code=:codeAgence and a.agtStatut=1";
			Query query = em.createQuery(jpql);
			query.setParameter("log", login);
			query.setParameter("pass", password);
			query.setParameter("codeAgence", codeAgence);
			agent = (Agent) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Login ou mot de passe agent incorrecte " + login);
		}
		return agent;
	}

	@Override
	public Supervisor getGerantByParams(String login, String password, String codeAgence) {
		Supervisor gerant = null;
		try {
			String jpql = "from Supervisor g where g.gerLogin = :log and g.gerPassword = :pass and g.gerStatut=1";
			// String jpql = "from Supervisor g where g.gerLogin = :log and
			// g.gerPassword = :pass and g.salePoint.salepoint_code=:codeAgence
			// and g.gerStatut=1";
			Query query = em.createQuery(jpql);
			query.setParameter("log", login);
			query.setParameter("pass", password);
			// query.setParameter("codeAgence", codeAgence);
			gerant = (Supervisor) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Login ou mot de passe gerant incorrecte " + login);
		}
		return gerant;
	}

	@Override
	public Connexion getConnexionByAgent(Agent agent, String mac, String token) {
		Connexion conn = null;
		String jpql = "from Connexion c where c.login = :log and c.password = :pass and c.mac = :adMac and date(c.dateUpdate) = :dat"
				+ " and c.dateUpdate in (Select max(c.dateUpdate) from Connexion c where  c.login = :log and c.password = :pass and c.mac = :adMac ) ";
		Query query = null;
		if (token != null)
			jpql += " and c.token=:tok";
		try {
			query = em.createQuery(jpql);
			query.setParameter("log", agent.getAgtLogin());
			query.setParameter("pass", agent.getAgtPassword());
			query.setParameter("adMac", mac);
			query.setParameter("dat", new Date(), TemporalType.DATE);
			if (token != null)
				query.setParameter("tok", token);
			conn = (Connexion) query.getSingleResult();
		} catch (Exception e) {
//			 e.printStackTrace();
			System.out.println("Pas de connexion pour l'agent " + agent.getAgtLogin());
		}
		return conn;
	}

	@Override
	public Connexion getConnexionByGerant(Supervisor gerant, String mac, String token) {
		Connexion conn = null;
		String jpql = "from Connexion c where c.login = :log and c.password = :pass and c.mac = :adMac and date(c.date) = :dat";
		Query query = null;
		if (token != null)
			jpql += " and c.token=:tok";
		try {
			query = em.createQuery(jpql);
			query.setParameter("log", gerant.getGerLogin());
			query.setParameter("pass", gerant.getGerPassword());
			query.setParameter("adMac", mac);
			query.setParameter("dat", new Date(), TemporalType.DATE);
			if (token != null)
				query.setParameter("tok", token);
			conn = (Connexion) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Pas de connexion pour le gerant " + gerant.getGerLogin());
		}
		return conn;
	}

	@Override
	public Agent getAgentByLoginAndPassword(String login, String password) {
		Agent agent = null;
		try {
			String jpql = "from Agent a where a.agtLogin = :log and a.agtPassword = :pass and a.agtStatut=1";
			Query query = em.createQuery(jpql);
			query.setParameter("log", login);
			query.setParameter("pass", password);
			agent = (Agent) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Login ou mot de passe agent incorrecte " + login);
		}
		return agent;
	}

	@Override
	public Supervisor getGerantByAndPassword(String login, String password) {
		Supervisor gerant = null;
		try {
			String jpql = "from Supervisor g where g.gerLogin = :log and g.gerPassword = :pass and g.gerStatut=1";
			Query query = em.createQuery(jpql);
			query.setParameter("log", login);
			query.setParameter("pass", password);
			gerant = (Supervisor) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Login ou mot de passe gerant incorrecte " + login);
		}
		return gerant;
	}

	@Override
	public Agent getAgentByLogin(String login) {
		Agent agent = null;
		try {
			String jpql = "from Agent a where a.agtLogin =:log and  a.agtStatut=1";
			Query query = em.createQuery(jpql);
			query.setParameter("log", login);
			agent = (Agent) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Login ou mot de passe agent incorrecte " + login);
		}
		return agent;
	}

	@Override
	public Agent getAgentByMail(String mail) {
		Agent agent = null;
		List<Agent> liste=new ArrayList<Agent>();
		try {
			String jpql = "from Agent a where a.mail = :mail and  a.agtStatut=1";
			Query query = em.createQuery(jpql);
			query.setParameter("mail", mail);
			
			liste =  query.getResultList();
			
			if(liste!=null && !liste.isEmpty()){
				agent=liste.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agent;
	}

	@Override
	public Agent getAgentByTel(String tel) {
		
		Agent agent = null;
		List<Agent> liste=new ArrayList<Agent>();
		try {
			String jpql = "from Agent a where a.agtTelephone = :tel and  a.agtStatut=1";
			Query query = em.createQuery(jpql);
			query.setParameter("tel", tel);
			
			liste =  query.getResultList();
			
			if(liste!=null && !liste.isEmpty()){
				agent=liste.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agent;
		
	}
}

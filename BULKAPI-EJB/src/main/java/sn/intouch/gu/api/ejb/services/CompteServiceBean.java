package sn.intouch.gu.api.ejb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import sn.intouch.gu.api.ejb.entities.Compte;


/**
 * Session Bean implementation class UtilisateurServiceBean
 */
@Stateless
public class CompteServiceBean implements CompteService {

	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;

	@Override
	public List<Compte> listerCompte() {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte a where a.supprime = false ");
			Compte= query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public Compte getCompteByID(Long code) {
		Compte compte = null;
		try {
			Query query = em.createQuery("from Compte s where s.supprime = false and s.idCompte = :code");
			compte= (Compte) query.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return compte;

	}
	@Override
	public Compte getCompteByCodeAgence(String code) {
		Compte compte = null;
		try {
			Query query = em.createQuery("from Compte s where s.supprime = false and s.codeAgenceBeneficiaire = :code");
			compte= (Compte) query.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return compte;

	}
	@Override
	public List<Compte> getComptesByCodeAgence(String code) {
		List<Compte> listCompte = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte s where s.supprime = false and s.codeAgenceBeneficiaire = :code");
			listCompte= (List<Compte>) query.setParameter("code", code).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return listCompte;

	}
	@Override
	public Compte getCompteByCodeAgent(String code) {
		Compte compte = null;
		try {
			Query query = em.createQuery("from Compte s where s.supprime = false and s.codeAgentBeneficiaire = :code");
			compte= (Compte) query.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return compte;

	}
	@Override

	public String getProprietaireByID(Long code) {
		String proprietaire = null;
		try {
			Query query = em.createQuery("select s.proprietaire from Compte s where s.supprime = false and s.idCompte = :code");
			proprietaire= (String) query.setParameter("code", code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return proprietaire;

	}
	
	@Override
	public Compte getCompteByNumCompte(String code) {
		Compte compte = null;
		List<Compte> comptes=new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte s where s.supprime = false and s.numeroCompte = :code");			
			comptes= query.setParameter("code", code).getResultList();
			if(comptes.size() != 0){
				//if(!comptes.isEmpty()){
					compte=comptes.get(0);
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return compte;

	}
	
	@Override
	public Compte getCompteByNumCompte(String code, String networkGroupCode) {
		Compte compte = null;
		List<Compte> comptes=new ArrayList<Compte>();
		try {
			//Query query = em.createQuery("from Compte s where s.supprime = false and s.numeroCompte = :code");	
			String sql = "from Compte s where s.supprime = false and s.numeroCompte = :code";
			//Controle sur le groupe de reseau concerne
			if (!networkGroupCode.equals("GNL"))
			{
				sql += " and s.network_groupe_code = :networkGroupCode";
			}
			Query query = em.createQuery(sql);
			if (!networkGroupCode.equals("GNL"))
			{
				query.setParameter("networkGroupCode", networkGroupCode);
			}
			comptes= query.setParameter("code", code).getResultList();

			if(comptes.size() != 0){
				//if(!comptes.isEmpty()){
					compte=comptes.get(0);
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return compte;

	}
	
	@Override
	public Compte getCompteByNumCompteGN(String code, String networkGroupCode) {
		Compte compte = null;
		List<Compte> comptes=new ArrayList<Compte>();
		try {
			//Query query = em.createQuery("from Compte s where s.supprime = false and s.numeroCompte = :code");
			
			String sql = "";
			//Controle sur le groupe de reseau concerne
			if (!networkGroupCode.equals("GNL"))
			{
				sql = "from Compte a where a.supprime = false and a.numeroCompte = :code and a.codeAgenceBeneficiaire IN (SELECT s.salepoint_code FROM SalePoint s WHERE s.supprime = false and s.network_id.networkgroup_id.network_group_code = :networkGroupCode)";
			}
			else
			{
				sql = "from Compte s where s.supprime = false and s.numeroCompte = :code";
			}
									
			Query query = em.createQuery(sql);
									
			if (!networkGroupCode.equals("GNL"))
			{
				query.setParameter("networkGroupCode", networkGroupCode);
			}
			
			comptes= query.setParameter("code", code).getResultList();
			if(comptes.size() != 0){
				//if(!comptes.isEmpty()){
					compte=comptes.get(0);
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return compte;

	}
	
	//////////////////Liste comptes
	@Override
	public List<Compte> getComptesByNumCompte(String code) {
		List<Compte> comptes=new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte s where s.supprime = false and s.numeroCompte = :code");
			comptes= query.setParameter("code", code).getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return comptes;

	}
	@Override
	public List<Compte> listerCompteGU(String networkGroupCode) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
			//Query query = em.createQuery("from Compte a where a.codeGUBeneficiaire = 'CODEGU' and a.supprime = false ");
			
			String sql = "from Compte a where a.codeGUBeneficiaire = 'CODEGU' and a.supprime = false";
			//Controle sur le groupe de reseau concerne
			if (!networkGroupCode.equals("GNL"))
			{
				sql += " and a.network_groupe_code = :networkGroupCode";
			}
			
				sql += " order by a.idCompte DESC";
						
			Query query = em.createQuery(sql);
									
			if (!networkGroupCode.equals("GNL"))
			{
				query.setParameter("networkGroupCode", networkGroupCode);
			}
			
			Compte= query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteReseau(String networkGroupCode) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
//			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeNetworkBeneficiaire IN (SELECT n.network_code FROM Network n WHERE n.supprime = false)"
//					+ "and a.supprime = false ");
			
			String sql = "";
			//Controle sur le groupe de reseau concerne
			if (!networkGroupCode.equals("GNL"))
			{
				sql = "from Compte a where a.supprime = false and a.codeNetworkBeneficiaire IN (SELECT n.network_code FROM Network n WHERE n.supprime = false and n.networkgroup_id.network_group_code = :networkGroupCode )";
			}
			else
			{
				sql = "from Compte a where a.supprime = false and a.codeNetworkBeneficiaire IN (SELECT n.network_code FROM Network n WHERE n.supprime = false )";
			}
			
				sql += " order by a.idCompte DESC";
						
			Query query = em.createQuery(sql);
									
			if (!networkGroupCode.equals("GNL"))
			{
				query.setParameter("networkGroupCode", networkGroupCode);
			}
			
			Compte= query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteReseauByCodeNetwork(String code) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeNetworkBeneficiaire = :code"
					+ " and a.supprime = false ");
			//Compte= query.getResultList();
			Compte= (List<Compte>) query.setParameter("code", code).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteAgenceByCodeNetwork(String code) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeAgenceBeneficiaire IN (SELECT s.salepoint_code FROM SalePoint s WHERE s.supprime = false AND s.network_id.network_code = :code)"
					+ " and a.supprime = false ");
			//Compte= query.getResultList();
			Compte= (List<Compte>) query.setParameter("code", code).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteAgenceByCodeAgence(String code) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeAgenceBeneficiaire = :code)"
					+ " and a.supprime = false ");
			//Compte= query.getResultList();
			Compte= (List<Compte>) query.setParameter("code", code).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteAgentByCodeNetwork(String code) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeAgentBeneficiaire IN (SELECT a.login FROM Agent a WHERE a.agtStatut = 1 AND a.salepoint.network_id.network_code = :code)"
					+ " and a.supprime = false ");
			//Compte= query.getResultList();
			Compte= (List<Compte>) query.setParameter("code", code).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteAgentByCodeAgence(String code) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeAgentBeneficiaire IN (SELECT a.login FROM Agent a WHERE a.agtStatut = 1 AND a.salepoint.salepoint_code = :code)"
					+ " and a.supprime = false ");
			//Compte= query.getResultList();
			Compte= (List<Compte>) query.setParameter("code", code).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteAgence(String networkGroupCode) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
//			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeAgenceBeneficiaire IN (SELECT s.salepoint_code FROM SalePoint s WHERE s.supprime = false)"
//					+ " and a.supprime = false ");
			
			String sql = "";
			//Controle sur le groupe de reseau concerne
			if (!networkGroupCode.equals("GNL"))
			{
				sql = "from Compte a where a.supprime = false and a.codeAgenceBeneficiaire IN (SELECT s.salepoint_code FROM SalePoint s WHERE s.supprime = false and s.network_id.networkgroup_id.network_group_code = :networkGroupCode)";
			}
			else
			{
				sql = "from Compte a where a.supprime = false and a.codeAgenceBeneficiaire IN (SELECT s.salepoint_code FROM SalePoint s WHERE s.supprime = false)";
			}
			
				sql += " order by a.idCompte DESC";
						
			Query query = em.createQuery(sql);
									
			if (!networkGroupCode.equals("GNL"))
			{
				query.setParameter("networkGroupCode", networkGroupCode);
			}
			
			Compte= query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteAgent(String networkGroupCode) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
//			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeAgentBeneficiaire IN (SELECT a.login FROM Agent a WHERE a.supprime = false)"
//					+ " and a.supprime = false ");
			
			String sql = "";
			//Controle sur le groupe de reseau concerne
			if (!networkGroupCode.equals("GNL"))
			{
				sql = "from Compte a where a.supprime = false and a.codeAgentBeneficiaire IN (SELECT a.login FROM Agent a WHERE a.supprime = false and a.salepoint.network_id.networkgroup_id.network_group_code = :networkGroupCode )";
			}
			else
			{
				sql = "from Compte a where a.supprime = false and a.codeAgentBeneficiaire IN (SELECT a.login FROM Agent a WHERE a.supprime = false )";
			}
			
				sql += " order by a.idCompte DESC";
						
			Query query = em.createQuery(sql);
									
			if (!networkGroupCode.equals("GNL"))
			{
				query.setParameter("networkGroupCode", networkGroupCode);
			}
			
			Compte= query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteAgentItinerant() {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte c where c.supprime = false and c.codeAgentBeneficiaire IN (SELECT a.login FROM Agent a WHERE a.supprime = false and a.type = 'ITINERANT')");
			Compte= query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> listerCompteBanque(String networkGroupCode) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {

			String sql = "from Compte a where a.supprime = false and a.codeBanqueBeneficiaire != null ";
			//Controle sur le groupe de reseau concerne
			if (!networkGroupCode.equals("GNL"))
			{
				sql += " and a.network_groupe_code = :networkGroupCode";
			}
			
			Query query = em.createQuery(sql);
			
			if (!networkGroupCode.equals("GNL"))
			{
				query.setParameter("networkGroupCode", networkGroupCode);
			}
			
			Compte= query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public void ajouterCompte(Compte Category) {
		em.persist(Category);
	}

	@Override
	public Compte supprimerCompte(Compte Category) {
		Category.setSupprime(true);
		return em.merge(Category);
	}

	@Override
	public Compte modifierCompte(Compte Category) {
		return em.merge(Category);
	}

	@Override
	public Compte find(Compte ag) {
		return em.find(Compte.class, ag.getIdCompte());
	}
	public Compte getCategoryS(Long code) {
		Compte m = em.find(Compte.class, code);
		if(m != null ) return m ;
		else return null;
		
	}

	@Override
	public Compte getCompte(Long code) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Compte> listerCompteAgentByCodeAgent(String code) {
		List<Compte> Compte = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeAgentBeneficiaire = :code"
					+ " and a.supprime = false ");
			//Compte= query.getResultList();
			Compte= (List<Compte>) query.setParameter("code", code).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Compte;
	}
	@Override
	public List<Compte> filtrerCompte(String codeAgent, String codeAgence, String codeReseau) {
		String sql = " select c from Compte c where c.supprime=false ";
		if(codeAgent!=null){
			sql+=" and c.codeAgentBeneficiaire = :codeAgent";
		}
		if(codeAgence!=null){
			sql+=" and c.codeAgenceBeneficiaire=:codeAgence";
		}
		if(codeReseau!=null){
			sql+=" and c.codeNetworkBeneficiaire=:codeReseau";
		}
		Query query = em.createQuery(sql);
		
		if(codeAgent!=null){
			query.setParameter("codeAgent",codeAgent);

		}
		if(codeAgence!=null){
			query.setParameter("codeAgence",codeAgence);

		}
		if(codeReseau!=null){
			query.setParameter("codeReseau",codeReseau);

		}
		
		return (List<Compte>) query.getResultList();
	}
	
	@Override
	public HashMap<Long, Object[]> getCompteNow() {
		List<Compte> Compte = new ArrayList<Compte>();
		
		HashMap<Long, Object[]> params = new HashMap<Long, Object[]>();
		try {
			Query query = em.createQuery("from Compte a where a.supprime = false and a.codeAgenceBeneficiaire != null");
			Compte= query.getResultList();
			
			for (int i = 0; i < Compte.size(); i++)
			{
				Object[] obj = new Object[2];
				obj[0] = Compte.get(i).getSoldeCompte();
				obj[1] = Compte.get(i).getNumeroCompte();
				
				params.put(Compte.get(i).getIdCompte(), obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	@Override
	public Compte getCompteByCodeAgentAndType(String code, Integer type) {
		Compte compte = null;
		try {
			Query query = em.createQuery("SELECT c FROM Compte c WHERE c.codeAgenceBeneficiaire = :codeAgenceBeneficiaire AND c.typeCompte_idtypeCompte.codeTypeCompte = :codeTypeCompte AND c.supprime = false");
			List<Compte> comptes = (List<Compte>)query.setParameter("codeAgenceBeneficiaire", code).setParameter("codeTypeCompte", type).getResultList();
			if (comptes != null && !comptes.isEmpty()) {
				compte = comptes.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return compte;
	}
	
	@Override
	public List<Compte> listerCompteAgenceForFraisConnexion(String numCompteBeneficiaire, String networkGroupCode, Date date) {
		List<Compte> compte = new ArrayList<Compte>();
		try {
			String sql = "from Compte a where a.supprime = false and a.codeAgenceBeneficiaire IN (SELECT s.salepoint_code FROM SalePoint s WHERE s.supprime = false) and a.numeroCompte != :numCompteBeneficiaire and (a.typeCompte_idtypeCompte.codeTypeCompte = 1 or a.typeCompte_idtypeCompte.codeTypeCompte = 3)";
			//Controle sur le groupe de reseau concerne
			if (!networkGroupCode.equals("GNL"))
			{
				sql += " and a.network_groupe_code = :networkGroupCode";
			}
			
			if (date != null)
			{
				sql += " and DATE(a.dateCreation) <= DATE(:datee) OR MONTH(a.dateCreation) = MONTH(:datee) ";
			}

			Query query = em.createQuery(sql);
			
			if (!networkGroupCode.equals("GNL"))
			{
				query.setParameter("networkGroupCode", networkGroupCode);
			}

			query.setParameter("numCompteBeneficiaire", numCompteBeneficiaire);
			if (date != null)
			{
				query.setParameter("datee", date, TemporalType.DATE);
			}
			
			compte= query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compte;
	}
	@Override
	public List<Compte> listerCompteAgentForFraisConnexion(String networkGroupCode, Date date) {

		List<Compte> compte = new ArrayList<Compte>();
		try {
			String sql = "from Compte a where a.supprime = false and a.codeAgentBeneficiaire IN (SELECT a.login FROM Agent a WHERE a.agtStatut = 1 and a.type = 'ITINERANT') and (a.typeCompte_idtypeCompte.codeTypeCompte = 1 or a.typeCompte_idtypeCompte.codeTypeCompte = 3)";
			//Controle sur le groupe de reseau concerne
			if (!networkGroupCode.equals("GNL"))
			{
				sql += " and a.network_groupe_code = :networkGroupCode";
			}
			
			if (date != null)
			{
				sql += " and (DATE(a.dateCreation) <= DATE(:datee) OR MONTH(a.dateCreation) = MONTH(:datee)) ";
			}
			
			Query query = em.createQuery(sql);
			
			if (!networkGroupCode.equals("GNL"))
			{
				query.setParameter("networkGroupCode", networkGroupCode);
			}
			
			if (date != null)
			{
				query.setParameter("datee", date, TemporalType.DATE);
			}
			
			compte= query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compte;
	}
	
	@Override
	public List<Compte>  getCompteByCodeAgenceAndType(String code, Integer type) {
		List<Compte> comptes = new ArrayList<Compte>();
		try {
			Query query = em.createQuery("SELECT c FROM Compte c WHERE c.codeAgenceBeneficiaire = :codeAgenceBeneficiaire AND c.typeCompte_idtypeCompte.codeTypeCompte = :codeTypeCompte AND c.supprime = false");
			comptes = (List<Compte>)query.setParameter("codeAgenceBeneficiaire", code).setParameter("codeTypeCompte", type).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
			return comptes;
	}
}

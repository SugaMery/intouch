package sn.intouch.gu.api.ejb.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.intouch.gu.api.ejb.entities.FraisServiceAgence;
@Stateless
public class FraisServiceAgenceServiceBean  implements FraisServiceAgenceService{
	
	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;

	@Override
	public void createFrais(FraisServiceAgence frais) {
		try {
			em.persist(frais);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public FraisServiceAgence modifierFrais(FraisServiceAgence frais) {
		return em.merge(frais);
	}

	@Override
	public FraisServiceAgence filterfrais(String agenceCode, String serviceCode) {
		FraisServiceAgence frais=null;
		List<FraisServiceAgence> liste=new ArrayList<FraisServiceAgence>();
		try {
			String sql="from FraisServiceAgence f where f.supprime=false";
			
			if(agenceCode!=null){
				sql+=" and f.agence.salepoint_code =:agenceCode ";
					
			}
			
			if(serviceCode!=null){
				sql+=" and f.service.service_code =:serviceCode";
			}
			Query query = em.createQuery(sql);
			
			if(agenceCode!=null){
				query.setParameter("agenceCode", agenceCode);
			}
			
			if(serviceCode!=null){
				query.setParameter("serviceCode", serviceCode);
			}
			liste=query.getResultList();
			
			if(liste!=null && !liste.isEmpty()){
				frais=liste.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return frais;
	}
@Override
	public List<FraisServiceAgence> fraisServiceByAgence(String agenceCode) {
		
		List<FraisServiceAgence> liste=new ArrayList<FraisServiceAgence>();
		try {
			String sql="from FraisServiceAgence f where f.supprime=false";
			
			if(agenceCode!=null){
				sql+=" and f.agence.salepoint_code =:agenceCode ";					
			}		
			
			Query query = em.createQuery(sql);			
			if(agenceCode!=null){
				query.setParameter("agenceCode", agenceCode);
			}
						
			liste=query.getResultList();
			
			if(liste!=null && !liste.isEmpty()){
				return liste;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return liste;
	}

	@Override
	public List<FraisServiceAgence> lister(String codeAgence,String codeNetwork,String groupereseau) {
		List<FraisServiceAgence> liste=new ArrayList<FraisServiceAgence>();
		try {
			String sql="from FraisServiceAgence f where f.supprime =false ";
			if(codeAgence!=null){
				sql+=" and f.agence.salepoint_code =:codeAgence";
			}
			
			 if(codeNetwork!=null){
				sql+=" and f.agence.network_id.network_code =:codeNetwork";
			 }
			 
			 if(groupereseau!=null){
				 sql+=" and f.agence.network_id.networkgroup_id.network_group_code =:groupereseau";
			 }
			 Query query = em.createQuery(sql);
			 
			 if(codeAgence!=null){
				 query.setParameter("codeAgence", codeAgence);
			 }
			 
			 if(codeNetwork!=null){
				 query.setParameter("codeNetwork", codeNetwork);
			 }
			 
			 if(groupereseau!=null){
				 query.setParameter("groupereseau", groupereseau);
			 }
			 liste=query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}



}

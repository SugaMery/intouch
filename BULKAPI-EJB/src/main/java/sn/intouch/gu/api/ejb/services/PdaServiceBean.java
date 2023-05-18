package sn.intouch.gu.api.ejb.services;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.intouch.gu.api.ejb.entities.PDA;


@Stateless
public class PdaServiceBean implements PdaService{

	
	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDA> findAllPDA() {
		Query query = em.createQuery("from PDA x where x.statut = '1' ");
		return (List<PDA>)query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDA> filtrerByEtatAndStation(String requete) {
		Query query = em.createQuery(requete);
		return (List<PDA>)query.getResultList();
	}
	
	
	@Override
	public PDA findPDAById(int idPDA) {
		PDA agent = null;
		try{
			Query query = em.createQuery("from PDA x where x.statut = '1' and x.pdaId =:idPDA ");
			query.setParameter("idPDA", idPDA);
			agent = (PDA)query.getSingleResult();
		}catch(NoResultException nre){/*MyLOG.log(nre);*/}
		return agent;
	}

	@Override
	public PDA save(PDA pda) throws Exception {
		return em.merge(pda);
	}

	@Override
	public PDA supprimer(PDA x) {
		x.setStatut(0);
		return em.merge(x);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PDA> lister(int debut, int nbParPage) {
		List<PDA> agents = new ArrayList<PDA>();
		try {
			Query query = em.createQuery("from PDA x where x.statut = '1' ");
			query.setFirstResult(debut);
			query.setMaxResults(nbParPage);
			agents = query.getResultList();
		} catch (Exception e) {
			//MyLOG.log(e);
		}
		return agents;
	}
	
	@Override
	public Long compter() {
		try {
			Query query = em.createQuery("select count(x) from PDA x where x.statut = '1' ");
			return (Long) query.getSingleResult();
		} catch (Exception e) {
			//MyLOG.log(e);
		}
		return 0l;
	}
	
	@Override
	public PDA findPDAByCode(String codePDA) {
		PDA agent = null;
		try{
		Query query = em.createQuery("from PDA x where x.statut = '1' and x.codePda =:codePda ");
		query.setParameter("codePda", codePDA);
		agent = (PDA)query.getSingleResult();
		}catch(NoResultException nre){
			//MyLOG.log(nre);
			}
		return agent;
	}
	
	@Override
	public PDA findPDAByIdSIM(String idSim) {
		PDA agent = null;
		try{
		Query query = em.createQuery("from PDA x where x.statut = '1' and x.numero =:idsim ");
		query.setParameter("idsim", idSim);
		agent = (PDA)query.getSingleResult();
		}catch(NoResultException nre){
			//MyLOG.log(nre);
			}
		return agent;
	}

	
/*	@Override
	public Station findStationOfPDA(int idPDA) {
		Station station = null;
		try{
		Query query = em.createQuery("select x.station from PDA x where x.statut = '1' and x.pdaId =:idPDA  ");
		query.setParameter("idPDA", idPDA);
		station = (Station)query.getSingleResult();
		}catch(NoResultException nre){MyLOG.log(nre);}
		return station;
	}
*/
	@Override
	public List<PDA> findPDAByStation(String staCode) {
		List<PDA> agents = new ArrayList<PDA>();
		try {
			Query query = em.createQuery("from PDA x where x.statut = '1' and x.codeStation = :codeStation ");
			query.setParameter("codeStation", staCode);
			agents = query.getResultList();
		} catch (Exception e) {
			//MyLOG.log(e);
		}
		return agents;
	}


	
}
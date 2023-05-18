package sn.intouch.gu.api.ejb.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.intouch.gu.api.ejb.entities.Parametre;

@Stateless
public class ParameterServiceBean implements ParameterService {

	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;

	@Override
	public int nextValue(String code) {

		Query query = em.createQuery("SELECT p FROM Parametre p WHERE p.prmCode = :code ");
		query.setParameter("code", code);
		Parametre p = (Parametre) query.getSingleResult();

		int i = p.getPrmValue();

		p.setPrmValue(i + 1);
		em.merge(p);

		return i;

	}

	@Override
	public int getValue(String code) {
		Query query = em.createQuery("SELECT p FROM Parametre p WHERE p.prmCode = :code ");
		query.setParameter("code", code);
		Parametre p = (Parametre) query.getSingleResult();

		int i = p.getPrmValue();

		return i;
	}

	@Override
	public Parametre getParameterByCode(String code) {
		Parametre parametre = null;
		try {
			Query query = em.createQuery("SELECT p FROM Parametre p WHERE p.prmCode = :code and p.prmStatut = 1 ");
			query.setParameter("code", code);
			parametre = (Parametre) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Pad de parametre de code " + code);
			e.printStackTrace();
		}
		return parametre;
	}

	@Override
	public int setValue(String code, String value) {

		Query query = em.createQuery("SELECT p FROM Parametre p WHERE p.prmCode = :code ");
		query.setParameter("code", code);
		Parametre p = (Parametre) query.getSingleResult();

		p.setPrmStringValue(value);
		em.merge(p);

		return 1;

	}
}

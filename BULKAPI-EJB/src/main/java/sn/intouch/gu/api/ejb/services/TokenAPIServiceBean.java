package sn.intouch.gu.api.ejb.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.intouch.gu.api.ejb.entities.TokenAPI;
import sn.intouch.gu.api.ejb.entities.TransactionTemp;

@Stateless
public class TokenAPIServiceBean implements TokenAPIService {

	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;

	@Override
	public TokenAPI getTokenByPartenaire(String partner_id) {
		List<TokenAPI> tokensAPI = new ArrayList<TokenAPI>();
		try {
			Query query = em.createQuery("from TokenAPI t where t.partner_id=:partnerId and t.supprime = false ");
			query.setParameter("partnerId", partner_id);
			tokensAPI = (List<TokenAPI>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

		return (!tokensAPI.isEmpty() && tokensAPI != null) ? tokensAPI.get(0) : null;
	}

	@Override
	public List<TokenAPI> listTokenAPI() {
		List<TokenAPI> tokensAPI = new ArrayList<TokenAPI>();
		try {
			Query query = em.createQuery("from TokenAPI t where t.supprime = 0 ");
			tokensAPI = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokensAPI;
	}

	@Override
	public void ajouterTransactionDistTemp(TransactionTemp temp) {
		em.persist(temp);
	}

}

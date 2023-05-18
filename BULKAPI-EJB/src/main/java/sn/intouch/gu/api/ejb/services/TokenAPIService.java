package sn.intouch.gu.api.ejb.services;

import java.util.List;

import javax.ejb.Remote;

import sn.intouch.gu.api.ejb.entities.TokenAPI;
import sn.intouch.gu.api.ejb.entities.TransactionTemp;

@Remote
public interface TokenAPIService {
	public TokenAPI getTokenByPartenaire(String partner_id);
	public List<TokenAPI> listTokenAPI();
	public void ajouterTransactionDistTemp(TransactionTemp temp);
}

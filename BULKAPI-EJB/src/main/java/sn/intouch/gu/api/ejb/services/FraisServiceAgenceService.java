package sn.intouch.gu.api.ejb.services;

import java.util.List;

import javax.ejb.Remote;

import sn.intouch.gu.api.ejb.entities.FraisServiceAgence;
@Remote
public interface FraisServiceAgenceService {
	
	public void createFrais(FraisServiceAgence  frais);
	public FraisServiceAgence modifierFrais(FraisServiceAgence frais);
	public FraisServiceAgence filterfrais(String agenceCode,String serviceCode);
	public List<FraisServiceAgence> lister(String codeAgence,String codeNetwork,String groupereseau);
public List<FraisServiceAgence> fraisServiceByAgence(String agenceCode);

}

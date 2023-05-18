package sn.intouch.gu.api.ejb.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import sn.intouch.gu.api.ejb.entities.Compte;



@Remote
public interface CompteService {
	
	public List<Compte> listerCompte();
	public void ajouterCompte(Compte action);
	public Compte supprimerCompte(Compte action);
	public Compte modifierCompte(Compte action);
	public Compte find(Compte u);
	public Compte getCompte (Long code);
	public List<Compte> listerCompteReseau(String networkGroupCode);
	public List<Compte> listerCompteAgence(String networkGroupCode);
	public List<Compte> listerCompteAgent(String networkGroupCode);
	public List<Compte> listerCompteAgentItinerant();
	public Compte getCompteByID(Long code);
	public Compte getCompteByNumCompte(String code);

	public String getProprietaireByID(Long code);
	
	public List<Compte> listerCompteReseauByCodeNetwork(String code);
	public List<Compte> listerCompteAgenceByCodeNetwork(String code);
	public List<Compte> listerCompteAgentByCodeNetwork(String code);
	public List<Compte> listerCompteAgenceByCodeAgence(String code);
	public List<Compte> listerCompteAgentByCodeAgence(String code);
	public List<Compte> listerCompteAgentByCodeAgent(String code);
	public List<Compte> getComptesByNumCompte(String code);
	public List<Compte> filtrerCompte(String codeAgent,String codeAgence,String codeReseau);
	public HashMap<Long, Object[]> getCompteNow();
	public Compte getCompteByCodeAgence(String code);
	public Compte getCompteByCodeAgent(String code);
	public Compte getCompteByCodeAgentAndType(String code, Integer type);
	public List<Compte> getComptesByCodeAgence(String code);
	public Compte getCompteByNumCompteGN(String code, String networkGroupCode);
	public List<Compte> listerCompteGU(String networkGroupCode);
	public List<Compte> listerCompteBanque(String networkGroupCode);
	public Compte getCompteByNumCompte(String code, String networkGroupCode);
	public List<Compte> listerCompteAgenceForFraisConnexion(String numCompteBeneficiaire, String networkGroupCode, Date date);
	public List<Compte> listerCompteAgentForFraisConnexion(String networkGroupCode, Date date);
	public List<Compte>  getCompteByCodeAgenceAndType(String code, Integer type);
}

package sn.intouch.gu.api.ejb.services;

import java.util.List;

import javax.ejb.Remote;

import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.entities.SalePoint;
import sn.intouch.gu.api.ejb.entities.Supervisor;


@Remote
public interface AgentService {
	
	public List<Agent> listerAgent();
	public void ajouterAgent(Agent action);
	public Agent supprimerAgent(Agent action);
	public Agent modifierAgent(Agent action);
	public Agent find(Agent u);
	public Agent findByLogin(String login);
	public List<Agent> listerAgents(String code);
	public List<Agent> filtrerByNetworkAndSalepoint(String requete);
	public Agent findAgentBySalepoint(SalePoint sp);
	public Agent getAgent(Long code);
	public Agent getAgentByCredentials(String login, String password);
	public Supervisor getSupervisorByCredentials(String login, String password);
	public List<Agent> getAgentByFonction(String codeAgence,String fonction);

	
}

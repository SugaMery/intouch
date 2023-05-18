package sn.intouch.gu.api.ejb.services;

import java.util.Date;
import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.entities.Connexion;
import sn.intouch.gu.api.ejb.entities.Supervisor;
import sn.intouch.gu.api.ejb.entities.Utilisateur;

public interface AuthentificationService {

	public Utilisateur findUtilisateurByLoginPassword(String login, String password);

	public void save(Utilisateur u);



	public void saveConnexion(Connexion conn);

	public Connexion getConnexionByParams(String login, String pass, String mac, Date date);

	public Agent getAgentByParams(String login, String password, String codeAgence);

	public Supervisor getGerantByParams(String login, String password, String codeAgence);

	public Connexion getConnexionByAgent(Agent agent, String mac, String token);

	public Connexion getConnexionByGerant(Supervisor gerant, String mac, String token);

	public Agent getAgentByLoginAndPassword(String login, String password);
	
	public Agent getAgentByLogin(String login);

	public Supervisor getGerantByAndPassword(String login, String password);
	
	public Agent getAgentByMail(String mail);
	
	public Agent getAgentByTel(String tel);

}

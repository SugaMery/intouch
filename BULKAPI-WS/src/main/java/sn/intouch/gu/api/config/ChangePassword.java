package sn.intouch.gu.api.config;

import org.apache.log4j.Logger;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.services.AgentService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.entities.UserExchange;


public class ChangePassword  extends  ServerResource{
	
	private static final Logger logger = Logger.getLogger(ChangePassword.class);

	AgentService agentService = (AgentService) JNDIUtils.lookUpEJB(EJBRegistry.AgentServiceBean);
	UserExchange userExchange = new UserExchange();

	@Post("json")
	public String changePassword(String entity) {
		Gson gson = new Gson();
		logger.info("==============>" + entity);
		try {
			if (entity != null) {
				userExchange = gson.fromJson(entity, UserExchange.class);
				if (userExchange != null) {
					if (userExchange.getUtilisateur() != null) {
						Agent agent = agentService.findByLogin(userExchange.getUtilisateur().getLogin());
						if (agent != null) {
							agent.setAgtPassword(userExchange.getUtilisateur().getPassword());
							agent.setPassword(userExchange.getUtilisateur().getPassword());
							agent.setAgtHasConnect(1);
							agentService.modifierAgent(agent);
							userExchange.getUtilisateur().setUserHasConnected(1);
							userExchange.setErrorCode("200");
							userExchange.setErrorMessage("Agent modifie avec succes");
							String resultat = gson.toJson(userExchange);
							return resultat;
						} else {
							userExchange.setErrorCode("404");
							userExchange.setErrorMessage("Le login de l'agent est incorrecte");
							String resultat = gson.toJson(userExchange);
							return resultat;
						}
						
					} else {
						userExchange.setErrorCode("403");
						userExchange.setErrorMessage("Veillez envoyer les information de l'utilisateur");
						String resultat = gson.toJson(userExchange);
						return resultat;
					}
				} else {
					userExchange = new UserExchange();
					userExchange.setErrorCode("403");
					userExchange.setErrorMessage("Veillez envoyer les information de l'utilisateur");
					String resultat = gson.toJson(userExchange);
					return resultat;
				}

			} else {
				userExchange.setErrorCode("403");
				userExchange.setErrorMessage("Le modele d'echange est nul");
				String resultat = gson.toJson(userExchange);
				return resultat;
			}

		} catch (Exception e) {
			e.printStackTrace();
			userExchange.setErrorCode("500");
			userExchange.setErrorMessage(e.getMessage());
			String resultat = gson.toJson(userExchange);
			return resultat;
		}

	}

}

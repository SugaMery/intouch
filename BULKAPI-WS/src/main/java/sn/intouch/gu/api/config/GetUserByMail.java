package sn.intouch.gu.api.config;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.services.AuthentificationService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.entities.UserExchange;
import sn.intouch.gu.api.entities.Utilisateur;

public class GetUserByMail  extends ServerResource{
	AuthentificationService authService = (AuthentificationService) JNDIUtils
			.lookUpEJB(EJBRegistry.AuthentificationServiceBean);

	
	@Get("json")
	public String getUser(){
		String mail = getQuery().getValues("mail");
		UserExchange user=new UserExchange();
		Utilisateur utilisateur=new Utilisateur();
		Gson gson = new Gson();
		
		try {
			Agent a =authService.getAgentByMail(mail);
			
			if(a!=null){
				utilisateur.setLogin(a.getLogin());
				utilisateur.setPrenom(a.getPrenom());
				utilisateur.setNom(a.getNom());
				user.setSalePointCode(a.getSalepoint().getSalepoint_code());
				user.setErrorCode("200");
				user.setErrorMessage("SUCCES");
				user.setUtilisateur(utilisateur);
			}else{
				user.setErrorCode("404");
				user.setErrorMessage("INTROUVABLE");
			}
			
		} catch (Exception e) {
			user.setErrorCode("500");
			user.setErrorMessage("Une erreur interne est survenue.Veuillez réessayer plus tard");
		}
		
		String resultat = gson.toJson(user);
		return resultat;
		
	}

}

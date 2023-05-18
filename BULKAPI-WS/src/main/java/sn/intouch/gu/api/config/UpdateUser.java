package sn.intouch.gu.api.config;

import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import com.google.gson.Gson;
import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.services.AuthentificationService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.entities.UserExchange;

public class UpdateUser   extends  ServerResource{
	private static Logger logger = Logger.getLogger(UpdateUser.class);
	private static final String EXCEPTION_MESS = "Une exception s'est produite";
	AuthentificationService authService = (AuthentificationService) JNDIUtils
			.lookUpEJB(EJBRegistry.AuthentificationServiceBean);



	
	@Put("json")
	public String updateUser(String jsonExchange) throws NoSuchAlgorithmException {
		Gson gson = new Gson();
		UserExchange userExchange = new UserExchange();
		logger.info("---------- STARTED AUTH EXCANGE REQUEST ------------");
		logger.info(jsonExchange);
		logger.info("----------- END AUTH EXCANGE REQUEST --------------");
		
			try {
				userExchange = gson.fromJson(jsonExchange, UserExchange.class);
			} catch (Exception e) {
				logger.error("", e);
				userExchange.setErrorCode("500");
				userExchange.setErrorMessage(EXCEPTION_MESS);
				return gson.toJson(userExchange);

			}
			Agent a = null;
			a=authService.getAgentByLogin(userExchange.getLoginAgent());
			if(a!=null){
				if(userExchange.getUtilisateur()!=null){
					 if(userExchange.getUtilisateur().getPrenom()!=null){
						 a.setAgtPrenom(userExchange.getUtilisateur().getPrenom());
						 a.setPrenom(userExchange.getUtilisateur().getPrenom());
					 }
					 
					 if(userExchange.getUtilisateur().getNom()!=null){
						 a.setAgtNom(userExchange.getUtilisateur().getNom());
						 a.setNom(userExchange.getUtilisateur().getNom());
					 }
					 
					 if(userExchange.getUtilisateur().getPoste()!=null){
						 a.setPoste(userExchange.getUtilisateur().getPoste());
					 }
					 
					 if(userExchange.getUtilisateur().getTelephone()!=null){
						 a.setAgtTelephone(userExchange.getUtilisateur().getTelephone());
						 a.setNumero(Long.valueOf(userExchange.getUtilisateur().getTelephone()));
						
					 }  
					 if(userExchange.getUtilisateur().getLangue()!=null){
						 a.setLangue(userExchange.getUtilisateur().getLangue());
					 }
					 
					 
					 if(userExchange.getUtilisateur().getMail()!=null){
						 
						 try {
							 Agent agt=authService.getAgentByMail(userExchange.getUtilisateur().getMail());
							 if(agt!=null  && !agt.getAgtLogin().equalsIgnoreCase(userExchange.getLoginAgent())){
								    
								     userExchange.setErrorCode("402");
									 userExchange.setErrorMessage("MAIL ALREADY EXIST");
									 return gson.toJson(userExchange);
									 
							 }else{
								 a.setMail(userExchange.getUtilisateur().getMail());
							 }
						} catch (Exception e) {
							logger.error("", e);
						}
						
					 }
					 
//					 if(userExchange.getUtilisateur().getTelephone()!=null){
//						 
//						 try {
//							 Agent agt=authService.getAgentByTel(userExchange.getUtilisateur().getTelephone());
//							 if(agt!=null  && !agt.getAgtLogin().equalsIgnoreCase(userExchange.getLoginAgent())){
//								 
//								 userExchange.setErrorCode("403");
//								 userExchange.setErrorMessage("PHONE ALREADY EXIST");
//								 return gson.toJson(userExchange);
//							 }else{
//								 a.setAgtTelephone(userExchange.getUtilisateur().getTelephone());
//								 a.setNumero(Long.valueOf(userExchange.getUtilisateur().getTelephone()));
//							 }
//						} catch (Exception e) {
//							logger.error("", e);
//						}
//                        
//					 }
					 
					 
					 try {
						authService.save(a);
						userExchange.setErrorCode("200");
						userExchange.setErrorMessage("SUCCESS");
					} catch (Exception e) {
						logger.error("", e);
						userExchange.setErrorCode("500");
						userExchange.setErrorMessage("INTERNAL SERVER ERROR");
					   return gson.toJson(userExchange);
					}
				}else{
					userExchange.setErrorCode("400");
					userExchange.setErrorMessage("BAD REQUEST");
					 return gson.toJson(userExchange);
				}
			}else{
				userExchange.setErrorCode("404");
				userExchange.setErrorMessage("AGENT NOT FOUND");
				 return gson.toJson(userExchange);
				
			}
	    return gson.toJson(userExchange);
	}

}

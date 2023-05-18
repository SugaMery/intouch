package sn.intouch.gu.api.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.entities.FraisServiceAgence;
import sn.intouch.gu.api.ejb.services.AuthentificationService;
import sn.intouch.gu.api.ejb.services.FraisServiceAgenceService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.entities.FraisExchange;


public class GetFraisByAgence extends ServerResource {

	private static Logger log= Logger.getLogger(GetFraisByAgence.class);
	FraisServiceAgenceService fraisService = (FraisServiceAgenceService) JNDIUtils
			.lookUpEJB(EJBRegistry.FraisServiceAgenceServiceBean);
	
	
	@Get("json")
	public String getUser(){
		String codeAgence = getQuery().getValues("codeAgence");
		FraisExchange fraisExchange=new FraisExchange();
	    List<FraisServiceAgence> fraisByAgenceList=new ArrayList<FraisServiceAgence>();
		 HashMap<String, String> fraisByServiceMap = new HashMap<String, String>();
		 
		 //List<Item> list;
	
		 
		Gson gson = new Gson(); 
		
		try {
			String frais;String serviceCode;
			
			fraisByAgenceList =fraisService.fraisServiceByAgence(codeAgence);		
			 for (FraisServiceAgence fraisAgence : fraisByAgenceList) 
			 {serviceCode=fraisAgence.getService().getService_code();
			  frais=fraisAgence.getMontant().toString();
			  if(fraisAgence.getTypeFrais().equalsIgnoreCase("Pourcentage"))
			  {  frais=frais.concat("%");}	  
			 
			 fraisByServiceMap.put(serviceCode,frais);}
			
			if(fraisByServiceMap !=null){
				
				fraisExchange.setFraisByService(fraisByServiceMap);
				fraisExchange.setErrorCode("200");
				fraisExchange.setErrorMessage("SUCCES");
				
			}else{
				fraisExchange.setErrorCode("404");
				fraisExchange.setErrorMessage("INTROUVABLE");
			}
			
		} catch (Exception e) {
			fraisExchange.setErrorCode("500");
			fraisExchange.setErrorMessage("Une erreur interne est survenue.Veuillez réessayer plus tard");
		}
		
		String resultat = gson.toJson(fraisExchange);
		return resultat;
		
	}
	
}

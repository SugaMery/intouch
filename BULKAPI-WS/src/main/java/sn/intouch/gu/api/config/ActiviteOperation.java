package sn.intouch.gu.api.config;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;


import sn.intouch.gu.api.ejb.models.ActiviteModel;
import sn.intouch.gu.api.ejb.services.OperationService;
import sn.intouch.gu.api.ejb.services.PrimitiveService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.ejb.utils.Utils;
import sn.intouch.gu.api.entities.OperationModel;

public class ActiviteOperation   extends ServerResource{
	
	private static OperationService operationService=(OperationService) JNDIUtils
			.lookUpEJB(EJBRegistry.OperationServiceBean);
	private static Logger log = Logger.getLogger(ActiviteOperation.class);
	private static PrimitiveService primitiveService=(PrimitiveService) JNDIUtils
			.lookUpEJB(EJBRegistry.PrimitiveServiceBean);

	
	
	@Get("json")
	public String getOperations() throws ParseException {
		String codeAgence = getQuery().getValues("codeAgence");
		String dateDeb=getQuery().getValues("dateDeb");
		String dateFin=getQuery().getValues("dateFin");
		
		OperationModel om=new OperationModel();
		Gson gson = new Gson();
		try {
			    
			
			HashMap<String, Object> result = new HashMap<String, Object>();
			
			Date datefinCal = Utils.addDays(new Date(), -3, TimeZone.getTimeZone("UTC"));
			Date dateDebut=new Date(Long.valueOf(dateDeb));
			
			if(dateDebut.before(datefinCal)) {
				log.info("GET OPERATION BULK SWITCH SUR 30J");
				result=primitiveService.getOperationMois(codeAgence, dateDeb, dateFin);

			}else {
				log.info("GET OPERATION BULK DEFAULT TABLE");
				result=primitiveService.getOperation(codeAgence, dateDeb, dateFin);

			}


			if(result!=null){
				Integer codeRetour=(Integer) result.get("CODE");
				String message=(String) result.get("MESSAGE");
				if(codeRetour == 202){
					List<ActiviteModel> opliste=(List<ActiviteModel>)result.get("OBJECT");

					if (opliste == null || opliste.isEmpty()) {
						om.setErrorMessage("Aucune operation");
						om.setErrorCode("200");
						String resultat = gson.toJson(om);
						return resultat;
					}else{
						om.setErrorMessage("Liste operation");
						om.setErrorCode("200");
						om.setActiviteModels(opliste);
						String resultat = gson.toJson(om);
						return resultat;
					}
				}else{
					om.setErrorMessage(message);
					om.setErrorCode(codeRetour+"");
					String resultat = gson.toJson(om);
					return resultat;
				}
			
			
			}else{
				om.setErrorMessage("NULL RESULT");
				om.setErrorCode("500");
				String resultat = gson.toJson(om);
				return resultat;
			}
			
		} catch (Exception e) {
			om.setErrorMessage("Erreur interne");
			om.setErrorCode("500");
			String resultat = gson.toJson(om);
			log.error("",e );
			return resultat;
		}
	


	}
}

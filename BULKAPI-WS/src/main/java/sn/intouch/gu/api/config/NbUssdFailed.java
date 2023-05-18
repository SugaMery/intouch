package sn.intouch.gu.api.config;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import sn.intouch.gu.api.ejb.models.USSDOperationModel;
import sn.intouch.gu.api.ejb.services.PrimitiveService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.entities.TransactionModel;
import sn.intouch.gu.api.entities.USSDModel;
import sn.intouch.gu.api.entities.UssdNbFailed;

public class NbUssdFailed extends  ServerResource{
	
	
	private static Logger log = Logger.getLogger(UssdOperation.class);
	private static PrimitiveService primitiveService=(PrimitiveService) JNDIUtils
			.lookUpEJB(EJBRegistry.PrimitiveServiceBean);



	@Get("json")
	public String getUssd() throws ParseException {
		String bulkid = getQuery().getValues("bulkid");
		String codeAgence= getQuery().getValues("codeAgence");

		List<USSDOperationModel> liste=new ArrayList<USSDOperationModel>();
		TransactionModel tm=new TransactionModel();
		HashMap<String,Object> result = null;
		UssdNbFailed ussdModel=new UssdNbFailed();
        String mybulk=bulkid+";";
		Gson gson = new Gson();
		try {
			result=primitiveService.getNbFaildeUssd(mybulk,codeAgence);
			if(result!=null){
				Integer codeRetour=(Integer) result.get("CODE");
				String message=(String) result.get("MESSAGE");
				if(codeRetour == 202){
					Long nb=(Long) result.get("OBJECT");
					ussdModel.setErrorCode("200");
					ussdModel.setErrorMessage("SUCCESS");
					ussdModel.setNbFailed(nb);
					String resultat = gson.toJson(ussdModel);
					return resultat;
				}else{
					ussdModel.setErrorCode(codeRetour+"");
					ussdModel.setErrorMessage(message);
					String resultat = gson.toJson(ussdModel);
					return resultat;
				}
				
			}else{
				ussdModel.setErrorCode("500");
				ussdModel.setErrorMessage("NULL VALUE");
				String resultat = gson.toJson(ussdModel);
				return resultat;
			}
		} catch (Exception e) {
			tm.setErrorMessage("Erreur interne");
			tm.setErrorCode("500");
			String resultat = gson.toJson(tm);
			log.error("",e );
			return resultat;
		}		
	}



}

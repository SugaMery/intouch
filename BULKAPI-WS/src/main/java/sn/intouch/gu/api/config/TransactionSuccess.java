package sn.intouch.gu.api.config;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import sn.intouch.gu.api.ejb.entities.Transaction;
import sn.intouch.gu.api.ejb.models.USSD;
import sn.intouch.gu.api.ejb.models.USSDOperationModel;
import sn.intouch.gu.api.ejb.services.PrimitiveService;
import sn.intouch.gu.api.ejb.services.TransactionService;
import sn.intouch.gu.api.ejb.services.TransactionServiceBean;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.entities.TransactionModel;

public class TransactionSuccess extends ServerResource{
	
	private static TransactionService transactionService=(TransactionService) JNDIUtils
			.lookUpEJB(EJBRegistry.TransactionServiceBean);
	private static Logger log = Logger.getLogger(TransactionSuccess.class);
	private static PrimitiveService primitiveService=(PrimitiveService) JNDIUtils
			.lookUpEJB(EJBRegistry.PrimitiveServiceBean);



	
	@Get("json")
	public String getTransaction() throws ParseException {
		String bulkid = getQuery().getValues("bulkid");
		USSDOperationModel om=new USSDOperationModel();
		HashMap<String,Object> result = null;
		 String mybulk=bulkid+";";
		Gson gson = new Gson();
		try {
			result=primitiveService.getTransactionSuccess(mybulk);
			if(result!=null){
				Integer codeRetour=(Integer) result.get("CODE");
				String message=(String) result.get("MESSAGE");
				if(codeRetour == 202){
					List<USSD> ussd=(List<USSD>) result.get("OBJECT");
					om.setErrorCode("200");
					om.setErrorMessage("SUCCESS");
					om.setListeUssd(ussd);
					String resultat = gson.toJson(om);
					return resultat;
				}else{
					
					om.setErrorCode(codeRetour+"");
					om.setErrorMessage(message);
					String resultat = gson.toJson(om);
					return resultat;
				}
				
			}else{
				om.setErrorCode("500");
				om.setErrorMessage("NULL VALUE");
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

package sn.intouch.gu.api.config;

import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import sn.intouch.gu.api.ejb.services.ParameterService;
import sn.intouch.gu.api.ejb.services.PrimitiveService;
import sn.intouch.gu.api.ejb.services.PrimitiveServiceBean;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.entities.TransactionExchange;

public class InitiateBulkPayment extends ServerResource {

	private static Logger log = Logger.getLogger(BULKAPIRequest.class);
	private static PrimitiveService primitiveService=(PrimitiveService) JNDIUtils
			.lookUpEJB(EJBRegistry.PrimitiveServiceBean);
	private static ParameterService parameterService=(ParameterService) JNDIUtils
			.lookUpEJB(EJBRegistry.ParameterServiceBean);

	@Put("json")
	@ApiOperation(value = "Initier paiement ", tags = "bulkpayment")
	public TransactionExchange initierPaiement(TransactionExchange transactionExchange){
		TransactionExchange tr = new TransactionExchange();
		tr=transactionExchange;
		log.info("Initier paiement ");

		try {
			
			HashMap<String, Object> result = new HashMap<String, Object>();
			result=primitiveService.getFraisByServiceAndAmount(transactionExchange.getServiceExchange().getServiceCode(), transactionExchange.getSalePointCode(), transactionExchange.getTransactionMontant());

			if(result!=null){
				Integer codeRetour=(Integer) result.get("CODE");
				String message=(String) result.get("MESSAGE");
				if(codeRetour == 202){
					TransactionExchange trInit=transactionExchange;
					trInit.getServiceExchange().getInfosSup().put("call_back_url", parameterService.getParameterByCode("BULK_NOTIF_URL").getPrmStringValue());
					TransactionExchange trInitResult= BULKAPIRequest.createUSSD(trInit);
					tr.setErrorCode(trInitResult.getErrorCode());
					tr.setErrorMessage(trInitResult.getErrorMessage());

				}else{
					tr.setErrorCode(String.valueOf(codeRetour));
					tr.setErrorMessage(message);
					log.info("-----------------------------GET FRAIS "+message);
				}
			}

			
		} catch (Exception e) {
			tr.setErrorCode("500");
			tr.setErrorMessage("ERREUR INTERNE");
			log.error("", e);
		}
		return tr;
	}
}
package sn.intouch.gu.api.config;

import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import sn.intouch.gu.api.ejb.entities.NotificationAPI;
import sn.intouch.gu.api.ejb.entities.USSDOperation;
import sn.intouch.gu.api.ejb.services.ParameterService;
import sn.intouch.gu.api.ejb.services.PrimitiveService;
import sn.intouch.gu.api.ejb.services.USSDOperationService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.entities.ExchangeOperation;
import sn.intouch.gu.api.entities.MoyenExchange;
import sn.intouch.gu.api.entities.ServiceExchange;
import sn.intouch.gu.api.entities.TransactionExchange;


public class ValidateBulkPay  extends ServerResource{

	Gson gson = new Gson();
	private static Logger LOG = Logger.getLogger(ValidateBulkPay.class);
	private static USSDOperationService ussdService=(USSDOperationService) JNDIUtils
			.lookUpEJB(EJBRegistry.USSDOperationServiceBean);
	private static PrimitiveService primitiveService=(PrimitiveService) JNDIUtils
			.lookUpEJB(EJBRegistry.PrimitiveServiceBean);
	


	@Post("json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESSFUL"),
			@ApiResponse(code = 404, message = "Ordre de paiement non disponible"),
			@ApiResponse(code = 500, message = "Erreur serveur"),
			@ApiResponse(code = 401, message = "Accés non autorisé au service"),
			@ApiResponse(code = 422, message = "Données invalides"), })
	@ApiOperation(value = "Valider le paiement bulk", tags = "Cash")
	public NotificationAPI validate(NotificationAPI notification) {	
		try {
			String payment_token = notification.getGu_transaction_id();
			USSDOperation ussd=new USSDOperation();

			LOG.info("---------- STARTED RESPONSE ------------");
			LOG.info(gson.toJson(notification));
			LOG.info("----------- END RESPONSE --------------");

			ussd=ussdService.getOperationByToken(payment_token);

			if (notification.getStatus().equals("SUCCESSFUL")){
				String codeAgence=ussd.getCodeSalePoint();
				String codeService=ussd.getCodeService();
				Double montant=ussd.getMontant();
				HashMap<String, Object> result = new HashMap<String, Object>();
				result=primitiveService.getFraisByServiceAndAmount(codeService, codeAgence, montant);

				if(result!=null){
					Integer codeRetour=(Integer) result.get("CODE");
					String message=(String) result.get("MESSAGE");
					if(codeRetour == 202){
						Double montantFrais =(Double)result.get("OBJECT");
						LOG.info("Creation Transaction exchange avec service BULKPAYMENT");

						TransactionExchange trbulk=new TransactionExchange();
						ServiceExchange service=new ServiceExchange();
						HashMap<String, String> map = new HashMap<String, String>();
						service.setInfosSup(map);
						trbulk.setServiceExchange(service);
						MoyenExchange moy=new MoyenExchange();
						trbulk.setMoyenExchange(moy);
						trbulk.setTransactionMontant(montantFrais);
						trbulk.getServiceExchange().setServiceCode("BULKPAYMENT");
						trbulk.setToken(new Date().getTime());

						trbulk.setToken(new Date().getTime());
						trbulk.setCodePDA(ussd.getCodePDA());
						trbulk.setLoginAgent(ussd.getLoginAgent());
						trbulk.setSalePointCode(codeAgence);
						trbulk.getServiceExchange().getInfosSup().put("complements",ussd.getSpareOp3());
						trbulk.getServiceExchange().getInfosSup().put("destinataire",ussd.getDestinataire());

						TransactionExchange trResult=BULKAPIRequest.createUSSD(trbulk);
						if(trResult.getErrorCode().equalsIgnoreCase("200")){
							LOG.info("USSD OPERATION SUCCES");
							Status s = new Status(200, "USSD OPERATION SUCCES");
							getResponse().setStatus(s);
							return notification;
						}
					}else{
						Status s = new Status(codeRetour, message);
						getResponse().setStatus(s);
						LOG.info(message);
						return notification;
					}
					
				}else{
					Status s = new Status(404, "FRAIS NOT FOUND");
					getResponse().setStatus(s);
					LOG.info("FRAIS NOT FOUND");
					return notification;
				}
			}else{
				Status s = new Status(500, "USSD OPERATION FAILED");
				getResponse().setStatus(s);
				LOG.info("USSD OPERATION FAILED");
				return notification;
			}


		}catch(Exception e){
            LOG.error("", e);
            Status s = new Status(500, "ERREUR SERVEUR");
			getResponse().setStatus(s);
			LOG.info("USSD OPERATION FAILED");
			return notification;
		}
		return notification;
	}
}

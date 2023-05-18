package sn.intouch.gu.api.ejb.services;

import java.util.HashMap;

import javax.ejb.Remote;

import sn.intouch.gu.api.ejb.models.USSDOperationModel;

@Remote
public interface PrimitiveService {
	
	public  HashMap<String, Object> getFraisByServiceAndAmount(String codeService,String codeAgence,Double montant);
	public  HashMap<String, Object> getUssdBulk(String idbulk);
	public HashMap<String, Object> getOperation(String codeAgence,String dateDeb,String dateFin);
	public  HashMap<String, Object> getUssdBulkByTags(String idbulk,String codeAgence);
	public  HashMap<String, Object> getTransactionSuccess(String idbulk);
	public  HashMap<String, Object> getNbFaildeUssd(String idbulk,String codeAgence);
	public  HashMap<String, Object> getUssdBulkByTagsMois(String idbulk,String codeAgence);
	public HashMap<String, Object> getOperationMois(String codeAgence,String dateDeb,String dateFin);


	


}

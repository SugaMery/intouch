package sn.intouch.gu.api.ejb.services;

import java.util.List;

import javax.ejb.Remote;

import sn.intouch.gu.api.ejb.entities.USSDOperation;
import sn.intouch.gu.api.ejb.entities.USSDOperationMois;

@Remote
public interface USSDOperationService {
	
	USSDOperation getOperationByToken(String token);
	USSDOperation getJumUssd(String token,String idbulk);
	List<USSDOperation> getSuccessUssd(String idbulk);
	List<USSDOperation> getFaildeUssd(String idbulk);
	List<USSDOperation> getEncoursUssd(String idbulk);
	List<USSDOperation> getAllUssd(String idbulk,String codeAgence);
	List<USSDOperation> getSuccess(String idbulk);
	Long getNumberFailed(String idbulk,String codeAgence);
	List<USSDOperationMois> getAllUssdMois(String idbulk,String codeAgence);
	
	
	

}

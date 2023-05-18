package sn.intouch.gu.api.ejb.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import sn.intouch.gu.api.ejb.entities.Operation;
import sn.intouch.gu.api.ejb.entities.OperationMois;

@Remote
public interface OperationService {
	public List<Operation> getOperations(String codeAgence,Date dateDeb,Date dateFin);
	public List<OperationMois> getOperationsMois(String codeAgence,Date dateDeb,Date dateFin);


}

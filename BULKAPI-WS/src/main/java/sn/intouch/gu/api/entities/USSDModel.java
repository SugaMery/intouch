package sn.intouch.gu.api.entities;

import sn.intouch.gu.api.ejb.models.USSDOperationModel;

public class USSDModel extends ExchangeAbstract {
	private USSDOperationModel ussdModel;

	public USSDOperationModel getUssdModel() {
		return ussdModel;
	}

	public void setUssdModel(USSDOperationModel ussdModel) {
		this.ussdModel = ussdModel;
	}
	
	

}

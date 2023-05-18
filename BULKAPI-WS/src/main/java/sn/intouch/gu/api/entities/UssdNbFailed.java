package sn.intouch.gu.api.entities;

import java.io.Serializable;

import sn.intouch.gu.api.ejb.models.ExchangeAbstract;

public class UssdNbFailed extends ExchangeAbstract implements Serializable  {
	private Long nbFailed;

	public Long getNbFailed() {
		return nbFailed;
	}

	public void setNbFailed(Long nbFailed) {
		this.nbFailed = nbFailed;
	}
	
	

}

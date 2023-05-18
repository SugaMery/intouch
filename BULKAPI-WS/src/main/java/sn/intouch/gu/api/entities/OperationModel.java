package sn.intouch.gu.api.entities;

import java.io.Serializable;
import java.util.List;

import sn.intouch.gu.api.ejb.entities.Operation;
import sn.intouch.gu.api.ejb.models.ActiviteModel;

public class OperationModel  extends ExchangeAbstract {
	private List<ActiviteModel> activiteModels;

	public List<ActiviteModel> getActiviteModels() {
		return activiteModels;
	}

	public void setActiviteModels(List<ActiviteModel> activiteModels) {
		this.activiteModels = activiteModels;
	}

}

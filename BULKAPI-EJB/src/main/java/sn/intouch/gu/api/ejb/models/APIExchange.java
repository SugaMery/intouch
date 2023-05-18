package sn.intouch.gu.api.ejb.models;

import java.util.List;

public class APIExchange extends ExchangeAbstract {
	private List<PdaModel> pdaModels;
	private List<AgenceModel> agenceModels;
	private List<TokenAPIModel> tokenAPIModels;

	public APIExchange() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<PdaModel> getPdaModels() {
		return pdaModels;
	}

	public void setPdaModels(List<PdaModel> pdaModels) {
		this.pdaModels = pdaModels;
	}

	public List<AgenceModel> getAgenceModels() {
		return agenceModels;
	}

	public void setAgenceModels(List<AgenceModel> agenceModels) {
		this.agenceModels = agenceModels;
	}

	public List<TokenAPIModel> getTokenAPIModels() {
		return tokenAPIModels;
	}

	public void setTokenAPIModels(List<TokenAPIModel> tokenAPIModels) {
		this.tokenAPIModels = tokenAPIModels;
	}

}

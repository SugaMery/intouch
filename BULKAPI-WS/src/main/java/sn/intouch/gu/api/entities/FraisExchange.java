package sn.intouch.gu.api.entities;

import java.io.Serializable;
import java.util.HashMap;

public class FraisExchange extends ExchangeAbstract implements Serializable {

	
	private HashMap<String, String> fraisByService = new HashMap<String, String>();
	
	
	
	public FraisExchange() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public HashMap<String, String> getFraisByService() {
		return fraisByService;
	}

	public void setFraisByService(HashMap<String, String> fraisByService) {
		this.fraisByService = fraisByService;
	}

	

}
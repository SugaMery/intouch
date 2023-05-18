package sn.intouch.gu.api.entities;


import sn.intouch.gu.api.ejb.entities.USSDOperation;

public class ExchangeOperation extends ExchangeAbstract {
	private String resultCode;
	private USSDOperation operation;
	private String method;

	public ExchangeOperation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExchangeOperation(String resultCode, USSDOperation operation, String method) {
		super();
		this.resultCode = resultCode;
		this.operation = operation;
		this.method = method;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public USSDOperation getOperation() {
		return operation;
	}

	public void setOperation(USSDOperation operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	

}

package sn.intouch.gu.api.entities;

public abstract class AbstractResponse {
	private String status;
	private String message;

	public AbstractResponse() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

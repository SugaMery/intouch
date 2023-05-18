package sn.intouch.gu.api.entities;

public class MoyenExchange {
	private String payementModeCode;
	private Long moyMontant;

	public Long getmoyMontant() {
		return moyMontant;
	}

	public void setmoyMontant(Long moyMontant) {
		this.moyMontant = moyMontant;
	}

	public String getPayementModeCode() {
		return payementModeCode;
	}

	public void setPayementModeCode(String payementModeCode) {
		this.payementModeCode = payementModeCode;
	}

	public MoyenExchange(String payementModeCode, Long moyMontant) {

		super();

		this.payementModeCode = payementModeCode;
		this.moyMontant = moyMontant;

	}

	public MoyenExchange() {
		super();
		// TODO Auto-generated constructor stub
	}
}

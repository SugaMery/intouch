package sn.intouch.gu.api.ejb.models;

public class PdaModel{
	private String codePda;
	private String codeStation;
	private String nomStation;
	private String pda_fcm_token;

	public PdaModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCodePda() {
		return codePda;
	}

	public void setCodePda(String codePda) {
		this.codePda = codePda;
	}

	public String getCodeStation() {
		return codeStation;
	}

	public void setCodeStation(String codeStation) {
		this.codeStation = codeStation;
	}

	public String getNomStation() {
		return nomStation;
	}

	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
	}

	public String getPda_fcm_token() {
		return pda_fcm_token;
	}

	public void setPda_fcm_token(String pda_fcm_token) {
		this.pda_fcm_token = pda_fcm_token;
	}

}

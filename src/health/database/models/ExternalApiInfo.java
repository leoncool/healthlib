package health.database.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the external_api_info database table.
 * 
 */
@Entity
@Table(name = "external_api_info")
public class ExternalApiInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "access_token")
	private String accessToken;

	private String device;

	@Column(name = "ext_id")
	private String extId;

	private String loginID;

	@Column(name = "temp_token")
	private String tempToken;

	@Column(name = "temp_token_verifier")
	private String tempTokenVerifier;

	@Column(name = "token_secrect")
	private String tokenSecrect;
	@Column(name = "lateDataUpdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lateDataUpdate;
	@Column(name = "retrieveDays")
	private int retrieveDays;
	@Column(name = "apiCounter")
	private int apiCounter;

	public ExternalApiInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getExtId() {
		return this.extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getLoginID() {
		return this.loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getTempToken() {
		return this.tempToken;
	}

	public void setTempToken(String tempToken) {
		this.tempToken = tempToken;
	}

	public String getTempTokenVerifier() {
		return this.tempTokenVerifier;
	}

	public void setTempTokenVerifier(String tempTokenVerifier) {
		this.tempTokenVerifier = tempTokenVerifier;
	}

	public String getTokenSecrect() {
		return this.tokenSecrect;
	}

	public void setTokenSecrect(String tokenSecrect) {
		this.tokenSecrect = tokenSecrect;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getLateDataUpdate() {
		return lateDataUpdate;
	}

	public void setLateDataUpdate(Date lateDataUpdate) {
		this.lateDataUpdate = lateDataUpdate;
	}

	public int getRetrieveDays() {
		return retrieveDays;
	}

	public void setRetrieveDays(int retrieveDays) {
		this.retrieveDays = retrieveDays;
	}

	public int getApiCounter() {
		return apiCounter;
	}

	public void setApiCounter(int apiCounter) {
		this.apiCounter = apiCounter;
	}

}
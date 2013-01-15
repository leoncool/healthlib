package health.database.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fitbitLog database table.
 * 
 */
@Entity
@Table(name="fitbit_log")
public class FitbitLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String fetchData;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fetchTime;

	private String loginID;

	public FitbitLog() {
	}
	public boolean isFinished;
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFetchData() {
		return this.fetchData;
	}

	public void setFetchData(String fetchData) {
		this.fetchData = fetchData;
	}

	public Date getFetchTime() {
		return this.fetchTime;
	}

	public void setFetchTime(Date fetchTime) {
		this.fetchTime = fetchTime;
	}

	public String getLoginID() {
		return this.loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

}
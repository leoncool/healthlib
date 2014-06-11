package health.database.models.as;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;


/**
 * The persistent class for the analysis_results database table.
 * 
 */
@Entity
@Table(name="analysis_results")
@NamedQuery(name="AnalysisResult.findAll", query="SELECT a FROM AnalysisResult a")
public class AnalysisResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="job_id")
	private String jobId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="job_end_time")
	private Date jobEndTime;

	@Column(name="job_log")
	private String jobLog;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="job_start_time")
	private Date jobStartTime;

	@Column(name="job_status")
	private String jobStatus;
	@Column(name="model_status")
	private String model_status;
	
	@Column(name="model_id")
	private String modelId;
	@Column(name="service_id")
	private int service_id;

	@Column(name="model_log")
	private String modelLog;

	@Column(name="model_log_file")
	private String modelLogFile;

	@Column(name="user_id")
	private String userId;
	@Expose
	@Column(name="json_results")
	private String json_results;
	public AnalysisResult() {
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getJobEndTime() {
		return this.jobEndTime;
	}

	public void setJobEndTime(Date jobEndTime) {
		this.jobEndTime = jobEndTime;
	}

	public Object getJobLog() {
		return this.jobLog;
	}

	public void setJobLog(String jobLog) {
		this.jobLog = jobLog;
	}

	public Date getJobStartTime() {
		return this.jobStartTime;
	}

	public void setJobStartTime(Date jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	public String getJobStatus() {
		return this.jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public Object getModelLog() {
		return this.modelLog;
	}

	public void setModelLog(String modelLog) {
		this.modelLog = modelLog;
	}

	public String getModelLogFile() {
		return this.modelLogFile;
	}

	public void setModelLogFile(String modelLogFile) {
		this.modelLogFile = modelLogFile;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public String getModel_status() {
		return model_status;
	}

	public void setModel_status(String model_status) {
		this.model_status = model_status;
	}

	public String getJson_results() {
		return json_results;
	}

	public void setJson_results(String json_results) {
		this.json_results = json_results;
	}
	

}
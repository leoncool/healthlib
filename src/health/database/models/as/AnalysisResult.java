package health.database.models.as;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_id")
	private String jobId;

	@Column(name="analysis_resultscol")
	private String analysisResultscol;

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

	@Column(name="model_id")
	private String modelId;

	@Column(name="model_log")
	private String modelLog;

	@Column(name="model_log_file")
	private String modelLogFile;

	@Column(name="user_id")
	private String userId;

	public AnalysisResult() {
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getAnalysisResultscol() {
		return this.analysisResultscol;
	}

	public void setAnalysisResultscol(String analysisResultscol) {
		this.analysisResultscol = analysisResultscol;
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

}
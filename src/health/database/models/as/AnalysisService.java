package health.database.models.as;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the analysis_service database table.
 * 
 */
@Entity
@Table(name="analysis_service")
@NamedQuery(name="AnalysisService.findAll", query="SELECT a FROM AnalysisService a")
public class AnalysisService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name="cron_job")
	private String cronJob;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	private Date endTime;

	@Column(name="model_id")
	private String modelId;

	@Column(name="num_executed")
	private int numExecuted;

	@Column(name="num_max_execute")
	private int numMaxExecute;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;

	private String status;

	@Column(name="user_id")
	private String userId;

	public AnalysisService() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCronJob() {
		return this.cronJob;
	}

	public void setCronJob(String cronJob) {
		this.cronJob = cronJob;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public int getNumExecuted() {
		return this.numExecuted;
	}

	public void setNumExecuted(int numExecuted) {
		this.numExecuted = numExecuted;
	}

	public int getNumMaxExecute() {
		return this.numMaxExecute;
	}

	public void setNumMaxExecute(int numMaxExecute) {
		this.numMaxExecute = numMaxExecute;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
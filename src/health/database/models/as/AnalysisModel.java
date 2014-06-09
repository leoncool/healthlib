package health.database.models.as;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the analysis_models database table.
 * 
 */
@Entity
@Table(name="analysis_models")
@NamedQuery(name="AnalysisModel.findAll", query="SELECT a FROM AnalysisModel a")
public class AnalysisModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name="cron_job")
	private String cronJob;

	private String desp;

	private String name;

	private String notes;

	private String permissions;

	private double price;

	@Column(name="pricing_model")
	private String pricingModel;

	private String publisher;

	private String status;

	private String terms;

	@Column(name="total_inputs")
	private int totalInputs;

	@Column(name="total_outputs")
	private int totalOutputs;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;

	public AnalysisModel() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public Object getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Object getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPricingModel() {
		return this.pricingModel;
	}

	public void setPricingModel(String pricingModel) {
		this.pricingModel = pricingModel;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getTerms() {
		return this.terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public int getTotalInputs() {
		return this.totalInputs;
	}

	public void setTotalInputs(int totalInputs) {
		this.totalInputs = totalInputs;
	}

	public int getTotalOutputs() {
		return this.totalOutputs;
	}

	public void setTotalOutputs(int totalOutputs) {
		this.totalOutputs = totalOutputs;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}



}
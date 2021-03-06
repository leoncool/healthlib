package health.database.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the data_summary database table.
 * 
 */
@Entity
@Table(name="data_summary")
public class DataSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Temporal(TemporalType.DATE)
    @Column(name = "date")
	private Date date;
    @Column(name = "dstreamID")
	private String dstreamID;
    @Column(name = "goal")
	private double goal;
    @Column(name = "title")
	private String title;
    @Column(name = "value")
	private double value;
    @Column(name = "unit_id")
 	private String unit_id;
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
	private Date updated;
    private String loginID;
	public DataSummary() {
	}

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

	public String getDstreamID() {
		return this.dstreamID;
	}

	public void setDstreamID(String dstreamID) {
		this.dstreamID = dstreamID;
	}

	public double getGoal() {
		return this.goal;
	}

	public void setGoal(double goal) {
		this.goal = goal;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

}
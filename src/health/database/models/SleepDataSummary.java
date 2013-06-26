package health.database.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the sleep_data_summary database table.
 * 
 */
@Entity
@Table(name="sleep_data_summary")
public class SleepDataSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int awakeningCount;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String dstreamID;

	private double efficiency;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endtime;

	private int inBedMinutes;

	private int minutesAfterWakeup;

	private int minutesAsleep;

	private int minutesAwake;

	private int minutesToFallAsleep;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@Column(name="unit_id")
	private String unitId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	private int totalSleepRecords;
	private String loginID;
	public SleepDataSummary() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAwakeningCount() {
		return this.awakeningCount;
	}

	public void setAwakeningCount(int awakeningCount) {
		this.awakeningCount = awakeningCount;
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

	public double getEfficiency() {
		return this.efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public int getInBedMinutes() {
		return this.inBedMinutes;
	}

	public void setInBedMinutes(int inBedMinutes) {
		this.inBedMinutes = inBedMinutes;
	}

	public int getMinutesAfterWakeup() {
		return this.minutesAfterWakeup;
	}

	public void setMinutesAfterWakeup(int minutesAfterWakeup) {
		this.minutesAfterWakeup = minutesAfterWakeup;
	}

	public int getMinutesAsleep() {
		return this.minutesAsleep;
	}

	public void setMinutesAsleep(int minutesAsleep) {
		this.minutesAsleep = minutesAsleep;
	}

	public int getMinutesAwake() {
		return this.minutesAwake;
	}

	public void setMinutesAwake(int minutesAwake) {
		this.minutesAwake = minutesAwake;
	}

	public int getMinutesToFallAsleep() {
		return this.minutesToFallAsleep;
	}

	public void setMinutesToFallAsleep(int minutesToFallAsleep) {
		this.minutesToFallAsleep = minutesToFallAsleep;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public Date getUpdated() {
		return this.updated;
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

	public int getTotalSleepRecords() {
		return totalSleepRecords;
	}

	public void setTotalSleepRecords(int totalSleepRecords) {
		this.totalSleepRecords = totalSleepRecords;
	}

}
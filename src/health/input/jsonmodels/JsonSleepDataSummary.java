/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.input.jsonmodels;


/**
 *
 * @author Leon
 */
public class JsonSleepDataSummary {
	private String id;
	private String date;
	private String date_long;
	private String datastream_id;

	private String title;

	private String goal;
	private String value;
	private String unit_id;
	private String update_time;
	
	private long sleep_start_long;
	private long sleep_end_long;
	private String sleep_start_time;
	private String sleep_end_time;
	private int awakened_count;
	private double efficiency;

	private int minutes_inbed;

	private int minutes_afterwakeup;

	private int minutes_asleep;

	private int minutes_awake;

	private int minutes_tofallasleep;

	private String loginid;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDatastream_id() {
		return datastream_id;
	}

	public void setDatastream_id(String datastream_id) {
		this.datastream_id = datastream_id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getDate_long() {
		return date_long;
	}

	public void setDate_long(String date_long) {
		this.date_long = date_long;
	}



	public long getSleep_start_long() {
		return sleep_start_long;
	}

	public void setSleep_start_long(long sleep_start_long) {
		this.sleep_start_long = sleep_start_long;
	}

	public long getSleep_end_long() {
		return sleep_end_long;
	}

	public void setSleep_end_long(long sleep_end_long) {
		this.sleep_end_long = sleep_end_long;
	}

	public String getSleep_start_time() {
		return sleep_start_time;
	}

	public void setSleep_start_time(String sleep_start_time) {
		this.sleep_start_time = sleep_start_time;
	}

	public String getSleep_end_time() {
		return sleep_end_time;
	}

	public void setSleep_end_time(String sleep_end_time) {
		this.sleep_end_time = sleep_end_time;
	}

	public int getAwakened_count() {
		return awakened_count;
	}

	public void setAwakened_count(int awakened_count) {
		this.awakened_count = awakened_count;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public int getMinutes_inbed() {
		return minutes_inbed;
	}

	public void setMinutes_inbed(int minutes_inbed) {
		this.minutes_inbed = minutes_inbed;
	}

	public int getMinutes_afterwakeup() {
		return minutes_afterwakeup;
	}

	public void setMinutes_afterwakeup(int minutes_afterwakeup) {
		this.minutes_afterwakeup = minutes_afterwakeup;
	}

	public int getMinutes_asleep() {
		return minutes_asleep;
	}

	public void setMinutes_asleep(int minutes_asleep) {
		this.minutes_asleep = minutes_asleep;
	}

	public int getMinutes_awake() {
		return minutes_awake;
	}

	public void setMinutes_awake(int minutes_awake) {
		this.minutes_awake = minutes_awake;
	}

	public int getMinutes_tofallasleep() {
		return minutes_tofallasleep;
	}

	public void setMinutes_tofallasleep(int minutes_tofallasleep) {
		this.minutes_tofallasleep = minutes_tofallasleep;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}



}

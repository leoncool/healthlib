package health.database.datamarket;

import health.database.models.Datastream;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the configs database table.
 * 
 */
@Entity
@Table(name="data_sharing")
public class DataSharing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "loginID")
	private String loginID;
	
	@Column(name = "targetLoginID")
	private String targetLoginID;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;


	@Column(name = "desp")
	private String description;
	
    @JoinColumn(name = "streamID", referencedColumnName = "streamId")
    @ManyToOne(optional = false)
    private Datastream datastream;
	
	public DataSharing() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}


	public String getTargetLoginID() {
		return targetLoginID;
	}

	public void setTargetLoginID(String targetLoginID) {
		this.targetLoginID = targetLoginID;
	}

	public Datastream getStreamID() {
		return datastream;
	}

	public void setStreamID(Datastream streamID) {
		this.datastream = streamID;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
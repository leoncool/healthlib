package health.database.datamarket;

import health.database.models.Datastream;
import health.database.models.DatastreamUnits;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;


/**
 * The persistent class for the configs database table.
 * 
 */
@Entity
@Table(name="data_sharing_market")
public class DataMarket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "loginID")
	private String loginID;


	private double price;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name = "desp")
	private String description;
	

    @JoinColumn(name = "streamID", referencedColumnName = "streamId")
    @ManyToOne(optional = false)
    private Datastream datastream;
    
    private String terms;
    
    @Transient
    private List<DatastreamUnits> datastreamUnitsList;
    
    @Transient
    private String streamid;
    
	public DataMarket() {
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


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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


	public void setDatastream(Datastream datastream) {
		this.datastream = datastream;
	}

	public List<DatastreamUnits> getDatastreamUnitsList() {
		return datastreamUnitsList;
	}

	public void setDatastreamUnitsList(List<DatastreamUnits> datastreamUnitsList) {
		this.datastreamUnitsList = datastreamUnitsList;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getStreamid() {
		return streamid;
	}

	public void setStreamid(String streamid) {
		this.streamid = streamid;
	}

	public Datastream getDatastream() {
		return datastream;
	}


}
package health.database.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the data_permissions database table.
 * 
 */
@Entity
@Table(name = "data_permissions")
public class DataPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "given_loginid")
	private String givenLoginid;

	private String permission;

	@Column(name = "target_data_id")
	private String targetDataId;

	@Column(name = "target_data_type")
	private String targetDataType;
	@Column(name = "owner")
	@Basic(optional = false)
	private String owner;
	@Column(name = "status")
	@Basic(optional = false)
	private String status;

	public DataPermission() {
	}

	public int getRecordId() {
		return this.recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getGivenLoginid() {
		return this.givenLoginid;
	}

	public void setGivenLoginid(String givenLoginid) {
		this.givenLoginid = givenLoginid;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getTargetDataId() {
		return this.targetDataId;
	}

	public void setTargetDataId(String targetDataId) {
		this.targetDataId = targetDataId;
	}

	public String getTargetDataType() {
		return this.targetDataType;
	}

	public void setTargetDataType(String targetDataType) {
		this.targetDataType = targetDataType;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
package health.database.models.as;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the analysis_model_mappings database table.
 * 
 */
@Entity
@Table(name="analysis_model_mappings")
@NamedQuery(name="AnalysisModelMapping.findAll", query="SELECT a FROM AnalysisModelMapping a")
public class AnalysisModelMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="entry_id")
	private String entryId;

	@Column(name="model_id")
	private String modelId;

	private String source;

	private String target;

	@Column(name="user_id")
	private String userId;

	public AnalysisModelMapping() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntryId() {
		return this.entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
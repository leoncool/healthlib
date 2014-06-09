package health.database.models.as;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the analysis_model_entries database table.
 * 
 */
@Entity
@Table(name = "analysis_model_entries")
@NamedQuery(name = "AnalysisModelEntry.findAll", query = "SELECT a FROM AnalysisModelEntry a")
public class AnalysisModelEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "data_action")
	private String dataAction;

	@Column(name = "data_type")
	private String dataType;
	
	@Column(name = "entry_name")
	private String entry_name; 

	@Column(name = "entry_type")
	private String entryType;

	@Column(name = "num_order")
	private int numOrder;

	@Column(name = "model_id")
	private String model_id;
	@Column(name = "validation")
	private String validation;
	@Column(name = "desp")
	private String description;

	public AnalysisModelEntry() {
	}

	public AnalysisModelEntry(String _model_id, String _entryType,
			String _dataType, String _action, int _order) {
		this.entryType = _entryType;
		this.model_id = _model_id;
		this.dataType = _dataType;
		this.dataAction = _action;
		this.numOrder = _order;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataAction() {
		return this.dataAction;
	}

	public void setDataAction(String dataAction) {
		this.dataAction = dataAction;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getEntryType() {
		return this.entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public int getOrder() {
		return this.numOrder;
	}

	public void setOrder(int order) {
		this.numOrder = order;
	}

	public int getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(int numOrder) {
		this.numOrder = numOrder;
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntry_name() {
		return entry_name;
	}

	public void setEntry_name(String entry_name) {
		this.entry_name = entry_name;
	}

	
}
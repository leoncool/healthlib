package health.database.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the configs database table.
 * 
 */
@Entity
@Table(name="configs")
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;


	private String value;
	@Id
	private String variable;

	public Config() {
	}
	public Config(String varia,String val)
	{
		this.value=val;
		this.variable=varia;
	}
		public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getVariable() {
		return this.variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

}
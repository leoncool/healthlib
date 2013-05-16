/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.input.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Leon
 */
public class JsonDataValues {
    protected String unit_id;
    @SerializedName(value="val")
    protected String val;
    protected String val_tag;
    protected String short_uid;

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getVal_tag() {
        return val_tag;
    }

    public void setVal_tag(String val_tag) {
        this.val_tag = val_tag;
    }

	public String getShort_uid() {
		return short_uid;
	}

	public void setShort_uid(String short_uid) {
		this.short_uid = short_uid;
	}

}

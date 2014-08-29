/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.input.jsonmodels.singleunitstream;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Leon
 */
public class JsonSingleDataPoints {

    protected String at;
    @SerializedName(value="val")
    protected String val;
    protected String val_tag;
    protected String link;
	public String getAt() {
		return at;
	}
	public void setAt(String at) {
		this.at = at;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
    
}

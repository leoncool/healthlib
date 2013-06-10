/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.hbase.models;

import health.input.jsonmodels.JsonDataImport;
import health.input.jsonmodels.JsonDatastreamUnits;

import java.util.List;

/**
 *
 * @author Leon
 */
public class HBaseDataImport extends JsonDataImport {
//a
    protected String datastream_id;
    protected String stream_title;
    protected List<JsonDatastreamUnits> units_list;
    protected String device_id;
    protected String single_Unit_ID;
    protected String truncated;
    public String getDatastream_id() {
        return datastream_id;
    }

    public void setDatastream_id(String datastream_id) {
        this.datastream_id = datastream_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
   
    public List<JsonDatastreamUnits> getUnits_list() {
        return units_list;
    }

    
    
    public String getSingle_Unit_ID() {
		return single_Unit_ID;
	}

	public void setSingle_Unit_ID(String single_Unit_ID) {
		this.single_Unit_ID = single_Unit_ID;
	}

	public void setUnits_list(List<JsonDatastreamUnits> units_list) {
        this.units_list = units_list;
    }


    public String getTruncated() {
		return truncated;
	}

	public void setTruncated(String truncated) {
		this.truncated = truncated;
	}

	public String getDeviceid() {
        return device_id;
    }

    public void setDeviceid(String deviceid) {
        this.device_id = deviceid;
    }

	public String getStream_title() {
		return stream_title;
	}

	public void setStream_title(String stream_title) {
		this.stream_title = stream_title;
	}
    
}
//
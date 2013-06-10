/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.input.jsonmodels;

import health.input.jsonmodels.singleunitstream.JsonSingleDataPoints;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import device.input.jsonmodels.JsonDevice;

/**
 *
 * @author Leon
 */
public class JsonDataImport implements Serializable {
    @SerializedName(value="data_points")
    protected List<JsonDataPoints> data_points;
    protected String block_id;
    protected JsonDatastream datastream;
    protected JsonDevice device;
    protected JsonDatastreamBlock datablock;
    @SerializedName(value="single_unit_data_points")
    protected List<JsonSingleDataPoints> data_points_single_list; 
    public JsonDatastream getDatastream() {
        return datastream;
    }

    public void setDatastream(JsonDatastream datastream) {
        this.datastream = datastream;
    }

    public List<JsonDataPoints> getData_points() {
        return data_points;
    }

    public void setData_points(List<JsonDataPoints> data_points) {
        this.data_points = data_points;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public JsonDevice getDevice() {
        return device;
    }

    public void setDevice(JsonDevice device) {
        this.device = device;
    }

	public List<JsonSingleDataPoints> getData_points_single_list() {
		return data_points_single_list;
	}

	public void setData_points_single_list(
			List<JsonSingleDataPoints> data_points_single_list) {
		this.data_points_single_list = data_points_single_list;
	}

	public JsonDatastreamBlock getDatablock() {
		return datablock;
	}

	public void setDatablock(JsonDatastreamBlock datablock) {
		this.datablock = datablock;
	}
	
	
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.input.jsonmodels;

import java.util.List;

/**
 *
 * @author Leon
 */
public class JsonDataPoints {

    protected String at;
    protected List<JsonDataValues> value_list;
    protected String timetag;
    
    public String getTimetag() {
        return timetag;
    }

    public void setTimetag(String timetag) {
        this.timetag = timetag;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public List<JsonDataValues> getValue_list() {
        return value_list;
    }

    public void setValue_list(List<JsonDataValues> value_list) {
        this.value_list = value_list;
    }
}

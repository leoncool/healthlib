/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.input.jsonmodels;


/**
 *
 * @author leon
 */
public class JsonUserAvatar {
    private Integer id;
    private String loginid;
    private String hash;
    private String url;   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}

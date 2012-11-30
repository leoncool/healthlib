/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.input.jsonmodels;

import java.util.Date;

/**
 *
 * @author Leon
 */
public class JsonUserToken {
    private String loginid;
    private String password;
    private String token;
    private String expire_in_seconds;
    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpire_in_seconds() {
		return expire_in_seconds;
	}

	public void setExpire_in_seconds(String expire_in_seconds) {
		this.expire_in_seconds = expire_in_seconds;
	}
    
}

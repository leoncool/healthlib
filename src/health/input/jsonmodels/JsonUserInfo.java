/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.input.jsonmodels;

/**
 *
 * @author Leon
 */
public class JsonUserInfo {

    protected JsonUserAvatar avatar;
    protected JsonUser user;
    protected String total_followers;
    protected String total_followings;
    protected boolean is_follower;
    protected boolean is_following;
    public JsonUserAvatar getAvatar() {
        return avatar;
    }

    public void setAvatar(JsonUserAvatar avatar) {
        this.avatar = avatar;
    }

    public JsonUser getUser() {
        return user;
    }

    public void setUser(JsonUser user) {
        this.user = user;
    }

    public String getTotal_followers() {
        return total_followers;
    }

    public void setTotal_followers(String total_followers) {
        this.total_followers = total_followers;
    }

    public String getTotal_followings() {
        return total_followings;
    }

    public void setTotal_followings(String total_followings) {
        this.total_followings = total_followings;
    }

	public boolean isIs_follower() {
		return is_follower;
	}

	public void setIs_follower(boolean is_follower) {
		this.is_follower = is_follower;
	}

	public boolean isIs_following() {
		return is_following;
	}

	public void setIs_following(boolean is_following) {
		this.is_following = is_following;
	}


    
    
}

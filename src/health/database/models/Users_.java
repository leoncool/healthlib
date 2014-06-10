package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.682+0100")
@StaticMetamodel(Users.class)
public class Users_ {
	public static volatile SingularAttribute<Users, String> loginID;
	public static volatile SingularAttribute<Users, Date> updated;
	public static volatile SingularAttribute<Users, Date> createdTime;
	public static volatile SingularAttribute<Users, String> password;
	public static volatile SingularAttribute<Users, String> parentID;
	public static volatile SingularAttribute<Users, String> groupID;
	public static volatile SingularAttribute<Users, String> language;
	public static volatile SingularAttribute<Users, String> timezone;
	public static volatile SingularAttribute<Users, String> screenname;
	public static volatile SingularAttribute<Users, String> email;
	public static volatile SingularAttribute<Users, Integer> userAvatarid;
	public static volatile SingularAttribute<Users, String> birthday;
	public static volatile SingularAttribute<Users, String> gender;
	public static volatile SingularAttribute<Users, UserDetails> userDetails;
	public static volatile SingularAttribute<Users, UserAvatar> userAvatar;
}

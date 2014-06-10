package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.672+0100")
@StaticMetamodel(LoginToken.class)
public class LoginToken_ {
	public static volatile SingularAttribute<LoginToken, String> tokenID;
	public static volatile SingularAttribute<LoginToken, Date> created;
	public static volatile SingularAttribute<LoginToken, Date> expireTime;
	public static volatile SingularAttribute<LoginToken, String> ip;
	public static volatile SingularAttribute<LoginToken, String> loginID;
}

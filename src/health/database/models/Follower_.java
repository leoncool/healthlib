package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.669+0100")
@StaticMetamodel(Follower.class)
public class Follower_ {
	public static volatile SingularAttribute<Follower, String> loginID;
	public static volatile SingularAttribute<Follower, String> followerID;
	public static volatile SingularAttribute<Follower, Date> createdTime;
	public static volatile SingularAttribute<Follower, String> note;
	public static volatile SingularAttribute<Follower, Integer> id;
	public static volatile SingularAttribute<Follower, String> status;
}

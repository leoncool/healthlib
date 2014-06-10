package health.database.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.680+0100")
@StaticMetamodel(UserDetails.class)
public class UserDetails_ {
	public static volatile SingularAttribute<UserDetails, Double> height;
	public static volatile SingularAttribute<UserDetails, Integer> hometown;
	public static volatile SingularAttribute<UserDetails, Double> weight;
	public static volatile SingularAttribute<UserDetails, Integer> id;
	public static volatile SingularAttribute<UserDetails, Users> users;
	public static volatile SingularAttribute<UserDetails, String> city;
	public static volatile SingularAttribute<UserDetails, String> country;
}

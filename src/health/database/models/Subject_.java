package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.676+0100")
@StaticMetamodel(Subject.class)
public class Subject_ {
	public static volatile SingularAttribute<Subject, Integer> id;
	public static volatile SingularAttribute<Subject, String> title;
	public static volatile SingularAttribute<Subject, Date> updated;
	public static volatile SingularAttribute<Subject, String> creator;
	public static volatile SingularAttribute<Subject, String> feed;
	public static volatile SingularAttribute<Subject, String> status;
	public static volatile SingularAttribute<Subject, String> description;
	public static volatile SingularAttribute<Subject, String> icon;
	public static volatile SingularAttribute<Subject, String> tags;
	public static volatile SingularAttribute<Subject, String> privateSet;
	public static volatile SingularAttribute<Subject, String> loginID;
	public static volatile SingularAttribute<Subject, String> privacy;
	public static volatile SingularAttribute<Subject, Date> createdTime;
	public static volatile SingularAttribute<Subject, Integer> parentSub;
	public static volatile SingularAttribute<Subject, String> purpose;
	public static volatile SingularAttribute<Subject, String> visibleSet;
}

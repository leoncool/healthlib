package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.671+0100")
@StaticMetamodel(JobsTable.class)
public class JobsTable_ {
	public static volatile SingularAttribute<JobsTable, Integer> id;
	public static volatile SingularAttribute<JobsTable, Date> createdDate;
	public static volatile SingularAttribute<JobsTable, String> method;
	public static volatile SingularAttribute<JobsTable, String> status;
	public static volatile SingularAttribute<JobsTable, String> targetObject;
	public static volatile SingularAttribute<JobsTable, String> targetObjectID;
	public static volatile SingularAttribute<JobsTable, Date> updatedDate;
}

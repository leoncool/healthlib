package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.522+0100")
@StaticMetamodel(DataSummary.class)
public class DataSummary_ {
	public static volatile SingularAttribute<DataSummary, Integer> id;
	public static volatile SingularAttribute<DataSummary, Date> date;
	public static volatile SingularAttribute<DataSummary, String> dstreamID;
	public static volatile SingularAttribute<DataSummary, Double> goal;
	public static volatile SingularAttribute<DataSummary, String> title;
	public static volatile SingularAttribute<DataSummary, Double> value;
	public static volatile SingularAttribute<DataSummary, String> unit_id;
	public static volatile SingularAttribute<DataSummary, Date> updated;
	public static volatile SingularAttribute<DataSummary, String> loginID;
}

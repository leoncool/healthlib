package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.652+0100")
@StaticMetamodel(FitbitLog.class)
public class FitbitLog_ {
	public static volatile SingularAttribute<FitbitLog, Integer> id;
	public static volatile SingularAttribute<FitbitLog, Date> date;
	public static volatile SingularAttribute<FitbitLog, String> fetchData;
	public static volatile SingularAttribute<FitbitLog, Date> fetchTime;
	public static volatile SingularAttribute<FitbitLog, String> loginID;
	public static volatile SingularAttribute<FitbitLog, Boolean> isFinished;
}

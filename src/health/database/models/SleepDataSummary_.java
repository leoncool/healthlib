package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.674+0100")
@StaticMetamodel(SleepDataSummary.class)
public class SleepDataSummary_ {
	public static volatile SingularAttribute<SleepDataSummary, Integer> id;
	public static volatile SingularAttribute<SleepDataSummary, Integer> awakeningCount;
	public static volatile SingularAttribute<SleepDataSummary, Date> date;
	public static volatile SingularAttribute<SleepDataSummary, String> dstreamID;
	public static volatile SingularAttribute<SleepDataSummary, Double> efficiency;
	public static volatile SingularAttribute<SleepDataSummary, Date> endtime;
	public static volatile SingularAttribute<SleepDataSummary, Integer> inBedMinutes;
	public static volatile SingularAttribute<SleepDataSummary, Integer> minutesAfterWakeup;
	public static volatile SingularAttribute<SleepDataSummary, Integer> minutesAsleep;
	public static volatile SingularAttribute<SleepDataSummary, Integer> minutesAwake;
	public static volatile SingularAttribute<SleepDataSummary, Integer> minutesToFallAsleep;
	public static volatile SingularAttribute<SleepDataSummary, Date> startTime;
	public static volatile SingularAttribute<SleepDataSummary, String> unitId;
	public static volatile SingularAttribute<SleepDataSummary, Date> updated;
	public static volatile SingularAttribute<SleepDataSummary, Integer> totalSleepRecords;
	public static volatile SingularAttribute<SleepDataSummary, String> loginID;
}

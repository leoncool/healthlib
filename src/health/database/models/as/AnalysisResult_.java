package health.database.models.as;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.502+0100")
@StaticMetamodel(AnalysisResult.class)
public class AnalysisResult_ {
	public static volatile SingularAttribute<AnalysisResult, String> jobId;
	public static volatile SingularAttribute<AnalysisResult, String> analysisResultscol;
	public static volatile SingularAttribute<AnalysisResult, Date> jobEndTime;
	public static volatile SingularAttribute<AnalysisResult, String> jobLog;
	public static volatile SingularAttribute<AnalysisResult, Date> jobStartTime;
	public static volatile SingularAttribute<AnalysisResult, String> jobStatus;
	public static volatile SingularAttribute<AnalysisResult, String> modelId;
	public static volatile SingularAttribute<AnalysisResult, String> modelLog;
	public static volatile SingularAttribute<AnalysisResult, String> modelLogFile;
	public static volatile SingularAttribute<AnalysisResult, String> userId;
}

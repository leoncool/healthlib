package health.database.models.as;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.492+0100")
@StaticMetamodel(AnalysisModel.class)
public class AnalysisModel_ {
	public static volatile SingularAttribute<AnalysisModel, String> id;
	public static volatile SingularAttribute<AnalysisModel, Date> createdTime;
	public static volatile SingularAttribute<AnalysisModel, String> cronJob;
	public static volatile SingularAttribute<AnalysisModel, String> desp;
	public static volatile SingularAttribute<AnalysisModel, String> name;
	public static volatile SingularAttribute<AnalysisModel, String> notes;
	public static volatile SingularAttribute<AnalysisModel, String> permissions;
	public static volatile SingularAttribute<AnalysisModel, Double> price;
	public static volatile SingularAttribute<AnalysisModel, String> pricingModel;
	public static volatile SingularAttribute<AnalysisModel, String> publisher;
	public static volatile SingularAttribute<AnalysisModel, String> status;
	public static volatile SingularAttribute<AnalysisModel, String> terms;
	public static volatile SingularAttribute<AnalysisModel, Integer> totalInputs;
	public static volatile SingularAttribute<AnalysisModel, Integer> totalOutputs;
	public static volatile SingularAttribute<AnalysisModel, Date> updatedTime;
	public static volatile SingularAttribute<AnalysisModel, String> thumbnail;
}

package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.639+0100")
@StaticMetamodel(ExternalApiInfo.class)
public class ExternalApiInfo_ {
	public static volatile SingularAttribute<ExternalApiInfo, Integer> id;
	public static volatile SingularAttribute<ExternalApiInfo, String> accessToken;
	public static volatile SingularAttribute<ExternalApiInfo, String> device;
	public static volatile SingularAttribute<ExternalApiInfo, String> extId;
	public static volatile SingularAttribute<ExternalApiInfo, String> loginID;
	public static volatile SingularAttribute<ExternalApiInfo, String> tempToken;
	public static volatile SingularAttribute<ExternalApiInfo, String> tempTokenVerifier;
	public static volatile SingularAttribute<ExternalApiInfo, String> tokenSecrect;
	public static volatile SingularAttribute<ExternalApiInfo, Date> lateDataUpdate;
	public static volatile SingularAttribute<ExternalApiInfo, Integer> retrieveDays;
	public static volatile SingularAttribute<ExternalApiInfo, Integer> apiCounter;
}

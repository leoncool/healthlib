package health.database.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.512+0100")
@StaticMetamodel(DataPermission.class)
public class DataPermission_ {
	public static volatile SingularAttribute<DataPermission, Integer> recordId;
	public static volatile SingularAttribute<DataPermission, String> givenLoginid;
	public static volatile SingularAttribute<DataPermission, String> permission;
	public static volatile SingularAttribute<DataPermission, String> targetDataId;
	public static volatile SingularAttribute<DataPermission, String> targetDataType;
	public static volatile SingularAttribute<DataPermission, String> owner;
	public static volatile SingularAttribute<DataPermission, String> status;
}

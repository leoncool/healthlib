package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.625+0100")
@StaticMetamodel(DeviceBinding.class)
public class DeviceBinding_ {
	public static volatile SingularAttribute<DeviceBinding, String> serialID;
	public static volatile SingularAttribute<DeviceBinding, Date> activeTime;
	public static volatile SingularAttribute<DeviceBinding, Date> createdTime;
	public static volatile SingularAttribute<DeviceBinding, String> activeBy;
	public static volatile SingularAttribute<DeviceBinding, String> deviceType;
	public static volatile SingularAttribute<DeviceBinding, String> deviceVendor;
	public static volatile SingularAttribute<DeviceBinding, String> owner;
	public static volatile SingularAttribute<DeviceBinding, String> deviceModel;
	public static volatile SingularAttribute<DeviceBinding, String> templateid;
}

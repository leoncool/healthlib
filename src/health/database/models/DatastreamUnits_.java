package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.521+0100")
@StaticMetamodel(DatastreamUnits.class)
public class DatastreamUnits_ {
	public static volatile SingularAttribute<DatastreamUnits, String> unitType;
	public static volatile SingularAttribute<DatastreamUnits, String> shortUnitID;
	public static volatile SingularAttribute<DatastreamUnits, String> unitSymbol;
	public static volatile SingularAttribute<DatastreamUnits, Float> minValue;
	public static volatile SingularAttribute<DatastreamUnits, Float> maxValue;
	public static volatile SingularAttribute<DatastreamUnits, Float> currentValue;
	public static volatile SingularAttribute<DatastreamUnits, String> unitLabel;
	public static volatile SingularAttribute<DatastreamUnits, Date> createdTime;
	public static volatile SingularAttribute<DatastreamUnits, Date> updatedTime;
	public static volatile SingularAttribute<DatastreamUnits, String> valueType;
	public static volatile SingularAttribute<DatastreamUnits, String> unitID;
	public static volatile SingularAttribute<DatastreamUnits, Datastream> streamID;
}

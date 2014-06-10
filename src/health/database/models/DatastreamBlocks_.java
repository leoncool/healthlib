package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.518+0100")
@StaticMetamodel(DatastreamBlocks.class)
public class DatastreamBlocks_ {
	public static volatile SingularAttribute<DatastreamBlocks, String> blockId;
	public static volatile SingularAttribute<DatastreamBlocks, String> blockDesc;
	public static volatile SingularAttribute<DatastreamBlocks, Date> created;
	public static volatile SingularAttribute<DatastreamBlocks, String> displayName;
	public static volatile SingularAttribute<DatastreamBlocks, String> tags;
	public static volatile SingularAttribute<DatastreamBlocks, Date> updated;
	public static volatile SingularAttribute<DatastreamBlocks, Datastream> streamID;
	public static volatile SingularAttribute<DatastreamBlocks, Date> startTime;
	public static volatile SingularAttribute<DatastreamBlocks, Date> endTime;
}

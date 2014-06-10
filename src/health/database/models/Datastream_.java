package health.database.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.515+0100")
@StaticMetamodel(Datastream.class)
public class Datastream_ {
	public static volatile SingularAttribute<Datastream, String> streamId;
	public static volatile SingularAttribute<Datastream, Date> updated;
	public static volatile SingularAttribute<Datastream, String> tags;
	public static volatile SingularAttribute<Datastream, Float> dataPoints;
	public static volatile SingularAttribute<Datastream, Integer> subId;
	public static volatile SingularAttribute<Datastream, String> note;
	public static volatile SingularAttribute<Datastream, Date> createdTime;
	public static volatile SingularAttribute<Datastream, String> owner;
	public static volatile SingularAttribute<Datastream, String> title;
	public static volatile SingularAttribute<Datastream, String> description;
	public static volatile SingularAttribute<Datastream, String> icon;
	public static volatile SingularAttribute<Datastream, String> purpose;
	public static volatile ListAttribute<Datastream, DatastreamUnits> datastreamUnitsList;
	public static volatile ListAttribute<Datastream, DatastreamBlocks> datastreamBlocksList;
}

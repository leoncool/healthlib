package com.lifestyle.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.352+0100")
@StaticMetamodel(Locationlogs.class)
public class Locationlogs_ {
	public static volatile SingularAttribute<Locationlogs, String> id;
	public static volatile SingularAttribute<Locationlogs, String> userID;
	public static volatile SingularAttribute<Locationlogs, Double> lat;
	public static volatile SingularAttribute<Locationlogs, Double> lon;
	public static volatile SingularAttribute<Locationlogs, Double> alt;
	public static volatile SingularAttribute<Locationlogs, Double> elevation;
	public static volatile SingularAttribute<Locationlogs, Double> accuracy;
	public static volatile SingularAttribute<Locationlogs, Double> bearing;
	public static volatile SingularAttribute<Locationlogs, Double> speed;
	public static volatile SingularAttribute<Locationlogs, Date> datetime;
}

package com.lifestyle.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-10T11:27:27.482+0100")
@StaticMetamodel(LocationPlaces.class)
public class LocationPlaces_ {
	public static volatile SingularAttribute<LocationPlaces, Integer> id;
	public static volatile SingularAttribute<LocationPlaces, Double> lat;
	public static volatile SingularAttribute<LocationPlaces, Double> lon;
	public static volatile SingularAttribute<LocationPlaces, Double> radius;
	public static volatile SingularAttribute<LocationPlaces, String> name;
	public static volatile SingularAttribute<LocationPlaces, String> userID;
	public static volatile SingularAttribute<LocationPlaces, String> category;
}

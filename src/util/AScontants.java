package util;

import java.util.ArrayList;

public interface AScontants {
	public static final String StringType = "string";
	public static final String AlertType = "alert";
	public static final String doubleType = "double";
	public static final String integerType = "int";
	public static  final String sensordataType = "sensordata";
	public static final String dataaction_saveOrUpdate = "save";
	public static final String dataaction_delete = "delete";
	public static final String dataaction_ignore = "ignore";
	public static final String dataaction_alert = "alert";
	public static final String fileType = "file";
	public static final String nullEntry = "null";
	public static final String as_input="input";
	public static final String as_output="output";
	public static final String status_draft="draft";
	public static final String status_live="live";
	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Host,Date,Authorization,Content-Length,"
			+ "Content-Type,x-amz-security-token,delimiter,marker,max-keys,prefix,Range,If-Modified-Since,"
			+ "If-Unmodified-Since,If-Match,If-None-Match,Cache-Control,Content-Disposition,Content-Encoding,"
			+ "Content-MD5,Expect,Expires,x-amz-acl";
	public static final String ACCESS_CONTROL_ALLOW_METHODS = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS";
	public static interface ModelJobStatus {
		public static final String running="running";
		public static final String finished_with_error="finished_with_error";
		public static final String finished_succesfully="finished_succesfully";
	}
	public static interface RequestParameters {
		public final String ModelName="model_name";
		public final String Model_ID="model_id";
		public final String Job_ID="job_id";
		public final String Service_ID="service_id";
		public final String ModelPrice="model_price";
		public final String ModelPriceModel="model_pricing_model";
		public final String ModelDescription="model_description";
		public final String ModelZipFile="model_zip_file";
		public final String ModelThumbnail="model_thumbnail";
		
		public final String ModelTerms="model_terms";

	}
}

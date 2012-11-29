//package health.database.DAO.nosql;
//
//import health.hbase.models.HBaseDataImport;
//
//import java.net.UnknownHostException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//
//import org.apache.hadoop.hbase.util.Bytes;
//
//import server.exception.ErrorCodeException;
//import util.AllConstants;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.MongoClient;
//
//public class MongoDatapointDAO implements DatapointDAOInterface {
//	
//	private final byte[] PROP_COL = Bytes.toBytes("p");
//	private final byte[] VALUE_COL = Bytes.toBytes("v");
//	private final byte[] VALUE_TAG_COL = Bytes.toBytes("vt");
//	private final byte[] TIME_TAG = Bytes.toBytes("tt");
//	private final byte[] BLOCK_ID = Bytes.toBytes("b");
//	private final String health_book_db = "hb";
//	private final String health_book_collection = "hb_collection";
//	private final String str_unassignBlockID = "noid";
//	private final byte[] unassignBlockID = Bytes.toBytes(str_unassignBlockID);
//	private DB db=null; 
//	protected MongoClient mongoClient=null;
//	protected DBCollection default_table;
//	
//	public MongoDatapointDAO() throws ErrorCodeException
//	{
//		try{
//		mongoClient = new MongoClient( "146.169.35.28" , 27017 );
//		if(db==null||default_table==null)
//		{
//			db=mongoClient.getDB(health_book_db);	
//			default_table=db.getCollection(health_book_collection);
//		}
//		}catch(UnknownHostException ex)
//		{
//			ex.printStackTrace();
//			throw new ErrorCodeException(AllConstants.ErrorDictionary.HBase_Internal_Error);
//		}
//	}
//	public int importDatapoints(HBaseDataImport importData)
//			throws ErrorCodeException
//			{
//	      BasicDBObject doc=new BasicDBObject("key", new Date().getTime());
//	      doc.append("value", "asdfa");
//	      default_table.insert(arr)
//		return 0;
//			}
//	public int importDatapoints(HBaseDataImport importData,
//			String newBlockName, String newBlockDecs)
//			throws ErrorCodeException{
//		return 0;
//	}
//	public HBaseDataImport exportDatapoints(String streamID, Long start,
//			Long end, String blockID, HashMap<String, String> dsUnitsList,
//			SimpleDateFormat format) throws ErrorCodeException
//			{
//		return null;
//			}
//	public long delete_A_Datapoint(String streamID, long at)
//			throws ErrorCodeException{
//		return 0;
//	}
//	public static void main(String args[])
//	{
//		
//	}
//}

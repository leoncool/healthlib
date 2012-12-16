package health.database.DAO.nosql;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import health.hbase.models.HBaseDataImport;
import server.exception.ErrorCodeException;

public interface DatapointDAOInterface {
	public int importDatapointsDatapoints(HBaseDataImport importData)
			throws ErrorCodeException;
	public int importDatapoints(HBaseDataImport importData,
			String newBlockName, String newBlockDecs)
			throws ErrorCodeException;
	public HBaseDataImport exportDatapoints(String streamID, Long start,
			Long end, String blockID, HashMap<String, String> dsUnitsList,
			SimpleDateFormat format) throws ErrorCodeException;
	public long delete_A_Datapoint(String streamID, long at)
			throws ErrorCodeException;
}

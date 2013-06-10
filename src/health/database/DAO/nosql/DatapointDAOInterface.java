package health.database.DAO.nosql;

import health.hbase.models.HBaseDataImport;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import server.exception.ErrorCodeException;

public interface DatapointDAOInterface {
	public int importDatapointsDatapoints(HBaseDataImport importData)
			throws ErrorCodeException;
	public int importDatapoints(HBaseDataImport importData,
			String newBlockName, String newBlockDecs)
			throws ErrorCodeException;
	public HBaseDataImport exportDatapoints(String streamID, Long start,
			Long end, String blockID, HashMap<String, String> dsUnitsList,
			SimpleDateFormat format,HashMap<String,Object> settings) throws ErrorCodeException;
	public long delete_A_Datapoint(String streamID, long at, List<String> unitIDList)
			throws ErrorCodeException;
}

package database;

import java.io.IOException;

import server.exception.ErrorCodeException;

import health.database.DAO.DatastreamDAO;
import health.database.DAO.nosql.HBaseDatapointDAO;
import health.database.models.DatastreamBlocks;

/**
 * @author Leon
 *
 */
public class DeleteADataPoint {
public static void main(String args[]) throws IOException, ErrorCodeException
{
	HBaseDatapointDAO dao=new HBaseDatapointDAO();
//	dao.delete_A_Datapoint("45c53baf-dafc-43cd-a634-3f0d8e8b0e75", 1354816675835L);
}
}

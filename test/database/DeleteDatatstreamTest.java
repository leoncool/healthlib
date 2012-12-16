package database;

import health.database.DAO.DatastreamDAO;
import health.database.models.Datastream;

/**
 * @author Leon
 *
 */
public class DeleteDatatstreamTest {
public static void main(String args[])
{
	DatastreamDAO dsDao=new DatastreamDAO();
	Datastream ds=dsDao.getDatastream("07e4eeb5-ab4f-49d6-84b4-764db3a1f7d4", false, false);
	dsDao.DeleteDatastream(ds);
}
}

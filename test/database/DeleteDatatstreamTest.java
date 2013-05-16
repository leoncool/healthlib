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
	Datastream stream = dsDao.getHealthDatastreamByTitle(664, "eeg",
			true, false);
	Datastream ds=dsDao.getDatastream(stream.getStreamId(), false, false);
	dsDao.DeleteDatastream(ds);
}
}

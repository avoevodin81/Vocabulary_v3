package interfaces;

import sql.DBConnector;

/**
 * Created by Test on 13.06.2016.
 */
public interface Closeble {
    public void closeDB(DBConnector connector);
}

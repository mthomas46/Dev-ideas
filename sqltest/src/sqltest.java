import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.schema.ISqlJetIndexDef;
import org.tmatesoft.sqljet.core.schema.ISqlJetTableDef;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.ISqlJetTransaction;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

public class sqltest {
	
	private static final String DB_NAME = "db.sqlite";
    private static final String TABLE_NAME = "Test1";

    private static final String FIRST_NAME_FIELD = "first_name";
    private static final String SECOND_NAME_FIELD = "second_name";
    
    
    public static void main(String[] args) throws SqlJetException {
    	
    
	File dbFile = new File(DB_NAME);
    dbFile.delete();
    
    
    // create database, table and two indices:
    SqlJetDb db = SqlJetDb.open(dbFile, true);
    // set DB option that have to be set before running any transactions: 
    db.getOptions().setAutovacuum(true);
    db.beginTransaction(SqlJetTransactionMode.WRITE);
    try{
    	db.getOptions().setUserVersion(1);
    	}
    finally
    	{
    	db.commit();
    	}
    String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" + SECOND_NAME_FIELD + " TEXT NOT NULL PRIMARY KEY , " + FIRST_NAME_FIELD + " TEXT NOT NULL)";

    db.beginTransaction(SqlJetTransactionMode.WRITE);
    try
    {
    	db.createTable(createTableQuery);
    }
    finally
    {
    	db.commit();
    }
    
    try
    {
    	ISqlJetTable table = db.getTable(TABLE_NAME);
    	table.insert("Thomas","Mykal");
    	table.insert("nortan","Mykal");
    }
    finally
    {
    	db.commit();
    	db.close();
    }
}
}
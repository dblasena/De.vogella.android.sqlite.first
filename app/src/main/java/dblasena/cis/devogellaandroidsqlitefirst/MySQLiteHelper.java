package dblasena.cis.devogellaandroidsqlitefirst;

/**
 * Created by dblasena on 3/31/2017.
 *This class is defining what will be created when the database is first set up
 * The database will be created with this information as its default values.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    // sets the table of comments for the comments to the comments variable
    public static final String TABLE_COMMENTS = "comments";
    // sets the column id to the id variable
    public static final String COLUMN_ID = "_id";
    // sets the column of the comments to the comments variable
    public static final String COLUMN_COMMENT = "comment";
    // sets the databases name to comments.db
    private static final String DATABASE_NAME = "commments.db";
    // sets teh version of the database to 1 when created
    private static final int DATABASE_VERSION = 1;

    public static final String COLUMN_RATING = "rating";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null, " + COLUMN_RATING + " text not null);";
    /**
     * Creats the MYSQLLiteHelper object for use in other classes
     * @param context passes in the information to create the object with the necessary information
     */
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /**
     * SQL create table command
     * CREATE TABLE table( id integer primary key autoincrement, Name string)
     * creates the database when the app is started
     * @param database  has all of the necessary information to create the databse
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    /**
     * if there is a newer version of the databse it will upgrade it to the newest version and delete all of the old data
     * @param db has the information to create the new database
     * @param newVersion has the number for the newest version of the database
     * @param oldVersion has the current version of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}

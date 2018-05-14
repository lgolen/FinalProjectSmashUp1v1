package edu.css.lonnie.finalproject;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class handles creating the player database. It uses constant variables to assemble the SQL statement that will be run onCreate.
 * A function is included to drop and recreate the database when upgrades are implemented.
 */
public class PlayerSQLLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_PLAYERS = "players";     // The name of the table to store players.
    public static final String COLUMN_ID = "_id";   // The column that will store the ID.
    public static final String COLUMN_PLAYER_NAME = "player_name"; // The column that will store the player name.

    private static final String DATABASE_NAME = "players.db";     // The name of the database for the new table.
    private static final int DATABASE_VERSION = 1;      //  The version of the database.

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_PLAYERS
            + "( " + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_PLAYER_NAME + " text not null);";

    public PlayerSQLLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Run the assembled SQL statement onCreate
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Drop and recreate the database. All data will be lost.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(GameSQLLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }
}
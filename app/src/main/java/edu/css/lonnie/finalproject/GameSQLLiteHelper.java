package edu.css.lonnie.finalproject;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class handles creating the game database. It uses constant variables to assemble the SQL statement that will be run onCreate.
 * A function is included to drop and recreate the database when upgrades are implemented.
 */
public class GameSQLLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_GAMES = "games";     // The name of the table to store games.
    public static final String COLUMN_ID = "_id";   // The column that will store the ID.
    public static final String COLUMN_PLAYER_ONE = "player_one"; // The column that will store the first player.
    public static final String COLUMN_FACTION_ONE_1 = "faction_one_1"; // The column that will store player one's first faction.
    public static final String COLUMN_FACTION_ONE_2 = "faction_one_2"; // The column that will store player one's second faction.
    public static final String COLUMN_SCORE_ONE = "score_one"; // The column that will store player one's score.
    public static final String COLUMN_PLAYER_TWO = "player_two"; // The column that will store the second player.
    public static final String COLUMN_FACTION_TWO_1 = "faction_two_1"; // The column that will store player two's first faction.
    public static final String COLUMN_FACTION_TWO_2 = "faction_two_2"; // The column that will store player two's second faction.
    public static final String COLUMN_SCORE_TWO = "score_two"; // The column that will store player two's score.

    private static final String DATABASE_NAME = "games.db";     // The name of the database for the new table.
    private static final int DATABASE_VERSION = 2;      //  The version of the database.

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
        + TABLE_GAMES
        + "( " + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_PLAYER_ONE + " text not null, "
        + COLUMN_FACTION_ONE_1 + " text not null, "
        + COLUMN_FACTION_ONE_2 + " text not null, "
        + COLUMN_SCORE_ONE + " text not null, "
        + COLUMN_PLAYER_TWO + " text not null, "
        + COLUMN_FACTION_TWO_1 + " text not null, "
        + COLUMN_FACTION_TWO_2 + " text not null, "
        + COLUMN_SCORE_TWO + " text not null);";

    public GameSQLLiteHelper(Context context) {
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
        onCreate(db);
    }
}
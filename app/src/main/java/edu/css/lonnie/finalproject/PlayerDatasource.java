package edu.css.lonnie.finalproject;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/*
 * This class defines the datasource for the player records
 */

public class PlayerDatasource {

    // Database fields
    private SQLiteDatabase database;
    private PlayerSQLLiteHelper dbHelper;
    private String[] allColumns = { PlayerSQLLiteHelper.COLUMN_ID,
            PlayerSQLLiteHelper. COLUMN_PLAYER_NAME};

    public PlayerDatasource(Context context) {
        dbHelper = new PlayerSQLLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Takes a string as a parameter and constructs a comment, which is then returned.
    public Player createPlayer(String name) {
        ContentValues values = new ContentValues();
        values.put(PlayerSQLLiteHelper.COLUMN_PLAYER_NAME, name);
        long insertId = database.insert(PlayerSQLLiteHelper.TABLE_PLAYERS, null,
                values);
        Cursor cursor = database.query(PlayerSQLLiteHelper.TABLE_PLAYERS,
                allColumns, PlayerSQLLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Player newPlayer = cursorToPlayer(cursor);
        cursor.close();
        return newPlayer;
    }

    // Takes a comment as a parameter, gets its ID, then uses that to delete it from the database.
    public void deletePlayer(Player player) {
        long id = player.getId();
        System.out.println("Player deleted with id: " + id);
        database.delete(PlayerSQLLiteHelper.TABLE_PLAYERS, PlayerSQLLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    // Return a list of all the comments from the database
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        Cursor cursor = database.query(PlayerSQLLiteHelper.TABLE_PLAYERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Player player = cursorToPlayer(cursor);
            players.add(player);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return players;
    }
    // Used in the createComment function to assist in creating and inserting a new comment
    private Player cursorToPlayer(Cursor cursor) {
        Player player = new Player();
        player.setId(cursor.getLong(cursor.getColumnIndex(PlayerSQLLiteHelper.COLUMN_ID)));
        player.setName(cursor.getString(cursor.getColumnIndex(PlayerSQLLiteHelper.COLUMN_PLAYER_NAME)));
        return player;
    }
}

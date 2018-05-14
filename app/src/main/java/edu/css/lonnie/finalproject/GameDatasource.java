package edu.css.lonnie.finalproject;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/*
 * This class defines the datasource for the games records
 */

public class GameDatasource {
    // Database fields
    private SQLiteDatabase database;
    private GameSQLLiteHelper dbHelper;
    private String[] allColumns = {GameSQLLiteHelper.COLUMN_ID,
        GameSQLLiteHelper.COLUMN_PLAYER_ONE,
        GameSQLLiteHelper.COLUMN_FACTION_ONE_1,
            GameSQLLiteHelper.COLUMN_FACTION_ONE_2,
        GameSQLLiteHelper.COLUMN_SCORE_ONE,
        GameSQLLiteHelper.COLUMN_PLAYER_TWO,
        GameSQLLiteHelper.COLUMN_FACTION_TWO_1,
            GameSQLLiteHelper.COLUMN_FACTION_TWO_2,
        GameSQLLiteHelper.COLUMN_SCORE_TWO};

    public GameDatasource(Context context) {
        dbHelper = new GameSQLLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Takes a string as a parameter and constructs a comment, which is then returned.
    public Game createGame(String playerOneName, String playerOneFaction1, String playerOneFaction2, String playerOneScore, String playerTwoName, String playerTwoFaction1, String playerTwoFaction2, String playerTwoScore) {
        ContentValues values = new ContentValues();

        values.put(GameSQLLiteHelper.COLUMN_PLAYER_ONE, playerOneName);
        values.put(GameSQLLiteHelper.COLUMN_FACTION_ONE_1, playerOneFaction1);
        values.put(GameSQLLiteHelper.COLUMN_FACTION_ONE_2, playerOneFaction2);
        values.put(GameSQLLiteHelper.COLUMN_SCORE_ONE, playerOneScore);
        values.put(GameSQLLiteHelper.COLUMN_PLAYER_TWO, playerTwoName);
        values.put(GameSQLLiteHelper.COLUMN_FACTION_TWO_1, playerTwoFaction1);
        values.put(GameSQLLiteHelper.COLUMN_FACTION_TWO_2, playerTwoFaction2);
        values.put(GameSQLLiteHelper.COLUMN_SCORE_TWO, playerTwoScore);

        long insertId = database.insert(GameSQLLiteHelper.TABLE_GAMES, null,
                values);
        Cursor cursor = database.query(GameSQLLiteHelper.TABLE_GAMES,
                allColumns, GameSQLLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Game newGame = cursorToGame(cursor);
        cursor.close();
        return newGame;
    }

    // Takes a comment as a parameter, gets its ID, then uses that to delete it from the database.
    public void deleteGame(Game game) {
        long id = game.getId();
        System.out.println("Game deleted with id: " + id);
        database.delete(GameSQLLiteHelper.TABLE_GAMES, GameSQLLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    // Return a list of all the comments from the database
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<Game>();

        Cursor cursor = database.query(GameSQLLiteHelper.TABLE_GAMES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Game game = cursorToGame(cursor);
            games.add(game);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return games;
    }
    // Used in the createComment function to assist in creating and inserting a new comment
    private Game cursorToGame(Cursor cursor) {
        Game game = new Game();
        game.setId(cursor.getLong(cursor.getColumnIndex(GameSQLLiteHelper.COLUMN_ID)));
        game.setPlayerOneName(cursor.getString(cursor.getColumnIndex(GameSQLLiteHelper.COLUMN_PLAYER_ONE)));
        game.setPlayerOneFaction1(cursor.getString(cursor.getColumnIndex(GameSQLLiteHelper.COLUMN_FACTION_ONE_1)));
        game.setPlayerOneFaction2(cursor.getString(cursor.getColumnIndex(GameSQLLiteHelper.COLUMN_FACTION_ONE_2)));
        game.setPlayerOneScore(cursor.getString(cursor.getColumnIndex(GameSQLLiteHelper.COLUMN_SCORE_ONE)));
        game.setPlayerTwoName(cursor.getString(cursor.getColumnIndex(GameSQLLiteHelper.COLUMN_PLAYER_TWO)));
        game.setPlayerTwoFaction1(cursor.getString(cursor.getColumnIndex(GameSQLLiteHelper.COLUMN_FACTION_TWO_1)));
        game.setPlayerTwoFaction2(cursor.getString(cursor.getColumnIndex(GameSQLLiteHelper.COLUMN_FACTION_TWO_2)));
        game.setPlayerTwoScore(cursor.getString(cursor.getColumnIndex(GameSQLLiteHelper.COLUMN_SCORE_TWO)));

        return game;
    }
}

package edu.css.lonnie.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/*
* This class is the activity for adding games to the database
*/
public class AddGameActivity extends AppCompatActivity {
    private GameDatasource gameDatasource;
    private PlayerDatasource playerDatasource;
    private List<Player> playerList;

    private Button buttonAddGame;
    private EditText editTextScoreOne, editTextScoreTwo;
    private Spinner spinnerPlayerOne, spinnerFactionOne1, spinnerFactionOne2, spinnerPlayerTwo, spinnerFactionTwo1, spinnerFactionTwo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game);

        gameDatasource = new GameDatasource(this);
        gameDatasource.open();
        playerDatasource = new PlayerDatasource(this);
        playerDatasource.open();


        buttonAddGame = (Button) findViewById(R.id.buttonAddGame);
        spinnerPlayerOne = (Spinner) findViewById(R.id.spinnerPlayerOne);
        spinnerFactionOne1 = (Spinner) findViewById(R.id.spinnerFactionOne1);
        spinnerFactionOne2 = (Spinner) findViewById(R.id.spinnerFactionOne2);
        spinnerPlayerTwo = (Spinner) findViewById(R.id.spinnerPlayerTwo);
        spinnerFactionTwo1 = (Spinner) findViewById(R.id.spinnerFactionTwo1);
        spinnerFactionTwo2 = (Spinner) findViewById(R.id.spinnerFactionTwo2);
        editTextScoreOne = (EditText) findViewById(R.id.editTextScoreOne);
        editTextScoreTwo = (EditText) findViewById(R.id.editTextScoreTwo);

        playerList = playerDatasource.getAllPlayers();
        ArrayList<Player> playerArrayList = new ArrayList<Player>(playerList);
        ArrayAdapter<Player> playerAdapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, playerArrayList);

        spinnerPlayerOne.setAdapter(playerAdapter);
        spinnerPlayerTwo.setAdapter(playerAdapter);

        buttonAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game game = gameDatasource.createGame(spinnerPlayerOne.getSelectedItem().toString(), spinnerFactionOne1.getSelectedItem().toString(), spinnerFactionOne2.getSelectedItem().toString(), editTextScoreOne.getText().toString(),
                        spinnerPlayerTwo.getSelectedItem().toString(), spinnerFactionTwo1.getSelectedItem().toString(),  spinnerFactionTwo2.getSelectedItem().toString(), editTextScoreTwo.getText().toString());
                Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActivityIntent);
                Toast.makeText(AddGameActivity.this, "Game added.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}

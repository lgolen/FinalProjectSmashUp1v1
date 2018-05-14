package edu.css.lonnie.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/*
 * This class is the activity for adding players to the database
 */

public class ManagePlayerActivity extends AppCompatActivity {
    private PlayerDatasource datasource;
    private Button buttonAddPlayer;
    private EditText editTextPlayerName;
    private ListView lv;
    ArrayAdapter<Player> playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_players);
        datasource = new PlayerDatasource(this);
        datasource.open();
        updateDataset();

        editTextPlayerName = (EditText) findViewById(R.id.editTextPlayerName);
        buttonAddPlayer = (Button) findViewById(R.id.buttonAddPlayer);
        buttonAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player player = datasource.createPlayer(editTextPlayerName.getText().toString());
                updateDataset();
                playerAdapter.notifyDataSetChanged();
                Toast.makeText(ManagePlayerActivity.this, "Player added.",
                        Toast.LENGTH_SHORT).show();
                //Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);
                //finish();
                //startActivity(mainActivityIntent);
            }
        });

        lv.setLongClickable(true);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(final AdapterView<?> p, View v, final int po, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManagePlayerActivity.this);
                builder.setTitle("Delete Player");
                builder.setMessage("Are you sure you want to delete?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int ii) {
                        Player thePlayer = playerAdapter.getItem(po);
                        datasource.deletePlayer(thePlayer);
                        updateDataset();
                        Toast.makeText(ManagePlayerActivity.this, "Player deleted.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int ii) {
                                dialog.dismiss();
                            }
                        }
                );
                builder.show();
                return true;
            }
        });
    }

    void updateDataset() {
        ArrayList<Player> values = datasource.getAllPlayers();
        playerAdapter = new PlayerAdapter(ManagePlayerActivity.this, android.R.layout.simple_list_item_single_choice, values);
        lv = (ListView)findViewById(R.id.listViewPlayers);
        lv.setAdapter(playerAdapter);
    }

}

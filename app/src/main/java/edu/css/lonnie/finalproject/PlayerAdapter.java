package edu.css.lonnie.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
 * This class is an adapter to list the players in a listview
 */

public class PlayerAdapter extends ArrayAdapter<Player> {

    private List<Player> playerList;            // The list of players to display
    private Context context;                // The original activity that displays this
    private int layoutResource;                   // the layout to use

    public PlayerAdapter(Context context, int resource, ArrayList<Player> playerList) {
        super(context, resource, playerList);
        this.context = context;
        this.layoutResource = resource;
        this.playerList = playerList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the fish we are displaying
        final Player player = playerList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //view = inflater.inflate(R.layout.fish_row_layout, null);
        view = inflater.inflate(R.layout.player_row, null);

        TextView textViewPlayerName = (TextView)view.findViewById(R.id.textViewPlayerName);
        textViewPlayerName.setText(player.getName());

        return(view);
    }


}

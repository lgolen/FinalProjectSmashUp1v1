package edu.css.lonnie.finalproject;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

/*
 * This class is an adapter to list the games in a listview
 */

public class GameAdapter extends ArrayAdapter<Game>{
    private List<Game> gameList;            // The list of players to display
    private Context context;                // The original activity that displays this
    private int layoutResource;                   // the layout to use

    public GameAdapter(Context context, int resource, List<Game> gameList) {
        super(context, resource, gameList);
        this.context = context;
        this.layoutResource = resource;
        this.gameList = gameList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the fish we are displaying
        Game game = gameList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //view = inflater.inflate(R.layout.fish_row_layout, null);
        view = inflater.inflate(R.layout.game_row, null);

        TextView textViewPlayerOneName = (TextView)view.findViewById(R.id.textViewPlayerOneName);
        TextView textViewPlayerTwoName = (TextView)view.findViewById(R.id.textViewPlayerTwoName);
        TextView textViewScoreOne = (TextView)view.findViewById(R.id.textViewScoreOne);
        TextView textViewScoreTwo = (TextView)view.findViewById(R.id.textViewScoreTwo);
        textViewPlayerOneName.setText(game.getPlayerOneName());
        textViewPlayerTwoName.setText(game.getPlayerTwoName());
        textViewScoreOne.setText(game.getPlayerOneFaction1() + "/" + game.getPlayerOneFaction2() + " (" + game.getPlayerOneScore() + ")");
        textViewScoreTwo.setText(game.getPlayerTwoFaction1() + "/" + game.getPlayerTwoFaction2() + " (" + game.getPlayerTwoScore() + ")");

        return(view);
    }


}

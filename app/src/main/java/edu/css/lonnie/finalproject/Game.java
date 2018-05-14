package edu.css.lonnie.finalproject;
/*
 * This class has all the information needed on a game record
 */
public class Game {
    private long id;

    private String playerOneName;
    private String playerOneFaction1;
    private String playerOneFaction2;
    private String playerOneScore;
    private String playerTwoName;
    private String playerTwoFaction1;
    private String playerTwoFaction2;
    private String playerTwoScore;

    // returns the ID of the comment instance
    public long getId() {
        return id;
    }

    // sets the ID of the comment
    public void setId(long id) {
        this.id = id;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public String getPlayerOneFaction1() {
        return playerOneFaction1;
    }

    public void setPlayerOneFaction1(String playerOneFaction1) {
        this.playerOneFaction1 = playerOneFaction1;
    }
    public String getPlayerOneFaction2() {
        return playerOneFaction2;
    }

    public void setPlayerOneFaction2(String playerOneFaction2) {
        this.playerOneFaction2 = playerOneFaction2;
    }

    public String getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(String playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    public String getPlayerTwoFaction1() {
        return playerTwoFaction1;
    }

    public void setPlayerTwoFaction1(String playerTwoFaction1) {
        this.playerTwoFaction1 = playerTwoFaction1;
    }

    public String getPlayerTwoFaction2() {
        return playerTwoFaction2;
    }

    public void setPlayerTwoFaction2(String playerTwoFaction2) {
        this.playerTwoFaction2 = playerTwoFaction2;
    }

    public String getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(String playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

}

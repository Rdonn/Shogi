package Communication.ServerCommunication;

public class GameRoomData {
  public int GameID;
  public Boolean inProgress;
  public PlayerData PlayerOne;
  public PlayerData PlayerTwo;
  public int turn;
  
  void setGameID(int GameID){
    this.GameID = GameID;
  }
  int getGameID(){
    return GameID;
  }
  void setInProgress(Boolean inProgress){
    this.inProgress = inProgress;
  }
  Boolean getInProgress(){
    return inProgress;
  }
  void setPlayerOne(PlayerData PlayerOne){
    this.PlayerOne = PlayerOne;
  }
  PlayerData getPlayerOne(){
    return PlayerOne;
  }
  void setPlayerTwo(PlayerData PlayerTwo){
    this.PlayerTwo = PlayerTwo;
  }
  PlayerData getPlayerTwo(){
    return PlayerTwo;
  }
  void setTurn(int turn){
    this.turn = turn;
  }
  int getTurn(){
    return turn;
  }
}

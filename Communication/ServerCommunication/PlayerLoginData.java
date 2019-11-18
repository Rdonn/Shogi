package Communication.ServerCommunication;

public class PlayerLoginData {
  public String username;
  public String password;
  
  void setUsername(String username){
    this.username = username
  }
  String getUsername(){
    return username;
  }
  void setPassword(String password){
    this.password = password;
  }
  String getPassword(){
    return password;
  }
  void getLoginData(){
    #Not sure what else is supposed to go here if username and password are already being set/get.
  }
}

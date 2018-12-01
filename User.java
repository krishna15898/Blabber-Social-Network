import java.util.*;
public class User{

  String userName;
  int lastRead = 0;

  public User(String name){userName = name;}
  public User(){userName = null;}

  public String getUserName(){return userName;}
  public int getLastRead(){return lastRead;}
  
  public Boolean equals(User u){
    if(userName.equals(u.getUserName()))
      return true;
    return false;
  }


}

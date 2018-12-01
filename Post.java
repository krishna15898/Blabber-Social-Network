import java.util.*;

public class Post{

  int timeOfPost;
  String postId;
  String typeOfPost;
  User user;
  String text;
  User repliedTo;
  LinkedList<User> readBy = new LinkedList<User>();

  public Post(){
      text = "";
  }

  public Post(int time,String Id,String type, User u, String t){
    timeOfPost = time;
    postId = Id;
    typeOfPost = type;
    user = u;
    text = t;
  }

  public Post(int time,String Id,String type, User u, String t, User ur){
    timeOfPost = time;
    postId = Id;
    typeOfPost = type;
    user = u;
    text = t;
    repliedTo = ur;
  }

  public String getText(){return text;}
  public User getUser(){return user;}
  public String getPostId(){return postId;}
  public int getTimeOfPost(){return timeOfPost;}
  public String getTypeOfPost(){return typeOfPost;}
  public User getRepliedTo(){return repliedTo;}
  public LinkedList<User> getReadBy(){return readBy;}

}

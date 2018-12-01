import java.util.*;

public class Blabber{

  LinkedList<Post> posts = new LinkedList<Post>();
  LinkedList<User> users = new LinkedList<User>();
  LinkedList<Subscription> subscriptions = new LinkedList<Subscription>();
  LinkedList<String> tids = new LinkedList<String>();

  public LinkedList<User> getUsers(){return users;}
  public LinkedList<Post> getPosts(){return posts;}
  public LinkedList<Subscription> getSubscriptions(){return subscriptions;}
  public LinkedList<String> getTids(){return tids;}
  public void publish(Post p){
    posts.addMember(p);
  }

  public Boolean isFeedWorthy(Post p, User reader, int time){

    Boolean result = false;
    if(p.getReadBy().IsMember(reader)) return false;

    if( time > p.getTimeOfPost() && p.getTimeOfPost() >= reader.lastRead ){//subscribed to
      User publisherOfPost = p.getUser();
      for(Subscription s : this.subscriptions){
        if( reader.equals(s.reader) && publisherOfPost.equals(s.publisher)){
          if(s.getTimeOfSubscription() <= p.getTimeOfPost())
            result = true;
            //System.out.println("Subscribed to publisher ");
        }
      }
      if( p.getTypeOfPost().length() == 5  && p.getTypeOfPost().substring(0,5).equals("REPLY") && p.getRepliedTo().equals(reader)){//reply to own post
            result = true;
            //System.out.println("Reply to own post ");
      }
    }
    return result;
  }
}

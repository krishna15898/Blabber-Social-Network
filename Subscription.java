import java.util.*;

public class Subscription{

  User reader;
  User publisher;
  int timeOfSubscription;

  public Subscription(User r, User p, int time){
    reader = r;
    publisher = p;
    timeOfSubscription = time;
  }

  public User getReader(){return reader;}
  public User getPublisher(){return publisher;}
  public int getTimeOfSubscription(){return timeOfSubscription;}

  public Boolean equals(Subscription s){
    if(reader.getUserName().equals(s.getReader().getUserName()) && publisher.getUserName().equals(s.getPublisher().getUserName()) && timeOfSubscription == s.getTimeOfSubscription())
      return true;
    return false;
  }
}

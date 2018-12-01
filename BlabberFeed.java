import java.util.*;
import java.io.*;

public class BlabberFeed{

  Blabber b;
	public BlabberFeed(){
		b = new Blabber();
	}

	public void performAction(String actionMessage){

		String res = "";
		String[] w = actionMessage.split(",");

/*	if(w[0].equals("GETSUBSCRIBERSOF")){
      if(getUserNamed(b.users,w[1]) == null){
        System.out.println("No user named "+w[1]+" is  on blabber");
        return;
      }
      User publisher = getUserNamed(b.users,w[1]);
      if(publisher.getSubscribers().IsEmpty()){
        System.out.println("no subscribers of "+w[1]+" found.");
        return;
      }
      for(Subscription s : publisher.getSubscribers()){
        System.out.println(s.getReader().getUserName()+";");
      }
      return;
		}
    if(w[0].equals("GETSUBSCRIBEDTOOF")){
      if(getUserNamed(b.users,w[1]) == null){
        System.out.println("No user named "+w[1]+" on blabber");
        return;
      }
      User reader = getUserNamed(b.users,w[1]);
      if(reader.getSubscribedTo().IsEmpty()){
        System.out.println("no subscribers of "+w[1]+" found.");
        return;
      }
      for(Subscription s : reader.getSubscribedTo()){
        System.out.println(s.getPublisher().getUserName()+";");
      }
      return;
    }
    if(w[0].equals("GETALLPOSTS")){
			for(Post p : b.posts){
				System.out.println(p.text);
			}
			return;
		}
    if(w[0].equals("GETALLSUBSCRIPTIONS")){
      if(b.getSubscriptions().IsEmpty())
        System.out.println("No active subscriptions found on blabber");
      else{
        System.out.println("Ther are a total of "+b.getSubscriptions().numberOfMembers()+" subscriptions;");
        for(Subscription s : b.getSubscriptions()){
          System.out.println("User "+s.getUser().userName+" at time "+s.timeOfSubscription+";");
        }
      }
      return;
    }
*/
    if(w[0].equals("GETALLUSERS")){
      if(b.getUsers().IsEmpty()){
        System.out.println("There are no registered users on Blabber.");
        return;
      }
      for(User u : b.getUsers())
        System.out.println(u.userName);
      return;
    }

    int time = Integer.parseInt(w[1]);

		if(w[0].equals("PUBLISH")){
			for(User user : b.users){//checking if the user existed.
				if(user.getUserName().equals(w[2])){
					String type = w[3];
					Post postToPublish = new Post();
					if (type.equals("NEW")) {
						String text = w[4];
						String textId = w[5];
            if(b.getTids().IsMember(textId)){
              System.out.println("TextId "+textId+" already taken.");
              return;
            }
            b.getTids().addMember(textId);
						postToPublish = new Post(time, textId, "NEW", user, text);
					}
					else if( type.length() >= 6 && type.substring(0,6).equals("REPOST")){
						String textId = w[4];
            if(b.getTids().IsMember(textId)){
              System.out.println("TextId "+textId+" already taken.");
              return;
            }
            b.getTids().addMember(textId);
						String ptid = type.substring(7,type.length()-1);
						Boolean foundPost = false;
						//System.out.println("inside Repost, ptid: "+ptid);//DELETE
						for (Post p : b.posts) {
						//System.out.println("ptid: "+ptid);//DELETE
							if(p.postId.equals(ptid)){
								//System.out.println("textId: "+textId);//DELETE
								String text = p.getText();
								postToPublish = new Post(time,textId,"REPOST",user,text);
								foundPost = true;
                //adding the user who replied To it in readByList of the post
                p.getReadBy().addMember(user);
							}
						}
						if(!foundPost){
							System.out.println("No post with id: "+ptid+" was found");
							return;
						}
					}
					else if( type.length() >= 5 && type.substring(0,5).equals("REPLY")){
						String text = w[4];
						String textId = w[5];
            if(b.getTids().IsMember(textId)){
              System.out.println("TextId "+textId+" already taken.");
              return;
            }
            b.getTids().addMember(textId);
						String ptid = type.substring(6,type.length()-1);
						//System.out.println("ptid: "+ptid);//DELETE
						Boolean foundPost =false;
						for (Post p : b.posts) {
							if(p.postId.equals(ptid)){
								postToPublish = new Post(time,textId,"REPLY",user,text,p.user);
								foundPost = true;
                //adding the user who replied To it in readByList of the post
                p.getReadBy().addMember(user);
							}
						}
						if(!foundPost){
							System.out.println("No post with id: "+ptid+" found");
							return;
						}
					}
					else{
						System.out.println("Type Of Post unknown: "+type);
						return;
					}
					b.publish(postToPublish);
          System.out.println(actionMessage);
					return;
				}
			}
			System.out.println("No user named "+w[2]+" is on Blabber");
			return;
		}
		else if(w[0].equals("SUBSCRIBE")){

	    if(getUserNamed(b.getUsers(),w[2]) == null) {b.getUsers().addMember(new User(w[2]));}
			if(getUserNamed(b.getUsers(),w[3]) == null) {b.getUsers().addMember(new User(w[3]));}

			User reader = getUserNamed(b.users,w[2]);
      User publisher = getUserNamed(b.users,w[3]);

      if( getSubscriptionNamed(b.subscriptions,w[2],w[3]) != null)
        return;

      Subscription s = new Subscription(reader,publisher,time);

      b.getSubscriptions().addMember(s);
      System.out.println(actionMessage);

		}
		else if(w[0].equals("UNSUBSCRIBE")){
      if(getUserNamed(b.users,w[2]) == null){
        System.out.println("There is no user named "+w[2]+" on blabber");
        return;
      }
      if(getUserNamed(b.users,w[3]) == null){
        System.out.println("There is no user named "+w[3]+" on blabber");
        return;
      }

      if( getSubscriptionNamed(b.subscriptions,w[2],w[3]) == null ){
          System.out.println("Reader "+w[2]+" is not subscribed to "+w[3]+" yet.");
          return;
      }

      Subscription s1 = getSubscriptionNamed(b.getSubscriptions(),w[2],w[3]);
      if(s1 == null) return;
      b.getSubscriptions().deleteMember(s1);
      System.out.println(actionMessage);
      return;
		}
		else if(w[0].equals("READ")){
      User reader = getUserNamed(b.users,w[2]);
      if(! b.getUsers().IsMember(reader)) {
        System.out.println("reader "+w[2]+" is not on Blabber Yet");
        return;
      }
      String feed = "[";
      for(Post p : b.posts){
        if(b.isFeedWorthy(p, reader, time)){
          feed += p.text+",";
          //adding reader to list of readers of the post.
          p.getReadBy().addMember(reader);
        }
      }
      feed = feed.substring(0,feed.length()-1)+ "]";
      if(feed.length() == 1){
        System.out.println("Nothing new to read for "+w[2]);
        return;
      }
      System.out.print(actionMessage);
      System.out.println(feed);
      reader.lastRead = time;
    }

	}

  public User getUserNamed(LinkedList<User> list,String name){
    for(User u : list){
      if(u.userName.equals(name))
        return u;
    }
    return null;
  }
  public Subscription getSubscriptionNamed(LinkedList<Subscription> list,String r,String p){
    for(Subscription s : list){
      if(s.getReader().getUserName().equals(r) && s.getPublisher().getUserName().equals(p))
          return s;
    }
    return null;
  }
}

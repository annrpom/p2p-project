/**
 * 
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.01
 * @date 04-14-20
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Inbox implements Serializable {
	
	ArrayList<Message> messages = new ArrayList<> ();
	
	Set<User> blockedUsers = new HashSet<> ();

	public Inbox() {
		super();
	}
	
	public Inbox(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	/**
	 * @return the messages
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public Message getMessage(int num) {
		return this.messages.get(num);
	}

	public void sendMessage(Message m) {
		//only accept message if user isn't blocked
		if (!blockedUsers.contains(m.fromUser))
			m.toUser.getUserInbox().messages.add(m);
	}

	public void block(User user) {
		blockedUsers.add(user);
	}
	
	public void unblock (User user) {
		blockedUsers.remove(user);
	}
	
	/**
	 * @param m is a message to be added to messages ArrayList
	 */
	public void addMessage(Message m) {
		messages.add(m);
	}

  /*
	 *@param toUsername to who it is sent
	  @param message is what will be sent
	 */
	public void sendMessage(String toUsername, String message) {
		
		User targetUser = Session.activeSession().getStore().getCurrentUsers().get(toUsername);
		Message messageToSend = new Message (targetUser, Session.activeSession().getCurrentUser(), message);
		targetUser.getUserInbox().messages.add(messageToSend);

	}

	@Override
	public String toString() {
		String endString = "Inbox --> \n";
		for(Message m: messages) {
			endString = endString + "From: "+ m.fromUser + ", " + m.getDateTime() + "\n";
		}
		return endString;
	}
}
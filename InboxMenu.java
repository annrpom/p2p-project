import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.03
 * @date 04-29-20
 */
public class InboxMenu extends Menu {

	Inbox inbox;
	
	@Override
	void displayBody() {
		System.out.println(inbox);
		ArrayList<Command> inboxCommands = new ArrayList<Command>();
		for (Message m : inbox.messages) {
			inboxCommands.add(new ViewMessage(m));
		}
		inboxCommands.add(new SendMessage());
		inboxCommands.add(new Session.GoBack());
		super.initializeCommands(inboxCommands);
	}
	
	private class ViewMessage extends Command{
		public Message message;
		
		public ViewMessage(Message m) {
			super("From: "+ m.fromUser + ", " + m.getDateTime());
			this.message = m;
		}
		@Override
		void execute() {
			Session.activeSession().changeMenu(new MessageMenu(message));
		}
	}
	
	private class SendMessage extends Command{
		
		public SendMessage() {
			super("Send message");
		}
		@Override
		void execute() {
			boolean userOK = false;
			boolean commandClose = false;
			String username= "";
			boolean firstTime = true;
			String messageText = "";
			boolean messageOk = false;
			boolean blocked = false;

			do {
				if (!userOK) {

					if(firstTime) {
						userInput.nextLine();
						firstTime = false;
					}

					System.out.println("Make sure the username exists!");
					System.out.print("username of recipient: ");
					username = userInput.nextLine();
					userOK = !(Session.activeSession().checkUsername(username));

					if(userOK){
						User currentUser = Session.activeSession().getCurrentUser();
						User userTo = Session.activeSession().getStore().getCurrentUsers().get(username);
						blocked = userTo.getUserInbox().blockedUsers.contains(currentUser);
						if(blocked){
							System.out.println("Sorry, you are not able to message this user.");
							System.out.println("Press enter to go back to previous menu");
							userInput.nextLine();
							commandClose = true;
						}
					}
				}else if(!messageOk){
					System.out.println("Your message has to be at least 1 char long.");
					System.out.print("Message to recipient: ");
					messageText = userInput.nextLine();
					messageOk = messageText.length() > 0;
				}

				if(userOK && messageOk){
					User fromUser = Session.activeSession().getCurrentUser();
					User toUser = Session.activeSession().getStore().getCurrentUsers().get(username);

					Message newMessage = new Message(toUser, fromUser, messageText);
					Inbox newInbox = toUser.getUserInbox();
					newInbox.messages.add(newMessage);
					toUser.setUserInbox(newInbox);
					System.out.println("Sent! Press enter to return to the previous menu");
					userInput.nextLine();
					commandClose = true;
				}
			} while (!commandClose);
      
		}
	}
	
	public InboxMenu (Inbox currentInbox) {

		this.inbox = currentInbox;
		ArrayList<Command> inboxCommands = new ArrayList<Command>();
		for (Message m : currentInbox.messages) {
			inboxCommands.add(new ViewMessage(m));
		}
		inboxCommands.add(new SendMessage());
		inboxCommands.add(new Session.GoBack());
		super.initializeCommands(inboxCommands);
	}
}

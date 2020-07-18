import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.03
 * @date 04-29-20
 */

public class MessageMenu extends Menu {

	public Message message;
	
	@Override
	void displayBody() {
		System.out.println("Message -->");
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("| Time sent: " + message.getDateTime() + " |");
		System.out.println("--------------------------------");
		System.out.println(message);
		System.out.println();
	}
	
	
	private class ReplyTo extends Command {

		public ReplyTo() {
			super("Reply to message");
		}

		@Override
		void execute() {
			String replyIndicate = "\nRE: " + message.fromUser + " from " + message.dateTime + "\n";
			Scanner currentInput = new Scanner(System.in);
			System.out.print("Message to recipient: ");
			String messageText = replyIndicate + currentInput.nextLine();
			System.out.println("Sent! Press enter to return to the previous menu");
			currentInput.nextLine();
			
			User fromUser = message.toUser;
			User toUser = message.fromUser;
		
			Message newMessage = new Message(toUser, fromUser, messageText);
			Inbox newInbox = toUser.getUserInbox();
			newInbox.messages.add(newMessage);
			toUser.setUserInbox(newInbox);
		}
	}
	
	public MessageMenu(Message message) {
		this.message = message;
		ArrayList<Command>  messageCommands = new ArrayList<Command> ();
		messageCommands.add(new ReplyTo());
		messageCommands.add(new Session.GoBack());
		super.initializeCommands(messageCommands);
	}

}

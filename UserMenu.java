import java.util.ArrayList;
/**
 *
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.03
 * @date 04-29-20
 */
public class UserMenu extends Menu {

	public final User user;
	
	private class SendMessage extends Command {

		public SendMessage() {
			super("Send a message to " + user.getUsername());
		}

		@Override
		void execute() {
			//TODO: open a new message menu pointing to the target user's inbox
		}
		
	}
	
	private class FriendRequest extends Command {

		public FriendRequest() {
			super("Send a friend request to " + user.getUsername());
		}

		@Override
		void execute() {
			//TODO: send a new friend request to the target user
		}
		
	}
	
	private class BlockUser extends Command {

		public BlockUser() {
			super("Block " + user.getUsername());
		}

		@Override
		void execute() {
			//Sets the target user to BLOCKED in the active user's inbox
			Session.activeSession().getCurrentUser().getUserInbox().block(user);

			ArrayList<Command> commandList = new ArrayList<> ();
			
			commandList.add(new UnblockUser ());
			commandList.add(new Session.GoBack ());
			
			UserMenu.this.initializeCommands(commandList);
		}
		
	}
	
	private class UnblockUser extends Command {
		public UnblockUser () {
			super ("Unblock " + user.getUsername());
		}
		
		@Override
		void execute () {
			//Sets the target user to UNBLOCKED in the active user's inbox
			Session.activeSession().getCurrentUser().getUserInbox().unblock(user);
			
			ArrayList<Command> commandList = new ArrayList<> ();
			
			commandList.add(new BlockUser ());
			commandList.add(new Session.GoBack ());
			
			UserMenu.this.initializeCommands(commandList);
		}
	}
	
	
	public UserMenu(User user) {
		this.user = user;

		ArrayList<Command> commandList = new ArrayList<> ();
		
		if (user != Session.activeSession().getCurrentUser()) commandList.add((Session.activeSession().getCurrentUser().isBlocked (user)) ? new UnblockUser () : new BlockUser ());
		commandList.add(new Session.GoBack ());
		
		super.initializeCommands(commandList);
	}

	@Override
	void displayBody() {
		System.out.println("User rating: " + user.getUserHistory().getRating());
	}
	
}
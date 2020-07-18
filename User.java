/**
 * 
 /**
 * 
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.01
 * @date 04-14-20
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class  User implements Searchable, Serializable{

	private String username;
	private String password;
	private Inbox userInbox;
	private History userHistory;
	
	
	private class GoToProfile extends Command {

		public GoToProfile() {
			super("Go to " + getUsername() + "'s profile");
		}

		@Override
		void execute() {
			// TODO Auto-generated method stub
			Session.activeSession().changeMenu(new UserMenu(User.this));
		}
	}
	
	public Command goToProfile () {
		return new GoToProfile ();
	}
	
	public User (String username, String password) {
		this.username = username;
		this.password = password;
		this.userInbox = new Inbox();
		this.userHistory = new History();
	}

	/**
	 * @param newPassword the password to set
	 * @oaram currentPassword the current password
	 */
	public void setPassword(String newPassword, String currentPassword) {
		if (currentPassword.equals(this.password)) this.password = newPassword;
	}

	@Override
	public String toString() {
		return getUsername();
	}

	/**
	 * @return the userInbox
	 */
	public Inbox getUserInbox() {
		return userInbox;
	}

	/**
	 * @param userInbox the userInbox to set
	 */
	public void setUserInbox(Inbox userInbox) {
		this.userInbox = userInbox;
	}

	/**
	 * @return the userHistory
	 */
	public History getUserHistory() { return userHistory; }

	/**
	 * @param password2 other password to compare this to
	 * @return the boolean indicating whether both passwords match
	 */
	public boolean passwordIs(String password2) {
		// TODO Auto-generated method stub
		return password.equals(password2);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean setUsername(String username, String password) {
		
		boolean success = this.passwordIs(password);
		
		if (success) this.username = username;
		return success;
	}

	@Override
	public boolean hasTag(String s) {
		if(s.equals(this.getUsername())) {
			return true;
		}
		return false;
	}

	public boolean isBlocked(User user) {
		return getUserInbox().blockedUsers.contains(user);
	}
}
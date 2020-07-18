/**
 *
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.03
 * @date 04-29-20
 */
public abstract class Command {

	public final String displayText;
	
	public Command (String displayText){
		this.displayText = displayText;
		
	}
	
	abstract void execute ();
}

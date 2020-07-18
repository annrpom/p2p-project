/**
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.01
 * @date 04-14-20
 */
import java.io.*;
/**
 *
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.03
 * @date 04-29-20
 */
public class P2Papp implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 69L;

	// method that serializes, all child classes need to do is call super() constructor with instance of intself
    // TODO question: should we keep everything in one file?
    /*
       As of now, I have decided to pass a String through this method as well as an object so we can differentiate
       between file names for different things, i felt as if it might be easier but i can always be swayed.
     */
    public static boolean saveInfo( P2Papp obj, String fileName ){
        // Serialization of whichever obj we pass through to fileName
        try{
            // This saves object in a file
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            //this is the method that serializes the object
            out.writeObject(obj);
            out.close();
            file.close();
            return true;
        }catch( IOException e ){
            System.out.println("File not found");
            e.printStackTrace();
            return false;
        }
    }

    // method that deserializes
    public static P2Papp pullInfo( P2Papp obj, String fileName ){
        P2Papp currObj = obj;
        try {
            // reading obj from fileName
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            // this is the method that deserializes the object
            currObj = (P2Papp) in.readObject();
            in.close();
            file.close();
            return currObj;
        }catch (FileNotFoundException e) {
        	e.printStackTrace();
        	return null;
        }catch( IOException e ){
            e.printStackTrace();
            System.out.println("IOException is caught");
            return null;
        }catch( ClassNotFoundException e ){
            e.printStackTrace();
            System.out.println("ClassNotFoundException");
            return null;
        }
    }
}

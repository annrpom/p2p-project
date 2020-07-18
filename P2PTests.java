import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.*;
/**
 *
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.03
 * @date 04-29-20
 */

public class P2PTests {

    Map<Integer, Product> currentProducts = new HashMap<>();
    Map<String, User> currentUsers = new HashMap<>();
    Set<String> tags = new HashSet<String>();
    @Test
    public void removing(){
        tags.add("shite");
        User annie = new User("apomp", "1234567");
        Product java = new Product("java", tags, "java beans", 12.50, annie);
        currentProducts.put(1, java);
        currentUsers.put("apomp", annie);
        StoreFront test = new StoreFront(currentProducts, currentUsers);
        Assert.assertTrue(test.removeProduct(java));
        Assert.assertTrue(test.removeUser(annie));
    }

    @Test
    public void sessionTests(){
        Session test = Session.activeSession();
        assertTrue(test.checkUsername("hello"));
        assertTrue(test.checkPassword("thisTr"));
        assertFalse(test.checkPassword(""));
        assertFalse(test.login("hello", "goodbye"));
        assertFalse(test.deleteAccount());
    }

}
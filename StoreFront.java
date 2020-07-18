/**
 * @author Annie Pompa, Arthur Hertz, Maureen Lynch
 * @version 1.02
 * @date 04-29-20
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StoreFront extends P2Papp implements Serializable {

	private static final long serialVersionUID = 3640438943938188273L;
	private Map<Integer, Product> currentProducts;
    private Map<String, User> currentUsers;

    // if there is a file with serialized info then the two maps will pull from those
    // else, we have empty maps (this is for
    public StoreFront() {
    	currentProducts = new HashMap <Integer,Product> ();
    	currentUsers = new HashMap<String,User> ();
    }

    // constructor needed to save all data correctly in serialization process
    public StoreFront( Map<Integer, Product> currentProducts, Map<String, User> currentUsers ){
        this.currentProducts = currentProducts;
        this.currentUsers = currentUsers;
    }

    /*
      Hey Arthur you might find these methods really useful for Session class
      
     */
    public Map<Integer, Product> getCurrentProducts() {
        return currentProducts;
    }

    public void addProduct(Integer productID, Product product) {
        currentProducts.put(productID, product);
    }

    public Map<String, User> getCurrentUsers() {
        return currentUsers;
    }

    public void addUser(String username, User user) {
        currentUsers.put(username, user);
    }

    public void saveInfo(){
        super.saveInfo(new StoreFront(currentProducts, currentUsers), "save.txt");
    }

    /*
       Mo this is for you
     */
    //todo test this
    public boolean removeProduct(Product toBeRemoved){
        int lastSize = currentProducts.size();
        currentProducts.values().remove(toBeRemoved);
        return !((lastSize - currentProducts.size()) == 0);
    }

    public boolean removeUser(User toBeRemoved){
        int lastSize = currentUsers.size();
        currentUsers.values().remove(toBeRemoved);
        return !((lastSize - currentUsers.size()) == 0);
    }

    public boolean purgeUser (User user) {
    	if (!currentUsers.containsValue(user)) return false;
    	
    	
    	ArrayList<Product> badProducts;
    	
		//Remove all bad products from user histories
    	for (User u : currentUsers.values()) {
    		History history = u.getUserHistory();
    		
    		badProducts = new ArrayList<>();
    		
    		for (Product p: history.getSoldItems())
    			if (p.seller == user || p.getBuyer() == user) {
    				badProducts.add(p);
    			}
    		
    		for (Product p : badProducts)
    			history.getSoldItems().remove(p);
    		
    		badProducts = new ArrayList<>();
    		
    		for (Product p: history.purchases)
    			if (p.seller == user || p.getBuyer() == user) {
    				badProducts.add(p);
    			}
    		
    		for (Product p : badProducts)
    			history.purchases.remove(p);
    	}
    	
		//Remove all bad products from current products for sale
		badProducts = new ArrayList<>();
		
		for (Product p : currentProducts.values()) {
			if (p.seller == user || p.getBuyer() == user) {
				badProducts.add(p);
			}
		}
		
		for (Product p : badProducts)
			currentProducts.values().remove(p);
    	
    	return true;
    }
}

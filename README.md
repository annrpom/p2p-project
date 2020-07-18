# Peer-to-Peer Selling
## Mo Lynch, Arthur Hertz, Annie Pompa
This is a C212 group project implementing a selling platform app.

### How to run?
After running the code with our driver class, Session, you will be prompted with a menu in console:
```
0. Register new user
1. Login as existing user
2. Quit the program
```
If you have not already created an account, we will not be able to find you in our database. For first time users, please type the following in the console:
```
1
```
Followed by enter. (This pattern is how you as the user will be able to interact with our program; that is, type your choice in console, followed by enter.)
After you have created an account (usernames and passwords are case sensitive), you will be brought back to the login screen and then able to **Login as existing user**. 

*Note:* Usernames **must** be between 1-9 characters long and passwords must be at least 6 characters. If you do not follow these guidelines, we will simply ask for you to try again.

From there, menu interactions will the same process as the one detailed above!


To elaborate on some features of our main menu, which looks like this:
```
0. Go to Inbox
1. Search Products and Users
2. Browse Products
3. Sell a New Product
4. View Buying and Selling History
5. Sign Out and Return to Login
```
we will have the sections below that summarize what each choice entails.


### Inbox
You are able to message existing users here. This means that the only two ways you will not be able to message another user is if they have blocked you or they do not exist.


### Search Products and Users
Here, you are able to search both products and users by tags. If a product is listed with specific tag properties, they will appear. If a username "tag" is entered, then the username will appear. Both have their respective result box. Below is a demonstration of what the console will look like if the tag *an* was searched (assuming an exists):
```
Search Results
*************************
Users found with tag ->
*************************
an

*************************
Products found with tag ->
*************************
No products found with that tag
------------------------------
```
Since this is the main place where users are able to lookup other users, this is also **the only place** where users are able to block/unblock users. Do this by viewing their profile! You can also produce more narrowed down/organized results using select filter and sort options! In addition to Browse Products, users are also able to buy products here.


### Browse Products
This is where users are able to browse all the products available. We did not hold back here. You can view ev-er-y product. **Ever**. (That has been posted).

### Sell a New Product
Here is where users are able to post products to sell. Everything here is pretty self-explanatory. 


### View History
In here, users are able to view their specific history. This includes two lists of products-one detailing things you have bought and the other one is items that you have sold. Go ahead and try out rating users.


### Sign Out and Return to Login
This is how you leave us (well, kinda).

## This Adroid Studio mobile app provides inventory management systems functionality.

### Documentation:
- [Application Design Proposal](https://github.com/CHenshaw010/Mobile-App-Development/blob/main/App%20Design%20Proposal.pdf)

- [Application Launch Plan](https://github.com/CHenshaw010/Mobile-App-Development/blob/main/App%20Launch%20Plan.pdf)

### Code:
- [Layouts](https://github.com/CHenshaw010/Mobile-App-Development/tree/main/app/src/main/res/layout)

- [Controllers](https://github.com/CHenshaw010/Mobile-App-Development/tree/main/app/src/main/java/com/zybooks/christianhenshawprojecttwo)

### Application Demonstration With Pictures (Note Space Concerns):
<details>
  <summary>Login Screen</summary>

  ![Login Screen](https://github.com/CHenshaw010/Mobile-App-Development/blob/7eb5098b9e37fd98ca3d57bcfbacc54aea39ebba/App%20Demo%20Pictures/Login_Screen.png)

</details>

<details>
  <summary>Add New Item Screen</summary>

  ![Add New Item Screen](https://github.com/CHenshaw010/Mobile-App-Development/blob/7eb5098b9e37fd98ca3d57bcfbacc54aea39ebba/App%20Demo%20Pictures/Add_New_Item_Screen.png)

</details>

<details>
  <summary>Item Inventory Screen</summary>

  ![Item Inventory Screen](https://github.com/CHenshaw010/Mobile-App-Development/blob/7eb5098b9e37fd98ca3d57bcfbacc54aea39ebba/App%20Demo%20Pictures/Item_Inventory_Screen.png)

</details>

<details>
  <summary>Edit Existing Item Screen</summary>

  ![Edit Existing Item Screen](https://github.com/CHenshaw010/Mobile-App-Development/blob/7eb5098b9e37fd98ca3d57bcfbacc54aea39ebba/App%20Demo%20Pictures/Edit_Item_Screen.png)

</details>

<details>
  <summary>Built In SMS Notification Prompt (Informs when an item stock reaches zero)</summary>

  ![Built In SMS Notification Prompt](https://github.com/CHenshaw010/Mobile-App-Development/blob/7eb5098b9e37fd98ca3d57bcfbacc54aea39ebba/App%20Demo%20Pictures/Built_In_SMS_Notifications.png)

</details>

<details>
  <summary>Insufficient Item Quantity Alert</summary>

  ![Insufficient Item Quantity Alert](https://github.com/CHenshaw010/Mobile-App-Development/blob/7eb5098b9e37fd98ca3d57bcfbacc54aea39ebba/App%20Demo%20Pictures/Insufficient_Item_Quantity_Alert.png)

</details>

### Application Description:
The application I have chosen to work on is the inventory app. The main purpose of the app is to streamline the inventory management of items held in a warehouse. It will be used by various employees to enhance their professional operations and increase production efficiency.
- What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?
  - A few screens were necessary to produce a user-centered UI, including a login screen, which also allowed for users to register for an account; an inventory overview database screen; a screen to add inventory items; and a screen to manipulate existing inventory items. The login screen authenticates user login credentials through user database verification. It either allows or denies the user access to the rest of the app based on the provided credentials. Alternatively, users can input login information and register for an account, which creates an entry in the user database for future authentication. The inventory overview screen mirrors the contents of the item database and displays the item name and item quantity. The table is populated with a RecyclerView with each row containing widgets for deleting or editing the respective entry. The edit widget starts the manipulate inventory screen, where users can modify the item database entry, which will be updated immediately. A floating action button is present on the inventory overview screen, which starts the add item inventory process, which creates a new item database entry based on user input.
  - A few features were necessary to support user needs, from SMS notifications to detailed widget layouts. When an inventory database item reaches zero quantity, the respective quantity text view background and text color are dynamically changed to highlight the insufficient inventory. Furthermore, users can accept or deny permissions using a button on the top right of the menu bar. If the permission is accepted, users will receive an SMS notification relaying pertinent inventory information immediately. The UI is heavily designed with user interaction in mind. Frequently pressed buttons are placed in a reachable place, such as the floating action button to add inventory items at the bottom right of the screen, to minimize strain. Additionally, text is sized appropriately to provide accessibility to those with visual impairments. The designs for this app were successful because they maintained a simplistic idea while providing excellent functionality and customization for the users.

### Personal Notes:
How did you approach the process of coding your app? What techniques or strategies did you use?
- I approach designing applications by creating a few iterations of prototypes with basic UIs to identify what features are necessary and where they should be placed. This process is also a good way to understand supplemental features to enhance the user experience, such as added hypertext links or moving functionality to separate screens. From there, I like to take a prototype and compare it to a competitive application already in use to compare and contrast each model. I also consider smaller, more user-focused factors, such as the distance a user will need to reach to click a frequently used button, to redesign the placement of widgets if necessary. Once the final prototype is completed with outside feedback considered, I document each layout thoroughly to assist in proper naming conventions in future development.
- I approach developing applications by first taking the previously mentioned list of requirements and building MVC and UML models. The UML models can be used to create classes and methods to identify how information will be passed throughout the program. From there, each method can be implemented based on the priority that was previously assigned. It is important to implement iteratively and test frequently to ensure no bugs are present or have been added with new code additions. I also continuously lean on coding principles such as inheritance and abstraction to reduce the amount of code present and increase the efficiency of the program. As the complexity of each class increases, it is imperative to include detailed comments to describe the functionality of each method to enhance future implementations and maintainability.

How did you test to ensure your code was functional? Why is this process important, and what did it reveal?
- Testing was conducted frequently and thoroughly throughout development. It was important to test code functionality when any method was added or modified to ensure no bugs already existed or were introduced into the code base. I ensured to test not only the functionality that was just added but the entire app to verify methods were not having issues communicating together. In doing so, a heavy amount of unit testing was utilized throughout development. This process revealed many important issues that needed to be rectified. For instance, when testing the screen to modify existing inventory items, when a new quantity or name was added, it was not being properly updated in the item database. To resolve this issue, I utilized a heavy amount of logging information to identify what information was passed at each communication point. This highlighted that the item returned after searching the database was returning a null value as the read query was not implemented correctly. The logs were instrumental in diagnosing the issue, and after some modifications, the read database functionality and update methods worked appropriately.

Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?
- After considering the entire design process, I had to innovate frequently to overcome challenges, as mobile app development was a new subject to me. For instance, I had trouble understanding how to properly implement the RecyclerView to display the item database. However, after researching and receiving feedback, I was better able to understand how each RecyclerView function was operating to either create or populate each row. I also modified the base RecyclerView methods to pass in more information to the information binding function and the inner class method. This allowed me to perform additional functionality, such as SMS notifications and dynamically updating the item quantity background and text colors for low inventory situations.

In what specific component of your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?
- The specific component of the inventory app that I was particularly successful at creating was the inventory overview screen. This screen is comprised of a database, a RecylerView, each row containing multiple widgets, a floating action bar, and a menu bar in an overall simplistic way. Updates are performed to the RecyclerView dynamically depending on certain pre-defined criteria. It highlights combining many complex topics into a simple UI, providing excellent interactions for users. The code behind this screen has been heavily tested to ensure the requirements have been met. Object-oriented principles and strong coding practices have been followed to reinforce quality and code standards. Furthermore, the code has been exceptionally commented to describe the overall functionality to enhance future additions, scalability, and maintainability.

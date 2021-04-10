project folder:
sunyesta-cs1c-project03/


Brief description of submitted files:

src/stacks/BrowserNavigation.java
    - Simulates a browser's back and forward buttons by recording links that are visited
      and then keeping a stack of "back" links and a stack of "forward" links.
    - Includes main() for running the application.
    

src/stacks/Navigator.java
    - provides three navigation options where the user can:
         1. Set the current link via setCurrentLink(linkName) method.
         2. Replace the current link by going back one link via goBack() method.
         3. Replace the current link by going forward one link via goForward() method.

src/stacks/StackList.java
    - Creates an object of type StackList that works similarly to a linked list for the user to be able to use


resources/inputFile03.txt
    - A line break separated file of links and commands for the computer to run
    - Used to test adding and removing items from back and forward stacks when they are both empty
    - to be used by src/stacks/BrowserNavigation.java

resources/inputFile.txt
    - A line break separated file of links and commands for the computer to run
    - Used for general testing of adding and navigating links
    - to be used by src/stacks/BrowserNavigation.java

resources/popEmptyStackOfLinks.txt
    - A line break separated file of links and commands for the computer to run
    - Used for testing empty back link stacks
    - to be used by src/stacks/BrowserNavigation.java

resources/links.txt
    - A line break separated file of links and commands for the computer to run
    - to be used by src/stacks/BrowserNavigation.java


resources/RUN.txt
    - console output of BrowserNavigation.java

README.txt
    - description of submitted files
package stacks;

/**
 * provides three navigation options where the user can:
 *  1. Set the current link via setCurrentLink(linkName) method.
 *  2. Replace the current link by going back one link via goBack() method.
 *  3. Replace the current link by going forward one link via goForward() method.
 *
 * @author Foothill College, Marian Zlateva
 */
public class Navigator {
    private String currentLink;
    private StackList<String> backLinks;
    private StackList<String> forwardLinks;

    /**
     * Sets currentLink to an empty String
     * creates a new StackList for backLinks
     * creates a new StackList for forwardLinks
     */
    public Navigator(){
        this.currentLink = "";
        this.backLinks = new StackList<>("Back Links");
        this.forwardLinks = new StackList<>("Forward Links");
    }

    /**
     * Sets the current link
     * Places old current link to the back links
     * Clears forwardLinks
     * @param linkName the name of the current link
     */
    public void setCurrentLink(String linkName){
        if(currentLink == null){
            return;
        }

        if(currentLink!=linkName){
            //adds the top link to the backLinks list
            if(currentLink!="") {
                backLinks.push(currentLink);
            }
            //clears the forwardLinks
            forwardLinks.clear();
            //sets new currentLink to passed in variable
            currentLink = linkName;

        }
    }

    /**
     * goes back 1 link if there are any instances in backLinks
     */
    public void goBack(){
        //only operate method if backLinks isn't empty
        if(backLinks.size()==0){
            System.out.println("WARNING FOR OPERATION BELOW: NO BACK LINKS TO GO BACK TO");
            return;
        }
        //pushes current link to forward links
        forwardLinks.push(currentLink);
        //removes first node in backLinks and sets it to currentLink
        currentLink = backLinks.pop();

    }

    /**
     * goes forwards 1 link if there are any instances in forwardLinks
     */
    public void goForward(){
        //only operate method if forwardLinks isn't empty
        if(forwardLinks.size()==0){
            System.out.println("WARNING FOR OPERATION BELOW: NO FORWARD TO GO FORWARD TO");
            return;
        }
        //pushes current link to backLinks
        backLinks.push(currentLink);
        //removes first node in forwardLinks and sets it to currentLink
        currentLink = forwardLinks.pop();
    }

    /**
     * @return the current link
     */
    public String getCurrentLink() {
        return currentLink;
    }

    /**
     * @return the StackList of backLinks
     */
    public StackList<String> getBackLinks() {
        return backLinks;
    }

    /**
     * @return the StackList of forwardLinks
     */
    public StackList<String> getForwardLinks() {
        return forwardLinks;
    }
}


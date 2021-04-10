package lazyTrees;

import java.util.*;


/**
 * Creates a lazy search tree for use in the super market class
 *
 * @param <E> the comparable data type of the objects that are being stored in the nodes
 * @author Marian Zlateva
 */
public class LazySearchTree<E extends Comparable<? super E>> implements Cloneable {
    //the size of the tree including soft deleted nodes
    protected int mSizeHard;

    //the size of the tree not including soft deleted nodes
    protected int mSize;

    //the root of the tree
    protected LazySTNode mRoot;

    /**
     * initializes the variables by calling clear()
     */
    public LazySearchTree() {
        clear();
    }

    /**
     * @return true if the tree is empty / false if it is not empty
     */
    public boolean empty() {
        return (mSize == 0);
    }

    /**
     * @return soft size of the tree
     */
    public int size() {
        return mSize;
    }

    /**
     * clears tree and sets all sizes to 0
     */
    public void clear() {
        mSize = 0;
        mSizeHard = 0;
        mRoot = null;
    }


    /**
     * finds the minimum value in the tree (soft)
     *
     * @return the minimum value of the tree
     */
    public E findMin() {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMin(mRoot).data;
    }

    /**
     * finds the minimum value in the tree (hard)
     *
     * @return the minimum value of the tree
     */
    public E findMinHard() {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMinHard(mRoot).data;
    }

    /**
     * finds the max value in the tree (soft)
     *
     * @return the max value of the tree
     */
    public E findMax() {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMax(mRoot).data;
    }

    /**
     * finds the max value in the tree (hard)
     *
     * @return the max value of the tree
     */
    public E findMaxHard() {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMaxHard(mRoot).data;
    }


    /**
     * finds the node that holds the object passed in (looks through soft deleted nodes)
     *
     * @param x the object contained in the node that is being looked for
     * @return the node that contains the object
     */
    public E find(E x) {
        LazySTNode resultNode;
        resultNode = find(mRoot, x, true);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }


    /**
     * finds the node that holds the object passed in (does not look through soft deleted nodes)
     *
     * @param x the object contained in the node that is being looked for
     * @return true if the node is contained in the tree / false if the node is not contained in the tree
     */
    public boolean contains(E x) {
        //System.out.println("x: "+x);
        return find(mRoot, x, true) != null;
    }

    /**
     * inserts a node into the tree
     *
     * @param x the data of the node being inserted
     * @return true if the node was successfully inserted into the tree/ false if not
     */
    public boolean insert(E x) {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * removes a node into the tree (soft)
     *
     * @param x the data of the node being removed
     * @return true if the node was successfully removed into the tree/ false if not
     */
    public boolean remove(E x) {
        int oldSize = mSize;
        //true is the size changed which means that the node was successfully deleted
        remove(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * removes a node into the tree (hard)
     *
     * @param x the data of the node being removed
     * @return true if the node was successfully removed into the tree/ false if not
     */
    public boolean removeHard(E x) {
        int oldSize = mSize;
        //true is the size changed which means that the node was successfully deleted
        removeHard(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * hard traverses through the tree
     *
     * @param func the function being called throughout the traversal
     * @param <F>  the data type of the traverser
     */
    public <F extends Traverser<? super E>>
    void traverseHard(F func) {
        traverse(func, mRoot, false);
    }

    /**
     * soft traverses through the tree
     *
     * @param func the function being called throughout the traversal
     * @param <F>  the data type of the traverser
     */
    public <F extends Traverser<? super E>>
    void traverseSoft(F func) {
        traverse(func, mRoot, true);
    }

    /**
     * clones tree
     *
     * @return cloned tree
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        LazySearchTree<E> newObject = (LazySearchTree<E>) super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;

        return newObject;
    }

    /**
     * Hard removes soft deleted nodes in the tree
     * @return true if tree size was changed / false if tree size was not changed
     */
    public boolean collectGarbage() {
        int oldSize = mSizeHard;
        collectGarbage(mRoot);

        return (oldSize == mSizeHard) ? true : false;
    }


    // private helper methods ----------------------------------------

    /**
     * finds object with minimum value in the tree (soft)
     *
     * @param root root of the tree
     * @return object with minimum value in the tree
     */
    protected LazySTNode findMin(LazySTNode root) {
        if (root == null) {
            return null;
        }
        LazySTNode tmp = findMin(root.lftChild);
        if (tmp != null) {
            return tmp;
        }
        if (!root.deleted) {
            return root;
        }
        return findMin(root.rtChild);
    }

    /**
     * finds object with minimum value in the tree (hard)
     *
     * @param root root of the tree
     * @return object with minimum value in the tree
     */
    protected LazySTNode findMinHard(LazySTNode root) {
        if (root == null)
            return null;
        if (root.lftChild == null)
            return root;
        return findMinHard(root.lftChild);
    }

    /**
     * finds object with maximum value in the tree
     *
     * @param root root of the tree
     * @return object with maximum value in the tree
     */
    protected LazySTNode findMax(LazySTNode root) {
        if (root == null) {
            return null;
        }
        LazySTNode tmp = findMax(root.rtChild);
        if (tmp != null) {
            return tmp;
        }
        if (!root.deleted) {
            return root;
        }
        return findMax(root.lftChild);
    }

    /**
     * finds object with maximum value in the tree (hard)
     *
     * @param root root of the tree
     * @return object with maximum value in the tree
     */
    protected LazySTNode findMaxHard(LazySTNode root) {
        if (root == null)
            return null;
        if (root.rtChild == null)
            return root;
        return findMaxHard(root.rtChild);
    }

    /**
     * inserts a node in the tree
     *
     * @param root root of tree
     * @param x    data type of the inserted node
     * @return the inserted node
     */
    protected LazySTNode insert(LazySTNode root, E x) {
        int compareResult;  // avoid multiple calls to compareTo()
        E data = null;
        if (root != null) {
            data = root.data;
        }
        //System.out.println("performing insertion for: " + x +"\troot is:"+data);
        //once you get to an empty spot, insert the node into the tree
        if (root == null) {
            mSize++;
            mSizeHard++;
            LazySTNode newNode = new LazySTNode(x, null, null);

            //System.out.println("added: " + x + "\tdeleted = " + newNode.deleted);
            return newNode;
        }
        compareResult = x.compareTo(root.data);
        if (compareResult < 0) {
            root.lftChild = insert(root.lftChild, x);

        } else if (compareResult > 0) {
            root.rtChild = insert(root.rtChild, x);
        } else if (root.deleted == true) {
            mSize++;
            root.deleted = false;
            //System.out.println("added: " + x + "\tdeleted = " + root.deleted);
        }


        return root;
    }

    /**
     * removes a node from the tree (soft)
     *
     * @param root the root of the tree
     * @param x    the data of the node you are removing
     */
    protected void remove(LazySTNode root, E x) {
        LazySTNode node = find(root, x, true);
        if (node != null && node.deleted == false) {
            node.deleted = true;
            //System.out.println("size: " + mSize);
            mSize--;
        }
    }

    /**
     * removes a node from the tree (hard)
     *
     * @param root the root of the tree
     * @param x    the data of the node you are removing
     */
    protected LazySTNode removeHard(LazySTNode root, E x) {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            root.lftChild = removeHard(root.lftChild, x);
        else if (compareResult > 0)
            root.rtChild = removeHard(root.rtChild, x);

            // found the node
        else if (root.lftChild != null && root.rtChild != null) {
            root.data = findMinHard(root.rtChild).data;
            root.deleted = findMinHard(root.rtChild).deleted;
            mSize++;
            root.rtChild = removeHard(root.rtChild, root.data);
        } else {
            if (root.deleted == false) {
                mSize--;
            }

            mSizeHard--;


            root = (root.lftChild != null) ? root.lftChild : root.rtChild;




        }
        return root;
    }

    /**
     * traverses through the tree
     *
     * @param func     the function that you are using when you traverse
     * @param treeNode the root of the tree
     * @param soft     bool value for whether you want to do a soft or hard traversal
     * @param <F>      data type of the class of the Traverser function
     */
    protected <F extends Traverser<? super E>>
    void traverse(F func, LazySTNode treeNode, Boolean soft) {
        if (treeNode == null)
            return;

        traverse(func, treeNode.lftChild, soft);


        if ((soft && treeNode.deleted == false) || !soft) {
            //System.out.println(treeNode.data + ":\tdeleted = " + treeNode.deleted);
            func.visit(treeNode.data);
        }
        traverse(func, treeNode.rtChild, soft);
    }

    /**
     * finds a node in the tree
     *
     * @param root     root of the tree
     * @param x        the object held in the node that the program is trying to find
     * @param softFind bool value for whether you want to do a soft or hard find
     * @return the found node
     */
    protected LazySTNode find(LazySTNode root, E x, boolean softFind) {
        // avoid multiple calls to compareTo()
        int compareResult;

        //if there is no root then you don't go through the tree
        if (root == null)
            return null;

        //goes in order in the tree looking for the correct node
        compareResult = x.compareTo(root.data);
        LazySTNode foundNode = null;
        if (compareResult < 0) {
            root = find(root.lftChild, x, softFind);
        } else if (compareResult > 0)
            root = find(root.rtChild, x, softFind);

        //this is where the recursion reaches the end of the tree and finds nothing
        if (root == null) {
            return null;
            //this checks if the node is soft deleted and soft finding is on
        } else if (softFind == true && root.deleted == true) {
            return null;
            //returns the found node up the recursive function
        } else {
            return root;
        }

    }

    /**
     * clones the subtree
     *
     * @param root the root of the tree
     * @return a clone of the subtree
     */
    protected LazySTNode cloneSubtree(LazySTNode root) {
        LazySTNode newNode;
        if (root == null)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new LazySTNode
                (
                        root.data,
                        cloneSubtree(root.lftChild),
                        cloneSubtree(root.rtChild)
                );
        return newNode;
    }


    /**
     * @return the hard size of the tree
     */
    public int sizeHard() {
        return mSizeHard;
    }

    /**
     * used for defining the nodes object that goes into the tree
     */
    private class LazySTNode {
        // use public access so the tree or other classes can access members
        private LazySTNode lftChild, rtChild;
        private E data;
        private LazySTNode myRoot;  // needed to test for certain error
        private boolean deleted;

        /**
         * instantiates the global variables in the class
         *
         * @param d   the data of the node
         * @param lft the left node
         * @param rt  the right node
         */
        public LazySTNode(E d, LazySTNode lft, LazySTNode rt) {
            lftChild = lft;
            rtChild = rt;
            data = d;
            deleted = false;
        }

        /**
         * default constructor for the class that gives the node a null data, left child, and right child
         */
        public LazySTNode() {
            this(null, null, null);
        }


    }

    /**
     * recursive method that hard removes soft deleted nodes from the tree
     * @param root the root of the tree
     */
    private void collectGarbage(LazySTNode root) {
        if (root == null)
            return;

        collectGarbage(root.lftChild);


        collectGarbage(root.rtChild);

        if (root.deleted) {
            removeHard(root.data);
        }
    }

}

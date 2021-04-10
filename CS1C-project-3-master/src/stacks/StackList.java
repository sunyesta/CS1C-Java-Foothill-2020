package stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Creates an object of type StackList that works similarly to a linked list for the user to be able to use
 * @param <T> the data type of the data that will be passed into the StackList
 * @author Foothill College, Marian Zlateva
 */
public class StackList <T> implements Iterable<T>{

    private String name;
    private Node top = null;
    private int size = 0;

    /**
     * sets the name of the StackList
     * @param name name of the StackList
     */
    public StackList(String name){
        this.name = name;
    }

    /**
     * creates a new StackIterator
     * @return a StackIterator for the user to be able to easily iterate through the stackList
     */
    @Override
    public Iterator<T> iterator(){
        return new StackIterator();
    }


    /**
     * takes a generic item as the argument and adds the item to the top of the stack.
     * @param item the item you want to add to the stackList
     */
    public void push(T item){
        Node newNode = new Node(item);
        top = newNode;
        size++;
    }


    /**
     * receives no arguments and removes the item from the top of the stack. This method should return the generic item popped.
     * @return the removed node
     */
    public T pop(){
        Node removedNode = top;
        top = top.nextNode;
        size--;
        return removedNode.getData();
    }


    /**
     * looks at the top of the stack and returns a generic type for the data seen at the top of the stack. The item should not be removed from the top of the stack.
     * NOTE: If the stack is empty, returns null.
     * @return top node data
     */
    public T peek(){
        return top.getData();
    }


    /**
     * discards all object references from the linked list to "empty" this StackList instance
     */
    public void clear(){
        top = null;
        size = 0;
    }

    /**
     * @return name of the stack passed in by the Navigator class in addition to the number of links in the stack and the contents of the stack
     */
    public String toString(){
        return  name + ":\n\tNode Count = " + size()+"\n\tNodes: "+getNodeList();
    }


    /**
     * checks if stackList is empty
     * @return true if stackList is empty, false if stackList is not empty
     */
    public boolean isEmpty(){
        if(size==0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return the number of elements in the stack
     */
    public int size(){
        return size;
    }

    /**
     * makes a string containing all of the nodes in the stack
     * @return the string containing all of the nodes in the stack
     */
    public String getNodeList(){
        String nodeList = "[";
        Iterator iterator = this.iterator();
        if(iterator.hasNext()){
            while (iterator.hasNext()){
                nodeList += iterator.next()+", ";
            }
            //if nodes are added the last comma gets erased
            nodeList = nodeList.substring(0,nodeList.length()-2);
        }

        nodeList += "]";

        return nodeList;
    }

    /**
     * An inner class that is used to create a new instance of a Node in the StackList class
     * Contains the data of the node and a reference to the previous node
     */
    class Node{
        private T data;
        private Node nextNode;

        public Node(T data){
            this.data = data;
            this.nextNode = top;
        }

        public Node getNextNode(){
            return nextNode;
        }

        public T getData(){
            return data;
        }
    }

    /**
     * An inner class that implements Iterator in order to create an iterator for the StackList class
     */
    class StackIterator implements Iterator{

        Node currentNode = null;

        /**
         * @return true if there is a next node, false if there is no next node
         */
        @Override
        public boolean hasNext() {


            if(currentNode==null && top!=null){
                //return true if the current node doesn't equal anything but the head does
                return true;
            }else if(currentNode!=null){
                //return true if there is a node connected to current node
                //return false if there is no node connected to current node
                return currentNode.getNextNode()!=null;
            }else{
                // return false if both current and head are equal to null
                return false;
            }


        }

        /**
         * sets the iterator's current node to the next node
         * @return the data of the new node
         */
        @Override
        public T next(){
            if(currentNode==null && top!=null){
                //set current node to the top node if there is no current node but there is a top node
                currentNode = top;
                return top.getData();
            }else if(currentNode!=null){
                //set current node to the next node if there is a current node
                currentNode = currentNode.getNextNode();
                return  currentNode.getData();
            }else{
                // throw error if there is no current or top node
                throw new NoSuchElementException();
            }
        }


    }

}


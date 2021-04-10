package lazyTrees;

import cs1c.SongEntry;

/**
 * Prints objects for traversing over the LazySearchTree
 * @param <T> The data type of the objects inside of the tree
 * @author Marian Zlateva
 */
public class PrintObject<T> implements Traverser<T>{


    public String toString(){
        return "";
    }

    @Override
    public void visit(T x) {
        if(x instanceof SongEntry){
            System.out.println(x);
        }else{
            System.out.print(x+" ");
        }

    }
}

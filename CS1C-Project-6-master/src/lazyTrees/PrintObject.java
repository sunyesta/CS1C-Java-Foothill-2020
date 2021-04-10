package lazyTrees;



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
        System.out.print(x+" ");
    }
}
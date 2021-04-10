package lazyTrees;

/**
 * The traverser used for the LazySearchTree
 * @param <E> the data type of the object in the tree
 */
public interface Traverser<E>
{
    public void visit(E x);
}
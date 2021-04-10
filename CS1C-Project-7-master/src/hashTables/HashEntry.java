package hashTables;

// HashEntry class for use by FHhashQP -----------------------

/**
 * the hash entry of the hash table
 * @param <E> the data type of the data held in the hash entry
 * @author Foothill College, Bita M, Marian Zlateva
 */
public class HashEntry<E>
{
    public E data;
    public int state;

    /**
     * instatiates the data and state variables
     * @param x the data in the hash entry
     * @param st the state of the hash entry
     */
    public HashEntry( E x, int st )
    {
        data = x;
        state = st;
    }

    /**
     * makes an empty hash entry
     */
    public HashEntry()
    {
        this(null, FHhashQP.EMPTY);
    }
}
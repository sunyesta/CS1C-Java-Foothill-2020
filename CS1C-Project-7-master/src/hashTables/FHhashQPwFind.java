package hashTables;

import java.util.NoSuchElementException;

/**
 * A child class of FHhashQP that allows searching through the hashtable with keys
 * @param <KeyType> the data type of the key
 * @param <E> the data type of the object
 * @author Foothill College, Marian Zlateva
 */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends FHhashQP<E> {


    /**
     * returns the found object, or throws a java.util.NoSuchElementException
     * @param key the key
     * @return the object with the corresponding key
     */
    public E find(KeyType key) {
        int location = findPosKey(key);
        HashEntry<E> retrievedData = mArray[location];

        if (retrievedData.state == ACTIVE) {
            return retrievedData.data;//returns the found object.
        } else {
            throw new NoSuchElementException();
        }

    }


    /**
     * provides a trivial modification to myHash() which uses the key rather than the object, to hash.
     * @param key the key
     * @return the hash key to be used by find pos key
     */
    protected int myHashKey(KeyType key) {
        int hashVal;

        hashVal = key.hashCode() % mTableSize;
        if (hashVal < 0)
            hashVal += mTableSize;

        return hashVal;
    }

    /**
     * provides trivial modification to findPos() which uses the key rather than the object, to get a position.
     * @param key the key
     * @return the index of the object with the corresponding key
     */
    protected int findPosKey(KeyType key) {
        int kthOddNum = 1;
        int index = myHashKey(key);

        while (mArray[index].state != EMPTY
                && !(mArray[index].data.compareTo(key) == 0)) {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if (index >= mTableSize)
                index -= mTableSize;
        }
        return index;
    }


}


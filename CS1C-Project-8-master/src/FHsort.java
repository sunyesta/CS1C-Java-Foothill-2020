/**
 * Class of many sort methods
 */
public class FHsort
{

    /**
     * insertion sort method
     * @param a the array being sorted
     * @param <E> the data type of the array
     */
    public static < E extends Comparable< ? super E > >
    void insertionSort(E[] a)
    {
        int k, pos, arraySize;
        E tmp;

        arraySize = a.length;
        for(pos = 1; pos < arraySize; pos++ )
        {
            tmp = a[pos];
            for(k = pos; k > 0 && tmp.compareTo(a[k-1]) < 0; k-- )
                a[k] = a[k-1];
            a[k] = tmp;
        }
    }


    /**
     * shell sort #1 -- using shell's outer loop
     * @param a the array being sorted
     * @param <E> the data type of the array
     */
    public static < E extends Comparable< ? super E > >
    void shellSort1(E[] a)
    {
        int gap = 1;
        int k, pos, arraySize;
        E tmp;

        arraySize = a.length;
        for (gap = arraySize/2;  gap > 0;  gap /= 2)
            for(pos = gap; pos < arraySize; pos++ )
            {
                tmp = a[pos];
                for(k = pos; k >= gap && tmp.compareTo(a[k-gap]) < 0; k -= gap )
                    a[k] = a[k-gap];
                a[k] = tmp;
            }
    }

    // mergesort and helpers -------------------------------------------
    // input array 1:  client[leftPos] ... client[rightPos-1]
    // input array 2:  client[rightPos] ... client[rightStop]
    // working[] array supplied by client to avoid local allocation

    /**
     * merge sort method
     * @param client array being sorted
     * @param working array supplied by client to avoid local allocation
     * @param leftPos start of left side
     * @param rightPos start of right side
     * @param rightStop stop of right side
     * @param <E> data type of array being sorted
     */
    protected static < E extends Comparable< ? super E > >
    void merge(E[] client, E[] working,
               int leftPos, int rightPos, int rightStop)
    {
        int leftStop, workingPos, arraySize;

        workingPos = leftPos;
        leftStop = rightPos - 1;
        arraySize = rightStop - leftPos + 1;

        // as soon as we reach the end of either input array, stop
        while(leftPos <= leftStop && rightPos <= rightStop)
            if(client[leftPos].compareTo(client[rightPos]) < 0 )
                working[workingPos++] = client[leftPos++];
            else
                working[workingPos++] = client[rightPos++];

        // merge is over; copy the remainder of one or the other input array
        while(leftPos <= leftStop)
            working[workingPos++] = client[leftPos++];
        while( rightPos <= rightStop )
            working[workingPos++] = client[rightPos++];

        // copy back into client array
        for( ; arraySize > 0; arraySize--, rightStop-- )
            client[rightStop] = working[rightStop];
    }



    /**
     * mergesort internal function
     * @param a array being sorted
     * @param working array supplied by client to avoid local allocation
     * @param start where the start is
     * @param stop where the end is
     * @param <E> data type of the array being sorted
     */
    protected static < E extends Comparable< ? super E > >
    void mergeSort(E[] a, E[] working, int start, int stop)
    {
        int rightStart;

        if (stop - start < 1)
            return;

        rightStart = (start + stop)/2 + 1;
        mergeSort(a, working, start, rightStart - 1);
        mergeSort(a, working, rightStart, stop);
        merge(a, working, start, rightStart, stop);
    }

    // mergesort public driver

    /**
     * the public driver of merge sort
     * @param a the array being sorted
     * @param <E> the datatype of the array being sorted
     */
    public static < E extends Comparable< ? super E > >
    void mergeSort(E[] a)
    {
        if (a.length < 2)
            return;

        E[] working = (E[])new Comparable[a.length];
        mergeSort(a, working, 0, a.length - 1);
    }

    // heapsort and helpers -------------------------------------------
    // percolateDown()

    /**
     * percolates down
     * @param a the array being sorted
     * @param hole the hole
     * @param arraySize the size of the array
     * @param <E> the data type of the array
     */
    protected static < E extends Comparable< ? super E > >
    void percolateDown(E[] a, int hole, int arraySize)
    {
        int child;
        E tmp;

        for( tmp = a[hole]; 2 * hole + 1 < arraySize; hole = child )
        {
            child = 2 * hole + 1;
            // if 2 children, get the GREATER of the two (because MAX heap)
            if( child < arraySize - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if( tmp.compareTo(a[child]) < 0 )   // MAX heap, not min heap
                a[hole] = a[child];
            else
                break;
        }
        a[hole] = tmp;
    }

    /**
     * heapsort public driver
     * @param a the array being sorted
     * @param <E> the datatype of the array
     */
    // heapsort public driver
    public static < E extends Comparable< ? super E > >
    void heapSort(E[] a)
    {
        int k, arraySize;
        E temp;

        // order the array using percolate down
        arraySize = a.length;
        for(k = arraySize/2; k >= 0; k-- )
            percolateDown(a, k, arraySize);

        // now remove the max element (root) and place at end of array
        for(k = arraySize - 1; k > 0; k-- )
        {
            // "remove" by placing at end of array
            temp = a[0];
            a[0] = a[k];
            a[k] = temp;
            percolateDown( a, 0, k );  // k represents the shrinking array size
        }
    }

    // quicksort and helpers -------------------------------------------
    // median3 sorts a[left], a[center] and a[right].
    // it leaves the smallest in a[left], the largest in a[right]
    // and median (the pivot) is moved "out-of-the-way" in a[right-1].
    // (a[center] has what used to be in a[right-1])

    /**
     *  sorts a[left], a[center] and a[right].
     * @param a the array bing sorted
     * @param left the left index of the array
     * @param right the right index of the array
     * @param <E> the datatype of the array
     * @return the median of the array's three data points
     */
    protected static < E extends Comparable< ? super E > >
    E median3(E[] a, int left, int right)
    {
        int center;
        E tmp;

        // swaps are done in-line for speed;  each compound line is a swap
        center = (left + right) / 2;
        if(a[center].compareTo(a[left]) < 0)
        { tmp = a[center]; a[center] = a[left]; a[left] = tmp; }
        if(a[right].compareTo(a[left]) < 0)
        { tmp = a[right]; a[right] = a[left]; a[left] = tmp; }
        if(a[right].compareTo(a[center]) < 0)
        { tmp = a[right]; a[right] = a[center]; a[center] = tmp; }

        tmp = a[center]; a[center] = a[right-1]; a[right-1] = tmp;

        return a[right - 1];
    }

    protected static int QS_RECURSION_LIMIT = 15;

    /**
     * sets the recursion limit of the array
     * @param newLim the new limit
     * @return true if the new limit was succesfully applied, false if it wasn't
     */
    public static boolean setRecursionLimit(int newLim)
    {
        if (newLim < 2 || newLim > 1000)
            return false;
        QS_RECURSION_LIMIT = newLim;
        return true;
    }

    /**
     * quick sort method
     * @param a the array being sorted
     * @param left the left index
     * @param right the right index
     * @param <E> the datatype of the array being sorted
     */
    protected static < E extends Comparable< ? super E > >
    void quickSort(E[] a, int left, int right)
    {
        E pivot, tmp;
        int i, j;

        if( left + QS_RECURSION_LIMIT <= right )
        {
            pivot = median3(a, left, right);

            for(i = left, j = right - 1; ; )
            {
                while( a[++i].compareTo(pivot) < 0 )
                    ;
                while( pivot.compareTo(a[--j]) < 0)
                    ;
                if(i < j)
                { tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp; }

                else
                    break;
            }

            // restore pivot
            tmp = a[i]; a[i] = a[right - 1]; a[right - 1] = tmp;

            // recursive calls on smaller sub-groups
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        }
        else
            // non-recursive escape valve - insertion sort
            insertionSort(a, left, right);
    }

    // private insertion sort that works on sub-arrays --------------

    /**
     * method for insertion sort
     * @param a array being sorted
     * @param start start of array
     * @param stop end of array
     * @param <E>  data type of the array
     */
    protected static < E extends Comparable< ? super E > >
    void insertionSort(E[] a, int start, int stop)
    {
        int k, pos;
        E tmp;

        // we are not testing for ranges to keep times down - private so ok
        for(pos = start + 1; pos <= stop; pos++ )
        {
            tmp = a[pos];
            for(k = pos; k > 0 && tmp.compareTo(a[k-1]) < 0; k-- )
                a[k] = a[k-1];
            a[k] = tmp;
        }
    }

    // public quicksort

    /**
     * quick sort method
     * @param a array being sorted
     * @param <E>  data type of the array
     */
    public static < E extends Comparable< ? super E > >
    void quickSort( E[] a )
    {
        quickSort(a, 0, a.length - 1);
    }
}
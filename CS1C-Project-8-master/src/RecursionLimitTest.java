import java.util.ArrayList;

/**
 * tests generates data for the time over recursion limit graphs
 *
 * @author Marian Zlateva
 */
public class RecursionLimitTest {
    static boolean first = true;

    /**
     * generates an Integer array of random numbers
     *
     * @param size the size of the array
     * @return the new array
     */
    public static Integer[] generateRandomNumberArray(int size) {
        Integer[] randomNumbers = new Integer[size];

        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = ((int) (Math.random() * 10000000));
        }
        return randomNumbers;
    }

    /**
     * prints an array in a comma seperated list with its name in front
     *
     * @param array the array you are printing
     * @param name  the name of the array
     */
    public static void printArray(ArrayList array, String name) {


        String printedArray = "";


        for (int i = 0; i < array.size(); i++) {
            printedArray += array.get(i) + ",";

        }

        System.out.println(name + "," + printedArray);

    }

    /**
     * copies the array
     *
     * @param array the array you are copying
     * @return the copied array
     */
    public static Integer[] copyArray(Integer[] array) {
        Integer[] newArray = new Integer[array.length];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = new Integer(array[i]);
        }
        return newArray;
    }

    /**
     * prints a list of duration values, each to a different recursion limit
     *
     * @param size the size of the array
     */
    public static void investigateRecursionLimit(int size) {
        Integer[] randomNumbers = generateRandomNumberArray(size);

        ArrayList<Integer> recursionLimitsInOrder = new ArrayList<>();
        ArrayList<Double> timesInOrder = new ArrayList<>();

        for (int i = 2; i <= 300 - 2; i += 2) {
            double[] totalTimes = new double[3];
            for (int j = 0; j < 3; j++) {
                Integer[] copyOfRandomNumbers = copyArray(randomNumbers);
                FHsort.setRecursionLimit(i);
                long startTime = System.nanoTime();
                FHsort.quickSort(copyOfRandomNumbers);
                long endTime = System.nanoTime();

                totalTimes[j] = (endTime - startTime) / (Math.pow(10, 9));
            }
            double totalTime = (totalTimes[0] + totalTimes[1] + totalTimes[2]) / 3;

            recursionLimitsInOrder.add(i);
            timesInOrder.add(totalTime);


        }

        if (first) {
            first = false;
            printArray(recursionLimitsInOrder, "recursion limits");
        }
        printArray(timesInOrder, "array of size " + size);


    }

    /**
     * generates multiple comma separated lists from investigateRecursionLimit(int size) with sizes up to 10 million
     */
    public static void main(String[] args) {
        final int MINSIZE = 20000;
        final int MAXSIZE = 10000000;
        final int NUMBER_OF_INTERVALS = 20;


        for (int i = MINSIZE; i < MAXSIZE; i += ((MAXSIZE - MINSIZE) / NUMBER_OF_INTERVALS)) {
            investigateRecursionLimit(i);
        }
        investigateRecursionLimit(MAXSIZE);


    }

}
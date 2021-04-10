package subsetsum;
import cs1c.SongEntry;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Finds the subset that is as close to the input limit as possible
 *
 * @author CS1C, Foothill College, Marian Zlateva
 * @version 2.0
 */
public class SubsetSum {

    /**
     * passes values into the computeSubset method
     * @param masterList is used to be fed into the computeSubset() method
     * @param budget is used to be fed into the computeSubset() method
     * @return a new ArrayList of Double that has a sum budget as close to the input budget as possible
     */
    public static ArrayList<Double> findSubset(ArrayList<Double> masterList, Double budget){
        return computeSubset(masterList,budget);
    }


    /**
     * passes values into the computeSubset method
     * @param masterList is used to be fed into the computeSubset() method
     * @param duration is used to be fed into the computeSubset() method
     * @return a new ArrayList of SongEntry that has a sum duration as close to the input duration as possible
     */
    public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry>masterList,double duration){
        return computeSubset(masterList,duration);
    }

    /**
     * Main function for computing the new subsets
     * @param masterList the main list of objects used for passing into the sum() method and create list method
     *                   also used to determine if optimization is on
     * @param limit used as a param in sum() to check if each of the new subsets fits within the budget
     * @param <T> used as the type of object inside the masterList and the Arraylist being returned
     *            also used in the sum() method to show sum how to extract the sum of the items in the subset
     * @return a new ArrayList of T that has a sum limit as close to the input limit as possible
     */
    public static <T> ArrayList<T> computeSubset(ArrayList<T>masterList, double limit){
        if(limit<0 || (limit>sumMasterSet(masterList) && masterList.get(0) instanceof SongEntry)){
            return new ArrayList<T>();
        }else if(limit>sumMasterSet(masterList)){
            return masterList;
        }
        final int OPTIMIZATION_THRESHOLD = 1000;
        boolean optimize;
        if(masterList.size()<OPTIMIZATION_THRESHOLD){
            optimize = false;
        }else{
            optimize = true;
        }
        ArrayList<ArrayList<Integer>>allPossibleSets = new ArrayList<>();
        allPossibleSets.add(new ArrayList<Integer>());
        ArrayList<Integer> bestList = allPossibleSets.get(0);
        //loop through sorted base array and add the current object to each of the new objects in an inner loop
        //break from the inner loop if the set is too big
        //if the sum of the object costs in the current set are bigger than the bestListIndex, then set bestListIndex to the current set
        //break from both loops if the limit matches

        outerLoop:
        for(int i=0;i<masterList.size();i++){

            int newSizeofAllPossibleSets = allPossibleSets.size();
            //loops through a controlled amount b/c the size of allPossibleSets keeps changing.
            for(int j=0;j<newSizeofAllPossibleSets;j++){
                //make a new sublist of the index of the new number and the current sublist
                ArrayList<Integer>newList = createSubset(i,allPossibleSets.get(j));
                double listPrice = sum(newList,masterList);
                if(listPrice>limit){

                    break;
                }else{


                    int newListIndex = allPossibleSets.size()-1;
                    if(listPrice == limit){
                        bestList = newList;
                        break outerLoop;

                    }else{
                        //if checkIfSetIsRelevant is false, then we don't add the set to the list of sets
                        if(!optimize || checkIfSetIsRelevant(newList,allPossibleSets,masterList)){
                            allPossibleSets.add(newList);
                            if(listPrice > sum(bestList,masterList)){
                                bestList = newList;
                            }
                        }

                    }
                }
            }
        }
        System.out.println("final: "+sum(bestList,masterList));
        return createList(bestList,masterList);
    }

    /**
     * Uses the masterList to sum all the objects in passed in list
     * @param list contains a set of Integers acting as indexes to spots in the masterlist
     * @param masterList contains all the real objects that are used for summing list
     * @param <T> acts as a placeholder for the type of object being passed through the arraylist
     * @return returns a double as the sum of list
     */
    public static <T extends Object> double sum(ArrayList<Integer>list, ArrayList<T> masterList){

        double total = 0;

        for(int i=0;i<list.size();i++){
            //list holds an index of the master list
            if(masterList.get(0) instanceof Double){
                total += (Double)masterList.get(list.get(i));
            }else if(masterList.get(0) instanceof SongEntry){
                double duration = ((SongEntry)(masterList.get(list.get(i)))).getDuration();
                duration = duration/60;
                total += duration;
                //System.out.println(total);
            }

        }
        return total;
    }

    /**
     * Sums master set
     * @param masterList used as the int being added to the baseList
     * @return the sum of the items in the masterList
     */
    public static <T extends Object> double sumMasterSet(ArrayList<T> masterList){
        double total = 0;

        for(int i=0;i<masterList.size();i++){
            //list holds an index of the master list
            if(masterList.get(0) instanceof Double){
                total += (Double)masterList.get(i);
            }else if(masterList.get(0) instanceof SongEntry){
                double duration = ((SongEntry)(masterList.get(i))).getDuration();
                duration = duration/60;
                total += duration;
                //System.out.println(total);
            }

        }
        return total;
    }

    /**
     * Creates a clone of the base Integer ArrayList being passed in, adds a new integer to it, and returns the new list
     * @param integer used as the int being added to the baseList
     * @param baseList used as the list integer is being added to
     * @return returns a new subset of the baseList and the added integer
     */
    public static ArrayList<Integer> createSubset(int integer, ArrayList<Integer>baseList){
        ArrayList<Integer>newSubset = new ArrayList<>(baseList);
        newSubset.add(integer);
        return newSubset;
    }

    /**
     * Turns a list of indexes to an ArrayList of the object
     * @param indexList list containing indexes to masterList
     * @param masterList ArrayList containing objects
     * @param <T> acts as a placeholder for the type of object passed into ArrayList
     * @return a new Arraylist made up of objects of type T that were specified in indexList
     */
    public static <T> ArrayList<T> createList(ArrayList<Integer>indexList,ArrayList<T>masterList){
        ArrayList<T>newList = new ArrayList<T>();

        for(int i=0;i<indexList.size();i++){
            newList.add(masterList.get(indexList.get(i)));
        }
        return newList;
    }

    /**
     * Check's if a set of the same value of another set allPossibleSets
     * @param currentSet the base set of indexes that you are comparing to the rest of the sets
     * @param allPossibleSubsets the list of sets of indexes that get compared to base set
     * @param masterList the set containing your main Objects
     *                   used for passing into the sum() method to find if 2 sets
     * @param <T> used as a placeholder for the type of ArrayList being passed in
     * @return false if the currentSet sum is the same as any of the sums in allPossibleSubsets
     */
    public static <T extends Object> boolean checkIfSetIsRelevant(ArrayList<Integer>currentSet, ArrayList<ArrayList<Integer>>allPossibleSubsets,ArrayList<T>masterList){
        double currentSetSize = sum(currentSet,masterList);
        currentSetSize = Math.round(currentSetSize*100)/100;
        for(int i=0;i<allPossibleSubsets.size();i++){
            double newSetSize = sum(allPossibleSubsets.get(i),masterList);
            newSetSize = Math.round(newSetSize*100)/100;
            if(currentSetSize==newSetSize){
                //System.out.println("deleted");
                return false;
            }
        }
        return true;
    }


}



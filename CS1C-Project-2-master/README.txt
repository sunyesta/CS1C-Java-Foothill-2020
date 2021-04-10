project folder:
sunyesta-cs1c-project02/


Brief description of submitted files:

src/cs1c/MillionSongDataSubset.java
    - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.

src/cs1c/SongEntry.java
    - One object of class SongEntry stores a simplified version of the genre data set from 
      the Million Song Dataset.
      http://labrosa.ee.columbia.edu/millionsong/blog/11-2-28-deriving-genre-dataset
    

src/cs1c/TimeConverter.java
    - Converts duration into a string representation.

src/subsetsum/FoothillTunesStore.java
    -  An object of type FoothillTunesStore creates an object of type MillionSongDataSubset,
       which in turn parses a JSON data set with a given file path. The parsed data set
       is stored in an array of SongEntry objects.
       Next, it reads prompts the user for a play list duration.
       Uses an object of type subsetsum.SubsetSum to make a play list of SongEntry objects
       that best match the given duration.
      

src/subsetsum/GroceriesFileReader.java
    - Reads the grocery list

src/subsetsum/ShoppingBag.java
    - An object of type ShoppingBag class creates an object of type subset sum to find a best
      possible grocery shopping list within the given budget.


src/subsetsum/ShoppingBag.java
    - An object of type ShoppingBag class creates an object of type subset sum to find a best
      possible grocery shopping list within the given budget.


src/subsetsum/SubsetSum.java
    - Finds the subset that is as close to the input limit as possible

resources/groceries.txt
    - Contains a list of groceries


resources/music_genre_subset.json
    - Contains a list of songs


resources/RUN.txt
    - contains the output of ShoppingBag.java and FoothillTunesStore.java


src/README.txt
    - description of submitted files
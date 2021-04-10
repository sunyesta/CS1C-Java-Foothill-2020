project folder:
sunyesta-cs1c-project05/


Brief description of submitted files:

src/cs1c/MillionSongDataSubset.java
    - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.

src/cs1c/SongEntry.java
    - One object of class SongEntry stores a simplified version of the genre data set from
      the Million Song Dataset.
      http://labrosa.ee.columbia.edu/millionsong/blog/11-2-28-deriving-genre-dataset


src/cs1c/TimeConverter.java
    - Converts duration into a string representation.

src/lazyTrees/FoothillTunesStore.java
    -   Simulates a music playlist scenario of removing and adding songs to a store's tunes.
        Implements BST with lazy deletion to keep track of total tunes ("deleted" + non deleted)
        and current tunes (non deleted only).


src/lazyTrees/Item.java
    -  One object of Item class represents one item in the inventory, with two class members.

src/lazyTrees/LazySearchTree.java
    - Creates a lazy search tree for use in the super market and foothill tunes store classes


src/lazyTrees/PrintObject.java
    - Prints objects for traversing over the LazySearchTree


src/lazyTrees/SuperMarket.java
    - Simulates the market scenario of buying and adding items to a store's inventory.
      Implements BST with lazy deletion to keep track of total inventory ("deleted" + non deleted)
      and current inventory (non deleted only).


src/lazyTrees/Traverser.java
    - The traverser used for the LazySearchTree

resources/inventory_invalid_removal.txt
    - Used for testing showing an Item with a count of 1 in the inventory and the user's request to buy the item twice

resources/inventory_log.txt
    - Used for testing testing adding and removing from the lazy search tree

resources/inventory_log_custom.txt
    - Used for testing only removing from the tree

resources/inventory_short.txt
    - Used for testing shorter testing adding and removing from the lazy search tree

resources/inventory_invalid_removal.txt
    - Used for testing showing an Item with a count of 1 in the inventory and the user's request to buy the item twice

resources/music_genre_subset.json
    - Contains a list of songs

resources/RUN.txt
    - contains the output of SuperMarket.java and FoothillTunesStore.java

resources/song_test.txt
    - Used for testing adding and removing from the lazy search tree using SongEntries

resources/song_test2.txt
    - Used for testing adding and removing songs with songs that aren't in the list anymore

src/README.txt
    - description of submitted files
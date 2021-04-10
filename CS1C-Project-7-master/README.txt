project folder:
sunyesta-cs1c-project07/


Brief description of submitted files:

src/cs1c/MillionSongDataSubset.java
    - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.

src/cs1c/SongEntry.java
    - One object of class SongEntry stores a simplified version of the genre data set from
      the Million Song Dataset.
      http://labrosa.ee.columbia.edu/millionsong/blog/11-2-28-deriving-genre-dataset


src/cs1c/TimeConverter.java
    - Converts duration into a string representation.

src/hashTables/FHhashQP.java
    - creates a hash table


src/hashTables/HashEntry.java
    - A child class of FHhashQP that allows searching through the hashtable with keys

src/hashTables/MyTunes.java
    - Tests the functionality of FHhashQPwFind.java.
      * Specifically checks for implementation of find() function to return an object associated with a given key input.

src/hashTables/SongCompInt.java
    - Wrapper class for a SongEntry object. We will use this to compare objects based on the songs int id field.

src/hashTables/SongsCompGenre.java
    - Wrapper class for a SongEntry object. We will use this to compare objects based on the songs String genre field. We will use this to determine the number of songs in each genre.

src/hashTables/TableGenerator.java
    - creates and populate two hash tables of type FHhashQPwFind class, one for each wrapper class

resources/empty.txt
    - Testing searching for empty inputs

resources/blank.txt
    - Testing searching for nothing

resources/findGenres.txt
    - used for testing searching for genres

resources/findGenresRepeated.txt
    - used for testing searching for genres in a repeated list

resources/findIDs.txt
    - used for testing searching for song ids

resources/findIDsRepeated.txt
    - used for testing searching for song ids in a repeated list

resources/music_genre_subset.json
    - Contains a list of songs

README.txt
    - description of submitted files
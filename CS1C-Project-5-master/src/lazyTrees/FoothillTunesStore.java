package lazyTrees;

import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Simulates a music playlist scenario of removing and adding songs to a store's tunes.
 * Implements BST with lazy deletion to keep track of total tunes ("deleted" + non deleted)
 * and current tunes (non deleted only).
 *
 * @author Foothill College, Marian Zlateva
 */
public class FoothillTunesStore {
    public static final boolean SHOW_DETAILS = true;


    // Note: Each class must be placed in its own .java file unless explicitly
    //       described as a nested class. See Program Guidelines for details.
    PrintObject<SongEntry> printObject = new PrintObject<SongEntry>();

    // The data structure, which we use to add and remove songs.
    private LazySearchTree<SongEntry> tunes;

    /**
     * Instantiates tunes to be a lazyTrees.LazySearchTree of song objects.
     */
    public FoothillTunesStore() {
        tunes = new LazySearchTree<SongEntry>();
    }

    /**
     * Add a new song with the name as in parameter into tunes.
     *
     * @param title The song to be added to the tunes tree.
     */
    public void addToTunes(String title, SongEntry[]allSongs) {
        // Create a temporary object to hold the song.
        tunes.insert(getSong(title,allSongs));
    }

    public SongEntry getSong(String title,SongEntry[]allSongs){
        System.out.println("title: "+title);
        for (SongEntry currentSong : allSongs) {
            if ((currentSong.getTitle()).equals(title)) {
                return currentSong;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * If the song exists in tunes, soft delete it from tunes
     *
     * @param title The song to be removed to the tunes tree.
     */
    public void removeFromTunes(String title, SongEntry[] allSongs) {
        SongEntry tmp = getSong(title,allSongs);


        boolean isFound = tunes.contains(tmp);

        // check if the song exists in the tunes disregarding lazy deletion
        if (!isFound) {
            throw new NoSuchElementException();
        }
        //bool value
        SongEntry found = tunes.find(tmp);


        if (found == null) {
            throw new NoSuchElementException();
        }
        // if the song has one left in stock,
        // then decrement its count and lazy delete it in the tree.
        else{
            tunes.remove(tmp);
        }

    }


    /**
     * Display the first song and last time of the soft tree in lexical order.
     */
    public void showFirstAndLastSong(String message) {
        System.out.println("\n" + message);


        try {
            SongEntry min = tunes.findMin();
            System.out.println("First song: " + min.toString());
        } catch (Exception NoSuchElementException) {
            System.out.println("Warning: minimum element not found!");
        }

        try {
            SongEntry max = tunes.findMax();
            System.out.println("Last song: " + max.toString());
        } catch (Exception NoSuchElementException) {
            System.out.println("Warning: maximum element not found!");
        }

    }

    /**
     * Shows the state of the tree by displaying:
     * - the *hard* tunes, which includes deleted nodes.
     * - the *soft* tunes, which excludes deleted nodes.
     *
     * @param message  Additional details about the state.
     * @param showTree Set to true if we want to display the contents of the tree
     */
    protected void displayTunesState(String message, boolean showTree) {
        System.out.println("\n" + message);
        System.out.println("\"hard\" number of unique songs (i.e. mSizeHard) = " + tunes.sizeHard());
        System.out.println("\"soft\" number of unique songs (i.e. mSize) = " + tunes.size());

        if (!showTree)
            return;

        System.out.println("\nTesting traversing \"hard\" tunes:");


        // NOTE: Here, we call the public version.
        tunes.traverseHard(printObject);


        System.out.println("\n\nTesting traversing \"soft\" tunes:");


        // NOTE: Here, we call the public version.
        tunes.traverseSoft(printObject);
        System.out.println("\n");
    }

    public static void main(String[] args) {

        MillionSongDataSubset millionSongDataSubset = new MillionSongDataSubset("resources/music_genre_subset.json");
        SongEntry[] allSongs = millionSongDataSubset.getArrayOfSongs();

        final String TESTFILE = "resources/song_test.txt";
        //final String TESTFILE = "resources/song_test2.txt";

        System.out.printf("Test file: %s \n", TESTFILE);

        FoothillTunesStore mySongs = new FoothillTunesStore();

        File infile = new File(TESTFILE);

        try {
            Scanner input = new Scanner(infile);

            String line = "";
            int lineNum = 0;
            while (input.hasNextLine()) {
                lineNum++;
                line = input.nextLine();
                String[] tokens = line.split(" ",2);

                String selection = tokens[0];
                String songName = tokens[1];

                String message = "at line #" + lineNum + ": " + line;

                // When an song is added:
                // If the song is not in our tunes,
                // create a new entry in our tunes.
                // Otherwise, increment the count of the song.
                if (selection.equals("add")) {
                    mySongs.addToTunes(songName,allSongs);

                    // NOTE: Currently displaying the contents is disabled to reduce cluttering the output.
                    // Suggestion: To start, enable displaying the contents of the tree to help you debug.
                    if (SHOW_DETAILS)
                        mySongs.displayTunesState("\nUpdate " + message, true);
                }

                // When an song is bought:
                // Decrement the count of the song.
                // If the song is out of stock,
                // remove the song from tunes.
                //
                // Note: buying an out of stock song, is invalid. Handle it appropriately.
                else if (selection.equals("remove")) {
                    try {
                        mySongs.removeFromTunes(songName,allSongs);

                        // NOTE: Currently displaying the contents is disabled to reduce cluttering the output.
                        // Suggestion: To start, enable displaying the contents of the tree to help you debug.
                        if (SHOW_DETAILS)
                            mySongs.displayTunesState("\nUpdate " + message, true);
                    } catch (java.util.NoSuchElementException ex) {
                        // NOTE: Ideally we'd print to the error stream,
                        //       but to allow correct interleaving of the output
                        //       we'll use the regular output stream.
                        System.out.printf("\nWarning: Unable to fulfill request: %s \n", message);
                        System.out.printf("Warning: song is not in the store.\n", songName);
                    }
                } else {
                    System.out.println("Warning: tunes selection not recognized!");
                }

                // Display the first song and the last song before checking
                // if it's time to clean up our tunes.
                if (SHOW_DETAILS)
                    mySongs.showFirstAndLastSong(message);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Display the tunes
        System.out.println("\n");
        mySongs.displayTunesState("\nFinal state of tunes:", true);

        // flush the error stream
        System.err.flush();

        System.out.println("\nDone with FoothillTunesStore.");
    }
}

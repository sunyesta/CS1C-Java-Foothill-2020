package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * creates and populate two hash tables of type FHhashQPwFind class, one for each wrapper class
 * @author Foothill College, Marian Zlateva
 */
public class TableGenerator {
    FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs;
    FHhashQPwFind<String, SongsCompGenre> tableOfSongGenres;
    ArrayList<String>genreNames;


    /**
     * Creates a table of song ids and another table of song genres and an arraylist of genre names
     */
    public TableGenerator() {
        tableOfSongIDs = new FHhashQPwFind<>();
        tableOfSongGenres = new FHhashQPwFind<>();
        genreNames = new ArrayList<>();


    }

    /**
     * populates the tableOfSongIDs
     * @param allSongs all the songs
     * @return the table of song ids
     */
    public FHhashQPwFind<Integer, SongCompInt>  populateIDtable(SongEntry[] allSongs) {
        for (SongEntry song : allSongs) {
            tableOfSongIDs.insert(new SongCompInt(song));
        }

        return  tableOfSongIDs;
    }


    /**
     * populates the tableOfGenres hash table and ArrayList of genre names.
     *
     * @param allSongs all the songs
     * @return a table of song genres
     */
    public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs) {

        for (SongEntry song : allSongs) {
            try {
                SongsCompGenre existingRecordInHashTable = tableOfSongGenres.find(song.getGenre());
                existingRecordInHashTable.addSong(song);
            } catch (NoSuchElementException e) {
                tableOfSongGenres.insert(new SongsCompGenre(song.getGenre()));
                genreNames.add(song.getGenre());
                SongsCompGenre newGenre = tableOfSongGenres.find(song.getGenre());
                newGenre.addSong(song);
            }
        }
        return tableOfSongGenres;
    }

    /**
     * @return a list of genre names
     */
    public ArrayList<String> getGenreNames() {
        return genreNames;
    }
}

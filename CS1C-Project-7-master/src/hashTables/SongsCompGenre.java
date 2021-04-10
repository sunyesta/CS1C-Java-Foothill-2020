package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * Wrapper class for a SongEntry object. We will use this to compare objects based on the songs String genre field. We will use this to determine the number of songs in each genre.
 * @author Foothill College, Marian Zlateva
 */
public class SongsCompGenre implements Comparable<String> {
    String genreName;
    ArrayList<SongEntry> songsOfgenre;

    public SongsCompGenre(String genreName) {
        this.genreName = genreName;
        songsOfgenre = new ArrayList<>();
    }

    /**
     * compares the genre name to the key
     * @param key should be another genre name
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(String key) {
        return genreName.compareTo(key);
    }

    /**
     * @return the hashCode for the genre Name
     */
    public int hashCode() {
        return genreName.hashCode();
    }

    /**
     * adds a song to the list of songs.
     * @param newSong the new song
     */
    void addSong(SongEntry newSong) {
        songsOfgenre.add(newSong);
    }

    /**
     * @return the name of the genre
     */
    String getName() {
        return genreName;
    }

    /**
     * @return the songs in the genre
     */
    ArrayList<SongEntry> getData() {
        return songsOfgenre;
    }
}

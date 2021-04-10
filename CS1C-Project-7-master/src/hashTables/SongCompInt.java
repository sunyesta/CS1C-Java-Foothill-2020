package hashTables;

import cs1c.SongEntry;

/**
 * Wrapper class for a SongEntry object. We will use this to compare objects based on the songs int id field.
 * @author Foothill College, Marian Zlateva
 */
public class SongCompInt implements Comparable<Integer>{
    SongEntry song;

    /**
     * instantiates song
     * @param song
     */
    public SongCompInt(SongEntry song){
        this.song = song;
    }

    /**
     * compares the song id to the key
     * @param key should be another song id
     * @return songID-key
     */
    @Override
    public int compareTo(Integer key) {
        return song.getID()-key;
    }

    /**
     * overrides the hashCode function of the Object class to make the new hashCode the song's id
     * @return the song's ID
     */
    public int hashCode(){
        return song.getID();
    }


}

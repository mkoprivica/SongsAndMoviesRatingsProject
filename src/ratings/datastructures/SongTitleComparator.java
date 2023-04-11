package ratings.datastructures;
import ratings.Song;


public class SongTitleComparator extends Comparator<Song>{


    @Override
    public boolean compare(Song a, Song b) {
        String a_title = a.getTitle();
        String b_title = b.getTitle();
        return a_title.compareToIgnoreCase(b_title) < 0;
    }

}